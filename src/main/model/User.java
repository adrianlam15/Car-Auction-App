package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashMap;


// User class for the Car Auction application
public class User implements Writable {
    private String username;
    private String password;
    private Car car;
    private Cars listedCars = new Cars();
    private Cars wonCars = new Cars();
    private ArrayList<Bid> biddedCars = new ArrayList<>();

    // REQUIRED: user, password must be non-empty strings and hashmap must be non-null.
    // EFFECTS: returns true if user and password combination match one in stored hashmap, false otherwise
    public boolean login(String usr, String pwd, HashMap<String, String> users) {
        for (String user : users.keySet()) {
            if (user.equals(usr) && users.get(user).equals(pwd)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: user, password, and checkPwd must be non-empty strings
    // MODIFIES: this
    // EFFECTS: if password and checkPwd match, creates a new user with the given username and password,
    //          returns true, otherwise returns false
    public boolean createUser(String usr, String pwd, String checkPwd) {
        if (pwd.equals(checkPwd)) {
            this.username = usr;
            this.password = pwd;
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: Car must be non-null
    // MODIFIES: this
    // EFFECTS: adds a car to the list of cars for sale
    public void createCar(Car carToCreate) {
        this.car = carToCreate;
        this.listedCars.addCar(car);
    }

    // REQUIRES: id must be > 0
    // MODIFIES: this
    // EFFECTS: removes a car from the list of cars for sale, returns Car if successful, null otherwise
    public Car deleteCar(int id) {
        for (Car car : listedCars.getCars()) {
            if (car.getId() == id) {
                listedCars.removeCar(car);
                return car;
            }
        }
        return null;
    }

    // REQUIRES: id must be > 0, editChoice must be one of the following: "make", "model", "colour", "transmission",
    //           "year", "price", "mileage", "description", newValue must be non-empty string
    // MODIFIES: this, car
    // EFFECTS: edits a car from the list of cars for sale
    public boolean editCar(int id, int editChoice, String newValue) {
        for (Car car : listedCars.getCars()) {
            if (car.getId() == id) {
                car.edit(editChoice, newValue);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: id must be > 0, bid must be > 0 and > current bid/price, and listedCars must be non-null,
    //           listedCars must not contain Car that belongs to User.
    // MODIFIES: this, car
    // EFFECTS: bids on a car from the list of cars for sale, returns true if successful, false otherwise
    public boolean placeBid(int id, int bid, Cars listedCars) {
        for (Car car : listedCars.getCars()) {
            if (car.getId() == id) {
                biddedCars.add(new Bid(this, car, bid));
                car.bid(this, bid);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Bid> getBids() {
        return biddedCars;
    }

    public ArrayList<Car> getCars() {
        return listedCars.getCars();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Car> getWonCars() {
        return wonCars.getCars();
    }

    public void addWonCar(Car car) {
        wonCars.addCar(car);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);
        json.put("listedCars", listedCars.toJson());
        json.put("wonCars", wonCars.toJson());
        json.put("biddedCars", bidCarsToJson());
        return json;
    }

    private JSONArray bidCarsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Bid bid : biddedCars) {
            jsonArray.put(bid.toJson());
        }
        System.out.println(jsonArray);
        return jsonArray;
    }
}
