package shipment;

import schedule.Ship;
import java.util.ArrayList;
import java.util.List;

public class PortScatter
{
    private List<Ship> ListOfLooseCargo;
    private List<Ship> ListOfLiquidCargo;
    private List<Ship> ListOfContainerCargo;
    private Statistic statistic;

    public PortScatter(List<Ship> ships)
    {
        ListOfLooseCargo = new ArrayList<Ship>();
        ListOfLiquidCargo = new ArrayList<Ship>();
        ListOfContainerCargo = new ArrayList<Ship>();
        for(Ship ship : ships)
        {
            switch (ship.getCargoType())
            {
                case LOOSE:
                    ListOfLooseCargo.add(ship);
                    break;
                case LIQUID:
                    ListOfLiquidCargo.add(ship);
                    break;
                case CONTAINER:
                    ListOfContainerCargo.add(ship);
            }
        }
        Port looseShips = new Port(ListOfLooseCargo);
        Port liquidShips = new Port(ListOfLiquidCargo);
        Port containerShips = new Port(ListOfContainerCargo);
        looseShips.start();
        liquidShips.start();
        containerShips.start();
        statistic = new Statistic(ships.size(), looseShips, liquidShips, containerShips);
        statistic.printStatistic();
    }
}
