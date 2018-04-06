package FinalDBProj;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.SystemColor;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;

public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ROOTUSERID = 0;
	static int loggedInUser;
	private JPanel contentPane;
	private JTable tableMainDisplay;
	
	/* 
	 * the following fields declare the profile specific data
	 * data in these fields default to the default user
	 * these are the values that are used by the default constructor
	 * 
	 */
	int profile_id = 0;
	int uid = 0;
	int favid = 0;
	String favorites = "default_playlist";
	//method that returns a table with the content of a table model param currently columnNames param does not work gotta overwrite getColumnName in setTableMode
	public JTable initTable(JTable table, TableModel tableModel, final String[] columnNames){		
		table = new JTable() {
			//custom table models are set by overriding the default implementation of getColumnName in JTable class
			//custom table columns is also achievable by overriding the default implementation of getColumnName in DefaultTableModel class then setting the model to 
			// the new DefaultTableModel
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
			
			@Override
			public String getColumnName(int index) {
				return columnNames[index];
			};
		};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBackground(Color.WHITE);
		table.setFocusable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(tableModel);	

		return table;
		
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		System.out.println("The user in the main window is : " + String.valueOf(loggedInUser));
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					if(loggedInUser == ROOTUSERID){
//						MainWindow frame = new MainWindow();
//						frame.setVisible(true);
//					}
//					else{
//						MainWindow frame = new MainWindow(loggedInUser);
//
//						frame.setVisible(true);
//					}
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][][][][80.00,grow][][][][][][grow][][][][30.00][][][grow][-3.00]", "[][][][grow][][][][][][][][][][][16.00][grow][][]"));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(rigidArea, "cell 0 0");
		
		JPanel panelMainTable = new JPanel();
		contentPane.add(panelMainTable, "cell 1 1 18 13,grow");
		panelMainTable.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][grow][grow][44.00,grow][72.00,grow]", "[][][][][][][][][][][]"));
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.control);
		scrollPane.setEnabled(false);
		scrollPane.setFocusable(false);
		scrollPane.setBounds(10, 78, 1264, 672);
		panelMainTable.add(scrollPane, "cell 1 0 22 11,grow");
		
		JButton btnNewButton = new JButton("Songs");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//load the jtable with the songs table rs
				tableMainDisplay = initTable(tableMainDisplay, MusicDatabase.getAllSongsTableModel(), new String[]{"Sid", "Title", "Album", "Artist"});
				scrollPane.setViewportView(tableMainDisplay);
			}
		});
		panelMainTable.add(btnNewButton, "cell 0 1,alignx center");
		
		JButton btnNewButton_1 = new JButton("Playlists");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reinit the table with the playlists data
				tableMainDisplay = initTable(tableMainDisplay, MusicDatabase.getAllPlaylistsTableModel(), new String[]{"PID", "Playlist", "Path", "Owner ID"});
				scrollPane.setViewportView(tableMainDisplay);
			}
		});
		panelMainTable.add(btnNewButton_1, "cell 0 2,alignx center");		
		
		//initialize the main display table and place in scroll pane
		tableMainDisplay = this.initTable(tableMainDisplay, MusicDatabase.getAllSongsTableModel(), new String[]{"Sid", "Title", "Album", "Artist"});
		System.out.println(tableMainDisplay);
		scrollPane.setViewportView(tableMainDisplay);
		
		JButton btnRefreshPlaylists = new JButton("Refresh Playlists");
		btnRefreshPlaylists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reinit the table with the playlists data
				tableMainDisplay = initTable(tableMainDisplay, MusicDatabase.getAllPlaylistsTableModel(), new String[]{"PID", "Playlist", "Path", "Owner ID"});
				scrollPane.setViewportView(tableMainDisplay);
			}
		});
		panelMainTable.add(btnRefreshPlaylists, "cell 0 3");
		
//		JButton btnViewPlaylist = new JButton("View Playlist");
//		btnViewPlaylist.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (tableMainDisplay.getColumnName(1).equals("sid") || tableMainDisplay.getSelectedRow() == -1) {
//					JOptionPane.showMessageDialog(tableMainDisplay, "Must have a playlist selected!");				
//				} 
//				else {
//
//					int row = tableMainDisplay.getSelectedRow();
//					Integer pid = (Integer) tableMainDisplay.getValueAt(row, 0);
//					MusicDatabase.getAllSongsForPlaylistTableModel();
//					
//				}
//			}
//		});
//		panelMainTable.add(btnViewPlaylist, "cell 0 4,alignx center");

		JPanel panelMainButtons = new JPanel();
		contentPane.add(panelMainButtons, "cell 3 15 17 2,grow");
		panelMainButtons.setLayout(new MigLayout("", "[92.00,grow][grow][grow][grow][grow][]", "[]"));
		
		//Login function
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: place Mark's login function
				LoginDriver login = new LoginDriver();
				login.main(null); // IF STATIC THEN SAME INSTANCE REGARDLESS OF NEW LOGINDRIVER OBJECT??
				System.out.println(Integer.toString(login.getCurrentUserId()));
				JButton thisButton = (JButton) e.getSource(); //get a reference to the running window
				JFrame mainWindowReference = (JFrame) thisButton.getTopLevelAncestor();//close the window and it's resources. login.main running?
				mainWindowReference.dispose();
				
								
				}
		});
		panelMainButtons.add(btnLogin, "cell 0 0,alignx center,aligny center");
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertWNDW.main(null);
			}
		});
		panelMainButtons.add(btnInsert, "cell 1 0,alignx center");
		
		JButton btnSearch = new JButton("Search & Delete");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchWindow.main(null);
			}
		});
		panelMainButtons.add(btnSearch, "cell 2 0,alignx center");
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateWindow updateWindow = new UpdateWindow();
				updateWindow.setVisible(true);
			}
		});
		panelMainButtons.add(btnUpdate, "cell 3 0,alignx center");
		
		JButton btnCreateList = new JButton("Create List");
		btnCreateList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PlaylistEditor playlistEditor = new PlaylistEditor();
				playlistEditor.setVisible(true);
			}
		});
		panelMainButtons.add(btnCreateList, "cell 4 0,alignx center");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(rigidArea_1, "cell 0 17");
	}

