package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

// class representing a car
public class Car implements Writable {
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
    private int timeLeftInSeconds;
    private int highestBid = 0;

    private final ArrayList<Bid> bids;

    private final Timer timer;

    private boolean expired;

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
        this.timer = new Timer();
        this.expired = false;
    }

    public ArrayList<Bid> getBids() {
        return bids;
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

    // REQUIRES: driveType must either be "Front Wheel Drive"/"FWD", "Rear Wheel Drive"/"RWD", or
    //           "All Wheel Drive"/"AWD"
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

    // MODIFIES: this
    // EFFECTS: gets highest bid on car
    public Bid getHighestBid() {
        if (bids.size() == 0) {
            return null;
        }
        highestBid = price;
        Bid highestBidObj = null;
        for (Bid bid : bids) {
            if (bid.getBidAmount() >= highestBid) {
                highestBid = bid.getBidAmount();
                highestBidObj = bid;
            } else {
                // do nothing
            }
        }
        return highestBidObj;
    }

    // REQUIRES: bid must be a positive integer
    // MODIFIES: this
    // EFFECTS: adds a bid to the car
    public void bid(User user, int bid) {
        this.bids.add(new Bid(user, bid));
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
        if (getTimer() == 0) {
            return "[" + getCondition() + " condition] " + getTransmission() + " " + getColour()
                    + " " + getMake() + " " + getModel() + ", " + getDriveType() + "; with "
                    + getMileage() + "km for $" + getPrice() + ".";
        } else {
            return "[" + getCondition() + " condition] " + getTransmission() + " " + getColour()
                    + " " + getMake() + " " + getModel() + ", " + getDriveType() + "; with "
                    + getMileage() + "km for $" + getPrice() + ".\n\tTime remaining: " + getTimer()
                    + " seconds.";
        }
    }

    public int getId() {
        return id;
    }

    @SuppressWarnings("methodlength")
    // REQUIRES: editChoice must be an integer between 1 and 11
    // MODIFIES: this
    // EFFECTS: edits the car based on the editChoice
    public void edit(int editChoice, String value) {
        if (editChoice == 1) {
            setMake(value);
        } else if (editChoice == 2) {
            setModel(value);
        } else if (editChoice == 3) {
            setColour(value);
        } else if (editChoice == 4) {
            setTransmission(value);
        } else if (editChoice == 5) {
            setDriveType(value);
        } else if (editChoice == 6) {
            setCondition(value);
        } else if (editChoice == 7) {
            setYear(Integer.parseInt(value));
        } else if (editChoice == 8) {
            setMileage(Integer.parseInt(value));
        } else if (editChoice == 9) {
            setPrice(Integer.parseInt(value));
        } else if (editChoice == 10) {
            setDescription(value);
        } else {
            setTimer(Integer.parseInt(value));
        }
    }

    // REQUIRES: time must be a positive integer > 0
    // MODIFIES: this
    // EFFECTS: sets the timer for the car
    public void setTimer(int time) {
        this.timeLeftInSeconds = time;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                timeLeftInSeconds--;
                if (timeLeftInSeconds <= 0) {
                    timeLeftInSeconds = 0;
                    markExpired();
                    giveToWinner();
                    timer.cancel();
                }
            }
        }, 1000L, 1000L);
    }

    // MODIFIES: this
    // EFFECTS: marks the car as expired
    public void markExpired() {
        this.expired = true;
    }

    // MODIFIES: this
    // EFFECTS: unmarks the car as expired
    public void unmarkExpired() {
        this.expired = false;
    }

    // MODIFIES: this
    // EFFECTS: gives the car to the winner
    public void giveToWinner() {
        if (getHighestBid() != null) {
            if (getHighestBid().getBidAmount() >= price) {
                User user = getHighestBid().getUser();
                user.getWonCars().add(this);
                this.expired = true;
            }
        }
    }

    public int getTimer() {
        return timeLeftInSeconds;
    }

    public boolean isExpired() {
        return expired;
    }

    // EFFECTS: returns the car as a string
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("make", make);
        json.put("model", model);
        json.put("colour", colour);
        json.put("transmission", transmission);
        json.put("driveType", driveType);
        json.put("condition", condition);
        json.put("year", year);
        json.put("price", price);
        json.put("mileage", mileage);
        json.put("description", description);
        json.put("id", id);
        json.put("expired", expired);
        json.put("timeLeftInSeconds", timeLeftInSeconds);
        EventLog.getInstance().logEvent(new Event("Car " + id + " saved to JSON file"));
        return json;
    }
}
