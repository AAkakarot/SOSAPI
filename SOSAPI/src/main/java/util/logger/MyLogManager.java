package util.logger;

public class MyLogManager {
    MyLogger myLogger;
 public  MyLogManager(final Class clazz){
       myLogger=new MyLogger();
   }
   public  void info(String s){
        myLogger.info(s);
    }
  public   void error(String s){
        myLogger.error(s);
    }
   public  void info(Exception s){
        myLogger.info(s);
    }
  public   void error(Exception s){
        myLogger.error(s);
    }
}
