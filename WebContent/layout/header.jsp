<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	
	function headercheck(){
		var f=document.searchForm;
		var mode2="${mode2}";
		
		
		if(mode2=='code')
		f.action="<%=cp%>/code/list.do?pageNum=1&scrollHeight=0";
		
		else
		f.action="<%=cp%>/bbs/list.do?pageNum=1&scrollHeight=0";
		f.submit();
	}


</script>
<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        	<span class="sr-only">Toggle navigation</span>
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>
      		</button>
      		<div style="padding-left: 15px; margin-right: 15px;" >
      		<img class="navbar-brand" src="<%=cp%>/icon/c.png" alt="">
      		</div>
      	</div>
      	
      	 <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav"> 
        		<li><a href="<%=cp%>/code/list.do">코드게시판</a></li>
        		<li><a href="<%=cp%>/bbs/list.do">자유게시판</a></li>
      		</ul>
      		

		<form class="navbar-form navbar-left" role="search" action="" method="post" name="searchForm">
			<div style="padding-left: 230px;">
				<input type="text" class="form-control"  placeholder="Search" name="searchValue" id="searchValue">
				<input type="button" value="검색"  class="btn btn-default" onclick="headercheck()"> 
			</div>
		</form > 	
		
		
		
		<ul class="nav navbar-nav navbar-right">
		<c:if test="${mode2=='code'}">
       		<li><a href="<%=cp%>/code/created.do">글쓰기</a></li>
       	</c:if>
       	<c:if test="${mode2=='bbs'}">
       		<li><a href="<%=cp%>/bbs/created.do">글쓰기</a></li>
       	</c:if>
       	<c:if test="${sessionScope.member.userId != null }">
       		<li><a href="<%=cp%>/member/logout.do">로그아웃</a></li>
       	</c:if>
       	<c:if test="${sessionScope.member.userId == null }">
       		<li><a href="<%=cp%>/member/login.do">로그인</a></li>
       	</c:if>
       	<c:if test="${mode2=='code'}">      	
        	<li><a href="<%=cp%>/member/mypage.do?userid=${sessionScope.member.userId}&beforepage=code">내정보</a></li>
       	</c:if>
       	<c:if test="${mode2=='bbs'}">      	
        	<li><a href="<%=cp%>/member/mypage.do?userid=${sessionScope.member.userId}&beforepage=bbs">내정보</a></li>
       	</c:if>
        	<%-- <li><a href="<%=cp%>/member/mypage.do?userid=${sessionScope.member.userId}">내정보</a></li> --%>
      	</ul>
	</div>
</div>
</nav>
</body>
</html>