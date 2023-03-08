package model;

import org.json.JSONArray;

import java.util.ArrayList;

// class representing a list of users
public class Users {
    private ArrayList<User> users;

    // EFFECTS: creates a new list of users
    public Users() {
        this.users = new ArrayList<User>();
    }

    // REQUIRES: user must be non-null
    // MODIFIES: this
    // EFFECTS: adds a user to the list of users
    public void add(User user) {
        users.add(user);
    }

    // REQUIRES: i must be a valid index
    // EFFECTS: returns the user at index i
    public User getUser(int i) {
        return users.get(i);
    }

    // REQUIRES: user must be non-null
    // MODIFIES: this
    // EFFECTS: removes a user from the list of users
    public void remove(User user) {
        users.remove(user);
    }

    // EFFECTS: returns the size of the list of users
    public int getSize() {
        return users.size();
    }

    // EFFECTS: returns the list of users
    public ArrayList<User> getUsers() {
        return users;
    }

    // EFFECTS: returns the list of users as a JSON array
    public JSONArray toJson() {
        JSONArray json = usersToJson();
        return json;
    }

    // EFFECTS: returns the list of users as a JSON array
    private JSONArray usersToJson() {
        JSONArray json = new JSONArray();
        for (User u : users) {
            json.put(u.toJson());
        }
        return json;
    }
}
