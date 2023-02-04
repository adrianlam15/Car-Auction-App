package model;

// Represents a car with make, model, condition, colour, transmission, drive type, mileage, and year made
public class Car {
    private String make;            // make of the car
    private String model;           // model of the car
    private String condition;       // condition of the car
    private String colour;          // colour of the car
    private String transmission;    // transmission of the car
    private String driveType;       // drive type of the car
    private int mileage;            // mileage of the car
    private int yearMade;           // year the car was made

    // REQUIRES: make has a non-zero length, model has a non-zero length, condition has a non-zero length,
    //           colour has a non-zero length, transmission has a non-zero length, driveType has a non-zero length,
    //           mileage >= 0, yearMade >= 0.
    public Car(String make, String model, String condition, String colour, String transmission,
               String driveType, int mileage, int yearMade) {
        this.make = make;
        this.model = model;
        this.condition = condition;
        this.colour = colour;
        this.transmission = transmission;
        this.driveType = driveType;
        this.mileage = mileage;
        this.yearMade = yearMade;
    }

    // EFFECTS: returns make of the car
    public String getMake() {
        return this.make;
    }

    // EFFECTS: returns model of the car
    public String getModel() {
        return this.model;
    }

    // EFFECTS: returns condition of the car
    public String getCondition() {
        return this.condition;
    }

    // EFFECTS: returns colour of the car
    public String getColour() {
        return this.colour;
    }

    // EFFECTS: returns transmission of the car
    public String getTransmission() {
        return this.transmission;
    }

    // EFFECTS: returns drive type of the car
    public String getDriveType() {
        return this.driveType;
    }

    // EFFECTS: returns mileage of the car
    public int getMileage() {
        return this.mileage;
    }

    // EFFECTS: returns year the car was made
    public int getYearMade() {
        return this.yearMade;
    }
}
