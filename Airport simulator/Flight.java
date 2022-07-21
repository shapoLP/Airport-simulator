import java.util.Random;

public class Flight extends Thread
{
    private final int flightNumber;
    private final Airport departingAirport;
    private final Airport landingAirport;
    private final Random rand = new Random();
    public Flight(int flightNumber, Airport departingAirport, Airport landingAirport)
    {
        this.flightNumber = flightNumber;
        this.departingAirport = departingAirport;
        this.landingAirport = landingAirport;
    }
    public void run (){

        try {
            int departRunway = this.departingAirport.depart(this.flightNumber);
            sleep((rand.nextInt(5)*100));
            this.departingAirport.freeRunway(departRunway, this.flightNumber);
            sleep(rand.nextInt(10)*50);
            int landRunway = this.landingAirport.land(this.flightNumber);
            sleep((rand.nextInt(5)*100));
            this.landingAirport.freeRunway(landRunway, this.flightNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
   }