package bl;

import dal.DAL;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import nwl.Net;

public abstract class AbstractInventory <Type>
        extends AbstractListModel<Type>
{
    protected static DAL dal = new DAL();
    protected ArrayList<Type> entries;
    protected Net net;
    
    public static final DateTimeFormatter DATEFORMATTER = 
            DateTimeFormatter.ofPattern("EEE, dd.MM.yyyy");
    
    public AbstractInventory(){
        entries = new ArrayList();
        net = new Net();
    }
    
    public void add(Type t){
        entries.add(t);
        this.fireContentsChanged(entries, 0, entries.size());
    }
    
    public void remove(int i){
        entries.remove(i);
        this.fireContentsChanged(entries, 0, entries.size());
    }
    
    
    
    public void saveFileToLocal(File localFile){
        AbstractInventory.dal.saveToLocal(localFile, entries);
    }
    
    public void saveFileToRemote(File file){
        
    }
    
    public void loadFileFromLocal(File file){
        
    }
    
    public void loadFileFromRemote(String address, String username,
            String password, File localFile)
            throws UnknownHostException, MalformedURLException, IOException,
                DateTimeParseException
    {
        this.entries = AppointmentInventory.dal.loadFileFromRemote(address, username, password, localFile);
        this.fireContentsChanged(entries, 0, entries.size());
    }

    @Override
    public Type getElementAt(int index){
        return this.entries.get(index);
    }

    @Override
    public int getSize(){
        return this.entries.size();
    }
    
    public static final void saveUserData(String username, File file)
            throws IOException
    {
        AbstractInventory.dal.saveUserData(username, file);
    }
    
    public static final String loadUserData(File file)
            throws IOException
    {
        return AbstractInventory.dal.loadUserData(file);
    }
    
}
