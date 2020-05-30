package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import com.toedter.calendar.JCalendar;
import controller.BusController;
import model.BusModel;
import model.ReservationModel;
import model.UserModel;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class ChooseDetailView extends JFrame {

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	
	@SuppressWarnings("unused")
	public ChooseDetailView(  UserModel userModel  ) {
		BusController busController=new BusController();
		BusModel busModel = new BusModel();
		
		HashMap<Integer,String> busTime= new HashMap<Integer,String>();
		try {
			busTime=busController.getBusTime();
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		final HashMap<Integer,String> busTimeInner = busTime;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Ticket Detail");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 45));
		lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		//third party api
		JCalendar calendar = new JCalendar();
		
		Date dateFrom = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dateFrom); 
		c.add(Calendar.DATE, 1);
		dateFrom = c.getTime();
		
		Date dateTo = new Date();
		c.add(Calendar.DATE, 89);
		dateTo = c.getTime();
		calendar.setSelectableDateRange(dateFrom,dateTo);
		
		calendar.setBounds(76, 59, 238, 176);
		panelCenter.add(calendar);
		
		JComboBox comboBoxLocation = new JComboBox();
		comboBoxLocation.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBoxLocation.setModel(new DefaultComboBoxModel(new String[] {"Johor", "Kedah", "Kelantan", "Melaka", "Negeri Sembilan", "Pahang", "Penang", "Perak", "Perlis", "Selangor", "Terenganu"}));
		comboBoxLocation.setBounds(408, 59, 133, 30);
		panelCenter.add(comboBoxLocation);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 30));
		lblDate.setBounds(163, 16, 62, 35);
		panelCenter.add(lblDate);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Arial", Font.PLAIN, 30));
		lblLocation.setBounds(425, 16, 112, 35);
		panelCenter.add(lblLocation);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Arial", Font.PLAIN, 30));
		lblTime.setBounds(441, 132, 67, 35);
		panelCenter.add(lblTime);
		
		
		JComboBox<String> comboBoxTime = new JComboBox();
		comboBoxTime.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBoxTime.setBounds(408, 174, 133, 30);
		for(String i:busTime.values()) {
			comboBoxTime.addItem(i);
		}
		panelCenter.add(comboBoxTime);
		
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
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenuView(userModel).setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 20));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 0;
		panelSouth.add(btnCancel, gbc_btnCancel);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date=calendar.getYearChooser().getYear()+"-"+(calendar.getMonthChooser().getMonth()+1)+"-"+calendar.getDayChooser().getDay();
				int busId = 0;
				
				for(int i:busTimeInner.keySet()) {
					if(comboBoxTime.getSelectedItem().toString().equals(busTimeInner.get(i))) {
						busModel.setId(i);
					}
				}
				
				ReservationModel reservationModel=new ReservationModel(userModel.getId(), busModel.getId(), comboBoxLocation.getSelectedItem().toString(), date );
				dispose();
				new ChooseSeatView(userModel,busModel,reservationModel).setVisible(true);
			}
		});
		btnNext.setFont(new Font("Arial", Font.PLAIN, 20));
		
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.fill = GridBagConstraints.BOTH;
		gbc_btnNext.gridx = 3;
		gbc_btnNext.gridy = 0;
		panelSouth.add(btnNext, gbc_btnNext);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
	}
}
