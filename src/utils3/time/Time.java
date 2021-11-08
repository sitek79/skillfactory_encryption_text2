package utils3.time;

import java.util.Calendar;

public class Time {
    //int millis = 999;

    public String getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        //System.out.println("------- Временная метка: " + calendar.getTime() + " --------");
        return "+------ Временная метка: " + calendar.getTime() + " -------+";
    }
}