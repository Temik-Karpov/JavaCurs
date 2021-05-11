package shipment;

import schedule.Ship;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CraneSimulation implements Callable<Object>
{
    private int currentTime = -43200;
    private int craneFine = 0;
    private ConcurrentLinkedQueue<Ship> ships;

    public CraneSimulation(ConcurrentLinkedQueue<Ship> ships)
    {
        this.ships = ships;
    }

    @Override
    public Object call() throws InterruptedException
    {
        while(true)
        {
            Ship nextShip = ships.peek();
            if (nextShip == null)
            {
                break;
            }
            Ship currentShip = ships.poll();
            currentTime = Math.max(currentTime, currentShip.getTime());
            currentTime += currentShip.getUnloadTime();
            if (nextShip.getTime() < currentTime)
            {
                int timeDelay = currentTime - nextShip.getTime();
                if (timeDelay % 60 == 0)
                {
                    craneFine += 100 * (timeDelay / 60);
                }
                else {
                    craneFine += 100 * (timeDelay / 60 + 1);
                }
            }
            Thread.sleep(1);
        }
        return null;
    }

    public int getDelay()
    {
        return craneFine;
    }
}
