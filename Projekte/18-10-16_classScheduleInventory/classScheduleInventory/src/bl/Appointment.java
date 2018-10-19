package bl;

import java.time.LocalDate;

public class Appointment{
    private final String title;
    private final String description;
    private final LocalDate date;
    
    
    public Appointment(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }
    
    

    public String getSubject() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    
    public String getDate(){
        return date.format(AbstractInventory.DATEFORMATTER);
    }
    

    @Override
    public String toString() {
        return title + " -> " + description + " -> " + getDate();
    }

}
