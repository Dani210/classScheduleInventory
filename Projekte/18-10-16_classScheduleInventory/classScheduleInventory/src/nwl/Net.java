package nwl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Net {
    
    public File loadFileFromRemote(String address, String username, String password,
            File localFile)
            throws MalformedURLException, IOException
    {
        
        //remote File to local File
        Scanner scan = new Scanner(new URL(
                "ftp://" + username + ":" + password + "@" + address
        ).openStream());
        BufferedWriter buf = new BufferedWriter(new FileWriter(localFile));
        
        while(scan.hasNextLine()){
            buf.write(scan.nextLine());
            buf.newLine();
        }
        scan.close();
        buf.close();
        
        return localFile;
    }
    
    public void saveFileToRemote(String address, String username, String password, 
            File localFile)
    
    {
        
    }
}
