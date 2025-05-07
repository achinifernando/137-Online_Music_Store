package musicStore.Servlets;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicStore.Service.Service;
import musicStore.model.PlaylistModel;
import musicStore.model.SongsModel;

@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            String query = request.getParameter("query");
            
            Service service = new Service();
            List<SongsModel> songs = service.Search(query);  // Homecontroller call
            int userId = 1;  // Replace with session or actual user ID
            List<PlaylistModel> playlists = service.getPlaylistsByUser(userId);

            request.setAttribute("songs", songs);
            request.setAttribute("playlists", playlists);
            request.getRequestDispatcher("SearchResults.jsp").forward(request, response);
        }


}
