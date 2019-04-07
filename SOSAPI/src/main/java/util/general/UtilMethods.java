package util.general;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UtilMethods {
    public static ArrayList<String> convertToVehcileArray(String s){
        String arr[]=s.split(" ");
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<Integer.parseInt(arr[0]);i++){
            arrayList.add(arr[i+1]);
        }
        return arrayList;
    }

    public static void copyFile(MultipartFile uploadedFile, File result) throws Exception
    {
        try
        {
            if (result.exists()) {
                if (result.delete())
                    System.out.println(" File Deleted !");
                else
                    System.out.println(" File Deletion failed !");
            }

            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[4096]; // 4K is
            // good
            // enough?
            int bytesRead;
            InputStream inputStream = uploadedFile.getInputStream();
            while ((bytesRead = inputStream.read(buffer)) > 0)
            {
                fileOutputStream.write(buffer, 0, bytesRead);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
    }


}
