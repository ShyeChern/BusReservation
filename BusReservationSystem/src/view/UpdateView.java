package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.ReservationController;
import model.BusModel;
import model.ReservationModel;
import model.UserModel;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	
	public UpdateView(  UserModel userModel, BusModel busModel, ReservationModel reservationModel  ) {
		ReservationController reservationController=new ReservationController();
	
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Update Seat");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 50));
		lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new MigLayout("", "[111px][42px][][][][][][111px][][][][][][][][]", "[41.00px][260px][][][][]"));
		
		JLabel lblDriver = new JLabel("Driver");
		lblDriver.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDriver.setHorizontalAlignment(SwingConstants.RIGHT);
		panelCenter.add(lblDriver, "cell 7 0,alignx right,aligny center");
		
		JPanel panelLeftSeat = new JPanel();
		panelCenter.add(panelLeftSeat, "cell 3 1,alignx left,aligny top");
		panelLeftSeat.setLayout(new GridLayout(0, 2, 10, 10));
		panelLeftSeat.setPreferredSize( new Dimension(120,260) );
		
		JPanel panelDivider = new JPanel();
		panelDivider.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCenter.add(panelDivider, "cell 5 1,alignx center,aligny top");
		panelDivider.setPreferredSize( new Dimension(50,260) );
		
		JPanel panelRightSeat = new JPanel();
		panelCenter.add(panelRightSeat, "cell 7 1,alignx left,aligny top");
		panelRightSeat.setLayout(new GridLayout(0, 2, 10, 10));
		panelRightSeat.setPreferredSize( new Dimension(120,260) );
		
		JLabel lblOriginalSeat = new JLabel("Original Seat: ");
		lblOriginalSeat.setFont(new Font("Arial", Font.PLAIN, 18));
		panelCenter.add(lblOriginalSeat, "cell 2 4 7 2,grow");
		String seat = "";
		for(int i:reservationModel.getSeatNo()) {
			reservationController.addSeat(i);
			seat+=i+", ";
		}
		lblOriginalSeat.setText(lblOriginalSeat.getText()+seat);
		reservationController.setOriginalSeat(reservationModel.getSeatNo());
		reservationController.setSeatLimit(reservationModel.getSeatNo().length);
		
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
				dispose();
				new HistoryDetailView(userModel, busModel, reservationModel).setVisible(true);
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
				int result=JOptionPane.showConfirmDialog (null, "Are you Confirm to Update the Seat? (RM 1.00 will be charged for each changed seat)", "Warning", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					try {
						reservationController.updateSeat(reservationModel.getReceiptNo());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
					new HistoryView(userModel).setVisible(true);
				}
			}
		});
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.gridx = 3;
		gbc_btnUpdate.gridy = 0;
		panelSouth.add(btnUpdate, gbc_btnUpdate);
		panelSouth.setPreferredSize( new Dimension(0, 60) );
		
		btnUpdate.setEnabled(false);
		
		for(int i=1;i<=24;i+=2) {
			String name=Integer.toString(i);
			JToggleButton tglbtn = new JToggleButton(name);
			final JToggleButton tglbtnInner = tglbtn;
			tglbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tglbtnInner.isSelected()) {
						if(reservationController.canChooseSeat()) {
							reservationController.addSeat(Integer.parseInt(e.getActionCommand()));
							if(reservationController.getSeat().length==reservationController.getSeatLimit()) {
								btnUpdate.setEnabled(true);
							}
						}
						else {
							tglbtnInner.setSelected(false);
						}
						
					}
					else if(!tglbtnInner.isSelected()) {
						reservationController.removeSeat(Integer.parseInt(e.getActionCommand()));
						btnUpdate.setEnabled(false);
					}
				}
			});
			
			panelLeftSeat.add(tglbtn);
			
			try {
				if(reservationController.checkSeat(busModel.getId(),reservationModel.getDestination(),i,reservationModel.getDate())){
					tglbtn.setEnabled(false);
					for(int seatNo:reservationModel.getSeatNo()) {
						if(i==seatNo) {
							tglbtn.setEnabled(true);
							tglbtn.setSelected(true);
						}
					}
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			i++;
			
			name=Integer.toString(i);
			tglbtn = new JToggleButton(name);
			final JToggleButton tglbtnInner1 = tglbtn;
			tglbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tglbtnInner1.isSelected()) {
						if(reservationController.canChooseSeat()) {
							reservationController.addSeat(Integer.parseInt(e.getActionCommand()));
							if(reservationController.getSeat().length==reservationController.getSeatLimit()) {
								btnUpdate.setEnabled(true);
							}
						}
						else {
							tglbtnInner1.setSelected(false);
						}
						
					}
					else if(!tglbtnInner1.isSelected()) {
						reservationController.removeSeat(Integer.parseInt(e.getActionCommand()));
						btnUpdate.setEnabled(false);
					}
				}
			});
			
			panelLeftSeat.add(tglbtn);
			
			try {
				if(reservationController.checkSeat(busModel.getId(),reservationModel.getDestination(),i,reservationModel.getDate())){
					tglbtn.setEnabled(false);
					for(int seatNo:reservationModel.getSeatNo()) {
						if(i==seatNo) {
							tglbtn.setEnabled(true);
							tglbtn.setSelected(true);
						}
					}
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			i++;
		}
		
		for(int i=3;i<=24;i+=2) {
			String name=Integer.toString(i);
			JToggleButton tglbtn = new JToggleButton(name);
			final JToggleButton tglbtnInner = tglbtn;
			tglbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tglbtnInner.isSelected()) {
						if(reservationController.canChooseSeat()) {
							reservationController.addSeat(Integer.parseInt(e.getActionCommand()));
							if(reservationController.getSeat().length==reservationController.getSeatLimit()) {
								btnUpdate.setEnabled(true);
							}
						}
						else {
							tglbtnInner.setSelected(false);
						}
						
					}
					else if(!tglbtnInner.isSelected()) {
						reservationController.removeSeat(Integer.parseInt(e.getActionCommand()));
						btnUpdate.setEnabled(false);
					}
				}
			});
			
			panelRightSeat.add(tglbtn);
			
			try {
				if(reservationController.checkSeat(busModel.getId(),reservationModel.getDestination(),i,reservationModel.getDate())){
					tglbtn.setEnabled(false);
					for(int seatNo:reservationModel.getSeatNo()) {
						if(i==seatNo) {
							tglbtn.setEnabled(true);
							tglbtn.setSelected(true);
						}
					}
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			i++;
			
			name=Integer.toString(i);
			tglbtn = new JToggleButton(name);
			final JToggleButton tglbtnInner1 = tglbtn;
			tglbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tglbtnInner1.isSelected()) {
						if(tglbtnInner1.isSelected()) {
							if(reservationController.canChooseSeat()) {
								reservationController.addSeat(Integer.parseInt(e.getActionCommand()));
								if(reservationController.getSeat().length==reservationController.getSeatLimit()) {
									btnUpdate.setEnabled(true);
								}
							}
							else {
								tglbtnInner1.setSelected(false);
							}
							
						}
					}
					else if(!tglbtnInner1.isSelected()) {
						reservationController.removeSeat(Integer.parseInt(e.getActionCommand()));
						btnUpdate.setEnabled(false);
					}
				}
			});
			
			panelRightSeat.add(tglbtn);
			
			try {
				if(reservationController.checkSeat(busModel.getId(),reservationModel.getDestination(),i,reservationModel.getDate())){
					tglbtn.setEnabled(false);
					for(int seatNo:reservationModel.getSeatNo()) {
						if(i==seatNo) {
							tglbtn.setEnabled(true);
							tglbtn.setSelected(true);
						}
					}
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			i++;
		}
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

}
