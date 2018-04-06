package FinalDBProj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

public class UpdateWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtArtist;
	private JTextField txtAlbum;
	private final Action action = new SwingAction();
	private JComboBox playlistComboBox;
	static int loggedInUser = 0;
	static final int ROOTUSERID = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {				
					if(loggedInUser == ROOTUSERID){
						UpdateWindow frame = new UpdateWindow();
						frame.setVisible(true);
					}
					else{
						UpdateWindow frame = new UpdateWindow(loggedInUser);
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1084, 732);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 243, 79, 172, 176, 0, 282, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 85, 0, 82, 0, 101, 0, 0, 101, 57, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
				
		JLabel lblSongAttributes = new JLabel("Song Attributes");
		lblSongAttributes.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblSongAttributes = new GridBagConstraints();
		gbc_lblSongAttributes.gridwidth = 2;
		gbc_lblSongAttributes.insets = new Insets(0, 0, 5, 5);
		gbc_lblSongAttributes.gridx = 1;
		gbc_lblSongAttributes.gridy = 5;
		contentPane.add(lblSongAttributes, gbc_lblSongAttributes);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 7;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblArtist = new GridBagConstraints();
		gbc_lblArtist.anchor = GridBagConstraints.WEST;
		gbc_lblArtist.gridwidth = 2;
		gbc_lblArtist.insets = new Insets(0, 0, 5, 5);
		gbc_lblArtist.gridx = 2;
		gbc_lblArtist.gridy = 7;
		contentPane.add(lblArtist, gbc_lblArtist);
		
		JLabel lblAlbum = new JLabel("Album");
		lblAlbum.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblAlbum = new GridBagConstraints();
		gbc_lblAlbum.anchor = GridBagConstraints.WEST;
		gbc_lblAlbum.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlbum.gridx = 4;
		gbc_lblAlbum.gridy = 7;
		contentPane.add(lblAlbum, gbc_lblAlbum);
		
		JLabel lblSid = new JLabel("SID");
		lblSid.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblSid = new GridBagConstraints();
		gbc_lblSid.insets = new Insets(0, 0, 5, 5);
		gbc_lblSid.gridx = 6;
		gbc_lblSid.gridy = 7;
		contentPane.add(lblSid, gbc_lblSid);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitle.gridx = 1;
		gbc_txtTitle.gridy = 8;
		contentPane.add(txtTitle, gbc_txtTitle);
		txtTitle.setColumns(10);
		
		txtArtist = new JTextField();
		txtArtist.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_txtArtist = new GridBagConstraints();
		gbc_txtArtist.gridwidth = 2;
		gbc_txtArtist.insets = new Insets(0, 0, 5, 5);
		gbc_txtArtist.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtArtist.gridx = 2;
		gbc_txtArtist.gridy = 8;
		contentPane.add(txtArtist, gbc_txtArtist);
		txtArtist.setColumns(10);
		
		txtAlbum = new JTextField();
		txtAlbum.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_txtAlbum = new GridBagConstraints();
		gbc_txtAlbum.gridwidth = 2;
		gbc_txtAlbum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAlbum.insets = new Insets(0, 0, 5, 5);
		gbc_txtAlbum.gridx = 4;
		gbc_txtAlbum.gridy = 8;
		contentPane.add(txtAlbum, gbc_txtAlbum);
		txtAlbum.setColumns(10);
				
		final JLabel lblSidDisplay = new JLabel("");
		lblSidDisplay.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblSid_1 = new GridBagConstraints();
		gbc_lblSid_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSid_1.gridx = 6;
		gbc_lblSid_1.gridy = 8;
		contentPane.add(lblSidDisplay, gbc_lblSid_1);
		
		final JComboBox songComboBox = new JComboBox();
		songComboBox.setBackground(Color.WHITE);
		songComboBox.setFont(new Font("Tahoma", Font.PLAIN, 34));
		songComboBox.setMinimumSize(new Dimension(48, 99));
		songComboBox.setPreferredSize(new Dimension(48, 99));
		songComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Song selectedSong = (Song) songComboBox.getSelectedItem();				
				txtTitle.setText(selectedSong.getTitle());
				txtAlbum.setText(selectedSong.getAlbum());
				txtArtist.setText(selectedSong.getArtist());
				lblSidDisplay.setText(Integer.toString(selectedSong.getSid()));
			}
		});
		GridBagConstraints gbc_songComboBox = new GridBagConstraints();
		gbc_songComboBox.gridwidth = 3; 	
		gbc_songComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_songComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_songComboBox.gridx = 1;
		gbc_songComboBox.gridy = 3;
		contentPane.add(songComboBox, gbc_songComboBox);

		playlistComboBox = new JComboBox(MusicDatabase.getAllPlaylists());
		playlistComboBox.setBackground(Color.WHITE);
		playlistComboBox.setFont(new Font("Tahoma", Font.PLAIN, 34));
		playlistComboBox.setMinimumSize(new Dimension(170, 99));
		playlistComboBox.setPreferredSize(new Dimension(231, 99));
		//once a playlist has been chosen populate the next combobox with the songs in that playlist.
		playlistComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Playlist selectedPlaylist = (Playlist) playlistComboBox.getSelectedItem(); 
		    	DefaultComboBoxModel songsInPlaylist = 
		    			new DefaultComboBoxModel(MusicDatabase.getSongsFromPlaylist(selectedPlaylist));
		    	
		        //populate the next box with the songs from the list
		    	songComboBox.setModel(songsInPlaylist);
		    }
		});
		GridBagConstraints gbc_playlistComboBox = new GridBagConstraints();
		gbc_playlistComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_playlistComboBox.gridwidth = 3;
		gbc_playlistComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_playlistComboBox.gridx = 1;
		gbc_playlistComboBox.gridy = 1;
		contentPane.add(playlistComboBox, gbc_playlistComboBox);
		
		JButton btnClickMe = new JButton("Submit");
		btnClickMe.setPreferredSize(new Dimension(33, 37));
		btnClickMe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnClickMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//send the changes to the db, use the built in update method
				Song selectedSong = (Song) songComboBox.getSelectedItem();	
				int sid = selectedSong.getSid();
				String title = txtTitle.getText();
				String artist = txtArtist.getText();
				String album = txtAlbum.getText();
				MusicDatabase.UpdateSongTable(sid, title, artist, album);
								
			}
		});
		btnClickMe.setAction(action);
		GridBagConstraints gbc_btnClickMe = new GridBagConstraints();
		gbc_btnClickMe.insets = new Insets(0, 0, 5, 5);
		gbc_btnClickMe.gridx = 6;
		gbc_btnClickMe.gridy = 9;
		contentPane.add(btnClickMe, gbc_btnClickMe);
		
	}
	
	public UpdateWindow(int loggedInUser) {
		this.loggedInUser = loggedInUser;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1084, 732);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 243, 79, 172, 176, 0, 282, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 85, 0, 82, 0, 101, 0, 0, 101, 57, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
				
		JLabel lblSongAttributes = new JLabel("Song Attributes");
		lblSongAttributes.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblSongAttributes = new GridBagConstraints();
		gbc_lblSongAttributes.gridwidth = 2;
		gbc_lblSongAttributes.insets = new Insets(0, 0, 5, 5);
		gbc_lblSongAttributes.gridx = 1;
		gbc_lblSongAttributes.gridy = 5;
		contentPane.add(lblSongAttributes, gbc_lblSongAttributes);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 7;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblArtist = new GridBagConstraints();
		gbc_lblArtist.anchor = GridBagConstraints.WEST;
		gbc_lblArtist.gridwidth = 2;
		gbc_lblArtist.insets = new Insets(0, 0, 5, 5);
		gbc_lblArtist.gridx = 2;
		gbc_lblArtist.gridy = 7;
		contentPane.add(lblArtist, gbc_lblArtist);
		
		JLabel lblAlbum = new JLabel("Album");
		lblAlbum.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblAlbum = new GridBagConstraints();
		gbc_lblAlbum.anchor = GridBagConstraints.WEST;
		gbc_lblAlbum.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlbum.gridx = 4;
		gbc_lblAlbum.gridy = 7;
		contentPane.add(lblAlbum, gbc_lblAlbum);
		
		JLabel lblSid = new JLabel("SID");
		lblSid.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblSid = new GridBagConstraints();
		gbc_lblSid.insets = new Insets(0, 0, 5, 5);
		gbc_lblSid.gridx = 6;
		gbc_lblSid.gridy = 7;
		contentPane.add(lblSid, gbc_lblSid);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitle.gridx = 1;
		gbc_txtTitle.gridy = 8;
		contentPane.add(txtTitle, gbc_txtTitle);
		txtTitle.setColumns(10);
		
		txtArtist = new JTextField();
		txtArtist.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_txtArtist = new GridBagConstraints();
		gbc_txtArtist.gridwidth = 2;
		gbc_txtArtist.insets = new Insets(0, 0, 5, 5);
		gbc_txtArtist.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtArtist.gridx = 2;
		gbc_txtArtist.gridy = 8;
		contentPane.add(txtArtist, gbc_txtArtist);
		txtArtist.setColumns(10);
		
		txtAlbum = new JTextField();
		txtAlbum.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_txtAlbum = new GridBagConstraints();
		gbc_txtAlbum.gridwidth = 2;
		gbc_txtAlbum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAlbum.insets = new Insets(0, 0, 5, 5);
		gbc_txtAlbum.gridx = 4;
		gbc_txtAlbum.gridy = 8;
		contentPane.add(txtAlbum, gbc_txtAlbum);
		txtAlbum.setColumns(10);
				
		final JLabel lblSidDisplay = new JLabel("");
		lblSidDisplay.setFont(new Font("Tahoma", Font.PLAIN, 34));
		GridBagConstraints gbc_lblSid_1 = new GridBagConstraints();
		gbc_lblSid_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSid_1.gridx = 6;
		gbc_lblSid_1.gridy = 8;
		contentPane.add(lblSidDisplay, gbc_lblSid_1);
		
		final JComboBox songComboBox = new JComboBox();
		songComboBox.setBackground(Color.WHITE);
		songComboBox.setFont(new Font("Tahoma", Font.PLAIN, 34));
		songComboBox.setMinimumSize(new Dimension(48, 99));
		songComboBox.setPreferredSize(new Dimension(48, 99));
		songComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Song selectedSong = (Song) songComboBox.getSelectedItem();				
				txtTitle.setText(selectedSong.getTitle());
				txtAlbum.setText(selectedSong.getAlbum());
				txtArtist.setText(selectedSong.getArtist());
				lblSidDisplay.setText(Integer.toString(selectedSong.getSid()));
			}
		});
		GridBagConstraints gbc_songComboBox = new GridBagConstraints();
		gbc_songComboBox.gridwidth = 3; 	
		gbc_songComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_songComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_songComboBox.gridx = 1;
		gbc_songComboBox.gridy = 3;
		contentPane.add(songComboBox, gbc_songComboBox);

		playlistComboBox = new JComboBox(MusicDatabase.getAllPlaylistsForUser(this.loggedInUser));
		playlistComboBox.setBackground(Color.WHITE);
		playlistComboBox.setFont(new Font("Tahoma", Font.PLAIN, 34));
		playlistComboBox.setMinimumSize(new Dimension(170, 99));
		playlistComboBox.setPreferredSize(new Dimension(231, 99));
		//once a playlist has been chosen populate the next combobox with the songs in that playlist.
		playlistComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Playlist selectedPlaylist = (Playlist) playlistComboBox.getSelectedItem(); 
		    	DefaultComboBoxModel songsInPlaylist = 
		    			new DefaultComboBoxModel(MusicDatabase.getSongsFromPlaylist(selectedPlaylist));
		    	
		        //populate the next box with the songs from the list
		    	songComboBox.setModel(songsInPlaylist);
		    }
		});
		GridBagConstraints gbc_playlistComboBox = new GridBagConstraints();
		gbc_playlistComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_playlistComboBox.gridwidth = 3;
		gbc_playlistComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_playlistComboBox.gridx = 1;
		gbc_playlistComboBox.gridy = 1;
		contentPane.add(playlistComboBox, gbc_playlistComboBox);
		
		JButton btnClickMe = new JButton("Submit");
		btnClickMe.setPreferredSize(new Dimension(33, 37));
		btnClickMe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnClickMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//send the changes to the db, use the built in update method
				Song selectedSong = (Song) songComboBox.getSelectedItem();	
				int sid = selectedSong.getSid();
				String title = txtTitle.getText();
				String artist = txtArtist.getText();
				String album = txtAlbum.getText();
				MusicDatabase.UpdateSongTable(sid, title, artist, album);
								
			}
		});
		btnClickMe.setAction(action);
		GridBagConstraints gbc_btnClickMe = new GridBagConstraints();
		gbc_btnClickMe.insets = new Insets(0, 0, 5, 5);
		gbc_btnClickMe.gridx = 6;
		gbc_btnClickMe.gridy = 9;
		contentPane.add(btnClickMe, gbc_btnClickMe);
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Submit");
			putValue(SHORT_DESCRIPTION, "Submit the changes to the database");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
