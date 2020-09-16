package com.subashtech.creditmanagement;

public class Transfer {


	private int id;
	private String from;
	private String to;
	private int amount;
	private boolean status;
	
	public Transfer(int id,String from, String to, int amount, boolean status) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.amount= amount;
		this.status= status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

	@Override
	public String toString() {
		return "Transfer [from=" + from + ", to=" + to + ", amount=" + amount + ", status=" + status + "]";
	}
	 
	
}
