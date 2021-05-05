package shipment;
import schedule.Time;

public class Statistic
{
    int shipCount;
    Time waitingTime;
    Time maxDelay;
    int finalFine;
    int liquidCount;
    int looseCount;
    int containerCount;
    Statistic()
    {
        waitingTime = new Time(0);
        maxDelay = new Time(0);
        shipCount = 0;
        finalFine = 0;
        liquidCount = 0;
        looseCount = 0;
        containerCount = 0;
    }

}
