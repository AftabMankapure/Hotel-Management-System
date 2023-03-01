package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class manageroom extends JFrame {

	private JPanel contentPane;
	private JTextField txtrno;
	private JTextField txtrate;
	private JTextField txtbed;
	private JTable rtable;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
	     			manageroom frame = new manageroom();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField txtsearch;
	final JComboBox cbstatus = new JComboBox();
	final JComboBox cbrtype = new JComboBox();

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
			String query="select * from room ";
			pat=con.prepareStatement(query);
			rs=pat.executeQuery();
			
			rtable.setModel(DbUtils.resultSetToTableModel(rs));
		}
		  catch (Exception e2) 
			{
			// TODO: handle exception
			  System.out.println(e2);
			}
	}
	
//constructor
	public manageroom() 
	{	
		Connect();
		initialize();
		load_table();
	}
	
// function for initialize components
	public void initialize() 
	{
		setBounds(100, 100, 1072, 574);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 74, 1038, 453);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnadd = new JButton("Add Room");
		btnadd.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
//To addd new room 
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String no=txtrno.getText();
				String bed=txtbed.getText();
				String rate=txtrate.getText();
				String type=(String) cbrtype.getSelectedItem();
				String status=(String) cbstatus.getSelectedItem();

				try 
				{
						pat=(PreparedStatement)con.prepareStatement("insert into room(R_NUMBER,R_TYPE,Rate,Bed,STATUS) values(?,?,?,?,?)");
						pat.setString(1, no);
						pat.setString(2, type);
						pat.setString(3, rate);
						pat.setString(4,bed );
						pat.setString(5,status );
						
						pat.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Room Added..");
						load_table();
	
						txtrate.setText("");
						txtrno.setText("");
						txtbed.setText("");
						cbrtype.setSelectedItem(null);
						cbstatus.setSelectedItem(null);
						
						txtrno.setFocusable(true);
				}
				catch (Exception e2) 
					{
						e2.printStackTrace();
					}
				
				
			}
		});
		btnadd.setBounds(66, 404, 150, 40);
		panel_1.add(btnadd);
		
		JButton btncancel = new JButton("Cancel");
	
//to close manageroom window
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);

			}
		});
		btncancel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btncancel.setBounds(774, 404, 150, 40);
		panel_1.add(btncancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(543, 88, 485, 283);
		panel_1.add(scrollPane);
		
		rtable = new JTable();
		scrollPane.setViewportView(rtable);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
//for update the room information
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String no=txtrno.getText();
				String rate=txtrate.getText();
				String bed=txtbed.getText();
				String type=(String) cbrtype.getSelectedItem();
				String status=(String) cbstatus.getSelectedItem();
				
//				  String search=txtsearch.getText();
				  try
				   {
//					  UPDATE `room` SET `id`='[value-1]',`R_NUMBER`='[value-2]',`R_TYPE`='[value-3]',
//					  `Rate`='[value-4]',`Bed`='[value-5]',`STATUS`='[value-6]' WHERE 1
					
					pat=(PreparedStatement)con.prepareStatement("update room set R_NUMBER=?, R_TYPE=?,Rate=?,Bed=?,STATUS=?  where R_NUMBER=? ");
					pat.setString(1,no);
					pat.setString(2, type);
					pat.setString(3,rate);
					pat.setString(4,bed);
					pat.setString(5,status);
					pat.setString(6,no);
					
					pat.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Room Record Updated..");
					load_table();
					
					txtrno.setText("");
					txtrate.setText("");
					txtbed.setText("");
					cbrtype.setSelectedItem(null);
					cbstatus.setSelectedItem(null);
				   }
				    catch (Exception e2)
				  	 	{
				    		// TODO: handle exception
				    		System.out.println(e2);
				  	 	}
			}
		});
		btnupdate.setBounds(308, 404, 150, 40);
		panel_1.add(btnupdate);
		
		JButton btndelete = new JButton("Delete");
		btndelete.setFont(new Font("Times New Roman", Font.BOLD, 25));
	
