package FinalDBProj;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class DBIO {
		private static Connection conn = null;
		
		//private static String url = "jdbc:sqlite:C:/Users/Trey/Documents/SQLiteStudio/music";
		//The commented out url is outdated. It was for my old testing databse. Keeping it here just in case
		//private static String url = "jdbc:sqlite:C:/Users/Trey/Dropbox/DBProject/musicLibrary.db";
		private static String url = "jdbc:sqlite:C:/Users/Trey/Dropbox/DBProject/musicLibraryPathsWithTrey.db";
		//Note: Though this connects to the dropbox database, it still only works for me. To use this for your dropbox directory,
		//		you'll have to change it accordingly.
		
		private static ResultSet rs;
		
		//Establishes the connection to the database
		private static void connect(){
			try {
					conn = DriverManager.getConnection(url);
			}//end try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end catch
		}//end of connect()
		
		//Closes the database
		public static void closeDB(){

			if (conn != null) {
				try {
					conn.close();
				}//end try
				catch (SQLException e) {
					System.out.println(e.getMessage());
				}//endCatch
			}//end if
		}//end of closeDB
		
		//Inserts a song with values s, a, al into the Song table
		public static void Insert(String s, String a, String al) {
			connect();
			System.out.println("making it??");
			//String sqlStat = "INSERT INTO Songs(name, artist, album) values(?, ?, ?)";
			//The commented out SQL string is outdated, it was used for my testing database.
			String sqlStat = "INSERT INTO Songs(title, artist, album) values(?, ?, ?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sqlStat);
				ps.setString(1, s);
				ps.setString(2, a);
				ps.setString(3, al);
				ps.executeUpdate();
			}//end try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end catch
			finally {
				closeDB();
			}//end of finally
		}//end of Insert 1
		
		//Inserts a song with values s, a into the song table
		public static void Insert(String s, String a) {
			connect();
			//String sqlStat = "INSERT INTO Songs(name, artist) values(?, ?)";
			//The commented out sql statement above was for my testing database
			String sqlStat = "INSERT INTO Songs(title, artist) values(?, ?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sqlStat);
				ps.setString(1, s);
				ps.setString(2, a);
				ps.executeUpdate();
			}//end try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end catch
			finally {
				closeDB();
			}//end of finally
		}//end of insert 2
		
		//Checks if an entry already exists with values song = s, artist = a, album = al, returns a boolean (true if there is one)
		public static boolean duplicate(String s, String a, String al) {
			//String sqlStat = "Select * from Songs where name = \"" + s + "\" and artist = \"" + a + "\" and album = \"" + al + "\"";
			//The commented out sql statement is for my old database for testing.
			String sqlStat = "Select * from Songs where title = \"" + s + "\" and artist = \"" + a + "\" and album = \"" + al + "\"";
			connect();
			String song = "", artist = "", album = "";
			try{
				Statement stmt = conn.createStatement();
				rs = stmt.executeQuery(sqlStat);
				song = rs.getString(2);
				artist = rs.getString(4);	//in my testing database, it was rs.getString(3)
				album = rs.getString(3);	//in my testing database, it was rs.getString(4)
				rs.close();
			}//end try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end catch
			finally {
				closeDB();
			}//end of finally
			if (song.equals(s) && artist.equals(a) && album.equals(al)) {
				return true;
			}//end if
			return false;
		}//end of duplicate
		
		//Checks if an entry already exists with values song = s, artist = a returns a boolean (true if there is one)
		public static boolean duplicate(String s, String a) {
			//String sqlStat = "Select * from Songs where name = \"" + s + "\" and artist = \"" + a + "\"";
			//The commented out sql statement above was for my testing database
			String sqlStat = "Select * from Songs where title = \"" + s + "\" and artist = \"" + a + "\"";
			connect();
			String song = "", artist = "";
			try{
				Statement stmt = conn.createStatement();
				rs = stmt.executeQuery(sqlStat);
				song = rs.getString(2);
				artist = rs.getString(4);	//in my testing database, it was rs.getString(3)
				rs.close();
			}//end try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end catch
			finally {
				closeDB();
			}//end of finally
			if (song.equals(s) && artist.equals(a)) {
				return true;
			}//end if
			return false;
		}//end of duplicate
		
		//Deletes a song with id = id from the song table
		public static void delete(int id){
			String sqlDelete = "Delete from Songs where sid = " + id;
			connect();
			try {
				PreparedStatement del = conn.prepareStatement(sqlDelete);
				del.executeUpdate();
			}//end of try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end of catch
			finally {
				closeDB();
			}//end of finally
		}//end of delete 1
		
		//Deletes an entry with song = s, artist = a, album = al from the song table
		public static void delete(String s, String a, String al){
			//String sqlDelete = "Delete from Songs where name = \"" + s + "\" and artist = \"" + a + "\" and album = \"" + al + "\"";
			//Above sql statement was for my testing database
			String sqlDelete = "Delete from Songs where title = \"" + s + "\" and artist = \"" + a + "\" and album = \"" + al + "\"";
			connect();
			try {
				PreparedStatement del = conn.prepareStatement(sqlDelete);
				del.executeUpdate();
			}//end of try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end of catch
			finally {
				closeDB();
			}//end of finally
		}//end of delete 2

		//Deletes an entry with song = s, artist = a from the song table
		public static void delete(String s, String a){
			//String sqlDelete = "Delete from Songs where name = \"" + s + "\" and artist = \"" + a + "\" and album is NULL";
			//Again, the above sql statement was for my testing DB
			String sqlDelete = "Delete from Songs where title = \"" + s + "\" and artist = \"" + a + "\" and album is NULL";
			connect();
			try {
				PreparedStatement del = conn.prepareStatement(sqlDelete);
				del.executeUpdate();
			}//end of try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end of catch
			finally {
				closeDB();
			}//end of finally
		}//end of delete 3
	
		//Deletes all duplicates, leaving only one instance of each tuple
		public static void removeDuplicates() {
			//String sqlDelete = "Delete from Songs where sid not in (select min(sid) from Songs group by name, artist, album)";
			//Again the above sql statement was for my testing database
			String sqlDelete = "Delete from Songs where sid not in (select min(sid) from Songs group by title, artist, album)";
			connect();
			try {
				PreparedStatement del = conn.prepareStatement(sqlDelete);
				del.executeUpdate();
			}//end of try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end of catch
			finally {
				closeDB();
			}//end of finally
		}//end remove duplicates
		
		//Searches for an entered string, sorts result based on order
		//WARNING: This subroutine does not close the database because the database has to be open to access the result set.
		//The calling function must close the database using DBIO.closeDB() when they are done with the resultset.
		public static ResultSet search(String str, int order) {
			String sqlStat;
			switch (order) {
			case 0:
				sqlStat = "Select title, artist, album from Songs where title like '%" + str + "%' or artist like '%" + str + "%' or album like '%" + str + "%'";
				break;
			case 1:
				sqlStat = "Select title, artist, album from Songs where title like '%" + str + "%' or artist like '%" + str + "%' or album like '%" + str + "%' order by title";
				break;
			case 2:
				sqlStat = "Select title, artist, album from Songs where title like '%" + str + "%' or artist like '%" + str + "%' or album like '%" + str + "%' order by artist";
				break;
			case 3:
				sqlStat = "Select title, artist, album from Songs where title like '%" + str + "%' or artist like '%" + str + "%' or album like '%" + str + "%' order by album";
				break;
			default:
				sqlStat = "Select title, artist, album from Songs where title like '%" + str + "%' or artist like '%" + str + "%' or album like '%" + str + "%'";
			}
			connect();
			try{
				Statement stmt = conn.createStatement();
				rs = stmt.executeQuery(sqlStat);
			}//end try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end catch
			return rs;
		}//end search

		//Returns all songs in the database, sorted based on order
		//This method also does not close the database.
		public static ResultSet getAllSongs(int order) {
			String sqlStat;
			switch (order) {
			case 0:
				sqlStat = "Select title, artist, album from Songs";
				break;
			case 1:
				sqlStat = "Select title, artist, album from Songs order by title";
				break;
			case 2:
				sqlStat = "Select title, artist, album from Songs order by artist";
				break;
			case 3:
				sqlStat = "Select title, artist, album from Songs order by album";
				break;
			default:
				sqlStat = "Select title, artist, album from Songs";
			}

			connect();
			try{
				Statement stmt = conn.createStatement();
				rs = stmt.executeQuery(sqlStat);
			}//end try
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}//end catch
			return rs;
		}//end get all songs
}//end of DBIO class
