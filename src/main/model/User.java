package model;

import java.util.ArrayList;

// User class for the Car Auction application
public class User {
    private String user;
    private String password;
    private Car car;
    private Cars listedCars = new Cars();
    private Cars savedCars;
    private Cars biddedCars = new Cars();

    // for development purposes, returns TRUE
    // REQUIRED: user and password must be non-empty strings
    // EFFECTS: returns true if user and password combination match one in stored file, false otherwise
    public boolean login(String usr, String pwd) {
        return true;
    }

    // REQUIRES: user, password, and checkPwd must be non-empty strings
    // MODIFIES: this
    // EFFECTS: if password and checkPwd match, creates a new user with the given username and password
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
    // EFFECTS: removes a car from the list of cars for sale
    public boolean deleteCar(int id) {
        for (Car car : listedCars.getCars()) {
            if (car.getId() == id) {
                listedCars.removeCar(car);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: id must be > 0
    // MODIFIES: this
    // EFFECTS: edits a car from the list of cars for sale
    public void editCar(int id, String choice) {
        for (Car car : listedCars.getCars()) {
            if (car.getId() == id) {
                // TODO: implement this method
            }
        }
    }

    // REQUIRES: id must be > 0
    // MODIFIES: this
    // EFFECTS: bids on a car from the list of cars for sale
    public boolean placeBid(int id, int bid, Cars listedCars) {
        for (Car car : listedCars.getCars()) {
            if (car.getId() == id) {
                biddedCars.addCar(car);
                return true;
            }
        }
        return false;
    }

    public void getBids() {
        // STUB
    }

    /*
    public ArrayList<Car> getBids() {
        //return biddedCars.getCars();
    }*/

    public ArrayList<Car> getCars() {
        return listedCars.getCars();
    }
}
