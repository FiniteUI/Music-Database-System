package FinalDBProj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//upon invocation of main 
public class LoginDriver {
	static CardLayout CL;
	static JPanel main = new JPanel();
	static JButton login   = new JButton("Login");
	static JButton logout  = new JButton("Logout");
	static JButton newUser = new JButton("New User");
	static JButton createUser  = new JButton("Create User");
	static JButton cancel  = new JButton("Cancel");
	static int loggedInUser = 0;
	private static final int ROOTUSERID = 0;
	
	//dbFilepath must be changed depending on user
	static String dbFilepath = "jdbc:sqlite:C:/Users/Trey/Dropbox/DBProject/musicLibraryPathsWithTrey.db";
	
	public static void main(String[] args) {
		JFrame myFrame = new JFrame();
		setDefaults(myFrame);
		JPanel contentPane = (JPanel) myFrame.getContentPane();
		main.setLayout(CL = new CardLayout());
		
		JPanel Card0 = new JPanel();
		Card0.setLayout(new GridLayout(4,1));
		constructCard0(Card0);
		
		JPanel Card1 = new JPanel();
		Card1.setLayout(new GridLayout(2,1));
		constructCard1(Card1);
		
		JPanel Card2 = new JPanel();
		Card2.setLayout(new GridLayout(7,1));
		constructCard2(Card2);
		
		main.add("Card0", Card0);
		main.add("Card1", Card1);
		main.add("Card2", Card2);
		CL.show(main, "Card0");
		
		contentPane.add(main);
		
		myFrame.setVisible(true);
		
	}//end main method
	
