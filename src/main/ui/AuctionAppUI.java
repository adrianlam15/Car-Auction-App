package ui;

import javax.swing.*;
import java.io.IOException;

/**
 * AuctionAppUI class (including UI) for the Car Auction application
 */
public class AuctionAppUI extends UiState {

    // MODIFIES: UiState
    // EFFECTS: starts the Auction App
    public AuctionAppUI() throws IOException {
        init();
        initFrame();
        initUIState();
        try {
            initUsers();
        } catch (IOException e) {
            System.out.println("Failed to import users and userMap");
        }
        runAuctionApp();
    }

    // MODIFIES: UiState
    // EFFECTS: initializes the UI with frames and panels
    private void runAuctionApp() {
        initUI();
        frame.add(cards);
        frame.setVisible(true);
    }

    // MODIFIES: UiState
    // EFFECTS: initializes appropriate subclasses of UiState
    private void initUI() {
        loginUI = new Login();
        mainMenuUI = new MainMenu();
        createListingUI = new CreateListing();
        viewListingsUI = new ViewListings();
        viewYourListingsUI = new ViewYourListings();
        createAccountUI = new CreateAccount();
        viewBidsUI = new ViewBids();
        viewWonUI = new ViewWon();
        initPanels();
        addCards();
        cardLayout.show(cards, "loginMenu");
    }

    // MODIFIES: UiState
    @Override
    protected JPanel loadPanel() {
        return null;
    }
}
