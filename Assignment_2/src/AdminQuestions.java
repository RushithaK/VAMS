import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class AdminQuestions extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTable table_1;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTable table_2;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private DefaultTableModel dm, dm1, dm2;
	private DBConnection jDbConnection;

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
					AdminQuestions frame = new AdminQuestions();
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
	public AdminQuestions() {
		
		jDbConnection=DBConnection.getInstance();
		dm = new DefaultTableModel();
		dm1 = new DefaultTableModel();
		dm2 = new DefaultTableModel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 424);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(102, 204, 255));
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
		
		JMenu mnNewMenu_1 = new JMenu("Result");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Exit");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new AdminMainPage().setVisible(true);
			}
		});
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(102, 204, 153));
		tabbedPane.setBounds(0, 0, 606, 352);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 153));
		panel.setForeground(new Color(102, 255, 204));
		tabbedPane.addTab("Insert", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 204, 204));
		panel_3.setForeground(new Color(255, 204, 204));
		panel_3.setBounds(10, 11, 581, 217);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Q ID");
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel_3.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Q Statement");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(20, 177, 98, 14);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Opt1");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(10, 51, 46, 14);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Opt2");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(206, 51, 46, 14);
		panel_3.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(10, 76, 86, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 76, 86, 20);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Opt3");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_4.setBounds(10, 107, 46, 14);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Opt4");
		lblNewLabel_5.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_5.setBounds(206, 107, 46, 14);
		panel_3.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 132, 86, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(184, 132, 86, 20);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Correct Answer");
		lblNewLabel_6.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_6.setBounds(359, 11, 98, 14);
		panel_3.add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		textField_4.setBounds(485, 8, 86, 20);
		panel_3.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Course ID");
		lblNewLabel_7.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_7.setBounds(376, 51, 61, 14);
		panel_3.add(lblNewLabel_7);
		
		textField_5 = new JTextField();
		textField_5.setBounds(485, 43, 86, 20);
		panel_3.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(184, 8, 86, 20);
		panel_3.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(159, 170, 124, 31);
		panel_3.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringQuery="insert into questions values('";
				stringQuery+=textField_6.getText()+"','";
				stringQuery+=textField.getText()+"', '";
				stringQuery+=textField_1.getText()+"',' ";
				stringQuery+=textField_2.getText()+"',' ";
				stringQuery+=textField_3.getText()+"', ";
				stringQuery+=textField_4.getText()+", ";
				stringQuery+=textField_5.getText()+", '";
				stringQuery+=textField_7.getText()+"')";
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
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton.setBounds(346, 84, 89, 23);
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("View");
		btnNewButton_3.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table,"SELECT * FROM QUESTIONS WHERE Q_ID = '"+textField_6.getText()+"'");
			}
		});
		btnNewButton_3.setBounds(482, 84, 89, 23);
		panel_3.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Clear");
		btnNewButton_4.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
			}
		});
		btnNewButton_4.setBounds(346, 130, 89, 23);
		panel_3.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Clear Table");
		btnNewButton_5.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_5.setBounds(459, 132, 112, 23);
		panel_3.add(btnNewButton_5);
		
		JButton btnNewButton_12 = new JButton("View All");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table,"SELECT * FROM QUESTIONS");
			}
		});
		btnNewButton_12.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_12.setBounds(398, 174, 89, 23);
		panel_3.add(btnNewButton_12);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 239, 581, 74);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 102, 102));
		tabbedPane.addTab("Delete", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 204, 102));
		panel_4.setBounds(10, 11, 292, 302);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Course ID");
		lblNewLabel_8.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_8.setBounds(27, 28, 89, 14);
		panel_4.add(lblNewLabel_8);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Arial Black", Font.BOLD, 11));
		textField_8.setBounds(160, 25, 86, 20);
		panel_4.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Question ID");
		lblNewLabel_9.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_9.setBounds(27, 77, 78, 14);
		panel_4.add(lblNewLabel_9);
		
		textField_9 = new JTextField();
		textField_9.setBounds(160, 74, 86, 20);
		panel_4.add(textField_9);
		textField_9.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(27, 129, 89, 23);
		panel_4.add(btnNewButton_1);
		
		JButton btnNewButton_6 = new JButton("Clear");
		btnNewButton_6.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_8.setText("");
				textField_9.setText("");
			}
		});
		btnNewButton_6.setBounds(27, 197, 89, 23);
		panel_4.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Clear Table");
		btnNewButton_7.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_7.setBounds(172, 129, 110, 23);
		panel_4.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("View");
		btnNewButton_8.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table_1,"SELECT * FROM Questions WHERE Q_ID = '"+textField_9.getText()+"'");
			}
		});
		btnNewButton_8.setBounds(172, 197, 89, 23);
		panel_4.add(btnNewButton_8);
		
		JButton btnNewButton_13 = new JButton("View All");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table_1,"SELECT * FROM QUESTIONS");
			}
		});
		btnNewButton_13.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_13.setBounds(94, 246, 89, 23);
		panel_4.add(btnNewButton_13);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(312, 11, 279, 302);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(102, 102, 255));
		tabbedPane.addTab("Update", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(153, 204, 102));
		panel_5.setBounds(13, 11, 578, 202);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Q ID");
		lblNewLabel_10.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_10.setBounds(10, 11, 46, 14);
		panel_5.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Q Statement");
		lblNewLabel_11.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_11.setBounds(10, 49, 86, 14);
		panel_5.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Opt1");
		lblNewLabel_12.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_12.setBounds(10, 94, 46, 14);
		panel_5.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Opt 2");
		lblNewLabel_13.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_13.setBounds(192, 94, 46, 14);
		panel_5.add(lblNewLabel_13);
		
		textField_10 = new JTextField();
		textField_10.setBounds(152, 8, 86, 20);
		panel_5.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Opt3");
		lblNewLabel_14.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_14.setBounds(10, 148, 46, 14);
		panel_5.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Opt4");
		lblNewLabel_15.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_15.setBounds(192, 148, 46, 14);
		panel_5.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Correct Option");
		lblNewLabel_16.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_16.setBounds(302, 11, 110, 14);
		panel_5.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Course ID");
		lblNewLabel_17.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_17.setBounds(302, 49, 71, 14);
		panel_5.add(lblNewLabel_17);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qno=textField_10.getText();
				String op1=textField_12.getText();
				String op2=textField_13.getText();
				String op3=textField_14.getText();
				String op4=textField_15.getText();
				String crtop =textField_16.getText();
				String cid=textField_17.getText();
				String qst=textField_11.getText();
				boolean flag=true;
				if(qno.equals("") || op1.equals("") || op2.equals("") || op3.equals("") || op4.equals("") || crtop.equals("") || cid.equals("") || qst.equals(""))
				{
					flag=false;
				}
				if(flag==false)
				{
					if(JOptionPane.showConfirmDialog(null,"Are you sure? Some of the values are empty!")==JOptionPane.YES_OPTION)
					{
						
						String query="update questions set option_1 = '";
						query+=op1+"' ,option_2 = '";
						query+=op2+"', option_3 = '";
						query+=op3+"' , option_4 = '";
						query+=op4+"' , correct_option = ";
						query+=crtop+" , q_statement = '";
						query+=qst+"' where q_id = '"+qno+"'";
						String res=jDbConnection.executeAlter(query);
						if(res=="inserted 1 rows in table")
							JOptionPane.showMessageDialog(null,"Successfully Updated!");
						else
							JOptionPane.showMessageDialog(null, "Enter valid details!!","Alert",JOptionPane.WARNING_MESSAGE);
					}
				}else
				{

					String query="update questions set option_1 = '";
					query+=op1+"' ,option_2 = '";
					query+=op2+"', option_3 = '";
					query+=op3+"' , option_4 = '";
					query+=op4+"' , correct_option = ";
					query+=op3+" , course_no = ";
					query+=cid+" , q_statement = '";
					query+=qst+"' where q_id = '"+qno+"'";
					String res=jDbConnection.executeAlter(query);
					if(res=="inserted 1 rows in table")
						JOptionPane.showMessageDialog(null,"Successfully Updated!");
					else
						JOptionPane.showMessageDialog(null, "Enter valid details!!","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_2.setBounds(299, 116, 89, 23);
		panel_5.add(btnNewButton_2);
		
		textField_11 = new JTextField();
		textField_11.setBounds(132, 46, 125, 37);
		panel_5.add(textField_11);
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setBounds(10, 117, 86, 20);
		panel_5.add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setBounds(171, 119, 86, 20);
		panel_5.add(textField_13);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setBounds(10, 174, 86, 20);
		panel_5.add(textField_14);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setBounds(171, 174, 86, 20);
		panel_5.add(textField_15);
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setBounds(462, 8, 86, 20);
		panel_5.add(textField_16);
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		textField_17.setBounds(462, 46, 86, 20);
		panel_5.add(textField_17);
		textField_17.setColumns(10);
		
		JButton btnNewButton_9 = new JButton("Clear");
		btnNewButton_9.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_10.setText("");
				textField_11.setText("");
				textField_12.setText("");
				textField_13.setText("");
				textField_14.setText("");
				textField_15.setText("");
				textField_16.setText("");
				textField_17.setText("");
			}
		});
		btnNewButton_9.setBounds(459, 116, 89, 23);
		panel_5.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Clear Table");
		btnNewButton_10.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_10.setBounds(302, 173, 110, 23);
		panel_5.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("View");
		btnNewButton_11.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table_2,"SELECT * FROM Questions WHERE Q_ID = '"+textField_10.getText()+"'");
		
			}
		});
		btnNewButton_11.setBounds(462, 173, 89, 23);
		panel_5.add(btnNewButton_11);
		
		JButton btnNewButton_14 = new JButton("View All");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table_2,"SELECT * FROM QUESTIONS");
			}
		});
		btnNewButton_14.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_14.setBounds(374, 77, 89, 23);
		panel_5.add(btnNewButton_14);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(13, 219, 578, 83);
		panel_2.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
	}

}
