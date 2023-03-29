package model;

import org.json.JSONArray;

import java.util.ArrayList;

public class Cars {
    private final ArrayList<Car> cars;

    public Cars() {
        this.cars = new ArrayList<Car>();
    }

    // REQUIRES: car must be non-null and contain valid data
    // MODIFIES: this
    // EFFECTS: adds a car to the list of cars for sale
    public void addCar(Car car) {
        cars.add(car);
    }

    // REQUIRES: car must be non-null and must be in list
    // MODIFIES: this
    // EFFECTS: removes a car from the list of cars for sale
    public void removeCar(Car car) {
        cars.remove(car);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public JSONArray toJson() {
        JSONArray json = carsToJson();
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
