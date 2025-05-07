<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="musicStore.model.SongsModel,musicStore.model.PlaylistModel" %>
<%@ page import="musicStore.Service.Service" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Results</title>
<link rel="stylesheet" type="text/css" href="css/MusicPage.css">

<!--fontawsome link-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>


<%
//fetch the playlist id sent from playlist.jsp
  int userId = 1;
  Service service = new Service();
 List<PlaylistModel> playlists = service.getPlaylistsByUser(userId);
 
 
    List<SongsModel> songs = (List<SongsModel>)request.getAttribute("songs");
%>
 <main class="main-content">
      <div class="hero glassmorphism">
            <h1><h2>Search Results:</h2></h1>
        
       
      </div>

<section class="tracks">

<% if (songs != null && !songs.isEmpty()) {

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
                <% for (PlaylistModel playlist : playlists) { %>
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
<%
        }
      } else if (songs != null) {
    %>
        <p>No songs found.</p>
    <%
      }
    %>

</section>

</main>
</body>
</html>