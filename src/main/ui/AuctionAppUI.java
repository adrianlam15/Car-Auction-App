package ui;

import model.Cars;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AuctionAppUI extends UiState {

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

    private void runAuctionApp() {
        initUI();
        frame.add(cards);
        frame.setVisible(true);
    }

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
    
    @Override
    protected JPanel loadPanel() {
        return null;
    }
}
