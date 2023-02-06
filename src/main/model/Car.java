package model;

public class Car {
    private String make;
    private String model;
    private String colour;
    private String transmission;
    private String driveType;
    private String condition;
    private int year;
    private int price;
    private int mileage;

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
}