public MainWindow(int currentUser) {
		this.loggedInUser = currentUser;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][][][][80.00,grow][][][][][][grow][][][][30.00][][][grow][-3.00]", "[][][][grow][][][][][][][][][][][16.00][grow][][]"));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(rigidArea, "cell 0 0");
		
		JPanel panelMainTable = new JPanel();
		contentPane.add(panelMainTable, "cell 1 1 18 13,grow");
		panelMainTable.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][grow][grow][44.00,grow][72.00,grow]", "[][][][][][][][][][][]"));
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.control);
		scrollPane.setEnabled(false);
		scrollPane.setFocusable(false);
		scrollPane.setBounds(10, 78, 1264, 672);
		panelMainTable.add(scrollPane, "cell 1 0 22 11,grow");
		
		JButton btnNewButton = new JButton("Songs");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//load the jtable with the songs table rs
				tableMainDisplay = initTable(tableMainDisplay, MusicDatabase.getAllSongsTableModel(), new String[]{"Sid", "Title", "Album", "Artist"});
				scrollPane.setViewportView(tableMainDisplay);
			}
		});
		panelMainTable.add(btnNewButton, "cell 0 1,alignx center");
		
		JButton btnNewButton_1 = new JButton("Playlists");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reinit the table with the playlists data
				tableMainDisplay = initTable(tableMainDisplay, MusicDatabase.getAllPlaylistsForUserTableModel(loggedInUser), new String[]{"PID", "Playlist", "Path", "Owner ID"});
				scrollPane.setViewportView(tableMainDisplay);
			}
		});
		panelMainTable.add(btnNewButton_1, "cell 0 2,alignx center");
		

		
		//initialize the main display table and place in scroll pane
		tableMainDisplay = this.initTable(tableMainDisplay, MusicDatabase.getAllSongsTableModel(), new String[]{"Sid", "Title", "Album", "Artist"});
		System.out.println(tableMainDisplay);
		scrollPane.setViewportView(tableMainDisplay);
		
		JButton btnRefreshPlaylists = new JButton("Refresh Playlists");
		btnRefreshPlaylists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reinit the table with the playlists data
				tableMainDisplay = initTable(tableMainDisplay, MusicDatabase.getAllPlaylistsForUserTableModel(loggedInUser), new String[]{"PID", "Playlist", "Path", "Owner ID"});
				scrollPane.setViewportView(tableMainDisplay);
			}
		});
		panelMainTable.add(btnRefreshPlaylists, "cell 0 5");

		JPanel panelMainButtons = new JPanel();
		contentPane.add(panelMainButtons, "cell 3 15 17 2,grow");
		panelMainButtons.setLayout(new MigLayout("", "[92.00,grow][grow][grow][grow][grow][]", "[]"));
		
		//Login function
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: place Mark's login function
				LoginDriver login = new LoginDriver();
				login.main(null);
				System.out.println(Integer.toString(login.getCurrentUserId()));
				JButton thisButton = (JButton) e.getSource(); //get a reference to the running window
				JFrame mainWindowReference = (JFrame) thisButton.getTopLevelAncestor();//close the window and it's resources. login.main running?
				mainWindowReference.dispose();
				
								
				}
		});
		panelMainButtons.add(btnLogin, "cell 0 0,alignx center,aligny center");
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertWNDW.main(null);
			}
		});
		panelMainButtons.add(btnInsert, "cell 1 0,alignx center");
		
		JButton btnSearch = new JButton("Search & Delete");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchWindow.main(null);
			}
		});
		panelMainButtons.add(btnSearch, "cell 2 0,alignx center");
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loggedInUser == ROOTUSERID){//Root user sees all playlists
					UpdateWindow updateWindow = new UpdateWindow();
					updateWindow.setVisible(true);
				}
				else{
					UpdateWindow updateWindow = new UpdateWindow(loggedInUser);
					updateWindow.setVisible(true);
				}

			}
		});
		panelMainButtons.add(btnUpdate, "cell 3 0,alignx center");
		
		JButton btnCreateList = new JButton("Create List");
		btnCreateList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PlaylistEditor playlistEditor = new PlaylistEditor(loggedInUser);
				playlistEditor.setVisible(true);
			}
		});
		panelMainButtons.add(btnCreateList, "cell 4 0,alignx center");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(rigidArea_1, "cell 0 17");
	}
}
