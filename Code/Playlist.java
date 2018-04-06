package FinalDBProj;
/**
 * @author Isaac
 *
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

public class Playlist {
	//Fields
	int pid;
	String playlist_name;
	String path;
	int    songCount;
	ArrayList<String> songsData = new ArrayList<String>();
	ArrayList<Integer> songSids = new ArrayList<Integer>();
	ArrayList<String> songPaths = new ArrayList<String>(); //just in case player is added
		
	//Constructor
	public Playlist(String path){
		this.path = path;	
		//try to read the file line by line
		//extract the song title from each line
		//add the extracted song tile to our ArrayList<String> of song titles
		//duplicate song titles allowed
		try(BufferedReader lineByLineReader = new BufferedReader( 
							new FileReader(this.path)))
		{
			String line;
			while( (line = lineByLineReader.readLine()) != null){
				//gotta parse the line to match the songtitle
				//pattern matches anything before the last period in the string
//				Pattern extractTitle = Pattern.compile("(.*)\\."); 
//				Matcher extracted = extractTitle.matcher(line);
//				String songTitle = FilenameUtils.removeExtension(line);
				String songData = null; 
				songsData.add(songData);			
			}
			lineByLineReader.close();
		} catch (IOException e){
			System.err.println("IOException : " + e);
			System.out.println("now exiting the program!");
			System.exit(1);// nonzero argument implies exit because of error
		} 	
		songCount = songsData.size();
	}
	
	public Playlist(int pid, String playlist_name, String path){
		this.pid = pid;
		this.playlist_name = playlist_name;
		this.path = path;	
		//try to read the file line by line
		//extract the song title from each line
		//add the extracted song tile to our ArrayList<String> of song titles
		//duplicate song titles allowed
		try(BufferedReader lineByLineReader = new BufferedReader( 
				new InputStreamReader(
	                      new FileInputStream(this.path), "UTF8")))

		{
			String line;
			while( (line = lineByLineReader.readLine()) != null){
				//gotta parse the line to match the songtitle
				//pattern matches anything before the last period in the string
//				Pattern extractTitle = Pattern.compile("(.*)\\."); 
//				Matcher extracted = extractTitle.matcher(line);
				String songTitle = FilenameUtils.removeExtension(line);
				songsData.add(songTitle);			
			}
			
			lineByLineReader.close();
		} catch (IOException e){
			System.err.println("IOException : " + e);
			System.out.println("now exiting the program!");
			System.exit(1);// nonzero argument implies exit because of error
		} 	
		songCount = songsData.size();
		
		//populate the sids container
		for(String songDat: songsData){
			songSids.add(Integer.parseInt(songDat));
		}
		
	}
	//Methods
	public int getSongCount() {
		return this.songCount;
	}
	
	public String getFileName() {
		return this.path;
	}
	
	public Integer[] getSongSidsList() {
		Integer[] songsArray = new Integer[songsData.size()];
		songsArray = songSids.toArray(songsArray);
		return songsArray;
	}
	
	public String toString(){
		return this.playlist_name;
	}
	
}
