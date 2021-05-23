package ru.karpov.spring.schedule;

import ru.karpov.spring.JSON.JsonReader;
import ru.karpov.spring.shipment.PortScatter;
import ru.karpov.spring.shipment.Statistic;

import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException {
        Generate generatedShip = new Generate();
        generatedShip.generate(1000);
        JsonReader jsonReader = new JsonReader();
        List<Ship> arrayShips = jsonReader.readSchedule("ScheduleFile.json");
        PortScatter port = new PortScatter(arrayShips);
        port.setStatistic();
    }
}