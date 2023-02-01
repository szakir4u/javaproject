import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class LoginERP implements ActionListener
{
	JFrame mf;
	Container c;
	JPanel p1;
	JLabel h1,footer;
	JTextField t1,t2,t3;
	JButton login,signUp;
	String dbName=" ",username=" ",password=" ";
	static int error=0;
	
	public static void main(String ashdg[])
	{
		new LoginERP().customIt();
	}
	
	void customIt()
	{
			mf=new JFrame("LOGIN PANEL FOR ERP BY SZAKIR");
			c=mf.getContentPane();
			
			ImageIcon icon=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\logo.jpg");
			mf.setIconImage(icon.getImage());
			
			h1=new JLabel("ERP LOGIN PANEL");
			h1.setBackground(Color.GREEN);
			h1.setForeground(Color.RED);
			h1.setFont(new Font("Times New Roman",Font.BOLD,27));
			h1.setBounds(60,-5,360,90);
			
			
			//t1 is for step 1 and is made invisible in remaining steps...
			t1=new JTextField("Enter Database Name Here");
			t1.setBackground(Color.YELLOW);
			t1.setForeground(Color.RED);
			t1.setFont(new Font("Times New Roman",Font.ITALIC,20));
			t1.setBounds(60,85,250,40);
			
			t2=new JTextField("Enter Database Username");
			t2.setBackground(Color.YELLOW);
			t2.setForeground(Color.RED);
			t2.setFont(new Font("Times New Roman",Font.ITALIC,20));
			t2.setBounds(60,135,250,40);
			
			t3=new JTextField("Enter Database Password");
			t3.setBackground(Color.YELLOW);
			t3.setForeground(Color.RED);
			t3.setFont(new Font("Times New Roman",Font.ITALIC,20));
			t3.setBounds(60,185,250,40);
			
			signUp=new JButton("SIGN UP");
			signUp.setBackground(new Color(20,40,80));
			signUp.setForeground(Color.RED);
			signUp.setFont(new Font("Times New Roman",Font.BOLD,15));
			signUp.setBounds(120,295,100,28);
			
			login=new JButton("LOGIN");
			login.setBackground(new Color(30,200,50));
			login.setForeground(Color.RED);
			login.setFont(new Font("Times New Roman",Font.BOLD,15));
			login.setBounds(240,295,100,28);
			
			footer=new JLabel("Note: Please Write the Values Caustiously to prevent the Generation of Bugs.");
			footer.setForeground(Color.RED);
			footer.setBounds(15,305,370,100);
			footer.setFont(new Font("sans serif",Font.ITALIC,10));
		
			//this panel should be last .. bcoz it is used for background color.
			p1=new JPanel();
			p1.setBackground(Color.BLACK);
			p1.setBounds(300,300,1,1);
			mf.add(p1);
			
			mf.add(h1);
			mf.add(t1);
			mf.add(t2);
			mf.add(t3);
			mf.add(signUp);
			mf.add(login);
			mf.add(footer);
			mf.add(p1);
			
			mf.setVisible(true);
			mf.setResizable(false);
			mf.setBounds(400,150,400,400);
			mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			login.addActionListener(this);
			signUp.addActionListener(this);
	}

	
	public void actionPerformed(ActionEvent ae)
	{
			if(ae.getSource()==login)
			{
				
							   if(t1.getText().equals(""))
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
								else
								{
									dbName=t1.getText();
									username=t2.getText();
									password=t3.getText();
									
									int validDetails=0,alreadyRegistered=0;
									try{
										Class.forName("com.mysql.jdbc.Driver");
										Connection c2=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,username,password);
										validDetails=1;
										Statement s2=c2.createStatement();
										String qr3="SELECT * FROM admin";
										ResultSet rs=s2.executeQuery(qr3);
										int total=0;
										while(rs.next())
										{
											total++;
										}
										if(total>0)   //valid and registered user...
										{
											//JOptionPane.showMessageDialog(mf,"Login Success");
											StartingClass sc=new StartingClass(dbName,username,password); 
											mf.setVisible(false);
											sc.doItNow();
										}
										else{
											JOptionPane.showMessageDialog(mf,"Login Failed... Goto Sign Up if you are using it first time.");
										}
									}
									catch(Exception e4)
									{
										if(validDetails==0)
											JOptionPane.showMessageDialog(mf,"Please Enter valid Details of your MySQL Database...");
										else 
											JOptionPane.showMessageDialog(mf,"Something went wrong... Please Ensure that your details are correct.");
									}
									
								}
								
									 
					
			}
			else if(ae.getSource()==signUp)
			{
				Customization cz=new Customization();
				mf.setVisible(false);
				cz.customIt();
			}
							
		}				
}
  

