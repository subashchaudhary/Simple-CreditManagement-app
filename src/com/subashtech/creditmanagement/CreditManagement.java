package com.subashtech.creditmanagement;

import java.util.ArrayList;

public interface CreditManagement {
	public ArrayList userList();
	public String UserDetails(String name, String email);
	public int creditTransfer(String fromUser, String toUser,int amount);
	
}
