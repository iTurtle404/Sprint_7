package org.example.courier;

public class BadCourier {

    private String login;
    private String firstname;

    public BadCourier(){};
    public BadCourier(String login, String firstname){
        this.login = login;
        this.firstname = firstname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
