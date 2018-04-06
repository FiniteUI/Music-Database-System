package FinalDBProj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JTextField;

public class PlaylistEditor extends JFrame {

	private JPanel contentPane;
	private JTable tableSongs;
	private JTable tableWorkingPlaylist;
	private JTextField txtPlaylistName;
	static int loggedInUser = 0;
	static final int ROOTUSERID = 0;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					if(loggedInUser == ROOTUSERID){
//						PlaylistEditor frame = new PlaylistEditor();
//						frame.setVisible(true);
//					}
//					else{
//						PlaylistEditor frame = new PlaylistEditor(loggedInUser);
//						frame.setVisible(true);
//					}
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	
	
	/**
	 * Create the frame.
	 */
	public PlaylistEditor() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[14.00,grow,right][208.00,grow,right][17.00,right][57.00,right][13.00,right][199.00,grow,right][18.00,grow,leading]", "[][267.00,grow][29.00][61.00,grow,baseline][25.00,bottom]"));
		
		JPanel panelSongsContainer = new JPanel();
		contentPane.add(panelSongsContainer, "cell 1 1,grow");
		panelSongsContainer.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPaneSongs = new JScrollPane();
		panelSongsContainer.add(scrollPaneSongs, "cell 0 0,grow");
		
		tableSongs = new JTable();
		tableSongs = initTable(tableSongs, MusicDatabase.getAllSongsTableModel(), new String[]{"sid", "Title", "Album", "Artist"});
		scrollPaneSongs.setViewportView(tableSongs);
		
		JPanel panelEditorButtonsContainer = new JPanel();
		contentPane.add(panelEditorButtonsContainer, "cell 3 1,grow");
		panelEditorButtonsContainer.setLayout(new MigLayout("", "[]", "[][][][][][][][]"));
	
		
		JPanel panelWorkingPlaylistContainer = new JPanel();
		contentPane.add(panelWorkingPlaylistContainer, "cell 5 1,grow");
		panelWorkingPlaylistContainer.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		final JScrollPane scrollPaneWorkingPlaylist = new JScrollPane();
		panelWorkingPlaylistContainer.add(scrollPaneWorkingPlaylist, "cell 0 0,grow");
		
		//init and add the working playlist
		tableWorkingPlaylist = new JTable();
		final DefaultTableModel workingPlaylistModel = new DefaultTableModel(new Object[]{"sid", "Title", "Album", "Artist"},0 );
		tableWorkingPlaylist.setModel(workingPlaylistModel);
		final int currPlaylistRow = 0;
		scrollPaneWorkingPlaylist.setViewportView(tableWorkingPlaylist);
		
		JPanel panelSubmitContainer = new JPanel();
		contentPane.add(panelSubmitContainer, "cell 1 3 5 1,grow");
		panelSubmitContainer.setLayout(new MigLayout("", "[][grow][][][]", "[]"));
		
		txtPlaylistName = new JTextField();
		txtPlaylistName.setText("Playlist Name Here");
		panelSubmitContainer.add(txtPlaylistName, "cell 1 0,growx");
		txtPlaylistName.setColumns(10);
		
		//TODO on click: 1. add selected song to playlist table 2. refresh playlist table to show new song added
		JButton btnAdd = new JButton("Add >");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row = tableSongs.getSelectedRow();
					workingPlaylistModel.addRow(new Object[]{tableSongs.getValueAt(row, 0),tableSongs.getValueAt(row, 1), tableSongs.getValueAt(row, 2),tableSongs.getValueAt(row, 3)});
					tableWorkingPlaylist.setModel(workingPlaylistModel);//update the table with new model
					scrollPaneWorkingPlaylist.setViewportView(tableWorkingPlaylist);//update the view of the scrollpane to reflect changes in table
				} catch (Exception e) {
					// catch in case user intends to add when no row is selected
					e.printStackTrace();
				}

			}
		});
		panelEditorButtonsContainer.add(btnAdd, "cell 0 2,alignx center");
		
		//TODO on click: 1. remove selected song from playlist table 2. refresh playlist table to show old added song 3. the right table is filled with a playlist model
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					workingPlaylistModel.removeRow(workingPlaylistModel.getRowCount()-1);
					tableWorkingPlaylist.setModel(workingPlaylistModel);//update the table with new model
					scrollPaneWorkingPlaylist.setViewportView(tableWorkingPlaylist);//update the view of the scrollpane to reflect changes in table
				} catch (Exception e1) {
					// catch in case clicks remove when working playlist table is empty believe its java array index out of bounds error
					e1.printStackTrace();
				}

			}
		});
		panelEditorButtonsContainer.add(btnRemove, "cell 0 5");
		
		//TODO button will use writePlaylist() to commit the playlist file
		final JButton btnSubmit = new JButton("Submit");
		btnSubmit.setMinimumSize(new Dimension(128, 31));
		btnSubmit.setMaximumSize(new Dimension(114, 31));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//write pl file
				//add pl to db
				int sizeOfData = tableWorkingPlaylist.getModel().getRowCount();
				String playlistFolder = "C:/Users/Trey/Dropbox/DBProject/playlists/";
				String playlistName = txtPlaylistName.getText();
				String fileName = playlistName + ".pl";
				String path = playlistFolder + fileName;

				Integer ownerId = 0; //this is default for now should be according to logged in user //TODO
				Integer[] songSids = new Integer[sizeOfData];
				for(int index = 0; index < sizeOfData;  index++){
					songSids[index] = (Integer) (tableWorkingPlaylist.getValueAt(index,0));
				}

				PlaylistWriter pl = new PlaylistWriter(path, playlistName, ownerId, songSids);
				
				try {//write the playlist file, handle stupid exceptions
					pl.writePlaylist();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//place the playlist into the database 
				MusicDatabase.insertIntoPlaylistTable(playlistName, path, ownerId);
				
				System.out.println(btnSubmit.getTopLevelAncestor().toString());
			}
		});
		panelSubmitContainer.add(btnSubmit, "cell 3 0");
		
	}
	
	public PlaylistEditor(final int loggedInUser) {
		this.loggedInUser = loggedInUser;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[14.00,grow,right][208.00,grow,right][17.00,right][57.00,right][13.00,right][199.00,grow,right][18.00,grow,leading]", "[][267.00,grow][29.00][61.00,grow,baseline][25.00,bottom]"));
		
		JPanel panelSongsContainer = new JPanel();
		contentPane.add(panelSongsContainer, "cell 1 1,grow");
		panelSongsContainer.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPaneSongs = new JScrollPane();
		panelSongsContainer.add(scrollPaneSongs, "cell 0 0,grow");
		
		tableSongs = new JTable();
		tableSongs = initTable(tableSongs, MusicDatabase.getAllSongsTableModel(), new String[]{"sid", "Title", "Album", "Artist"});
		scrollPaneSongs.setViewportView(tableSongs);
		
		JPanel panelEditorButtonsContainer = new JPanel();
		contentPane.add(panelEditorButtonsContainer, "cell 3 1,grow");
		panelEditorButtonsContainer.setLayout(new MigLayout("", "[]", "[][][][][][][][]"));
	
		
		JPanel panelWorkingPlaylistContainer = new JPanel();
		contentPane.add(panelWorkingPlaylistContainer, "cell 5 1,grow");
		panelWorkingPlaylistContainer.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		final JScrollPane scrollPaneWorkingPlaylist = new JScrollPane();
		panelWorkingPlaylistContainer.add(scrollPaneWorkingPlaylist, "cell 0 0,grow");
		
		//init and add the working playlist
		tableWorkingPlaylist = new JTable();
		final DefaultTableModel workingPlaylistModel = new DefaultTableModel(new Object[]{"sid", "Title", "Album", "Artist"},0 );
		tableWorkingPlaylist.setModel(workingPlaylistModel);
		final int currPlaylistRow = 0;
		scrollPaneWorkingPlaylist.setViewportView(tableWorkingPlaylist);
		
		JPanel panelSubmitContainer = new JPanel();
		contentPane.add(panelSubmitContainer, "cell 1 3 5 1,grow");
		panelSubmitContainer.setLayout(new MigLayout("", "[][grow][][][]", "[]"));
		
		txtPlaylistName = new JTextField();
		txtPlaylistName.setText("Playlist Name Here");
		panelSubmitContainer.add(txtPlaylistName, "cell 1 0,growx");
		txtPlaylistName.setColumns(10);
		
		//TODO on click: 1. add selected song to playlist table 2. refresh playlist table to show new song added
		JButton btnAdd = new JButton("Add >");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row = tableSongs.getSelectedRow();
					workingPlaylistModel.addRow(new Object[]{tableSongs.getValueAt(row, 0),tableSongs.getValueAt(row, 1), tableSongs.getValueAt(row, 2),tableSongs.getValueAt(row, 3)});
					tableWorkingPlaylist.setModel(workingPlaylistModel);//update the table with new model
					scrollPaneWorkingPlaylist.setViewportView(tableWorkingPlaylist);//update the view of the scrollpane to reflect changes in table
				} catch (Exception e) {
					// catch in case user intends to add when no row is selected
					e.printStackTrace();
				}

			}
		});
		panelEditorButtonsContainer.add(btnAdd, "cell 0 2,alignx center");
		
		//TODO on click: 1. remove selected song from playlist table 2. refresh playlist table to show old added song 3. the right table is filled with a playlist model
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					workingPlaylistModel.removeRow(workingPlaylistModel.getRowCount()-1);
					tableWorkingPlaylist.setModel(workingPlaylistModel);//update the table with new model
					scrollPaneWorkingPlaylist.setViewportView(tableWorkingPlaylist);//update the view of the scrollpane to reflect changes in table
				} catch (Exception e1) {
					// catch in case clicks remove when working playlist table is empty believe its java array index out of bounds error
					e1.printStackTrace();
				}

			}
		});
		panelEditorButtonsContainer.add(btnRemove, "cell 0 5");
		
		//TODO button will use writePlaylist() to commit the playlist file
		final JButton btnSubmit = new JButton("Submit");
		btnSubmit.setMinimumSize(new Dimension(128, 31));
		btnSubmit.setMaximumSize(new Dimension(114, 31));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//write pl file
				//add pl to db
				int sizeOfData = tableWorkingPlaylist.getModel().getRowCount();
				String playlistFolder = "C:/Users/Trey/Dropbox/DBProject/playlists/";
				String playlistName = txtPlaylistName.getText();
				String fileName = playlistName + ".pl";
				String path = playlistFolder + fileName;

				Integer ownerId = loggedInUser; //this is default for now should be according to logged in user //TODO
				Integer[] songSids = new Integer[sizeOfData];
				for(int index = 0; index < sizeOfData;  index++){
					songSids[index] = (Integer) (tableWorkingPlaylist.getValueAt(index,0));
				}

				PlaylistWriter pl = new PlaylistWriter(path, playlistName, ownerId, songSids);
				
				try {//write the playlist file, handle stupid exceptions
					pl.writePlaylist();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//place the playlist into the database 
				MusicDatabase.insertIntoPlaylistTable(playlistName, path, ownerId);
				System.out.println(btnSubmit.getTopLevelAncestor().toString());
			}
		});
		panelSubmitContainer.add(btnSubmit, "cell 3 0");
		
		
	}
	
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


}
