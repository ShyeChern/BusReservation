package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.BusController;
import model.UserModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class UserChartView extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public UserChartView(UserModel userModel) {
		BusController busController=new BusController();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 856);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("User Ticket History");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 50));
		lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setPreferredSize(new Dimension(700, 470));
		contentPane.add(panelCenter, BorderLayout.CENTER);
		
		//third party api
		CategoryDataset dataset = null;
		try {
			dataset = busController.createDataset(userModel.getId());
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JFreeChart chart = busController.createChart(dataset);  
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white); 
		panelCenter.add(chartPanel);
		pack();
		 
		
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
