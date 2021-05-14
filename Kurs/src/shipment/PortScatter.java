package shipment;

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
        int fine = looseShips_.getCurrentFine_() + liquidShips_.getCurrentFine_() + containerShips_.getCurrentFine_();
        System.out.println("Fine = " + fine);
    }
}
