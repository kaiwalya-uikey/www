package dbms;

import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class dbms {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    dbms window = new dbms();
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
    public dbms() {
        initialize();
        Connect();
    }

    Connection con;
    PreparedStatement pst; // this 2 classes are coming from sql namespace import java.sql.*;
    ResultSet rs;
    private JTable scrollPane_2;
    private JButton INSERT;

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:8080/Javacrud", "root", "");

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }

    /**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1287, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ename");
		lblNewLabel.setBounds(122, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("age");
		lblNewLabel_1.setBounds(224, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("salary");
		lblNewLabel_2.setBounds(344, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("cid");
		lblNewLabel_3.setBounds(472, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("cname");
		lblNewLabel_4.setBounds(596, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("startdate");
		lblNewLabel_5.setBounds(748, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(102, 129, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(198, 129, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(312, 129, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(432, 129, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(572, 129, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(729, 129, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		INSERT = new JButton("INSERT");
		INSERT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
String ename , age , salary , cid , cname , startdate;
				
				ename= textField.getText();
				age= textField_1.getText();
				salary= textField_2.getText();
				cid= textField_3.getText();
				cname= textField_4.getText();
				startdate= textField_5.getText();
				
				try {
					pst = con.prepareStatement("insert into book(ename,age,salary,cid,cname,startdate)values(?,?,?,?,?,?)");
					            pst.setString(1, ename);
					            pst.setString(2, age);
					            pst.setString(3, salary);
					            pst.setString(4, cid);
					            pst.setString(5, cname);
					            pst.setString(6, startdate);
					            pst.executeUpdate();
					            JOptionPane.showMessageDialog(null, "Record Added!!!!!");
					            table_load(); // this will refresh the tableload() function above 
					          
					            textField.setText("");
					            textField_1.setText("");
					            textField_2.setText("");
					            textField.requestFocus(); //when we save the record the box should be cleared for inserting new ones
				}
				catch (SQLException el) {
					el.printStackTrace();
				}
			}
		
			  public void table_load()
			    {
			    	try 
			    	 {
				    pst = con.prepareStatement("select * from book");
				    rs = pst.executeQuery(); // rs = resultset object ,,, after calling pstexecuteQuery all the query will be loaded into resultset object
				    scrollPane_2.setModel(DbUtils.resultSetToTableModel(rs));
				} 
			    	catch (SQLException e) 
			    	 {
			    		e.printStackTrace();
				  }
			    
			}
			}
		);
		
			
			    
			
		JButton btnNewButton_1 = new JButton("SEARCH");
		btnNewButton_1.setBounds(432, 171, 252, 65);
		frame.getContentPane().add(btnNewButton_1);
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
			            String id = textField.getText();// whatever we will type in BOOK_ID it will be assigned to id variable

			                pst = con.prepareStatement("select ename,age,salary,cid,cname,startdate from book where id = ?"); // the id will go to ? mark here
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String ename = rs.getString(1); // getString1 means name
			                String age = rs.getString(2);// getString2 means edition
			                
			                String salary = rs.getString(3);// getString3 means price
			                String cid = rs.getString(4);
			                		 String cname = rs.getString(5);
			                				 String startdate = rs.getString(6);
			                
			                textField.setText(ename);
							 textField_1.setText(age);
						 textField_2.setText(salary);
							 textField_3.setText(cid);
							 textField_4.setText(cname);
							 textField_5.setText(startdate); // all the assigned value 
			                
			                
			                
			            }   
			            else
			            {
			            	 textField.setText("");
							 textField_1.setText("");
						 textField_2.setText("");
							 textField_3.setText("");
							 textField_4.setText("");
							 textField_5.setText("");
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
				
				
				
				
			}
		});
		
		
		textField_6 = new JTextField();
		textField_6.setBounds(187, 247, 753, 65);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(155, 333, 850, 200);
		frame.getContentPane().add(lblNewLabel_6);
		
		JButton btnNewButton_2 = new JButton("EDIT");
		btnNewButton_2.addActionListener(new ActionListener() {
		//public void actionPerformed(ActionEvent e) {
			
			
			String ename , age , salary , cid , cname , startdate;
			
			
			ename= textField.getText();
			age= textField_1.getText();
			salary= textField_2.getText();
			cid= textField_3.getText();
			cname= textField_4.getText();
			startdate= textField_5.getText();
			 try {
					pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");//whatever will be in BOOK_ID will be assigned to txtbid and then here in place of ?
					pst.setString(1, ename);
		            pst.setString(2, age);
		            pst.setString(3, salary);
		            pst.setString(4, cid);
		            pst.setString(5, cname);
		            pst.setString(6, startdate);


		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
		            table_load();
		           
		            txtbname.setText("");
		            txtedition.setText("");
		            txtprice.setText("");
		            txtbname.requestFocus();
				}

	            catch (SQLException e1) {
					
					e1.printStackTrace();
				}

		
	;	
		
		JButton btnNewButton_3 = new JButton("DELETE");
		btnNewButton_3.addActionListener(new ActionListener() {
		//frame.getContentPane().add(btnNewButton_3);
		public void actionPerformed(ActionEvent e) {
            String bid;
                 bid  = textField.getText();

 try {
		pst = con.prepareStatement("delete from book where id =?");

        pst.setString(1, bid);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
        table_load();
       
        textField.setText("");
		 textField_1.setText("");
	 textField_2.setText("");
		 textField_3.setText("");
		 textField_4.setText("");
		 textField_5.setText("");
	}

    catch (SQLException e1) {
		
		e1.printStackTrace();
	}
		
	;)}
		}

    private void table_load1() {
		// TODO Auto-generated method stub
		
	}
