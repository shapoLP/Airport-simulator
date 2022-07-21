public class FlightRunway
{
    private final int flightNumber;
    private final boolean isDeparting;
    public FlightRunway(int flightNumber, boolean isDeparting)
    {
        this.flightNumber = flightNumber;
        this.isDeparting = isDeparting;
    }
    public int getFlightNumber()
    {
        return this.flightNumber;
    }
    public boolean isDeparting()
    {
        return this.isDeparting;
    }
}