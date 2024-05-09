import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Statement;


public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldUserName;
	private JPasswordField passwordField;


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public Login() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Stock Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(new Color(0, 0, 102));
		panelLeft.setBounds(0, 0, 218, 382);
		contentPane.add(panelLeft);
		
		JLabel lblLogIn = new JLabel("LOG IN");
		lblLogIn.setForeground(new Color(0, 0, 102));
		lblLogIn.setFont(new Font("Roboto Black", Font.BOLD, 38));
		lblLogIn.setBounds(252, 47, 247, 47);
		contentPane.add(lblLogIn);
		
		JLabel lblHedingText = new JLabel("Hello ! Welcome Back.Plaese log in to use your Inventory");
		lblHedingText.setVerticalAlignment(SwingConstants.TOP);
		lblHedingText.setFont(new Font("Open Sans", Font.PLAIN, 13));
		lblHedingText.setBounds(252, 86, 474, 18);
		contentPane.add(lblHedingText);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(new Color(0, 0, 102));
		lblUserName.setBounds(252, 131, 132, 17);
		contentPane.add(lblUserName);
		
		fieldUserName = new JTextField();
		fieldUserName.setBounds(252, 147, 366, 30);
		contentPane.add(fieldUserName);
		fieldUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 0, 102));
		lblPassword.setBounds(252, 189, 78, 17);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(252, 205, 366, 30);
		contentPane.add(passwordField);
		
		JCheckBox chckbxKeepMeLogged = new JCheckBox("  Keep me logged in");
		chckbxKeepMeLogged.setFont(new Font("SansSerif", Font.PLAIN, 12));
		chckbxKeepMeLogged.setForeground(new Color(0, 0, 102));
		chckbxKeepMeLogged.setBackground(new Color(255, 255, 255));
		chckbxKeepMeLogged.setBounds(252, 243, 159, 25);
		contentPane.add(chckbxKeepMeLogged);
		
		JButton btnLogin = new JButton("LOG IN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {login();}
		});
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 0, 102));
		btnLogin.setBounds(252, 292, 366, 47);
		contentPane.add(btnLogin);
		
		JLabel lblManagementSystem = new JLabel("Management System.");
		lblManagementSystem.setVerticalAlignment(SwingConstants.TOP);
		lblManagementSystem.setFont(new Font("Open Sans", Font.PLAIN, 13));
		lblManagementSystem.setBounds(252, 101, 474, 18);
		contentPane.add(lblManagementSystem);
	}
	
	public void login() {
		DbConnection con = new DbConnection();
		try {			
			Statement st = con.getConnection().createStatement();
			
			String query = "CREATE TABLE " + this.fieldUserName.getText() + " (id INT PRIMARY KEY AUTO_INCREMENT,"
					+ "name VARCHAR(255))";
			st.execute(query);
			st.close();
			con.getConnection().close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
