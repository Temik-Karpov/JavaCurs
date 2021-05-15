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
    private int craneThreads_;
    private int currentFine_ = 0;
    private int AllQueueOfShips_ = 0;
    private List<Ship> ships_;
    private ConcurrentLinkedQueue<Ship> queueOfShips_;
    private ArrayList<CraneSimulation> listOfCranes_;
    private Statistic statistic_;
    private int queueSize_ = 0;

    public Port(List<Ship> ships)
    {
        this.ships_ = ships;
    }

    @Override
    public void run()
    {
        while (currentFine_ >= CRANE_COST * craneThreads_)
        {
            queueOfShips_ = new ConcurrentLinkedQueue<>(ships_);
            craneThreads_++;
            currentFine_ = 0;
            listOfCranes_ = new ArrayList<>(craneThreads_);
            ExecutorService executor = Executors.newFixedThreadPool(craneThreads_);
            for (int i = 0; i < craneThreads_; i++)
            {
                CraneSimulation crane = new CraneSimulation(queueOfShips_);
                listOfCranes_.add(crane);
            }
            try {
                List<Future<Object>> result = executor.invokeAll(listOfCranes_);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.shutdown();
            for (CraneSimulation crane : listOfCranes_)
            {
                currentFine_ += crane.getFine();
            }
        }
        /*for (CraneSimulation crane : listOfCranes_)
        {
            this.queueSize_ += crane.getQueueSize();
        }*/
    }

    public int getCountCranes()
    {
        return craneThreads_;
    }

    public int getCurrentFine()
    {
        return currentFine_;
    }

    public int getQueueSize()
    {
        return queueSize_;
    }
}
