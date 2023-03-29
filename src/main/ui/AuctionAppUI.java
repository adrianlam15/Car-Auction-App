package ui;

import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AuctionAppUI extends UiState {

    public AuctionAppUI() throws IOException {
        loaded = false;
        frame = new JFrame("Car Auction App");
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        setFrame();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        currentUser = new User();
        users = jsonReader.readUsers();
        userMap = jsonReader.getUserMap();
        runAuctionApp();
    }

    private void runAuctionApp() {


    }

    @Override
    protected JPanel loadPanel() {
        return null;
    }
}
