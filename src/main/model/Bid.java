package model;

// Represents a bid made by a User on a Listing. Contains the bid amount, the User who made the bid, and the Listing
public class Bid {
    private double bidAmount;   // amount of bid
    private User bidder;        // user who made the bid
    private Listing listing;    // listing that the bid was made on

    public Bid(double bidAmount, User bidder, Listing listing) {
        this.bidAmount = bidAmount;
        this.bidder = bidder;
        this.listing = listing;
    }

    // EFFECTS: returns bid amount
    public double getAmount() {
        return this.bidAmount;
    }

    // EFFECTS: returns bidder
    public User getBidder() {
        return this.bidder;
    }

    // EFFECTS: returns listing
    public Listing getListing() {
        return this.listing;
    }
}
