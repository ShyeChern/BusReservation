package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.joda.time.Days;
import database.DBConnection;
import model.BusModel;
import model.ReservationModel;

public class ReservationController {
	ArrayList<Integer> seat=new ArrayList<Integer>();
	Integer[] originalSeat;
	int seatLimit;
	
	public ReservationController() {
		
	}
	

	public void addSeat(int seatNo) {
		seat.add(seatNo);
	}
	
	public void removeSeat(int seatNo) {
		seat.remove(Integer.valueOf(seatNo));
	}
	
	public Integer[] getSeat() {
		Integer[] allSeat = seat.toArray(new Integer[seat.size()]);
		return allSeat;
	}
	
	public boolean checkSeat(int busId, String destination, int seatNo, String date) throws SQLException, ClassNotFoundException {
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("SELECT COUNT(*) FROM RESERVATION WHERE BusId=? AND Destination=? AND SeatNo=? AND Date=?");
		preparedStatement.setInt (1, busId);
		preparedStatement.setString (2, destination);
		preparedStatement.setInt (3, seatNo);
		preparedStatement.setString (4, date);
		
		ResultSet rs=preparedStatement.executeQuery();
		rs.next();
		if(rs.getInt(1)>0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void confirmPayment(ReservationModel reservationModel) throws SQLException, ClassNotFoundException {
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("SELECT ReceiptNo FROM reservation ORDER BY ReceiptNo DESC LIMIT 1");
		ResultSet rs=preparedStatement.executeQuery();
		rs.next();
		reservationModel.setReceiptNo(rs.getInt(1)+1);
		
		preparedStatement=conn.prepareStatement("INSERT INTO RESERVATION (ReceiptNo, UserId, BusId, Destination, SeatNo, Date)"
				+ "VALUES (?,?,?,?,?,?)");
		
		for(int i:reservationModel.getSeatNo()) {
			preparedStatement.setInt (1, reservationModel.getReceiptNo());
			preparedStatement.setInt (2, reservationModel.getUserId());
			preparedStatement.setInt (3, reservationModel.getBusId());
			preparedStatement.setString (4, reservationModel.getDestination());
			preparedStatement.setInt (5, i);
			preparedStatement.setString (6, reservationModel.getDate());
			preparedStatement.executeUpdate();
		}
	}
	
	public HashMap<String, Integer> displayHistory(int userId) throws ClassNotFoundException, SQLException {
		HashMap<String, Integer> history=new HashMap<String, Integer>();
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("SELECT Destination, Date, ReceiptNo FROM RESERVATION WHERE UserId=? ORDER BY Date");
		preparedStatement.setInt (1, userId);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()) {
			history.put("("+rs.getInt(3)+") "+rs.getString(2)+" "+rs.getString(1), rs.getInt(3));
		}
		return history;
	}
	
	public void setHistory(ReservationModel reservationModel, BusModel busModel) throws SQLException, ClassNotFoundException {
		ArrayList <Integer> seatNo=new ArrayList<Integer>();
		double penalty=0;
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("SELECT BusId, Destination, SeatNo, Date, Penalty FROM RESERVATION WHERE ReceiptNo=?");
		preparedStatement.setInt (1, reservationModel.getReceiptNo());
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()) {
			busModel.setId(rs.getInt(1));
			reservationModel.setBusId(rs.getInt(1));
			reservationModel.setDestination(rs.getString(2));
			seatNo.add(rs.getInt(3));
			reservationModel.setDate(rs.getString(4));
			penalty+=rs.getDouble(5);
		}
		Collections.sort(seatNo);
		reservationModel.setSeatNo(seatNo.toArray(new Integer[seatNo.size()]));
		reservationModel.setPenalty(penalty);
		
		preparedStatement=conn.prepareStatement("SELECT Time,Price FROM BUS WHERE Id=?");
		preparedStatement.setInt (1, busModel.getId());
		rs=preparedStatement.executeQuery();
		rs.next();
		busModel.setTime(rs.getString(1));
		busModel.setPrice(rs.getDouble(2));
	}
	
	public boolean checkValidDelete(String date, String time) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String busDate=date+" "+time;
		String curDate=format.format(new Date());
		
		Date busDate1 = format.parse(busDate);
		Date curDate1 = format.parse(curDate);
		
		DateTime busDateTime = new DateTime(busDate1);
		DateTime curDateTime = new DateTime(curDate1);
		
		// third party api
		if(Days.daysBetween(curDateTime,busDateTime).getDays()>=7) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void deleteReservation(int receiptNo) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("DELETE FROM RESERVATION WHERE ReceiptNo=?");
		preparedStatement.setInt (1, receiptNo);
		preparedStatement.executeUpdate();
	}
	
	public boolean checkValidUpdate(String date, String time) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String busDate=date+" "+time;
		String curDate=format.format(new Date());
		
		Date busDate1 = format.parse(busDate);
		Date curDate1 = format.parse(curDate);
		
		DateTime busDateTime = new DateTime(busDate1);
		DateTime curDateTime = new DateTime(curDate1);
		
		// third party api
		if(Days.daysBetween(curDateTime,busDateTime).getDays()>=3) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canChooseSeat() {
		if (seat.size()<seatLimit) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getSeatLimit() {
		return seatLimit;
	}

	public void setSeatLimit(int seatLimit) {
		this.seatLimit = seatLimit;
	}
	
	public Integer[] getOriginalSeat() {
		return originalSeat;
	}

	public void setOriginalSeat(Integer [] originalSeat) {
		this.originalSeat = originalSeat;
	}
	
	public void updateSeat(int receiptNo) throws ClassNotFoundException, SQLException {
		ArrayList<Integer> oriSeatUpdate=new ArrayList<Integer>();
		
		ArrayList<Integer> oriIndexRemove=new ArrayList<Integer>();
		ArrayList<Integer> seatIndexRemove=new ArrayList<Integer>();
		
		Collections.sort(seat);
		
		for(int i:originalSeat) {
			System.out.println(i);
			oriSeatUpdate.add(i);
		}
		
		for(int i=0;i<oriSeatUpdate.size();i++) {
			for(int j=0;j<seat.size();j++) {
				if(oriSeatUpdate.get(i)==seat.get(j)) {
					oriIndexRemove.add(i);
					seatIndexRemove.add(j);
				}
			}
		}
		
		Collections.reverse(oriIndexRemove);
		Collections.reverse(seatIndexRemove);
		
		for(int i:oriIndexRemove) {
			oriSeatUpdate.remove(i);
		}
		
		for(int i:seatIndexRemove) {
			seat.remove(i);
		}
		
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("UPDATE RESERVATION SET SeatNo=?, Penalty=Penalty+1 WHERE ReceiptNo=? AND SeatNo=?");
		for(int i=0;i<oriSeatUpdate.size();i++) {
			preparedStatement.setInt (1, seat.get(i));
			preparedStatement.setInt (2, receiptNo);
			preparedStatement.setInt (3, oriSeatUpdate.get(i));
			preparedStatement.executeUpdate();
		}
		
	}
}
