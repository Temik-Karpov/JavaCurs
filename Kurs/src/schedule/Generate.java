package schedule;

import java.io.IOException;
import java.util.*;
import JSON.JsonWriter;
import shipment.Statistic;

public class Generate
{
    private List<Ship> arrayShips_;
    public Generate()
    {}

    public void generate(int numberOfShips) throws IOException {
        this.arrayShips_ = new ArrayList();
        int sumOfDelay = 0;
        Statistic statistic = new Statistic();
        statistic.setNumberOfShips(numberOfShips);
        for(int i = 0; i < numberOfShips; i++)
        {
            Ship newShip = new Ship();
            int name = (int)(Math.random() * (10000 - 1) + 1);
            newShip.setName(name);
            int date = (int)(Math.random() * (30 - 1)+1);
            int lateness = (int)(Math.random()*14 - 7);
            if((date + lateness <= 0) || (date + lateness > 30))
            {
                newShip.setDate(date);
            }
            else {
                newShip.setDate(date + lateness);
            }
            int cargoWeight = (int)(Math.random() * (1000-1)+1);
            newShip.setCargoWeight(cargoWeight);
            Time time = new Time();
            time.setHours((int)(Math.random() * 24));
            time.setMinutes((int)(Math.random() * (60-1)+1));
            newShip.setTime(time);
            int random = (int)(Math.random() * 3);
            int delay = (int)(Math.random() * 1441);
            sumOfDelay += delay;
            statistic.setMaxDelay(delay);
            String cargoType;
            switch(random) {
                case 0:
                    cargoType = "LOOSE";
                    break;
                case 1:
                    cargoType = "LIQUID";
                    break;
                case 2:
                    cargoType = "CONTAINER";
                    break;
                default:
                    throw new IOException();
            }
            newShip.setCargoType(cargoType);
            int unloadTime = countUnloadTime(cargoType, cargoWeight, delay);
            newShip.setUnloadTime(unloadTime);
            this.arrayShips_.add(newShip);
            newShip.printInfo();
        }
        statistic.setAverageDelay(sumOfDelay, numberOfShips);
        Scanner input = new Scanner(System.in);
        while (true)
        {
            System.out.println("Enough?");
            String answer = input.nextLine();
            if (answer.equals("no"))
            {
                ManualEntry();
            } else
                {
                break;
            }
        }
        arrayShips_.sort(Comparator.comparing(Ship::getTime));
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.writeSchedule(arrayShips_);
        statistic.printShipInfo();
    }

    public void ManualEntry() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the ship number: ");
        Ship ship = new Ship();
        Time time = new Time();
        int name;
        if (in.hasNextInt())
        {
            name = in.nextInt();
            ship.setName(name);
        }


        int cargoTypeU;
        String cargoType;
        System.out.println("Enter type of cargo 1)LOOSE 2)LIQUID 3)CONTAINER : ");
        if (in.hasNextInt())
        {
            cargoTypeU = in.nextInt();
            switch(cargoTypeU) {
                case 1:
                    cargoType = "LOOSE";
                    break;
                case 2:
                    cargoType = "LIQUID";
                    break;
                case 3:
                    cargoType = "CONTAINER";
                    break;
                default:
                    throw new IOException();
            }
            ship.setCargoType(cargoType);
        }

        int cargoWeight = 0;
        System.out.println("Enter weight of cargo: ");
        if (in.hasNextInt())
        {
            cargoWeight = in.nextInt();
            ship.setCargoWeight(cargoWeight);
        }

        int date;
        System.out.println("Enter arrival day: ");
        if (in.hasNextInt())
        {
            date = in.nextInt();
            ship.setDate(date);
        }

        int hours;
        System.out.println("Enter arrival hours: ");
        if (in.hasNextInt())
        {
            hours = in.nextInt();
            time.setHours(hours);
        }

        int minutes;
        System.out.println("Enter arrival minutes: ");
        if (in.hasNextInt())
        {
            minutes = in.nextInt();
            time.setMinutes(minutes);
        }
        ship.setTime(time);
        this.arrayShips_.add(ship);
    }

    public int countUnloadTime(String cargoType, int cargoWeight, int delay)
    {
        int unloadTime = 0;
        switch(cargoType) {
            case "LOOSE":
                unloadTime = cargoWeight / 10;
                break;
            case "LIQUID":
                unloadTime = cargoWeight / 8;
                break;
            case "CONTAINER":
                unloadTime = cargoWeight / 6;
                break;
        }
            return unloadTime + delay;
    }

    public List<Ship> getArrayShips_()
    {
        return arrayShips_;
    }
}
