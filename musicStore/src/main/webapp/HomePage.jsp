<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="musicStore.model.GenreModel" %>
<%@ page import="musicStore.Service.Service" %>

<%
//User Validation
//String username = (String) session.getAttribute("username");
%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page </title>
<link rel="stylesheet" type="text/css" href="css/HomePage.css">

<!--fontawsome link-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>
<header>
<div class="container">
        <div class="logo">
          <img src="images/logo.png" alt="logo image" />
        </div>
        <nav>
          <ul>
            <li>
              <a href="GenresServlet" class="link">Home</a>
              <a href="#" class="link">About Us</a>
              <a href="DisplayServlet" class="link">Playlists</a>
              <a href="#">Contact</a>
              
            </li>
          </ul>
        </nav>
       <div class="login-button">
  <%
    //String username = (String) session.getAttribute("username");
   // if (username != null && !username.isEmpty()) {
  %>
      <form action="/logout" method="post">
        <button class="btn-login">Logout</button>
      </form>
  <%
  //  } else {
  %>
      <a href="/profile"><button class="btn-login"><i class="fa-solid fa-user"></i><%//=username %></button></a>
      <a href="/login"><button class="btn-login">Login</button></a>
      <a href="/signup"><button class="btn-login">Sign up</button></a>
  <%
  //  }
  %>
</div>

   
    <!-- search bar -->



<div class="search-cart-container">
    <form action="searchServlet" method="post" name="search_form">
  <input type="text" placeholder="Search a song..." name="query" class="search-bar">
  
  <input type="submit" value="Search" name="search_data" class="search-btn">
 
  </form>
  
 
</div>
    
</header>



<!-- genre section -->

<section class="Home">
    <div class="glass-container">
        <div class="text-box">
            <h1>Beat <span>Bazzer</span></h1>
            <h4>"Where words fail, music speaks – find your perfect tune at our store!" 🎵🎶</h4>
        </div>
       <button class="review-btn">Leave a Review</button>
    </div>
     

</section>

<section class="genres">
<div class = "title">
<h3>Albums You Might Be Interested In</h3>
</div>

 <div class="slider-container">
  <div class="slider">
  
  
  <% 
  List<GenreModel> genres = (List<GenreModel>) request.getAttribute("allGenres");

  for (GenreModel genre : genres) { %>
    <a href="DisplaySongsServlet?genreId=<%= genre.getId() %>">

  <div class="card">
          <h4><%= genre.getGenreName() %></h4>
          <img src="<%= genre.getGenreImg() %>" alt="genreimg">
        
        </div></a>
  <%} %>
   </div>
            <button class="slide-left">&#8249;</button>
            <button class="slide-right">&#8250;</button>
            
    </div>
   
  </section>
  <footer>
        <div class="footer-content">
            <div class="footer-section">
                <h4>Quick Links</h4>
                <ul>
                    <li><a href="HomePage.jsp">Home</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="Playlists.jsp">Playlist</a></li>
                    <li><a href="#">Contact</a></li>
                    <li><a href="#">Reviews</a></li>
                </ul>
            </div>
       
            </div>
            
        <div class="footer-bottom">
            &copy; SE/OOP/2025/S1/MLB/WD/137.All Rights Reserved.
        </div>
        
    </footer>
    
    
    
    
    <script>
    //slider options in the genre section
    
  const slider = document.querySelector('.slider');
  const leftBtn = document.querySelector('.slide-left');
  const rightBtn = document.querySelector('.slide-right');

  leftBtn.addEventListener('click', () => {
    slider.scrollBy({
      left: -300, // scroll left by 300px
      behavior: 'smooth'
    });
  });

  rightBtn.addEventListener('click', () => {
    slider.scrollBy({
      left: 300, // scroll right by 300px
      behavior: 'smooth'
    });
  });
</script>
    
  
</body>
</html>