import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;

class StartingClass implements Runnable,ActionListener,ItemListener
{
	//...values related to DB operations...
	String dbName="";
	int numOfHostels=0;
	String actualHostelName;
	static int roomLimit=4;
	int totalinhostel1=0,totalinhostel2=0,totalinhostel3=0,totalinhostel4=0,totalinhostel5=0,step=0;
	String hn1,hn2,hn3,hn4,hn5,userName,password;
	static int menuSelected=0,previousSelected=-1;
	String nameOfLast,enrollmentOfLast,mobileOfLast,room_noOfLast,hostel_nameOfLast;
	ArrayList<String> nameSr=new ArrayList<String>();
	ArrayList<String> enrollmentSr=new ArrayList<String>();
	ArrayList<String> hostelSr=new ArrayList<String>();
	ArrayList<String> room_numberSr=new ArrayList<String>();
	static int iteration=0,total=0;  //global iteration for searched results...
	
	int configured=0;    //it is altered to 1 if all DB details are true...
	JFrame f1;
	Container c;
	JPanel p1,p2,p3,templast,line,pnlLeftOne,pnlLeftTwo,pnlRightOne,pnlRightTwo,pnlWlcm;     //templast is used for background color...
	JPanel pnlOneMain,pnlTwoMain,pnlThreeMain,pnlFourMain,pnlFiveMain,pnlSixMain,pnlSevenMain,pnlEightMain;
	JButton b1,b2,b3,b4,b5,b6,b7,b8;
	ImageIcon m1,m2,m3,m4,m5,m6,m7,m8,zas,sp,indianFlag,mewarLogo;
	JLabel heading1,heading2,heading3,szakir,salman,adminName1,adminMob1,adminEmail1,adminName2,adminMob2,adminEmail2,footer;
	JLabel oneMainHeading,twoMainHeading,threeMainHeading,fourMainHeading,fiveMainHeading,sixMainHeading,sevenMainHeading,eightMainHeading;
	
	JLabel lblone,lbltwo,lblthree,lblfour,lblfive,lblsix,lblseven,lbleight;  //for hostel details on left panel
	JLabel lblLeft2;
	JLabel lblRTOne,lblRTTwo,lblRTThree,lblRTFour,lblRTFive,lblRTSix,lblRTSeven,lblRTEight,lblRTNine,lblRTTen;
	JLabel rLowerHeading,lblLowerName,lblLowerEnrollment,lblLowerHostel,lblLowerRoom; //for right lower search result panel
	JTextField searchByName;
	JButton submitNameSearch,nextSearch;
	
	//Labels for oneMainPanel...
	JTextField name,email,room_no,mobile,dob,enrollment,address;
	Choice hostel_names;
	JButton submitRegister;
	String n,e,rn,mob,birthd,enroll,addr,hostel_name;
	static int hostelSelected=0;

	JLabel iflagCommon=new JLabel(indianFlag);
	JLabel mlogoCommon=new JLabel(mewarLogo);
	
	//For Delete Menu Display...
	JTextField delTextField;
	String delTextString,delEnrollment="";
	JButton delSubmit;
	
	//For Update Menu Display...
	JTextField updateTextField;
	String updateEnrollment;
	JButton updateSubmit;
	JTextField updateRoom,updateMobile,updateName;
	Choice updateHostel;
	String previousName,previousRoom,previousMobile,previousHostel,newRoom,newMobile,newHostel;    //name is actually not updated..
	JButton newUpdateSubmit;
	int updateDetailAdded=0,updatedHostel=0;
	int i=0;   //for counting already in room
	String updaten,updatee,updatern,updatemb,updatedb,updateen,updatead,updatehn;

	//For Search Display on main panel center...
	JTextField searchTextField;
	String searchName,searchEmail,searchRoom,searchMobile,searchDob,searchEnrollment,searchAddress,searchHostel;
	JButton searchSubmit;
	int searchDisplayed=0;
	JLabel nameOne,nameTwo,emailOne,emailTwo,roomOne,roomTwo,mobileOne,mobileTwo,dobOne,dobTwo,enrollmentOne,enrollmentTwo,addressOne,addressTwo,hostelOne,hostelTwo;

	
	//For Settings
	int hostelSel=0;
	String hostelNameSelected;
	String totalAvailable;
	JTextField delfld;
	JButton setbut;
	Choice setchoice;
	
    //Main heading font...
	Font fHeading=new Font("Times New Roman",Font.BOLD,25);

	Connection con;
	Statement stm;
	
	Thread footerThread,lblThread;
	
	
	public static void main(String asndb[])
	{
		
		new StartingClass().doItNow();
	}
	
	
	public StartingClass()
	{
		
	}
	public StartingClass(String dbn,String un,String pw)
	{
		this.dbName=dbn;
		this.userName=un;
		this.password=pw;
		//JOptionPane.showMessageDialog(null,"Details...:"+this.dbName+" "+this.userName+" "+this.password);
	}
	


	void loadHostelDetails()
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
		stm=con.createStatement();
		
