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
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.table.DefaultTableModel;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DrawerController drawer;
	private JLayeredPane layeredPane;
	private JPanel dashboard, supliers, Customers, products, stockin, stockout, locations, users;
	private DbConnection con = new DbConnection();
	private JTable tableCustomers;
	private DefaultTableModel model;


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
	    
	    Image dashimg = new ImageIcon(getClass().getResource("/images/dashboard.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    Image usersimg = new ImageIcon(getClass().getResource("/images/group.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    Image productsimg = new ImageIcon(getClass().getResource("/images/cubes.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    Image stockoutimg = new ImageIcon(getClass().getResource("/images/up-arrow.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    Image stockinimg = new ImageIcon(getClass().getResource("/images/arrow-down.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    Image warehouseimg = new ImageIcon(getClass().getResource("/images/warehouse.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    Image logoutimg = new ImageIcon(getClass().getResource("/images/logout.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    Image menuimg = new ImageIcon(getClass().getResource("/images/menu.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    Image userimg = new ImageIcon(getClass().getResource("/images/user.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	    Image addimg = new ImageIcon(getClass().getResource("/images/plus.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
	    
		checkDatabseTables();
		drawer = Drawer.newDrawer(this)
				.header(new JLabel("Header Text"))
				.addChild(new DrawerItem("Dashboard").icon(new ImageIcon(dashimg)).build())
				.addChild(new DrawerItem("Customers").icon(new ImageIcon(usersimg)).build())
				.addChild(new DrawerItem("Supliers").icon(new ImageIcon(usersimg)).build())
				.addChild(new DrawerItem("Products").icon(new ImageIcon(productsimg)).build())
				.addChild(new DrawerItem("Stock In").icon(new ImageIcon(stockinimg)).build())
				.addChild(new DrawerItem("Stock Out").icon(new ImageIcon(stockoutimg)).build())
				.addChild(new DrawerItem("Stock Locations").icon(new ImageIcon(warehouseimg)).build())
				.addChild(new DrawerItem("Users").icon(new ImageIcon(usersimg)).build())
				.addFooter(new DrawerItem("Log Out").icon(new ImageIcon(logoutimg)).build())
				.duration(200)
				.event(new EventDrawer() {

					@Override
					public void selected(int index, DrawerItem item) {
						switch(index) {
							case 0 :
								switchPages(dashboard);
								openCloseDrawer();
								break;
							case 1 :
								switchPages(Customers);
								openCloseDrawer();
								break;
							case 2 :
								switchPages(supliers);
								openCloseDrawer();
								break;
							case 3 :
								switchPages(products);
								openCloseDrawer();
								break;
							case 4 :
								switchPages(stockin);
								openCloseDrawer();
								break;
							case 5 :
								switchPages(stockout);
								openCloseDrawer();
								break;
							case 6 :
								switchPages(locations);
								openCloseDrawer();
								break;
							case 7 :
								switchPages(users);
								openCloseDrawer();
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1024, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 60, 1024, 540);
		panel.add(layeredPane);
		
		dashboard = new JPanel();
		layeredPane.setLayer(dashboard, 0);
		dashboard.setBounds(0, 0, 1024, 567);
		layeredPane.add(dashboard);
		dashboard.setBackground(new Color(255, 255, 255));
		dashboard.setLayout(null);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setBounds(34, 0, 126, 25);
		dashboard.add(lblDashboard);
		lblDashboard.setFont(new Font("Roboto", Font.BOLD, 19));
		
		JPanel totalProducts = new JPanel();
		totalProducts.setBackground(new Color(0, 0, 102));
		totalProducts.setBounds(34, 44, 215, 106);
		dashboard.add(totalProducts);
		totalProducts.setLayout(null);
		
		JLabel lblTotalProducts = new JLabel("Total Products");
		lblTotalProducts.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalProducts.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTotalProducts.setForeground(new Color(255, 255, 255));
		lblTotalProducts.setBounds(0, 12, 215, 17);
		totalProducts.add(lblTotalProducts);
		
		JLabel label = new JLabel("22");
		label.setFont(new Font("Dialog", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(79, 41, 60, 36);
		totalProducts.add(label);
		
		JPanel totalLocations = new JPanel();
		totalLocations.setLayout(null);
		totalLocations.setBackground(new Color(0, 0, 102));
		totalLocations.setBounds(285, 44, 215, 106);
		dashboard.add(totalLocations);
		
		JLabel lblTotalLocations = new JLabel("Total Locations");
		lblTotalLocations.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalLocations.setForeground(Color.WHITE);
		lblTotalLocations.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTotalLocations.setBounds(0, 12, 215, 17);
		totalLocations.add(lblTotalLocations);
		
		JLabel label_1 = new JLabel("22");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.BOLD, 24));
		label_1.setBounds(79, 39, 60, 36);
		totalLocations.add(label_1);
		
		JPanel totalStockOut = new JPanel();
		totalStockOut.setLayout(null);
		totalStockOut.setBackground(new Color(0, 0, 102));
		totalStockOut.setBounds(531, 44, 215, 106);
		dashboard.add(totalStockOut);
		
		JLabel lblTotalIssues = new JLabel("Total Stock Outs");
		lblTotalIssues.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalIssues.setForeground(Color.WHITE);
		lblTotalIssues.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTotalIssues.setBounds(0, 12, 215, 17);
		totalStockOut.add(lblTotalIssues);
		
		JLabel label_2 = new JLabel("22");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Dialog", Font.BOLD, 24));
		label_2.setBounds(79, 41, 60, 36);
		totalStockOut.add(label_2);
		
		JPanel totalLocations_1 = new JPanel();
		totalLocations_1.setLayout(null);
		totalLocations_1.setBackground(new Color(0, 0, 102));
		totalLocations_1.setBounds(775, 44, 215, 106);
		dashboard.add(totalLocations_1);
		
		JLabel lblTotalLocations_1 = new JLabel("Total Stock Ins");
		lblTotalLocations_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalLocations_1.setForeground(Color.WHITE);
		lblTotalLocations_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTotalLocations_1.setBounds(0, 12, 215, 17);
		totalLocations_1.add(lblTotalLocations_1);
		
		JLabel label_1_1 = new JLabel("22");
		label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1_1.setForeground(Color.WHITE);
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 24));
		label_1_1.setBounds(79, 41, 60, 36);
		totalLocations_1.add(label_1_1);
		
		Customers = new JPanel();
		layeredPane.setLayer(Customers, 1);
		Customers.setBounds(0, 0, 1024, 567);
		layeredPane.add(Customers);
		Customers.setLayout(null);
		Customers.setBackground(Color.WHITE);
		
		JLabel lblCustomers = new JLabel("Customers");
		lblCustomers.setFont(new Font("Roboto", Font.BOLD, 19));
		lblCustomers.setBounds(40, 0, 126, 25);
		Customers.add(lblCustomers);
		
		Object[] column = {"Name", "City", "Email", "Contact", "Action"};
		Object[] row = new Object[0];
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setIcon(new ImageIcon(addimg));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addRow(row);
			}
		});
		btnAdd.setBounds(883, 31, 105, 27);
		Customers.add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 67, 966, 457);
		Customers.add(scrollPane);
		
		tableCustomers = new JTable();
		tableCustomers.setBackground(new Color(255, 255, 255));
		tableCustomers.setForeground(new Color(0, 0, 0));
		model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		tableCustomers.setModel(model);
		scrollPane.setViewportView(tableCustomers);
		

		
		supliers = new JPanel();
		layeredPane.setLayer(supliers, 0);
		supliers.setLayout(null);
		supliers.setBackground(Color.WHITE);
		supliers.setBounds(0, 0, 1024, 567);
		layeredPane.add(supliers);
		
		JLabel lblSupliers = new JLabel("Supliers");
		lblSupliers.setFont(new Font("Roboto", Font.BOLD, 19));
		lblSupliers.setBounds(40, 0, 126, 25);
		supliers.add(lblSupliers);
		
		products = new JPanel();
		layeredPane.setLayer(products, 0);
		products.setLayout(null);
		products.setBackground(Color.WHITE);
		products.setBounds(0, 0, 1024, 567);
		layeredPane.add(products);
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.setFont(new Font("Roboto", Font.BOLD, 19));
		lblProducts.setBounds(40, 0, 126, 25);
		products.add(lblProducts);
		
		stockin = new JPanel();
		stockin.setLayout(null);
		stockin.setBackground(Color.WHITE);
		stockin.setBounds(0, 0, 1024, 567);
		layeredPane.add(stockin);
		
		JLabel lblStockIn = new JLabel("Stock In");
		lblStockIn.setFont(new Font("Roboto", Font.BOLD, 19));
		lblStockIn.setBounds(40, 0, 126, 25);
		stockin.add(lblStockIn);
		
		stockout = new JPanel();
		stockout.setBounds(0, 0, 1024, 567);
		layeredPane.add(stockout);
		stockout.setLayout(null);
		stockout.setBackground(Color.WHITE);
		
		JLabel lblStockOut = new JLabel("Stock Out");
		lblStockOut.setFont(new Font("Roboto", Font.BOLD, 19));
		lblStockOut.setBounds(40, 0, 126, 25);
		stockout.add(lblStockOut);
		
		locations = new JPanel();
		locations.setLayout(null);
		locations.setBackground(Color.WHITE);
		locations.setBounds(0, 0, 1024, 567);
		layeredPane.add(locations);
		
		JLabel lblStockLocations = new JLabel("Stock Locations");
		lblStockLocations.setFont(new Font("Roboto", Font.BOLD, 19));
		lblStockLocations.setBounds(40, 0, 126, 25);
		locations.add(lblStockLocations);
		
		users = new JPanel();
		users.setLayout(null);
		users.setBackground(Color.WHITE);
		users.setBounds(0, 0, 1024, 567);
		layeredPane.add(users);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setFont(new Font("Roboto", Font.BOLD, 19));
		lblUsers.setBounds(40, 0, 126, 25);
		users.add(lblUsers);
		
		JButton btnMenue = new JButton();
		btnMenue.setForeground(new Color(255, 255, 255));
		btnMenue.setBounds(898, 12, 36, 27);
		panel.add(btnMenue);
		btnMenue.setBackground(new Color(255, 255, 255));
		btnMenue.setIcon(new ImageIcon(menuimg));
		
		JButton btnProfile = new JButton();
		btnProfile.setBounds(957, 12, 36, 27);
		panel.add(btnProfile);
		btnProfile.setBackground(new Color(255, 255, 255));
		btnProfile.setIcon(new ImageIcon(userimg));
		btnMenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCloseDrawer();
			}
		});
	}
	
	public void switchPages(JPanel panel) {
		this.layeredPane.removeAll();
		this.layeredPane.add(panel);
		this.layeredPane.repaint();
		this.layeredPane.revalidate();
	}
	
	public void openCloseDrawer() {
		if (drawer.isShow()) {
			drawer.hide();
		}
		else {
			drawer.show();
		}
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
