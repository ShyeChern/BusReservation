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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class HistoryDetailView extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	
	public HistoryDetailView(  UserModel userModel, BusModel busModel, ReservationModel reservationModel    ) {
		BusController busController=new BusController();
		ReservationController reservationController=new ReservationController();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("History Detail");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		JLabel lblSeatNo = new JLabel("Seat No:");
		lblSeatNo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSeatNo.setBounds(202, 174, 77, 24);
		panelCenter.add(lblSeatNo);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTotalPrice.setBounds(176, 270, 101, 24);
		panelCenter.add(lblTotalPrice);
		
		JLabel lblTotalPriceInfo = new JLabel("RM 12.00");
		lblTotalPriceInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTotalPriceInfo.setBounds(358, 270, 90, 24);
		panelCenter.add(lblTotalPriceInfo);
		try {
			lblTotalPriceInfo.setText("RM "+busController.calculatePrice(busModel.getPrice(), reservationModel.getSeatNo().length, reservationModel.getReceiptNo())+"0");
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDestination.setBounds(176, 16, 103, 24);
		panelCenter.add(lblDestination);
		
		JLabel lblDestinationInfo = new JLabel();
		lblDestinationInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDestinationInfo.setBounds(358, 16, 143, 24);
		panelCenter.add(lblDestinationInfo);
		lblDestinationInfo.setText(reservationModel.getDestination());
		
		JLabel lblDateInfo = new JLabel();
		lblDateInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDateInfo.setBounds(358, 68, 165, 24);
		panelCenter.add(lblDateInfo);
		lblDateInfo.setText(reservationModel.getDate());
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDate.setBounds(231, 68, 48, 24);
		panelCenter.add(lblDate);
		
		JLabel lblTimeInfo = new JLabel();
		lblTimeInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTimeInfo.setBounds(358, 124, 98, 24);
		panelCenter.add(lblTimeInfo);
		lblTimeInfo.setText(busModel.getTime());
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTime.setBounds(230, 124, 49, 24);
		panelCenter.add(lblTime);
		
		JLabel lblSeatNoInfo = new JLabel("13, 14, 15");
		lblSeatNoInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSeatNoInfo.setBounds(358, 174, 163, 24);
		panelCenter.add(lblSeatNoInfo);
		String seatNoInfo = "";
		for(int i:reservationModel.getSeatNo()) {
			seatNoInfo+=i+" ";
		}
		lblSeatNoInfo.setText(seatNoInfo);
		
		JLabel lblNote = new JLabel("Penalty of RM 1.00 will be charge to change the seat");
		lblNote.setFont(new Font("Arial", Font.ITALIC, 16));
		lblNote.setBounds(146, 310, 370, 19);
		panelCenter.add(lblNote);
		
		JLabel lblPenalty = new JLabel("Penalty:");
		lblPenalty.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPenalty.setBounds(208, 223, 71, 24);
		panelCenter.add(lblPenalty);
		
		JLabel lblPenaltyInfo = new JLabel("RM 12.00");
		lblPenaltyInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPenaltyInfo.setBounds(358, 223, 90, 24);
		panelCenter.add(lblPenaltyInfo);
		lblPenaltyInfo.setText("RM "+reservationModel.getPenalty()+"0");
		
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
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new HistoryView(userModel).setVisible(true);
			}
		});
		
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.BOTH;
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 0;
		panelSouth.add(btnBack, gbc_btnBack);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(reservationController.checkValidUpdate(reservationModel.getDate(),busModel.getTime())) {
						dispose();
						new UpdateView(userModel, busModel, reservationModel).setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null,"Update are not available (less than 3 days from the departure date or already past)","Warning",JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(reservationController.checkValidDelete(reservationModel.getDate(),busModel.getTime())) {
						int result=JOptionPane.showConfirmDialog (null, "Cancellation are available (more than 7 days from the departure date)\n Cancel the Reservation?", "Warning", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.YES_OPTION) {
							try {
								reservationController.deleteReservation(reservationModel.getReceiptNo());
								dispose();
								new HistoryView(userModel).setVisible(true);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Cancellation are not available (less than 7 days from the departure date)","Warning",JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (HeadlessException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.VERTICAL;
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 0;
		panelSouth.add(btnDelete, gbc_btnDelete);
		
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.gridx = 3;
		gbc_btnUpdate.gridy = 0;
		panelSouth.add(btnUpdate, gbc_btnUpdate);
		panelSouth.setPreferredSize( new Dimension(0, 60) );
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
