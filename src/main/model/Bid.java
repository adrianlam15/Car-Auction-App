package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a class for a bid
public class Bid implements Writable {
    private Car car;
    private final User user;
    private final int bidAmount;

    public Bid(User user, Car car, int bidAmount) {
        this.user = user;
        this.car = car;
        this.bidAmount = bidAmount;
    }

    public Bid(User user,int bidAmount) {
        this.user = user;
        this.bidAmount = bidAmount;
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
        return json;
    }

}
