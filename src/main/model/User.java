package model;

public class User {
    private String user;
    private String password;
    private Car car;
    private Cars listedCars;
    private Cars savedCars;
    private Cars biddedCars;

    // for development purposes, returns TRUE
    public boolean login(String usr, String pwd) {
        return true;
    }

    public boolean createUser(String usr, String pwd, String checkPwd) {
        if (pwd.equals(checkPwd)) {
            this.user = usr;
            this.password = pwd;
            return true;
        } else {
            return false;
        }
    }

    public void createCar(Car carToCreate) {
        car = carToCreate;
    }

    public void deleteCar() {
        // TODO: implement this method
    }

    public void editCar() {
        // TODO: implement this method
    }

    public void placeBid() {
        // TODO: implement this method
    }

    public void viewListings() {
        // TODO: implement this method
    }

    public void viewBids() {
        // TODO: implement this method
    }

}
