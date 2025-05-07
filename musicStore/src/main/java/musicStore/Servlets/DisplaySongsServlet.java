package musicStore.Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import musicStore.Service.Service;
import musicStore.model.PlaylistModel;
import musicStore.model.SongsModel;

@WebServlet("/DisplaySongsServlet")
public class DisplaySongsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//fetching the genre id from the home.jsp
        String genreParam = request.getParameter("genreId");
        int genreId = 0;

        Service service = new Service(); 
        List<SongsModel> songs = null;

        if (genreParam != null) {
           //converting the fetched genre id into an integer
                genreId = Integer.parseInt(genreParam);
                //store the songs of the genre in the song list
                songs = service.getSongsByGenre(genreId);
            
        }
        
        //fetching the playlists of the logged user for pass for the add to playlist button

        int userId = 1; // TODO: Replace with session-based value
        List<PlaylistModel> playlists = service.getPlaylistsByUser(userId);

        request.setAttribute("songs", songs);
        request.setAttribute("playlists", playlists);

        RequestDispatcher dispatcher = request.getRequestDispatcher("MusicPage.jsp");
        dispatcher.forward(request, response);
    }
}
