package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfListingTest {
    ListOfListing lol1;
    ListOfListing lol2;
    Listing l1;
    Listing l2;
    Car car1;
    Car car2;

    @BeforeEach
    void setUp(){
        car1 = new Car("Toyota", "Supra", "Excellent", "Silver", "6-Speed Manual",
                "RWD", 10000, 1998);
        car2 = new Car("Nissan", "R-34 GTR", "Fair", "Blue", "6-Speed Manual",
                "AWD", 10000, 1998);
        lol1 = new ListOfListing();
        lol2 = new ListOfListing();
        l1 = new Listing("listing1", "description1", 1.0, 0, car1);
        l2 = new Listing("listing2", "description2", 2.0, 1, car2);
    }

    @Test
    void constructorTest() {
        assertEquals(0, lol1.getListings().size());
        assertEquals(0, lol2.getListings().size());
    }

    @Test
    void addListingTest() {
        lol2.addListing(l1);
        lol2.addListing(l2);
        assertEquals(0, lol1.getListings().size());
        assertEquals(2, lol2.getListings().size());
    }

    @Test
    void removeListingTest() {
        lol2.addListing(l1);
        lol2.addListing(l2);
        lol2.removeListing(l1);
        assertEquals(1, lol2.getListings().size());
    }
}