//for delete the room record from table 
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String no=txtrno.getText();
				
				try 
				{
					 pat=(PreparedStatement) con.prepareStatement("delete from room where R_NUMBER=?");
					 pat.setString(1, no);
					 
					 pat.executeUpdate();
					 JOptionPane.showMessageDialog(null, "Room Record deleted..");

					 load_table();
					
					 txtrno.setText("");
					 txtrate.setText("");
					 txtbed.setText("");
					 cbrtype.setSelectedItem(null);
					 cbstatus.setSelectedItem(null);
					   
				}
				  catch (Exception e2) 
					{
					  	System.out.println(e2);
					}
			}
		});
		btndelete.setBounds(558, 404, 150, 40);
		panel_1.add(btndelete);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(10, 10, 1018, 68);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Room Number");
		lblNewLabel_7.setBounds(33, 25, 206, 26);
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		txtsearch = new JTextField();
		txtsearch.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtsearch.setBounds(399, 21, 206, 30);
		panel.add(txtsearch);
		txtsearch.setColumns(10);
		
		JButton btnsearch = new JButton("Search");
		btnsearch.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
// for search the record for table and display in respective textfield
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String search=txtsearch.getText();
				   
				   try {
						pat=(PreparedStatement) con.prepareStatement("select * from  room where R_NUMBER=?");
						pat.setString(1,search);
						rs=pat.executeQuery();
											
							if(rs.next()==true)
							{
								String R_NUMBER=rs.getString(2);
								String R_TYPE=rs.getString(3);
								String bed=rs.getString(5);
								String Rate=rs.getString(4);	
								String STATUS=rs.getString(6);
				
								txtrno.setText(R_NUMBER);
								txtbed.setText(bed);
								txtrate.setText(Rate);
								cbrtype.setSelectedItem(R_TYPE);
								cbstatus.setSelectedItem(STATUS);
								
							}
								else
									{
										JOptionPane.showMessageDialog(null,"Invalid Room Number");
									}
					} 
				   		catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
			}
		});
		btnsearch.setBounds(760, 21, 150, 34);
		panel.add(btnsearch);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 255));
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 82, 503, 289);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Room Status");
		lblNewLabel_6.setBounds(23, 228, 203, 30);
		panel_2.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel lblNewLabel_5 = new JLabel("Number Of Bed");
		lblNewLabel_5.setBounds(23, 175, 254, 30);
		panel_2.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel lblNewLabel_4 = new JLabel("Room Rate");
		lblNewLabel_4.setBounds(23, 121, 203, 30);
		panel_2.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel lblNewLabel_3 = new JLabel("Room Type");
		lblNewLabel_3.setBounds(23, 78, 203, 30);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel lblNewLabel_2 = new JLabel("Room Number");
		lblNewLabel_2.setBounds(23, 24, 203, 30);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		cbrtype.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		cbrtype.setBounds(302, 82, 181, 30);
		panel_2.add(cbrtype);
		
		cbrtype.addItem("NON AC");
		cbrtype.addItem("AC");
		cbrtype.addItem("VIP");
		cbrtype.addItem("DELUXE");;
		cbrtype.setSelectedItem(null);
		
		txtrno = new JTextField();
		txtrno.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtrno.setBounds(302, 29, 181, 30);
		panel_2.add(txtrno);
		txtrno.setColumns(10);
		
		txtrate = new JTextField();
		txtrate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtrate.setBounds(302, 126, 181, 30);
		panel_2.add(txtrate);
		txtrate.setColumns(10);
		
		txtbed = new JTextField();
		txtbed.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtbed.setBounds(302, 180, 181, 30);
		panel_2.add(txtbed);
		txtbed.setColumns(10);
		cbstatus.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		cbstatus.setBounds(302, 228, 181, 30);
		panel_2.add(cbstatus);
		//occupied, vacant, dirty, clean, 
				cbstatus.addItem("Occupied");
				cbstatus.addItem("Vacant");
				cbstatus.addItem("Dirty");
				cbstatus.addItem("Clean");
				
				cbstatus.setSelectedItem(null);
		
		
		JLabel lblNewLabel = new JLabel("Manage Room ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 35));
		lblNewLabel.setBounds(381, 31, 293, 44);
		contentPane.add(lblNewLabel);
		
			}
}
