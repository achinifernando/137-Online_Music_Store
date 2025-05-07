package musicStore.Servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicStore.Service.Service;
import musicStore.model.playlistSongsModel;


@WebServlet("/AddSongToPlaylistServlet")
public class AddSongToPlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddSongToPlaylistServlet.class.getName());

       
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 //fetching the playlists,songId sent by the add to playlist button
	        int playlistId = Integer.parseInt(request.getParameter("playlistId"));
	        int songId = Integer.parseInt(request.getParameter("songId"));

	        try {
	        	//passing a playlistSongsModel object (ps) as a parameter because this model object encapsulates both the playlistId and songId
	        	playlistSongsModel ps = new playlistSongsModel(playlistId, songId);
	        	
	        	Service service = new Service();
		        service.addSongToPlaylist(ps);
		        response.sendRedirect("PlaylistSongsServlet?id=" + playlistId);


	        } catch (Exception e) {
	        	logger.severe("An error occurred: " + e.getMessage());
	            request.getRequestDispatcher("MusicPage.jsp").forward(request, response);
	        }
	    }
	}