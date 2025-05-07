package musicStore.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicStore.Service.Service;
import musicStore.model.SongsModel;

@WebServlet("/PlaylistSongsServlet")
public class PlaylistSongsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//fetching the songs in the playlist by passing playlistId to the view playlist button
		
		int playlistId = Integer.parseInt(request.getParameter("id"));
		
	    Service service = new Service();
	    List<SongsModel> songs = service.getSongsInPlaylist(playlistId);

	    request.setAttribute("songs", songs);
	    request.setAttribute("playlistId", playlistId);
	    request.getRequestDispatcher("viewPlaylist.jsp").forward(request, response);
	}

	
	

}
