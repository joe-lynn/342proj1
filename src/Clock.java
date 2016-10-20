/**
 * Created by Zak on 10/20/2016.
 */
public class Clock extends Thread {

    private int time; // current time stored in minutes
    private int days; // number of days to simulate
    final private static int dayLength = 1440; // 60 minutes in hour, 24 hours in day therefore 1440 minutes
    private int daysPassed; // number of days gone by


    public Clock(int days){
        this.days = days;
        this.time = 0;
        this.daysPassed = 0;
    }

    public void run(){
        while(this.daysPassed < this.days){
            this.time++;
            if(this.time > this.dayLength){
                this.time = 0;
                this.daysPassed++;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        returns the current clock time
     */
    public int currentTime(){
        return this.time;
    }

    /*
        returns true if the clock is still running
     */
    public boolean ticking(){
        return this.daysPassed < this.days;
    }
}
