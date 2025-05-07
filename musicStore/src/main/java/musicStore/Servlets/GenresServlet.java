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
import musicStore.model.GenreModel;


@WebServlet("/GenresServlet")
public class GenresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		  //Database Access: Calls DBUtil.selectAllGenres() to get all genres
		  Service service = new Service();
		  List<GenreModel> listGenres = service.selectAllGenres();
			
		 // Sets genres list as allGenres attribute
	        request.setAttribute("allGenres", listGenres);
	        
	        //Dispatches to HomePage.jsp with the data
	        RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage.jsp"); 
	        dispatcher.forward(request, response);
	    }
	  
	  
	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	          throws ServletException, IOException {
	      doPost(request, response); 
	  }

	    
	  
}