package JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import schedule.CargoType;
import schedule.Ship;
import schedule.Time;

import java.io.IOException;
import java.util.Date;

public class JsonDeserializer extends StdDeserializer<Ship> {
    public JsonDeserializer()
    {
        this(null);
    }

    protected JsonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Ship deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int name = node.get("name").asInt();
        String cargo = node.get("cargo").get("cargoType").asText();
        CargoType cargoType;
        switch(cargo) {
            case "LOOSE":
                cargoType = CargoType.LOOSE;
                break;
            case "LIQUID":
                cargoType = CargoType.LIQUID;
                break;
            case "CONTAINER":
                cargoType = CargoType.CONTAINER;
                break;
            default:
                throw new IOException();
        }
        int cargoWeight = node.get("cargo").get("CargoWeight").asInt();
        int day = node.get("day").asInt();
        int hours = node.get("hour").asInt();
        int minutes = node.get("minutes").asInt();
        Time time = new Time(hours, minutes);
        int unloadTime = node.get("unloadTime").asInt();
        return new Ship("Ship" + name, cargoType, cargoWeight, day, time, unloadTime);
    }
}
