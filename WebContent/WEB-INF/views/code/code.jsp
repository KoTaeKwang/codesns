
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp=request.getContextPath();
   request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<script src="//code.jquery.com/jquery.min.js"></script>


<script type="text/javascript">

var scrollHeight;

$(document).ready(function() {
   $(window).scroll(function() {
      scrollHeight = $(window).scrollTop();
   
   });
   
   document.body.scrollTop = ${scrollHeight};
});
</script>

<script type="text/javascript">
function listPage(page) {
   var params="pageNum="+page+"&${params}" + "scrollHeight=" + scrollHeight;
   location.href="<%=cp%>/code/list.do?"+params;
}

function like(num) {
   location.href="<%=cp %>/code/updateLike.do?translatedText=${translatedText}&num=" + num+"&scrollHeight="+scrollHeight+"&searchValue=${searchValue}";
}

function dislike(num) {
   location.href="<%=cp%>/code/updateDislike.do?translatedText=${translatedText}&num=" + num+"&scrollHeight="+scrollHeight+"&searchValue=${searchValue}";
}

function transgo(num){
	var content=document.getElementById("memo"+num).value;
	location.href="<%=cp%>/code/change.do?num="+num+"&content="+content+"&country=${sessionScope.member.country}&searchValue=${searchValue}&scrollHeight="+scrollHeight;
}


function moveYourPage(userid) {

      location.href="<%=cp %>/member/yourpage.do?userid=" + userid + "&scrollHeight=" + scrollHeight + "&beforepage=code";  

}


function codecommentOk(num) {
    var f = document.contentform;
    var rc=document.getElementById("commentcontent"+num);
    var str=rc.value;
    if(!str){
       alert("\n내용을 입력하세요. ");
        rc.focus();
        return false;
    }
    
    f.num.value=num;
    f.commentcontent.value=rc.value;
    
    f.action = "<%=cp%>/code/comment.do?translatedText=${translatedText}&scrollHeight="+scrollHeight+"&searchValue=${searchValue}";
    f.submit();
}

function comment_delete(num,comment_num) {
   location.href="<%=cp%>/code/commemt_delete.do?translatedText=${translatedText}&num=" + num+"&scrollHeight="+scrollHeight+"&comment_num="+comment_num+"&searchValue=${searchValue}";
}

</script>


<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>


<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

.highlight {
    background-color: #FFFFB3;
    -moz-border-radius: 5px; /* FF1+ */
    -webkit-border-radius: 5px; /* Saf3-4 */
    border-radius: 3px; /* Opera 10.5, IE 9, Saf5, Chrome */
    -moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.9); /* FF3.5+ */
    -webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.7); /* Saf3.0+, Chrome */
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.4); /* Opera 10.5+, IE 9.0 */
}

.highlight {
    padding:0px 3px;
    padding-left:2px;
    margin:0 -3px;
}



</style>



</head>
<body>
<div style="background-color: #fafafa">
<div class="layoutHeader">
   <jsp:include page="/layout/header.jsp"></jsp:include>
</div>



<c:forEach var="dto" items="${list}">
<div style="width:600px; border: 1px solid #EDEDED; margin-right: auto; margin-left: auto; background-color: #FFFFFF;">

<div class="div" style="text-align: center; height: 40px; line-height: 40px; border-bottom: 1px solid #EDEDED;" >&nbsp;&nbsp;${dto.subject}</div>

<div style="width: 598px; height:40px; border-bottom: 1px solid #EDEDED;">

<div class="div" style=" float:left; text-align: left; width: 296px;  height: 40px; line-height: 40px;">

<img src="<%=cp%>/uploads/profile/${dto.profile}" width="30px" height="30px" style="margin-left: 5px; padding-bottom: 3px" onerror="this.src = '<%=cp%>/image/noprofile.jpg';">
&nbsp;<a onclick="moveYourPage('${dto.userid }');" style="cursor: pointer;">${dto.userid}</a></div>

<div class="div" style=" float:left; height: 40px; width: 298px; line-height: 40px; text-align: right; color: #4470bd">${dto.created}&nbsp;&nbsp;</div>
</div>

<c:if test="${not empty dto.imagefilename}">
<div class="div" style=" height: 500px;">
   <img src="<%=cp%>/uploads/code/${dto.imagefilename}" width="599px" height="499px">
</div>
</c:if>

<div style="width: 598px; height:40px;  border-right: 1px solid #EDEDED; border-bottom: 1px solid #EDEDED;" >
<div class="div" style="width: 296px;  height: 40px; line-height:40px; float:left; padding-left: 10px;  border-bottom: 1px solid #EDEDED;">
<a style="cursor: pointer;" onclick="like('${dto.num}');">
<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true">&nbsp;${dto.liked}</span></a>
&nbsp;&nbsp;&nbsp;<a style="cursor: pointer;" onclick="dislike('${dto.num}');">
<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true">&nbsp;${dto.dislike}</span></a>
</div>

