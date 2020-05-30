package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DBConnection;
import model.UserModel;

public class UserController {
	
	public UserController() {
		
	}
	
	public String addUser(UserModel userModel) throws ClassNotFoundException, SQLException{
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement=conn.prepareStatement("SELECT Count(*) FROM user WHERE Email=?");
		preparedStatement.setString (1, userModel.getEmail());
		ResultSet rs=preparedStatement.executeQuery();
		rs.next();
		if(userModel.getFirstName().isEmpty()||userModel.getLastName().isEmpty()||userModel.getEmail().isEmpty()||userModel.getPassword().isEmpty()||userModel.getPasswordConfirm().isEmpty()) {
			return "Please Fill in all the field";
		}
		else if (!userModel.getPassword().equals(userModel.getPasswordConfirm())) {
			return "Mismatch Password";
		}
		else if(rs.getInt(1)==1) {
			return "Email already Exist";
		}
		else {
			preparedStatement = conn.prepareStatement("INSERT INTO USER "
					+ "(FirstName, LastName, Email, Password) VALUES (?, ?, ?, ?)");
	
			preparedStatement.setString (1, userModel.getFirstName());	
			preparedStatement.setString (2, userModel.getLastName());	
			preparedStatement.setString (3, userModel.getEmail());	
			preparedStatement.setString (4, userModel.getPassword());
			preparedStatement.executeUpdate();
			conn.close();
			return "Register Success";
		}
		
	}
	
	public boolean login(UserModel userModel) throws SQLException, ClassNotFoundException {
		if(userModel.getEmail().isEmpty()||userModel.getPassword().isEmpty()) {
			return false;
		}
		Connection conn = DBConnection.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement
				("SELECT Id, FirstName, Password FROM USER WHERE BINARY Email=?");
		preparedStatement.setString(1, userModel.getEmail());
		
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()) {
			if(rs.getString(3).equals(userModel.getPassword())){
				userModel.setId(rs.getInt(1));
				userModel.setFirstName(rs.getString(2));
				return true;
			}
		}
		conn.close();
		return false;
			
	}

}
