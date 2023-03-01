package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
 

public class discount extends JFrame 
{
	private JPanel contentPane;
	private JTextField txtrate;
	private JTable table;
	private JTextField txttype;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					discount frame = new discount();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


// database connection  
	Connection con;
	PreparedStatement pat;
	ResultSet rs;
	private JTextField txtsearch;
	final JComboBox cbstatus = new JComboBox();
	
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
// database connection close
	

//load table function	
	public void load_table()
	{
		try 
		{
			String query="select * from discount ";
			pat=con.prepareStatement(query);
			rs=pat.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			
		}
		  catch (Exception e2) 
			{
			// TODO: handle exception
			}
	}
//load table function end
	
//constructor	
	public discount() 
	{	
		Connect();
		initialize();
		load_table();
	}
	
	
//	initialize function for initialize components
	public void initialize()
	{
		setBounds(100, 100, 1072, 521);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(20, 85, 1028, 369);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnsave = new JButton("Save");
		btnsave.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
// After click on save button
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				String type,rate,status;
				type=txttype.getText();
				rate=txtrate.getText();
				status=(String) cbstatus.getSelectedItem();
				
				try 
				 {
						pat=(PreparedStatement) con.prepareStatement("insert into discount(type,rate,status) values (?,?,?)");
						pat.setString(1, type);
						pat.setString(2, rate);
						pat.setString(3, status);
						
						pat.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Discount Added..");
						load_table();
						
						txttype.setText("");
						txtrate.setText("");
						cbstatus.setSelectedItem(null);
						txtsearch.setText("");
				 }
					catch (Exception e2)
						{
							// TODO: handle exception
							System.out.println(e2);
						}
				
				
			}
		});
//end of save operation
		btnsave.setBounds(8, 314, 150, 30);
		panel_1.add(btnsave);
		
		JButton btncancel = new JButton("Cancel");
//After cancel button click 
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);

			}
		});
//end of cancel operation
		btncancel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btncancel.setBounds(770, 314, 150, 30);
		panel_1.add(btncancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(541, 77, 477, 197);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		
		JButton btnupdate = new JButton("Update");
		btnupdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
// For update the discount 
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// update
				String type,rate,status,id;
				type=txttype.getText();
				rate=txtrate.getText();
				id=txtsearch.getText();
				status=(String) cbstatus.getSelectedItem();
				
				try 
				 {
//					UPDATE `discount` SET `id`='[value-1]',`type`='[value-2]',`rate`='[value-3]',`status`='[value-4]' WHERE 1
						pat=(PreparedStatement) con.prepareStatement("update discount set type=?,rate=?,status=? where id=?");
						pat.setString(1, type);
						pat.setString(2, rate);
						pat.setString(3, status);
						pat.setString(4, id);
						pat.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Discount Updated..");
						load_table();
						
						txttype.setText("");
						txtrate.setText("");
						cbstatus.setSelectedItem(null);
						txtsearch.setText("");

				 }
					catch (Exception e2)
						{
							// TODO: handle exception
							System.out.println(e2);
						}
			}
		});
		btnupdate.setBounds(260, 314, 150, 30);
		panel_1.add(btnupdate);
		
		JButton btndelete = new JButton("Delete");
		btndelete.setFont(new Font("Times New Roman", Font.BOLD, 20));

//for delete the discount
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				 String id=txtsearch.getText();
				 System.out.println(id);

			   try 
			   {
				   //DELETE FROM `discount` WHERE 0
				 pat=(PreparedStatement) con.prepareStatement("delete from discount where id=?");
				 pat.setString(1,id);
				 
				 pat.executeUpdate();
				 
					
				 JOptionPane.showMessageDialog(null,"Discount Type Deleted..");
				 load_table();
				 
					txttype.setText("");
					txtrate.setText("");
					cbstatus.setSelectedItem(null);
					txtsearch.setText("");
			   }
			   	catch (Exception e2) 
			   		{
			   			// TODO: handle exception
			   			e2.printStackTrace();
			   		}
			}
		});
		btndelete.setBounds(500, 314, 150, 30);
		panel_1.add(btndelete);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(10, 10, 1008, 57);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Serach ID");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_4.setBounds(153, 17, 144, 30);
		panel.add(lblNewLabel_4);
		
		txtsearch = new JTextField();
		txtsearch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtsearch.setBounds(595, 17, 200, 30);
		panel.add(txtsearch);
		txtsearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String id=txtsearch.getText();
				//SELECT `id`, `type`, `rate`, `status` FROM `discount` WHERE 1

				try {
					pat=(PreparedStatement) con.prepareStatement("select * from  discount where id=?");
					pat.setString(1,id);
					rs=pat.executeQuery();	
					
					if(rs.next()==true)
					{
						String dtype=rs.getString(2);
						String rate=rs.getString(3);
						String status=rs.getString(4);
												
						txttype.setText(dtype);
						txtrate.setText(rate);
						cbstatus.setSelectedItem(status);

					}
				} 
				
					catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		txtsearch.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(20, 77, 500, 192);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Discount Type");
		lblNewLabel_1.setBounds(10, 10, 209, 30);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		txttype = new JTextField();
		txttype.setBounds(272, 15, 200, 30);
		panel_2.add(txttype);
		txttype.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Discount Rate(%)");
		lblNewLabel_2.setBounds(10, 71, 209, 30);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		txtrate = new JTextField();
		txtrate.setBounds(272, 67, 200, 30);
		panel_2.add(txtrate);
		txtrate.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(10, 130, 144, 30);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
	
		cbstatus.setBounds(272, 130, 200, 30);
		panel_2.add(cbstatus);
		cbstatus.addItem("ACTIVE");
		cbstatus.addItem("INACTIVE");
		cbstatus.setSelectedItem(null);
		
		JLabel lblNewLabel = new JLabel("Manage Discount");
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 34));
		lblNewLabel.setBounds(340, 21, 346, 54);
		contentPane.add(lblNewLabel);
	}
}
