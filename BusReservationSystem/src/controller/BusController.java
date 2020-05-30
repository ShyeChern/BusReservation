package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import database.DBConnection;
import model.BusModel;
import model.ReservationModel;

public class BusController {
	
	public BusController() {
		
	}
	
	public ImageIcon getIcon(int width, int height) throws IOException {
		BufferedImage img = null;
		img = ImageIO.read(new File("C:\\Users\\Lenovo\\eclipse-workspace\\BusReservationSystem\\logo.png"));
		Image dimg = img.getScaledInstance(width, height,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		return imageIcon;
	}
	
	@SuppressWarnings("null")
	public HashMap<Integer,String> getBusTime() throws SQLException, ClassNotFoundException {
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("SELECT Id, Time FROM Bus ORDER BY Time");
		ResultSet rs=preparedStatement.executeQuery();
		HashMap<Integer,String> busTime = new HashMap<Integer,String>();
		while(rs.next()) {
			busTime.put(rs.getInt(1),rs.getString(2));
		}
		
		return busTime;
	}
	
	public double calculatePrice(BusModel busModel, ReservationModel reservationModel) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("SELECT Price,Time FROM Bus WHERE Id=?");
		preparedStatement.setInt (1, busModel.getId());
		ResultSet rs=preparedStatement.executeQuery();
		rs.next();
		busModel.setPrice(rs.getDouble(1));
		busModel.setTime(rs.getString(2));
		double price = 0;
		for(int i=0; i<reservationModel.getSeatNo().length;i++) {
			price+=rs.getDouble(1);
		}
		price+=1;
		return price;
	}
	
	public double calculatePrice(double price, int totalSeat, int receiptNo) throws ClassNotFoundException, SQLException {
		double penalty = 0;
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("SELECT Penalty FROM RESERVATION WHERE ReceiptNo=?");
		preparedStatement.setInt (1, receiptNo);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()) {
			penalty+=rs.getDouble(1);
		}
		return (price*totalSeat+1+penalty);
	}
	
	//third party api
	public CategoryDataset createDataset(int userId) throws ClassNotFoundException, SQLException {
		
		ArrayList<Integer> data=new ArrayList<Integer>();
		Connection conn = DBConnection.doConnection();
		for(int i=0;i<12;i++) {
			PreparedStatement preparedStatement=conn.prepareStatement("SELECT COUNT(*) FROM reservation WHERE MONTH(Date) = ? AND UserId=?");
			preparedStatement.setInt (1, i+1);
			preparedStatement.setInt (2, userId);
			ResultSet rs=preparedStatement.executeQuery();
			rs.next();
			data.add(rs.getInt(1));
		}
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(data.get(0), "Tickets", "Jan");
		dataset.setValue(data.get(1), "Tickets", "Feb");
		dataset.setValue(data.get(2), "Tickets", "Mar");
		dataset.setValue(data.get(3), "Tickets", "Apr");
		dataset.setValue(data.get(4), "Tickets", "May");
		dataset.setValue(data.get(5), "Tickets", "Jun");
		dataset.setValue(data.get(6), "Tickets", "Jul");
		dataset.setValue(data.get(7), "Tickets", "Aug");
		dataset.setValue(data.get(8), "Tickets", "Sep");
		dataset.setValue(data.get(9), "Tickets", "Oct");
		dataset.setValue(data.get(10), "Tickets", "Nov");
		dataset.setValue(data.get(11), "Tickets", "Dec");
	
		return dataset;
	}
	
	public JFreeChart createChart(CategoryDataset dataset) {
		
		JFreeChart barChart = ChartFactory.createBarChart(
				"Ticket Bought in year " + Calendar.getInstance().get(Calendar.YEAR),
                "Month",
                "Ticket Bought",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
		
		CategoryPlot chartPlot = barChart.getCategoryPlot();
		ValueAxis yAxis = chartPlot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
        return barChart;
    }
}
