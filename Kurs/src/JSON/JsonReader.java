package JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import schedule.Ship;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Paths;

public class JsonReader
{
    List<Ship> arrayShips;
    private final String file = "ScheduleFile.json";
    public List<Ship> readSchedule()
    {
        arrayShips = new ArrayList<Ship>();
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            arrayShips = Arrays.asList(mapper.readValue(Paths.get(file).toFile(), Ship[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayShips;
    }
}
