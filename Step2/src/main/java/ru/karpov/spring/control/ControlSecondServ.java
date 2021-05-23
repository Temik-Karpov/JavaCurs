package ru.karpov.spring.control;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.karpov.spring.JSON.JsonReader;
import ru.karpov.spring.JSON.JsonWriter;
import ru.karpov.spring.schedule.Generate;
import ru.karpov.spring.shipment.Statistic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class ControlSecondServ
{
    @GetMapping("/ScheduleJson")
    public String getScheduleJson()
    {
        SpringApplication app = new SpringApplication(ControlFirstServ.class);
        Map<String, Object> map = new HashMap<>();
        map.put("server.port", "8280");
        map.put("server.host", "localhost");
        app.setDefaultProperties(map);
        ConfigurableApplicationContext context = app.run();
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        String url = restTemplate.getForObject("http://localhost:8280/schedule", String.class);
        context.close();
        Generate schedule = new Generate();
        JsonReader reader = new JsonReader();
        reader.readScheduleAsString(url, schedule);
        JsonWriter writer = new JsonWriter();
        writer.writeSchedule("schedule", schedule);
        return url;
    }
    @GetMapping("/ScheduleJsonByName")
    public String getScheduleJsonByName(@RequestParam String filename) {
        Generate schedule = new Generate();
        try {
            JsonReader reader = new JsonReader();
            reader.readStream(new FileInputStream(filename), schedule);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonWriter writer = new JsonWriter();
        return writer.writeScheduleAsString(schedule);
    }
    @PostMapping("/statistic")
    public void setStatisticJson(@RequestBody String filename) throws FileNotFoundException {
        Statistic statistic = new Statistic();
        JsonReader reader = new JsonReader();
        reader.readStatisticAsString(filename, statistic);
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream("statistic"), statistic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
