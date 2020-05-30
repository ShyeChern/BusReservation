package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BusController;
import controller.UserController;
import model.UserModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class RegisterView extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtRetypePassword;

	/**
	 * Create the frame.
	 */
	public RegisterView() {
		BusController busController=new BusController();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Fastliner Bus Reservation");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 48));
		lblTitle.setBounds(63, 0, 622, 119);
		contentPane.add(lblTitle);
		
		JLabel lblIconLeft = new JLabel("");
		lblIconLeft.setBounds(142, 114, 95, 82);
		try {
			lblIconLeft.setIcon(busController.getIcon(lblIconLeft.getWidth(),lblIconLeft.getHeight()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		contentPane.add(lblIconLeft);
		
		JLabel lblRegister = new JLabel("Register\r\n");
		lblRegister.setFont(new Font("Arial", Font.BOLD, 41));
		lblRegister.setBounds(266, 128, 186, 68);
		contentPane.add(lblRegister);
		
		JLabel lblIconRight = new JLabel("");
		lblIconRight.setBounds(466, 114, 95, 82);
		try {
			lblIconRight.setIcon(busController.getIcon(lblIconRight.getWidth(),lblIconRight.getHeight()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		contentPane.add(lblIconRight);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Arial", Font.BOLD, 23));
		lblFirstName.setBounds(142, 255, 126, 28);
		contentPane.add(lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Arial", Font.PLAIN, 20));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(300, 251, 309, 39);
		contentPane.add(txtFirstName);
		
		JLabel lblResult = new JLabel("*");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Arial", Font.PLAIN, 16));
		lblResult.setForeground(Color.RED);
		lblResult.setBounds(213, 212, 329, 36);
		contentPane.add(lblResult);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Arial", Font.BOLD, 23));
		lblLastName.setBounds(145, 318, 123, 28);
		contentPane.add(lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Arial", Font.PLAIN, 20));
		txtLastName.setColumns(10);
		txtLastName.setBounds(300, 314, 309, 39);
		contentPane.add(txtLastName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 23));
		lblEmail.setBounds(200, 384, 68, 28);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(300, 380, 309, 39);
		contentPane.add(txtEmail);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		txtPassword.setColumns(10);
		txtPassword.setBounds(300, 441, 309, 39);
		contentPane.add(txtPassword);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 23));
		lblPassword.setBounds(151, 445, 117, 28);
		contentPane.add(lblPassword);
		
		txtRetypePassword = new JPasswordField();
		txtRetypePassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String result = null;
					UserController userController=new UserController();
					UserModel userModel=new UserModel(txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtPassword.getPassword(), txtRetypePassword.getPassword());
					try {
						result=userController.addUser(userModel);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(result=="Register Success") {
						txtFirstName.setText("");
						txtLastName.setText("");
						txtEmail.setText("");
						txtPassword.setText("");
						txtRetypePassword.setText("");
					}
					lblResult.setText(result);
				}
			}
		});
		txtRetypePassword.setFont(new Font("Arial", Font.PLAIN, 20));
		txtRetypePassword.setColumns(10);
		txtRetypePassword.setBounds(300, 506, 309, 39);
		contentPane.add(txtRetypePassword);
		
		JLabel lblRetypePassword = new JLabel("Re-type Password:");
		lblRetypePassword.setFont(new Font("Arial", Font.BOLD, 23));
		lblRetypePassword.setBounds(63, 510, 207, 28);
		contentPane.add(lblRetypePassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = null;
				UserController userController=new UserController();
				UserModel userModel=new UserModel(txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtPassword.getPassword(), txtRetypePassword.getPassword());
				try {
					result=userController.addUser(userModel);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(result=="Register Success") {
					txtFirstName.setText("");
					txtLastName.setText("");
					txtEmail.setText("");
					txtPassword.setText("");
					txtRetypePassword.setText("");
				}
				lblResult.setText(result);
			}
		});
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 26));
		btnRegister.setBounds(444, 586, 165, 47);
		contentPane.add(btnRegister);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new LoginView().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 26));
		btnBack.setBounds(135, 585, 133, 49);
		contentPane.add(btnBack);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
	}

}
