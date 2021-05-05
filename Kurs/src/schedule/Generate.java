package schedule;

import java.util.*;

public class Generate
{
    private List<Ship> arrayShips;
    public Generate()
    {}

    public void generate(int numberOfShips)
    {
        this.arrayShips = new ArrayList();
        for(int i = 0; i < numberOfShips; i++)
        {
            String name = "Ship " + (int)(Math.random() * (10000 - 1) + 1);
            int date = (int)(Math.random() * (30 - 1)+1);
            int cargoWeight = (int)(Math.random() * (100-5)+5);
            Time time = new Time();
            time.setHours((int)(Math.random() * 24));
            time.setMinutes((int)(Math.random() * (60-1)+1));
            CargoType cargoType = CargoType.LOOSE;
            int random = (int) (Math.random() * 3);
            switch(random)
            {
                case 0:
                    cargoType = CargoType.LOOSE;
                    break;
                case 1:
                    cargoType = CargoType.LIQUID;
                    break;
                case 2:
                    cargoType = CargoType.CONTAINER;
                    break;
            }
            Ship newShip = new Ship(name, cargoType, cargoWeight, date, time);
            this.arrayShips.add(newShip);
            newShip.printInfo();
        }
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
        arrayShips.sort(Comparator.comparing(Ship::getUnloadTime));
    }

    public void ManualEntry()
    {
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

        int cargoType = 0;
        System.out.println("Enter type of cargo 1)LOOSE 2)LIQUID 3)CONTAINER : ");
        if (in.hasNextInt())
        {
            cargoType = in.nextInt();
            ship.setCargoType(cargoType);
        }

        int cargoWeight;
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
        ship.setUnloadTime();
        this.arrayShips.add(ship);
    }


    public List<Ship> getArrayShips()
    {
        return arrayShips;
    }
}
