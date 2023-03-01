package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

public class checkinlist extends JFrame {

	private JPanel contentPane;
	private JTable tablecheckin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkinlist frame = new checkinlist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	//database connection 
		Connection con;
		PreparedStatement pat;
		ResultSet rs;
		
		public void Connect()
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost/hms","root","");
				
			}
			catch(ClassNotFoundException ex)
			{
				
			}
			catch(SQLException ex)
			{
				
			}
		}
	// end of connection function 

	//for load table
		public void load_table()
		{
			try 
			{
				String query="select * from checkin ";
				pat=con.prepareStatement(query);
				rs=pat.executeQuery();
				
				tablecheckin.setModel(DbUtils.resultSetToTableModel(rs));
			}
			  catch (Exception e2) 
				{
				// TODO: handle exception
				  System.out.println(e2);
				}
		}	
	
//constructor
	public checkinlist()
	{
		init();
		Connect();
		load_table();
	}
	
//function for initialize components 

	public void init() 
	{
		setBackground(new Color(204, 255, 255));
		setBounds(100, 100, 1334, 766);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Check IN List");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(553, 33, 214, 40);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 112, 1290, 575);
		contentPane.add(scrollPane);
		
		tablecheckin = new JTable();
		scrollPane.setViewportView(tablecheckin);
		tablecheckin.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Name", "Phone No", "ID Type", "ID No", "Gender", "Adult", "Child", "Room Type", "Room No", "Rate", "Bed", "CheckIn","Checkin In Time", "CheckOut", "Discount Type", "Amount", "Total", "Advance", "Pending","Balance"
				}
			));
		tablecheckin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}
}
