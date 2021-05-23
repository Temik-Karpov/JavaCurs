package ru.karpov.spring.JSON;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.karpov.spring.schedule.Generate;
import ru.karpov.spring.schedule.Ship;
import ru.karpov.spring.shipment.Statistic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Paths;

public class JsonReader
{
    List<Ship> arrayShips_;
    public List<Ship> readSchedule(String file)
    {
        final String file_ = file;
        arrayShips_ = new ArrayList<Ship>();
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            arrayShips_ = Arrays.asList(mapper.readValue(Paths.get(file).toFile(), Ship[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayShips_;
    }

    public void readScheduleAsString(String filename, Generate schedule)
    {
        if (schedule.getArrayShips_() == null) {
            schedule.setArrayShips(new ArrayList<Ship>());
        }
        try {
            schedule.getArrayShips_().addAll(new ObjectMapper().readValue(filename, Generate.class).getArrayShips_());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readStream(InputStream src, Generate schedule) {
        if (schedule.getArrayShips_() == null) {
            schedule.setArrayShips(new ArrayList<Ship>());
        }
        try {
            schedule.getArrayShips_().addAll(new ObjectMapper().readValue(src, Generate.class).getArrayShips_());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readStatisticAsString(String filename, Statistic statistic)
    {
        Statistic stat = null;
        try {
            stat = new ObjectMapper().readValue(filename, Statistic.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        statistic.liquidCount = stat.liquidCount;
        statistic.looseCount = stat.looseCount;
        statistic.containerCount = stat.containerCount;
        statistic.fine = stat.fine;
    }
}
