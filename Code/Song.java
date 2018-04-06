package FinalDBProj;

public class Song {
	
	//fields
	int sid;
	String title;
	String album;
	String artist;
	
	public Song(){
	}
	//constructor
	
	public Song(String title, String album, String artist){
		this.title = title;
		this.album = album;
		this.artist = artist;
	}
	
	
	public Song(int sid, String title, String album, String artist){
		this.sid = sid;
		this.title = title;
		this.album = album;
		this.artist = artist;
	}
	
	//methods
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String toString(){
		return this.title;
	}
	
}
