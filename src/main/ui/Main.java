package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new AuctionApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (IOException e) {   // remove exception after finished debugging json
            System.out.println("Unable to run application: IO exception");
        }
    }
}
