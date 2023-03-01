package hotel;


import java.awt.Dimension;


import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.border.TitledBorder;



import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;

public class login extends JFrame
{

	private static final long serialVersionUID = -8383077646910056161L;
	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpasswoard;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	
	// database connection start
	Connection con;
	PreparedStatement pat;
	ResultSet rs;
	public void Connect()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/hms","root","");
			//System.out.println("succes");
			
			
		}
		catch(ClassNotFoundException ex)
		{
			
		}
		catch(SQLException ex)
		{
			
		}
	}
	
	// end of connection function
	
	
	// constructor
	public login() 
	{
		setTitle("LOGIN\r\n");
		setBackground(new Color(224, 255, 255));
		Connect();
		initialize();
	
	}
	
	
	// function for initialize components
	private void initialize() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 444);
		setLocationRelativeTo(null);  

		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("LOGIN");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnNewButton_2.setBackground(new Color(0, 204, 0));
		btnNewButton_2.setBounds(72, 573, 129, 43);
		contentPane.add(btnNewButton_2);
		
		JButton btnclear = new JButton("CLEAR");
		btnclear.setForeground(Color.WHITE);
		btnclear.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnclear.setBackground(new Color(0, 204, 0));
		btnclear.setBounds(211, 573, 129, 43);
		contentPane.add(btnclear);
		
		JLabel lhms = new JLabel("NEW TAJ HOTEL");
		lhms.setBounds(96, 20, 270, 48);
		contentPane.add(lhms);
		lhms.setFont(new Font("Algerian", Font.BOLD, 35));
		
		JButton btnclear_1 = new JButton("CLEAR");
		btnclear_1.setBounds(200, 297, 129, 43);
		contentPane.add(btnclear_1);
		btnclear_1.setForeground(Color.WHITE);
		btnclear_1.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnclear_1.setBackground(new Color(0, 204, 0));
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBounds(61, 297, 129, 43);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Statement stmt = con.createStatement();
//SELECT `id`, `user`, `password` FROM `user` WHERE 1
					String sql = "select * from user where user = '"+txtusername.getText()+"' and password = '"+txtpasswoard.getText().toString()+"' ";
					ResultSet rs = stmt.executeQuery(sql);
						if(rs.next())
							{
							 JOptionPane.showMessageDialog(null, "Login Successfull..!!","Login Success..!!",JOptionPane.INFORMATION_MESSAGE);
							 dashboard.main(null);
							 setVisible(false);
							
							}
							else
								{
							    JOptionPane.showMessageDialog(null, "Check The Credentials..!!");
								
								}
								con.close();
								
				  				}
						catch(Exception e1)
								{
				  					JOptionPane.showMessageDialog(null, "Database Error..!!", "Database Error", 2);
								  //System.out.print(e1);
								}
			
			}
		});
		
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnNewButton.setBackground(new Color(0, 204, 0));
		
		txtpasswoard = new JPasswordField();
		txtpasswoard.setBounds(135, 238, 291, 35);
		contentPane.add(txtpasswoard);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1_1.setBounds(20, 189, 194, 25);
		contentPane.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Arial Black", Font.BOLD, 26));
		
		txtusername = new JTextField();
		txtusername.setBounds(135, 134, 291, 35);
		contentPane.add(txtusername);
		txtusername.setFont(new Font("Arial Black", Font.BOLD, 26));
		txtusername.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("USERNAME");
		lblNewLabel_1_1.setBounds(20, 83, 194, 25);
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 26));
	}
}
