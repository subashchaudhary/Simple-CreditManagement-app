package com.subashtech.creditmanagement;


public class User {

private String id;
private String name;
private String email;
private String amount;

public User(String id, String name, String email, String amount) {
	this.id = id;
	this.name = name;
	this.email = email;
	this.amount = amount;
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

public void setEmail(String email) {
	this.email = email;
}

public String getAmount() {
	return amount;
}

public void setAmount(String amount) {
	this.amount = amount;
}


@Override
public String toString() {
	return "User [name=" + name + ", email=" + email + ", amount=" + amount + "]";
}

}