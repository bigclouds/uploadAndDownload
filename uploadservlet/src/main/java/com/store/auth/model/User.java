package com.store.auth.model;

import java.util.Objects;

public class User {

    private String email;
    private String password;
    private String username;
    private boolean isadmin;

    public User(String email, String username, String password) {
	this(email, username, password, false);
    }

    public User(String email, String password) {
        this(email, null, password, false);
    }

    public User(String email, String username, String password, boolean isadmin) {
        this.email = email;
        this.username = username;
        this.password = password;
	this.isadmin = isadmin;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public String getName() {
	return username;
    }

    public boolean isAdmin() {
	return this.isadmin;
    }

    @Override
    public String toString() {
        return this.email + " " + (this.isadmin ? "ADMIN" : "Not ADMIN");
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
            Objects.equals(password, user.password);
    }
}
