<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.*,musicStore.model.SongsModel,musicStore.Service.Service,musicStore.model.PlaylistModel" %>






<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Music Page</title>
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
      <a href="/login"><button class="btn-login">Login</button></a>
      <a href="/signup"><button class="btn-login">Sign up</button></a>
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
        <h1>Music Fantasy</h1>
        
       
      </div>

      <section class="tracks">

<% List<SongsModel> songs = (List<SongsModel>) request.getAttribute("songs");
for (SongsModel song : songs) { %>
<div class="track">
    <img src="images/song.jpg" class="track-image">
    
    <div class="audio-wrapper">
        <audio controls oncontextmenu="return false;" controlsList="nodownload">
            <source src="<%= song.getFile_path() %>" type="audio/mpeg" />
        </audio>
    </div>
    
    <div class="track-info">
        <strong><%= song.getTitle() %></strong>
        <p><%= song.getArtist() %></p>
    </div>
    
    <div class="actions">
        <form action="AddSongToPlaylistServlet" method="post">
            <input type="hidden" name="songId" value="<%= song.getId() %>" />
            
            <div class="dropdown-container">
            <select class="select" name="playlistId">
                <% List<PlaylistModel> playlists = (List<PlaylistModel>) request.getAttribute("playlists");
                for (PlaylistModel playlist : playlists) { %>
                    <option value="<%= playlist.getPlaylist_id() %>">
                        <%= playlist.getPlaylist_name() %>
                    </option>
                <% } %>
            </select>
 
            <button type="submit" class="btn">Add to Playlist</button>
            </div>
        </form>
    </div>
</div>
<% } %>

      </section>
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









         