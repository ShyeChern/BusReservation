package view;

import java.awt.Dimension;
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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Create the frame.
	 */
	
	public LoginView() {
		BusController busController=new BusController();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 648);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Fastliner Bus Reservation");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 52));
		lblTitle.setBounds(57, 34, 661, 119);
		contentPane.add(lblTitle);
		
		JLabel lblIconRight = new JLabel("");
		lblIconRight.setBounds(487, 156, 95, 82);
		try {
			lblIconRight.setIcon(busController.getIcon(lblIconRight.getWidth(),lblIconRight.getHeight()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		contentPane.add(lblIconRight);
		
		JLabel lblIconLeft = new JLabel("");
		lblIconLeft.setBounds(166, 156, 95, 82);
		try {
			lblIconLeft.setIcon(busController.getIcon(lblIconLeft.getWidth(),lblIconLeft.getHeight()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		contentPane.add(lblIconLeft);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 45));
		lblLogin.setBounds(310, 156, 125, 68);
		contentPane.add(lblLogin);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 25));
		lblEmail.setBounds(201, 298, 100, 47);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 25));
		lblPassword.setBounds(144, 391, 139, 47);
		contentPane.add(lblPassword);
		
		JLabel lblResult = new JLabel("*");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setForeground(Color.RED);
		lblResult.setFont(new Font("Arial", Font.PLAIN, 16));
		lblResult.setBounds(257, 254, 230, 25);
		contentPane.add(lblResult);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEmail.setBounds(311, 297, 317, 50);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					UserModel userModel=new UserModel(txtEmail.getText(), txtPassword.getPassword());
					UserController userController=new UserController();
					try {
						if(userController.login(userModel)) {
							dispose();
							new MainMenuView(userModel).setVisible(true);
						}
						else {
							lblResult.setText("Invalid Email or Password");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		txtPassword.setBounds(311, 390, 317, 50);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserModel userModel=new UserModel(txtEmail.getText(), txtPassword.getPassword());
				UserController userController=new UserController();
				try {
					if(userController.login(userModel)) {
						dispose();
						new MainMenuView(userModel).setVisible(true);
					}
					else {
						lblResult.setText("Invalid Email or Password");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 26));
		btnLogin.setBounds(495, 492, 133, 49);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegisterView().setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 26));
		btnRegister.setBounds(165, 493, 165, 47);
		contentPane.add(btnRegister);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
