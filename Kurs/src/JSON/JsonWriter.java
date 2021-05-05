package JSON;

import schedule.Generate;
import schedule.Ship;
import java.util.Comparator;
import java.util.Scanner;

public class JsonWriter
{
    public JsonWriter(int numberOfShips)
    {
        Generate generatedShip = new Generate();
        generatedShip.generate(numberOfShips);
        Scanner input = new Scanner(System.in);
        while (true)
        {
            System.out.println("Enough?");
            String answer = input.nextLine();
            if (answer.equals("no"))
            {
                generatedShip.ManualEntry();
            }
            else
            {
                break;
            }
        }

        generatedShip.getArrayShips().sort(Comparator.comparing(Ship::getUnloadTime));
        System.out.println(generatedShip.getArrayShips());
    }
}
