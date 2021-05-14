package shipment;

import schedule.CargoType;

public class Statistic
{
    private int numberOfShips;
    private int maxDelay;
    private int averageDelay;
    private int fine;
    private int liquidCount;
    private int looseCount;
    private int containerCount;
    public Statistic()
    {
        liquidCount = 0;
        looseCount = 0;
        containerCount = 0;
        numberOfShips = 0;
        maxDelay = 0;
        averageDelay = 0;
        fine = 0;
    }

    /*public void printStatistic()
    {
        System.out.println("Count of ships " + this.shipsCount);
        System.out.println("MaxDelay " + this.maxDelay);
        System.out.println("AverageDelay " + this.averageDelay);
        System.out.println("Count of loose cranes " + this.looseCount);
        System.out.println("Count of liquid cranes " + this.liquidCount);
        System.out.println("Count of container cranes " + this.containerCount);
    }*/
    
    public void printCraneStatistic(CargoType cargoType, int craneCount)
    {
        String stringCount;
        switch(cargoType)
        {
            case LOOSE:
                stringCount = "Count of loose cranes ";
                break;
            case LIQUID:
                stringCount = "Count of liquid cranes ";
                break;
            case CONTAINER:
                stringCount = "Count of container cranes ";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cargoType);
        }
        System.out.println(stringCount + craneCount);
    }

    public void setMaxDelay(int delay)
    {
        if(this.maxDelay < delay)
        {
            this.maxDelay = delay;
        }
    }

    public void setAverageDelay(int delay, int numberOfShips)
    {
        this.averageDelay = delay / numberOfShips;
    }

    public void setFine(int fine)
    {
        this.fine += fine;
    }

    public void setNumberOfShips(int numberOfShips)
    {
        this.numberOfShips = numberOfShips;
    }

    public void printInfo()
    {
        System.out.println("Count of ships = " + this.numberOfShips);
        System.out.println("MaxDelay =  " + this.maxDelay);
        //System.out.println("Fine = " + this.fine);
        System.out.println("AverageDelay = " + this.averageDelay);
    }
}
