package model;

import java.util.ArrayList;


// User class for the Car Auction application
public class User {
    private String user;
    private String password;
    private Car car;
    private Cars listedCars = new Cars();
    private Cars wonCars = new Cars();
    private ArrayList<Bid> biddedCars = new ArrayList<>();

    // METHOD FOR PHASE 2:
    // for development purposes, returns TRUE
    // REQUIRED: user and password must be non-empty strings
    // EFFECTS: returns true if user and password combination match one in stored file, false otherwise
    /*
    public boolean login(String usr, String pwd) {
        return true;
    }*/

    // REQUIRES: user, password, and checkPwd must be non-empty strings
    // MODIFIES: this
    // EFFECTS: if password and checkPwd match, creates a new user with the given username and password,
    //          returns true, otherwise returns false
    public boolean createUser(String usr, String pwd, String checkPwd) {
        if (pwd.equals(checkPwd)) {
            this.user = usr;
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

    public String getName() {
        return user;
    }

    public ArrayList<Car> getWonCars() {
        return wonCars.getCars();
    }
}
