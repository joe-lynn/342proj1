import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Zak on 10/20/2016.
 */
public class Employee extends Thread {

    private Clock clock;
    private String teamNumber;
    private String employeeNumber;
    private Firm firm;
    private boolean atWork;
    private boolean atLunch;
    private boolean atMeeting;
    private boolean waiting;
    private int currentTime;
    private int chanceOfQuestion;

    // The following are for end of simulation statistics
    private int timeWorking;
    private int timeEating;
    private int timeMeetings;
    private int timeWaiting;


    public Employee(Clock clock, String teamNumber, String employeeNumber, Firm firm, int chanceOfQuestion){
        this.clock = clock;
        this.teamNumber = teamNumber;
        this.employeeNumber = employeeNumber;
        this.firm = firm;
        this.chanceOfQuestion = chanceOfQuestion;
        this.atWork = false;
        this.atLunch = false;
        this.atMeeting = false;
        this.waiting = false;
        this.currentTime = 0;
    }

    public void run(){
        while(this.clock.ticking()){
            if(this.clock.currentTime() > this.currentTime){
                //minute has passed, do only one thing per minute
                this.currentTime = this.clock.currentTime();
                if(this.currentTime >= 480 && this.currentTime <= 510 && !this.atWork ){
                    this.atWork = true;
                    announce("arrives at work", this.currentTime);
                }
                else if(this.currentTime >= 660 && this.currentTime <= 720 && this.atWork && !this.atLunch && this.timeEating < 1){
                    this.atLunch = true;
                    announce("goes to lunch", this.currentTime);
                }
                else if(this.atLunch && this.timeEating >= 30){
                    this.atLunch = false;
                    announce("goes back to work", this.currentTime);
                }
                else if(this.currentTime >= 930 && this.atWork && this.timeWorking >= 480){
                    this.atWork = false;
                    announce("leaves for the day", this.currentTime);
                }

                //Statistics gathered at the end of each minute based on state
                if(this.atWork && !this.atLunch){
                    this.timeWorking++;
                }
                if(this.atLunch){
                    this.timeEating++;
                }
                if(this.atMeeting){
                    this.timeMeetings++;
                }
                if(this.waiting){
                    this.timeWaiting++;
                }
            }
            else{
                this.yield();
            }
        }
    }

    private void announce(String event, int time){
        String currentTimeString = "";
        if(time % 60 < 10){
            currentTimeString = String.format("%d:%d0",(time - (time % 60)) / 60, time % 60 );
        }
        else{
            currentTimeString = String.format("%d:%d",(time - (time % 60)) / 60, time % 60 );
        }
        System.out.printf("%s Developer %s%s %s\n", currentTimeString, this.teamNumber, this.employeeNumber, event);
    }

    private boolean question(){
        int random = ThreadLocalRandom.current().nextInt(1,1001);
        if(random <= this.chanceOfQuestion){
            return true;
        }
        else{
            return false;
        }
    }
}
