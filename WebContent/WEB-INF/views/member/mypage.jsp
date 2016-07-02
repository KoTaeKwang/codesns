<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<style>

* {
    margin: 0;
    padding: 0;
}

hr{
margin: 10px;}

.id-wrapper{
	font-size: 17pt;
	font-weight: bold;
}


.myPageTop{
	padding: 20px;
	text-align: center;
	width: 75%;
	margin: auto;
	height: 280px;
	color:white
}

label{
	font-weight: bold;
	font-size:11pt;
}

.mainbtn{
	text-align: center;	
}

.myPageContent{
	width:75%;
	margin:auto;
	font-size: 9pt;

	
}

.myPageTotal{
background-color: #FAFAFA;

}

body{
background: #EAEAEA;
}

/* input file 버튼 css*/
div.imgInp {
    width: 120px;
    height: 30px;
    background: url(https://lh6.googleusercontent.com/-dqTIJRTqEAQ/UJaofTQm3hI/AAAAAAAABHo/w7ruR1SOIsA/s157/upload.png);
    background-size: 120px 30px;
    margin: auto;
    overflow: hidden;
}

div.imgInp input {
    display: block !important;
    width: 120px !important;
    height: 30px !important;
    opacity: 0 !important;
    overflow: hidden !important;
}

/* 저장, 친구추가 버튼 css */
.imgButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow:inset 0px 1px 0px 0px #ffffff;
	box-shadow:inset 0px 1px 0px 0px #ffffff;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f9f9f9), color-stop(1, #e9e9e9));
	background:-moz-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:-webkit-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:-o-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:-ms-linear-gradient(top, #f9f9f9 5%, #e9e9e9 100%);
	background:linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f9f9f9', endColorstr='#e9e9e9',GradientType=0);
	background-color:#f9f9f9;
	-moz-border-radius:4px;
	-webkit-border-radius:4px;
	border-radius:4px;
	border:1px solid #dcdcdc;
	display:inline-block;
	cursor:pointer;
	color:#666666;
	font-family:Arial;
	font-size:13px;
	font-weight:bold;
	padding:4px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
}
.imgButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #e9e9e9), color-stop(1, #f9f9f9));
	background:-moz-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:-webkit-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:-o-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:-ms-linear-gradient(top, #e9e9e9 5%, #f9f9f9 100%);
	background:linear-gradient(to bottom, #e9e9e9 5%, #f9f9f9 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#e9e9e9', endColorstr='#f9f9f9',GradientType=0);
	background-color:#e9e9e9;
}
.imgButton:active {
	position:relative;
	top:1px;
}
/* 메인으로 돌아가기 버튼 꾸미기 */
.goMainButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #54a3f7;
	-webkit-box-shadow:inset 0px 1px 0px 0px #54a3f7;
	box-shadow:inset 0px 1px 0px 0px #54a3f7;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #2886b8), color-stop(1, #316e99));
	background:-moz-linear-gradient(top, #2886b8 5%, #316e99 100%);
	background:-webkit-linear-gradient(top, #2886b8 5%, #316e99 100%);
	background:-o-linear-gradient(top, #2886b8 5%, #316e99 100%);
	background:-ms-linear-gradient(top, #2886b8 5%, #316e99 100%);
	background:linear-gradient(to bottom, #2886b8 5%, #316e99 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#2886b8', endColorstr='#316e99',GradientType=0);
	background-color:#2886b8;
	-moz-border-radius:3px;
	-webkit-border-radius:3px;
	border-radius:3px;
	border:1px solid #124d77;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:13px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #3a6191;
}
.goMainButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #316e99), color-stop(1, #2886b8));
	background:-moz-linear-gradient(top, #316e99 5%, #2886b8 100%);
	background:-webkit-linear-gradient(top, #316e99 5%, #2886b8 100%);
	background:-o-linear-gradient(top, #316e99 5%, #2886b8 100%);
	background:-ms-linear-gradient(top, #316e99 5%, #2886b8 100%);
	background:linear-gradient(to bottom, #316e99 5%, #2886b8 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#316e99', endColorstr='#2886b8',GradientType=0);
	background-color:#316e99;
}
.goMainButton:active {
	position:relative;
	top:1px;
}

/* 부트스트랩 css */
.card {
    padding-top: 20px;
    margin: 10px 0 20px 0;
    background-color: rgba(214, 224, 226, 0.2);
    border-top-width: 0;
    border-bottom-width: 2px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    box-shadow: none;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}

.card .card-heading {
    padding: 0 20px;
    margin: 0;
}

.card .card-heading.simple {
    font-size: 20px;
    font-weight: 300;
    color: #777;
    border-bottom: 1px solid #e5e5e5;
}

.card .card-heading.image img {
    display: inline-block;
    width: 46px;
    height: 46px;
    margin-right: 15px;
    vertical-align: top;
    border: 0;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    border-radius: 50%;
}

.card .card-heading.image .card-heading-header {
    display: inline-block;
    vertical-align: top;
}

.card .card-heading.image .card-heading-header h3 {
    margin: 0;
    font-size: 14px;
    line-height: 16px;
    color: #262626;
}

.card .card-heading.image .card-heading-header span {
    font-size: 12px;
    color: #999999;
}

.card .card-body {
    padding: 0 20px;
    margin-top: 20px;
}

.card .card-media {
    padding: 0 20px;
    margin: 0 -14px;
}

.card .card-media img {
    max-width: 100%;
    max-height: 100%;
}

.card .card-actions {
    min-height: 30px;
    padding: 0 20px 20px 20px;
    margin: 20px 0 0 0;
}

.card .card-comments {
    padding: 20px;
    margin: 0;
    background-color: #f8f8f8;
}

.card .card-comments .comments-collapse-toggle {
    padding: 0;
    margin: 0 20px 12px 20px;
}

.card .card-comments .comments-collapse-toggle a,
.card .card-comments .comments-collapse-toggle span {
    padding-right: 5px;
    overflow: hidden;
    font-size: 12px;
    color: #999;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.card-comments .media-heading {
    font-size: 13px;
    font-weight: bold;
}

.card.people {
    position: relative;
    display: inline-block;
    width: 170px;
    height: 300px;
    padding-top: 0;
    margin-left: 20px;
    overflow: hidden;
    vertical-align: top;
}

.card.people:first-child {
    margin-left: 0;
}

.card.people .card-top {
    position: absolute;
    top: 0;
    left: 0;
    display: inline-block;
    width: 170px;
    height: 150px;
    background-color: #ffffff;
}

.card.people .card-top.green {
    background-color: #53a93f;
}

.card.people .card-top.blue {
    background-color: #427fed;
}

.card.people .card-info {
    position: absolute;
    top: 150px;
    display: inline-block;
    width: 100%;
    height: 101px;
    overflow: hidden;
    background: #ffffff;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}

.card.people .card-info .title {
    display: block;
    margin: 8px 14px 0 14px;
    overflow: hidden;
    font-size: 16px;
    font-weight: bold;
    line-height: 18px;
    color: #404040;
}

.card.people .card-info .desc {
    display: block;
    margin: 8px 14px 0 14px;
    overflow: hidden;
    font-size: 12px;
    line-height: 16px;
    color: #737373;
    text-overflow: ellipsis;
}

.card.hovercard {
    position: relative;
    padding-top: 0;
    overflow: hidden;
    text-align: center;
    background-color: rgba(214, 224, 226, 0.2);
}

.card.hovercard .cardheader {
    width:100%;
    background-size:100% cover;
    height: 145px;
    filter:blur(15px);
    -webkit-filter:blur(15px);
    -mox-filter:blur(15px);
}
.card.hovercard .avatar {
    position: relative;
    top: -50px;
    margin-bottom: -50px;
}

.card.hovercard .avatar img {
    width: 100px;
    height: 100px;
    max-width: 100px;
    max-height: 100px;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    border-radius: 50%;
    border: 5px solid rgba(255,255,255,0.5);
}

.card.hovercard .info {
    padding: 4px 8px 10px;
}

.card.hovercard .info .title {
    margin-bottom: 4px;
    font-size: 24px;
    line-height: 1;
    color: #262626;
    vertical-align: middle;
}

.card.hovercard .info .desc {
    overflow: hidden;
    font-size: 15px;
    line-height: 20px;
    color: #737373;
    text-overflow: ellipsis;
}

.card.hovercard .bottom {
    padding: 0 20px;
    margin-bottom: 17px;
}

.btn{ border-radius: 50%; width:32px; height:32px; line-height:18px;  }

</style>


<script>
	function input() {
		document.getElementById("imgInp").select();
		document.selection.clear();
	}

	$(document).ready(function() {
		function readURL(input) {
			if (input.files && input.files[0]) {
				//파일을 읽기 위한 FileReader객체 생성
				var reader = new FileReader();

				//파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
				reader.onload = function(e) {
					//이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
					//(아래 코드에서 읽어들인 dataURL형식)
					$('#blah').attr('src', e.target.result);
				}
				//File내용을 읽어 dataURL형식의 문자열로 저장
				reader.readAsDataURL(input.files[0]);
			}
		}	
		
		//file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
		$("#imgInp").change(function() {
			readURL(this);
		});
	});
	function updatepic(){
		var f = document.forms[0];
		
		url = "<%=cp %>/member/updateProfile_ok.do?userid=${userid}&beforepage=${beforepage}";
		f.action = url;
		f.method="post";
		f.submit();
		
		window.opener.location.reload();
	}
	
	function updateProfile() {
		updatepic();
		//window.close();
	}
</script>
</head>
<body>
<div class="myPageTotal">
<div class="myPageTop">


<c:set var="beforepage" value="${beforepage}"/><br>
<div class="container">
	<div class="row">
		<div class="col-lg-3 col-sm-6">

            <div class="card hovercard">
                <img class="cardheader" src="<%=cp%>/uploads/profile/${dto.profile}" onerror="this.src = '<%=cp%>/image/noprofile.jpg';"/>
                <div class="avatar">
                    <img alt="" src="<%=cp%>/uploads/profile/${dto.profile}" onerror="this.src = '<%=cp%>/image/noprofile.jpg';">
                    <input type="hidden" value="${beforepage}" name="beforepage">
                </div>
                <c:if test="${userid == sessionScope.member.userId }">
					<form enctype="multipart/form-data">
						<div style="width: 240px; margin:0px auto;">
						<div class="imgInp" style="clear:both; width: 120px; float: left">
						<input type="file" id="imgInp" name="imgInp">
						</div>
						<div style="float:left; width: 120px;">
						<input type="button" class="imgButton" value="save" onclick="updateProfile();" style="width: 120px">
						</div>
						</div>
						<input type="hidden" value="${sessionScope.member.userId}" name="userid">
						
					</form>
				</c:if>
                <div class="info" style="clear:both">
                    <div class="title">
                        ${dto.userid}
                    </div>
                    <div class="desc">${dto.country}<br></div>
                </div>
            </div>

        </div>

	</div>
</div>
</div>
<div class="myPageContent"> <!-- 친추, 좋아요, 활동 div-->
<br>
<br>
<label>&nbsp;&nbsp;&nbsp;${msg }팔로잉</label>
<c:set var="msg2" value="${msg2}"/>
<c:choose>
	<c:when test="${userid != sessionScope.member.userId }">
		<c:choose>
			<c:when test="${msg2 >= 10}">
				&nbsp;&nbsp;이미 등록된 친구 입니다.
			</c:when>
			<c:otherwise>
				<input type="button" class="imgButton" value="친구 추가" onclick="javascript:location.href='<%=cp %>/member/addFriend.do?friendid=${userid}';">
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${userid == sessionScope.member.userId }">
		
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

<hr color="#EBEBEB"><br>

<table style="width: 930px; margin: 0px auto; border-spacing: 0px;">
<c:forEach var="dto" items="${list1}" varStatus="status">
		<c:if test="${status.index==0}">
			<tr>
		</c:if>
		<c:if test="${status.index!=0&&status.index%3==0}">
			<c:out value="</tr><tr>" escapeXml="false"/>		
			<tr>
		</c:if>
	
		<td width="250" align="center">
			<div class="imgLayout">
				<img src="<%=cp%>/uploads/profile/${dto.profile }" width="200" height="150" onerror="this.src = '<%=cp%>/image/noprofile.jpg';"><br>
				<a href="<%=cp%>/member/yourpage.do?userid=${dto.userid }&beforepage=${beforepage}" style="text-decoration: none;">${dto.userid }<br></a>
			</div>
</c:forEach>
<c:set var="n" value="${list.size()}"/>
<c:if test="${n>0&&n%3!=0}">
	<c:forEach var="i" begin="${n%3+1}" end="3">
		<td width="210">
			<div class="imgLayout">&nbsp;</div>
		</td>
	</c:forEach>
</c:if>

<c:if test="${n!=0}">
	<c:out value="</tr>" escapeXml="false"/>
</c:if>
	</table>
	 
	<table style="width: 630px; margin: 0px auto; border-spacing: 0px;">
		<tr height="30">
			<td align="center" width="700">
			   <c:if test="${dataCount==0}">
			   	등록된 게시물이 없습니다.
			   </c:if>
			   <c:if test="${dataCount!=0}">
			   	${pageIndexList}
			   </c:if>
			</td>
		</tr>
	</table>
<%-- <c:forEach var="dto" items="${list1 }" varStatus="status">
	
	<img src="<%=cp%>/uploads/profile/${dto.profile }" width="200" height="150" onerror="this.src = '<%=cp%>/image/noprofile.jpg';">
	<a href="<%=cp%>/member/yourpage.do?userid=${dto.userid }">&nbsp;&nbsp;&nbsp;&nbsp;${dto.userid }<br></a>
</c:forEach>
<br> --%>

<hr color="#EBEBEB">

<c:if test="${userid == sessionScope.member.userId }">
<label>&nbsp;&nbsp;&nbsp;${msg }좋아한 코드게시판<br></label>
<hr color="#EBEBEB"><br>
<!-- 좋아요 3개씩 뿌리기 -->
<table style="width: 930px; margin: 0px auto; border-spacing: 0px;">
		<c:forEach var="dto" items="${list2}" varStatus="status">
				<c:if test="${status.index==0}">
					<tr>
				</c:if>
				<c:if test="${status.index!=0&&status.index%3==0}">
					<c:out value="</tr><tr>" escapeXml="false"/>		
					<tr>
				</c:if>
			
				<td width="250" align="center">
					<div class="imgLayout">
						<img src="<%=cp%>/uploads/code/${dto.imagefilename}" width="300" height="300" border="0" onerror="this.src = '<%=cp%>/image/no.PNG';">
						<span class="subject" onclick="javascript:location.href='';">
							<br>
							${dto.subject}<br>
<%-- 							${dto.content }<br> --%>
							${dto.created }<br>
						</span>
					</div>
					
				<c:if test="${dto.imagefilename}==null">
				  <div class="imgLayout">
					<img src="<%=cp%>/image/no.PNG" width="300" height="300" border="0">
						<span class="subject" onclick="javascript:location.href='';">
							${dto.subject}
						</span>
					</div>
				</c:if>
		</c:forEach>
		<c:set var="n" value="${list.size()}"/>
		<c:if test="${n>0&&n%3!=0}">
			<c:forEach var="i" begin="${n%3+1}" end="3">
				<td width="210">
					<div class="imgLayout">&nbsp;</div>
				</td>
			</c:forEach>
		</c:if>
		
		<c:if test="${n!=0}">
			<c:out value="</tr>" escapeXml="false"/>
		</c:if>
			</table>
			 
			<table style="width: 630px; margin: 0px auto; border-spacing: 0px;">
				<tr height="30">
					<td align="center" width="700">
					   <c:if test="${dataCount==0}">
					   	등록된 게시물이 없습니다.
					   </c:if>
					   <c:if test="${dataCount!=0}">
					   	${pageIndexList}
					   </c:if>
					</td>
				</tr>
			</table>
		</c:if>
		
		
<hr color="#EBEBEB">

<c:if test="${userid == sessionScope.member.userId }">
<label>&nbsp;&nbsp;&nbsp;${msg }좋아한 자유 게시판<br></label>
<hr color="#EBEBEB"><br>
<!-- 좋아요 3개씩 뿌리기 -->
<table style="width: 930px; margin: 0px auto; border-spacing: 0px;">
		<c:forEach var="dto" items="${list5}" varStatus="status">
				<c:if test="${status.index==0}">
					<tr>
				</c:if>
				<c:if test="${status.index!=0&&status.index%3==0}">
					<c:out value="</tr><tr>" escapeXml="false"/>		
					<tr>
				</c:if>
			
				<td width="250" align="center">
					<div class="imgLayout">
						<img src="<%=cp%>/uploads/bbs/${dto.imagefilename}" width="300" height="300" border="0" onerror="this.src = '<%=cp%>/image/no.PNG';">
						<span class="subject" onclick="javascript:location.href='';">
							<br>
							${dto.subject}<br>
<%-- 							${dto.content }<br> --%>
							${dto.created }<br>
						</span>
					</div>
					
				<c:if test="${dto.imagefilename}==null">
				  <div class="imgLayout">
					<img src="<%=cp%>/image/no.PNG" width="300" height="300" border="0">
						<span class="subject" onclick="javascript:location.href='';">
							${dto.subject}
						</span>
					</div>
				</c:if>
		</c:forEach>
		<c:set var="n" value="${list.size()}"/>
		<c:if test="${n>0&&n%3!=0}">
			<c:forEach var="i" begin="${n%3+1}" end="3">
				<td width="210">
					<div class="imgLayout">&nbsp;</div>
				</td>
			</c:forEach>
		</c:if>
		
		<c:if test="${n!=0}">
			<c:out value="</tr>" escapeXml="false"/>
		</c:if>
			</table>
			 
			<table style="width: 630px; margin: 0px auto; border-spacing: 0px;">
				<tr height="30">
					<td align="center" width="700">
					   <c:if test="${dataCount==0}">
					   	등록된 게시물이 없습니다.
					   </c:if>
					   <c:if test="${dataCount!=0}">
					   	${pageIndexList}
					   </c:if>
					</td>
				</tr>
			</table>
		</c:if>
		

<br>
<hr color="#EBEBEB">
<label>&nbsp;&nbsp;&nbsp;${msg }코드 게시판 활동</label><br>
<hr color="#EBEBEB"><br>
<!-- 3개씩 뿌리기 -->
<table style="width: 930px; margin: 0px auto; border-spacing: 0px;">
		<c:forEach var="dto" items="${list3}" varStatus="status">
				<c:if test="${status.index==0}">
					<tr>
				</c:if>
				<c:if test="${status.index!=0&&status.index%3==0}">
					<c:out value="</tr><tr>" escapeXml="false"/>		
					<tr>
				</c:if>
			
				<td width="250" align="center">
					<div class="imgLayout">
						<img src="<%=cp%>/uploads/code/${dto.imagefilename}" width="300" height="300" border="0" onerror="this.src = '<%=cp%>/image/no.PNG';">
						<span class="subject" onclick="javascript:location.href='';">
							<br>
							${dto.subject}<br>
<%-- 							${dto.content }<br> --%>
							${dto.created }<br>
						</span>
					</div>
					
				<c:if test="${dto.imagefilename}==null">
				  <div class="imgLayout">
					<img src="<%=cp%>/image/no.PNG" width="300" height="300" border="0">
						<span class="subject" onclick="javascript:location.href='';">
							${dto.subject}
						</span>
					</div>
				</c:if>
		</c:forEach>
		<c:set var="n" value="${list.size()}"/>
		<c:if test="${n>0&&n%3!=0}">
			<c:forEach var="i" begin="${n%3+1}" end="3">
				<td width="210">
					<div class="imgLayout">&nbsp;</div>
				</td>
			</c:forEach>
		</c:if>
		
		<c:if test="${n!=0}">
			<c:out value="</tr>" escapeXml="false"/>
		</c:if>
			</table>
			 
			<table style="width: 630px; margin: 0px auto; border-spacing: 0px;">
				<tr height="30">
					<td align="center" width="700">
					   <c:if test="${dataCount==0}">
					   	등록된 게시물이 없습니다.
					   </c:if>
					   <c:if test="${dataCount!=0}">
					   	${pageIndexList}
					   </c:if>
					</td>
				</tr>
			</table>
			<hr color="#EBEBEB">

<label>&nbsp;&nbsp;&nbsp;${msg }자유 게시판 활동</label><br>
<hr color="#EBEBEB"><br>
<!-- 3개씩 뿌리기 -->
<table style="width: 930px; margin: 0px auto; border-spacing: 0px;">
		<c:forEach var="dto" items="${list4}" varStatus="status">
				<c:if test="${status.index==0}">
					<tr>
				</c:if>
				<c:if test="${status.index!=0&&status.index%3==0}">
					<c:out value="</tr><tr>" escapeXml="false"/>		
					<tr>
				</c:if>
			
				<td width="250" align="center">
					<div class="imgLayout">
						<img src="<%=cp%>/uploads/bbs/${dto.imagefilename}" width="300" height="300" border="0" onerror="this.src = '<%=cp%>/image/no.PNG';">
						<span class="subject" onclick="javascript:location.href='';">
							<br>
							${dto.subject}<br>
<%-- 							${dto.content }<br> --%>
							${dto.created }<br>
						</span>
					</div>
					
				<c:if test="${dto.imagefilename}==null">
				  <div class="imgLayout">
					<img src="<%=cp%>/image/no.PNG" width="300" height="300" border="0">
						<span class="subject" onclick="javascript:location.href='';">
							${dto.subject}
						</span>
					</div>
				</c:if>
		</c:forEach>
		<c:set var="n" value="${list.size()}"/>
		<c:if test="${n>0&&n%3!=0}">
			<c:forEach var="i" begin="${n%3+1}" end="3">
				<td width="210">
					<div class="imgLayout">&nbsp;</div>
				</td>
			</c:forEach>
		</c:if>
		
		<c:if test="${n!=0}">
			<c:out value="</tr>" escapeXml="false"/>
		</c:if>
			</table>
			 
			<table style="width: 630px; margin: 0px auto; border-spacing: 0px;">
				<tr height="30">
					<td align="center" width="700">
					   <c:if test="${dataCount==0}">
					   	등록된 게시물이 없습니다.
					   </c:if>
					   <c:if test="${dataCount!=0}">
					   	${pageIndexList}
					   </c:if>
					</td>
				</tr>
			</table>
			<hr color="#EBEBEB">
			

<br>
<div class="mainbtn">
<input type="button" class="goMainButton" value="메인으로" onclick="javascript:location.href='<%=cp %>/${beforepage}/list.do?pageNum=1&scrollHeight=${scrollHeight }';">
</div>
<br>
<br>
</div>
</div>
</body>
</html>