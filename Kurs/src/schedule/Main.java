package schedule;

import JSON.JsonReader;
import shipment.PortScatter;

import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException {
        Generate generatedShip = new Generate();
        generatedShip.generate(10);
        JsonReader jsonReader = new JsonReader();
        List<Ship> arrayShips = jsonReader.readSchedule();
        PortScatter port = new PortScatter(arrayShips);

    }
}