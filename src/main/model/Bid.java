package model;

// represents a class for a bid
public class Bid {
    private Car car;
    private int bidAmount;

    public Bid(Car car, int bidAmount) {
        this.car = car;
        this.bidAmount = bidAmount;
    }

    public Bid(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Car getCar() {
        return car;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public String getBid() {
        if (car == null) {
            return "Bid: $" + bidAmount;
        }
        return car.getListingCar() + "\nBid: $" + bidAmount;
    }
}
