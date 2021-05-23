package ru.karpov.spring.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.karpov.spring.schedule.Generate;
import java.io.IOException;
import java.nio.file.Paths;


public class JsonWriter
{
    public void writeSchedule(String file, Generate arrayShips)
    {
        final String file_ = file;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(Paths.get(file_).toFile(), arrayShips);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String writeScheduleAsString(Generate schedule)
    {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(schedule);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
