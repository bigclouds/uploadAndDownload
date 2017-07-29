import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test{
    public static void main(String[] args){
        Path path = Paths.get(args[0]);  
        String contentType = null;  
        try {  
            contentType = Files.probeContentType(path);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        System.out.println("File content type is : " + contentType);   
    }
}
