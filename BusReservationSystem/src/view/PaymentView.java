package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BusController;
import controller.ReservationController;
import model.BusModel;
import model.ReservationModel;
import model.UserModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PaymentView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	
	public PaymentView(UserModel userModel, BusModel busModel, ReservationModel reservationModel) {
		ReservationController reservationController=new ReservationController();
		BusController busController=new BusController();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Payment");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 50));
		lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Arial", Font.BOLD, 20));
		lblTotalPrice.setBounds(176, 235, 109, 24);
		panelCenter.add(lblTotalPrice);
		
		JLabel lblTotalPriceInfo = new JLabel();
		lblTotalPriceInfo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTotalPriceInfo.setBounds(358, 235, 101, 24);
		panelCenter.add(lblTotalPriceInfo);
		try {
			lblTotalPriceInfo.setText("RM "+busController.calculatePrice(busModel, reservationModel)+"0");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblTotalSeat = new JLabel("Total Seat:");
		lblTotalSeat.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTotalSeat.setBounds(188, 111, 97, 24);
		panelCenter.add(lblTotalSeat);
		
		JLabel lblPriceSeat = new JLabel("Price/Seat:");
		lblPriceSeat.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPriceSeat.setBounds(187, 59, 98, 24);
		panelCenter.add(lblPriceSeat);
		
		JLabel lblTotalSeatInfo = new JLabel();
		lblTotalSeatInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalSeatInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTotalSeatInfo.setBounds(358, 111, 101, 24);
		panelCenter.add(lblTotalSeatInfo);
		lblTotalSeatInfo.setText(""+reservationModel.getSeatNo().length);
		
		JLabel lblPriceSeatInfo = new JLabel();
		lblPriceSeatInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPriceSeatInfo.setBounds(358, 62, 101, 24);
		panelCenter.add(lblPriceSeatInfo);
		lblPriceSeatInfo.setText("RM "+busModel.getPrice()+"0");
		
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTax.setBounds(247, 159, 38, 24);
		panelCenter.add(lblTax);
		
		JLabel lblTaxInfo = new JLabel("RM 1.00");
		lblTaxInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTaxInfo.setBounds(358, 159, 101, 24);
		panelCenter.add(lblTaxInfo);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setPreferredSize( new Dimension(0, 60) );
		panelSouth.setBorder(new EmptyBorder(0, 0, 20, 0));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_panelSouth = new GridBagLayout();
		gbl_panelSouth.columnWidths = new int[]{121, 115, 190, 126, 0};
		gbl_panelSouth.rowHeights = new int[]{40, 0};
		gbl_panelSouth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelSouth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelSouth.setLayout(gbl_panelSouth);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservationModel.setSeatNo(null);
				dispose();
				new ChooseSeatView(userModel,busModel, reservationModel).setVisible(true);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.BOTH;
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 0;
		panelSouth.add(btnBack, gbc_btnBack);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					reservationController.confirmPayment(reservationModel);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new ReceiptView(userModel, busModel, reservationModel).setVisible(true);
			}
		});
		btnConfirm.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.fill = GridBagConstraints.BOTH;
		gbc_btnConfirm.gridx = 3;
		gbc_btnConfirm.gridy = 0;
		panelSouth.add(btnConfirm, gbc_btnConfirm);
		panelSouth.setPreferredSize( new Dimension(0, 60) );
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
