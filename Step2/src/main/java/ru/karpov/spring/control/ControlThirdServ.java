package ru.karpov.spring.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.karpov.spring.JSON.JsonReader;
import ru.karpov.spring.schedule.Generate;
import ru.karpov.spring.shipment.PortScatter;
import ru.karpov.spring.shipment.Statistic;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class ControlThirdServ
{
    static final String path = "http://localhost:8080/";
    public static void getStatistic() throws InterruptedException {
        SpringApplication app = new SpringApplication(ControlSecondServ.class);
        Map<String, Object> map = new HashMap<>();
        map.put("server.port", "8080");
        map.put("server.host", "localhost");
        app.setDefaultProperties(map);
        ConfigurableApplicationContext context = app.run();
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        String url = restTemplate.getForObject(path + "ScheduleJson", String.class);
        System.out.println(url);
        Generate schedule = new Generate();
        JsonReader reader = new JsonReader();
        reader.readScheduleAsString(url, schedule);
        PortScatter port = new PortScatter(schedule.getArrayShips_());
        port.setStatistic();
        Statistic statistic = port.getStatistic();
        statistic.printCraneInfo();
        try {
            restTemplate.postForObject(path + "statistic",
                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(statistic), String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        context.close();
    }
}
