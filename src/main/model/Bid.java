package model;

// represents a class for a bid
public class Bid {
    private Car car;
    private User user;
    private int bidAmount;

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
}
