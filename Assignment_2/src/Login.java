import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private DefaultTableModel dm, dm1, dm2;
	private DBConnection jDbConnection;
	public static boolean isLoggedin=false;
	protected String username="admin";
	protected String password="admin";
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	void loadTable(DefaultTableModel dm, JTable table, String queryString) {
	    dm.setColumnCount(0);
	    dm.setRowCount(0);
	    ResultSet resultSet =  jDbConnection.executeQuery(queryString);
	        ResultSetMetaData rsmd;
	    try {
	      rsmd = resultSet.getMetaData();
	      //Coding to get columns-
	        int cols=rsmd.getColumnCount();
	        String c[]=new String[cols];
	        for(int i=0;i<cols;i++){
	            c[i]=rsmd.getColumnName(i+1);
	            dm.addColumn(c[i]);
	        }
	        //get data from rows
	        Object row[]=new Object[cols];
	        while(resultSet.next()){
	             for(int i=0;i<cols;i++){
	                    row[i]=resultSet.getString(i+1);
	                }
	            dm.addRow(row);
	        }
	        table.setModel(dm);
	    } catch (SQLException e1) {
	      // TODO Auto-generated catch block
	      e1.printStackTrace();
	    }
	  }
	
	
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

	/**
	 * Create the frame.
	 */
	public Login() {
		
		jDbConnection=DBConnection.getInstance();
		dm = new DefaultTableModel();
		dm1 = new DefaultTableModel();
		dm2 = new DefaultTableModel();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADMIN LOGIN");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\VAMS\\Images\\icons8-microsoft-admin-30.png"));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel.setBounds(215, 3, 166, 46);
		contentPane.add(lblNewLabel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUserName.getText().equals(username))
				{
					if(txtPassword.getText().equals(password))
					{
						isLoggedin = true;
						dispose();
						new AdminMainPage().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null,"Invalid Password!");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Invalid User Name!");
			}
			}
		});
		btnLogin.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnLogin.setBounds(72, 327, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainPage().setVisible(true);
			}
		});
		btnExit.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnExit.setBounds(453, 327, 89, 23);
		contentPane.add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 100, 0));
		separator.setBounds(46, 295, 516, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 0));
		separator_1.setBounds(46, 60, 516, 2);
		contentPane.add(separator_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(34, 73, 528, 211);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtPassword = new JTextField();
		txtPassword.setBackground(new Color(204, 255, 204));
		txtPassword.setBounds(268, 130, 184, 20);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblUserName = new JLabel("");
		lblUserName.setBounds(101, 22, 89, 40);
		panel.add(lblUserName);
		lblUserName.setIcon(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\VAMS\\Images\\icons8-user-50.png"));
		lblUserName.setFont(new Font("Arial", Font.ITALIC, 13));
		
		txtUserName = new JTextField();
		txtUserName.setBackground(new Color(204, 255, 204));
		txtUserName.setBounds(268, 42, 191, 20);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setBounds(101, 116, 112, 68);
		panel.add(lblPassword);
		lblPassword.setIcon(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\VAMS\\Images\\icons8-password-50.png"));
		lblPassword.setFont(new Font("Arial", Font.ITALIC, 13));
	}
}
