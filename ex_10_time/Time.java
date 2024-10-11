package ex_10_time;

import java.io.Serializable;

public class Time implements Serializable {
    private static final long serialVersionUID = 1L;
    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "Hours: " + this.hours + "; Minutes: " + this.minutes + "; Seconds: " + this.seconds + ";";
    }
}