<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.form-signin
{
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
}
.form-signin .form-signin-heading, .form-signin .checkbox
{
    margin-bottom: 10px;
}
.form-signin .checkbox
{
    font-weight: normal;
}
.form-signin .form-control
{
    position: relative;
    font-size: 16px;
    height: auto;
    padding: 10px;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
.form-signin .form-control:focus
{
    z-index: 2;
}
.form-signin input[type="text"]
{
    margin-bottom: -1px;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
}
.form-signin input[type="password"]
{
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
.account-wall
{
    margin-top: 20px;
    padding: 40px 0px 20px 0px;
    background-color: #f7f7f7;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.login-title
{
    color: #555;
    font-size: 18px;
    font-weight: 400;
    display: block;
}
.profile-img
{
    width: 96px;
    height: 96px;
    margin: 0 auto 10px;
    display: block;
    -moz-border-radius: 50%;
    -webkit-border-radius: 50%;
    border-radius: 50%;
}
.need-help
{
    margin-top: 10px;
}
.new-account
{
    display: block;
    margin-top: 10px;
}
body {
 background:url(<%=cp%>/icon/back.jpg) no-repeat center center fixed; 
 	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

* {
	margin: 0px; padding: 0px;
}

</style>

<script type="text/javascript">
function login(){
	 var f=document.loginForm;
	 f.action = "<%=cp%>/member/login_ok.do?pageNum=1&scrollHeight=0";
	 f.method="post";
	 f.submit();
}

</script>

</head>
 <body>

<div class="container" style="margin-top: 130px;">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to continue to CodeSns</h1>
           
            <div class="account-wall" style="background: #000000; background : rgba(0, 0, 0, 0.5);">
                <img class="profile-img" src="<%=cp%>/icon/c.png" alt="">
                <form class="form-signin" name="loginForm">
                <input type="text" class="form-control" placeholder="Id" required autofocus name="userId">
                <input type="password" class="form-control" placeholder="Password" required name="userPwd">
                <div style="font-size: 9pt; color:#FFD2FF ">${message}</div>
                <div>
              <input class="btn btn-lg btn-primary btn-block" type="button"
                 onclick="login()" value="log in"/>

                <input class="btn btn-lg btn-success btn-block" type="button"
                 onclick="javascript:location.href='<%=cp%>/member/member.do';" value="sign in"/>
                 
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
  </body>
</html>