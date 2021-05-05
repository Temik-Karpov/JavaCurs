package shipment;

import schedule.Generate;
import schedule.Ship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

public class Port
{
    private List<Ship> ListOfLooseCargo;
    private List<Ship> ListOfLiquidCargo;
    private List<Ship> ListOfContainerCargo;

    public void setListsOfCargo(List<Ship> ships)
    {
        ListOfLooseCargo = new ArrayList<Ship>();
        ListOfLiquidCargo = new ArrayList<Ship>();
        ListOfContainerCargo = new ArrayList<Ship>();
        for(Ship ship : ships)
        {
            Scatter(ship);
        }

    }

    private void Scatter(Ship ship)
    {
        switch (ship.getCargoType()) {
            case LOOSE:
                ListOfLooseCargo.add(ship);
                break;
            case LIQUID:
                ListOfLiquidCargo.add(ship);
                break;
            case CONTAINER:
                ListOfContainerCargo.add(ship);
                break;
        }
    }
}
