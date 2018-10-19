package bl;

import java.time.LocalDate;

public class Schedule{
    private final String subject;
    private final String description;
    private final LocalDate dateDue;

    
    public Schedule(String subject, String description, LocalDate dateDue) {
        this.subject = subject;
        this.description = description;
        this.dateDue = dateDue;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }
    
    public String getDateDue(){
        return dateDue.format(AbstractInventory.DATEFORMATTER);
    }
    

    @Override
    public String toString() {
        return subject + " -> " + description + " -> " + getDateDue();
    }

    
}
