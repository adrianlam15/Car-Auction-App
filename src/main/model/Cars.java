package model;

import org.json.JSONArray;

import java.util.ArrayList;

public class Cars {
    private ArrayList<Car> cars;

    public Cars() {
        this.cars = new ArrayList<Car>();
    }

    // REQUIRES: car must be non-null and contain valid data
    // MODIFIES: this
    // EFFECTS: adds a car to the list of cars for sale
    public void addCar(Car car) {
        cars.add(car);
        EventLog.getInstance().logEvent(new Event("Car added to list: " + car.getListingCar()));
    }

    // REQUIRES: car must be non-null and must be in list
    // MODIFIES: this
    // EFFECTS: removes a car from the list of cars for sale
    public void removeCar(Car car) {
        cars.remove(car);
        EventLog.getInstance().logEvent(new Event("Car removed from list: " + car.getListingCar()));
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public JSONArray toJson() {
        JSONArray json = carsToJson();
        EventLog.getInstance().logEvent(new Event("Cars converted to JSON: " + json));
        return json;
    }

    private JSONArray carsToJson() {
        JSONArray json = new JSONArray();
        for (Car c : cars) {
            json.put(c.toJson());
        }
        return json;
    }
}
