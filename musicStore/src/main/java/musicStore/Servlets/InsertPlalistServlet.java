package musicStore.Servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicStore.Service.Service;

@WebServlet("/InsertPlalistServlet")
public class InsertPlalistServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private static final Logger logger = Logger.getLogger(InsertPlalistServlet.class.getName());

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	        String playlistName = request.getParameter("playlist_name");
	        String userIdStr = request.getParameter("user_id");

	        if (playlistName == null || playlistName.trim().isEmpty() || userIdStr == null || userIdStr.trim().isEmpty()) {
	            logger.warning("Missing playlist name or user ID.");
	            
	            return;
	        }

	        int userId;
	        try {
	            userId = Integer.parseInt(userIdStr);
	        } catch (NumberFormatException e) {
	            logger.warning("Invalid user ID format: " + userIdStr);
	           
	            return;
	        }

	        Service service = new Service();
	        boolean isSuccess = service.insertData(playlistName, userId);

	        if (isSuccess) {
	            logger.info("Playlist created successfully for user ID " + userId);
	        } else {
	            logger.severe("Failed to create playlist for user ID " + userId);
	        }

	        // Forward back to Playlists.jsp in both cases
	        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayServlet");
	        dispatcher.forward(request, response);
	    }
	}