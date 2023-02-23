package model;

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
    }

    // REQUIRES: car must be non-null and must be in list
    public void removeCar(Car car) {
        cars.remove(car);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}
