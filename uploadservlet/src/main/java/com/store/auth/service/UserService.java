package com.store.auth.service;

import java.util.ArrayList;
import java.util.List;
import com.store.auth.model.User;

public class UserService {

    private static final List<User> USERS = new ArrayList<>();

    static {
        USERS.add(new User("admin@admin", "admin"));
        USERS.add(new User("user@user", "user"));
    }

    public static boolean hasUser(User user) {
        return USERS.contains(user);
    }
}
