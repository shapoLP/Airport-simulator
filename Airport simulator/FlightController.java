import java.util.Random;

public class FlightController
{
    public static void main (String[] args){

        Airport BenGurionAirport = new Airport("Ben Gurion", 3);
        Airport JFKAirport = new Airport("JFK", 3);
        Flight flight;
        Random rand = new Random();

        for (int i = 0 ; i < 10 ; i++)
        {
            if ( rand.nextInt(100)%2 == 1 ){
                flight = new Flight(rand.nextInt(50), BenGurionAirport, JFKAirport);
            }
            else {
                flight = new Flight(rand.nextInt(50), JFKAirport, BenGurionAirport);
            }
            flight.start();
        }
    }
}
