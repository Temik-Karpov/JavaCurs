package shipment;

import schedule.CargoType;

public class Statistic
{
    private int numberOfShips;
    private int maxDelay;
    private int averageDelay;
    private int fine;
    private int averageQueueSize;
    private int liquidCount;
    private int looseCount;
    private int containerCount;
    private int cranesCount;
    public Statistic()
    {
        liquidCount = 0;
        looseCount = 0;
        containerCount = 0;
        numberOfShips = 0;
        maxDelay = 0;
        averageDelay = 0;
        fine = 0;
        cranesCount = 0;
        averageQueueSize = 0;
    }
    
    public void setCranesCount(CargoType cargoType, int craneCount)
    {
        switch(cargoType)
        {
            case LOOSE:
                this.looseCount = craneCount;
                break;
            case LIQUID:
                this.liquidCount = craneCount;
                break;
            case CONTAINER:
                this.containerCount = craneCount;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cargoType);
        }
        this.cranesCount += craneCount;
    }

    public void setAverageQueueSize(int queueSize)
    {
        this.averageQueueSize = queueSize / this.cranesCount;
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

    public void setFine(int looseFine, int liquidFine, int containerFine)
    {
        this.fine = looseFine + liquidFine + containerFine;
    }

    public void setNumberOfShips(int numberOfShips)
    {
        this.numberOfShips = numberOfShips;
    }

    public void printCraneInfo()
    {
        System.out.println("Fine = " + this.fine);
        System.out.println("Count of loose cranes " + this.looseCount);
        System.out.println("Count of liquid cranes " + this.liquidCount);
        System.out.println("Count of container cranes " + this.containerCount);
    }

    public void printShipInfo()
    {
        System.out.println("Count of ships = " + this.numberOfShips);
        System.out.println("MaxDelay =  " + this.maxDelay);
        System.out.println("AverageDelay = " + this.averageDelay);
        //System.out.println("AverageQueueSize = " + this.averageQueueSize);
    }
}
