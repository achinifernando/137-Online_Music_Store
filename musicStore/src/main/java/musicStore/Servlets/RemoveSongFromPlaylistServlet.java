package musicStore.Servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicStore.Service.Service;

@WebServlet("/RemoveSongFromPlaylistServlet")
public class RemoveSongFromPlaylistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(RemoveSongFromPlaylistServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String songIdStr = request.getParameter("song_id");
        String playlistIdStr = request.getParameter("playlist_id");

        if (songIdStr == null || playlistIdStr == null) {
            logger.warning("Missing song ID or playlist ID.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("Playlists.jsp?error=missing_parameters");
            return;
        }

        int songId, playlistId;
        try {
            songId = Integer.parseInt(songIdStr);
            playlistId = Integer.parseInt(playlistIdStr);
        } catch (NumberFormatException e) {
            logger.warning("Invalid ID format: songId=" + songIdStr + ", playlistId=" + playlistIdStr);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("Playlists.jsp?error=invalid_ids");
            return;
        }

        Service service = new Service();
        boolean success = service.deletePlaylistSongs(songId, playlistId);

        if (success) {
            logger.info("Removed song ID " + songId + " from playlist ID " + playlistId);
            response.sendRedirect("PlaylistSongsServlet?id=" + playlistId);  // Ensure PlaylistSongsServlet loads updated data
        } else {
            logger.severe("Failed to remove song ID " + songId + " from playlist ID " + playlistId);
            response.sendRedirect("PlaylistSongsServlet?id=" + playlistId + "&error=remove_failed");
        }
    }
}
