package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BusController;
import model.UserModel;

import java.io.IOException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuView extends JFrame {

	private JPanel contentPane;
	private JPanel panelNorth;
	private JLabel lblTitle;
	private JLabel lblMainMenu;
	private JLabel lblIconLeft;
	private JLabel lblIconRight;
	private JPanel panelSouth;
	private JButton btnReserveSeat;
	private JButton btnHistory;
	private JLabel lblWelcomeBack;
	private JButton btnStatistic;
	private JButton btnLogout;

	/**
	 * Create the frame.
	 */
	
	public MainMenuView(UserModel userModel) {
		BusController busController=new BusController();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorth = new JPanel();
		panelNorth.setPreferredSize( new Dimension(150, 180) );
		contentPane.add(panelNorth, BorderLayout.NORTH);
		
		lblTitle = new JLabel("Fastliner Bus Reservation");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 52));
		panelNorth.add(lblTitle);
		
		lblIconLeft = new JLabel("");
		lblIconLeft.setPreferredSize( new Dimension( 95, 82 ) );
		Dimension d = lblIconLeft.getPreferredSize();  
		try {
			lblIconLeft.setIcon(busController.getIcon((int)d.getWidth(), (int)d.getHeight()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		panelNorth.add(lblIconLeft);
		
		lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setBorder(new EmptyBorder(10, 30, 10, 30));
		lblMainMenu.setFont(new Font("Arial", Font.BOLD, 45));
		panelNorth.add(lblMainMenu);
		
		lblIconRight = new JLabel("");
		lblIconRight.setPreferredSize( new Dimension( 95, 82 ) );
		try {
			lblIconRight.setIcon(busController.getIcon((int)d.getWidth(), (int)d.getHeight()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panelNorth.add(lblIconRight);
		
		panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.CENTER);
		GridBagLayout gbl_panelSouth = new GridBagLayout();
		gbl_panelSouth.columnWidths = new int[]{196, 325, 0};
		gbl_panelSouth.rowHeights = new int[]{48, 53, 35, 53, 35, 53, 35, 50, 0};
		gbl_panelSouth.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelSouth.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSouth.setLayout(gbl_panelSouth);
		
		lblWelcomeBack = new JLabel("Welcome Back"+" "+userModel.getFirstName());
		lblWelcomeBack.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_lblWelcomeBack = new GridBagConstraints();
		gbc_lblWelcomeBack.insets = new Insets(0, 0, 5, 0);
		gbc_lblWelcomeBack.gridx = 1;
		gbc_lblWelcomeBack.gridy = 0;
		panelSouth.add(lblWelcomeBack, gbc_lblWelcomeBack);
		
		btnReserveSeat = new JButton("Reserve Seat");
		btnReserveSeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ChooseDetailView(userModel).setVisible(true);
			}
		});
		btnReserveSeat.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnReserveSeat = new GridBagConstraints();
		gbc_btnReserveSeat.fill = GridBagConstraints.BOTH;
		gbc_btnReserveSeat.insets = new Insets(0, 0, 5, 0);
		gbc_btnReserveSeat.gridx = 1;
		gbc_btnReserveSeat.gridy = 1;
		panelSouth.add(btnReserveSeat, gbc_btnReserveSeat);
		
		btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HistoryView(userModel).setVisible(true);
			}
		});
		btnHistory.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnHistory = new GridBagConstraints();
		gbc_btnHistory.fill = GridBagConstraints.BOTH;
		gbc_btnHistory.insets = new Insets(0, 0, 5, 0);
		gbc_btnHistory.gridx = 1;
		gbc_btnHistory.gridy = 3;
		panelSouth.add(btnHistory, gbc_btnHistory);
		
		btnStatistic = new JButton("Statistic");
		btnStatistic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserChartView(userModel).setVisible(true);
			}
		});
		
		btnStatistic.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnStatistic = new GridBagConstraints();
		gbc_btnStatistic.insets = new Insets(0, 0, 5, 0);
		gbc_btnStatistic.fill = GridBagConstraints.BOTH;
		gbc_btnStatistic.gridx = 1;
		gbc_btnStatistic.gridy = 5;
		panelSouth.add(btnStatistic, gbc_btnStatistic);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginView().setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.fill = GridBagConstraints.BOTH;
		gbc_btnLogout.gridx = 1;
		gbc_btnLogout.gridy = 7;
		panelSouth.add(btnLogout, gbc_btnLogout);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
		
}
