package util.network;


import controller.SignUpController;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.logger.MyLogManager;

import java.util.Map;

@Component
public class HttpUtil {

    private static MyLogManager myLogManager =new MyLogManager(SignUpController.class);

    @Autowired
    private ConnectionPool connectionPool;

    public String doPostWithHeaders(Map<String, String> headers, String requestUrl,
                                    String content, String contentType) {
        HttpPost postMethod = null;
        CloseableHttpClient httpClient = connectionPool.getConnection();
        String response = null;
        postMethod = new HttpPost(requestUrl);

        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                postMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse httpResponse = null;
        try {
            postMethod.setEntity(new StringEntity(content, contentType, "UTF-8"));
            myLogManager.info("Sos api url " + requestUrl);
            long startTime = System.currentTimeMillis();
            httpResponse = httpClient.execute(postMethod);
            int status = httpResponse.getStatusLine().getStatusCode();
            myLogManager.info("Sos Response code for url + " + " {" + status + "} " + (System.currentTimeMillis() - startTime));
            if (status == HttpStatus.SC_OK) {
                response = new BasicResponseHandler().handleResponse(httpResponse);
            }
            return response;
        } catch (Exception exception) {
            myLogManager.error(exception);

        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (Exception e) {
                myLogManager.error(e);
            }
        }
        return null;
    }
}
