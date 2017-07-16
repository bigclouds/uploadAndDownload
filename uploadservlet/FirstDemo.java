public class FirstDemo { 

  /** 
    *API中String的常用方法 
    */ 
  // 查找指定字符串是否存在 
  public static void main(String[] args) { 
    String str1 = "abcdefghijklmnabc"; 
    String s2 = "fileuploadmvc/WEB-INF/jspmsg.jsp";
    String s3 = "/fileuploadmvc/test2";
    System.out.println(s2.indexOf("/jsp")); 
    System.out.println(s3.indexOf("/test")); 
    System.out.println(s3.indexOf("/test2")); 
    // 从头开始查找是否存在指定的字符 
    //System.out.println(str1.indexOf("c")); 
    // 从第四个字符位置开始往后继续查找 
    //System.out.println(str1.indexOf("c", 3)); 
    //若指定字符串中没有该字符则系统返回-1 
    //System.out.println(str1.indexOf("x")); 
  }
}
