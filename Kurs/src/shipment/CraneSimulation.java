package shipment;

import schedule.Ship;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CraneSimulation implements Callable<Object>
{
    private int currentTime_ = -43200;
    private int craneFine_ = 0;
    private ConcurrentLinkedQueue<Ship> ships_;

    public CraneSimulation(ConcurrentLinkedQueue<Ship> ships)
    {
        this.ships_ = ships;
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
            currentTime_ = Math.max(currentTime_, currentShip.getTime());
            currentTime_ += currentShip.getUnloadTime();
            if (nextShip.getTime() < currentTime_)
            {
                int timeDelay = currentTime_ - nextShip.getTime();
                if (timeDelay % 60 == 0)
                {
                    craneFine_ += 100 * (timeDelay / 60);
                }
                else {
                    craneFine_ += 100 * (timeDelay / 60 + 1);
                }
            }
        }
        return null;
    }

    public int getFine()
    {
        return craneFine_;
    }
}
