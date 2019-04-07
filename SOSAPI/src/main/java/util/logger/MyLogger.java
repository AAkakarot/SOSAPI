package util.logger;

public class MyLogger {
    void info(String s){
        System.out.print(s);
    }
    void error(String s){
        System.out.print(s);
    }
    void info(Exception s){
        System.out.print(s);
        s.printStackTrace();
    }
    void error(Exception s){
        System.out.print(s);
        s.printStackTrace();
    }
}
