import java.util.ArrayList;

/**
 * Created by Zak on 10/20/2016.
 */
public class Firm {

    private int numberOfTeams;
    private int numberofDevelopers;
    private Manager manager;
    private boolean managerPresent;
    private ArrayList<Thread> standupSpots;

    public Firm(int numberOfTeams, int numberOfDevelopers, Manager manager){
        this.numberofDevelopers = numberOfDevelopers;
        this.numberOfTeams = numberOfTeams;
        this.manager = manager;
        this.standupSpots = new ArrayList<>();
    }

    public synchronized boolean attendMorningStandup(Thread employee){
        if(!this.standupSpots.contains(employee)){
            this.standupSpots.add(employee);
        }
        else if(standupSpots.size() == this.numberOfTeams && this.managerPresent){
            return true;
        }
        return false;
    }

    public synchronized void managerEntersOffice(Thread manager){
        this.managerPresent = true;
    }
}
