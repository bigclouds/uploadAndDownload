package com.store.model;

import java.util.Date;
import org.springframework.util.Assert;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document(collection = "user") 
@CompoundIndexes({  
    @CompoundIndex(name = "email_idx", def = "{'email': 1, 'birthday': -1}")  
})  
public class User {
	
	@Id
	@Indexed(unique = true)
	private String id;
	private String name;
	@Indexed(unique = true)
	private String email;
	private String password;
	private Date birthday;
	boolean confirmed;
	boolean isAdmin;

	public User(String name, String email, String password, Date date, boolean verify, boolean isadm) {
		//Assert.hasText(name, "name must not be null or empty!");
		Assert.hasText(email, "email must not be null or empty!");
		Assert.hasText(password, "password must not be null or empty!");
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = date;
		this.confirmed = verify;
		this.isAdmin = isadm;
	}
	public User() {
	}
	public User(String name, String email, String password) {
		this(name, email, password, null, false, false);
	}
	public User(String email, String password) {
		this(null, email, password, null, false, false);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String name) {
		this.email = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String name) {
		this.password = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean verify) {
		this.confirmed = verify;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean adm) {
		this.isAdmin = adm;
	}

	@Override
	public String toString() {
		return "User[name=" + name + ",email=" + email + ",birthday=" + birthday + "]";
	}

	@Override
        public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(this.email, user.email);
       }
}
