package FinalDBProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

//TODO if a song in the playlist is not found in the database
//TODO duplicate song handling, prompt asking user if song is
//TODO same y/n. t
//TODO if yes then remove duplicates and fix all foreign key
//TODO relationships, if any
//TODO else find way to associate particular song with unique
//TODO sid to it's reference in a playlist file

public class MusicDatabase {
	// JDBC driver name and database URL
	static final String DB_URL = "jdbc:sqlite:C:/Users/Trey/Dropbox/DBProject/musicLibraryPathsWithTrey.db";
	
	public static void UpdateSongTable(int sid, String title, String artist, String album) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			// STEP 4: Execute the update query
			System.out.println("Creating statement...");
			String sql = "UPDATE Songs SET title = ? , " 
					+  "album = ?, "
					+  "artist = ? "
					+ "WHERE sid = ?";
			pstmt = conn.prepareStatement(sql);
			
			//set the ? params in the prepared statement pstmt
			pstmt.setString(1, title);
			pstmt.setString(2, album);
			pstmt.setString(3, artist);
			pstmt.setInt(4, sid);
			
			pstmt.executeUpdate();

			// STEP 6: Clean-up environment
			pstmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
	
	public static void insertIntoPlaylistTable(String playlistName, String path, int ownerId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Playlists (playlist_name, path, owner_id) VALUES (?, ?, ?)" ;

		try {
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("Creating statement...");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playlistName);
			pstmt.setString(2, path);
			pstmt.setInt(3, ownerId);
			System.out.println("Executing statement");
			pstmt.executeUpdate(); //despite the name this is the method to execute INSERT stmts into the db
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { //try catch block to handle pstmt.close() sqlexception
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {//block to handle conn.close() sqlexception
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	public static DefaultTableModel getAllSongsTableModel(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet allSongsRS = null;
		DefaultTableModel allSongsTableModel = null;
							
		try{
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);
			
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM Songs";
			allSongsRS = stmt.executeQuery(sql);
			
			allSongsTableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(allSongsRS);
			
			allSongsRS.close();
			stmt.close();
			conn.close();	
			System.out.println("Closing the connection to the database...");	
		}
		catch(SQLException se){
			se.printStackTrace();			
		}
//		if(allSongsTableModel == null) System.out.println("Bad Songs!");
		return allSongsTableModel;
	}
	
//	public static TableModel getAllSongsForPlaylistTableModel(int pid){
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet allSongsRS = null;
//		TableModel allSongsTableModel = null;
//				
//		try{
//			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(DB_URL);
//			
//			System.out.println("Creating statement...");
//			stmt = conn.createStatement();
//			String sql;
//			sql = "SELECT * FROM Songs WHERE ";
//			allSongsRS = stmt.executeQuery(sql);
//			
//			allSongsTableModel = DbUtils.resultSetToTableModel(allSongsRS);
//			
//			allSongsRS.close();
//			stmt.close();
//			conn.close();	
//			System.out.println("Closing the connection to the database...");	
//		}
//		catch(SQLException se){
//			se.printStackTrace();			
//		}
////		if(allSongsTableModel == null) System.out.println("Bad Songs!");
//		allSongsTAbleModel.addRow
//		return allSongsTableModel;
//	}
	public static TableModel getAllPlaylistsTableModel(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet allPlaylistsRS = null;
		TableModel allPlaylistsTableModel = null;
				
		try{
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);
			
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM Playlists";
			allPlaylistsRS = stmt.executeQuery(sql);
			
			allPlaylistsTableModel = DbUtils.resultSetToTableModel(allPlaylistsRS);
			
			allPlaylistsRS.close();
			stmt.close();
			conn.close();	
		}
		catch(SQLException se){
			se.printStackTrace();			
		}

		return allPlaylistsTableModel;
	}
	
	public static TableModel getAllPlaylistsForUserTableModel(int userId){
		Connection conn = null;
		Statement stmt = null;
		ResultSet allPlaylistsRS = null;
		TableModel allPlaylistsTableModel = null;
				
		try{
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);
			
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM Playlists WHERE owner_id="+ String.valueOf(userId);
			allPlaylistsRS = stmt.executeQuery(sql);
			
			allPlaylistsTableModel = DbUtils.resultSetToTableModel(allPlaylistsRS);
			
			allPlaylistsRS.close();
			stmt.close();
			conn.close();	
		}
		catch(SQLException se){
			se.printStackTrace();			
		}

		return allPlaylistsTableModel;
	}

