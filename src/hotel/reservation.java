package hotel;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class reservation extends JFrame 
{
	private JPanel main;
	private JTextField txtname;
	private JTextField txtphoneno;
	private JTextField txtidno;
	private JTextField txtadult;
	private JTextField txtchild;
	private JTextField txtrate;
	private JTextField txtbed;
	private final JTextField textField = new JTextField();
	private JTextField txtadvance;
	private JTextField txttotal;
	private JTextField txtbalance;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reservation frame = new reservation();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
// connection to DB
	Connection con;
	PreparedStatement pst,pst1;
	ResultSet rs;
	
	
	//fields
	final JComboBox cbrno = new JComboBox();
	final JComboBox cbdtype = new JComboBox();
	final JDateChooser dcto = new JDateChooser();
	final JDateChooser dcfrom = new JDateChooser();
	private JTextField txtpending;
	private JTextField txtamount;



	//connect
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
			System.out.println(ex);
		}
	}
	
	//room no
	public void RoomNo()
	{
		String sqlq="select * from room WHERE STATUS = 'Vacant'";
		try 
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost/hms","root","");

			pst=con.prepareStatement(sqlq);
			rs=pst.executeQuery();

			while(rs.next())
			{
				cbrno.setSelectedItem(null);
				String room=rs.getString("R_NUMBER");
				cbrno.addItem(room);
			}
			
		} 	
			catch (Exception e1)
			{
				// TODO: handle exception
				System.out.println(e1);
			}

	}


	
	
		public void rate()
		{
			try 
				{
				
					String rno=(String) cbrno.getSelectedItem();
					String sql="Select * from room where R_NUMBER=?";
				 	Connect();
				 	pst=con.prepareStatement(sql);
				 	pst.setString(1,rno);
				 	rs=pst.executeQuery();
				 	String rate=rs.getString(3);
				 	txtrate.setText(rate);
				 	
				}
					catch (Exception e)
						{
							// TODO: handle exception
							System.out.println(e);
						}
			
						
			
		}

		
		//disount
		public void discount()
		{
				
			String sql="select * from discount WHERE status = 'ACTIVE'";
			try 
			{
				Connect();
				pst=con.prepareStatement(sql);
				rs=pst.executeQuery();

				while(rs.next())
				{
					String name=rs.getString("type");
					cbdtype.addItem(name);
				}
				
			} 	
				catch (Exception e)
				{
					// TODO: handle exception
					System.out.println(e);
				}
			
			cbdtype.addItemListener(new ItemListener() {
			
				
				
					public void itemStateChanged(ItemEvent e) 
					{
						 try {  
							 
							   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
							   java.util.Date firstDate = dcfrom.getDate();
							   java.util.Date secondDate = dcto.getDate();
							   long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
							   long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
							   System.out.print("Day :"+ diff);

								int rate = Integer.parseInt(txtrate.getText().toString());
						    
								String type=(String) cbdtype.getSelectedItem();
								pst=(PreparedStatement) con.prepareStatement("select * from discount where type=?");
								pst.setString(1,type );
								ResultSet rs=pst.executeQuery();
								
								try 	
								{
									  if(rs.next()==true)
									  {
										  int discount=Integer.parseInt(rs.getString(3).toString());
										  System.out.println("Discount : "+discount);
										  
										  long  amount=diff* rate;
										  txtamount.setText(String.valueOf(amount));
										  
										  long dis=(amount*discount)/100;
										  long total=amount-dis;
										  txttotal.setText(String.valueOf(total));
										 
										  
									  }
									
								}
									catch (Exception e2) 
										{
												// TODO: handle exception
												System.out.println(e2);
										}
							   	
							  }
						 		catch (Exception e1) 
						 			{
						 				System.out.println(e1);
						 			}

					
					}
				});
		
			cbdtype.setSelectedItem(null);
		
		}	
		
		//constructor  
		public reservation()
	{
		Connect();
		initialize();
		discount();
		RoomNo();

	}
	
	
	
	public void initialize() 
	{

		textField.setColumns(10);
		setBounds(100, 100, 1072, 751);
		
		main = new JPanel();
		main.setBackground(new Color(153, 255, 255));
		main.setBorder(new EmptyBorder(5, 5, 5, 5)); 

		setContentPane(main);
		main.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 10, 1038, 690);
		main.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 1008, 50);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RESERVATION FORM");
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 30));
		lblNewLabel.setBounds(379, 10, 314, 40);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 70, 1008, 153);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(36, 10, 144, 30);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID Type");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setBounds(36, 61, 144, 30);
		panel_2.add(lblNewLabel_2);
		
		final JComboBox cbid = new JComboBox();
		cbid.setBounds(227, 61, 200, 30);
		cbid.addItem("Aadhar Card");
		cbid.addItem("Voter ID");
		cbid.addItem("Passport");
		cbid.setSelectedItem(null);
		panel_2.add(cbid);

		JLabel Gender = new JLabel("Gender");
		Gender.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Gender.setBounds(36, 109, 144, 30);
		panel_2.add(Gender);
		
		final JComboBox cbgender = new JComboBox();
		cbgender.setBounds(227, 113, 200, 30);
		cbgender.addItem("Male");
		cbgender.addItem("Female");
		cbgender.setSelectedItem(null);

		panel_2.add(cbgender);
		
		JLabel lblNewLabel_4 = new JLabel("Phone No.");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_4.setBounds(552, 15, 144, 30);
		panel_2.add(lblNewLabel_4);
		
		txtname = new JTextField();
		txtname.setBounds(227, 10, 200, 30);
		panel_2.add(txtname);
		txtname.setColumns(10);
		
		txtphoneno = new JTextField();
		txtphoneno.setBounds(767, 15, 200, 30);
		panel_2.add(txtphoneno);
		txtphoneno.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("ID Number");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_5.setBounds(552, 71, 144, 30);
		panel_2.add(lblNewLabel_5);
		
		txtidno = new JTextField();
		txtidno.setBounds(767, 71, 200, 30);
		panel_2.add(txtidno);
		txtidno.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(10, 233, 1008, 50);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Adult");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_3.setBounds(36, 10, 144, 30);
		panel_3.add(lblNewLabel_3);
		
		txtadult = new JTextField();
		txtadult.setBounds(227, 10, 200, 30);
		panel_3.add(txtadult);
		txtadult.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Child");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_6.setBounds(552, 10, 144, 30);
		panel_3.add(lblNewLabel_6);
		
		txtchild = new JTextField();
		txtchild.setBounds(767, 7, 200, 30);
		panel_3.add(txtchild);
		txtchild.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBounds(10, 293, 1008, 153);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Room Type");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_7.setBounds(36, 10, 144, 30);
		panel_4.add(lblNewLabel_7);
				
		JLabel lblNewLabel_8 = new JLabel("Room NO.");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_8.setBounds(552, 10, 144, 30);
		panel_4.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Rate");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_9.setBounds(36, 50, 144, 30);
		panel_4.add(lblNewLabel_9);
		
		txtrate = new JTextField();
		txtrate.setBounds(227, 53, 200, 30);
		panel_4.add(txtrate);
		txtrate.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("No Of Bed");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_10.setBounds(552, 53, 144, 30);
		panel_4.add(lblNewLabel_10);
		
		txtbed = new JTextField();
		txtbed.setBounds(768, 55, 200, 30);
		panel_4.add(txtbed);
		txtbed.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("From");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_11.setBounds(36, 99, 144, 30);
		panel_4.add(lblNewLabel_11);
	
		JLabel lblNewLabel_12 = new JLabel("To");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_12.setBounds(552, 99, 144, 30);
		panel_4.add(lblNewLabel_12);
		
		//date chooser checkout
		dcto.setBounds(767, 99, 200, 30);
		panel_4.add(dcto);
		
		
		 cbrno.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) 
		 	{
		 		try 
				{
					
		 			  txtrate.setText(null);
					  txtbed.setText(null);
					  
					  
					String rno=(String) cbrno.getSelectedItem();
					pst=(PreparedStatement) con.prepareStatement("select * from room where R_NUMBER=?");
					pst.setString(1,rno );
					ResultSet rs=pst.executeQuery();
					
					  if(rs.next()==true)
						  {
						  
							
							  String rate=rs.getString(4);
							  String No_bed=rs.getString(5);
							  
							  txtrate.setText(rate);
							  txtbed.setText(No_bed);
							  
						  }
						
				}
					catch (Exception e2)
						{
							// TODO: handle exception
							System.out.println(e2);
						}
		
		 	}
		 });
		
		  cbrno.setSelectedItem(null);
		  txtrate.setText(" ");
		  txtbed.setText(" ");
		

		cbrno.setBounds(767, 6, 200, 30);
		panel_4.add(cbrno);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.setBounds(10, 456, 1008, 147);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_13 = new JLabel("Discount Type");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_13.setBounds(36, 10, 193, 30);		
		panel_5.add(lblNewLabel_13);
				
		JLabel lblNewLabel_14 = new JLabel("Sub Total");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_14.setBounds(36, 58, 144, 30);
		panel_5.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Advance");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_15.setBounds(552, 58, 144, 30);
		panel_5.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Pending Amount");
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_16.setBounds(36, 98, 181, 30);
		panel_5.add(lblNewLabel_16);
		
		cbdtype.setBounds(227, 6, 200, 30);
		panel_5.add(cbdtype);
		
		final JComboBox cbrtype = new JComboBox();	
		cbrtype.setBounds(227, 6, 200, 30);
		cbrtype.addItem("NON AC");
		cbrtype.addItem("AC");
		cbrtype.addItem("VIP");
		cbrtype.addItem("DELUXE");
		cbrtype.setSelectedItem(null);
		panel_4.add(cbrtype);
		
		//date chooser checkin	
		dcfrom.setBounds(227, 99, 200, 30);
		panel_4.add(dcfrom);

		txtadvance = new JTextField();
		txtadvance.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				  int advance=Integer.parseInt(txtadvance.getText().toString());
				  int total = Integer.parseInt(txttotal.getText().toString());
				  long pending=total-advance;
				  if(pending>0)
					  {
					  		txtpending.setText(String.valueOf(pending));
					  }
				  	else
				  			{
				  				txtpending.setText("0");

				  			}
					 
				  long balance=advance-total;
				  if(balance>0)
					  {
					  		txtbalance.setText(String.valueOf(balance));
					  }
				  else
				  		{
					  		txtbalance.setText("0");

				  		}
			}
		});
		txtadvance.setBounds(767, 58, 200, 30);
		panel_5.add(txtadvance);
		txtadvance.setColumns(10);
		
		txttotal = new JTextField();
		txttotal.setBounds(227, 63, 200, 30);
		panel_5.add(txttotal);
		txttotal.setColumns(10);
		
		txtbalance = new JTextField();
		txtbalance.setBounds(767, 103, 200, 30);
		panel_5.add(txtbalance);
		txtbalance.setColumns(10);
		
		JLabel lblNewLabel_15_1 = new JLabel("Balance");
		lblNewLabel_15_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_15_1.setBounds(552, 103, 144, 30);
		panel_5.add(lblNewLabel_15_1);
		
		txtpending = new JTextField();
		txtpending.setColumns(10);
		txtpending.setBounds(227, 103, 200, 30);
		panel_5.add(txtpending);
		
		JLabel lblNewLabel_15_2 = new JLabel("Actual Amount");
		lblNewLabel_15_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_15_2.setBounds(552, 10, 176, 30);
		panel_5.add(lblNewLabel_15_2);
		
		txtamount = new JTextField();
		txtamount.setColumns(10);
		txtamount.setBounds(767, 10, 200, 30);
		panel_5.add(txtamount);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6.setBounds(10, 613, 1008, 50);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JButton btnok = new JButton("RESERVE");
		btnok.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{	
				String Name=txtname.getText();
				String Phone=txtphoneno.getText();
				String type=(String) cbid.getSelectedItem();
				String id_no=txtidno.getText();
				String gender=(String) cbgender.getSelectedItem();
				
				String adult=txtadult.getText();
				String child=txtchild.getText();
				
				String room_type=(String) cbrtype.getSelectedItem();
				String rno=(String) cbrno.getSelectedItem();
				String rrate=txtrate.getText();
				String bed=txtbed.getText();
				

				SimpleDateFormat date_form = new SimpleDateFormat("dd-MM-yyyy");
				String from = date_form.format(dcfrom.getDate()); 
				String to = date_form.format(dcto.getDate()); 

				String dtype=(String) cbdtype.getSelectedItem();
				String amount=txtamount.getText();
				String total=txttotal.getText();
				String advance=txtadvance.getText();
				String pending=txtpending.getText();
				String balance=txtbalance.getText();
					
				Connect();
				try 
				 {
					
//					SELECT `id`, `name`, `phoneno`, `idtype`, `idno`, `gender`, `adult`, `child`,
//					`roomtype`, `roomno`, `rate`, `Bed`, `from`, `to`,
//					`dtype`, `amount`, `total`, `advance`, `pending`, `balance` FROM `reservation` WHERE 1
//					
					pst=(PreparedStatement)con.prepareStatement("insert into reservation(`name`, `phoneno`, `idtype`, `idno`, `gender`, `adult`, `child`, `roomtype`, `roomno`, `rate`, `Bed`, `from`, `to`, `dtype`, `amount`, `total`, `advance`, `pending`, `balance`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");		
				    // 	pst=(PreparedStatement)con.prepareStatement("insert into reservation(name, phoneno, idtype, idno, gender, adult,child, roomtype, roomno, rate,Bed,from,to,dtype, amount,total, advance,pending, balance) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");		
					 
					pst.setString(1,Name);
					pst.setString(2, Phone);
					pst.setString(3, type);
					pst.setString(4,id_no);
					pst.setString(5, gender);
					pst.setString(6, adult);
					pst.setString(7,child);
					pst.setString(8, room_type);
					pst.setString(9, rno);
					pst.setString(10,rrate);
					pst.setString(11, bed);
					pst.setString(12, from);
					pst.setString(13, to);
					pst.setString(14,dtype);
					pst.setString(15,amount);
					pst.setString(16, total);
					pst.setString(17,advance);
					pst.setString(18,pending);
					pst.setString(19,balance);
		



					pst.executeUpdate();			

					JOptionPane.showMessageDialog(null,"Room reserved");
	

		 			  
		 			  
		 			    pst=(PreparedStatement) con.prepareStatement("update room  set STATUS='Reserved' where R_NUMBER=?");
						pst.setString(1,rno );
						pst.executeUpdate();
						
						
						cbrno.setSelectedItem(null);						
						  txtname.setText(null);
			 			  txtphoneno.setText(null);
			 			  txtidno.setText(null);
			 			  txtadult.setText(null);
			 			  txtchild.setText(null);
			 			  txtrate.setText(null);
			 			  txtbed.setText(null);
			 			  txtamount.setText(null);
			 			  txttotal.setText(null);
			 			  txtadvance.setText(null);
			 			  txtpending.setText(null);
			 			  txtbalance.setText(null);
			 			 
			 			  cbid.setSelectedItem(null);
			 			  cbgender.setSelectedItem(null);
			 			  cbrtype.setSelectedItem(null);
			 			  cbdtype.setSelectedItem(null);

			 			  dcfrom.setDate(null);
			 			  dcto.setDate(null);
			 			  
			 			  


				 } 
				
				catch (Exception e2)
				 {
				
					System.out.println(e2);
				 }			
			}
		});
		btnok.setBounds(138, 10, 171, 30);
		panel_6.add(btnok);
		
		JButton btncancel = new JButton("CANCEL");
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			//	dashboard obj=new dashboard();
			//	obj.main(null);		
			}
		});
		btncancel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btncancel.setBounds(419, 10, 150, 30);
		panel_6.add(btncancel);
		
		JButton btnreset = new JButton("RESET");
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

	 			  txtname.setText(null);
	 			  txtphoneno.setText(null);
	 			  txtidno.setText(null);
	 			  txtadult.setText(null);
	 			  txtchild.setText(null);
	 			  txtrate.setText(null);
	 			  txtbed.setText(null);
	 			  txtamount.setText(null);
	 			  txttotal.setText(null);
	 			  txtadvance.setText(null);
	 			  txtpending.setText(null);
	 			  txtbalance.setText(null);
	 			 
	 			  cbid.setSelectedItem(null);
	 			  cbgender.setSelectedItem(null);
	 			  cbrtype.setSelectedItem(null);
	 			  cbrno.setSelectedItem(null);
	 			  cbdtype.setSelectedItem(null);

	 			  dcfrom.setDate(null);
	 			  dcto.setDate(null);
			}
		});
		btnreset.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnreset.setBounds(665, 10, 150, 30);
		panel_6.add(btnreset);
	}
}