	public static void setDefaults(JFrame frame) {
		frame.setSize(500,400);
		frame.setLocation(400, 150);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//end setDefaults method
	
	public int getCurrentUserId(){
		return this.loggedInUser;
	}
	//constructCard0 invokes the main login window panel
	public static void constructCard0(JPanel Card0) {
		//create label
		JPanel labelPanel0 = new JPanel();
		JLabel label0 = new JLabel("Please enter your login information:");
		labelPanel0.add(label0);
		Card0.add(labelPanel0);
		
		//create Username textbox
		JPanel textPanel0 = new JPanel();
		JLabel label1 = new JLabel("Username:");
		textPanel0.add(label1);
		final JTextField Username = new JTextField(10);
		//Username.setColumns(10);
		textPanel0.add(Username);
		Card0.add(textPanel0);
		
		//create Password textbox
		JPanel textPanel1 = new JPanel();
		JLabel label2 = new JLabel("Password:");
		textPanel1.add(label2);
		final JPasswordField Password = new JPasswordField();
		Password.setColumns(10);
		textPanel1.add(Password);
		Card0.add(textPanel1);
		
		//create login and new user buttons
		JPanel btPanel0 = new JPanel();
		btPanel0.add(login);
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_Test myTest = new GUI_Test();
				
				String inputUsername = Username.getText();
				String inputPassword = Password.getText();
				String dbUsername = null;
				String dbPassword = null;
				int    uid = 0;
				boolean repeat = true;
				
				Connection c = null;
				Statement s = null;
				ResultSet results = null;
				
				MainWindow mainWindow = null;

				try {
					
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection(dbFilepath);
					s = c.createStatement();
					results = s.executeQuery("SELECT * FROM USERS;");
					
					while(repeat) {
						while(results.next()){
							dbUsername = results.getString("uname");
							dbPassword = results.getString("pass");
							uid = results.getInt("uid");
							if(inputUsername.equals(dbUsername) && inputPassword.equals(dbPassword)) {
								JOptionPane.showMessageDialog(main, "Login Successful!");
								loggedInUser = uid;
								System.out.println("The current logged in user is: " + String.valueOf(loggedInUser));
								repeat = false;
								Username.setText("");
								Password.setText("");
//								CL.show(main, "Card1");
								s.close();
								c.close();
																
								if (mainWindow != null) {
									mainWindow.dispose();
									
								} 
								else {									
									if(loggedInUser == ROOTUSERID){//Root user sees all playlists
										mainWindow = new MainWindow();
										mainWindow.setVisible(true);
									}
									else{
										mainWindow = new MainWindow(loggedInUser);
										mainWindow.setVisible(true);
									}
								}		
//								myTest.main(null);
								break;
							}
							else {
								repeat = true; //TODO: cause of erroneous call of login failed dialog?
								//System.out.println("repeat true");
								CL.show(main, "Card0");
							}//end if-else		
						}//end while(results.next());
						if(!(inputUsername.equals(dbUsername) && inputPassword.equals(dbPassword))) {
							//JOptionPane.showMessageDialog(main, "Login Failed!");
							s.close();
							c.close();
						}//end if(repeat)
						break;
					}//end while(repeat)
				} 
				catch(Exception except) {
					System.err.println(except.getClass().getName() + ": " + except.getMessage());
				} 
		}});
		btPanel0.add(newUser);
		newUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CL.show(main, "Card2");
			}
		});
		Card0.add(btPanel0);
	}//end constructCard0 method
	
	//constructCard1 invokes a placeholder window, may comment out later
	public static void constructCard1(JPanel Card1) {
		//create sample text label
		JPanel labelPanel0 = new JPanel();
		JLabel label0 = new JLabel("This is a placeholder");
		labelPanel0.add(label0);
		Card1.add(labelPanel0);
		
		//create logout function
		JPanel btPanel0 = new JPanel();
		btPanel0.add(logout);
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CL.show(main, "Card0");
			}
		});
		Card1.add(btPanel0);
		
	}//end construct Card1 method
	
	// constructCard2 invokes the new user entry panel
	public static void constructCard2(JPanel Card2) {
		//create text label
		JPanel labelPanel0 = new JPanel();
		JLabel label0 = new JLabel("Please enter the new User information: ");
		labelPanel0.add(label0);
		Card2.add(labelPanel0);
		
		//create Username textbox
		JPanel textPanel0 = new JPanel();
		JLabel label1 = new JLabel("Username: ");
		textPanel0.add(label1);
		final JTextField Username1 = new JTextField();
		Username1.setColumns(10);
		textPanel0.add(Username1);
		Card2.add(textPanel0);
		
		//create Password textbox
		JPanel textPanel1 = new JPanel();
		JLabel label2 = new JLabel("Password: ");
		textPanel1.add(label2);
		final JPasswordField Password1 = new JPasswordField();
		Password1.setColumns(10);
		textPanel1.add(Password1);
		Card2.add(textPanel1);
		
		//create text label
		JPanel labelPanel1 = new JPanel();
		JLabel label3 = new JLabel("Please re-enter the information: ");
		labelPanel1.add(label3);
		Card2.add(labelPanel1);
		
		//create 2nd Username Textbox
		JPanel textPanel2 = new JPanel();
		JLabel label4 = new JLabel("Username: ");
		textPanel2.add(label4);
		final JTextField Username2 = new JTextField();
		Username2.setColumns(10);
		textPanel2.add(Username2);
		Card2.add(textPanel2);
		
		//create 2nd Password Textbox
		JPanel textPanel3 = new JPanel();
		JLabel label5 = new JLabel("Password: ");
		textPanel3.add(label5);
		final JPasswordField Password2 = new JPasswordField();
		Password2.setColumns(10);
		textPanel3.add(Password2);
		Card2.add(textPanel3);
		
		//create buttons
		JPanel btPanel0 = new JPanel();
		btPanel0.add(createUser);
		createUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CL.show(main, "Card0");
				//System.out.println("Test");
				
				String inputUN1 = Username1.getText();
				String inputUN2 = Username2.getText();
				String inputPW1 = Password1.getText();
				String inputPW2 = Password2.getText();
				
				Connection c = null;
//				Statement s = null;
//				ResultSet results = null;
				
				try {
					Class.forName("org.sqlite.JDBC");//wtf does this do?
					c = DriverManager.getConnection(dbFilepath);
//					s = c.createStatement();
//					results = s.executeQuery("SELECT * FROM USERS;");
					
					/*Manually auto-increments
					 *May have to omit if database auto-increments
					 *for the user*/
//					while(results.next()) {
//					}//end while(results.next())
					if(inputUN1.equals(inputUN2) && inputPW1.equals(inputPW2)) {
						String insert = "INSERT INTO USERS (uname,pass) VALUES( \"" + inputUN1 + "\",\"" + inputPW1 + "\")";
						PreparedStatement Pstmt = c.prepareStatement(insert);
						
						
						try {
							Pstmt.executeUpdate();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							System.out.println("The error is: " + e1);
						}
						
						
						JOptionPane.showMessageDialog(main, "New User Created Successfully!");
						Username1.setText("");
						Username2.setText("");
						Password1.setText("");
						Password2.setText("");
						

						
						
						Pstmt.close();
						c.close();
						
						CL.show(main, "Card0");


					}
					else{
						JOptionPane.showMessageDialog(main, "ERROR: Information does not match\nPlease try again");
					}//end if-else
				} catch(Exception except) {
					
				}//end try-catch
				
			}
		});
		btPanel0.add(cancel);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Username1.setText("");
				Username2.setText("");
				Password1.setText("");
				Password2.setText("");
				CL.show(main, "Card0");
			}
		});
		Card2.add(btPanel0);
	}//end construct Card2 method
	

}//end program :^)