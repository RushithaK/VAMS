import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUseName;
	private DefaultTableModel dm, dm1, dm2;
	private DBConnection jDbConnection;
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
					UserLogin frame = new UserLogin();
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
	public UserLogin() {
		
		jDbConnection=DBConnection.getInstance();
		dm = new DefaultTableModel();
		dm1 = new DefaultTableModel();
		dm2 = new DefaultTableModel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("USER LOGIN");
		lblLogin.setIcon(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\VAMS\\Images\\icons8-login-24.png"));
		lblLogin.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblLogin.setBounds(235, 11, 158, 49);
		contentPane.add(lblLogin);
		
		txtUseName = new JTextField();
		txtUseName.setBounds(313, 121, 221, 29);
		contentPane.add(txtUseName);
		txtUseName.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user=txtUseName.getText();
				String pass=txtPassword.getText();
			}
		});
		btnLogin.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnLogin.setBounds(70, 318, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new MainPage().setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNewButton_2.setBounds(427, 318, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(205, 92, 92));
		separator.setBounds(49, 284, 516, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(205, 92, 92));
		separator_1.setBounds(49, 71, 510, 2);
		contentPane.add(separator_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(188, 143, 143));
		panel.setBounds(49, 84, 516, 189);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(268, 124, 218, 26);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblUserName = new JLabel("");
		lblUserName.setBounds(100, 11, 155, 81);
		panel.add(lblUserName);
		lblUserName.setIcon(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\VAMS\\Images\\icons8-user-50.png"));
		lblUserName.setFont(new Font("Arial", Font.ITALIC, 13));
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setBounds(100, 103, 89, 57);
		panel.add(lblPassword);
		lblPassword.setIcon(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\VAMS\\Images\\icons8-password-50.png"));
		lblPassword.setFont(new Font("Arial", Font.ITALIC, 13));
	}

}
