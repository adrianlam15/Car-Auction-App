package model;

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

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
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
