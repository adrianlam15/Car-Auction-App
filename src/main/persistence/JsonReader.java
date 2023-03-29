package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

// Represents a reader that reads JSON representation of users from file
public class JsonReader {
    private final String source;
    private final HashMap<String, String> userMap;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
        this.userMap = new HashMap<>();
    }

    // EFFECTS: reads users from file and returns it;
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: reads users from file and returns it;
    public Users readUsers() throws IOException {
        String jsonData = readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUsers(jsonObject);
    }

    // MODIFIES: Users, Cars, User
    // EFFECTS: parses users from JSON object and returns it
    private Users parseUsers(JSONObject jsonObject) {
        Users users = new Users();
        Cars listedCars = new Cars();
        JSONArray jsonArray = jsonObject.getJSONArray("users");
        for (Object json : jsonArray) {
            User user = new User();
            userMap.put(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"));
            user.createUser(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"),
                    ((JSONObject) json).getString("password"));
            JSONArray jsonListings = ((JSONObject) json).getJSONArray("listedCars");
            mapUserCars(jsonListings, user, listedCars);
            users.add(user);
        }
        mapUserBids(jsonArray, users, listedCars);
        return users;
    }

    private void mapUserBids(JSONArray jsonArray, Users users, Cars listedCars) {
        for (Object json : jsonArray) {
            String name = ((JSONObject) json).getString("username");
            for (User u : users.getUsers()) {
                mapUserWon(json, u, listedCars);
                if (u.getUsername().equals(name)) {
                    JSONArray jsonBids = ((JSONObject) json).getJSONArray("biddedCars");
                    for (Object json1 : jsonBids) {
                        Object jsonCar = ((JSONObject) json1).get("car");
                        int id = ((JSONObject) jsonCar).getInt("id");
                        int bidAmount = ((JSONObject) json1).getInt("bidAmount");
                        u.placeBid(id, bidAmount, listedCars);
                    }
                }
            }
        }
    }

    // MODIFIES: Car, User
    // EFFECTS: parses cars from JSON object and returns it
    private void mapUserWon(Object json, User user, Cars listedCars) {
        JSONArray jsonWon = ((JSONObject) json).getJSONArray("wonCars");
        for (Object json1 : jsonWon) {
            for (Car car : listedCars.getCars()) {
                if (car.getId() == ((JSONObject) json1).getInt("id")) {
                    user.addWonCar(car);
                }
            }
        }
    }

    // MODIFIES: Car, User, Cars
    // EFFECTS: parses cars from JSON object and returns it
    private void mapUserCars(JSONArray jsonListings, User user, Cars listedCars) {
        for (Object json1 : jsonListings) {
            Car car = new Car();
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
            boolean expired = ((JSONObject) json1).getBoolean("expired");
            if (expired) {
                car.markExpired();
            } else {
                car.unmarkExpired();
            }
            user.createCar(car);
            listedCars.addCar(car);
        }
    }

    // EFFECTS: returns userMap
    public HashMap<String, String> getUserMap() {
        return this.userMap;
    }

    // EFFECTS: reads date from file and returns it;
    public String readDate() throws IOException {
        String jsonData = readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDate(jsonObject);
    }

    // EFFECTS: parses date from JSON object and returns it
    private String parseDate(JSONObject jsonObject) {
        return jsonObject.getString("date");
    }
}
