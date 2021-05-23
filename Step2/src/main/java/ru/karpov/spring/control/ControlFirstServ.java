package ru.karpov.spring.control;
import ru.karpov.spring.JSON.JsonWriter;
import ru.karpov.spring.schedule.Generate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
public class ControlFirstServ
{
    @GetMapping("/schedule")
    public String getSchedule() throws IOException
    {
        Generate schedule = new Generate();
        schedule.generate(100);
        JsonWriter writer = new JsonWriter();
        return writer.writeScheduleAsString(schedule);
    }
}
