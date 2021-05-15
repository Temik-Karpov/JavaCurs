package shipment;

import schedule.CargoType;
import schedule.Ship;
import java.util.ArrayList;
import java.util.List;

public class PortScatter
{
    private List<Ship> ListOfLooseCargo_;
    private List<Ship> ListOfLiquidCargo_;
    private List<Ship> ListOfContainerCargo_;
    private Port looseShips_;
    private Port liquidShips_;
    private Port containerShips_;
    private Statistic statistic_;

    public PortScatter(List<Ship> ships) throws InterruptedException {
        ListOfLooseCargo_ = new ArrayList<Ship>();
        ListOfLiquidCargo_ = new ArrayList<Ship>();
        ListOfContainerCargo_ = new ArrayList<Ship>();
        for(Ship ship : ships)
        {
            switch (ship.getCargoType())
            {
                case LOOSE:
                    ListOfLooseCargo_.add(ship);
                    break;
                case LIQUID:
                    ListOfLiquidCargo_.add(ship);
                    break;
                case CONTAINER:
                    ListOfContainerCargo_.add(ship);
            }
        }
        looseShips_ = new Port(ListOfLooseCargo_);
        liquidShips_ = new Port(ListOfLiquidCargo_);
        containerShips_ = new Port(ListOfContainerCargo_);
        looseShips_.start();
        liquidShips_.start();
        containerShips_.start();
        looseShips_.join();
        liquidShips_.join();
        containerShips_.join();
    }

    public void setStatistic()
    {
        statistic_ = new Statistic();
        statistic_.setCranesCount(CargoType.LOOSE, looseShips_.getCountCranes());
        statistic_.setCranesCount(CargoType.LIQUID, liquidShips_.getCountCranes());
        statistic_.setCranesCount(CargoType.CONTAINER, containerShips_.getCountCranes());
        statistic_.setFine(looseShips_.getCurrentFine(), liquidShips_.getCurrentFine(), containerShips_.getCurrentFine());
        //statistic_.setAverageQueueSize(looseShips_.getQueueSize() + liquidShips_.getQueueSize() + containerShips_.getQueueSize());
        statistic_.printCraneInfo();
    }
}
