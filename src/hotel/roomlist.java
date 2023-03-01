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
import javax.swing.border.BevelBorder;

public class roomlist extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					roomlist frame = new roomlist();
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
		private JTable table;
		
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
				String query="select * from room ORDER BY R_NUMBER  ";
				pat=con.prepareStatement(query);
				rs=pat.executeQuery();
				
				table.setModel(DbUtils.resultSetToTableModel(rs));

			}
			  catch (Exception e2) 
				{
				// TODO: handle exception
				  System.out.println(e2);
				}
		}	
	
//constructor
	public roomlist()
	{
		init();
		Connect();
		load_table();
	}
	
//function for initialize components 
	public void  init()
	{
		setBounds(100, 100, 1342, 746);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Room List");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
		lblNewLabel.setBounds(521, 22, 243, 38);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 116, 1308, 583);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "ROOM NO", "ROOM TYPE", "RATE", "BED", "STATUS"
			}
		));
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setMinWidth(20);
		scrollPane.setViewportView(table);
	}
}
