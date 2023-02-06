package model;

import java.util.ArrayList;

// Represents a user with username, password, and bids
public class User {
    private String username;                    // username of user
    private String password;                    // password of user
    private ArrayList<Bid> bids;                // bids of user
    private ArrayList<Listing> listingsWon;     // listings won by User
    private ArrayList<Listing> listingsCreated; // listings created by User

    // REQUIRES: username and password are not null
    // MODIFIES: this
    // EFFECTS: creates a new user with username and password with empty savedListings, currentListings, and bids
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bids = new ArrayList<Bid>();
        this.listingsWon = new ArrayList<Listing>();
        this.listingsCreated = new ArrayList<Listing>();
    }

    // EFFECTS: returns username
    public String getUsername() {
        return this.username;
    }

    // EFFECTS: returns password
    public String getPassword() {
        return this.password;
    }

    // REQUIRES: amount > 0, Listing is not null
    // MODIFIES: this, listing
    // EFFECTS: adds Bid to the bids and adds bid to Listing
    public void makeBid(int amount, Listing listing) {
        Bid bid = new Bid(amount, this, listing);
        this.bids.add(bid);
        listing.addBid(bid);
    }

    // REQUIRES: Listing is not null
    // MODIFIES: this
    // EFFECTS: removes bid from bids and removes bid from Listing if present
    public void removeBid(Listing listing) {
        ArrayList<Bid> bidsToRemove = new ArrayList<Bid>();
        for (Bid bid : this.bids) {
            if (bid.getListing().equals(listing)) {
                bidsToRemove.add(bid);
            }
        }
        this.bids.removeAll(bidsToRemove);
    }

    // REQUIRES: Listing is not null
    // MODIFIES: this
    // EFFECTS: if bid is in bids, returns true, otherwise returns false
    public boolean inList(Listing listing) {
        for (Bid bid : this.bids) {
            if (bid.getListing().equals(listing)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns bids
    public ArrayList<Bid> getBids() {
        return this.bids;
    }

    public void createListing() {
        this.listingsCreated.add(listing);
    }
}
