package model;

import java.util.ArrayList;

// Represents List of Listings with listings and size
public class ListOfListing {
    private ArrayList<Listing> listings;
    private int size;

    // MODIFIES: this
    // EFFECTS: creates a new list of listings with size 0
    public ListOfListing() {
        this.listings = new ArrayList<>();
        this.size = 0;
    }

    // REQUIRES: Listing that is not already in listings
    // MODIFIES: this
    // EFFECTS: adds listing to listings
    public void addListing(Listing listing) {
        listings.add(listing);
    }

    // REQUIRES: Listing that is already in listings
    // MODIFIES: this
    // EFFECTS: removes listing from listings
    public void removeListing(Listing listing) {
        listings.remove(listing);
    }

    // EFFECTS: returns the size of listings
    public int getSize() {
        return 0;
    }
}
