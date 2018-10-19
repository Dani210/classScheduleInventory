package dal;

import bl.AbstractInventory;
import bl.Appointment;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import nwl.Net;

public class DAL {

    private Net net;

    public DAL() {
        net = new Net();
    }
    
    

    public void saveToLocal(File localFile, ArrayList entries) {

    }

    public ArrayList loadFromLocal(File localFile) {
        ArrayList schedules = new ArrayList();

        return schedules;
    }

    public void saveToRemote(File remoteFile, ArrayList entries) {

    }

    public ArrayList loadFileFromRemote(String address,
            String username, String password, File localFile)
            throws IOException
    {
        
        localFile = net.loadFileFromRemote(address, username, password, localFile);
        
        Scanner scan;
        

        //localFile to ArrayList
        ArrayList<Appointment> entries = new ArrayList();

        if (localFile != null) {
            scan = new Scanner(localFile);

            String goodLine;
            String[] tokens;

            String title, desc;
            LocalDate date;

            while (scan.hasNextLine()) {
                goodLine = scan.nextLine();
                if (goodLine.contains("<td>")) {
                    System.out.println(goodLine);
                    try {
                        tokens = goodLine.split(" -> ");
                        title = tokens[0];
                        desc = tokens[1];
                        date = LocalDate.parse(tokens[2], AbstractInventory.DATEFORMATTER);
                        entries.add(new Appointment(title, desc, date));
                    } catch (DateTimeParseException e) {
                        throw e;
                    }

                }
            }
        }

        return entries;
    }
}
