package utilsencrypt2;

import java.util.Calendar;

public class Time {
    public String getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        //System.out.println("------- Временная метка: " + calendar.getTime() + " --------");
        return "+------ Временная метка: " + calendar.getTime() + " -------+";
    }
}
