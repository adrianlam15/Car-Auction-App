package persistence;

import model.*;
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
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    public Users readUsers() throws IOException {
        String jsonData = readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUsers(jsonObject);
    }

    private Users parseUsers(JSONObject jsonObject) {
        Users users = new Users();
        Cars listedCars = new Cars();
        JSONArray jsonArray = jsonObject.getJSONArray("users");
        for (Object json : jsonArray) {
            User user = new User();
            userMap.put(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"));
            user.createUser(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"),
                    ((JSONObject) json).getString("password"));
            // ADD LIST OF CARS (BIDDEDCARS, LISTEDCARS, WONCARS) to USER
            mapUserCars(json, user, listedCars);
            users.add(user);
        }
        for (Object json : jsonArray) {
            User user = new User();
            user.createUser(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"),
                    ((JSONObject) json).getString("password"));
            mapUserBids(json, user, listedCars);
            mapUserWon(json, user, listedCars);
        }
        return users;
    }


    /*// EFFECTS: reads users from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public ArrayList<User> readUsers() throws IOException {
        String jsonData = readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUsers(jsonObject);
    }

    private ArrayList<User> parseUsers(JSONObject jsonObject) {
        ArrayList<User> users = new ArrayList<>();
        Cars listedCars = new Cars();

        JSONArray jsonArray = jsonObject.getJSONArray("users");
        for (Object json : jsonArray) {
            User user = new User();

            userMap.put(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"));
            user.createUser(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"),
                    ((JSONObject) json).getString("password"));
            mapUserCars(json, user, listedCars);
            users.add(user);
        }
        for (Object json : jsonArray) {
            User user = new User();
            user.createUser(((JSONObject) json).getString("username"), ((JSONObject) json).getString("password"),
                    ((JSONObject) json).getString("password"));
            mapUserBids(json, user, listedCars);
            mapUserWon(json, user, listedCars);
        }
        return users;
    }*/

    private void mapUserWon(Object json, User user, Cars listedCars) {
        JSONArray jsonWon = ((JSONObject) json).getJSONArray("wonCars");
        for (Object json1 : jsonWon) {
            for (Car car : listedCars.getCars()) {
                if (car.getId() == ((JSONObject) json1).getInt("carId")) {
                    user.addWonCar(car);
                }
            }
        }
    }

    private void mapUserBids(Object json, User user, Cars listedCars) {
        JSONArray jsonBids = ((JSONObject) json).getJSONArray("bids");
        JSONArray jsonListedCars = ((JSONObject) json).getJSONArray("listedCars");
        for (Object json1 : jsonBids) {
            user.placeBid(((JSONObject) json1).getInt("carId"), ((JSONObject) json1).getInt("amount"),
                    listedCars);
        }
    }

    private void mapUserCars(Object json, User user, Cars listedCars) {
        JSONArray jsonListings = ((JSONObject) json).getJSONArray("listedCars");
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
            user.createCar(car);
            listedCars.addCar(car);
        }
    }

    public HashMap<String, String> getUserMap() {
        return this.userMap;
    }

    public String readDate() throws IOException {
        String jsonData = readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDate(jsonObject);
    }

    private String parseDate(JSONObject jsonObject) {
        return jsonObject.getString("date");
    }
}
