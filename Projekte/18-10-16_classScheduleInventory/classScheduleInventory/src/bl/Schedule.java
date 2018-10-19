package bl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Schedule{
    private final String subject;
    private final String description;
    private final LocalDate dateDue;

    private static final DateTimeFormatter DATEFORMATTER = 
            DateTimeFormatter.ofPattern("EEE, dd.MM.yyyy");
    public static DateTimeFormatter getDateFormatter(){
        return Schedule.DATEFORMATTER;
    }
    
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
        return dateDue.format(DATEFORMATTER);
    }
    

    @Override
    public String toString() {
        return subject + " -> " + description + " -> " + getDateDue();
    }

    
}
