package model;

import java.util.ArrayList;

// Represents a car listing with a title...
public class Listing {
    private String title;           // title of the car
    private String description;     // description of the car
    private int id;                 // id of the car
    private static int nextId = 1;  // tracks id of next car listing created
    private double minBid;           // minBid of the car
    private double highestBid;         // bid of the car
    private int timeLeft;           // time left for the car
    private Car car;                // car of the car listing
    private ArrayList<Bid> bids;    // bids of the car listing

    // REQUIRES: title has a non-zero length, description has a non-zero length, mileage >= 0, yearMade >= 0,
    //           price >= 0, timeLeft >= 0.
    // MODIFIES: this
    // EFFECTS: title of the car is set to title, description of the car is set to description, mileage of the
    //          car is set to mileage, year the car was made is set to yearMade, price of the car is set to price.
    public Listing(String title, String description, double minBid, int timeLeft, Car car) {
        this.title = title;
        this.description = description;
        this.id = nextId++;
        this.minBid = minBid;
        this.highestBid = 0.0;
        this.timeLeft = timeLeft;
        this.car = car;
        this.bids = new ArrayList<Bid>();
    }

    // EFFECTS: returns title of the car
    public String getTitle() {
        return this.title;
    }

    // REQUIRES: bid is not null
    // MODIFIES: this
    // EFFECTS: returns description of the car
    public void addBid(Bid bid) {
        if (bid.getAmount() > highestBid) {
            highestBid = bid.getAmount();
        }
        bids.add(bid);
    }

    // EFFECTS: returns the highest bid
    public double getHighestBid() {
        return 0.0;  // STUB
    }

    // EFFECTS: returns number of bids
    public ArrayList<Bid> getBids() {
        return this.bids;
    }

    // EFFECTS: returns time left
    public int getTimeLeft() {
        return this.timeLeft;
    }
}
