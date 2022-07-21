import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Airport {

    private final int runwayNumber;
    private final String name;
    private final boolean[] runways;
    private final ConcurrentLinkedQueue <FlightRunway> queue;

    public Airport(String name,int runNumber){
        this.name = name;
        this.runwayNumber = runNumber;
        this.runways = new boolean[runNumber];
        this.queue = new ConcurrentLinkedQueue<>();
        System.out.println( name + " Airport" + " with " + runNumber + " runways is good to go..." + "\n");
    }
    public int depart(int flightNumber){
        this.queue.add(new FlightRunway(flightNumber,true));
        synchronized (this)
        {
            while ( getFreeRunway()== -1 || !Objects.requireNonNull(this.queue.peek()).isDeparting())
            {
                try{wait();} catch (InterruptedException e){
                    System.out.println("check again");
                }
            }
            int freeRunway = getFreeRunway();
            this.runways[freeRunway] = true;
            System.out.println("Flight " + this.queue.remove().getFlightNumber() + " from " + this.name +
                    " Airport departs from runway " + (freeRunway + 1) + "\n");
            return freeRunway;
        }
    }
    public int land (int flightNumber){
        this.queue.add(new FlightRunway(flightNumber,false));
        synchronized (this)
        {
            while ( getFreeRunway() == -1 || Objects.requireNonNull(this.queue.peek()).isDeparting())
            {
                try{wait();} catch (InterruptedException e){
                    System.out.println("check again");
                }
            }
            int freeRunway = getFreeRunway();
            this.runways[freeRunway] = true;
            System.out.println("Flight " + this.queue.remove().getFlightNumber() +
                    " landed at " + this.name + " Airport in runway " + (freeRunway + 1) + "\n");
            return freeRunway;
        }
    }
    public void freeRunway(int runNumber, int flightNumber){
        synchronized (this) {
            this.runways[runNumber] = false;
            System.out.println("Flight " + flightNumber + " cleared from runway " + (runNumber + 1)
                    + " at " + this.name + " Airport" + "\n");
            notifyAll();
        }
    }
    private int getFreeRunway(){
        for (int i = 0; i < this.runwayNumber; i++)
            if ( !this.runways[i])
                return i;
        return -1;
    }
}