		ResultSet adminValues=stm.executeQuery("SELECT * FROM admin");
		while(adminValues.next())
		{
			numOfHostels=adminValues.getInt(4);
			hn1=adminValues.getString(5);  //hostel name 100
			if(numOfHostels>1)
			{
				hn2=adminValues.getString(6); 
				if(numOfHostels>2)
				{
					hn3=adminValues.getString(7); 
					if(numOfHostels>3)
					{
						hn4=adminValues.getString(8);
						if(numOfHostels>4)
						{
							hn5=adminValues.getString(9); 
						}
					}
				}
			}
			
		}
		

		}
		catch(Exception e5)
		{
			
		}
	}
	
	
	
	
	//load data for last added student from database table last added....
	
	void loadLastAdded()
	{
		try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
				stm=con.createStatement();
				
				ResultSet lastAdded=stm.executeQuery("SELECT * FROM lastadded");
				while(lastAdded.next())
				{
					nameOfLast=lastAdded.getString(1);
					enrollmentOfLast=lastAdded.getString(2);
					mobileOfLast=lastAdded.getString(3);
					room_noOfLast=lastAdded.getString(4);
					hostel_nameOfLast=lastAdded.getString(5);
				}
				//JOptionPane.showMessageDialog(f1,"Last Added Student Details..."+nameOfLast+" "+enrollmentOfLast+" "+mobileOfLast+" "+room_noOfLast+" "+hostel_nameOfLast+" ");
				lblRTTwo.setText(" "+nameOfLast);
				lblRTFour.setText(" "+enrollmentOfLast);
				lblRTSix.setText(" "+mobileOfLast);
				lblRTEight.setText(" "+room_noOfLast);
				lblRTTen.setText(" "+hostel_nameOfLast);
				
				
				
				
				
				
				step++;
				if(step>1)
				{
					totalinhostel1=0;
					totalinhostel2=0;
					totalinhostel3=0;
					totalinhostel4=0;
					totalinhostel5=0;
				}
				//update total students in hostel value...
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
				stm=con.createStatement();
				
				String h1,h2,h3,h4,h5;
				h1=hn1.replaceAll("\\s+","");
				ResultSet r43=stm.executeQuery("SELECT * FROM "+h1);
				while(r43.next())
				{
					totalinhostel1++;
				}
				if(numOfHostels>1)
				{
					h2=hn2.replaceAll("\\s+","");
					ResultSet r44=stm.executeQuery("SELECT * FROM "+h2);
					while(r44.next())
					{
						totalinhostel2++;
					}
					if(numOfHostels>2)
					{
						h3=hn3.replaceAll("\\s+","");
						ResultSet r45=stm.executeQuery("SELECT * FROM "+h3);
						while(r45.next())
						{
							totalinhostel3++;
						}
						if(numOfHostels>3)
						{
							h4=hn4.replaceAll("\\s+","");
							ResultSet r46=stm.executeQuery("SELECT * FROM "+h4);
							while(r46.next())
							{
								totalinhostel4++;
							}
							if(numOfHostels>4)
							{
								h5=hn5.replaceAll("\\s+","");
								ResultSet r47=stm.executeQuery("SELECT * FROM "+h5);
								while(r47.next())
								{
									totalinhostel5++;
								}
							}
						}
					}
				}
				
		}
		catch(Exception ee8)
		{
			JOptionPane.showMessageDialog(f1,"Unable to load Last Added Student Details"+ee8);
		}
	}
	
	
	
	
	
	
	void loadSearchedData()
	{
		if(iteration<total && total!=0)
		{
			lblLowerName.setText(nameSr.get(iteration));
			lblLowerEnrollment.setText(enrollmentSr.get(iteration));
			lblLowerHostel.setText(hostelSr.get(iteration));
			lblLowerRoom.setText(room_numberSr.get(iteration));
			iteration++;
			pnlRightTwo.add(nextSearch);
		}
		else{
			lblLowerName.setText("No More Results Available");
			lblLowerEnrollment.setText("Try Different keywords");
			lblLowerHostel.setText("");
			lblLowerRoom.setText("");
			total=0;iteration=0;
			pnlRightTwo.remove(nextSearch);
			
			nameSr.clear();
			enrollmentSr.clear();
			hostelSr.clear();
			room_numberSr.clear();
		}
	}
	
	

	
	void doItNow()
	{
		loadHostelDetails(); 
		
		
		f1=new JFrame("ERP By SZAKIR & SALMAAN");
		c=f1.getContentPane();
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		templast=new JPanel();
		line=new JPanel();
		heading1=new JLabel("HOSTEL MONITORING");
		heading2=new JLabel("MEWAR UNIVERSITY HOSTEL MANAGEMENT SOFTWARE");
		heading3=new JLabel("LAST STUDENT ADDED");
		footer=new JLabel("This Software has been created by Szakir and Salman. Thanks for using our Software.");
		
		
		m1=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\a.png");
		m2=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\b.png");
		m3=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\c.png");
		m4=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\d.png");
		m5=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\e.png");
		m6=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\f.png");
		m7=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\g.png");
		m8=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\h.png");
		zas=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\zas.png");
		sp=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\salman.png");
		indianFlag=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\indianflag.gif");
		mewarLogo=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\mewarlogo.png");

		//main frame common logos on left and right side.
		iflagCommon=new JLabel(indianFlag);
		mlogoCommon=new JLabel(mewarLogo);
		iflagCommon.setBounds(2,0,60,50);
		mlogoCommon.setBounds(789,0,60,50);

		//set icon to the frame itself...
		ImageIcon icon=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\logo.jpg");
		f1.setIconImage(icon.getImage());
		
		
		szakir=new JLabel(zas);
		salman=new JLabel(sp);
		adminName1=new JLabel("SZAKIR");
		adminMob1=new JLabel("7073898166");
		adminEmail1=new JLabel("contact@szakir.in");
		adminName2=new JLabel("SALMAN");
		adminMob2=new JLabel("7073898166");
		adminEmail2=new JLabel("salu@gmail.com");
		adminName1.setForeground(Color.RED);
		adminName2.setForeground(Color.RED);
		adminMob1.setForeground(Color.YELLOW);
		adminMob2.setForeground(Color.YELLOW);
		adminEmail1.setForeground(Color.YELLOW);
		adminEmail2.setForeground(Color.YELLOW);
		footer.setForeground(Color.YELLOW);
		
		b1=new JButton(m1);
		b2=new JButton(m2);
		b3=new JButton(m3);
		b4=new JButton(m4);
		b5=new JButton(m5);
		b6=new JButton(m6);
		b7=new JButton(m7);
		b8=new JButton(m8);
		
		b1.setToolTipText("Add New Student");
		b2.setToolTipText("Delete Single Student");
		b3.setToolTipText("Update Details");
		b4.setToolTipText("Delete Multiple Students");
		b5.setToolTipText("Settings");
		b6.setToolTipText("Search Student Details By Enrollment");
		b7.setToolTipText("About");
		b8.setToolTipText("Help");
		
		b1.setBackground(Color.ORANGE);
		b2.setBackground(Color.GRAY);
		b3.setBackground(Color.ORANGE);
		b4.setBackground(Color.GRAY);
		b5.setBackground(Color.ORANGE);
		b6.setBackground(Color.GRAY);
		b7.setBackground(Color.ORANGE);
		b8.setBackground(Color.GRAY);
		
		b1.setBounds(20,15,100,100);
		b2.setBounds(130,15,100,100);
		b3.setBounds(240,15,100,100);
		b4.setBounds(350,15,100,100);
		b5.setBounds(460,15,100,100);
		b6.setBounds(570,15,100,100);
		b7.setBounds(680,15,100,100);
		b8.setBounds(790,15,100,100);
		p1.setBounds(20,150,210,500);
		p2.setBounds(260,150,850,500);
		p3.setBounds(1140,150,200,500);
		templast.setBounds(1360,150,5,5);
		szakir.setBounds(1120,-20,120,120);
		salman.setBounds(1240,-20,120,120);
		adminName1.setBounds(1160,70,120,30);
		adminMob1.setBounds(1145,77,120,50);
		adminEmail1.setBounds(1132,87,120,50);
		adminName2.setBounds(1280,70,120,30);
		adminMob2.setBounds(1265,77,120,50);
		adminEmail2.setBounds(1257,87,120,50);
		heading1.setBounds(25,155,200,60);
		heading2.setBounds(270,155,800,90);
		heading3.setBounds(1145,155,180,60);
		
		Font ntr=new Font("New Times Roman",1,30);
		Font ss=new Font("Sans Serif",1,19);
		Font ss2=new Font("Sans Serif",1,17);

		
		heading1.setForeground(Color.RED);
		heading2.setForeground(Color.RED);
		heading3.setForeground(new Color(100,200,150));
		heading1.setBackground(Color.BLACK);
		heading2.setBackground(Color.BLACK);
		heading3.setBackground(Color.BLACK);
		heading1.setFont(ss);
		heading2.setFont(ntr);
		heading3.setFont(ss2);
		
		//adminintro.setBackground(new Color(200,200,200));          //PINK);
		p1.setBackground(new Color(50,100,60));
		p2.setBackground(Color.WHITE);
		p3.setBackground(new Color(130,70,60));
		templast.setBackground(new Color(0,0,0));
		line.setBackground(Color.BLUE);
		line.setBounds(0,125,1365,17);
	
	
	
	
		//.....Dynamic pannels of left side....
		lblone=new JLabel("Hostel Name");
		lbltwo=new JLabel("Name of Hostel");
		lblthree=new JLabel("Total Rooms");
		lblfour=new JLabel("123");
		lblfive=new JLabel("Available Rooms");
		lblsix=new JLabel("123");
		lblseven=new JLabel("Total Students");
		lbleight=new JLabel("317");
		lbltwo.setForeground(Color.GREEN);
		lblfour.setForeground(Color.GREEN);
		lblsix.setForeground(Color.GREEN);
		lbleight.setForeground(Color.GREEN);
		lblone.setForeground(Color.RED);
		lblthree.setForeground(Color.RED);
		lblfive.setForeground(Color.RED);
		lblseven.setForeground(Color.RED);
		lbltwo.setFont(new Font("Times New Roman",Font.ITALIC,20));
		lblfour.setFont(new Font("Times New Roman",Font.ITALIC,20));
		lblsix.setFont(new Font("Times New Roman",Font.ITALIC,20));
		lbleight.setFont(new Font("Times New Roman",Font.ITALIC,20));
		lblone.setFont(new Font("Times New Roman",Font.BOLD,14));
		lblthree.setFont(new Font("Times New Roman",Font.BOLD,14));
		lblfive.setFont(new Font("Times New Roman",Font.BOLD,14));
		lblseven.setFont(new Font("Times New Roman",Font.BOLD,14));
		lblone.setBounds(25,220,120,15);
		lbltwo.setBounds(20,240,120,15);
		lblthree.setBounds(25,265,120,15);
		lblfour.setBounds(20,285,120,15);
		lblfive.setBounds(25,310,120,15);
		lblsix.setBounds(20,330,120,15);
		lblseven.setBounds(25,355,120,15);
		lbleight.setBounds(20,375,120,15);
		
		
		
		
		//right top panel for last student added...
		lblRTOne=new JLabel("Student Name",JLabel.CENTER);
		lblRTTwo=new JLabel("Value of Name",JLabel.CENTER);
		lblRTThree=new JLabel("Enrollment",JLabel.CENTER);
		lblRTFour=new JLabel("Value of Enrollment",JLabel.CENTER);
		lblRTFive=new JLabel("Mobile Number",JLabel.CENTER);
		lblRTSix=new JLabel("Value of Mobile",JLabel.CENTER);
		lblRTSeven=new JLabel("Room Number",JLabel.CENTER);
		lblRTEight=new JLabel("Value of Room Number",JLabel.CENTER);
		lblRTNine=new JLabel("Hostel Name",JLabel.CENTER);
		lblRTTen=new JLabel("Value of Hostel Name",JLabel.CENTER);
		lblRTTwo.setForeground(Color.BLUE);
		lblRTFour.setForeground(Color.BLUE);
		lblRTSix.setForeground(Color.BLUE);
		lblRTEight.setForeground(Color.BLUE);
		lblRTTen.setForeground(Color.BLUE);
		lblRTOne.setForeground(Color.RED);
		lblRTThree.setForeground(Color.RED);
		lblRTFive.setForeground(Color.RED);
		lblRTSeven.setForeground(Color.RED);
		lblRTNine.setForeground(Color.RED);
		lblRTTwo.setFont(new Font("Times New Roman",Font.ITALIC,17));
		lblRTFour.setFont(new Font("Times New Roman",Font.ITALIC,17));
		lblRTSix.setFont(new Font("Times New Roman",Font.ITALIC,17));
		lblRTEight.setFont(new Font("Times New Roman",Font.ITALIC,17));
		lblRTTen.setFont(new Font("Times New Roman",Font.ITALIC,17));
		lblRTOne.setFont(new Font("Times New Roman",Font.BOLD,10));
		lblRTThree.setFont(new Font("Times New Roman",Font.BOLD,10));
		lblRTFive.setFont(new Font("Times New Roman",Font.BOLD,10));
		lblRTSeven.setFont(new Font("Times New Roman",Font.BOLD,10));
		lblRTNine.setFont(new Font("Times New Roman",Font.BOLD,10));
		
		//right lower Panel...for search...
		rLowerHeading=new JLabel("SEARCH BY NAME");
		searchByName=new JTextField("Enter Name to Search");
		submitNameSearch=new JButton("Search");	
		rLowerHeading.setForeground(Color.RED);
		rLowerHeading.setFont(new Font("Times New Roman",Font.ITALIC,22));
		searchByName.setForeground(Color.BLUE);
		searchByName.setFont(new Font("Times New Roman",Font.ITALIC,15));
		rLowerHeading.setBackground(Color.BLACK);
		rLowerHeading.setForeground(Color.RED);
		lblLowerName=new JLabel("",JLabel.CENTER);   //display name here for search
		lblLowerName.setFont(new Font("Times New Roman",Font.BOLD,13));
		lblLowerName.setForeground(Color.BLACK);
		lblLowerEnrollment=new JLabel("",JLabel.CENTER);   //display Enrollment on it
		lblLowerEnrollment.setFont(new Font("Times New Roman",Font.BOLD,14));
		lblLowerEnrollment.setForeground(Color.RED);
		lblLowerHostel=new JLabel("",JLabel.CENTER);
		lblLowerHostel.setFont(new Font("Times New Roman",Font.BOLD,15));
		lblLowerHostel.setForeground(Color.GRAY);
		lblLowerRoom=new JLabel("",JLabel.CENTER);
		lblLowerRoom.setFont(new Font("Times New Roman",Font.BOLD,16));
		lblLowerRoom.setForeground(new Color(120,40,20));
		nextSearch=new JButton("Next Result");
		nextSearch.setForeground(Color.BLUE);
		
		searchByName.setColumns(16);

		rLowerHeading.setBounds(10,2,250,40);
		searchByName.setBounds(23,40,150,30);
		submitNameSearch.setBounds(42,72,90,24);
		lblLowerName.setBounds(24,112,150,22);
		lblLowerEnrollment.setBounds(24,136,150,22);
		lblLowerHostel.setBounds(24,160,150,22);
		lblLowerRoom.setBounds(24,184,150,22);	
		nextSearch.setBounds(42,208,130,25);
		
		
		//two rows for two panels upper panel and lower panel
		p1.setLayout(new GridLayout(2,1,10,10));   
		p3.setLayout(new GridLayout(2,1,17,17));
		p2.setLayout(new BorderLayout());
		
		//2 panels of left panel p1...
		pnlLeftOne=new JPanel();
		pnlLeftOne.setBackground(Color.WHITE);
		pnlLeftTwo=new JPanel();
		pnlLeftTwo.setBackground(new Color(200,200,230));
		
		//2 panels of right panel p3...
		pnlRightOne=new JPanel();
		pnlRightOne.setBackground(new Color(230,200,200));
		pnlRightTwo=new JPanel();
		pnlRightTwo.setBackground(new Color(200,230,200));
		pnlRightTwo.setLayout(null);                                 //let's check
		
		
		lblLeft2=new JLabel("INTRODUCTION");
		lblLeft2.setFont(new Font("Sans Serif",Font.BOLD,18));
		lblLeft2.setForeground(new Color(80,120,50));
		lblLeft2.setBounds(20,350,200,50);
	
	
	
	
		loadMainPanel();	 //calling to the main center panel to load...
	
	
	
	
		f1.add(szakir);
		f1.add(salman);
		f1.add(adminName1);
		f1.add(adminMob1);
		f1.add(adminEmail1);
		f1.add(adminName2);
		f1.add(adminMob2);
		f1.add(adminEmail2);
		
		f1.add(b1);
		f1.add(b2);
		f1.add(b3);
		f1.add(b4);
		f1.add(b5);
		f1.add(b6);
		f1.add(b7);
		f1.add(b8);
		f1.add(p1);
		f1.add(p2);
		f1.add(p3);
		f1.add(line);
		
		p2.add(heading2,BorderLayout.NORTH);
		p2.add(pnlWlcm,BorderLayout.CENTER);
		
		//p3.add(heading3);
		
		//adding data to upper panle of left panel p1...
		pnlLeftOne.add(heading1);
		pnlLeftOne.add(new JLabel("                            "));
		pnlLeftOne.add(new JLabel("                            "));
		pnlLeftOne.add(new JLabel("                                                                      "));
		pnlLeftOne.add(lblone);
		pnlLeftOne.add(lbltwo);
		pnlLeftOne.add(lblthree);
		pnlLeftOne.add(lblfour);
		pnlLeftOne.add(lblfive);
		pnlLeftOne.add(lblsix);
		pnlLeftOne.add(lblseven);
		pnlLeftOne.add(lbleight);
		
		//adding data to lower panel of left panel p1...
		pnlLeftTwo.add(lblLeft2);
		pnlLeftTwo.add(new JLabel("My Name iS Zakir Ahmad.I have"));
		pnlLeftTwo.add(new JLabel("Created This Software for Mewar"));
		pnlLeftTwo.add(new JLabel("Mewar University Hostels. Thanks"));
		pnlLeftTwo.add(new JLabel("for Using my Software."));
		pnlLeftTwo.add(new JLabel("                                  "));
		pnlLeftTwo.add(new JLabel("Download more Softwares from"));
		pnlLeftTwo.add(new JLabel("     www.szakir.in"));
		pnlLeftTwo.add(new JLabel("                                  "));
		pnlLeftTwo.add(new JLabel("If you have any Complain or"));
		pnlLeftTwo.add(new JLabel("Suggession, Feel free to contact me"));
		pnlLeftTwo.add(new JLabel("Phone No.: 7073898166"));
		
		//adding data to upper panel of right panel p3...
		pnlRightOne.setLayout(new GridLayout(11,1,5,5));
		pnlRightOne.add(heading3);
		pnlRightOne.add(lblRTOne);
		pnlRightOne.add(lblRTTwo);
		pnlRightOne.add(lblRTThree);
		pnlRightOne.add(lblRTFour);
		pnlRightOne.add(lblRTFive);
		pnlRightOne.add(lblRTSix);
		pnlRightOne.add(lblRTSeven);
		pnlRightOne.add(lblRTEight);
		pnlRightOne.add(lblRTNine);
		pnlRightOne.add(lblRTTen);
		
		//adding data to lower panel of right panel p4...
		pnlRightTwo.add(rLowerHeading);
		pnlRightTwo.add(searchByName);
		pnlRightTwo.add(submitNameSearch);
		pnlRightTwo.add(new JLabel("                                                                                                             "));
		pnlRightTwo.add(lblLowerName);
		//pnlRightTwo.add(new JLabel("                                    "));
		pnlRightTwo.add(lblLowerEnrollment);
		//pnlRightTwo.add(new JLabel("                                    "));
		pnlRightTwo.add(lblLowerHostel);
		//pnlRightTwo.add(new JLabel("                                    "));
		pnlRightTwo.add(lblLowerRoom);
		//pnlRightTwo.add(new JLabel("                                    "));
		//pnlRightTwo.add(nextSearch);   add dynamically
		
		p1.add(pnlLeftOne);
		p1.add(pnlLeftTwo);
		
		p3.add(pnlRightOne);
		p3.add(pnlRightTwo);
		
		f1.add(footer);
		
		f1.add(templast);  //this should be added last to the frame f1 bcoz it is used for background color of the main frame...
		
		
		f1.setVisible(true);
		f1.setSize(1365,768);
		f1.setResizable(false);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		footerThread=new Thread(this,"footerThread");
		footerThread.start();  //calling d footer thread
		lblThread=new Thread(this,"lblThread");
		lblThread.start();
		
		
		loadLastAdded();   //last added user details on right panel...
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		
		submitNameSearch.addActionListener(this);
		nextSearch.addActionListener(this);
	}
	
	
	
	//center panel data loading is done here.............
	void loadMainPanel()
	{
		switch(previousSelected)    //remove from loaded panel on main panel p2...
		{
			case 0: p2.remove(pnlWlcm);
			break;
			case 1: p2.remove(pnlOneMain);
			break;
			case 2: p2.remove(pnlTwoMain);
			break;
			case 3: p2.remove(pnlThreeMain);
			break;
			case 4: p2.remove(pnlFourMain);
			break;
			case 5: p2.remove(pnlFiveMain);
			break;
			case 6: p2.remove(pnlSixMain);
			break;
			case 7: p2.remove(pnlSevenMain);
			break;
			case 8: p2.remove(pnlEightMain);
			break;
		}
		//start main panel data..................
		switch(menuSelected)
		{
			case 0:   //load Welcome message....
					pnlWlcm=new JPanel();
					pnlWlcm.setBackground(Color.GRAY);            //new Color(230,230,230));
					pnlWlcm.setLayout(null);
					
					JLabel iflag=new JLabel(indianFlag);
					JLabel mlogo=new JLabel(mewarLogo);
					ImageIcon wlcmImage=new ImageIcon("C:\\Users\\HP\\Desktop\\SF\\images\\wlcmimage.png");
					JLabel wlcmLbl=new JLabel(wlcmImage);
					JLabel wlcmMsg=new JLabel("Welcome to our Hostel Management Software. We have created this Software as an open");
					JLabel wlcmMsg2=new JLabel("platform for Colleges, Universities and Organizations to Manage their Hostels Data. We");
					JLabel wlcmMsg3=new JLabel("Welcome You for Your Suggestions and Queries. Feel free to Contact us.");
					JLabel adminLbl1=new JLabel("SZAKIR");
					JLabel adminLbl2=new JLabel("7073898166");
					JLabel adminLbl3=new JLabel("contact@szakir.in");
					JLabel salmanLbl1=new JLabel("SALMAAN");
					JLabel salmanLbl2=new JLabel("7073898166");
					JLabel salmanLbl3=new JLabel("salmaan@szakir.in");
					wlcmMsg.setFont(new Font("Arial",Font.ITALIC,20));
					wlcmMsg.setForeground(Color.YELLOW);
					wlcmMsg2.setFont(new Font("Arial",Font.ITALIC,20));
					wlcmMsg2.setForeground(Color.YELLOW);
					wlcmMsg3.setFont(new Font("Arial",Font.ITALIC,20));
					wlcmMsg3.setForeground(Color.YELLOW);
					adminLbl1.setFont(new Font("Arial",Font.BOLD,20));
					adminLbl1.setForeground(Color.YELLOW);
					adminLbl2.setFont(new Font("Arial",Font.BOLD,20));
					adminLbl2.setForeground(Color.YELLOW);
					adminLbl3.setFont(new Font("Arial",Font.BOLD,20));
					adminLbl3.setForeground(Color.YELLOW);
					salmanLbl1.setFont(new Font("Arial",Font.BOLD,20));
					salmanLbl1.setForeground(Color.YELLOW);
					salmanLbl2.setFont(new Font("Arial",Font.BOLD,20));
					salmanLbl2.setForeground(Color.YELLOW);
					salmanLbl3.setFont(new Font("Arial",Font.BOLD,20));
					salmanLbl3.setForeground(Color.YELLOW);

					iflag.setBounds(2,0,60,50);
					mlogo.setBounds(789,0,60,50);
					wlcmLbl.setBounds(230,0,390,170);   //welcome image

					wlcmMsg.setBounds(10,180,815,70);   //detail message
					wlcmMsg2.setBounds(10,205,815,70);
					wlcmMsg3.setBounds(10,230,815,70);

					adminLbl1.setBounds(200,300,200,70);
					adminLbl2.setBounds(190,325,200,70);
					adminLbl3.setBounds(170,350,200,70);
					salmanLbl1.setBounds(550,300,200,70);
					salmanLbl2.setBounds(540,325,200,70);
					salmanLbl3.setBounds(520,350,200,70);

					pnlWlcm.add(iflag);
					pnlWlcm.add(wlcmLbl);
					pnlWlcm.add(mlogo);
					pnlWlcm.add(wlcmMsg);
					pnlWlcm.add(wlcmMsg2);
					pnlWlcm.add(wlcmMsg3);
					pnlWlcm.add(adminLbl1);
					pnlWlcm.add(adminLbl2);
					pnlWlcm.add(adminLbl3);
					pnlWlcm.add(salmanLbl1);
					pnlWlcm.add(salmanLbl2);
					pnlWlcm.add(salmanLbl3);
					p2.add(pnlWlcm);
					
			break;  //end main panel data.................
			
			case 1:  //load form for new student insertion and update hostel details...
					pnlOneMain=new JPanel();
					pnlOneMain.setBackground(Color.GRAY);                 //new Color(230,230,230));
					//pnlOneMain.setLayout(new GridLayout(11,3,5,5));
					pnlOneMain.setLayout(null);    
					
					oneMainHeading=new JLabel("Register New Student");
					oneMainHeading.setForeground(Color.YELLOW);
					oneMainHeading.setFont(new Font("Times New Roman",Font.BOLD,30));
					
					
					name=new JTextField("Enter Student Name");
					name.setBackground(Color.BLACK);
					name.setForeground(Color.WHITE);
					name.setFont(new Font("Sans Serif",Font.ITALIC,16));
					email=new JTextField("Enter Student Email");
					email.setBackground(Color.BLACK);
					email.setForeground(Color.WHITE);
					email.setFont(new Font("Sans Serif",Font.ITALIC,16));
					room_no=new JTextField("Provide a Room Number");
					room_no.setBackground(Color.BLACK);
					room_no.setForeground(Color.WHITE);
					room_no.setFont(new Font("Sans Serif",Font.ITALIC,16));
					mobile=new JTextField("Mobile Number");
					mobile.setBackground(Color.BLACK);
					mobile.setForeground(Color.WHITE);
					mobile.setFont(new Font("Sans Serif",Font.ITALIC,16));
					dob=new JTextField("D.O.B as dd/mm/yyyy");
					dob.setBackground(Color.BLACK);
					dob.setForeground(Color.WHITE);
					dob.setFont(new Font("Sans Serif",Font.ITALIC,16));
					enrollment=new JTextField("Enrollment Number");
					enrollment.setBackground(Color.BLACK);
					enrollment.setForeground(Color.WHITE);
					enrollment.setFont(new Font("Sans Serif",Font.ITALIC,16));
					address=new JTextField("Enter Student Address");
					address.setBackground(Color.BLACK);
					address.setForeground(Color.WHITE);
					address.setFont(new Font("Sans Serif",Font.ITALIC,16));
					
					hostel_names=new Choice();
					hostel_names.add("Select Hostel for Allocation");
					hostel_names.add(" "+hn1);
					if(numOfHostels>1)
					{
						hostel_names.add(" "+hn2);
						if(numOfHostels>2)
						{
							hostel_names.add(" "+hn3);
							if(numOfHostels>3)
							{
								hostel_names.add(" "+hn4);
								if(numOfHostels>4)
								{
									hostel_names.add(" "+hn5);
								}
							}
						}
					} 
					hostel_names.select(0);
					hostel_names.setBackground(Color.BLACK);
					hostel_names.setForeground(Color.WHITE);
					hostel_names.setFont(new Font("Sans Serif",Font.ITALIC,16));
					submitRegister=new JButton("Click to Register");
					submitRegister.setForeground(Color.YELLOW);
					submitRegister.setBackground(new Color(200,20,210));
					submitRegister.setFont(new Font("Sans Serif",Font.BOLD,20));
					
					//set Bounds for positioning components of Main Panel...
					
					oneMainHeading.setBounds(300,15,320,40);
					name.setBounds(240,60,340,30);
					email.setBounds(240,95,340,30);
					room_no.setBounds(240,130,340,30);
					mobile.setBounds(240,165,340,30);
					dob.setBounds(240,200,340,30);
					enrollment.setBounds(240,235,340,30);
					address.setBounds(240,270,340,30);
					hostel_names.setBounds(240,305,340,30);
					submitRegister.setBounds(300,350,200,30);

					pnlOneMain.add(iflagCommon);
					pnlOneMain.add(mlogoCommon);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(oneMainHeading,FlowLayout.CENTER);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(name);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(email);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(room_no);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(mobile);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(dob);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(enrollment);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(address);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(hostel_names);
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(new JLabel("   "));
					pnlOneMain.add(submitRegister);
					pnlOneMain.add(new JLabel("   "));
					p2.add(pnlOneMain);
					submitRegister.addActionListener(this);
					hostel_names.addItemListener(this);
					
			break;    //insert new student...menu first ended
			
			case 2:  //delete student... second menu starting
					
					pnlTwoMain=new JPanel();
					pnlTwoMain.setBackground(Color.GRAY);
					pnlTwoMain.setLayout(null);
					//pnlOneMain.setLayout(new GridLayout(11,3,5,5));
					twoMainHeading=new JLabel("DELETE REGISTERED STUDENT");
					twoMainHeading.setForeground(Color.YELLOW);
					twoMainHeading.setFont(new Font("Times New Roman",Font.BOLD,25));

					pnlTwoMain.add(iflagCommon);
					pnlTwoMain.add(mlogoCommon);
					
					delTextField=new JTextField("Enter Student Enrollment or Hostel Id Number");
					delTextField.setForeground(Color.WHITE);
					delTextField.setBackground(Color.BLACK);
					delTextField.setFont(new Font("Sans Serif",Font.ITALIC,16));
					delTextField.setColumns(25);

					delSubmit=new JButton("Delete Student");
					delSubmit.setForeground(Color.RED);
					delSubmit.setBackground(Color.YELLOW);
					delSubmit.setFont(new Font("Times New Roman",Font.BOLD,20));

					twoMainHeading.setBounds(250,30,650,40);
					delTextField.setBounds(240,135,340,40);
					delSubmit.setBounds(300,180,180,30);
					
					pnlTwoMain.add(twoMainHeading);
					pnlTwoMain.add(delTextField);
					pnlTwoMain.add(delSubmit);
					p2.add(pnlTwoMain);
					
					delSubmit.addActionListener(this);
			break;
			
			case 3: //update detail of student
					pnlThreeMain=new JPanel();
					pnlThreeMain.setBackground(Color.GRAY);
					pnlThreeMain.setLayout(null);
	
					threeMainHeading=new JLabel("UPDATE USER DETAILS");
					threeMainHeading.setForeground(Color.YELLOW);
					threeMainHeading.setFont(fHeading);

					updateTextField=new JTextField("Enter Student Enrollment to Update Details");
					updateTextField.setForeground(Color.WHITE);
					updateTextField.setBackground(Color.BLACK);
					updateTextField.setFont(new Font("Sans Serif",Font.ITALIC,16));
					updateTextField.setColumns(25);

					updateSubmit=new JButton("Search Student");
					updateSubmit.setForeground(Color.RED);
					updateSubmit.setBackground(Color.YELLOW);
					updateSubmit.setFont(new Font("Times New Roman",Font.BOLD,20));

					threeMainHeading.setBounds(250,30,650,40);
					updateTextField.setBounds(240,135,340,40);
					updateSubmit.setBounds(300,180,180,30);
					
					pnlThreeMain.add(threeMainHeading);
					pnlThreeMain.add(iflagCommon);
					pnlThreeMain.add(mlogoCommon);

					pnlThreeMain.add(updateTextField);
					pnlThreeMain.add(updateSubmit);



					p2.add(pnlThreeMain);
					updateSubmit.addActionListener(this);
			
			break;
			
			case 4:  //delete all form
					pnlFourMain=new JPanel();
					pnlFourMain.setBackground(new Color(230,230,230));

					pnlFourMain.setBackground(Color.GRAY);
					pnlFourMain.setLayout(null);
	
					fourMainHeading=new JLabel("DELETE ALL STUDENTS");
					fourMainHeading.setForeground(Color.YELLOW);
					fourMainHeading.setFont(fHeading);

					fourMainHeading.setBounds(300,30,650,40);
					pnlFourMain.add(fourMainHeading);
					pnlFourMain.add(iflagCommon);
					pnlFourMain.add(mlogoCommon);
					
					JLabel del2= new JLabel();
					del2.setText("Click here to delete all user data");
					del2.setFont(fHeading);
					del2.setForeground(Color.GREEN);
					
					JButton delbutton = new JButton("DELETE ALL");
					delbutton.setForeground(Color.WHITE);
					delbutton.setBackground(Color.BLACK);
					delbutton.setFont(new Font("italic",Font.BOLD,18));

					
					del2.setBounds(300,100,500,40);
					delbutton.setBounds(300,160,150,40);
					
					pnlFourMain.add(del2);
					pnlFourMain.add(delbutton);
					

					p2.add(pnlFourMain);
		
					
			break;
			
			case 5:  //settings  like font size, color alteration...
					pnlFiveMain=new JPanel();
					
					pnlFiveMain.setBackground(Color.GRAY);
					pnlFiveMain.setLayout(null);
	
					fiveMainHeading=new JLabel("HOSTEL SETTINGS");
					fiveMainHeading.setForeground(Color.YELLOW);
					fiveMainHeading.setFont(fHeading);

					fiveMainHeading.setBounds(300,30,650,40);
					pnlFiveMain.add(fiveMainHeading);
					pnlFiveMain.add(iflagCommon);
					pnlFiveMain.add(mlogoCommon);

					setchoice= new Choice();
					setchoice.add("please select database");
					setchoice.add(""+hn1);
					if(numOfHostels>1)
					{
						setchoice.add(""+hn2);
						if(numOfHostels>2)
						{
							setchoice.add(""+hn3);
							if(numOfHostels>3)
							{
								setchoice.add(""+hn4);
								if(numOfHostels>4)
								{
									setchoice.add(""+hn5);
								}
							}
						}
							
					}
					
					setchoice.setForeground(Color.BLUE);
					
					setchoice.setFont(new Font("italic",Font.BOLD,16));
					
					setchoice.setBounds(300,80,350,60);
					
					
					delfld= new JTextField("Enter Total Number of Rooms");
					delfld.setBackground(Color.YELLOW);
					
					
					delfld.setBounds(300,120,350,50);
					delfld.setFont(new Font("italic",Font.BOLD,16));
					delfld.setForeground(Color.RED);
					delfld.setBackground(Color.YELLOW);
					
					setbut= new JButton("OK");
					setbut.setBounds(450,190,150,40);
					setbut.setFont(new Font("italic",Font.BOLD,16));
					setbut.setForeground(Color.RED);
					setbut.setBackground(Color.YELLOW);
					
					pnlFiveMain.add(setchoice);
					pnlFiveMain.add(delfld);
					pnlFiveMain.add(setbut);
					
					p2.add(pnlFiveMain);
					setbut.addActionListener(this);
					setchoice.addItemListener(this);
			break;
			
			case 6: //search
					pnlSixMain=new JPanel();
					

					pnlSixMain.setBackground(Color.GRAY);
					pnlSixMain.setLayout(null);
	
					sixMainHeading=new JLabel("SEARCH BY ENROLLMENT");
					sixMainHeading.setForeground(Color.YELLOW);
					sixMainHeading.setFont(fHeading);
					searchTextField=new JTextField("Enter Enrollment to Search Students Data");
					searchTextField.setForeground(Color.WHITE);
					searchTextField.setBackground(Color.BLACK);
					searchTextField.setFont(new Font("Sans Serif",Font.ITALIC,16));
					searchTextField.setColumns(25);
					
					searchSubmit=new JButton("Search Details");
					searchSubmit.setForeground(Color.RED);
					searchSubmit.setBackground(Color.YELLOW);
					searchSubmit.setFont(new Font("Times New Roman",Font.BOLD,20));
					sixMainHeading.setBounds(240,30,650,40);
					searchTextField.setBounds(240,115,340,40);
					searchSubmit.setBounds(300,160,180,30);
					pnlSixMain.add(sixMainHeading);
					pnlSixMain.add(iflagCommon);
					pnlSixMain.add(mlogoCommon);
					pnlSixMain.add(searchTextField);
					pnlSixMain.add(searchSubmit);

					p2.add(pnlSixMain);
					searchSubmit.addActionListener(this);	
			break;
			
			case 7: //about 
					pnlSevenMain=new JPanel();
					pnlSevenMain.setBackground(Color.GRAY);
					pnlSevenMain.setLayout(null);
					
					sevenMainHeading=new JLabel("ABOUT US");
					sevenMainHeading.setForeground(Color.YELLOW);
					sevenMainHeading.setFont(fHeading);

					sevenMainHeading.setBounds(300,30,650,40);
					pnlSevenMain.add(sevenMainHeading);
					pnlSevenMain.add(iflagCommon);
					pnlSevenMain.add(mlogoCommon);

					
					ImageIcon sal=new ImageIcon("images\\salman.png");
					JLabel sal1=new JLabel(sal);
					JLabel sal2= new JLabel();
					sal2.setText("MOHAMMAD SALMAN PADDER");
					JLabel sal3= new JLabel();
					sal3.setText("email: spadder786@gmail.com");
					JLabel sal4= new JLabel();
					sal4.setText("phone: 7740881389");
					
					JLabel sal5= new JLabel();
					sal5.setText("Website: www.spadder.6te.net");
					
					
					//sal1.setVisible(true);
					
					Font saloo= new Font("elephant",Font.BOLD,18);
					sal1.setBounds(20,80,150,150);
					sal2.setBounds(20,205,350,30);
					sal3.setBounds(20,240,360,30);
					sal4.setBounds(20,275,360,30);
					sal5.setBounds(20,305,360,30);
					sal2.setFont(saloo);
					sal3.setFont(saloo);
					sal4.setFont(saloo);
					sal5.setFont(saloo);
					sal2.setForeground(Color.GREEN);
					sal3.setForeground(Color.GREEN);
					sal4.setForeground(Color.GREEN);
					sal5.setForeground(Color.GREEN);
					
					pnlSevenMain.add(sal1);
					pnlSevenMain.add(sal2);
					pnlSevenMain.add(sal3);
					pnlSevenMain.add(sal4);
					pnlSevenMain.add(sal5);
					/////////// zakkkk details
					
						ImageIcon zak=new ImageIcon("images\\zas.png");
					JLabel zak1=new JLabel(zak);
					JLabel zak2= new JLabel();
					zak2.setText("ZAKIR AHMAD SHIEKH");
					JLabel zak3= new JLabel();
					zak3.setText("email: contact@szakir.in");
					
					JLabel zak4= new JLabel();
					zak4.setText("phone: 7740881389");
					
					JLabel zak5= new JLabel();
					zak5.setText("Website: www.szakir.in");
					zak3.setBackground(Color.YELLOW);
					
					zak1.setBounds(420,80,150,150);
					zak2.setBounds(420,205,350,30);
					zak3.setBounds(420,240,360,30);
					zak4.setBounds(420,275,360,30);
					zak5.setBounds(420,305,360,30);
					zak2.setFont(saloo);
					zak3.setFont(saloo);
					zak4.setFont(saloo);
					zak5.setFont(saloo);
					zak2.setForeground(Color.GREEN);
					zak3.setForeground(Color.GREEN);
					zak4.setForeground(Color.GREEN);
					zak5.setForeground(Color.GREEN);
					pnlSevenMain.add(zak1);
					pnlSevenMain.add(zak2);
					pnlSevenMain.add(zak3);
					pnlSevenMain.add(zak4);
					pnlSevenMain.add(zak5);

					p2.add(pnlSevenMain);
			break;
			
			case 8:  //help
					pnlEightMain=new JPanel();
					
					
					pnlEightMain.setBackground(Color.GRAY);
					pnlEightMain.setLayout(null);
	
					eightMainHeading=new JLabel("HELP DESK");
					eightMainHeading.setForeground(Color.YELLOW);
					eightMainHeading.setFont(fHeading);

					eightMainHeading.setBounds(300,30,650,40);
					pnlEightMain.add(eightMainHeading);
					pnlEightMain.add(iflagCommon);
					pnlEightMain.add(mlogoCommon);
					
					JLabel help1= new JLabel();
					help1.setFont(fHeading);
					help1.setText("This is the  trial version of this  software  and here  are some restrictions ");
					
					JLabel help2= new JLabel();
					help2.setFont(fHeading);
					
					help2.setText("like there is a limit of only five hostels and the time period of  28  days. If");
					
					JLabel help3= new JLabel();
					
					
					help3.setText("you want to use the premimum version of this software you can download");
					help3.setFont(fHeading);

					JLabel help4= new JLabel();
					help4.setText("over the website http://www.szakir.in  and easily use it, Because it is havi");
					help4.setFont(fHeading);
					
					JLabel help5= new JLabel();
					help5.setText("ng a Good User Interface so it can be used by anyone");
					help5.setFont(fHeading);
					
					
					JLabel help6= new JLabel();
					help6.setText("for any query email us at:   salman@szakir.in");
					help6.setFont(fHeading);
					
					help1.setForeground(Color.BLUE);
					help2.setForeground(Color.BLUE);
					help3.setForeground(Color.BLUE);
					help4.setForeground(Color.BLUE);
					help5.setForeground(Color.BLUE);
					help6.setForeground(Color.BLUE);
					
					pnlEightMain.add(help1);
					pnlEightMain.add(help2);
					pnlEightMain.add(help3);
					pnlEightMain.add(help4);
					pnlEightMain.add(help5);
					pnlEightMain.add(help6);
					
					help1.setBounds(20,80,800,30);
					help2.setBounds(20,125,800,30);
					help3.setBounds(20,170,800,30);
					help4.setBounds(20,215,800,30);
					help5.setBounds(20,260,800,30);
					help6.setBounds(200,400,800,30);

					p2.add(pnlEightMain);
			break;
		}
		
	}
	
	
	




	//display searched data on main frames Sixth frame...
	
	void displaySearchedData()
	{
		//JLabel nameOne,nameTwo,emailOne,emailTwo,roomOne,roomTwo,mobileOne,mobileTwo,dobOne,dobTwo,enrollmentOne,enrollmentTwo,addressOne,addressTwo,hostelOne,hostelTwo;
		if(searchDisplayed==1)
		{
			//remove all these from pannel
			pnlSixMain.remove(nameOne);
			pnlSixMain.remove(nameTwo);
			pnlSixMain.remove(emailOne);
			pnlSixMain.remove(emailTwo);
			pnlSixMain.remove(roomOne);
			pnlSixMain.remove(roomTwo);
			pnlSixMain.remove(mobileOne);
			pnlSixMain.remove(mobileTwo);
			pnlSixMain.remove(dobOne);
			pnlSixMain.remove(dobTwo);
			pnlSixMain.remove(enrollmentOne);
			pnlSixMain.remove(enrollmentTwo);
			pnlSixMain.remove(addressOne);
			pnlSixMain.remove(addressTwo);
			pnlSixMain.remove(hostelOne);
			pnlSixMain.remove(hostelTwo);
		}
		
		nameOne=new JLabel("Name");
		nameTwo=new JLabel(searchName);
		emailOne=new JLabel("Email");
		emailTwo=new JLabel(searchEmail);
		roomOne=new JLabel("Room Number");
		roomTwo=new JLabel(searchRoom);
		mobileOne=new JLabel("Mobile");
		mobileTwo=new JLabel(searchMobile);
		dobOne=new JLabel("D.O.B");
		dobTwo=new JLabel(searchDob);
		enrollmentOne=new JLabel("Enrollment");
		enrollmentTwo=new JLabel(searchEnrollment);
		addressOne=new JLabel("Address");
		addressTwo=new JLabel(searchAddress);
		hostelOne=new JLabel("Hostel");
		hostelTwo=new JLabel(searchHostel);

		nameOne.setForeground(Color.YELLOW);
		nameTwo.setForeground(Color.RED);
		emailOne.setForeground(Color.YELLOW);
		emailTwo.setForeground(Color.RED);
		roomOne.setForeground(Color.YELLOW);
		roomTwo.setForeground(Color.RED);
		mobileOne.setForeground(Color.YELLOW);
		mobileTwo.setForeground(Color.RED);
		dobOne.setForeground(Color.YELLOW);
		dobTwo.setForeground(Color.RED);
		enrollmentOne.setForeground(Color.YELLOW);
		enrollmentTwo.setForeground(Color.RED);
		addressOne.setForeground(Color.YELLOW);
		addressTwo.setForeground(Color.RED);
		hostelOne.setForeground(Color.YELLOW);
		hostelTwo.setForeground(Color.RED);


		nameOne.setFont(new Font("Times New Roman",Font.BOLD,15));
		nameTwo.setFont(new Font("Times New Roman",Font.ITALIC,20));
		emailOne.setFont(new Font("Times New Roman",Font.BOLD,15));
		emailTwo.setFont(new Font("Times New Roman",Font.ITALIC,20));
		roomOne.setFont(new Font("Times New Roman",Font.BOLD,15));
		roomTwo.setFont(new Font("Times New Roman",Font.ITALIC,20));
		mobileOne.setFont(new Font("Times New Roman",Font.BOLD,15));
		mobileTwo.setFont(new Font("Times New Roman",Font.ITALIC,20));
		dobOne.setFont(new Font("Times New Roman",Font.BOLD,15));
		dobTwo.setFont(new Font("Times New Roman",Font.ITALIC,20));
		enrollmentOne.setFont(new Font("Times New Roman",Font.BOLD,15));
		enrollmentTwo.setFont(new Font("Times New Roman",Font.ITALIC,20));
		addressOne.setFont(new Font("Times New Roman",Font.BOLD,15));
		addressTwo.setFont(new Font("Times New Roman",Font.ITALIC,20));
		hostelOne.setFont(new Font("Times New Roman",Font.BOLD,15));
		hostelTwo.setFont(new Font("Times New Roman",Font.ITALIC,20));


		nameOne.setBounds(100,300,100,30);
		nameTwo.setBounds(250,300,150,30);
		emailOne.setBounds(450,300,100,30);
		emailTwo.setBounds(600,300,150,30);
		roomOne.setBounds(100,330,100,30);
		roomTwo.setBounds(250,330,150,30);
		mobileOne.setBounds(450,330,100,30);
		mobileTwo.setBounds(600,330,150,30);
		dobOne.setBounds(100,360,100,30);
		dobTwo.setBounds(250,360,150,30);
		enrollmentOne.setBounds(450,360,100,30);
		enrollmentTwo.setBounds(600,360,150,30);
		addressOne.setBounds(100,390,100,30);
		addressTwo.setBounds(250,390,150,30);
		hostelOne.setBounds(450,390,100,30);
		hostelTwo.setBounds(600,390,150,30);

		pnlSixMain.add(nameOne);
		pnlSixMain.add(nameTwo);
		pnlSixMain.add(emailOne);
		pnlSixMain.add(emailTwo);
		pnlSixMain.add(roomOne);
		pnlSixMain.add(roomTwo);
		pnlSixMain.add(mobileOne);
		pnlSixMain.add(mobileTwo);
		pnlSixMain.add(dobOne);
		pnlSixMain.add(dobTwo);
		pnlSixMain.add(enrollmentOne);
		pnlSixMain.add(enrollmentTwo);
		pnlSixMain.add(addressOne);
		pnlSixMain.add(addressTwo);
		pnlSixMain.add(hostelOne);
		pnlSixMain.add(hostelTwo);

		searchDisplayed=1;
	}//end of displaying search results....
	











	//load the update form on mainPanel of center panels update panel....
	void loadUpdateForm()
	{
		updateDetailAdded=1;
		updateName=new JTextField(""+previousName);
		updateRoom=new JTextField(""+previousRoom);
		updateMobile=new JTextField(""+previousMobile);
		updateHostel=new Choice();
		updateHostel.add("Select Hostel for Allocation");
					if(numOfHostels>1)
					{
						updateHostel.add(" "+hn2);
						if(numOfHostels>2)
						{
							updateHostel.add(" "+hn3);
							if(numOfHostels>3)
							{
								updateHostel.add(" "+hn4);
								if(numOfHostels>4)
								{
									updateHostel.add(" "+hn5);
								}
							}
						}
					} 
		updateHostel.select(0);
		
		newUpdateSubmit=new JButton("Update");
		
		updateName.setForeground(Color.RED);
		updateName.setBackground(Color.BLACK);
		updateName.setFont(new Font("Sans Serif",Font.ITALIC,16));
		updateName.setColumns(20);
		updateName.setEditable(false);     //important to disable name change....

		updateRoom.setForeground(Color.RED);
		updateRoom.setBackground(Color.BLACK);
		updateRoom.setFont(new Font("Sans Serif",Font.ITALIC,16));
		updateRoom.setColumns(20);
		
		updateMobile.setForeground(Color.RED);
		updateMobile.setBackground(Color.BLACK);
		updateMobile.setFont(new Font("Sans Serif",Font.ITALIC,16));
		updateMobile.setColumns(20);

		updateHostel.setForeground(Color.RED);
		updateHostel.setBackground(Color.BLACK);
		updateHostel.setFont(new Font("Sans Serif",Font.ITALIC,16));

		newUpdateSubmit.setForeground(Color.RED);
		newUpdateSubmit.setBackground(Color.WHITE);
		newUpdateSubmit.setFont(new Font("Sans Serif",Font.BOLD,16));


		updateName.setBounds(250,260,300,30);
		updateRoom.setBounds(250,295,300,30);
		updateMobile.setBounds(250,330,300,30);
		updateHostel.setBounds(250,365,300,30);
		newUpdateSubmit.setBounds(310,395,160,30);

		pnlThreeMain.add(updateName);
		pnlThreeMain.add(updateRoom);
		pnlThreeMain.add(updateMobile);
		pnlThreeMain.add(updateHostel);
		pnlThreeMain.add(newUpdateSubmit);

		newUpdateSubmit.addActionListener(this);
		updateHostel.addItemListener(this);
	}













	//for handling top menu...
	public void actionPerformed(ActionEvent ae1)
	{
		if(ae1.getSource()==b1)  
		{
			previousSelected=menuSelected;
			menuSelected=1;
			//pnlOneMain.setVisible(true);
			loadMainPanel();
		}
		else if(ae1.getSource()==b2)
		{
			previousSelected=menuSelected;
			menuSelected=2;
			//pnlTwoMain.setVisible(true);
			loadMainPanel();
		}
		else if(ae1.getSource()==b3)
		{
			previousSelected=menuSelected;
			menuSelected=3;
			//pnlThreeMain.setVisible(true);
			loadMainPanel();
		}
		else if(ae1.getSource()==b4)
		{
			previousSelected=menuSelected;
			menuSelected=4;
			//pnlFourMain.setVisible(true);
			loadMainPanel();
		}
		else if(ae1.getSource()==b5)
		{
			previousSelected=menuSelected;
			menuSelected=5;
			//pnlFiveMain.setVisible(true);
			loadMainPanel();
		}
		else if(ae1.getSource()==b6)
		{
			previousSelected=menuSelected;
			menuSelected=6;
			//pnlSixMain.setVisible(true);
			loadMainPanel();
		}
		else if(ae1.getSource()==b7)
		{
			previousSelected=menuSelected;
			menuSelected=7;
			//pnlSevenMain.setVisible(true);
			loadMainPanel();
		}
		else if(ae1.getSource()==b8)
		{
			previousSelected=menuSelected;
			menuSelected=8;
			//pnlEightMain.setVisible(true);
			loadMainPanel();
		}
		else if(ae1.getSource()==submitRegister)
		{
			//JOptionPane.showMessageDialog(f1,"Form submit button clicked");
			//validate form data.... n,e,rn,mob,birthd,enroll,addr,hostel_name;
			n=name.getText();
			e=email.getText();
			rn=room_no.getText();
			mob=mobile.getText();
			birthd=dob.getText();
			enroll=enrollment.getText();
			addr=address.getText();
			
			if(n.equals("") || n.equals("Enter Student Name"))
				JOptionPane.showMessageDialog(f1,"Please Enter the Name of the Student.");
			else if(e.equals("") || e.equals("Enter Student Email"))
				JOptionPane.showMessageDialog(f1,"Please Enter the Email of the Student.");
			else if(rn.equals("") || rn.equals("Provide a Room Number"))
				JOptionPane.showMessageDialog(f1,"Please Enter the Room Number to be Allocated");
			else if(mob.equals("") || mob.equals("Mobile Number"))
				JOptionPane.showMessageDialog(f1,"Please Enter the Mobile Number of the Student.");
			else if(birthd.equals("") || birthd.equals("D.O.B as dd/mm/yyyy"))
				JOptionPane.showMessageDialog(f1,"Please Enter the D.O.B of the Student.");
			else if(enroll.equals("") || enroll.equals("Enrollment Number"))
				JOptionPane.showMessageDialog(f1,"Please Enter the Enrollment or Hostel Card Number of the Student.");
			else if(addr.equals("") || addr.equals("Enter Student Address"))
				JOptionPane.showMessageDialog(f1,"Please Enter the Address of the Student.");
			else if(hostelSelected==0)
				JOptionPane.showMessageDialog(f1,"Please Select a particular Hostel out of the given for Student Hostel Allocation.");
			else // try to insert into database....
			{
				//JOptionPane.showMessageDialog(f1,"All the data field are Valid..."+n+" "+e+" "+rn+" "+mob+" "+birthd+" "+enroll+" "+addr+" "+hostel_name);
				
				try{
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
						stm=con.createStatement();
						ResultSet r4=stm.executeQuery("SELECT * FROM "+hostel_name+" WHERE room_no='"+rn+"'");
						int count=0;
						while(r4.next())
						{
							count++;
						}
						if(count>=roomLimit)
						{
							JOptionPane.showMessageDialog(f1,"There are already 4 Students in Hostel: "+hostel_name+" Room No: "+rn);
						}
						else
						{	
							String ho1=hn1.replaceAll("\\s+","");
							stm.executeUpdate("DELETE FROM "+ho1+" WHERE enrollment='"+enroll+"'");
							if(numOfHostels>1)
							{
								String ho2=hn2.replaceAll("\\s+","");
								stm.executeUpdate("DELETE FROM "+ho2+" WHERE enrollment='"+enroll+"'");
								if(numOfHostels>2)
								{
									String ho3=hn3.replaceAll("\\s+","");
									stm.executeUpdate("DELETE FROM "+ho3+" WHERE enrollment='"+enroll+"'");
									if(numOfHostels>3)
									{
										String ho4=hn4.replaceAll("\\s+","");
										stm.executeUpdate("DELETE FROM "+ho4+" WHERE enrollment='"+enroll+"'");
										if(numOfHostels>4)
										{
											String ho5=hn5.replaceAll("\\s+","");
											stm.executeUpdate("DELETE FROM "+ho5+" WHERE enrollment='"+enroll+"'");
										}
									}
								}
							}
							stm.executeUpdate("INSERT INTO "+hostel_name+" VALUES ('"+n+"','"+e+"','"+rn+"','"+mob+"','"+birthd+"','"+enroll+"','"+addr+"','"+actualHostelName+"')");
							ResultSet r5=stm.executeQuery("SELECT * FROM "+hostel_name+" WHERE room_no='"+rn+"'");
							int countUpdate=0;
							while(r5.next())
							{
								countUpdate++;
							}
							if(countUpdate>count)
							{
								ResultSet r9=stm.executeQuery("SELECT * FROM lastadded");
								int countLast=0;
								while(r9.next())
								{
									countLast++;
								}
								if(countLast>=1)
									stm.executeUpdate("DELETE FROM lastadded");
								String lastadded="lastadded";
								stm.executeUpdate("INSERT INTO "+lastadded+" VALUES ('"+n+"','"+enroll+"','"+mob+"','"+rn+"','"+actualHostelName+"')");
								JOptionPane.showMessageDialog(f1,"Student "+n+" has been Registered for Hostel "+actualHostelName+" and Room Number "+rn+" has been Allocated");
								loadLastAdded();    //reload Last Added Student...
							}
							else
							{
								JOptionPane.showMessageDialog(f1,"Registration Failed... Something went Wrong");
							}
						}
				}
				catch(Exception ee3)
				{
					JOptionPane.showMessageDialog(f1,"Something went Wrong. Please try again. "+ee3);
				}
			}
		}//end register handling...
		
		
		else if(ae1.getSource()==submitNameSearch)
		{
			try{
					String n=searchByName.getText();
					
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
					stm=con.createStatement();
					String h1=hn1.replaceAll("\\s+","");
					ResultSet r1=stm.executeQuery("SELECT name,enrollment,hostel_name,room_no FROM "+h1+" WHERE name like('%"+n+"%') or name like('"+n+"%') or name like('%"+n+"')");
					while(r1.next())
					{
						nameSr.add(r1.getString("name"));
						enrollmentSr.add(r1.getString("enrollment"));
						hostelSr.add(r1.getString("hostel_name"));
						room_numberSr.add(r1.getString("room_no"));
						
						total++;
						//JOptionPane.showMessageDialog(f1,"Searched Result Name"+r4.getString("name")+" with Enrollment : "+r4.getString("enrollment"));
					}
					if(numOfHostels>1)
					{
						String h2=hn2.replaceAll("\\s+","");
						ResultSet r2=stm.executeQuery("SELECT name,enrollment,hostel_name,room_no FROM "+h2+" WHERE name like('%"+n+"%') or name like('"+n+"%') or name like('%"+n+"')");
						while(r2.next())
						{
							nameSr.add(r2.getString("name"));
							enrollmentSr.add(r2.getString("enrollment"));
							hostelSr.add(r2.getString("hostel_name"));
							room_numberSr.add(r2.getString("room_no"));
							total++;
							//JOptionPane.showMessageDialog(f1,"Searched Result Name"+r4.getString("name")+" with Enrollment : "+r4.getString("enrollment"));
						}
						if(numOfHostels>2)
						{
							String h3=hn3.replaceAll("\\s+","");
							ResultSet r3=stm.executeQuery("SELECT name,enrollment,hostel_name,room_no FROM "+h3+" WHERE name like('%"+n+"%') or name like('"+n+"%') or name like('%"+n+"')");
							while(r3.next())
							{
								nameSr.add(r3.getString("name"));
								enrollmentSr.add(r3.getString("enrollment"));
								hostelSr.add(r3.getString("hostel_name"));
								room_numberSr.add(r3.getString("room_no"));
								total++;
								//JOptionPane.showMessageDialog(f1,"Searched Result Name"+r4.getString("name")+" with Enrollment : "+r4.getString("enrollment"));
							}
							if(numOfHostels>3)
							{
								String h4=hn4.replaceAll("\\s+","");
								ResultSet r4=stm.executeQuery("SELECT name,enrollment,hostel_name,room_no FROM "+h4+" WHERE name like('%"+n+"%') or name like('"+n+"%') or name like('%"+n+"')");
								while(r4.next())
								{
									nameSr.add(r4.getString("name"));
									enrollmentSr.add(r4.getString("enrollment"));
									hostelSr.add(r4.getString("hostel_name"));
									room_numberSr.add(r4.getString("room_no"));
									total++;
									//JOptionPane.showMessageDialog(f1,"Searched Result Name"+r4.getString("name")+" with Enrollment : "+r4.getString("enrollment"));
								}
								if(numOfHostels>4)
								{
									String h5=hn5.replaceAll("\\s+","");
									ResultSet r5=stm.executeQuery("SELECT name,enrollment,hostel_name,room_no FROM "+h5+" WHERE name like('%"+n+"%') or name like('"+n+"%') or name like('%"+n+"')");
									while(r5.next())
									{
										nameSr.add(r5.getString("name"));
										enrollmentSr.add(r5.getString("enrollment"));
										hostelSr.add(r5.getString("hostel_name"));
										room_numberSr.add(r5.getString("room_no"));
										total++;
										//JOptionPane.showMessageDialog(f1,"Searched Result Name"+r4.getString("name")+" with Enrollment : "+r4.getString("enrollment"));
									}
						
								}
						
							}
						
						}
					}
					if(total==0)
					{
						JOptionPane.showMessageDialog(f1,"No Results Found for your Enter Data");
					}
					else
					{
						loadSearchedData();
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(f1,"Unable to Search Student Details...something went wrong"+e);
				}
			
		}//end right search panel handling
		
		else if(ae1.getSource()==nextSearch)
		{
			iteration++;
			loadSearchedData();
		}
		
		else if(ae1.getSource()==delSubmit)
		{
			//JOptionPane.showMessageDialog(f1,"Del Button Clicked");
			delEnrollment=delTextField.getText();
			if(delEnrollment.equals("") || delEnrollment.equals("Enter Student Enrollment or Hostel Id Number"))
				JOptionPane.showMessageDialog(f1,"Please Enter an Enrollment to Delete Student Details.");
			else
			{
				try{
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
						stm=con.createStatement();
						
						int present=0;
						int deleted=0;
						String h1=hn1.replaceAll("\\s+","");
						ResultSet rr1=stm.executeQuery("SELECT * FROM "+h1+" WHERE enrollment='"+delEnrollment+"'");
						while(rr1.next())
						{
							present++;
						}
						if(present>0)
						{
							stm.executeUpdate("DELETE FROM "+h1+" WHERE enrollment='"+delEnrollment+"'");
							deleted++;
						}
						if(numOfHostels>1)
						{
							String h2=hn2.replaceAll("\\s+","");
							present=0;
							ResultSet rr2=stm.executeQuery("SELECT * FROM "+h2+" WHERE enrollment='"+delEnrollment+"'");
							while(rr2.next())
							{
								present++;
							}
							if(present>0)
							{
								stm.executeUpdate("DELETE FROM "+h2+" WHERE enrollment='"+delEnrollment+"'");
								deleted++;
							}
							if(numOfHostels>2)
							{
								String h3=hn3.replaceAll("\\s+","");
								present=0;
								ResultSet rr3=stm.executeQuery("SELECT * FROM "+h3+" WHERE enrollment='"+delEnrollment+"'");
								while(rr3.next())
								{
									present++;
								}
								if(present>0)
								{
									stm.executeUpdate("DELETE FROM "+h3+" WHERE enrollment='"+delEnrollment+"'");
									deleted++;
								}
								if(numOfHostels>3)
								{
									String h4=hn4.replaceAll("\\s+","");
									present=0;
									ResultSet rr4=stm.executeQuery("SELECT * FROM "+h4+" WHERE enrollment='"+delEnrollment+"'");
									while(rr4.next())
									{
										present++;
									}
									if(present>0)
									{
										stm.executeUpdate("DELETE FROM "+h4+" WHERE enrollment='"+delEnrollment+"'");
										deleted++;
									}
									if(numOfHostels>4)
									{
										String h5=hn5.replaceAll("\\s+","");
										present=0;
										ResultSet rr5=stm.executeQuery("SELECT * FROM "+h5+" WHERE enrollment='"+delEnrollment+"'");
										while(rr5.next())
										{
											present++;
										}
										if(present>0)
										{
											stm.executeUpdate("DELETE FROM "+h5+" WHERE enrollment='"+delEnrollment+"'");
											deleted++;
										}
									}
								}
							}
						}
						if(deleted>0)
						{
							JOptionPane.showMessageDialog(f1,"Sucessfully Deleted the Details of Student with Enrollment Number : "+delEnrollment);
						}
						else
						{
							JOptionPane.showMessageDialog(f1,"There is no Student Present in our Database with Enrollment Number : "+delEnrollment);
						}
					}
					catch(Exception e11)
					{
						JOptionPane.showMessageDialog(f1,"Something went Wrong. Unable to Delete Student Details."+e11);	
					}
			}
		}//end delSubmit....

		else if(ae1.getSource()==updateSubmit)
		{
			//JOptionPane.showMessageDialog(f1,"Update Clicked");
			updateEnrollment=updateTextField.getText();
			if(updateEnrollment.equals("") || updateEnrollment.equals("Enter Student Enrollment to Update Details"))
				JOptionPane.showMessageDialog(f1,"Please Enter the Enrollment or Hostel Card number of the Student to Update Details");
			else
			{
					

				try{
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
						stm=con.createStatement();
						
						previousName="";
						previousRoom="";
						previousMobile="";
						previousHostel="";
						int present=0;
						String h1=hn1.replaceAll("\\s+","");
						ResultSet rr1=stm.executeQuery("SELECT name,room_no,mobile,hostel_name FROM "+h1+" WHERE enrollment='"+updateEnrollment+"'");
						while(rr1.next())
						{
							previousName=rr1.getString("name");
							previousRoom=rr1.getString("room_no");
							previousMobile=rr1.getString("mobile");
							previousHostel=rr1.getString("hostel_name");
							present++;
						}
						if(numOfHostels>1)
						{
							String h2=hn2.replaceAll("\\s+","");
							ResultSet rr2=stm.executeQuery("SELECT name,room_no,mobile,hostel_name FROM "+h2+" WHERE enrollment='"+updateEnrollment+"'");
							while(rr2.next())
							{
								previousName=rr2.getString("name");
								previousRoom=rr2.getString("room_no");
								previousMobile=rr2.getString("mobile");
								previousHostel=rr2.getString("hostel_name");
								present++;
							}
							if(numOfHostels>2)
							{
								String h3=hn3.replaceAll("\\s+","");
								ResultSet rr3=stm.executeQuery("SELECT name,room_no,mobile,hostel_name FROM "+h3+" WHERE enrollment='"+updateEnrollment+"'");
								while(rr3.next())
								{
									previousName=rr3.getString("name");
									previousRoom=rr3.getString("room_no");
									previousMobile=rr3.getString("mobile");
									previousHostel=rr3.getString("hostel_name");
									present++;
								}
								if(numOfHostels>3)
								{
									String h4=hn4.replaceAll("\\s+","");
									ResultSet rr4=stm.executeQuery("SELECT name,room_no,mobile,hostel_name FROM "+h4+" WHERE enrollment='"+updateEnrollment+"'");
									while(rr4.next())
									{
										previousName=rr4.getString("name");
										previousRoom=rr4.getString("room_no");
										previousMobile=rr4.getString("mobile");
										previousHostel=rr4.getString("hostel_name");
										present++;
									}
									if(numOfHostels>4)
									{
										String h5=hn5.replaceAll("\\s+","");
										ResultSet rr5=stm.executeQuery("SELECT name,room_no,mobile,hostel_name FROM "+h5+" WHERE enrollment='"+updateEnrollment+"'");
										while(rr5.next())
										{
											previousName=rr5.getString("name");
											previousRoom=rr5.getString("room_no");
											previousMobile=rr5.getString("mobile");
											previousHostel=rr5.getString("hostel_name");
											present++;
										}
									}
								}
							}
						}
						if(present>0)
						{
							present=0;
							JOptionPane.showMessageDialog(f1,"Update Your Details by submitting the below form.... Rn:"+previousRoom+" Mob: "+previousMobile+" and Hostel: "+previousHostel);
							if(updateDetailAdded==1)
							{
								pnlThreeMain.remove(updateName);
								pnlThreeMain.remove(updateRoom);
								pnlThreeMain.remove(updateMobile);
								pnlThreeMain.remove(updateHostel);
								pnlThreeMain.remove(newUpdateSubmit);
							}
							loadUpdateForm();
						}
						else
						{
							JOptionPane.showMessageDialog(f1,"There is no Student with the Enrollment Number "+updateEnrollment+" in our Database");
						}
					}
					catch(Exception e11)
					{
						JOptionPane.showMessageDialog(f1,"Something went Wrong. Unable to Fetch Student Details."+e11);	
					}
			}
		}//end updateSubmit....



		else if(ae1.getSource()==newUpdateSubmit)
		{
			newRoom=updateRoom.getText().toString();
			newMobile=updateMobile.getText().toString();
			if(updatedHostel==1)
			{
				if(newRoom.equals("") || newMobile.equals(""))
				JOptionPane.showMessageDialog(f1,"Room Number and Mobile Number should not be Empty.");
				else
				{
						newHostel=updateHostel.getSelectedItem().toString();
						try
						{
								Class.forName("com.mysql.jdbc.Driver");
								con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
								stm=con.createStatement();
								String hpre=previousHostel.replaceAll("\\s+","");
								updateEnrollment=updateEnrollment.replaceAll("\\s+","");
								String hnew=newHostel.replaceAll("\\s+","");
								ResultSet rs=stm.executeQuery("SELECT * FROM "+hpre+" WHERE enrollment='"+updateEnrollment+"'");
								while(rs.next())
								{
									updaten=rs.getString(1);
									updatee=rs.getString(2);
									updatern=rs.getString(3);
									updatemb=rs.getString(4);
									updatedb=rs.getString(5);
									updateen=rs.getString(6);
									updatead=rs.getString(7);
									updatehn=rs.getString(8);
								}
								
								ResultSet r13=stm.executeQuery("SELECT * FROM "+hnew+" WHERE room_no='"+newRoom+"'");
								while(r13.next())
								{
									i++;
								}
								if(i>=4)
								{
									i=0;
									JOptionPane.showMessageDialog(f1,"There are already 4 Students in your choosed room. Please try different Hostel or room");
								}
								else
								{
									i=0;
									stm.executeUpdate("DELETE FROM "+hpre+" WHERE enrollment='"+updateEnrollment+"'");
									stm.executeUpdate("INSERT INTO "+hnew+" VALUES('"+updaten+"','"+updatee+"','"+newRoom+"','"+newMobile+"','"+updatedb+"','"+updateen+"','"+updatead+"','"+newHostel+"')");
									//submit data, choose updateDetailAdded=0; and remove all the updateTextFields like updateName updateMobile...
									updateDetailAdded=0;  //means no form is now shown
									pnlThreeMain.remove(updateName);
									pnlThreeMain.remove(updateRoom);
									pnlThreeMain.remove(updateMobile);
									pnlThreeMain.remove(updateHostel);
									pnlThreeMain.remove(newUpdateSubmit);
									
									JOptionPane.showMessageDialog(f1,"Sucessfully Updated "+previousName+"\\'s data");
								}
								
						}
						catch(Exception e12)
						{
								JOptionPane.showMessageDialog(f1,"Unable to Update Student Details. Something went Wrong"+e12);
						}
						
				}
			}
			else
			{
				JOptionPane.showMessageDialog(f1,"Please select a particular Hostel.");
			}
		}//end Update submitted ...



		//remaining still.....menu 4 and 5... to handle here



		else if(ae1.getSource()==searchSubmit)
		{
			searchEnrollment=searchTextField.getText().toString();
			if(searchEnrollment.equals("") || searchEnrollment.equals("Enter Enrollment to Search Students Data"))
				JOptionPane.showMessageDialog(f1,"Please Enter Enrollment of Hostel card Number to Search Students data");
			else
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
					stm=con.createStatement();

					int present=0;
						String h1=hn1.replaceAll("\\s+","");
						ResultSet rr1=stm.executeQuery("SELECT * FROM "+h1+" WHERE enrollment='"+searchEnrollment+"'");
						while(rr1.next())
						{
							//String searchName,searchEmail,searchRoom,searchMobile,searchDob,searchEnrollment,searchAddress,searchHostel;
							searchName=rr1.getString(1);
							searchEmail=rr1.getString(2);
							searchRoom=rr1.getString(3);
							searchMobile=rr1.getString(4);
							searchDob=rr1.getString(5);
							searchEnrollment=rr1.getString(6);
							searchAddress=rr1.getString(7);
							searchHostel=rr1.getString(8);
							present++;
						}
						if(numOfHostels>1)
						{
							String h2=hn2.replaceAll("\\s+","");
							ResultSet rr2=stm.executeQuery("SELECT * FROM "+h2+" WHERE enrollment='"+searchEnrollment+"'");
							while(rr2.next())
							{
								searchName=rr2.getString(1);
								searchEmail=rr2.getString(2);
								searchRoom=rr2.getString(3);
								searchMobile=rr2.getString(4);
								searchDob=rr2.getString(5);
								searchEnrollment=rr2.getString(6);
								searchAddress=rr2.getString(7);
								searchHostel=rr2.getString(8);
								present++;
							}
							if(numOfHostels>2)
							{
								String h3=hn3.replaceAll("\\s+","");
								ResultSet rr3=stm.executeQuery("SELECT * FROM "+h3+" WHERE enrollment='"+searchEnrollment+"'");
								while(rr3.next())
								{
									searchName=rr3.getString(1);
									searchEmail=rr3.getString(2);
									searchRoom=rr3.getString(3);
									searchMobile=rr3.getString(4);
									searchDob=rr3.getString(5);
									searchEnrollment=rr3.getString(6);
									searchAddress=rr3.getString(7);
									searchHostel=rr3.getString(8);
									present++;
								}
								if(numOfHostels>3)
								{
									String h4=hn4.replaceAll("\\s+","");
									ResultSet rr4=stm.executeQuery("SELECT * FROM "+h4+" WHERE enrollment='"+searchEnrollment+"'");
									while(rr4.next())
									{
										searchName=rr4.getString(1);
										searchEmail=rr4.getString(2);
										searchRoom=rr4.getString(3);
										searchMobile=rr4.getString(4);
										searchDob=rr4.getString(5);
										searchEnrollment=rr4.getString(6);
										searchAddress=rr4.getString(7);
										searchHostel=rr4.getString(8);
										present++;
									}
									if(numOfHostels>4)
									{
										String h5=hn5.replaceAll("\\s+","");
										ResultSet rr5=stm.executeQuery("SELECT * FROM "+h5+" WHERE enrollment='"+searchEnrollment+"'");
										while(rr5.next())
										{
											searchName=rr5.getString(1);
											searchEmail=rr5.getString(2);
											searchRoom=rr5.getString(3);
											searchMobile=rr5.getString(4);
											searchDob=rr5.getString(5);
											searchEnrollment=rr5.getString(6);
											searchAddress=rr5.getString(7);
											searchHostel=rr5.getString(8);
											present++;
										}
									}
								}
							}
						}
						if(present>0)
						{
							present=0;
							JOptionPane.showMessageDialog(f1,"Your Data will be Now Shown..");
							displaySearchedData();
						}
						else
						{
							JOptionPane.showMessageDialog(f1,"There is no Student with the Enrollment Number "+searchEnrollment+" in our Database");
						}
					
				}
				catch(Exception e14)
				{
					JOptionPane.showMessageDialog(f1,"Something went wrong. Unable to Search Students Data."+e14);
				}
			}
		}//end search...
		
		
		
		//for settings...
		
		else if(ae1.getSource()==setbut)
		{
			if(hostelSel==1)
			{
				totalAvailable=delfld.getText().toString();
				if(totalAvailable.equals(""))
				{
					JOptionPane.showMessageDialog(f1,"Please Enter Values of Total Available");
				}
				else
				{
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
						stm=con.createStatement();
						String totalrooms="totalrooms";
						String hh1=hn1.replaceAll("\\s+","");
						String hh2=hn2.replaceAll("\\s+","");
						String hh3=hn3.replaceAll("\\s+","");
						String hh4=hn4.replaceAll("\\s+","");
						String hh5=hn5.replaceAll("\\s+","");
						
						String qrn="CREATE TABLE IF NOT EXISTS "+totalrooms+" ("
																								+hh1+" TEXT,"
																								+hh2+" TEXT,"
																								+hh3+" TEXT,"
																								+hh4+" TEXT,"
																								+hh5+" TEXT"
																								+")";
						stm.executeUpdate(qrn);
						//insert default valued one query...
						//stm.executeUpdate("UPDATE table totalrooms set "+hh1+"='"+totalAvailable+"'");
						JOptionPane.showMessageDialog(f1,"Successfully Updated");
					}
					catch(Exception e44)
					{
						JOptionPane.showMessageDialog(f1,"Something went wrong.."+e44);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(f1,"Please Select a particular Hostel");
			}
		}//settings end

	}	
		
		
		
		
		
		
		
		
		
	public void itemStateChanged(ItemEvent ie1)
	{
			if(menuSelected==3)
			{
				if(updateHostel.getSelectedItem().equals("Select Hostel for Allocation"))
					updatedHostel=0;
				else
				{
					updatedHostel=1;
					newHostel=updateHostel.getSelectedItem().toString();
				}
			}
			else if(menuSelected==5)
			{
				if(setchoice.getSelectedItem().equals("please select database"))
				{
						hostelSel=0;
						JOptionPane.showMessageDialog(f1,"Select Option...");
				}
				
				else
				{
					
					hostelSel=1;
					hostelNameSelected=setchoice.getSelectedItem().toString();
				}
			}
			else
			{
				if(hostel_names.getSelectedItem().equals("Select Hostel for Allocation"))
					hostelSelected=0;
				else
				{
						hostelSelected=1;
						hostel_name=hostel_names.getSelectedItem().toString();
						actualHostelName=hostel_name;
						hostel_name=hostel_name.replaceAll("\\s+","");
				}
			}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//for dynamicity.....
	
		public void run()
		{
			if(Thread.currentThread()==footerThread)
			{
				try{ 
				int i=1365;
				while(i>=-600)
				{
					footer.setBounds(i--,650,600,50);
					Thread.sleep(8);
					if(i==-600)
						i=1365;
				}
				
				}
				catch(Exception ee)
				{
				
				}
			}
			int i=0;
			if(Thread.currentThread()==lblThread)
			{
				while(true)    //two name, four total_rooms,six available and eight total_students
				{
					
					try{
					//hostel 1 details...
					lbltwo.setText(hn1);
					lblfour.setText("1234");   //these values should be changed...
					lblsix.setText("1234");    //these values should be changed...
					lbleight.setText(" "+totalinhostel1);
					Thread.sleep(3000);
					if(numOfHostels>1)
					{
						lbltwo.setText(hn2);
						lblfour.setText("9935");
						lblsix.setText("9935");
						lbleight.setText(" "+totalinhostel2);
						Thread.sleep(3000);
						if(numOfHostels>2)
						{
							lbltwo.setText(hn3);
							lblfour.setText("3333");
							lblsix.setText("3333");
							lbleight.setText(" "+totalinhostel3);
							Thread.sleep(3000);
							if(numOfHostels>3)
							{
								lbltwo.setText(hn4);
								lblfour.setText("4444");
								lblsix.setText("4444");
								lbleight.setText(" "+totalinhostel4);
								Thread.sleep(3000);
								if(numOfHostels>4)
								{
									lbltwo.setText(hn5);
									lblfour.setText("4444");
									lblsix.setText("4444");
									lbleight.setText(" "+totalinhostel5);
									Thread.sleep(3000);
								}
							}
						}
					}
				}catch(Exception e6)
				{
					//....handle it
					System.out.println("Exception occured"+e6);
				}
					
				}
			}
			
		} 
		
		
		

		
	
}


