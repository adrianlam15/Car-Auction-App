package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a class for a bid
public class Bid implements Writable {
    private Car car;
    private final User user;
    private int bidAmount;

    public Bid(User user, Car car, int bidAmount) {
        this.user = user;
        this.car = car;
        this.bidAmount = bidAmount;
        EventLog.getInstance().logEvent(new Event("Bid placed on " + car.getListingCar() + " for $" + bidAmount));
    }

    public Bid(User user,int bidAmount) {
        this.user = user;
        this.bidAmount = bidAmount;
        EventLog.getInstance().logEvent(new Event("Bid placed for $" + bidAmount));
    }

    public Car getCar() {
        return car;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public User getUser() {
        return user;
    }

    public String getBid() {
        if (car == null) {
            return "Bid: $" + bidAmount;
        }
        return car.getListingCar() + "\nBid: $" + bidAmount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("car", car.toJson());
        json.put("bidAmount", bidAmount);
        EventLog.getInstance().logEvent(new Event("Bid converted to JSON: " + json));
        return json;
    }

}
