<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration ERROR</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-center">
    <h1>Task Scheduler!</h1>
</div>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-red">
            <h2>ERROR</h2>
        </div>
        <form method="post" action="/registration"
              class="w3-selection w3-light-grey w3-padding">
            <label>Name:
                <input type="text" name="name"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
            </label>
            <label>Password:
                <input type="password" name="password"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
            </label>
            <label>Email:
                <input type="email" name="email"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
            </label>
            <button type="submit" value="Submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='../..'">Back to main</button>
</div>
</body>
</html>
</body>
</html>