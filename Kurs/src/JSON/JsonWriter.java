package JSON;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import schedule.Ship;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonWriter
{
    public void writeSchedule(List<Ship> arrayShips)
    {
        final String file = "ScheduleFile.json";
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(Paths.get(file).toFile(), arrayShips);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
