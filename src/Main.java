/**
 * Created by Zak on 10/20/2016.
 */
public class Main {

    final private static int numberOfDays = 1; // number of days to be simulated

    public static void main(String args[]){
        Clock clock = new Clock(numberOfDays);
        Firm firm = new Firm();
        Employee initialTestEmployee = new Employee(clock, "1", "1", firm);
        clock.start();
        initialTestEmployee.start();
    }
}
