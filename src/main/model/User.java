package model;

import java.util.ArrayList;

// Represents a user with username, password, and bids
public class User {
    private String username;        // username of user
    private String password;        // password of user
    private ArrayList<Bid> bids;    // bids of user

    // REQUIRES: username and password are not null
    // MODIFIES: this
    // EFFECTS: creates a new user with username and password with empty savedListings, currentListings, and bids
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // EFFECTS: returns username
    public String getUsername() {
        return this.username;
    }

    // EFFECTS: returns password
    public String getPassword() {
        return this.password;
    }

    // REQUIRES: bid is not null
    // MODIFIES: this, listing
    // EFFECTS: adds bid to bids and adds bid to listing
    public void makeBid(Bid bid, Listing listing) {
        bids.add(bid);
        listing.addBid(bid);
    }
}
