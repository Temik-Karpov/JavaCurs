package schedule;

import JSON.JsonWriter;
import shipment.Port;

import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Generate generatedShip = new Generate();
        generatedShip.generate(10);
        List<Ship> arrayShips = generatedShip.getArrayShips();
        Port port = new Port();
        port.setListsOfCargo(arrayShips);
    }
}