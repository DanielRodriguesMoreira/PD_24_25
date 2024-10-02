package ex_09_udp_time_incomplete;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    @Override
    public String toString() {
        return "Horas: " + this.hours + " ; Minutos: " + this.minutes + " ; Segundos: " + this.seconds;
    }
}
