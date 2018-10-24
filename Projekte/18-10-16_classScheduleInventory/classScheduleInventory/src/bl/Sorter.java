package bl;

import java.time.LocalDate;
import java.util.Comparator;

public class Sorter implements Comparator
{
    @Override
    public int compare(Object o1, Object o2) {
        Schedule s1, s2;
        Appointment a1, a2;
        
        if(o1 instanceof Schedule
                && o2 instanceof Schedule)
        {
            s1 = (Schedule) o1;
            s2 = (Schedule) o2;
            
            return LocalDate.parse(s1.getDateDue(), 
                    AbstractInventory.DATEFORMATTER).
                    compareTo(LocalDate.parse(s2.getDateDue(),
                            AbstractInventory.DATEFORMATTER));
        }
        else if(o1 instanceof Appointment
                && o2 instanceof Appointment)
        {
            a1 = (Appointment) o1;
            a2 = (Appointment) o2;
            
            return LocalDate.parse(a1.getDate(), 
                    AbstractInventory.DATEFORMATTER).
                    compareTo(LocalDate.parse(a2.getDate(),
                            AbstractInventory.DATEFORMATTER));
        }
        return 0;
    }
    
}
