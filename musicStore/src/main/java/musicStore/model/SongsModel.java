package musicStore.model;

public class SongsModel {
	private int id;
	private String title;
	private int genre_id;
	private String file_path;
	private String Artist;
	
	public SongsModel() {}
	public SongsModel(int id, String title, int genre_id, String file_path, String artist) {
		
		this.id = id;
		this.title = title;
		this.genre_id = genre_id;
		this.file_path = file_path;
		Artist = artist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}


	public int getGenre_id() {
		return genre_id;
	}


	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}


	public String getFile_path() {
		return file_path;
	}


	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}


	public String getArtist() {
		return Artist;
	}

	public void setArtist(String artist) {
		Artist = artist;
	}
	
	
	
	

}
