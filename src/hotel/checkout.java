package hotel;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.toedter.calendar.JDateChooser;

import java.awt.Component;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class checkout extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtphoneno;
	private JTextField txtidno;
	private JTextField txtroomno;
	private JTextField txtroomype;
	private JTextField txtrate;
	private JTextField txtbed;
	private JTextField txtadult;
	private JTextField txtchild;
	private JTextField txtcheckin;
	private JTextField txtcheckout;
	private JTextField txtrnosearch;
	private JTextField txtidtype;
	private JTextField txtgender;
	private JTextField txtadvance;
	private JTextField txttotal;
	private JTextField txtbalance;
	private JTextField txtpending;
	private JTextField txtamount;
	private JTextField txtcheckintime;
	private JTextField txtpay;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkout frame = new checkout();
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
		final JDateChooser dccheckout = new JDateChooser();
		final JDateChooser dccheckin = new JDateChooser();
		private JTextField txtdiscount;
		private JTextField txtcheckouttime;



//connect
		public void Connect()
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost/hms","root","");
				//System.out.println("success");
				
			}
			catch(ClassNotFoundException ex)
			{
				
			}
			catch(SQLException ex)
			{
				System.out.println(ex);
			}
		}
		

//constructor		
	public checkout()
	{
		init();
		Connect();
	}
	
	public void  init() {
		setBounds(100, 100, 1337, 786);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		panel_1.setBounds(20, 54, 1038, 753);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 69, 1018, 146);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(36, 10, 144, 30);
		panel_2.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtname.setBounds(237, 10, 200, 30);
		panel_2.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Phone No");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_5.setBounds(552, 13, 144, 30);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("ID Type");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_6.setBounds(36, 55, 144, 30);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("ID NO");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_7.setBounds(552, 55, 144, 30);
		panel_2.add(lblNewLabel_7);
		
		txtphoneno = new JTextField();
		txtphoneno.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtphoneno.setBounds(767, 20, 200, 30);
		panel_2.add(txtphoneno);
		txtphoneno.setColumns(10);
		
		txtidno = new JTextField();
		txtidno.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtidno.setBounds(767, 60, 200, 30);
		panel_2.add(txtidno);
		txtidno.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Gender ");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_9.setBounds(36, 100, 144, 30);
		panel_2.add(lblNewLabel_9);
		
		txtidtype = new JTextField();
		txtidtype.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtidtype.setColumns(10);
		txtidtype.setBounds(237, 57, 200, 30);
		panel_2.add(txtidtype);
		
		txtgender = new JTextField();
		txtgender.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtgender.setColumns(10);
		txtgender.setBounds(237, 102, 200, 30);
		panel_2.add(txtgender);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel_1, txtname, lblNewLabel_5, lblNewLabel_6, lblNewLabel_7, txtphoneno, txtidno, lblNewLabel_9}));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(10, 276, 1018, 189);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Room No");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setBounds(36, 10, 144, 30);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Type");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_3.setBounds(36, 50, 144, 30);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Rate");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_4.setBounds(552, 10, 144, 30);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_8 = new JLabel("NO of Bed");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_8.setBounds(552, 50, 144, 30);
		panel_3.add(lblNewLabel_8);
		
		txtroomno = new JTextField();
		txtroomno.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtroomno.setBounds(237, 15, 200, 30);
		panel_3.add(txtroomno);
		txtroomno.setColumns(10);
		
		txtroomype = new JTextField();
		txtroomype.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtroomype.setBounds(237, 55, 200, 30);
		panel_3.add(txtroomype);
		txtroomype.setColumns(10);
		
		txtrate = new JTextField();
		txtrate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtrate.setBounds(767, 10, 200, 30);
		panel_3.add(txtrate);
		txtrate.setColumns(10);
		
		txtbed = new JTextField();
		txtbed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtbed.setBounds(767, 55, 200, 30);
		panel_3.add(txtbed);
		txtbed.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Check In Date");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_14.setBounds(36, 98, 178, 30);
		panel_3.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Check Out Dte");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_15.setBounds(36, 138, 178, 30);
		panel_3.add(lblNewLabel_15);
		
		final JFormattedTextField txtcheckin = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		txtcheckin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtcheckin.setBounds(237, 100, 200, 30);
		panel_3.add(txtcheckin);
		txtcheckin.setColumns(10);

		txtcheckout = new JTextField();
		txtcheckout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtcheckout.setBounds(237, 140, 200, 30);
		panel_3.add(txtcheckout);
		txtcheckout.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("CheckOut Time ");
		lblNewLabel_20.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_20.setBounds(552, 154, 200, 30);
		panel_3.add(lblNewLabel_20);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();		
		System.out.println(dateFormat.format(cal.getTime()));
		
		JLabel lblNewLabel_15_3 = new JLabel("CheckIn Time");
		lblNewLabel_15_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_15_3.setBounds(552, 98, 178, 30);
		panel_3.add(lblNewLabel_15_3);
		
		txtcheckintime = new JTextField();
		txtcheckintime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtcheckintime.setColumns(10);
		txtcheckintime.setBounds(767, 100, 200, 30);
		panel_3.add(txtcheckintime);
		
		txtcheckouttime = new JTextField();
		txtcheckouttime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtcheckouttime.setColumns(10);
		txtcheckouttime.setBounds(767, 148, 200, 30);
		
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();
		txtcheckouttime.setText(dtf.format(now));
		panel_3.add(txtcheckouttime);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBounds(10, 225, 1018, 45);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Adult");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_10.setBounds(36, 5, 144, 30);
		panel_4.add(lblNewLabel_10);
		
		txtadult = new JTextField();
		txtadult.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtadult.setBounds(237, 7, 200, 30);
		panel_4.add(txtadult);
		txtadult.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Child");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_11.setBounds(552, 5, 144, 30);
		panel_4.add(lblNewLabel_11);
		
		txtchild = new JTextField();
		txtchild.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtchild.setBounds(767, 7, 200, 30);
		panel_4.add(txtchild);
		txtchild.setColumns(10);
		
		JButton btncheckout = new JButton("CHECK OUT");
		btncheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
