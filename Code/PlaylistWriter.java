package FinalDBProj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.TableModel;

public class PlaylistWriter {
	String path;
	String playlistName;
	Integer ownerID;
	ArrayList<Integer> songSids;
	ArrayList<String> songPaths; // just in case mp3 player functionality

	public PlaylistWriter() {

	}

	public PlaylistWriter(String path, String playlistName, Integer ownerID, ArrayList<Integer> songSids) {
		this.path = path;
		this.playlistName = playlistName;
		this.ownerID = ownerID;
		this.songSids = songSids;
		this.songPaths = null;
	}

	public PlaylistWriter(String path, String playlistName, Integer ownerID, Integer[] songSids) {
		this.path = path;
		this.playlistName = playlistName;
		this.ownerID = ownerID;
		this.songSids = new ArrayList<Integer>(Arrays.asList(songSids));
		this.songPaths = null;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public Integer getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(Integer ownerID) {
		this.ownerID = ownerID;
	}

	public ArrayList<Integer> getSongSids() {
		return songSids;
	}

	public void setSongSids(ArrayList<Integer> songSids) {
		this.songSids = songSids;
	}

	public ArrayList<String> getSongPaths() {
		return songPaths;
	}

	public void setSongPaths(ArrayList<String> songPaths) {
		this.songPaths = songPaths;
	}

	public void addSongSid(Integer songSid) {
		this.songSids.add(songSid);

	}
	
	public void removeSongSid(){
		this.songSids.remove(songSids.size()-1); //removes the most recent song in the model
	}

	public void writePlaylist() throws IOException, UnsupportedEncodingException {
		// File newfile= new File(this.getPath());
		PrintWriter writer = new PrintWriter(this.getPath(), "UTF-8");
		for (Integer sid : this.getSongSids()) {
			writer.println(Integer.toString(sid));
		}
		writer.close();
	}
	
	public void printPlaylistModelSids(){
		System.out.println(songSids);
	}
	

	public static void main(String[] args){
		String playlistFolder = "C:/Users/Isaac/Dropbox/DBProject/playlists/";
		String myPath = playlistFolder + "testingPlaylist.pl" ;
		String myPlName = "testPlaylist";
		Integer oid = 0;
		ArrayList<Integer> mySongSids = new ArrayList<Integer>();
		
		mySongSids.add(140);
		mySongSids.add(141);
		mySongSids.add(142);
		mySongSids.add(143);
		mySongSids.add(144);
		mySongSids.add(145);
		mySongSids.add(146);
		mySongSids.add(147); 
		
		PlaylistWriter plWriter = new PlaylistWriter(myPath, myPlName, oid, mySongSids);
		plWriter.printPlaylistModelSids();
		try {
			plWriter.writePlaylist();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
