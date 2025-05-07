package musicStore.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicStore.Service.Service;
import musicStore.model.PlaylistModel;


@WebServlet("/DisplayServlet")
public class DisplayPlaylistsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int userId = 1; // (Integer) session.getAttribute("userId");
		    Service service = new Service();
		    List<PlaylistModel> playlists = service.getPlaylistsByUser(userId);
		    
		 // Sets playlists list as playlists attribute
	        request.setAttribute("playlists", playlists);
	        request.setAttribute("userId", userId);
	        
	      //Dispatches to Playlists.jsp with the data
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Playlists.jsp"); 
	        dispatcher.forward(request, response);

	}
	
	@Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	          throws ServletException, IOException {
	      doPost(request, response); 
	  }


}
