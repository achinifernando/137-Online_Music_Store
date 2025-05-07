package musicStore.Service;

import java.util.List;

import musicStore.model.GenreModel;
import musicStore.model.PlaylistModel;
import musicStore.model.SongsModel;
import musicStore.model.playlistSongsModel;

public interface ServiceInerface {
	
	public List<SongsModel> Search(String name);
	//search songs s by name in the search bar and it is case-insensitive
	 
	public boolean insertData(String playlist_name, int user_id);
	//Creates a new playlist for a user.
	
	public List<PlaylistModel> getPlaylistsByUser(int user_id);
	// Fetches all playlists for a user, sorted by creation date (newest first).
	
	public  boolean deletePlaylist(int playlistId);
	//Deletes a playlist and its songs (due to foreign key constraints)
	
	public  List<GenreModel> selectAllGenres();
	//Fetches all genres from the genres table.
	
	public  List<SongsModel> getSongsByGenre(int genreId);
	//Filters songs by genre ID.
	
	public  void addSongToPlaylist(playlistSongsModel ps);
	//Links a song to a playlist.
	
	public  List<SongsModel> getSongsInPlaylist(int playlistId);
	//Lists all songs in a playlist.
	
	public  boolean deletePlaylistSongs(int songId,int playlistId );
	//Removes a song from a playlist.
	
	public boolean updatePlaylistName(int playlistId, String newName);
	//Renames a playlist.

}
