<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="java.util.List" %>
<%@ page import="musicStore.model.PlaylistModel" %>
<%@ page import="musicStore.Service.Service" %>


    


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Playlists</title>
<link rel="stylesheet" type="text/css" href="css/playlist.css">

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

<main class="main-content">
      <div class="hero glassmorphism">
        <h1>Playlist</h1>
        
        <div class="actions">
          
          <button class="btn" onclick="openForm()">Create a Playlist</button>
          
          
        </div>
      </div>


<!--  playlist album cards -->

<div class="album-container">
    
    <% List<PlaylistModel> playlists = (List<PlaylistModel>) request.getAttribute("playlists");
    int userId = (Integer) request.getAttribute("userId");
    
    
    for (PlaylistModel playlist : playlists) { %>
    <div class="album-card">
       <img alt="playlist-img" src="images/FlatGold.jpg">
     
      <h2><%=playlist.getPlaylist_name() %></h2><br>
     
     
     <!--   delete playlists form-->
  <form action="DeletePlaylistServlet" method="post" class="delete-form">
    <input type="hidden" name="playlist_id" value="<%=playlist.getPlaylist_id()%>">
    <button type="submit" class="trash-icon">
        <i class="fas fa-trash"></i>
    </button>
</form>



 <!-- Trigger Button for View Playlist-->
  <a href="PlaylistSongsServlet?id=<%= playlist.getPlaylist_id() %>">
    <button class="btn">View Playlist</button>
</a>


 <!-- Trigger Button for Update-->
<button class="btn" onclick="openUpdateForm(<%= playlist.getPlaylist_id() %>, '<%= playlist.getPlaylist_name() %>')">
    Update Name
</button>
    </div>
  
   
 
   <% } %>
   
 
  </div>
  

</main>





<!-- The create playlist form -->
<div class="form-popup" id="myForm">
  <form class="form-container" method="post" action="InsertPlalistServlet" onsubmit="return validateForm()">
    <h1>Create Playlist</h1>

    <label for="playlist-name"><b>Playlist Name</b></label>
    <input type="text" id="playlist-name" name="playlist_name" placeholder="Enter playlist name" required maxlength="50" pattern="[a-zA-Z0-9\s]+">
    <input type="hidden"  name="user_id" value="<%=userId %>">

    <input type="submit" class="btn">
    <button onclick="closeForm()" class="btn">Cancel</button>
  </form>
  
</div>





<!-- update Playlist Name form -->

<div id="updatePopup" class="form-popup">
  <form class="form-container" method="post" action="updatePlaylistNameServlet" >
    <h2>Update Playlist Name</h2>

    <input type="hidden" name="playlist_id" id="updatePlaylistId">
    
    <label for="new_name"><b>New Name</b></label>
    <input type="text" name="new_name" id="updatePlaylistName" required maxlength="50" pattern="[a-zA-Z0-9\s]+">
    
    <input type="submit" class="btn" value="Update">
    <button type="button" class="btn cancel" onclick="closeUpdateForm()">Cancel</button>
  </form>
</div>









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
    
    /* create playlist popup form*/
    function openForm() {
      document.getElementById("myForm").style.display = "block";
    }

    function closeForm() {
      document.getElementById("myForm").style.display = "none";
    }
    
    
    /* update playlist name popup form*/
    function openUpdateForm(id, name) {
        document.getElementById("updatePlaylistId").value = id;
        document.getElementById("updatePlaylistName").value = name;
        document.getElementById("updatePopup").style.display = "block";
    }

    function closeUpdateForm() {
        document.getElementById("updatePopup").style.display = "none";
    }

    /* validate form input*/
    function validateForm() {
  let x = document.forms["myForm"]["playlist_name"].value;
  if (x == "") {
    alert("Name must be filled out");
    return false;
  }
}
    </script>
</body>
</html>