package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;

public class Users {
    private ArrayList<User> users;

    public Users() {
        this.users = new ArrayList<User>();
    }

    public void add(User user) {
        users.add(user);
    }

    public User get(int id) {
        return users.get(id);
    }

    public void remove(User user) {
        users.remove(user);
    }

    public int getSize() {
        return users.size();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public JSONArray toJson() {
        JSONArray json = usersToJson();
        return json;
    }

    private JSONArray usersToJson() {
        JSONArray json = new JSONArray();
        for (User u : users) {
            json.put(u.toJson());
        }
        return json;
    }
}
