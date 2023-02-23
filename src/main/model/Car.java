package model;

import java.util.ArrayList;

// class representing a car
public class Car {
    private String make;
    private String model;
    private String colour;
    private String transmission;
    private String driveType;
    private String condition;
    private String description;
    private int year;
    private int price;
    private int mileage;
    private int id;

    private int highestBid = 0;
    private ArrayList<Bid> bids;

    public Car() {
        this.make = "";
        this.model = "";
        this.colour = "";
        this.transmission = "";
        this.driveType = "";
        this.condition = "";
        this.description = "";
        this.year = 0;
        this.price = 0;
        this.mileage = 0;
        this.bids = new ArrayList<>();
        }

    // REQUIRES: make must be non-empty string
    // MODIFIES: this
    // EFFECTS: sets the make of the car
    public void setMake(String make) {
        this.make = make;
    }

    // REQUIRES: model must be non-empty string
    // MODIFIES: this
    // EFFECTS: sets the model of the car
    public void setModel(String model) {
        this.model = model;
    }

    // REQUIRES: colour must be non-empty string
    // MODIFIES: this
    // EFFECTS: sets the colour of the car
    public void setColour(String colour) {
        this.colour = colour;
    }

    // REQUIRES: transmission must either be "Automatic" or "Manual"
    // MODIFIES: this
    // EFFECTS: sets the transmission of the car
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    // REQUIRES: driveType must either be "Front-Wheel Drive", "Rear-Wheel Drive", or "All-Wheel Drive"
    // MODIFIES: this
    // EFFECTS: sets the drive type of the car
    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    // REQUIRES: condition must either be "New", "Used", or "Poor"
    // MODIFIES: this
    // EFFECTS: sets the condition of the car
    public void setCondition(String condition) {
        this.condition = condition;
    }

    // REQUIRES: year must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the year of the car
    public void setYear(int year) {
        this.year = year;
    }

    // REQUIRES: price must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the price of the car
    public void setPrice(int price) {
        this.price = price;
    }

    // REQUIRES: mileage must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the mileage of the car
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    // REQUIRES: description must be a non-empty string
    // MODIFIES: this
    // EFFECTS: sets the description of the car
    public void setDescription(String description) {
        this.description = description;
    }

    // REQUIRES: id must be a positive integer
    // MODIFIES: this
    // EFFECTS: sets the id of the car
    public void setId(int id) {
        this.id = id;
    }

    public int getHighestBid() {
        return highestBid;
    }

    // REQUIRES: bid must be a positive integer
    // MODIFIES: this
    // EFFECTS: adds a bid to the car
    public void bid(int bid) {
        this.bids.add(new Bid (bid));
        if (bid > highestBid) {
            highestBid = bid;
        }
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getDriveType() {
        return driveType;
    }

    public String getCondition() {
        return condition;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public int getMileage() {
        return mileage;
    }

    public String getDescription() {
        return description;
    }

    public String getListingCar() {
        return "[" + getCondition() + " condition] " + getTransmission() + " " + getColour() + " " + getMake() + " " + getModel() + ", "
                + getDriveType() + "; with " + getMileage() + "km for $" + getPrice() + ".";
    }

    public int getId() {
        return id;
    }
}
