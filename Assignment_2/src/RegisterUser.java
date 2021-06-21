import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private DefaultTableModel dm, dm1, dm2;
	private DBConnection jDbConnection;
	private JTextField textField_7;

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
					RegisterUser frame = new RegisterUser();
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
	public RegisterUser() {
		
		jDbConnection=DBConnection.getInstance();
		dm = new DefaultTableModel();
		dm1 = new DefaultTableModel();
		dm2 = new DefaultTableModel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 424);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Main Page");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new MainPage().setVisible(true);
			}
		});
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 204));
		panel.setBounds(10, 0, 607, 352);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration ID");
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel.setBounds(10, 11, 105, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(10, 122, 86, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(10, 222, 121, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(23, 273, 73, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("E-Mail");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_4.setBounds(345, 172, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("UserName");
		lblNewLabel_5.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_5.setBounds(10, 77, 86, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Last Name");
		lblNewLabel_6.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_6.setBounds(330, 122, 86, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Gender");
		lblNewLabel_7.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_7.setBounds(10, 172, 65, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("DOB");
		lblNewLabel_8.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_8.setBounds(345, 222, 46, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Collage Address");
		lblNewLabel_9.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_9.setBounds(336, 273, 115, 14);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Password");
		lblNewLabel_10.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_10.setBounds(330, 77, 86, 14);
		panel.add(lblNewLabel_10);
		
		textField = new JTextField();
		textField.setBounds(157, 8, 136, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(157, 75, 136, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(157, 120, 136, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(157, 173, 46, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(157, 219, 136, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(157, 271, 136, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(474, 75, 105, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(474, 170, 105, 20);
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(474, 219, 105, 20);
		panel.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(474, 271, 105, 20);
		panel.add(textField_10);
		textField_10.setColumns(10);
		
		JButton btnNewButton = new JButton("Clear All");
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_8.setText("");
				textField_9.setText("");
				textField_10.setText("");
			}
		});
		btnNewButton.setBounds(42, 318, 105, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Confirm");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringQuery="insert into userdetails values('";
				stringQuery+=textField_1.getText()+"','";
				stringQuery+=textField_6.getText()+"', '";
				stringQuery+=textField_2.getText()+"',' ";
				stringQuery+=textField_7.getText()+"',' ";
				stringQuery+=textField_3.getText()+"',' ";
				stringQuery+=textField_8.getText()+"', ";
				stringQuery+=textField_4.getText()+", '";
				stringQuery+=textField_9.getText()+"', ";
				stringQuery+=textField.getText()+", '";
				stringQuery+=textField_5.getText()+"', '";
				stringQuery+=textField_10.getText()+"')";
				//stringQuery+=")";
				String res=jDbConnection.executeAlter(stringQuery);
				if(res=="1")
				{
					JOptionPane.showMessageDialog(null,res);
				}else
				{
					JOptionPane.showMessageDialog(null,res);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_2.setBounds(475, 318, 104, 23);
		panel.add(btnNewButton_2);
		
		textField_7 = new JTextField();
		textField_7.setBounds(474, 120, 105, 20);
		panel.add(textField_7);
		textField_7.setColumns(10);
	}

}
