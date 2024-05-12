import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DrawerController drawer;
	private JLayeredPane layeredPane;
	private JPanel dashboard, supliers, Customers, products, stockin, stockout, locations, users, CustomerAdd, CustomerEdit, suplierAdd, suplierEdit, StockLocationAdd;
	private DbConnection con = new DbConnection();
	private Statement st;
	private JTable tableCustomers;
	private DefaultTableModel modelCustomer, modelSuplier, modelStock;
	private JTextField textCustomerFirstName;
	private JTextField textCustomerLastName;
	private JTextField txtCustomerAddressLine1;
	private JTextField textCustomerAddressLine2;
	private JTextField textCustomerZip;
	private JTextField textCustomerCity;
	private JTextField textCustomerCountry;
	private JTextField textCustomerEmail;
	private JTextField textCustomerMobile;
	private JTextField textCustomerOffice;
	private JTextField textCustomerEfirstname;
	private JTextField textCustomerElastname;
	private JTextField textCustomerEaddressline1;
	private JTextField textCustomerEaddressline2;
	private JTextField textCustomerEzip;
	private JTextField textCustomerEcity;
	private JTextField textCustomerEcountry;
	private JTextField textCustomerEemail;
	private JTextField textCustomerEmobile;
	private JTextField textCustomerEofficel;
	private JTable tableSupliers;
	private JTextField textSuplierFirstname;
	private JTextField textSuplierLastname;
	private JTextField textSuplierAddressline1;
	private JTextField textSuplierAddressline2;
	private JTextField textSuplierZip;
	private JTextField textSuplierCity;
	private JTextField textSuplierCountry;
	private JTextField textSuplierEmail;
	private JTextField textSuplierPhone;
	private JTextField textSuplierOffice;
	private JTextField textSuplierEfirstname;
	private JTextField textSuplierElastname;
	private JTextField textSuplierEaddressline1;
	private JTextField textSuplierEaddressline2;
	private JTextField textSuplierEzip;
	private JTextField textSuplierEcity;
	private JTextField textSuplierEcountry;
	private JTextField textSuplierEemail;
	private JTextField textSuplierEmobile;
	private JTextField textSuplierEoffice;
	private JTable tableStockLocations;
	private JTextField textLocationCode;
	private JTextField textLocationName;
	private JTextField textLocationEcode;
	private JTextField textLocationEname;


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
	    Image removeimg = new ImageIcon(getClass().getResource("/images/delete.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
	    Image editimg = new ImageIcon(getClass().getResource("/images/edit.png")).getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
	    
	    
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
		
		JLabel lblShortcuts = new JLabel("Shortcuts");
		lblShortcuts.setBounds(34, 177, 60, 17);
		dashboard.add(lblShortcuts);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 51));
		panel_2.setBounds(34, 166, 956, 6);
		dashboard.add(panel_2);
		
		Customers = new JPanel();
		layeredPane.setLayer(Customers, 0);
		Customers.setBounds(0, 0, 1024, 567);
		layeredPane.add(Customers);
		Customers.setLayout(null);
		Customers.setBackground(Color.WHITE);
		
		JLabel lblCustomers = new JLabel("Customers");
		lblCustomers.setFont(new Font("Roboto", Font.BOLD, 19));
		lblCustomers.setBounds(40, 0, 126, 25);
		Customers.add(lblCustomers);
		
		Object[] column = {"id", "Name", "City", "Email", "Contact"};
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setIcon(new ImageIcon(addimg));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPages(CustomerAdd);				
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
		modelCustomer = new DefaultTableModel();
		modelCustomer.setColumnIdentifiers(column);
		writeCustomerDetails();
		tableCustomers.setModel(modelCustomer);
		scrollPane.setViewportView(tableCustomers);
		
		JButton btnDeleteCustomer = new JButton();
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableCustomers.getSelectedRow() != -1) {
					String user_id = (String) tableCustomers.getModel().getValueAt(tableCustomers.getSelectedRow(), 0);
					int id = Integer.parseInt(user_id);
					
					removeCustomer(id);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please click on customer you want to remove.");
				}
			}
		});
		btnDeleteCustomer.setIcon(new ImageIcon(removeimg));
		btnDeleteCustomer.setBackground(new Color(255, 255, 255));
		btnDeleteCustomer.setBounds(841, 31, 30, 27);
		Customers.add(btnDeleteCustomer);
		
		JButton btnEditCustomer = new JButton("");
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableCustomers.getSelectedRow() != -1) {
					String user_id = (String) tableCustomers.getModel().getValueAt(tableCustomers.getSelectedRow(), 0);
					int id = Integer.parseInt(user_id);
					
					editCustomer(id);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please click on customer you want to edit.");
				}
			}
		});
		btnEditCustomer.setIcon(new ImageIcon(editimg));
		btnEditCustomer.setBackground(new Color(255, 255, 255));
		btnEditCustomer.setBounds(799, 31, 30, 27);
		Customers.add(btnEditCustomer);
		

		
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
		
		JButton btnSuplierAdd = new JButton("ADD");
		btnSuplierAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPages(suplierAdd);
			}
		});
		btnSuplierAdd.setIcon(new ImageIcon(addimg));
		btnSuplierAdd.setBackground(Color.WHITE);
		btnSuplierAdd.setBounds(884, 33, 105, 27);
		supliers.add(btnSuplierAdd);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 68, 967, 456);
		supliers.add(scrollPane_1);
		
		tableSupliers = new JTable();
		tableSupliers.setBackground(new Color(255, 255, 255));
		tableSupliers.setForeground(new Color(0, 0, 0));
		modelSuplier = new DefaultTableModel();
		modelSuplier.setColumnIdentifiers(column);
		writeSuplierDetails();
		tableSupliers.setModel(modelSuplier);
		scrollPane_1.setViewportView(tableSupliers);
		
		JButton btnDeleteSuplier = new JButton();
		btnDeleteSuplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableSupliers.getSelectedRow() != -1) {
					String user_id = (String) tableSupliers.getModel().getValueAt(tableSupliers.getSelectedRow(), 0);
					int id = Integer.parseInt(user_id);
					
					removeSuplier(id);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please click on customer you want to remove.");
				}
			}
		});
		btnDeleteSuplier.setIcon(new ImageIcon(removeimg));
		btnDeleteSuplier.setBackground(Color.WHITE);
		btnDeleteSuplier.setBounds(842, 33, 30, 27);
		supliers.add(btnDeleteSuplier);
		
		JButton btnEditSuplier = new JButton("");
		btnEditSuplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableSupliers.getSelectedRow() != -1) {
					String user_id = (String) tableSupliers.getModel().getValueAt(tableSupliers.getSelectedRow(), 0);
					int id = Integer.parseInt(user_id);
					
					editSuplier(id);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please click on suplier you want to update.");
				}
			}
		});
		btnEditSuplier.setIcon(new ImageIcon(editimg));
		btnEditSuplier.setBackground(Color.WHITE);
		btnEditSuplier.setBounds(800, 33, 30, 27);
		supliers.add(btnEditSuplier);
		
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
		layeredPane.setLayer(locations, 0);
		locations.setLayout(null);
		locations.setBackground(Color.WHITE);
		locations.setBounds(0, 0, 1024, 540);
		layeredPane.add(locations);
		
		JLabel lblStockLocations = new JLabel("Stock Locations");
		lblStockLocations.setFont(new Font("Roboto", Font.BOLD, 19));
		lblStockLocations.setBounds(40, 0, 183, 25);
		locations.add(lblStockLocations);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(36, 71, 957, 443);
		locations.add(scrollPane_2);
		
		tableStockLocations = new JTable();
		Object[] stockColumns = {"id", "Location Code", "Location Name"}; 
		scrollPane_2.setViewportView(tableStockLocations);
		tableStockLocations.setBackground(new Color(255, 255, 255));
		tableStockLocations.setForeground(new Color(0, 0, 0));
		modelStock = new DefaultTableModel();
		modelStock.setColumnIdentifiers(stockColumns);
		writeStockLocationData();
		tableStockLocations.setModel(modelStock);
		
		JButton btnAddStock = new JButton("ADD");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPages(StockLocationAdd);
			}
		});
		btnAddStock.setIcon(new ImageIcon(addimg));
		btnAddStock.setBackground(Color.WHITE);
		btnAddStock.setBounds(881, 35, 105, 27);
		locations.add(btnAddStock);
		
		JButton btnEditStock = new JButton("");
		btnEditStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditStock.setIcon(new ImageIcon(editimg));
		btnEditStock.setBackground(Color.WHITE);
		btnEditStock.setBounds(797, 35, 30, 27);
		locations.add(btnEditStock);
		
		JButton btnDeleteStock = new JButton();
		btnDeleteStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteStock.setIcon(new ImageIcon(removeimg));
		btnDeleteStock.setBackground(Color.WHITE);
		btnDeleteStock.setBounds(839, 35, 30, 27);
		locations.add(btnDeleteStock);
		
		users = new JPanel();
		users.setLayout(null);
		users.setBackground(Color.WHITE);
		users.setBounds(0, 0, 1024, 567);
		layeredPane.add(users);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setFont(new Font("Roboto", Font.BOLD, 19));
		lblUsers.setBounds(40, 0, 126, 25);
		users.add(lblUsers);
		
		CustomerAdd = new JPanel();
		layeredPane.setLayer(CustomerAdd, 0);
		CustomerAdd.setLayout(null);
		CustomerAdd.setBackground(Color.WHITE);
		CustomerAdd.setBounds(0, 0, 1024, 567);
		layeredPane.add(CustomerAdd);
		
		JLabel lblCustomersAdd = new JLabel("Customers / Add");
		lblCustomersAdd.setFont(new Font("Roboto", Font.BOLD, 19));
		lblCustomersAdd.setBounds(40, 0, 198, 25);
		CustomerAdd.add(lblCustomersAdd);
		
		JLabel lblFirstname = new JLabel("First Name");
		lblFirstname.setBounds(40, 52, 81, 17);
		CustomerAdd.add(lblFirstname);
		
		textCustomerFirstName = new JTextField();
		textCustomerFirstName.setBounds(40, 70, 321, 21);
		CustomerAdd.add(textCustomerFirstName);
		textCustomerFirstName.setColumns(10);
		
		JLabel lblLastname = new JLabel("Last Name");
		lblLastname.setBounds(40, 103, 81, 17);
		CustomerAdd.add(lblLastname);
		
		textCustomerLastName = new JTextField();
		textCustomerLastName.setBounds(40, 119, 321, 21);
		CustomerAdd.add(textCustomerLastName);
		textCustomerLastName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(40, 165, 60, 17);
		CustomerAdd.add(lblAddress);
		
		txtCustomerAddressLine1 = new JTextField();
		txtCustomerAddressLine1.setToolTipText("Address Line 1");
		txtCustomerAddressLine1.setBounds(40, 207, 321, 21);
		CustomerAdd.add(txtCustomerAddressLine1);
		txtCustomerAddressLine1.setColumns(10);
		
		JLabel lblAddressLine = new JLabel("Address Line 1");
		lblAddressLine.setBounds(40, 191, 138, 17);
		CustomerAdd.add(lblAddressLine);
		
		JLabel lblAddressLine_2 = new JLabel("Address Line 2");
		lblAddressLine_2.setBounds(40, 240, 138, 17);
		CustomerAdd.add(lblAddressLine_2);
		
		textCustomerAddressLine2 = new JTextField();
		textCustomerAddressLine2.setBounds(40, 257, 321, 21);
		CustomerAdd.add(textCustomerAddressLine2);
		textCustomerAddressLine2.setColumns(10);
		
		JLabel lblAddressLine_2_1 = new JLabel("Zip Code");
		lblAddressLine_2_1.setBounds(40, 290, 73, 17);
		CustomerAdd.add(lblAddressLine_2_1);
		
		textCustomerZip = new JTextField();
		textCustomerZip.setBounds(40, 308, 114, 21);
		CustomerAdd.add(textCustomerZip);
		textCustomerZip.setColumns(10);
		
		JLabel lblAddressLine_2_1_1 = new JLabel("City");
		lblAddressLine_2_1_1.setBounds(181, 290, 73, 17);
		CustomerAdd.add(lblAddressLine_2_1_1);
		
		textCustomerCity = new JTextField();
		textCustomerCity.setBounds(181, 308, 180, 21);
		CustomerAdd.add(textCustomerCity);
		textCustomerCity.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Counrty");
		lblNewLabel.setBounds(40, 341, 60, 17);
		CustomerAdd.add(lblNewLabel);
		
		textCustomerCountry = new JTextField();
		textCustomerCountry.setBounds(40, 358, 321, 21);
		CustomerAdd.add(textCustomerCountry);
		textCustomerCountry.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 102));
		panel_1.setBounds(28, 159, 967, 4);
		CustomerAdd.add(panel_1);
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setBounds(488, 52, 180, 17);
		CustomerAdd.add(lblEmailAddress);
		
		textCustomerEmail = new JTextField();
		textCustomerEmail.setBounds(488, 70, 347, 21);
		CustomerAdd.add(textCustomerEmail);
		textCustomerEmail.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(488, 103, 60, 17);
		CustomerAdd.add(lblMobile);
		
		textCustomerMobile = new JTextField();
		textCustomerMobile.setBounds(488, 119, 151, 21);
		CustomerAdd.add(textCustomerMobile);
		textCustomerMobile.setColumns(10);
		
		JLabel lblOffice = new JLabel("Office");
		lblOffice.setBounds(671, 103, 60, 17);
		CustomerAdd.add(lblOffice);
		
		textCustomerOffice = new JTextField();
		textCustomerOffice.setBounds(671, 119, 164, 21);
		CustomerAdd.add(textCustomerOffice);
		textCustomerOffice.setColumns(10);
		
		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCustomer();
			}
		});
		btnAddCustomer.setForeground(new Color(255, 255, 255));
		btnAddCustomer.setBackground(new Color(0, 204, 0));
		btnAddCustomer.setBounds(40, 429, 143, 27);
		CustomerAdd.add(btnAddCustomer);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPages(Customers);
			}
		});
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(255, 0, 0));
		btnCancel.setBounds(217, 429, 105, 27);
		CustomerAdd.add(btnCancel);
		
		CustomerEdit = new JPanel();
		layeredPane.setLayer(CustomerEdit, 0);
		CustomerEdit.setLayout(null);
		CustomerEdit.setBackground(Color.WHITE);
		CustomerEdit.setBounds(0, 0, 1024, 567);
		layeredPane.add(CustomerEdit);
		
		JLabel lblCustomersEdit = new JLabel("Customers / Edit");
		lblCustomersEdit.setFont(new Font("Roboto", Font.BOLD, 19));
		lblCustomersEdit.setBounds(40, 0, 198, 25);
		CustomerEdit.add(lblCustomersEdit);
		
		JLabel lblFirstname_1 = new JLabel("First Name");
		lblFirstname_1.setBounds(40, 52, 81, 17);
		CustomerEdit.add(lblFirstname_1);
		
		textCustomerEfirstname = new JTextField();
		textCustomerEfirstname.setColumns(10);
		textCustomerEfirstname.setBounds(40, 70, 321, 21);
		CustomerEdit.add(textCustomerEfirstname);
		
		JLabel lblLastname_1 = new JLabel("Last Name");
		lblLastname_1.setBounds(40, 103, 81, 17);
		CustomerEdit.add(lblLastname_1);
		
		textCustomerElastname = new JTextField();
		textCustomerElastname.setColumns(10);
		textCustomerElastname.setBounds(40, 119, 321, 21);
		CustomerEdit.add(textCustomerElastname);
		
		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setBounds(40, 165, 60, 17);
		CustomerEdit.add(lblAddress_1);
		
		textCustomerEaddressline1 = new JTextField();
		textCustomerEaddressline1.setToolTipText("Address Line 1");
		textCustomerEaddressline1.setColumns(10);
		textCustomerEaddressline1.setBounds(40, 207, 321, 21);
		CustomerEdit.add(textCustomerEaddressline1);
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 1");
		lblAddressLine_1.setBounds(40, 191, 138, 17);
		CustomerEdit.add(lblAddressLine_1);
		
		JLabel lblAddressLine_2_2 = new JLabel("Address Line 2");
		lblAddressLine_2_2.setBounds(40, 240, 138, 17);
		CustomerEdit.add(lblAddressLine_2_2);
		
		textCustomerEaddressline2 = new JTextField();
		textCustomerEaddressline2.setColumns(10);
		textCustomerEaddressline2.setBounds(40, 257, 321, 21);
		CustomerEdit.add(textCustomerEaddressline2);
		
		JLabel lblAddressLine_2_1_2 = new JLabel("Zip Code");
		lblAddressLine_2_1_2.setBounds(40, 290, 73, 17);
		CustomerEdit.add(lblAddressLine_2_1_2);
		
		textCustomerEzip = new JTextField();
		textCustomerEzip.setColumns(10);
		textCustomerEzip.setBounds(40, 308, 114, 21);
		CustomerEdit.add(textCustomerEzip);
		
		JLabel lblAddressLine_2_1_1_1 = new JLabel("City");
		lblAddressLine_2_1_1_1.setBounds(181, 290, 73, 17);
		CustomerEdit.add(lblAddressLine_2_1_1_1);
		
		textCustomerEcity = new JTextField();
		textCustomerEcity.setColumns(10);
		textCustomerEcity.setBounds(181, 308, 180, 21);
		CustomerEdit.add(textCustomerEcity);
		
		JLabel lblNewLabel_1 = new JLabel("Counrty");
		lblNewLabel_1.setBounds(40, 341, 60, 17);
		CustomerEdit.add(lblNewLabel_1);
		
		textCustomerEcountry = new JTextField();
		textCustomerEcountry.setColumns(10);
		textCustomerEcountry.setBounds(40, 358, 321, 21);
		CustomerEdit.add(textCustomerEcountry);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(0, 0, 102));
		panel_1_1.setBounds(28, 159, 967, 4);
		CustomerEdit.add(panel_1_1);
		
		JLabel lblEmailAddress_1 = new JLabel("Email Address");
		lblEmailAddress_1.setBounds(488, 52, 180, 17);
		CustomerEdit.add(lblEmailAddress_1);
		
		textCustomerEemail = new JTextField();
		textCustomerEemail.setColumns(10);
		textCustomerEemail.setBounds(488, 70, 347, 21);
		CustomerEdit.add(textCustomerEemail);
		
		JLabel lblMobile_1 = new JLabel("Mobile");
		lblMobile_1.setBounds(488, 103, 60, 17);
		CustomerEdit.add(lblMobile_1);
		
		textCustomerEmobile = new JTextField();
		textCustomerEmobile.setColumns(10);
		textCustomerEmobile.setBounds(488, 119, 151, 21);
		CustomerEdit.add(textCustomerEmobile);
		
		JLabel lblOffice_1 = new JLabel("Office");
		lblOffice_1.setBounds(671, 103, 60, 17);
		CustomerEdit.add(lblOffice_1);
		
		textCustomerEofficel = new JTextField();
		textCustomerEofficel.setColumns(10);
		textCustomerEofficel.setBounds(671, 119, 164, 21);
		CustomerEdit.add(textCustomerEofficel);
		
		JButton btnUpdateCustomer = new JButton("Update");
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user_id = (String) tableCustomers.getModel().getValueAt(tableCustomers.getSelectedRow(), 0);
				int id = Integer.parseInt(user_id);
				
				updateCustomer(id);
			}
		});
		btnUpdateCustomer.setForeground(Color.WHITE);
		btnUpdateCustomer.setBackground(new Color(0, 204, 0));
		btnUpdateCustomer.setBounds(40, 429, 95, 27);
		CustomerEdit.add(btnUpdateCustomer);
		
		JButton btnCancelEditCustomer = new JButton("Cancel");
		btnCancelEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPages(Customers);
			}
		});
		btnCancelEditCustomer.setForeground(Color.WHITE);
		btnCancelEditCustomer.setBackground(Color.RED);
		btnCancelEditCustomer.setBounds(147, 429, 105, 27);
		CustomerEdit.add(btnCancelEditCustomer);
		
		suplierAdd = new JPanel();
		layeredPane.setLayer(suplierAdd, 0);
		suplierAdd.setLayout(null);
		suplierAdd.setBackground(Color.WHITE);
		suplierAdd.setBounds(0, 0, 1024, 567);
		layeredPane.add(suplierAdd);
		
		JLabel lblSupliersAdd = new JLabel("Supliers / Add");
		lblSupliersAdd.setFont(new Font("Roboto", Font.BOLD, 19));
		lblSupliersAdd.setBounds(40, 0, 198, 25);
		suplierAdd.add(lblSupliersAdd);
		
		JLabel lblFirstname_2 = new JLabel("First Name");
		lblFirstname_2.setBounds(40, 52, 81, 17);
		suplierAdd.add(lblFirstname_2);
		
		textSuplierFirstname = new JTextField();
		textSuplierFirstname.setColumns(10);
		textSuplierFirstname.setBounds(40, 70, 321, 21);
		suplierAdd.add(textSuplierFirstname);
		
		JLabel lblLastname_2 = new JLabel("Last Name");
		lblLastname_2.setBounds(40, 103, 81, 17);
		suplierAdd.add(lblLastname_2);
		
		textSuplierLastname = new JTextField();
		textSuplierLastname.setColumns(10);
		textSuplierLastname.setBounds(40, 119, 321, 21);
		suplierAdd.add(textSuplierLastname);
		
		JLabel lblAddress_2 = new JLabel("Address");
		lblAddress_2.setBounds(40, 165, 60, 17);
		suplierAdd.add(lblAddress_2);
		
		textSuplierAddressline1 = new JTextField();
		textSuplierAddressline1.setToolTipText("Address Line 1");
		textSuplierAddressline1.setColumns(10);
		textSuplierAddressline1.setBounds(40, 207, 321, 21);
		suplierAdd.add(textSuplierAddressline1);
		
		JLabel lblAddressLine_3 = new JLabel("Address Line 1");
		lblAddressLine_3.setBounds(40, 191, 138, 17);
		suplierAdd.add(lblAddressLine_3);
		
		JLabel lblAddressLine_2_3 = new JLabel("Address Line 2");
		lblAddressLine_2_3.setBounds(40, 240, 138, 17);
		suplierAdd.add(lblAddressLine_2_3);
		
		textSuplierAddressline2 = new JTextField();
		textSuplierAddressline2.setColumns(10);
		textSuplierAddressline2.setBounds(40, 257, 321, 21);
		suplierAdd.add(textSuplierAddressline2);
		
		JLabel lblAddressLine_2_1_3 = new JLabel("Zip Code");
		lblAddressLine_2_1_3.setBounds(40, 290, 73, 17);
		suplierAdd.add(lblAddressLine_2_1_3);
		
		textSuplierZip = new JTextField();
		textSuplierZip.setColumns(10);
		textSuplierZip.setBounds(40, 308, 114, 21);
		suplierAdd.add(textSuplierZip);
		
		JLabel lblAddressLine_2_1_1_2 = new JLabel("City");
		lblAddressLine_2_1_1_2.setBounds(181, 290, 73, 17);
		suplierAdd.add(lblAddressLine_2_1_1_2);
		
		textSuplierCity = new JTextField();
		textSuplierCity.setColumns(10);
		textSuplierCity.setBounds(181, 308, 180, 21);
		suplierAdd.add(textSuplierCity);
		
		JLabel lblNewLabel_2 = new JLabel("Counrty");
		lblNewLabel_2.setBounds(40, 341, 60, 17);
		suplierAdd.add(lblNewLabel_2);
		
		textSuplierCountry = new JTextField();
		textSuplierCountry.setColumns(10);
		textSuplierCountry.setBounds(40, 358, 321, 21);
		suplierAdd.add(textSuplierCountry);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(0, 0, 102));
		panel_1_2.setBounds(28, 159, 967, 4);
		suplierAdd.add(panel_1_2);
		
		JLabel lblEmailAddress_2 = new JLabel("Email Address");
		lblEmailAddress_2.setBounds(488, 52, 180, 17);
		suplierAdd.add(lblEmailAddress_2);
		
		textSuplierEmail = new JTextField();
		textSuplierEmail.setColumns(10);
		textSuplierEmail.setBounds(488, 70, 347, 21);
		suplierAdd.add(textSuplierEmail);
		
		JLabel lblMobile_2 = new JLabel("Mobile");
		lblMobile_2.setBounds(488, 103, 60, 17);
		suplierAdd.add(lblMobile_2);
		
		textSuplierPhone = new JTextField();
		textSuplierPhone.setColumns(10);
		textSuplierPhone.setBounds(488, 119, 151, 21);
		suplierAdd.add(textSuplierPhone);
		
		JLabel lblOffice_2 = new JLabel("Office");
		lblOffice_2.setBounds(671, 103, 60, 17);
		suplierAdd.add(lblOffice_2);
		
		textSuplierOffice = new JTextField();
		textSuplierOffice.setColumns(10);
		textSuplierOffice.setBounds(671, 119, 164, 21);
		suplierAdd.add(textSuplierOffice);
		
		JButton btnAddSuplier = new JButton("Add Suplier");
		btnAddSuplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSuplier();
			}
		});
		btnAddSuplier.setForeground(Color.WHITE);
		btnAddSuplier.setBackground(new Color(0, 204, 0));
		btnAddSuplier.setBounds(40, 429, 143, 27);
		suplierAdd.add(btnAddSuplier);
		
		JButton btnCancelSuplierAdd = new JButton("Cancel");
		btnCancelSuplierAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPages(supliers);
			}
		});
		btnCancelSuplierAdd.setForeground(Color.WHITE);
		btnCancelSuplierAdd.setBackground(Color.RED);
		btnCancelSuplierAdd.setBounds(217, 429, 105, 27);
		suplierAdd.add(btnCancelSuplierAdd);
		
		suplierEdit = new JPanel();
		layeredPane.setLayer(suplierEdit, 0);
		suplierEdit.setLayout(null);
		suplierEdit.setBackground(Color.WHITE);
		suplierEdit.setBounds(0, 0, 1024, 567);
		layeredPane.add(suplierEdit);
		
		JLabel lblSupliersEdit = new JLabel("Supliers / Edit");
		lblSupliersEdit.setFont(new Font("Roboto", Font.BOLD, 19));
		lblSupliersEdit.setBounds(40, 0, 198, 25);
		suplierEdit.add(lblSupliersEdit);
		
		JLabel lblFirstname_2_1 = new JLabel("First Name");
		lblFirstname_2_1.setBounds(40, 52, 81, 17);
		suplierEdit.add(lblFirstname_2_1);
		
		textSuplierEfirstname = new JTextField();
		textSuplierEfirstname.setColumns(10);
		textSuplierEfirstname.setBounds(40, 70, 321, 21);
		suplierEdit.add(textSuplierEfirstname);
		
		JLabel lblLastname_2_1 = new JLabel("Last Name");
		lblLastname_2_1.setBounds(40, 103, 81, 17);
		suplierEdit.add(lblLastname_2_1);
		
		textSuplierElastname = new JTextField();
		textSuplierElastname.setColumns(10);
		textSuplierElastname.setBounds(40, 119, 321, 21);
		suplierEdit.add(textSuplierElastname);
		
		JLabel lblAddress_2_1 = new JLabel("Address");
		lblAddress_2_1.setBounds(40, 165, 60, 17);
		suplierEdit.add(lblAddress_2_1);
		
		textSuplierEaddressline1 = new JTextField();
		textSuplierEaddressline1.setToolTipText("Address Line 1");
		textSuplierEaddressline1.setColumns(10);
		textSuplierEaddressline1.setBounds(40, 207, 321, 21);
		suplierEdit.add(textSuplierEaddressline1);
		
		JLabel lblAddressLine_3_1 = new JLabel("Address Line 1");
		lblAddressLine_3_1.setBounds(40, 191, 138, 17);
		suplierEdit.add(lblAddressLine_3_1);
		
		JLabel lblAddressLine_2_3_1 = new JLabel("Address Line 2");
		lblAddressLine_2_3_1.setBounds(40, 240, 138, 17);
		suplierEdit.add(lblAddressLine_2_3_1);
		
		textSuplierEaddressline2 = new JTextField();
		textSuplierEaddressline2.setColumns(10);
		textSuplierEaddressline2.setBounds(40, 257, 321, 21);
		suplierEdit.add(textSuplierEaddressline2);
		
		JLabel lblAddressLine_2_1_3_1 = new JLabel("Zip Code");
		lblAddressLine_2_1_3_1.setBounds(40, 290, 73, 17);
		suplierEdit.add(lblAddressLine_2_1_3_1);
		
		textSuplierEzip = new JTextField();
		textSuplierEzip.setColumns(10);
		textSuplierEzip.setBounds(40, 308, 114, 21);
		suplierEdit.add(textSuplierEzip);
		
		JLabel lblAddressLine_2_1_1_2_1 = new JLabel("City");
		lblAddressLine_2_1_1_2_1.setBounds(181, 290, 73, 17);
		suplierEdit.add(lblAddressLine_2_1_1_2_1);
		
		textSuplierEcity = new JTextField();
		textSuplierEcity.setColumns(10);
		textSuplierEcity.setBounds(181, 308, 180, 21);
		suplierEdit.add(textSuplierEcity);
		
		JLabel lblNewLabel_2_1 = new JLabel("Counrty");
		lblNewLabel_2_1.setBounds(40, 341, 60, 17);
		suplierEdit.add(lblNewLabel_2_1);
		
		textSuplierEcountry = new JTextField();
		textSuplierEcountry.setColumns(10);
		textSuplierEcountry.setBounds(40, 358, 321, 21);
		suplierEdit.add(textSuplierEcountry);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setBackground(new Color(0, 0, 102));
		panel_1_2_1.setBounds(28, 159, 967, 4);
		suplierEdit.add(panel_1_2_1);
		
		JLabel lblEmailAddress_2_1 = new JLabel("Email Address");
		lblEmailAddress_2_1.setBounds(488, 52, 180, 17);
		suplierEdit.add(lblEmailAddress_2_1);
		
		textSuplierEemail = new JTextField();
		textSuplierEemail.setColumns(10);
		textSuplierEemail.setBounds(488, 70, 347, 21);
		suplierEdit.add(textSuplierEemail);
		
		JLabel lblMobile_2_1 = new JLabel("Mobile");
		lblMobile_2_1.setBounds(488, 103, 60, 17);
		suplierEdit.add(lblMobile_2_1);
		
		textSuplierEmobile = new JTextField();
		textSuplierEmobile.setColumns(10);
		textSuplierEmobile.setBounds(488, 119, 151, 21);
		suplierEdit.add(textSuplierEmobile);
		
		JLabel lblOffice_2_1 = new JLabel("Office");
		lblOffice_2_1.setBounds(671, 103, 60, 17);
		suplierEdit.add(lblOffice_2_1);
		
		textSuplierEoffice = new JTextField();
		textSuplierEoffice.setColumns(10);
		textSuplierEoffice.setBounds(671, 119, 164, 21);
		suplierEdit.add(textSuplierEoffice);
		
		JButton btnEditESuplier = new JButton("Update Suplier");
		btnEditESuplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableSupliers.getSelectedRow() != -1) {
					String user_id = (String) tableSupliers.getModel().getValueAt(tableSupliers.getSelectedRow(), 0);
					int id = Integer.parseInt(user_id);
					updateSuplier(id);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Please click on customer you want to remove.");
				}
			}
		});
		btnEditESuplier.setForeground(Color.WHITE);
		btnEditESuplier.setBackground(new Color(0, 204, 0));
		btnEditESuplier.setBounds(40, 429, 143, 27);
		suplierEdit.add(btnEditESuplier);
		
		JButton btnCancelSuplierEdit = new JButton("Cancel");
		btnCancelSuplierEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPages(supliers);
			}
		});
		btnCancelSuplierEdit.setForeground(Color.WHITE);
		btnCancelSuplierEdit.setBackground(Color.RED);
		btnCancelSuplierEdit.setBounds(217, 429, 105, 27);
		suplierEdit.add(btnCancelSuplierEdit);
		
		StockLocationAdd = new JPanel();
		layeredPane.setLayer(StockLocationAdd, 0);
		StockLocationAdd.setLayout(null);
		StockLocationAdd.setBackground(Color.WHITE);
		StockLocationAdd.setBounds(0, 0, 1024, 567);
		layeredPane.add(StockLocationAdd);
		
		JLabel lblStockLocations_1 = new JLabel("Stock Locations  / Add");
		lblStockLocations_1.setFont(new Font("Roboto", Font.BOLD, 19));
		lblStockLocations_1.setBounds(40, 0, 198, 25);
		StockLocationAdd.add(lblStockLocations_1);
		
		JLabel lblFirstname_3 = new JLabel("Location Code");
		lblFirstname_3.setBounds(40, 52, 105, 17);
		StockLocationAdd.add(lblFirstname_3);
		
		textLocationCode = new JTextField();
		textLocationCode.setColumns(10);
		textLocationCode.setBounds(40, 70, 321, 21);
		StockLocationAdd.add(textLocationCode);
		
		JLabel lblLastname_3 = new JLabel("Location Name");
		lblLastname_3.setBounds(40, 103, 105, 17);
		StockLocationAdd.add(lblLastname_3);
		
		textLocationName = new JTextField();
		textLocationName.setColumns(10);
		textLocationName.setBounds(40, 119, 321, 21);
		StockLocationAdd.add(textLocationName);
		
		JButton btnAddLocation = new JButton("Add Location");
		btnAddLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveLocation();
			}
		});
		btnAddLocation.setForeground(Color.WHITE);
		btnAddLocation.setBackground(new Color(0, 204, 0));
		btnAddLocation.setBounds(40, 188, 143, 27);
		StockLocationAdd.add(btnAddLocation);
		
		JButton btnCancelLocationAdd = new JButton("Cancel");
		btnCancelLocationAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPages(locations);
			}
		});
		btnCancelLocationAdd.setForeground(Color.WHITE);
		btnCancelLocationAdd.setBackground(Color.RED);
		btnCancelLocationAdd.setBounds(195, 188, 105, 27);
		StockLocationAdd.add(btnCancelLocationAdd);
		
		JPanel StockLocationEdit = new JPanel();
		layeredPane.setLayer(StockLocationEdit, 1);
		StockLocationEdit.setLayout(null);
		StockLocationEdit.setBackground(Color.WHITE);
		StockLocationEdit.setBounds(0, 0, 1024, 567);
		layeredPane.add(StockLocationEdit);
		
		JLabel lblStockLocations_1_1 = new JLabel("Stock Locations  / Add");
		lblStockLocations_1_1.setFont(new Font("Roboto", Font.BOLD, 19));
		lblStockLocations_1_1.setBounds(40, 0, 198, 25);
		StockLocationEdit.add(lblStockLocations_1_1);
		
		JLabel lblFirstname_3_1 = new JLabel("Location Code");
		lblFirstname_3_1.setBounds(40, 52, 105, 17);
		StockLocationEdit.add(lblFirstname_3_1);
		
		textLocationEcode = new JTextField();
		textLocationEcode.setColumns(10);
		textLocationEcode.setBounds(40, 70, 321, 21);
		StockLocationEdit.add(textLocationEcode);
		
		JLabel lblLastname_3_1 = new JLabel("Location Name");
		lblLastname_3_1.setBounds(40, 103, 105, 17);
		StockLocationEdit.add(lblLastname_3_1);
		
		textLocationEname = new JTextField();
		textLocationEname.setColumns(10);
		textLocationEname.setBounds(40, 119, 321, 21);
		StockLocationEdit.add(textLocationEname);
		
		JButton btnUpdateLocation = new JButton("Update");
		btnUpdateLocation.setForeground(Color.WHITE);
		btnUpdateLocation.setBackground(new Color(0, 204, 0));
		btnUpdateLocation.setBounds(40, 188, 143, 27);
		StockLocationEdit.add(btnUpdateLocation);
		
		JButton btnCancelLocationEdit = new JButton("Cancel");
		btnCancelLocationEdit.setForeground(Color.WHITE);
		btnCancelLocationEdit.setBackground(Color.RED);
		btnCancelLocationEdit.setBounds(195, 188, 105, 27);
		StockLocationEdit.add(btnCancelLocationEdit);
		
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
	
	
	// stock locations works
	public void saveLocation() {
		try {
			String query = "INSERT INTO `storage_locations`(`location_code`,`location_name`) "
					+ "VALUES ('"+this.textLocationCode.getText()+"', '"+this.textLocationName.getText()+"');";
			// empty fields
			this.textLocationCode.setText("");
			this.textLocationName.setText("");
			
			st.execute(query);
			st.close();
			writeStockLocationData();
			JOptionPane.showMessageDialog(null, "Stock location created.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeStockLocationData() {
		try {
			String query = "SELECT * FROM storage_locations";
			st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			this.modelStock.setRowCount(0);
			while (rs.next()) {
				String location_code = rs.getString("location_code");
				String location_name = rs.getString("location_name");
				String id = String.valueOf(rs.getInt("location_id"));
				
				String[] tblData = {id, location_code, location_name};
				
				this.modelStock.addRow(tblData);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// supplier works
 	public void updateSuplier(int id) {
		try {
			String query = "UPDATE `supliers` SET `first_name`='"+this.textSuplierEfirstname.getText()+"', `last_name`='"+this.textSuplierElastname.getText()+"',`address_line1`='"+this.textSuplierEaddressline1.getText()+"',`address_line2`='"+this.textSuplierEaddressline2.getText()+"',`zip` = "+this.textSuplierEzip.getText()+",`town`='"+this.textSuplierEcity.getText()+"',`country`='"+this.textSuplierEcountry.getText()+"',`email`='"+this.textSuplierEemail.getText()+"',`phone`='"+this.textSuplierEmobile.getText()+"',`office`='"+this.textSuplierEoffice.getText()+"' WHERE `customers`.`id` = "+id+";"; 
			st.execute(query);
			writeSuplierDetails();
			JOptionPane.showMessageDialog(null, "Supplier details updated.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editSuplier(int id) {
		try {
			
			String query = "SELECT * FROM supliers WHERE id="+id+"";
			st = con.getConnection().createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				this.textSuplierEfirstname.setText(rs.getString("first_name"));
				this.textSuplierElastname.setText(rs.getString("last_name"));
				this.textSuplierEaddressline1.setText(rs.getString("address_line1"));
				this.textSuplierEaddressline2.setText(rs.getString("address_line2"));
				this.textSuplierEzip.setText(String.valueOf(rs.getInt("zip")));
				this.textSuplierEcity.setText(rs.getString("town"));
				this.textSuplierEcountry.setText(rs.getString("country"));
				this.textSuplierEemail.setText(rs.getString("email"));
				this.textSuplierEmobile.setText(rs.getString("phone"));
				this.textSuplierEoffice.setText(rs.getString("office"));
				switchPages(suplierEdit);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeSuplier(int id) {
		try {
			String query = "DELETE FROM supliers WHERE id="+id+"";
			st.execute(query);
			st.close();
			writeSuplierDetails();
			JOptionPane.showMessageDialog(null, "Successfully removed.");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void saveSuplier() {
		try {
			String query = "INSERT INTO `supliers`(`first_name`,`last_name`,`address_line1`,`address_line2`,`zip`,`town`,`country`,`email`,`phone`,`office`) "
					+ "VALUES ('"+this.textSuplierFirstname.getText()+"', '"+this.textSuplierLastname.getText()+"', '"+this.textSuplierAddressline1.getText()+"', '"+this.textSuplierAddressline2.getText()+"', '"+this.textSuplierZip.getText()+"', '"+this.textSuplierCity.getText()+"', '"+this.textSuplierCountry.getText()+"', '"+this.textSuplierEmail.getText()+"', '"+this.textSuplierPhone.getText()+"', '"+this.textSuplierOffice.getText()+"');";
			// empty fields
			this.textSuplierFirstname.setText("");
			this.textSuplierLastname.setText("");
			this.textSuplierAddressline1.setText("");
			this.textSuplierAddressline2.setText("");
			this.textSuplierZip.setText("");
			this.textSuplierCountry.setText("");
			this.textSuplierCity.setText("");
			this.textSuplierEmail.setText("");
			this.textSuplierPhone.setText("");
			this.textSuplierOffice.setText("");
			
			st.execute(query);
			st.close();
			writeSuplierDetails();
			JOptionPane.showMessageDialog(null, "Supplier details created.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeSuplierDetails() {
		try {
			String query = "SELECT * FROM supliers";
			st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			this.modelSuplier.setRowCount(0);
			while (rs.next()) {
				String name = rs.getString("first_name") + " " + rs.getString("last_name");
				String city = rs.getString("town");
				String email = rs.getString("email");
				String contact = rs.getString("phone");
				String id = String.valueOf(rs.getInt("id"));
				
				String[] tblData = {id, name,city,email,contact};
				
				this.modelSuplier.addRow(tblData);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// customer works	
	public void updateCustomer(int id) {
		try {
			String query = "UPDATE `customers` SET `first_name`='"+this.textCustomerEfirstname.getText()+"', `last_name`='"+this.textCustomerElastname.getText()+"',`address_line1`='"+this.textCustomerEaddressline1.getText()+"',`address_line2`='"+this.textCustomerEaddressline2.getText()+"',`zip` = "+this.textCustomerEzip.getText()+",`town`='"+this.textCustomerEcity.getText()+"',`country`='"+this.textCustomerEcountry.getText()+"',`email`='"+this.textCustomerEemail.getText()+"',`phone`='"+this.textCustomerEmobile.getText()+"',`office`='"+this.textCustomerEofficel.getText()+"' WHERE `customers`.`id` = "+id+";"; 
			st.execute(query);
			writeCustomerDetails();
			JOptionPane.showMessageDialog(null, "Customer details updated.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editCustomer(int id) {
		try {
			
			String query = "SELECT * FROM customers WHERE id="+id+"";
			st = con.getConnection().createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				this.textCustomerEfirstname.setText(rs.getString("first_name"));
				this.textCustomerElastname.setText(rs.getString("last_name"));
				this.textCustomerEaddressline1.setText(rs.getString("address_line1"));
				this.textCustomerEaddressline2.setText(rs.getString("address_line2"));
				this.textCustomerEzip.setText(String.valueOf(rs.getInt("zip")));
				this.textCustomerEcity.setText(rs.getString("town"));
				this.textCustomerEcountry.setText(rs.getString("country"));
				this.textCustomerEemail.setText(rs.getString("email"));
				this.textCustomerEmobile.setText(rs.getString("phone"));
				this.textCustomerEofficel.setText(rs.getString("office"));
				switchPages(CustomerEdit);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeCustomer(int id) {

		try {
			String query = "DELETE FROM customers WHERE id="+id+"";
			st.execute(query);
			st.close();
			writeCustomerDetails();
			JOptionPane.showMessageDialog(null, "Successfully removed.");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		System.out.println(id);
	}
	
	public void saveCustomer() {
		try {
			String query = "INSERT INTO `customers`(`first_name`,`last_name`,`address_line1`,`address_line2`,`zip`,`town`,`country`,`email`,`phone`,`office`) "
					+ "VALUES ('"+this.textCustomerFirstName.getText()+"', '"+this.textCustomerLastName.getText()+"', '"+this.txtCustomerAddressLine1.getText()+"', '"+this.textCustomerAddressLine2.getText()+"', '"+this.textCustomerZip.getText()+"', '"+this.textCustomerCity.getText()+"', '"+this.textCustomerCountry.getText()+"', '"+this.textCustomerEmail.getText()+"', '"+this.textCustomerMobile.getText()+"', '"+this.textCustomerOffice.getText()+"');";
			// empty fields
			this.textCustomerFirstName.setText("");
			this.textCustomerLastName.setText("");
			this.txtCustomerAddressLine1.setText("");
			this.textCustomerAddressLine2.setText("");
			this.textCustomerZip.setText("");
			this.textCustomerCity.setText("");
			this.textCustomerCountry.setText("");
			this.textCustomerEmail.setText("");
			this.textCustomerMobile.setText("");
			this.textCustomerOffice.setText("");
			
			st.execute(query);
			st.close();
			writeCustomerDetails();
			JOptionPane.showMessageDialog(null, "Customer details created.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeCustomerDetails() {		
		try {
			String query = "SELECT * FROM customers";
			st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			this.modelCustomer.setRowCount(0);
			while (rs.next()) {
				String name = rs.getString("first_name") + " " + rs.getString("last_name");
				String city = rs.getString("town");
				String email = rs.getString("email");
				String contact = rs.getString("phone");
				String id = String.valueOf(rs.getInt("id"));
				
				String[] tblData = {id, name,city,email,contact};
				
				this.modelCustomer.addRow(tblData);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// switch between pages
	public void switchPages(JPanel panel) {
		this.layeredPane.removeAll();
		this.layeredPane.add(panel);
		this.layeredPane.repaint();
		this.layeredPane.revalidate();
	}
	
	
	// drawer opening and close
	public void openCloseDrawer() {
		if (drawer.isShow()) {
			drawer.hide();
		}
		else {
			drawer.show();
		}
	}
	
	// main table execution
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
