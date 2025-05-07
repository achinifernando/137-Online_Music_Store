<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="musicStore.model.SongsModel" %>
<%@ page import="musicStore.Service.Service" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Playlist Songs</title>
    <link rel="stylesheet" type="text/css" href="css/MusicPage.css">
    
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
      
  <%
  //  }
  %>
</div>
      </div>
   
    <!-- search bar -->



<div class="search-cart-container">
    <form action="searchServlet" method="post" name="search_form">
  <input type="text" placeholder="Search a song..." name="query" class="search-bar">
  
  <input type="submit" value="Search" name="search_data" class="search-btn">
 
  </form>
  
 
</div>
    
</header>

 <main class="main-content">
      <div class="hero glassmorphism">
            <h1>Songs in Playlist</h1>
        
       
      </div>

   <% 
    // Fetch the list of songs and playlistId from request attributes
    List<SongsModel> songs = (List<SongsModel>) request.getAttribute("songs");
   int playlistId = (Integer) request.getAttribute("playlistId");

    // Iterate through the list of songs
    for (SongsModel song : songs) { 
%>
    <div class="track">
        <img src="images/song.jpg" class="track-image">

        <p><strong><%= song.getTitle() %></strong> <br> <%= song.getArtist() %></p>
        <audio controls oncontextmenu="return false;" controlsList="nodownload">
            <source src="<%= song.getFile_path() %>" type="audio/mpeg" />
        </audio>

        <!-- Form to remove the song from the playlist -->
        <form action="RemoveSongFromPlaylistServlet" method="post">
            <input type="hidden" name="song_id" value="<%= song.getId() %>">
            <input type="hidden" name="playlist_id" value="<%= playlistId %>">
            <button type="submit" class="btn">Remove</button>
        </form>
    </div>
<% 
    } // End of song loop
%>

</main>

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
</body>
</html>

</body>
</html>
