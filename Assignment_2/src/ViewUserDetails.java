import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewUserDetails extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private DefaultTableModel dm, dm1, dm2;
	private DBConnection jDbConnection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUserDetails frame = new ViewUserDetails();
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
	
	
	
	public ViewUserDetails() {
		 
		jDbConnection=DBConnection.getInstance();
		dm = new DefaultTableModel();
		dm1 = new DefaultTableModel();
		dm2 = new DefaultTableModel();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 424);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Exit");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new AdminMainPage().setVisible(true);
			}
		});
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Log Out");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Login.isLoggedin=false;
				dispose();
				new MainPage().setVisible(true);
			}
		});
		menuBar.add(mnNewMenu_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 153));
		panel.setBounds(10, 34, 745, 118);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel.setBounds(74, 22, 119, 36);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(214, 31, 119, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("VIEW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table,"SELECT * FROM USERDETAILS WHERE USERNAME = '"+textField.getText()+"'");
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNewButton.setBounds(74, 80, 92, 27);
		panel.add(btnNewButton);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		btnClear.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnClear.setBounds(214, 80, 92, 27);
		panel.add(btnClear);
		
		JButton btnClearTable = new JButton("CLEAR TABLE");
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.setColumnCount(0);
				dm.setRowCount(0);
			}
		});
		btnClearTable.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnClearTable.setBounds(594, 80, 141, 27);
		panel.add(btnClearTable);
		
		JButton btnNewButton_1 = new JButton("VIEW ALL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table,"SELECT * FROM USERDETAILS ");
			}
		});
		btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnNewButton_1.setBounds(413, 84, 119, 23);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 163, 745, 211);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("USER DETAILS");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(316, 11, 150, 22);
		contentPane.add(lblNewLabel_1);
	}
}
