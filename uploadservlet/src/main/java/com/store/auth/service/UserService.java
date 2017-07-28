package com.store.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.apache.log4j.Logger;

import com.store.auth.model.UserProxy;
import com.store.model.User;
import com.store.repo.UserRepository;

@Service
@Controller
public class UserService {

    private static Logger logger = Logger.getLogger(UserService.class);
    private static final List<UserProxy> USERS = new ArrayList<>();
    @Autowired
    private UserRepository userrepo;

    static {
        USERS.add(new UserProxy("admin@admin", "admin", "admin", true));
        USERS.add(new UserProxy("user@user", "user", "user"));
    }

    public boolean hasUser(UserProxy user) {
	if (USERS.contains(user)) {
		return USERS.contains(user);
	} else {
		User u = userrepo.findOne(user.getEmail(), user.getPassword());
		return u != null ? true : false;
	}
    }

   public UserProxy getUser(UserProxy up) {
	int i = USERS.indexOf(up);
	if (i >= 0) {
		return USERS.get(i);
	} else {
		User u = userrepo.findOne(up.getEmail(), up.getPassword());
		if (u != null)
			return new UserProxy(u.getEmail(), u.getName(), u.getPassword(), u.getIsAdmin());
		else
			return null;
	}
   }
}
