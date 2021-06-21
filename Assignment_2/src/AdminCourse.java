import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminCourse extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField txtCNo;
	private JTextField txtCName;
	private JTable table_1;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private DefaultTableModel dm, dm1, dm2;
	private DBConnection jDbConnection;
	private JTable table_2;
	private JTextField textField_8;
	private JTextField textField_9;

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
					AdminCourse frame = new AdminCourse();
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
	public AdminCourse() {
		
		jDbConnection=DBConnection.getInstance();
		dm = new DefaultTableModel();
		dm1 = new DefaultTableModel();
		dm2 = new DefaultTableModel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 424);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(240, 230, 140));
		setJMenuBar(menuBar);
		
		JMenu mnMain = new JMenu("Main");
		mnMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new MainPage().setVisible(true);
			}
		});
		menuBar.add(mnMain);
		
		JMenu mnNewMenu_1 = new JMenu("");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnQuestions = new JMenu("Questions");
		mnQuestions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new AdminQuestions().setVisible(true);
			}
		});
		menuBar.add(mnQuestions);
		
		JMenu mnNewMenu = new JMenu("Exit");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new AdminMainPage().setVisible(true);
			}
		});
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 821, 341);
		contentPane.add(tabbedPane);
		tabbedPane.setBackground(new Color(255, 240, 245));
		
		JPanel panleDelete = new JPanel();
		panleDelete.setBackground(new Color(60, 179, 113));
		tabbedPane.addTab("Delete", null, panleDelete, null);
		panleDelete.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 222, 179));
		panel_1.setBounds(10, 11, 325, 291);
		panleDelete.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCNo1 = new JLabel("Course  No");
		lblCNo1.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblCNo1.setBounds(27, 63, 68, 14);
		panel_1.add(lblCNo1);
		
		txtCNo = new JTextField();
		txtCNo.setBounds(190, 60, 86, 20);
		panel_1.add(txtCNo);
		txtCNo.setColumns(10);
		
		JLabel lblCName = new JLabel("Course Name");
		lblCName.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblCName.setBounds(27, 132, 86, 14);
		panel_1.add(lblCName);
		
		txtCName = new JTextField();
		txtCName.setBounds(190, 132, 86, 20);
		panel_1.add(txtCName);
		txtCName.setColumns(10);
		
		JButton btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cno=txtCNo.getText();
					if(cno!="")
					{
						String res=jDbConnection.executeAlter("delete from course_details where course_no = "+cno);
						JOptionPane.showMessageDialog(null,"Successfully Deleted!");
					}else
					{
						JOptionPane.showMessageDialog(null, "Course Number cannot be empty!","Alert",JOptionPane.WARNING_MESSAGE);
					}
					
				}catch(Exception e1) {
					
				}
			}
		});
		btnDel.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnDel.setBounds(109, 176, 89, 23);
		panel_1.add(btnDel);
		
		JButton btnNewButton_4 = new JButton("Clear ");
		btnNewButton_4.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCNo.setText("");
				txtCName.setText("");
			}
		});
		btnNewButton_4.setBounds(6, 210, 89, 23);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Clear Table");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.setColumnCount(0);
				dm.setRowCount(0);
			}
		});
		btnNewButton_5.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnNewButton_5.setBounds(190, 210, 125, 23);
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("View");
		btnNewButton_6.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table_2,"SELECT * FROM COURSE_DETAILS WHERE COURSE_NO = '"+txtCNo.getText()+"'");
			}
		});
		btnNewButton_6.setBounds(6, 257, 89, 23);
		panel_1.add(btnNewButton_6);
		
		JButton btnNewButton_11 = new JButton("View All");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table_2,"SELECT * FROM COURSE_DETAILS ");
			}
		});
		btnNewButton_11.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnNewButton_11.setBounds(190, 258, 109, 23);
		panel_1.add(btnNewButton_11);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(345, 11, 461, 291);
		panleDelete.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		JPanel panelUpdate = new JPanel();
		panelUpdate.setBackground(new Color(147, 112, 219));
		tabbedPane.addTab("Update", null, panelUpdate, null);
		panelUpdate.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(216, 191, 216));
		panel_2.setBounds(10, 11, 306, 302);
		panelUpdate.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course no");
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel.setBounds(10, 51, 68, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Course Name");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(10, 10, 89, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date Of Exam");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(10, 100, 89, 14);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("No Of Ques");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(10, 142, 89, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Weightage");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblNewLabel_4.setBounds(10, 179, 68, 14);
		panel_2.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(173, 8, 86, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(173, 49, 86, 20);
		panel_2.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(173, 97, 86, 20);
		panel_2.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(173, 176, 86, 20);
		panel_2.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton_7 = new JButton("Modify");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cno=textField_5.getText();
				String cna=textField_3.getText();
				String date=textField_6.getText();
				String weight=textField_7.getText();
				String noofq=textField_9.getText();
				boolean flag=true;
				if(cno.equals("") || cna.equals("") || date.equals("") || weight.equals("") || noofq.equals(""))
				{
					flag=false;
				}
				if(flag==false)
				{
					if(JOptionPane.showConfirmDialog(null,"Are you sure? Some of the values are empty!")==JOptionPane.YES_OPTION)
					{
						
						String query="update course_details set course_name = '";
						query+=cna+"' ,date_of_creation = '";
						query+=date+"', total_question = ";
						query+=noofq+" ,question_mark = ";
						query+=weight+" where course_no = "+cno;
						String res=jDbConnection.executeAlter(query);
						if(res=="inserted 1 rows in table")
							JOptionPane.showMessageDialog(null,"Successfully Updated!");
						else
							JOptionPane.showMessageDialog(null, "Enter valid details!!","Alert",JOptionPane.WARNING_MESSAGE);
					}
				}else
				{
					String query="update course_details set course_name = '";
					query+=cna+"' ,date_of_creation = '";
					query+=date+"', total_question = ";
					query+=noofq+" ,question_mark = ";
					query+=weight+" where course_no = "+cno;
					String res=jDbConnection.executeAlter(query);
					if(res=="inserted 1 rows in table")
						JOptionPane.showMessageDialog(null,"Successfully Updated!");
					else
						JOptionPane.showMessageDialog(null, "Enter valid details!!","Alert",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnNewButton_7.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_7.setBounds(100, 207, 89, 23);
		panel_2.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Clear");
		btnNewButton_8.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setText("");
				textField_7.setText("");
				textField_5.setText("");
				textField_6.setText("");
			}
		});
		btnNewButton_8.setBounds(10, 242, 89, 23);
		panel_2.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Clear Table");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm1.setColumnCount(0);
				dm1.setRowCount(0);
			}
			
		});
		btnNewButton_9.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_9.setBounds(182, 242, 114, 23);
		panel_2.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("View");
		btnNewButton_10.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm1,table_1,"SELECT * FROM COURSE_DETAILS WHERE COURSE_NO = '"+textField_3.getText()+"'");
			}
		});
		btnNewButton_10.setBounds(10, 276, 89, 23);
		panel_2.add(btnNewButton_10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(173, 140, 86, 20);
		panel_2.add(textField_9);
		textField_9.setColumns(10);
		
		JButton btnNewButton_12 = new JButton("View All");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm1,table_1,"SELECT * FROM COURSE_DETAILS");
			}
		});
		btnNewButton_12.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_12.setBounds(192, 276, 89, 23);
		panel_2.add(btnNewButton_12);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(326, 11, 480, 302);
		panelUpdate.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JPanel panelInsert = new JPanel();
		panelInsert.setBackground(new Color(240, 128, 128));
		tabbedPane.addTab("Insert", null, panelInsert, null);
		panelInsert.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(119, 136, 153));
		panel.setBounds(0, 11, 316, 302);
		panelInsert.add(panel);
		panel.setLayout(null);
		
		JLabel lblCourseNo = new JLabel("CourseNo");
		lblCourseNo.setFont(new Font("Arial Black", Font.ITALIC, 13));
		lblCourseNo.setBounds(10, 67, 72, 14);
		panel.add(lblCourseNo);
		
		textField = new JTextField();
		textField.setBounds(167, 20, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setFont(new Font("Arial Black", Font.ITALIC, 13));
		lblCourseName.setBounds(10, 21, 100, 14);
		panel.add(lblCourseName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 66, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDOE = new JLabel("Date Of Exam");
		lblDOE.setFont(new Font("Arial Black", Font.ITALIC, 13));
		lblDOE.setBounds(10, 114, 100, 14);
		panel.add(lblDOE);
		
		textField_2 = new JTextField();
		textField_2.setBounds(167, 111, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNoOfQues = new JLabel("");
		lblNoOfQues.setBounds(10, 169, 46, 14);
		panel.add(lblNoOfQues);
		
		JLabel lblTotalQuestions = new JLabel("Weightage Of Each Ques");
		lblTotalQuestions.setFont(new Font("Arial Black", Font.ITALIC, 11));
		lblTotalQuestions.setBounds(10, 171, 157, 37);
		panel.add(lblTotalQuestions);
		
		textField_4 = new JTextField();
		textField_4.setBounds(167, 142, 86, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNOfQues = new JLabel("No Of Questions");
		lblNOfQues.setFont(new Font("Arial Black", Font.ITALIC, 13));
		lblNOfQues.setBounds(7, 144, 128, 14);
		panel.add(lblNOfQues);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringQuery="insert into course_details values('";
				stringQuery+=textField.getText()+"',";
				stringQuery+=textField_1.getText()+", '";
				stringQuery+=textField_2.getText()+"', ";
				stringQuery+=textField_4.getText()+", ";
				stringQuery+=textField_8.getText()+")";
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
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNewButton.setBounds(103, 211, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				
			}
		});
		btnNewButton_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNewButton_1.setBounds(10, 234, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear Table");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.setColumnCount(0);
				dm.setRowCount(0);
			}
		});
		btnNewButton_2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton_2.setBounds(193, 235, 113, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table,"SELECT * FROM COURSE_DETAILS WHERE COURSE_NO = '"+textField_1.getText()+"'");
				
			}
		});
		btnNewButton_3.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnNewButton_3.setBounds(10, 268, 89, 23);
		panel.add(btnNewButton_3);
		
		textField_8 = new JTextField();
		textField_8.setBounds(167, 180, 86, 20);
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnNewButton_13 = new JButton("View All");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(dm,table,"SELECT * FROM COURSE_DETAILS");
			}
		});
		btnNewButton_13.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton_13.setBounds(203, 269, 89, 23);
		panel.add(btnNewButton_13);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 11, 477, 302);
		panelInsert.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
