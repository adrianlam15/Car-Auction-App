package persistence;

import model.Bid;
import model.Car;
import model.User;
import model.Cars;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;


public class JsonReader {
    private String source;
    private HashMap<String, String> userMap;

    public JsonReader(String source) {
        this.source = source;
        this.userMap = new HashMap<>();
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: reads users from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public ArrayList<User> readUsers() throws IOException {
        String jsonData = readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUsers(jsonObject);
    }

    private ArrayList<User> parseUsers(JSONObject jsonObject) {
        ArrayList<User> users = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("users");
        for (Object json : jsonArray) {
            userMap.put(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"));
            User user = new User();
            user.createUser(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"),
                    ((JSONObject) json).getString("password"));
            Car car = new Car();
            JSONArray jsonArray1 = ((JSONObject) json).getJSONArray("listings");
            for (Object json1 : jsonArray1) {
                car.setId(((JSONObject) json1).getInt("id"));
                car.setMake(((JSONObject) json1).getString("make"));
                car.setModel(((JSONObject) json1).getString("model"));
                car.setColour(((JSONObject) json1).getString("colour"));
                car.setTransmission(((JSONObject) json1).getString("transmission"));
                car.setDriveType(((JSONObject) json1).getString("driveType"));
                car.setCondition(((JSONObject) json1).getString("condition"));
                car.setYear(((JSONObject) json1).getInt("year"));
                car.setPrice(((JSONObject) json1).getInt("price"));
                car.setMileage(((JSONObject) json1).getInt("mileage"));
                car.setDescription(((JSONObject) json1).getString("description"));
                car.setTimer(((JSONObject) json1).getInt("timeLeftInSeconds"));
                user.createCar(car);
            }
            users.add(user);
        }
        return users;
    }

    public HashMap<String, String> getUserMap() {
        return this.userMap;
    }

    public Bid readBid() {
        return null;
    }

    public Car readCar() {
        return null;
    }

    public Cars readCars() {
        return null;
    }
}
