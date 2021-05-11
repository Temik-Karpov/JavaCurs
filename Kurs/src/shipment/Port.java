package shipment;

import schedule.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Port extends Thread
{
    private final int CRANE_COST = 30000;
    private int craneThreads;
    private int currentDelay;
    private List<Ship> ships;
    private ConcurrentLinkedQueue<Ship> queueOfShips;
    private ArrayList<CraneSimulation> listOfCranes;
    private Statistic statistic;

    public Port(List<Ship> ships)
    {
        this.ships = ships;
    }

    @Override
    public void run()
    {
        while (currentDelay >= CRANE_COST * craneThreads)
        {
            queueOfShips = new ConcurrentLinkedQueue<>(ships);
            craneThreads++;
            listOfCranes = new ArrayList<>(craneThreads);
            ExecutorService executor = Executors.newFixedThreadPool(craneThreads);
            for (int i = 0; i < craneThreads; i++)
            {
                CraneSimulation crane = new CraneSimulation(queueOfShips);
                listOfCranes.add(crane);
            }
            try {
                List<Future<Object>> result = executor.invokeAll(listOfCranes);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.shutdown();
            currentDelay = 0;
            for (CraneSimulation crane : listOfCranes)
            {
                currentDelay += crane.getDelay();
            }
        }
    }

    public int getCountCranes()
    {
        return craneThreads;
    }

    public int getCurrentDelay()
    {
        return currentDelay;
    }
}
