package shipment;

import schedule.CargoType;

public class Statistic
{
    private int shipsCount;
    private int maxDelay;
    private int averageDelay;
    private int liquidCount;
    private int looseCount;
    private int containerCount;
    public Statistic()
    {
        liquidCount = 0;
        looseCount = 0;
        containerCount = 0;
        shipsCount = 0;
        maxDelay = 0;
        averageDelay = 0;
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
    
    public void printStatistic(CargoType cargoType, int craneCount, int fineAmount)
    {
        String stringCount;
        String stringFine;
        switch(cargoType)
        {
            case LOOSE:
                stringCount = "Count of loose cranes ";
                stringFine = "Fine amount of loose = ";
                break;
            case LIQUID:
                stringCount = "Count of liquid cranes ";
                stringFine = "Fine amount of liquid = ";
                break;
            case CONTAINER:
                stringCount = "Count of container cranes ";
                stringFine = "Fine amount of container = ";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cargoType);
        }
        System.out.println(stringCount + craneCount);
        System.out.println(stringFine + fineAmount);
    }
}
