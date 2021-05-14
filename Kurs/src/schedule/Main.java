package schedule;

import JSON.JsonReader;
import shipment.PortScatter;
import shipment.Statistic;

import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException {
        Generate generatedShip = new Generate();
        generatedShip.generate(100);
        JsonReader jsonReader = new JsonReader();
        List<Ship> arrayShips = jsonReader.readSchedule();
        PortScatter port = new PortScatter(arrayShips);
    }
}