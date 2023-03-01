package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class dashboard extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard frame = new dashboard();
					frame.setVisible(true);
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	

	public dashboard() 
	{
	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Dashboard");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 191, 255));
		menuBar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.setBackground(new Color(255, 255, 153));
		menuBar.setBounds(10, 78, (int) dim.getWidth(), 42);
		contentPane.add(menuBar);
		
		JMenu HOME = new JMenu("HOME");
		HOME.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dashboard.main(null);
			}
		});
		HOME.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(HOME);
		
		JMenu FILE = new JMenu("FILE");
		FILE.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(FILE);
		
		JMenuItem checkin = new JMenuItem("New Check In");
		checkin.setBackground(Color.WHITE);
		checkin.setForeground(new Color(0, 0, 0));
		checkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				checkin obj=new checkin();
				obj.main(null);
				//frame.setVisible(true);
			}
		});
		checkin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		FILE.add(checkin);
		
		final JMenuItem reservation = new JMenuItem("New Reservation");
		reservation.setBackground(Color.WHITE);
		reservation.setForeground(new Color(0, 0, 0));
		reservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				reservation obj=new reservation();
				obj.main(null);
			}
		});
		reservation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		FILE.add(reservation);

		
		JMenuItem checkout = new JMenuItem("Check Out");
		checkout.setBackground(Color.WHITE);
		checkout.setForeground(new Color(0, 0, 0));
		checkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				checkout obj=new checkout();
				obj.main(null);
			}
		});
		
		JMenuItem reservecheckin = new JMenuItem("ReserveCheckIn");
		reservecheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				reserveCheckin obj=new reserveCheckin();
				obj.main(null);
			}
		});
		reservecheckin.setBackground(Color.WHITE);
		reservecheckin.setForeground(new Color(0, 0, 0));
		reservecheckin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		FILE.add(reservecheckin);
		checkout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		FILE.add(checkout);
		
		
		JMenu MONITORING = new JMenu("MONITORING");
		MONITORING.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(MONITORING);
		
		final JMenuItem checkinlist = new JMenuItem("Checked In List");
		checkinlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				checkinlist obj=new checkinlist();
				obj.main(null);
			
			}
		});
		checkinlist.setBackground(Color.WHITE);
		checkinlist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		MONITORING.add(checkinlist);
		
		final JMenuItem reservationlist = new JMenuItem("Reservation List");
		reservationlist.setBackground(Color.WHITE);
		reservationlist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		reservationlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				reservationlist obj=new reservationlist();
				obj.main(null);
				
			}
		});
		MONITORING.add(reservationlist);
		
		JMenuItem checkoutlist = new JMenuItem("Checked Out List");
		checkoutlist.setBackground(Color.WHITE);
		checkoutlist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		checkoutlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				checkoutlist obj=new checkoutlist();
				obj.main(null);
				 	
			}
		});
		MONITORING.add(checkoutlist);
		
		JMenuItem roomlist = new JMenuItem("Room List");
		roomlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				roomlist obj=new roomlist();
				obj.main(null);
			}
		});
		roomlist.setBackground(Color.WHITE);
		roomlist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		MONITORING.add(roomlist);
		
		JMenu UPDATION = new JMenu("UPDATION");
		UPDATION.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(UPDATION);
		
		JMenuItem discount = new JMenuItem("Manage Discount");
		discount.setBackground(Color.WHITE);
		discount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				discount obj=new discount();
				obj.main(null);
			}
		});
		discount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		UPDATION.add(discount);
		
		JMenuItem room = new JMenuItem("Manage Rooms");
		room.setBackground(Color.WHITE);
		room.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				manageroom obj=new manageroom();
				obj.main(null);
			}
		});
		room.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		UPDATION.add(room);
		
		JMenu LOGOUT = new JMenu("LOGOUT");
		LOGOUT.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(LOGOUT);
		
		JMenuItem logout = new JMenuItem("LogOut");
		logout.setBackground(Color.WHITE);
		logout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);

				login obj1=new login();
				obj1.main(null);
			}
		});
		LOGOUT.add(logout);
		
		JMenu EXIT = new JMenu("EXIT");
		EXIT.setFont(new Font("Times New Roman", Font.BOLD, 25));
		menuBar.add(EXIT);
		
		JMenuItem Exit = new JMenuItem("Exit");
		Exit.setBackground(Color.WHITE);
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				System.exit(0);

			}
		});
		Exit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		EXIT.add(Exit);
		
		JPanel bg = new JPanel();
		bg.setBackground(new Color(224, 255, 255));
		bg.setBounds(0, 130, 1512, 687);
		contentPane.add(bg);
		bg.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NEW TAJ HOTEL");
		lblNewLabel.setBounds(647, 10, 400, 54);
		contentPane.add(lblNewLabel);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 40));
		
		
		
		

	}
}
