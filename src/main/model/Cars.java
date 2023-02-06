package model;

import java.util.ArrayList;

public class Cars {
    ArrayList<Car> cars;

    public Cars() {
        this.cars = new ArrayList<Car>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}
