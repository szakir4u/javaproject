import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Customization implements ActionListener,ItemListener
{
	JFrame mf;
	Container c;
	JPanel p1;
	JLabel h1,footer;
	JTextField t1,t2,t3,hn1,hn2,hn3,hn4,hn5;
	Choice hostels;
	JButton b1,b2;
	static int stepNumber=1,configured=0;
	static int numOfHostels=0;
	String admin="admin";
	String dbName=" ",username=" ",password=" ";
	String hostel1,hostel2,hostel3,hostel4,hostel5;
	static int error=0;
	static int tablesCreated=0;
	
	public static void main(String ashdg[])
	{
		new Customization().customIt();
	}
	
	void customIt()
	{
			mf=new JFrame("Customization Pannel");
			c=mf.getContentPane();
			
			
			ImageIcon icon=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\logo.jpg");
			mf.setIconImage(icon.getImage());
			
			
			h1=new JLabel("CUSTOMIZATION PANNEL");
			h1.setBackground(Color.GREEN);
			h1.setForeground(Color.RED);
			h1.setFont(new Font("Times New Roman",Font.BOLD,27));
			h1.setBounds(20,-5,360,90);
			
			
			//t1 is for step 1 and is made invisible in remaining steps...
			t1=new JTextField("Enter Database Name Here");
			t1.setBackground(Color.YELLOW);
			t1.setForeground(Color.RED);
			t1.setFont(new Font("Times New Roman",Font.ITALIC,20));
			t1.setBounds(20,85,250,40);
			
			t2=new JTextField("Enter Database Username");
			t2.setBackground(Color.YELLOW);
			t2.setForeground(Color.RED);
			t2.setFont(new Font("Times New Roman",Font.ITALIC,20));
			t2.setBounds(20,135,250,40);
			
			t3=new JTextField("Enter Database Password");
			t3.setBackground(Color.YELLOW);
			t3.setForeground(Color.RED);
			t3.setFont(new Font("Times New Roman",Font.ITALIC,20));
			t3.setBounds(20,185,250,40);
			
			
			hostels=new Choice();
			hostels.add("Select Number of Hostels");
			hostels.add("1 Hostel");
			hostels.add("2 Hostels");
			hostels.add("3 Hostels");
			hostels.add("4 Hostels");
			hostels.add("5 Hostels");
			hostels.setBounds(20,235,250,60);
			hostels.setBackground(Color.YELLOW);
			hostels.setForeground(Color.RED);
			hostels.setFont(new Font("Times New Roman",Font.ITALIC,12));
			hostels.select(0);
			
			b1=new JButton("Next");
			b1.setBackground(Color.BLACK);
			b1.setForeground(Color.RED);
			b1.setFont(new Font("Times New Roman",Font.BOLD,18));
			b1.setBounds(240,295,90,25);
			
			if(stepNumber==2)
			{
				b2=new JButton("Finish");
				b2.setBackground(Color.BLACK);
				b2.setForeground(Color.RED);
				b2.setFont(new Font("Times New Roman",Font.BOLD,18));
				b2.setBounds(240,295,90,25);
				mf.add(b2);
				b2.addActionListener(this);
			}
			
			footer=new JLabel("Note: Please Write the Values Caustiously to prevent the Generation of Bugs.");
			footer.setForeground(Color.RED);
			footer.setBounds(15,305,370,100);
			footer.setFont(new Font("sans serif",Font.ITALIC,10));
		
			//this panel should be last .. bcoz it is used for background color.
			p1=new JPanel();
			p1.setBackground(new Color(240,240,200));
			p1.setBounds(300,300,1,1);
			mf.add(p1);
			
			mf.add(h1);
			mf.add(t1);
			mf.add(t2);
			mf.add(t3);
			mf.add(b1);
			mf.add(hostels);
			mf.add(footer);
			mf.add(p1);
			
			mf.setVisible(true);
			mf.setResizable(false);
			mf.setBounds(400,150,400,400);
			mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			b1.addActionListener(this);
			hostels.addItemListener(this);
	}
	
	public void itemStateChanged(ItemEvent iee)
	{
		if(hostels.getSelectedItem().equals("Select Number of Hostels"))
			numOfHostels=0;
		else if(hostels.getSelectedItem().equals("1 Hostel"))
			numOfHostels=1;
		else if(hostels.getSelectedItem().equals("2 Hostels"))
			numOfHostels=2;
		else if(hostels.getSelectedItem().equals("3 Hostels"))
			numOfHostels=3;
		else if(hostels.getSelectedItem().equals("4 Hostels"))
			numOfHostels=4;
		else if(hostels.getSelectedItem().equals("5 Hostels"))
			numOfHostels=5;
		
	}
	
	public void actionPerformed(ActionEvent ae)
{
		if(ae.getSource()==b1)
		{
			switch(stepNumber)
			{
				case 1:   if(t1.getText().equals(""))
							{
							t1.setText("Enter Database Name Here");
							JOptionPane.showMessageDialog(mf,"Please Enter Your Database Name... Name Should Not Be Empty.");
							}	
							else if(t1.getText().equals("Enter Database Name Here"))
							{
							t1.setText("Enter Database Name Here");
							 JOptionPane.showMessageDialog(mf,"Please Enter Your Database Name... Replace the Given Text");
							}
							else if(t2.getText().equals("") || t2.getText().equals("Enter Database Username"))
							{
							 JOptionPane.showMessageDialog(mf,"Please Enter Your Database Username");
							}
							else if(t3.getText().equals("Enter Database Name Here") || t3.getText().equals("Enter Database Password"))
							{
							 JOptionPane.showMessageDialog(mf,"Please Enter Your Database Password");
							}
							else if(numOfHostels==0)
							JOptionPane.showMessageDialog(mf,"Please Select Total Number of Hostels.");
							else
							{
								dbName=t1.getText();
								username=t2.getText();
								password=t3.getText();
								stepNumber++;   //..........goto next step 
								t1.setVisible(false);
								t2.setVisible(false);
								t3.setVisible(false);
								hostels.setVisible(false);
							
								//show data for second step.......
								{
								        hn1=new JTextField("Enter Name for Hostel 1");
									    hn1.setBackground(Color.YELLOW);
										hn1.setForeground(Color.RED);
										hn1.setFont(new Font("Times New Roman",Font.ITALIC,20));
										hn1.setBounds(20,85,250,35);
										mf.add(hn1);
										if(numOfHostels==1)
											break;
								
										hn2=new JTextField("Enter Name for Hostel 2");
									    hn2.setBackground(Color.YELLOW);
										hn2.setForeground(Color.RED);
										hn2.setFont(new Font("Times New Roman",Font.ITALIC,20));
										hn2.setBounds(20,125,250,35);
										mf.add(hn2);
										if(numOfHostels==2)
											break;
										
										hn3=new JTextField("Enter Name for Hostel 3");
									    hn3.setBackground(Color.YELLOW);
										hn3.setForeground(Color.RED);
										hn3.setFont(new Font("Times New Roman",Font.ITALIC,20));
										hn3.setBounds(20,165,250,35);
										mf.add(hn3);
										if(numOfHostels==3)
											break;
										
										hn4=new JTextField("Enter Name for Hostel 4");
									    hn4.setBackground(Color.YELLOW);
										hn4.setForeground(Color.RED);
										hn4.setFont(new Font("Times New Roman",Font.ITALIC,20));
										hn4.setBounds(20,205,250,35);
										mf.add(hn4);
										if(numOfHostels==4)
											break;
										hn5=new JTextField("Enter Name for Hostel 5");
									    hn5.setBackground(Color.YELLOW);
										hn5.setForeground(Color.RED);
										hn5.setFont(new Font("Times New Roman",Font.ITALIC,20));
										hn5.setBounds(20,245,250,35);
										mf.add(hn5);
								}
							}
							break;   //case 1
							
							
							case 2:   
									
									
											if(hn1.getText().equals("") || hn1.getText().equals("Enter Name for Hostel 1"))
												error=1;
											if(numOfHostels>1)
												{
													if(hn2.getText().equals("") || hn2.getText().equals("Enter Name for Hostel 2"))
														error=1;
													if(numOfHostels>2)
													{
														if(hn3.getText().equals("") || hn3.getText().equals("Enter Name for Hostel 3"))
															error=1;
														if(numOfHostels>3)
														{
															if(hn4.getText().equals("") || hn4.getText().equals("Enter Name for Hostel 4"))
																error=1;
															if(numOfHostels>4)
															{
																if(hn5.getText().equals("") || hn5.getText().equals("Enter Name for Hostel 5"))
																	error=1;
															}
														}
													}
												}

									if(error==1) 
									{
										JOptionPane.showMessageDialog(mf,"Please Enter Valid Hostels Names");
										error=0;
									}
										
									else if(error==0)   //0 means no error...
									{
										//Fetching table names.....
										hostel1=hn1.getText();
										if(numOfHostels>1)
										{
											hostel2=hn2.getText();
											if(numOfHostels>2)
											{
												hostel3=hn3.getText();
												if(numOfHostels>3)
												{
													hostel4=hn4.getText();
													if(numOfHostels>4)
													{
														hostel5=hn5.getText();
													}
												}
											}
										}
										
										
										
										
										
										//start creating tables....
										Connection con;
										Statement stm;
										try{
											String tableName="";
												Class.forName("com.mysql.jdbc.Driver");
												Connection c2=DriverManager.getConnection("jdbc:mysql://localhost/",username,password);
												Statement s2=c2.createStatement();
												s2.executeUpdate("CREATE DATABASE IF NOT EXISTS "+dbName);
												c2.close();
												
												
												con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,username,password);
												stm=con.createStatement();
												
												for(int i=1;i<=numOfHostels;i++)
												{
													if(i==1)
														tableName=hostel1.replaceAll("\\s+","");
													else if(i==2)
														tableName=hostel2.replaceAll("\\s+","");
													else if(i==3)
														tableName=hostel3.replaceAll("\\s+","");
													else if(i==4)
														tableName=hostel4.replaceAll("\\s+","");
													else if(i==5)
														tableName=hostel5.replaceAll("\\s+","");
													stm.executeUpdate("DROP TABLE IF EXISTS "+tableName);
													
													String qr="CREATE TABLE "+tableName+" ("
													+"name TEXT NOT NULL,"
													+"email TEXT,"
													+"room_no INT(10),"
													+"mobile TEXT,"
													+"dob TEXT,"
													+"enrollment TEXT,"
													+"address TEXT,"
													+"hostel_name TEXT"
													+")";
													//JOptionPane.showMessageDialog(mf,""+qr);
													stm.executeUpdate(qr);
													tablesCreated++;
												}
												
												if(tablesCreated==numOfHostels)
												{
													JOptionPane.showMessageDialog(mf,"Successfully Customized our ERP Software...Enjoy our Services...");
													//create DB table to store Admin values like dbname, table names, username of db , password etc...
														con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,username,password);
														stm=con.createStatement();
														stm.executeUpdate("DROP TABLE IF EXISTS admin");
														String qr1="CREATE TABLE "+admin+" ("
																								+"dbname TEXT,"
																								+"username TEXT,"
																								+"password TEXT,"
																								+"numofhostels INT(15),"
																								+"hn1 TEXT,"
																								+"hn2 TEXT,"
																								+"hn3 TEXT,"
																								+"hn4 TEXT,"
																								+"hn5 TEXT"
																								+")";
														stm.executeUpdate(qr1);
														String qr2="INSERT INTO admin VALUES ('"+dbName+"','"+username+"','"+password+"','"+numOfHostels+"','"+hostel1+"','"+hostel2+"','"+hostel3+"','"+hostel4+"','"+hostel5+"')";
														stm.executeUpdate(qr2);
														
														String lastadded="lastadded";
														String qrn="CREATE TABLE "+lastadded+" ("
																								+"name TEXT,"
																								+"enrollment TEXT,"
																								+"mobile TEXT,"
																								+"room_no TEXT,"
																								+"hostel_name TEXT"
																								+")";
														stm.executeUpdate(qrn);
														
														configured=1;    //it is not used anywhere...should be removed from decleration also.
													StartingClass sc=new StartingClass(dbName,username,password); 
													mf.setVisible(false);
													sc.doItNow();
												}
												
											}
											catch(Exception e)
											{
												JOptionPane.showMessageDialog(mf,"Something went wrong while connecting your DB. Please ensure that your credentials are all correct"+e);
												mf.setVisible(false);
												stepNumber=1;
												Customization ob1=new Customization();
												ob1.customIt();
											}
										
										//end creating tables.....
										
										
										
									}
									
							break;
							
    						     
				
			}
						
		}				
		
	}
}  

