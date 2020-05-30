package model;

public class ReservationModel {
	private int receiptNo;
	private int userId;
	private int busId;
	private String destination;
	private Integer[] seatNo;
	private String date;
	private double penalty;
	
	public ReservationModel() {
		
	}
	
	public ReservationModel(int userId, int busId, String destination, String date) {
		this.userId=userId;
		this.busId=busId;
		this.destination=destination;
		this.date=date;
	}
	
	public int getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Integer [] getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(Integer [] seatNo) {
		this.seatNo = seatNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	
	
}
