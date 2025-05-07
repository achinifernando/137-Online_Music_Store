package musicStore.Service;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import musicStore.Util.DBconnection;
import musicStore.model.GenreModel;
import musicStore.model.PlaylistModel;
import musicStore.model.SongsModel;
import musicStore.model.playlistSongsModel;

public class Service implements ServiceInerface{
	
	private static final Logger logger = Logger.getLogger(Service.class.getName());

	
	/*isSuccess: Tracks if an operation succeeded.
      conn: Database connection.
      result: Holds query results.
      pstmt: Used for parameterized SQL queries.*/
	
	private static boolean isSuccess;
	private static Connection conn= null;
	private static ResultSet result= null;
	private static PreparedStatement  pstmt= null;

//search songs in the search bar
	
public List<SongsModel> Search(String name) {
		
	 String sql = "SELECT * FROM songs WHERE LOWER(Title) LIKE ?";
	    List<SongsModel> songs = new ArrayList<>();

	    try {
	        conn = DBconnection.getInstance().getConnection();
;
	        pstmt = conn.prepareStatement(sql);
	        
	     // If the user typed a single letter or anything, it still works
	        String searchPattern = "%" + name.toLowerCase() + "%";
	        pstmt.setString(1, searchPattern);

	        result = pstmt.executeQuery();

	        while (result.next()) {
	            SongsModel song = new SongsModel();
	            song.setId(result.getInt(1));
	            song.setTitle(result.getString(2));
	            song.setGenre_id(result.getInt(3));
	            song.setFile_path(result.getString(4));
	            song.setArtist(result.getString(5));
	            
	            songs.add(song);
	        }
	    } catch (Exception e) {
	    	logger.log(Level.SEVERE, "Error in searching song", e);
	    } finally {
	    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
	        try {
	            if (result != null) result.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	        	logger.log(Level.WARNING, "Error closing database resources", e);
	        }
	    }

	    return songs;
	}

	
	
	//insert playlists
	
	public  boolean insertData(String playlist_name, int user_id) {
	    isSuccess = false;

	    String insertSql = "INSERT INTO playlist (playlist_name, user_id) VALUES (?, ?)";
	    
	    try {
	        
	    	conn = DBconnection.getInstance().getConnection();

	        pstmt = conn.prepareStatement(insertSql);
	        
	        pstmt.setString(1, playlist_name);
	        pstmt.setInt(2, user_id);

	        int rs = pstmt.executeUpdate();

	        if (rs > 0) {
	            isSuccess = true;
	        }

	    } catch (Exception e) {
	    	logger.log(Level.SEVERE, "Error in inserting data to the playlist database", e);
	    }finally {
	    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
	        try {
	            if (result != null) result.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	        	logger.log(Level.WARNING, "Error closing database resources", e);
	        }
	    }
	    return isSuccess;
	}

	
	//get playlist by userid
	
	public  List<PlaylistModel> getPlaylistsByUser(int user_id) {
	    List<PlaylistModel> playlists = new ArrayList<>();

	    String sql = "SELECT * FROM playlist WHERE user_id = ? ORDER BY createdAt DESC";

	    try { conn = DBconnection.getInstance().getConnection();

	          pstmt = conn.prepareStatement(sql);

	        pstmt.setInt(1, user_id);
	        result = pstmt.executeQuery();

	        while (result.next()) {
	            int id = result.getInt(1);
	            String name = result.getString(2);
	            LocalDateTime createdAt = result.getTimestamp(4).toLocalDateTime();

	            PlaylistModel playlist = new PlaylistModel(id, name, user_id, createdAt);
	            playlists.add(playlist);
	        }

	    } catch (Exception e) {
	    	logger.log(Level.SEVERE, "Error in fetching playlists of the logged user", e);
	    }finally {
	    	
	    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
	        try {
	            if (result != null) result.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	        	logger.log(Level.WARNING, "Error closing database resources", e);
	        }
	    }

	    return playlists;
	}
	
	
//delete playlists
	public  boolean deletePlaylist(int playlistId) {
	     isSuccess = false;
	     
	  // Step 1: Delete related songs from playlist_songs since playlist_id is  foreign key in the playlist_songs
	        String deleteSongsSql = "DELETE FROM playlist_songs WHERE playlist_id = ?";
	        try { 
	        	conn = DBconnection.getInstance().getConnection();

	        	pstmt = conn.prepareStatement(deleteSongsSql);
	            pstmt.setInt(1, playlistId);
	            pstmt.executeUpdate(); // You can ignore rows affected here
	        }
	        catch (Exception e) {
	        	logger.log(Level.SEVERE, "Error in deleteing songs in the playlist", e);
		    }finally {
		    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
		        try {
		            if (result != null) result.close();
		            if (pstmt != null) pstmt.close();
		            if (conn != null) conn.close();
		        } catch (SQLException e) {
		        	logger.log(Level.WARNING, "Error closing database resources", e);
		        }
		    }
	        
	     // Step 2: Delete the playlist itself

	    String sql = "DELETE FROM playlist WHERE playlist_id = ?";

	    try { conn = DBconnection.getInstance().getConnection();

	          pstmt = conn.prepareStatement(sql);

	        pstmt.setInt(1, playlistId);

	        int rowsAffected = pstmt.executeUpdate();
	        isSuccess = rowsAffected > 0;

	    } catch (Exception e) {
	    	logger.log(Level.SEVERE, "Error in deleting the playlist", e);
	    }finally {
	    	
	    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
	        try {
	            if (result != null) result.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	        	logger.log(Level.WARNING, "Error closing database resources", e);
	        }
	    }

	    return isSuccess;
	}



