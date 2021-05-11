package shipment;

public class Statistic
{
    private int shipsCount;
    private int maxDelay;
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
    }

    public Statistic(int shipsCount, Port looseCranes, Port liquidCranes, Port containerCranes)
    {
        this.shipsCount = shipsCount;
        this.looseCount = looseCranes.getCountCranes();
        this.liquidCount = liquidCranes.getCountCranes();
        this.containerCount = containerCranes.getCountCranes();
        this.maxDelay = Math.max(looseCranes.getCurrentDelay(), Math.max(liquidCranes.getCurrentDelay(), containerCranes.getCurrentDelay()));

    }
    public void printStatistic()
    {
        System.out.println("Count of ships " + this.shipsCount);
        System.out.println("MaxDelay " + this.maxDelay);
        System.out.println("Count of loose cranes " + this.looseCount);
        System.out.println("Count of liquid cranes " + this.liquidCount);
        System.out.println("Count of container cranes " + this.containerCount);
    }
}
