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

@WebServlet("/updatePlaylistNameServlet")
public class updatePlaylistNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(updatePlaylistNameServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newName = request.getParameter("new_name");
        String playlistIdStr = request.getParameter("playlist_id");

        if (newName == null || newName.trim().isEmpty() || playlistIdStr == null || playlistIdStr.trim().isEmpty()) {
            logger.warning("Missing new name or playlist ID");
            
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
        boolean isSuccess = service.updatePlaylistName(playlistId, newName);

        if (isSuccess) {
            logger.info("Playlist name updated successfully for ID " + playlistId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayServlet");
            dispatcher.forward(request, response);
            
        } else {
            logger.severe("Failed to update playlist name for ID " + playlistId);
            
        }
        
        
    }
}