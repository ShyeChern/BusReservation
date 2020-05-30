package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BusController;
import model.BusModel;
import model.ReservationModel;
import model.UserModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ReceiptView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	
	public ReceiptView(UserModel userModel, BusModel busModel, ReservationModel reservationModel) {
		BusController busController=new BusController();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Receipt");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 50));
		lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		JLabel lblReceiptNo = new JLabel("Receipt No:");
		lblReceiptNo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblReceiptNo.setBounds(158, 46, 103, 24);
		panelCenter.add(lblReceiptNo);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTime.setBounds(212, 109, 49, 24);
		panelCenter.add(lblTime);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDate.setBounds(213, 171, 48, 24);
		panelCenter.add(lblDate);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTotalPrice.setBounds(160, 234, 101, 24);
		panelCenter.add(lblTotalPrice);
		
		JLabel lblNote = new JLabel("Thank you for your purchase. Have a nice day.");
		lblNote.setFont(new Font("Arial", Font.ITALIC, 18));
		lblNote.setBounds(121, 311, 374, 22);
		panelCenter.add(lblNote);
		
		JLabel lblReceiptNoInfo = new JLabel();
		lblReceiptNoInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceiptNoInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblReceiptNoInfo.setBounds(300, 46, 101, 24);
		panelCenter.add(lblReceiptNoInfo);
		lblReceiptNoInfo.setText(""+reservationModel.getReceiptNo());
		
		JLabel lblTimeInfo = new JLabel();
		lblTimeInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTimeInfo.setBounds(300, 109, 103, 24);
		panelCenter.add(lblTimeInfo);
		lblTimeInfo.setText(busModel.getTime());
		
		JLabel lblDateInfo = new JLabel();
		lblDateInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDateInfo.setBounds(300, 171, 103, 24);
		panelCenter.add(lblDateInfo);
		lblDateInfo.setText(reservationModel.getDate());
		
		JLabel lblTotalPriceInfo = new JLabel("RM 12.00");
		lblTotalPriceInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTotalPriceInfo.setBounds(300, 234, 103, 24);
		panelCenter.add(lblTotalPriceInfo);
		try {
			lblTotalPriceInfo.setText("RM "+busController.calculatePrice(busModel.getPrice(), reservationModel.getSeatNo().length, 0)+"0");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenuView(userModel).setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
		btnNewButton.setPreferredSize( new Dimension(100, 40) );
		panelSouth.add(btnNewButton);
		panelSouth.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

}
