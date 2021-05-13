<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/comman.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Login.js"></script>
</head>
<body>

<!-----------------------------------------------------------Nve bar Start---------------------------------------------->

<nav class="navbar navbar-expand-lg navbar-light bg-light">
<div class="container">
  <a class="navbar-brand" href="#">GB Online Store</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Product<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Contact Us<span class="sr-only">(current)</span></a>
      </li>
      
    </ul>
    <form class="form-inline my-2 my-lg-0">
    	<div class="navbut">
      		<a  href="#">Login</a>
      	</div>
      <a class="navbut" href="#">Register</a>
    </form>
  </div>
  </div>
</nav>

<!-----------------------------------------------------------Nve bar End---------------------------------------------->

	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Login</h1>
				<form id="formLogin">
					Username: <input id="txtUsername" name="txtUsername" type="text"
						class="form-control form-control-sm"> Password: <input
						id="txtPassword" name="txtPassword" type="password"
						class="form-control form-control-sm"> <br> <input
						id="btnLogin" name="btnLogin" type="button" value="Login"
						class="btn btn-primary"> <br> <br>
					<div id="alertError" class="alert alert-danger"></div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>