	// get all the playlistNames in the playlist table
	@SuppressWarnings("unchecked")
	public static Playlist[] getAllPlaylists() {
		Playlist[] thePlaylists = new Playlist[256];// will calculate table size
													// later

		// the user param must be a valid user in the users table
		Connection conn = null;
		Statement stmt = null;
		try {

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			// Execute the update query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM Playlists";
			ResultSet rs = stmt.executeQuery(sql);

			int i = 0;
			while (rs.next()) {
				System.out.println(i);
				int pid;
				String playlist_name;
				String path;

				pid = rs.getInt("pid");
				playlist_name = rs.getString("playlist_name");
				path = rs.getString("path");

				thePlaylists[i] = new Playlist(pid, playlist_name, path);
				i++;

			}
			

			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return thePlaylists;
	}
	public static Playlist[] getAllPlaylistsForUser(int userId) {
		Playlist[] thePlaylists = new Playlist[256];// will calculate table size
													// later

		// the user param must be a valid user in the users table
		Connection conn = null;
		Statement stmt = null;
		try {

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			// Execute the update query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM Playlists WHERE owner_id="+String.valueOf(userId) +";";
			ResultSet rs = stmt.executeQuery(sql);

			int i = 0;
			while (rs.next()) {
				System.out.println(i);
				int pid;
				String playlist_name;
				String path;

				pid = rs.getInt("pid");
				playlist_name = rs.getString("playlist_name");
				path = rs.getString("path");

				thePlaylists[i] = new Playlist(pid, playlist_name, path);
				i++;

			}
			

			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return thePlaylists;
	}

	public static String[] getAllPlaylistNames() {

		String[] thePlaylistNames = new String[256];
		Playlist[] thePlaylists = new Playlist[256];// will calculate table size
													// later

		// the user param must be a valid user in the users table
		Connection conn = null;
		Statement stmt = null;
		try {

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			// Execute the update query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM Playlists";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int i = 0;
				int pid;
				String playlist_name;
				String path;

				pid = rs.getInt("pid");
				playlist_name = rs.getString("playlist_name");
				path = rs.getString("path");

				thePlaylists[i] = new Playlist(pid, playlist_name, path);
				i++;

			}
			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		for (int i = 0; thePlaylists[i] != null; i++) {
			thePlaylistNames[i] = thePlaylists[i].playlist_name;
		}

		return thePlaylistNames;
	}

	public static Song findSongInDatabase(String songTitle) {
		Song returnSong = new Song();

		// the user param must be a valid user in the users table
		Connection conn = null;
		Statement stmt = null;
		try {

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			// Execute the update query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT title FROM Songs WHERE title=" + songTitle + ";";
			ResultSet rs = stmt.executeQuery(sql);

			// get the song data from the query
			Integer sid;
			String title;
			String album;
			String artist;

			if (rs.next()) {
				sid = rs.getInt("sid");
				title = rs.getString("title");
				album = rs.getString("album");
				artist = rs.getString("artist");
				// pass the retrieved values of the first matched song to our
				// return song object
				returnSong.setAlbum(album);
				returnSong.setArtist(artist);
				returnSong.setSid(sid);
				returnSong.setTitle(title);

			}

			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return returnSong;
	}

	public static Song[] getSongsFromPlaylist(Playlist playlist) {
		Integer[] sidsInPlaylist = playlist.getSongSidsList();
		// Song[] songsInPlaylist = new Song[256];

		ArrayList<Song> songsInPlaylist = new ArrayList<Song>();

		Connection conn = null;// object used to connect to the database
		Statement stmt = null;// object used to execute sql statements to the
								// database, requires init

		try {
			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			// Init the statement object and run the sql query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			ResultSet rs = null;
			Song currSong = new Song();
			int sid;
			String title;
			String album;
			String artist;
			for (Integer sidOfSong : sidsInPlaylist) {
				// query the song table for song with matching name, pass that
				// row data to a song object, pass that song to an array to be
				// returned
				sql = "SELECT * FROM Songs WHERE sid=" + sidOfSong.intValue() + ";";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {// if duplicate song we just pass the first one
								// to our resulting array
					sid = rs.getInt("sid");
					title = rs.getString("title");
					album = rs.getString("album");
					artist = rs.getString("artist");
					//
					// currSong.setAlbum(album);
					// currSong.setArtist(artist);
					// currSong.setSid(sid);
					// currSong.setTitle(title);
					// System.out.println(songTitle + " equals " +
					// currSong.toString()); //GOOD
					// songsInPlaylist.add(currSong); //THIS WAS REALLY BAD, i
					// was appending the SAME pointer to the list then updating
					// the pointer. BAD
					songsInPlaylist.add(new Song(sid, title, album, artist));
					System.out.println(songsInPlaylist.get(songsInPlaylist.size() - 1)); // GOOD
				}
			}

			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
			// for some reason my array list is only taking the song values for
			// "i love you" the last song in the query
		Song[] songs = new Song[songsInPlaylist.size()];

		songs = songsInPlaylist.toArray(new Song[songsInPlaylist.size()]);
		return songs;
	}
	

}// end class
