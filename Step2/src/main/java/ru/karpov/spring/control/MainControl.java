package ru.karpov.spring.control;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;
import ru.karpov.spring.JSON.JsonReader;
import ru.karpov.spring.schedule.Generate;
import ru.karpov.spring.shipment.PortScatter;
import ru.karpov.spring.shipment.Statistic;
import java.util.HashMap;
import java.util.Map;

public class MainControl
{
    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(ControlThirdServ.class);
        Map<String, Object> map = new HashMap<>();
        map.put("server.port", "8080");
        map.put("server.host", "localhost");
        app.setDefaultProperties(map);
        ConfigurableApplicationContext context = app.run();
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        String url = restTemplate.getForObject("http://localhost:8080/ScheduleJson", String.class);
        System.out.println(url);
        Generate schedule = new Generate();
        JsonReader reader = new JsonReader();
        reader.readScheduleAsString(url, schedule);
        PortScatter port = new PortScatter(schedule.getArrayShips_());
        port.setStatistic();
        Statistic statistic = port.getStatistic();
        statistic.printCraneInfo();
        try {
            restTemplate.postForObject("http://localhost:8080/statistic",
                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(statistic), String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        context.close();
    }
}