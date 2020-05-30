package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ReservationController;
import model.BusModel;
import model.ReservationModel;
import model.UserModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class HistoryView extends JFrame {

	private JPanel contentPane;

	public HistoryView(  UserModel userModel  ) {
		ReservationController reservationController=new ReservationController();
		ReservationModel reservationModel=new ReservationModel();
		BusModel busModel=new BusModel();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistory.setFont(new Font("Arial", Font.BOLD, 50));
		lblHistory.setBorder(new EmptyBorder(20, 0, 20, 0));
		contentPane.add(lblHistory, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(new EmptyBorder(30, 30, 30, 30));
		
		JPanel scrollPanel = new JPanel();
		scrollPane.setViewportView(scrollPanel);
		scrollPanel.setLayout(new GridLayout(0, 2, 50, 50));
		
		HashMap<String, Integer> history=new HashMap<String, Integer>();
		try {
			history=reservationController.displayHistory(userModel.getId());
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(String i:history.keySet()) {
			final HashMap<String,Integer> historyInner = history;
			JButton button = new JButton(i);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(String i:historyInner.keySet()) {
						if(i.equals(button.getText())) {
							reservationModel.setReceiptNo(historyInner.get(i));
							try {
								reservationController.setHistory(reservationModel,busModel);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					dispose();
					new HistoryDetailView(userModel, busModel, reservationModel).setVisible(true);
				}
			});
			button.setFont(new Font("Arial", Font.PLAIN, 18));
			scrollPanel.add(button);
		}
		
		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenuView(userModel).setVisible(true);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 18));
		btnBack.setPreferredSize( new Dimension(100, 40) );
		panelSouth.add(btnBack);
		panelSouth.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