//				SELECT `id`, `name`, `phoneno`, `idtype`, `idno`, `gender`, `adult`, `child`, 
//				`roomno`, `rate`, `roomtype`, `occupancy`, `cindate`, `cintime`, `countdate`, `couttime`, 
//				`dtype`, `amount`, `total`, `advance`, `pending`, `balance` FROM `checkout` WHERE 1

				String Name=txtname.getText();
				String Phone=txtphoneno.getText();
				String type=txtidtype.getText();
				String idno=txtidno.getText();
				String gender=txtgender.getText();
				
				String adult=txtadult.getText();
				String child=txtchild.getText();
				
				String room_type=txtroomype.getText();
				String rno=txtroomno.getText();
				String rrate=txtrate.getText();
				String bed=txtbed.getText();
				
				String cindate=txtcheckin.getText();
				String cintime=txtcheckintime.getText();
				String coutdate=txtcheckout.getText();
				String couttime=txtcheckouttime.getText();

				String dtype=txtdiscount.getText();
				String amount=txtamount.getText();
				String total=txttotal.getText();
				String advance=txtadvance.getText();
				String pending=txtpending.getText();
				String balance=txtbalance.getText();
						
				try 
				 {
//					SELECT `id`, `name`, `phoneno`, `idtype`, `idno`, `gender`, `adult`, `child`,
//					`roomno`, `rate`, `roomtype`, `bed`, `cindate`, `cintime`, `countdate`, `couttime`, 
//					`dtype`, `amount`, `total`, `advance`, `pending`, `balance` FROM `checkout` WHERE 1

					pst=(PreparedStatement)con.prepareStatement("insert into checkout(name, phoneno, idtype, idno, gender, adult,child,roomno,rate, roomtype, bed,cindate, cintime, countdate, couttime,dtype, amount, total, advance, pending, balance) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");		
					
					pst.setString(1,Name);
					pst.setString(2, Phone);
					pst.setString(3, type);
					pst.setString(4,idno);
					pst.setString(5, gender);
					pst.setString(6, adult);
					pst.setString(7,child);
					pst.setString(8, rno);
					pst.setString(9, rrate);
					pst.setString(10,room_type);
					pst.setString(11, bed);
					pst.setString(12, cindate);
					pst.setString(13, cintime);
					pst.setString(14, coutdate);
					pst.setString(15, couttime);
					pst.setString(16,dtype);
					pst.setString(17,amount);
					pst.setString(18, total);
					pst.setString(19,advance);
					pst.setString(20,pending);
					pst.setString(21,balance);
		
					pst.executeUpdate();			
						
					JOptionPane.showMessageDialog(null,"Successfully Checked Out");
				
					  txtname.setText(null);
		 			  txtphoneno.setText(null);
		 			  txtidtype.setText(null);
		 			  txtidno.setText(null);
		 			  txtgender.setText(null);
		 			  
		 			  txtadult.setText(null);
		 			  txtchild.setText(null);
		 			  
		 			  txtroomype.setText(null);
		 			  txtroomno.setText(null);
		 			  txtrate.setText(null);
		 			  txtbed.setText(null);
		 			  
		 			  txtcheckin.setText(null);
		 			  txtcheckintime.setText(null);
		 			  txtcheckout.setText(null);
		 			  txtcheckouttime.setText(null);
		 			  
		 			  txtdiscount.setText(null);
		 			  txtamount.setText(null);
		 			  txttotal.setText(null);
		 			  txtadvance.setText(null);
		 			  txtpending.setText(null);
		 			  txtbalance.setText(null);
		 			  
					  txtrnosearch.setText(null);
				 			  
		 			    pst=(PreparedStatement) con.prepareStatement("update room  set STATUS='Dirty' where R_NUMBER=?");
						pst.setString(1,rno );
						pst.executeUpdate();
			
				 }
						catch (Exception e2)
						 {
						
							e2.printStackTrace();
						 }			
					}
				});
		btncheckout.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btncheckout.setBounds(125, 653, 200, 35);
		panel_1.add(btncheckout);
		
		JButton btncancel = new JButton("CANCEL");
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btncancel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btncancel.setBounds(735, 653, 200, 35);
		panel_1.add(btncancel);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6.setBounds(10, 12, 1018, 51);
		panel_1.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_19 = new JLabel("Rom NO.");
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_19.setBounds(36, 10, 144, 30);
		panel_6.add(lblNewLabel_19);
		
		txtrnosearch = new JTextField();
		txtrnosearch.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtrnosearch.setBounds(237, 10, 200, 30);
		panel_6.add(txtrnosearch);
		txtrnosearch.setColumns(10);
		
		JButton btnsearch = new JButton("Search");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
