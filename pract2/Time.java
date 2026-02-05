public class Time{
    private int hour;
    private int min;
    private int sec;
    public Time(int hour, int min, int sec){
        if(hour < 0 || hour > 24){
            throw new IllegalArgumentException("Incorrect hour value!");
        }
        this.hour = hour;
        if(min < 0 || min >= 60){
            throw new IllegalArgumentException("Incorrect min value!");
        }
        this.min = min;
        if(sec < 0 || sec >= 60){
            throw new IllegalArgumentException("Incorrect sec value!");
        }
        this.sec = sec;
    }
    public String toUniversal(){
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
    public String toStandard() {
        int newH = hour % 12;
        if (newH == 0) {
            newH = 12;
        }

        String period = (hour < 12) ? "AM" : "PM";
        return String.format("%02d:%02d:%02d %s",newH,hour,min,sec,period);
    }

    public void add(Time time2) {
        int total =
                this.sec + time2.sec +
                (this.min + time2.min) * 60 +
                (this.hour + time2.hour) * 3600;

        total %= 24 * 3600;

        this.hour = total / 3600;
        total %= 3600;
        this.min = total / 60;
        this.sec = total % 60;
    }

    public static void main(String args[]){
        Time t1 = new Time(14,25,34);
        Time t2 = new Time(15,30,19);
        System.out.println(t1.toUniversal());
        System.out.println(t1.toStandard());
        t1.add(t2);
        System.out.println(t1.toStandard());
    }
}