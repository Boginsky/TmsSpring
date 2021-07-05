<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login.css">
    <style>
        body {
            background-image: url("http://tutfon.ru/wallpapers/image.raw?view=image&type=orig&id=14266");
            color: darkslateblue;
            background-attachment: fixed;
            background-repeat: no-repeat;
            background-size: 100%;
        }
    </style>
</head>
<body>
<div class="container px-4 py-5 mx-auto">
    <div class="card card0">
        <div class="d-flex flex-lg-row flex-column-reverse">
            <div class="card card1">
                <div class="row justify-content-center my-auto">
                    <div class="col-md-8 col-10 my-5">
                        <h6 class="msg-info">Please enter your email and password</h6>
                        <form: form method="post" action="createUser">
                            <div class="form-group"><label class="form-control-label text-muted"></label> <input
                                    type="text" id="name" name="name" placeholder="Name" class="form-control"></div>
                            <h6 class="msg-info"></h6>
                            <div class="form-group"><label class="form-control-label text-muted"></label> <input
                                    type="text" id="email" name="email" placeholder="Email" class="form-control"></div>
                            <h6 class="msg-info"></h6>
                            <div class="form-group"><label class="form-control-label text-muted"></label> <input
                                    type="text" id="password" name="password" placeholder="Password"
                                    class="form-control"></div>
                            <div class="row justify-content-center my-3 px-3">
                                <button class="btn-block btn-color">Sign up</button>
                            </div>
                        </form:>
                    </div>
                </div>
                <div class="bottom text-center mb-5">
                    <p href="#" class="sm-text mx-auto mb-3"></p>
                </div>
            </div>
            <div class="card card2">
                <div class="my-auto mx-md-5 px-md-5 right">
                    <h3 class="text-white">Make your life organized</h3> <small class="text-white">This app is designed
                    to create your own todo list, organize your daily routine, and build good habits.</small>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
