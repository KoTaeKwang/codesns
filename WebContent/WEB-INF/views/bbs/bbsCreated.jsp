<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="en">
<script type="text/javascript">

	function check(){
		
		var mode="${mode}";
		var f=document.form;
		var userid="${sessionScope.member.userId}";	
	     if(mode=="created")
	    
	    	 f.action = "<%=cp%>/bbs/created_ok.do?userid="+userid;
	         f.submit();
	}
	
function cancel(){
		
		var mode="${mode}";
		var f=document.form;
		var userid="${sessionScope.member.userId}";	
	    
    	 f.action = "<%=cp%>/bbs/list.do?userid="+userid;
         f.submit();
	}
</script>


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>bootstrap template</title>

    <!-- Bootstrap -->
    <link href="<%=cp%>/css/bootstrap.min.css" rel="stylesheet">
    <!-- font awesome -->
    <link rel="stylesheet" href="<%=cp%>/css/font-awesome.min.css" media="screen" title="no title" charset="utf-8">
    <!-- Custom style -->
    <link rel="stylesheet" href="<%=cp%>/css/style.css" media="screen" title="no title" charset="utf-8">
</head>

<body>
<div class="layoutHeader">
	<jsp:include page="/layout/header.jsp"></jsp:include>
</div>
<br>
  <article class="container">
        <div class="col-md-12">
        <div class="page-header">
    	    <h1>자유 게시판 작성<small> Create bbs board </small></h1>
        </div>	
        
        <form class="form-horizontal" name="form" method="post" enctype="multipart/form-data">
        <div class="form-group">
          <label class="col-sm-3 control-label" for="inputEmail">제목</label>
        <div class="col-sm-6">
          <input class="form-control" id="subject" type="text" placeholder="subject"  name="subject">
        </div>
        </div>
        
       
       <div class="form-group" >
       	  <label class="col-sm-3 control-label" for="inputEmail">사진</label>
       	  <div class="col-sm-6">
       	    <input class="btn btn-success" type="file" name="imagefilename" id="image" />
       	  </div>
       	  </div>
       	  
       	  
       	    <div class="form-group" >
       	     <label class="col-sm-3 control-label" for="inputEmail"></label>
       	   <div class="col-sm-6"  id="image_preview">
       	   <img src="<%=cp%>/image/no.PNG" style="width: 300px; height:300px"/>   	   
       	   <a href="#">Remove</a>  
       	   </div>
        </div>
        
        <div class="form-group">
          <label class="col-sm-3 control-label" for="inputPassword">내용</label>
        <div class="col-sm-6">
        <textarea class="form-control" rows="10" id="inputPassword" name="content" cols="50" ></textarea>
        </div>
        </div>
   

        <div class="form-group">
          <div class="col-sm-12 text-center">
            <input class="btn btn-primary" onclick="check()" value="글올리기" style="width: 100px">
            <input class="btn btn-danger" onclick="cancel()" style="width: 100px" value="작성취소">
          </div>
        </div>
        </form>
          <hr>
        </div>
      </article>


<!-- <form action="" name="form" method="post" enctype="multipart/form-data"> -->
<!-- <div>제목</div> -->
<!--  <input type="text" name="subject"> -->
<!-- <div>내용</div> -->
<!--  <input type="text" name="content"> -->
<!-- <div>첨부파일</div> -->
<!--  <input type="file" name="imagefilename" > -->
<!-- <div><input type="button" value="생성" onclick="check()"/> -->
<!-- </div> -->
<!-- </form> -->

      
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
jQuery('#image').on('change', function () {
    ext = jQuery(this).val().split('.').pop().toLowerCase();
    if (jQuery.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
        resetFormElement(jQuery(this));
        window.alert('Not an image!');
    } else {
        file = jQuery('#image').prop("files")[0];
        blobURL = window.URL.createObjectURL(file);
        jQuery('#image_preview img').attr('src', blobURL);
        jQuery('#image_preview').slideDown();
        jQuery(this).slideUp();
    }
});

/**
onclick event handler for the delete button.
It removes the image, clears and unhides the file input field.
*/
jQuery('#image_preview a').bind('click', function () {
    resetFormElement(jQuery('#image'));
    jQuery('#image').slideDown();
    jQuery(this).parent().slideUp();
    return false;
});

/** 
 * Reset form element
 * 
 * @param e jQuery object
 */
function resetFormElement(e) {
    e.wrap('<form>').closest('form').get(0).reset();
    e.unwrap();
}
</script>


</body>
</html>