//					SELECT `id`, `name`, `phoneno`, `idtype`, `idno`, `gender`, `adult`, `child`, 
//					`roomtype`, `roomno`, `rate`, `occupancy`, `checkin`, `checkintime`, `checkout`,
//					`dtype`, `amount`, `total`, `advance`, `pending`, `balance` FROM `checkin` WHERE 1
//					
					
					String no=txtrnosearch.getText();
					pst=(PreparedStatement) con.prepareStatement("select * from checkin where roomno=?");
					pst.setString(1,no );
					
					ResultSet rs=pst.executeQuery();
					
					  if(rs.next()==true)
					  {
	
							String Name=rs.getString(2);
							String Phone=rs.getString(3);
							String type=rs.getString(4);
							String id_no=rs.getString(5);
							String gender=rs.getString(6);
							
							String adult=rs.getString(7);
							String child=rs.getString(8);
							
							String room_type=rs.getString(9);
							String rno=rs.getString(10);
							String rrate=rs.getString(11);
							String bed=rs.getString(12);
							

							String checkin = rs.getString(13);
							String checkintime =rs.getString(14);
							String checkout =rs.getString(15);

							String dtype=rs.getString(16);
							String amount=rs.getString(17);
							String total=rs.getString(18);
							String advance=rs.getString(19);
							String pending=rs.getString(20);
							String balance=rs.getString(21);						  
						  
						  
						  
						  txtname.setText(Name);
						  txtphoneno.setText(Phone);
						  txtidtype.setText(type);
						  txtidno.setText(id_no);
						  txtgender.setText(gender);
						  
						  txtadult.setText(adult);
						  txtchild.setText(child);
						  
						  txtroomype.setText(room_type);
						  txtroomno.setText(rno);
						  txtrate.setText(rrate);
						  txtbed.setText(bed);
						  
						  txtcheckin.setText(checkin);
						  txtcheckintime.setText(checkintime);
						  txtcheckout.setText(checkout);
						  
						  txtdiscount.setText(dtype);
						  txtamount.setText(amount);
						  txttotal.setText(total);
						  txtadvance.setText(advance);
						  txtpending.setText(pending);
						  txtbalance.setText(balance);
						  
					  }
					  else 
					  {
							JOptionPane.showMessageDialog(null,"Room not found..");
						  
					  }
				
				} catch (Exception e2) 
				{
					// TODO: handle exception
					System.out.println(e2);
				}
					
			}
		});
		btnsearch.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnsearch.setBounds(552, 15, 144, 30);
		panel_6.add(btnsearch);
		panel_6.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtrnosearch, lblNewLabel_19, btnsearch}));
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.setBounds(10, 486, 1008, 146);
		panel_1.add(panel_5);
		
		JLabel lblNewLabel_13 = new JLabel("Discount Type");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_13.setBounds(36, 10, 193, 30);
		panel_5.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14_1 = new JLabel("Total");
		lblNewLabel_14_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_14_1.setBounds(36, 58, 144, 30);
		panel_5.add(lblNewLabel_14_1);
		
		JLabel lblNewLabel_15_1 = new JLabel("Advance");
		lblNewLabel_15_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_15_1.setBounds(552, 58, 144, 30);
		panel_5.add(lblNewLabel_15_1);
		
		JLabel lblNewLabel_16 = new JLabel("Pending Amount");
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_16.setBounds(36, 103, 181, 30);
		panel_5.add(lblNewLabel_16);
		
		txtadvance = new JTextField();
		txtadvance.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtadvance.setColumns(10);
		txtadvance.setBounds(767, 58, 200, 30);
		panel_5.add(txtadvance);
		
		txttotal = new JTextField();
		txttotal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txttotal.setColumns(10);
		txttotal.setBounds(237, 63, 200, 30);
		panel_5.add(txttotal);
		
		txtbalance = new JTextField();
		txtbalance.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtbalance.setColumns(10);
		txtbalance.setBounds(767, 103, 200, 30);
		panel_5.add(txtbalance);
		
		JLabel lblNewLabel_15_1_1 = new JLabel("Balance");
		lblNewLabel_15_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_15_1_1.setBounds(552, 103, 144, 30);
		panel_5.add(lblNewLabel_15_1_1);
		
		txtpending = new JTextField();
		txtpending.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtpending.setColumns(10);
		txtpending.setBounds(237, 103, 200, 30);
		panel_5.add(txtpending);
		
		JLabel lblNewLabel_15_2 = new JLabel("Actual Amount");
		lblNewLabel_15_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_15_2.setBounds(552, 10, 176, 30);
		panel_5.add(lblNewLabel_15_2);
		
		txtamount = new JTextField();
		txtamount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtamount.setColumns(10);
		txtamount.setBounds(767, 10, 200, 30);
		panel_5.add(txtamount);
		
		txtdiscount = new JTextField();
		txtdiscount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtdiscount.setColumns(10);
		txtdiscount.setBounds(237, 10, 200, 30);
		panel_5.add(txtdiscount);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				  txtrnosearch.setText(null);
				
				  txtname.setText(null);
				  txtphoneno.setText(null);
				  txtidtype.setText(null);
				  txtidno.setText(null);
				  txtgender.setText(null);
				  
				  txtadult.setText(null);
				  txtchild.setText(null);
				  
				  txtroomype.setText(null);
				  txtroomno.setText(null);
				  txtrate.setText(null);
				  txtbed.setText(null);
				  
				  txtcheckin.setText(null);
				  txtcheckintime.setText(null);
				  txtcheckout.setText(null);
				  
				  txtdiscount.setText(null);
				  txtamount.setText(null);
				  txttotal.setText(null);
				  txtadvance.setText(null);
				  txtpending.setText(null);
				  txtbalance.setText(null);
				  
				  txtpay.setText(null);
				  
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnReset.setBounds(430, 653, 200, 35);
		panel_1.add(btnReset);
		
		JLabel lblNewLabel = new JLabel("Check Out Form");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(391, 10, 334, 47);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 30));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Pending Amount", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(1068, 54, 245, 319);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_19_1 = new JLabel("Pay");
		lblNewLabel_19_1.setBounds(20, 37, 144, 30);
		panel.add(lblNewLabel_19_1);
		lblNewLabel_19_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		txtpay = new JTextField();
		txtpay.setBounds(20, 91, 200, 30);
		panel.add(txtpay);
		txtpay.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtpay.setColumns(10);
		
		JButton btnpay = new JButton("PAY");
		btnpay.setBounds(20, 157, 144, 30);
		panel.add(btnpay);
		btnpay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int pay=Integer.parseInt(txtpay.getText().toString());
				int pending=Integer.parseInt(txtpending.getText().toString());
				int total=Integer.parseInt(txttotal.getText().toString());

				if(pending>0)
				{
					int pending_amount=pending-pay;
					txtpending.setText(String.valueOf(pending_amount));
					
				}
				
				
		     	if(pay>pending)
		     	{
		     		int balance=pay-pending;
		     		txtbalance.setText(String.valueOf(balance));
		     		txtpending.setText("0");
		     	}
				
	     	}
		});
		btnpay.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		
	}
}
