package shipment;

import schedule.Ship;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CraneSimulation implements Callable<Object>
{
    private int craneFine_;
    private ConcurrentLinkedQueue<Ship> ships_;
    private int queueSize_;

    public CraneSimulation(ConcurrentLinkedQueue<Ship> ships)
    {
        this.ships_ = ships;
        this.craneFine_ = 0;
        this.queueSize_ = 0;
    }

    @Override
    public Object call() throws InterruptedException
    {
        while(true)
        {
            Ship nextShip = ships_.peek();
            if (nextShip == null)
            {
                break;
            }
            Ship currentShip = ships_.poll();
            int currentTime = currentShip.getUnloadTime() + currentShip.getTime();
            if (currentTime > nextShip.getTime())
            {
                int timeDelay = currentTime - nextShip.getTime();
                craneFine_ += 100 * (timeDelay / 60);
            }
            /*if(currentShip.getUnloadTime() + currentShip.getTime() > nextShip.getTime())
            {
                queueSize_++;
            }*/
        }
        return null;
    }

    public int getFine()
    {
        return craneFine_;
    }
    /*public int getQueueSize()
    {
        return queueSize_;
    }*/
}
