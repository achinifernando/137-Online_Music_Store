package musicStore.Servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicStore.Service.Service;


@WebServlet("/DeletePlaylistServlet")
public class DeletePlaylistServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private static final Logger logger = Logger.getLogger(DeletePlaylistServlet.class.getName());

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String playlistIdStr = request.getParameter("playlist_id");

	        // Check if the playlist ID parameter is missing
	        if (playlistIdStr == null || playlistIdStr.trim().isEmpty()) {
	            logger.warning("Missing playlist ID parameter");
	            
	            return;
	        }

	        int playlistId;
	        try {
	            playlistId = Integer.parseInt(playlistIdStr);
	        } catch (NumberFormatException e) {
	            logger.warning("Invalid playlist ID format: " + playlistIdStr);
	            
	            return;
	        }

	        Service service = new Service();
	        boolean success = service.deletePlaylist(playlistId);

	        if (success) {
	            logger.info("Playlist with ID " + playlistId + " deleted successfully.");
	            // redirect to reload deleted playlist
		        response.sendRedirect("DisplayServlet?id=" + playlistId);
	            
	        } else {
	            logger.severe("Failed to delete playlist with ID " + playlistId);

	        }
	       
	    }
	}