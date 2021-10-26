<%@ page import="by.vironit.taskscheduler.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>Task Scheduler</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
      integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png"/>
<style>
    body, h1, h2, h3, h4, h5, h6 {
        font-family: "Raleway", sans-serif
    }

    body, html {
        height: 100%;
        line-height: 1.8;
    }

    .w3-bar .w3-button {
        padding: 16px;
    }
</style>
<body>

<!-- Navbar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar w3-white w3-card" id="myNavbar">
        <a class="w3-bar-item w3-button w3-wide" onclick="location.href='..'">Task Scheduler</a>
        <!-- Right-sided navbar links -->
        <div class="w3-right w3-hide-small">
            <form method="post" action="/logout">
                <a href="/#home" class="w3-bar-item w3-button"><i class="fas fa-user"></i>LOGOUT</a>
                <a href="/#about" class="w3-bar-item w3-button">ABOUT</a>
            </form>
        </div>
        <!-- Hide right-floated links on small screens and replace them with a menu icon -->
        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium"
           onclick="w3_open()">
            <i class="fa fa-bars"></i>
        </a>
    </div>
</div>

<!-- Task Section -->
<div class="w3-container w3-#fbfbfd" style="padding:128px 16px" id="login">
    <h3 class="w3-center">ADDED YOUR FIRST TASK GROUP</h3>
    <p class="w3-center w3-large">Welcome.</p>
    <div style="margin-top:48px">
        <p><i class="fa fa-map-marker fa-fw w3-xxlarge w3-margin-right"></i> Minsk, Belarus</p>
        <p><i class="fa fa-envelope fa-fw w3-xxlarge w3-margin-right"> </i> Email: b.drozdov.vironit@vironit.ru</p>
        <br>
        <form method="post" action="/login">
            <p><input class="w3-input w3-border" type="email" placeholder="Email" required name="email"></p>
            <p><input class="w3-input w3-border" type="password" placeholder="Password" required name="password"></p>
            <p>
                <button class="w3-button w3-black" type="submit" value="Submit">
                    LOGIN
                </button>
            </p>
        </form>
    </div>
</div>

<!-- Footer -->
<footer class="w3-center w3-black w3-padding-64">
    <a class="w3-button w3-light-grey" onclick="location.href='..'"><i
            class="fa fa-arrow-up w3-margin-right"></i>HOME</a>
    <div class="w3-xlarge w3-section">
        <a href="https://www.instagram.com/arimekishou"><i class="fa fa-instagram w3-hover-opacity"></i></a>
        <a href="https://twitter.com/arimekishou"><i class="fa fa-twitter w3-hover-opacity"></i></a>
    </div>
    <p>Powered by <a href="https://github.com/arimekishou" title="github" target="_blank"
                     class="w3-hover-text-green">Boris Drozdov</a></p>
</footer>

<script>

    // Modal Image Gallery
    function onClick(element) {
        document.getElementById("img01").src = element.src;
        document.getElementById("modal01").style.display = "block";
        var captionText = document.getElementById("caption");
        captionText.innerHTML = element.alt;
    }

    // Toggle between showing and hiding the sidebar when clicking the menu icon
    var mySidebar = document.getElementById("mySidebar");

    function w3_open() {
        if (mySidebar.style.display === 'block') {
            mySidebar.style.display = 'none';
        } else {
            mySidebar.style.display = 'block';
        }
    }

    // Close the sidebar with the close button
    function w3_close() {
        mySidebar.style.display = "none";
    }
</script>

</body>
</html>