<div class="div" style="width: 300px;  height: 40px; float:left; padding-left: 229px; padding-top: 4px; border-bottom: 1px solid #EDEDED;">

<%-- <input class="btn btn-primary" type="button" value="번역" name="translate" onclick="transgo('${dto.num}','${dto.content}');"> --%>
<button type="button" class="btn btn-info btn-sm" name="translate" onclick="transgo('${dto.num}');">
<span class="glyphicon glyphicon-globe"></span>&nbsp;번역</button>
<input type="hidden" value="${dto.content}" id="memo${dto.num}">
</div>
</div>

<c:if test="${dto.num != num}">
<div class="div"  id="${dto.num}" style="border-bottom:1px solid #EDEDED; word-break:break-all;"><pre style="background:#FFFFFF; background-color: transparent; border: 0" >${dto.content}</pre></div>
</c:if>

<c:if test="${dto.num == num}">
<div class="div"  id="${dto.num}" style="border-bottom:1px solid #EDEDED; word-break:break-all; "><pre style="background:#FFFFFF; background-color: transparent; border: 0" >${translatedText}</pre></div>
</c:if>



<table style="width: 596px; border-right: 1px solid #EDEDED; margin: 0px auto; border-spacing: 0px; table-layout: fixed">      
            <tr align="center" height="0"> 
               <td width="105"></td>
               <td width="380"></td>
               <td width="110"></td>
           </tr>
           
           
<c:forEach var="comment_list" items="${comment_list_list}">
   <c:forEach var="cdto" items="${comment_list}">
      <c:if test="${dto.num==cdto.num}">
           <tr align="center" bgcolor="#ffffff" height="25" >
           
               <td align="left" style="color: #4470bd;  clear: none;">
               
                     &nbsp;<img src="<%=cp%>/uploads/profile/${cdto.profile}"
                     width="20px" height="20px" onerror="this.src = '<%=cp%>/image/noprofile.jpg';" style="margin-right: 5px">
                     <a onclick="moveYourPage('${cdto.userid }');" style="cursor: pointer;">${cdto.userid}</a>
               </td>
               
               <td align="left" style="word-break:break-all; padding-left: 10px; clear: none">
               ${cdto.content}&nbsp;
             </td>
             
              <td align="center" style="float: right; margin-right:8px;height:20px; line-height: 20px; clear: none ;align: right;">${cdto.created}</td>
               <c:if test="${sessionScope.member.userId==cdto.userid}">
                <td align="center" style="float: right; margin-right: 3px;">
               <a style="cursor: pointer;" onclick="comment_delete('${dto.num}','${cdto.comment_num}')">
                <span class="glyphicon glyphicon-remove" aria-hidden="true" style="color:rgb(250,128,114);" ></span></a>
               </td>
               </c:if>
           </tr>
           
           <tr><td height="1" colspan="6" bgcolor="#e4e4e4"></td></tr> 
      </c:if>
   </c:forEach>
</c:forEach>
</table>




           <table style="width: 598px; margin: 0px auto; border-spacing: 0px; height: 40px; border-right: 1px solid #EDEDED;">
                <tr>
               <td width="100" align="center" style="padding-left:10px;">
                      &nbsp;<img src="<%=cp%>/uploads/profile/${sessionScope.member.profile}"
                      width="30px" height="30px" onerror="this.src = '<%=cp%>/image/noprofile.jpg';">
                </td>
                
               <td width="200" align="center" valign="top" style="padding-left:10px; padding-bottom:5px;"> 
                 <input type="text" style="width: 375px; margin-top: 7px" id="commentcontent${dto.num}" class="boxRe">
                     </td>
                     
                 <td align="center" style="float: right; margin-right: 12px; margin-top: 4px">
               
<%--                <input type="button" class="btn btn-primary" onclick="codecommentOk('${dto.num}');" value="등록"> --%>
              
               <button type="button" class="btn btn-warning btn-sm" onclick="codecommentOk('${dto.num}');">
               <span class="glyphicon glyphicon-ok"></span>&nbsp;등록</button>
                
               </td>
             </tr>
         </table>  



</div>


<br>
<br>
<br>

</c:forEach>


<form name="contentform" method="post">
  <input type="hidden" value="${sessionScope.member.userId}" name="userid">
  <input type="hidden" name="num">
  <input type="hidden" name="commentcontent">
</form>

<!-- 스크롤 수정 부분 -->
<div style="margin-bottom: 80px;" align="center">
   ${pageIndexList }
</div>
<!-- 스크롤 수정 부분 -->


<script type="text/javascript" src="<%=cp%>/js/highlight.js"></script>
<script type="text/javascript">

$(document).ready(function(){
   $(function() {

        // pull in the new value
       var searchValue ="${searchValue}";
  
        // remove any old highlighted terms
        $('.div').removeHighlight();

        // disable highlighting if empty
        if ( searchValue ) {
            // highlight the new term
            $('.div').highlight( searchValue );
        }
   });
});


   </script>


</div>
</body>
</html>