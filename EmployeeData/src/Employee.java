import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
public class Employee {

	private JFrame frame;
	private JTextField jtxtEmployeeID;
	private JTable table;
	private JTextField jtxtNINumber;
	private JTextField jtxtFirstname;
	private JTextField jtxtSurname;
	private JTextField jtxtGender;
	private JTextField jtxtDOB;
	private JTextField jtxtAge;
	private JTextField jtxtSalary;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null; 
	
	DefaultTableModel model = new DefaultTableModel();
	
	public void updateTable()
	{
		conn = EmployeeData.ConnectDB();
		if (conn != null)
		{
			String sql = "Select EmpID, NINumber, Firstname, Surname, Gender, DOB, Age, Salary";
		
		try
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Object[] columnDate = new Object[8];
			
			while(rs.next())
			{
				columnDate [0] = rs.getString("EmpID");
				columnDate [1] = rs.getString("NINumber");
				columnDate [2] = rs.getString("Firstname");
				columnDate [3] = rs.getString("Surname");
				columnDate [4] = rs.getString("Gender");
				columnDate [5] = rs.getString("DOB");
				columnDate [6] = rs.getString("Age");
				columnDate [7] = rs.getString("Salary");
				
				model.addRow(columnDate);
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Employee() {
		initialize();
		
		conn = EmployeeData.ConnectDB();
		Object col[] = {"EmpID","NINumber","Firstname","Surname","Gender","DOB","Age","Salary"};
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0,0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Employee ID");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(42, 50, 153, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		jtxtEmployeeID = new JTextField();
		jtxtEmployeeID.setFont(new Font("Dialog", Font.BOLD, 18));
		jtxtEmployeeID.setBounds(244, 49, 153, 36);
		frame.getContentPane().add(jtxtEmployeeID);
		jtxtEmployeeID.setColumns(10);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sql = "INSERT INTO emplyee(EmpID,NINumber,Firstname,"
						+ "Surname,Gender,DOB,Age,Salary)VALUES(?,?,?,?,?,?,?,?)";
				
				try
				{
					pst = conn.prepareStatement(sql);
					pst.setString(1, jtxtEmployeeID.getText());
					pst.setString(1, jtxtNINumber.getText());
					pst.setString(1, jtxtFirstname.getText());
					pst.setString(1, jtxtSurname.getText());
					pst.setString(1, jtxtGender.getText());
					pst.setString(1, jtxtDOB.getText());
					pst.setString(1, jtxtAge.getText());
					pst.setString(1, jtxtSalary.getText());
					
					pst.execute();
					
					rs.close();
					pst.close();

				}catch(Exception ev)
				{
					JOptionPane.showMessageDialog(null, "System updated completed");
				}
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						jtxtEmployeeID.getText(),
						jtxtNINumber.getText(),
						jtxtFirstname.getText(),
						jtxtSurname.getText(),
						jtxtGender.getText(),
						jtxtDOB.getText(),
						jtxtAge.getText(),
						jtxtSalary.getText()
				});
				if(table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, 
								"Membership update confirmed",
								"Employee Database System",
								JOptionPane.OK_OPTION);
					}
				}
			}
		});
		btnAddNew.setFont(new Font("Dialog", Font.BOLD, 18));
		btnAddNew.setBounds(72, 502, 168, 33);
		frame.getContentPane().add(btnAddNew);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(471, 80, 734, 397);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EmpID", "NINumber", "Firstname", "Surname", "Gender", "DOB", "Age", "Salary"
			}
		));
		table.setFont(new Font("Dialog", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1_1 = new JLabel("NI Number");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(42, 98, 153, 35);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		jtxtNINumber = new JTextField();
		jtxtNINumber.setFont(new Font("Dialog", Font.BOLD, 18));
		jtxtNINumber.setColumns(10);
		jtxtNINumber.setBounds(244, 97, 153, 36);
		frame.getContentPane().add(jtxtNINumber);
		
		JLabel lblNewLabel_1_2 = new JLabel("Firstname");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(42, 146, 153, 35);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		jtxtFirstname = new JTextField();
		jtxtFirstname.setFont(new Font("Dialog", Font.BOLD, 18));
		jtxtFirstname.setColumns(10);
		jtxtFirstname.setBounds(244, 145, 153, 36);
		frame.getContentPane().add(jtxtFirstname);
		
		JLabel lblNewLabel_1_3 = new JLabel("Surname");
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(42, 194, 153, 35);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		jtxtSurname = new JTextField();
		jtxtSurname.setFont(new Font("Dialog", Font.BOLD, 18));
		jtxtSurname.setColumns(10);
		jtxtSurname.setBounds(244, 193, 153, 36);
		frame.getContentPane().add(jtxtSurname);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Gender");
		lblNewLabel_1_3_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_3_1.setBounds(42, 243, 153, 35);
		frame.getContentPane().add(lblNewLabel_1_3_1);
		
		jtxtGender = new JTextField();
		jtxtGender.setFont(new Font("Dialog", Font.BOLD, 18));
		jtxtGender.setColumns(10);
		jtxtGender.setBounds(244, 242, 153, 36);
		frame.getContentPane().add(jtxtGender);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("DOB");
		lblNewLabel_1_3_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_3_2.setBounds(42, 291, 153, 35);
		frame.getContentPane().add(lblNewLabel_1_3_2);
		
		jtxtDOB = new JTextField();
		jtxtDOB.setFont(new Font("Dialog", Font.BOLD, 18));
		jtxtDOB.setColumns(10);
		jtxtDOB.setBounds(244, 290, 153, 36);
		frame.getContentPane().add(jtxtDOB);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("Age");
		lblNewLabel_1_3_3.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_3_3.setBounds(42, 348, 153, 35);
		frame.getContentPane().add(lblNewLabel_1_3_3);
		
		jtxtAge = new JTextField();
		jtxtAge.setFont(new Font("Dialog", Font.BOLD, 18));
		jtxtAge.setColumns(10);
		jtxtAge.setBounds(244, 347, 153, 36);
		frame.getContentPane().add(jtxtAge);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				MessageFormat header = new MessageFormat("Printing in Progress");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");
				
				try 
				{
					table.print();
				}catch(java.awt.print.PrinterException ev)
				{
					System.err.format("No Printer Found", ev.getMessage());
				}

			}
		});
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPrint.setBounds(269, 502, 168, 33);
		frame.getContentPane().add(btnPrint);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtxtEmployeeID.setText(null);
				jtxtNINumber.setText(null);
				jtxtFirstname.setText(null);
				jtxtSurname.setText(null);
				jtxtAge.setText(null);
				jtxtGender.setText(null);
				jtxtDOB.setText(null);
				jtxtSalary.setText(null);
				
			}
		});
		btnReset.setFont(new Font("Dialog", Font.BOLD, 18));
		btnReset.setBounds(473, 502, 168, 33);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", 
						"Employee Database System", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Dialog", Font.BOLD, 18));
		btnExit.setBounds(674, 502, 168, 33);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel_1_3_3_1 = new JLabel("Salary");
		lblNewLabel_1_3_3_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_3_3_1.setBounds(42, 396, 153, 35);
		frame.getContentPane().add(lblNewLabel_1_3_3_1);
		
		jtxtSalary = new JTextField();
		jtxtSalary.setFont(new Font("Dialog", Font.BOLD, 18));
		jtxtSalary.setColumns(10);
		jtxtSalary.setBounds(244, 395, 153, 36);
		frame.getContentPane().add(jtxtSalary);
		
		JLabel lblNewLabel = new JLabel("Employee Database Management System");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel.setBounds(297, 11, 805, 26);
		frame.getContentPane().add(lblNewLabel);
	}
}
