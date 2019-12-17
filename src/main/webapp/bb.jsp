<%--
  Created by IntelliJ IDEA.
  User: LENOVEN
  Date: 2019/12/10
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        input[type=button], input[type=submit], input[type=reset] {
            background-color: lightblue;
            border-radius: 10px;
            color: white;
            padding: 16px 32px;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
        }
        input[type=button]:focus, input[type=submit]:focus, input[type=reset]:focus{
            background-color:red;
        }
        input[type=text] {
            width: 250px;
            border: none;

            border-bottom: 2px solid lightblue;
            font-size: 16px;


            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.5s ease-in-out;

            transition: width 0.4s ease-in-out;
            outline:none;
        }
        input[type=text]:focus {
            width: 30%;
            border-bottom: 2px solid red;
        }
        #name{
            background-image: url('img/name.png')
        }
        #password{
            background-image: url('img/password.png')
        }
    </style>
</head>
<body>
    <form action="login"align="center">
        <input type="text" id="name" name="name"  placeholder="用户"><br>
        <input type="text" id="password" name="password"  placeholder="密码"><br>
        <input type="submit"value="登录">
    </form>
</body>
</html>