    // Get all genres
	public  List<GenreModel> selectAllGenres() {
		 
		        List<GenreModel> genres = new ArrayList<>();
		        String ALL_GENRES = "SELECT * FROM genres";
		        
		        try { conn = DBconnection.getInstance().getConnection();

		              pstmt = conn.prepareStatement(ALL_GENRES); //Creates a PreparedStatement object for sending parameterized SQL statements to the database. 
		              result = pstmt.executeQuery(); //returns a ResultSet object that contains the data produced by the query
		            
		            while (result.next()) { //Moves the cursor forward one row from its current position.
		                int id = result.getInt(1);
		                String name = result.getString(2);
		                String imageUrl = result.getString(3);
		                
		                genres.add(new GenreModel(id, name, imageUrl));
		            }
		        }catch (Exception e) {
		        	logger.log(Level.SEVERE, "Error in fetching genres", e);
			    }finally {
			    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
			        try {
			            if (result != null) result.close();
			            if (pstmt != null) pstmt.close();
			            if (conn != null) conn.close();
			        } catch (SQLException e) {
			        	logger.log(Level.WARNING, "Error closing database resources", e);
			        }
			    }
		        return genres;
		    }



//filter songs by genre id
public  List<SongsModel> getSongsByGenre(int genreId) {
	
    List<SongsModel> songs = new ArrayList<>();
    String sql = "SELECT * FROM songs WHERE genre_id = ?";

    try { conn = DBconnection.getInstance().getConnection();

          pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, genreId);// This sets the first '?' in the query to the value of genreId
        result = pstmt.executeQuery();

        while (result.next()) {
        	SongsModel song = new SongsModel();
        	song.setId(result.getInt(1));
            song.setTitle(result.getString(2));
            song.setArtist(result.getString(5));
            song.setGenre_id(genreId);
            song.setFile_path(result.getString(4));
            songs.add(song);
        }

    } catch (SQLException e) {
    	logger.log(Level.SEVERE, "Error in fetching songs by genre", e);
    }finally {
    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
        try {
            if (result != null) result.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
        	logger.log(Level.WARNING, "Error closing database resources", e);
        }
    }

    return songs;
}


//add songs to playlists

//passing a playlistSongsModel object (ps) as a parameter because this model object encapsulates both the playlistId and songId
public  void addSongToPlaylist(playlistSongsModel ps) {
	String sql = "INSERT INTO playlist_songs (playlist_id, song_id) VALUES (?, ?)";


    try { conn = DBconnection.getInstance().getConnection();

          pstmt = conn.prepareStatement(sql) ;
          
        pstmt.setInt(1, ps.getPlaylistId());
        pstmt.setInt(2, ps.getSongId());
        pstmt.executeUpdate();
    }
    catch (SQLException e) {
    	logger.log(Level.SEVERE, "Error in inserting songs to the playlist", e);
    }finally {
    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
        try {
            if (result != null) result.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
        	
        	logger.log(Level.WARNING, "Error closing database resources", e);
        }
    }
}



//get songs in a playlist
public  List<SongsModel> getSongsInPlaylist(int playlistId) {
    List<SongsModel> songs = new ArrayList<>();
    
    
    String sql = "SELECT * FROM songs s JOIN playlist_songs ps ON s.song_id = ps.song_id WHERE ps.playlist_id = ?" ;
           
    try {
    	conn = DBconnection.getInstance().getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, playlistId);
       result = pstmt.executeQuery();

        while (result.next()) {
            SongsModel song = new SongsModel(0, sql, playlistId, sql, sql);
            song.setId(result.getInt(1));
            song.setTitle(result.getString(2));
            song.setArtist(result.getString(5));
            song.setFile_path(result.getString(4));
            songs.add(song);
        }
    } catch (Exception e) {
    	logger.log(Level.SEVERE, "Error in fetching songs by playlst", e);
    }finally {
    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
        try {
            if (result != null) result.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
        	logger.log(Level.WARNING, "Error closing database resources", e);
        }
    }
    return songs;
}




//delete songs from playlist

public  boolean deletePlaylistSongs(int songId,int playlistId ) {
    isSuccess = false;

try {
	conn = DBconnection.getInstance().getConnection();

    String sql = "DELETE FROM playlist_songs WHERE playlist_id = ? AND song_id = ?";
     pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, playlistId);
    pstmt.setInt(2, songId);
    pstmt.executeUpdate();
} catch (Exception e) {
	logger.log(Level.SEVERE, "Error in deleting songs in the playlist ", e);
}finally {
	//closing your Connection, PreparedStatement, and ResultSet in the finally block
    try {
        if (result != null) result.close();
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    } catch (SQLException e) {
    	logger.log(Level.WARNING, "Error closing database resources", e);
    }
}
return isSuccess;

}




//update Playlist Name
public  boolean updatePlaylistName(int playlistId, String newName) {
     isSuccess = false;

    try {
    	conn = DBconnection.getInstance().getConnection();

        String sql = "UPDATE playlist SET playlist_name = ? WHERE playlist_id = ?";
         pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, newName);
        pstmt.setInt(2, playlistId);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
        	isSuccess = true;
        }

       

    } catch (Exception e) {
    	logger.log(Level.SEVERE, "Error in updating playlist name", e);
    }finally {
    	//closing your Connection, PreparedStatement, and ResultSet in the finally block
        try {
            if (result != null) result.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
        	logger.log(Level.WARNING, "Error closing database resources", e);
        }
    }

    return isSuccess;
}

	
}
		