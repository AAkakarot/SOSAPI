package util.network;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import util.logger.MyLogManager;

import javax.annotation.PostConstruct;

@Component
public class ConnectionPool {

    private static MyLogManager myLogManager =new MyLogManager(ConnectionPool.class);
    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    @Value("${upi.connection.maxConnectionsPerHost:60}")
    String maxConnectionsPerHost;
    @Value("${upi.connection.maxConnections:200}")
    String maxConnections;
    @Value("${upi.connection.socketTimeout:30000}")
    String socketTimeout;
    @Value("${upi.connection.connectionTimeout:10000}")
    String connectionTimeout;
    @Value("${upi.connection.connectionRequestTimeout:10000}")
    String connectionRequestTimeout;


    private CloseableHttpClient httpClient;

    @PostConstruct
    public void setUpConnectionManager() {
        try {
            myLogManager.info("setting up sos connection pool");
            poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(Integer.parseInt(maxConnections));
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(Integer.parseInt(maxConnectionsPerHost));


            ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {

                public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                    // Honor 'keep-alive' header
                    HeaderElementIterator it = new BasicHeaderElementIterator(
                            response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                    while (it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && param.equalsIgnoreCase("timeout")) {
                            try {
                                return Long.parseLong(value) * 1000;
                            } catch (NumberFormatException ignore) {
                            }
                        }
                    }
                    return 60000;
                }
            };

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(Integer.parseInt(connectionRequestTimeout))
                    .setConnectTimeout(Integer.parseInt(connectionTimeout))
                    .setSocketTimeout(Integer.parseInt(socketTimeout)).build();

            httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager)
                    .setDefaultRequestConfig(requestConfig).setKeepAliveStrategy(keepAliveStrategy).build();

            myLogManager.info("sos connection pool setup success");
        } catch (Exception e) {
            myLogManager.error("Error creating sos connection pool");
            myLogManager.error(e);
        }

    }
    public CloseableHttpClient getConnection() {
        return httpClient;
    }

}
