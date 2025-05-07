package musicStore.model;

public class GenreModel {
	private int id;
    private String genreName;
    private String genreImg;
    
    
	public GenreModel(int id, String genreName, String genreImg) {
		super();
		this.id = id;
		this.genreName = genreName;
		this.genreImg = genreImg;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getGenreName() {
		return genreName;
	}


	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getGenreImg() {
		return genreImg;
	}


	public void setGenreImg(String genreImg) {
		this.genreImg = genreImg;
	}
    
	@Override
    public String toString() {
        return "Genre{" +
               "id=" + id +
               ", name='" + genreName + '\'' +
               ", imageUrl='" + genreImg + '\'' +
               '}';}

}
