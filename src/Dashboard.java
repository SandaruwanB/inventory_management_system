import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DrawerController drawer;
	private DbConnection con = new DbConnection();


	public Dashboard() {
		setTitle("Stock Management System - Dashboard");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
	    int y = (screenSize.height -getHeight()) / 2;
	    
	    setLocation(x,y);
	    
		checkDatabseTables();
		drawer = Drawer.newDrawer(this)
				.header(new JLabel("Header Text"))
				.addChild(new DrawerItem("Dashboard").icon(new ImageIcon(getClass().getResource("/images/house.svg"))).build())
				.addChild(new DrawerItem("Customers").build())
				.addChild(new DrawerItem("Supliers").build())
				.addChild(new DrawerItem("Products").build())
				.addChild(new DrawerItem("Stock In").build())
				.addChild(new DrawerItem("Stock Out").build())
				.addChild(new DrawerItem("Stock Locations").build())
				.addChild(new DrawerItem("Users").build())
				.addFooter(new DrawerItem("Log Out").build())
				.background(new Color(0,0,102))
				.duration(200)
				.event(new EventDrawer() {

					@Override
					public void selected(int index, DrawerItem item) {
						switch(index) {
							case 0 :
								System.out.println("Dashboard");
								break;
							case 1 :
								System.out.println("Customers");
								break;
							case 2 :
								System.out.println("Supliers");
								break;
							case 3 :
								System.out.println("Products");
								break;
							case 4 :
								System.out.println("Stock In");
								break;
							case 5 :
								System.out.println("Stock Out");
								break;
							case 6 :
								System.out.println("Stock Locations");
								break;
							case 7 :
								System.out.println("Users");
								break;
							case 8 :
								System.out.println("Log out");
								break;
						}
					}
				})
				.build();
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("|||");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(drawer.isShow()) {
					drawer.hide();
				}
				else {
					drawer.show();
				}
			}
		});
		button.setBounds(12, 30, 55, 27);
		contentPane.add(button);
	}
	
	
	public void checkDatabseTables(){
		try {
			Statement st = con.getConnection().createStatement();
			PreparedStatement pst;
			
			String query = "SHOW TABLES;";
			pst = con.getConnection().prepareStatement(query);
			ResultSet res = pst.executeQuery();
			if (res.next()) {
				if (res.getString("Tables_in_" + con.DB_NAME).equals("user_details")) {
					System.out.println("Tables Loaded");
				}
				else {
					try {
					// create required database tables
					
					String que = "CREATE TABLE IF NOT EXISTS user_details(id INT PRIMARY KEY AUTO_INCREMENT,first_name VARCHAR (255),last_name VARCHAR (255),address_line1 VARCHAR (100),address_line2 VARCHAR (100),zip_code INT,email VARCHAR (255),mobile INT,home INT,user_id INT);";
					st.execute(que);
					que = "CREATE TABLE IF NOT EXISTS supliers(id INT PRIMARY KEY AUTO_INCREMENT,first_name VARCHAR (255),last_name VARCHAR (255),address_line1 VARCHAR (255),address_line2 VARCHAR (255),zip INT,town VARCHAR (100),country VARCHAR (100),email VARCHAR (255),phone VARCHAR (12),office VARCHAR (12),reg_date DATE,timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);";
					st.execute(que);
					que = "CREATE TABLE IF NOT EXISTS customers(id INT PRIMARY KEY AUTO_INCREMENT,first_name VARCHAR (255),last_name VARCHAR (255),address_line1 VARCHAR (255),address_line2 VARCHAR (255),zip INT,town VARCHAR (100),country VARCHAR (100),email VARCHAR (255),phone VARCHAR (12),office VARCHAR (12),reg_date DATE,timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);";
					st.execute(que);
					que = "CREATE TABLE IF NOT EXISTS products(id INT AUTO_INCREMENT PRIMARY KEY,product_code VARCHAR (100),product_name VARCHAR (100),sales_price FLOAT,cost FLOAT,on_hand_qty FLOAT,orderd_qty FLOAT,unit_of_mesure VARCHAR (10) );";
					st.execute(que);
					que = "CREATE TABLE IF NOT EXISTS storage_locations(location_id INT AUTO_INCREMENT PRIMARY KEY,location_code VARCHAR (100),location_name VARCHAR (100) );";
					st.execute(que);
					que = "CREATE TABLE IF NOT EXISTS stock_issue(id INT PRIMARY KEY AUTO_INCREMENT,customer_id INT,location_id INT,note VARCHAR (255),issued_by INT,date DATE,state VARCHAR (100),timestamp DATETIME DEFAULT CURRENT_TIMESTAMP );";
					st.execute(que);
					que = "CREATE TABLE IF NOT EXISTS stock_issue_line(id INT AUTO_INCREMENT PRIMARY KEY,stock_issue_id INT,item INT,quantity FLOAT );";
					st.execute(que);
					que = "CREATE TABLE IF NOT EXISTS stock_income(id INT PRIMARY KEY AUTO_INCREMENT,supplier_id INT,location_id INT,note VARCHAR (255),requested_by INT,date DATE,state VARCHAR (100),timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);";
					st.execute(que);
					que = "CREATE TABLE IF NOT EXISTS stock_income_line(id INT AUTO_INCREMENT PRIMARY KEY,stock_income_id INT,item INT,quantity FLOAT );";					
					st.execute(que);
					
					// create relationships
					que = "ALTER TABLE `user_details` ADD CONSTRAINT `user and user_details` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_income_line` ADD CONSTRAINT `stock income and line` FOREIGN KEY (`stock_income_id`) REFERENCES `stock_income`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_issue_line` ADD CONSTRAINT `stock issue and line` FOREIGN KEY (`stock_issue_id`) REFERENCES `stock_issue`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_issue` ADD CONSTRAINT `customer and issue` FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_income` ADD CONSTRAINT `supplier and line` FOREIGN KEY (`supplier_id`) REFERENCES `supliers`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_income` ADD CONSTRAINT `user and stock income` FOREIGN KEY (`requested_by`) REFERENCES `users`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_income` ADD CONSTRAINT `location and income` FOREIGN KEY (`location_id`) REFERENCES `storage_locations`(`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_issue` ADD CONSTRAINT `user and issue` FOREIGN KEY (`issued_by`) REFERENCES `users`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_issue` ADD CONSTRAINT `location and issue` FOREIGN KEY (`location_id`) REFERENCES `storage_locations`(`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_income_line` ADD CONSTRAINT `item and income` FOREIGN KEY (`item`) REFERENCES `products`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;";
					st.execute(que);
					que = "ALTER TABLE `stock_issue_line` ADD CONSTRAINT `item and issue` FOREIGN KEY (`item`) REFERENCES `products`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;";
					st.execute(que);
					
					
					
					System.out.println("Tables Created");
					} catch(Exception e) {
						System.out.println("tables exists");
					}
				}
				
			}	
			
			st.close();
			con.getConnection().close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
