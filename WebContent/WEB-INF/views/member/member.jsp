<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");
%>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<%=cp%>/res/css/member/member.css" type="text/css"/>

<style>	
#image_preview {
    display:none;
}
        
img {
	width:144px; 
	height:144px;
	width: expression(this.width > 200 ? 200: true);
}
	
#prImg{
 	width:144px;
 	height:144px;
 }
 
 body {
 background:url(<%=cp%>/icon/back.jpg) no-repeat center center fixed; 
 	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

.membeItem{
border-radius: 15px;
}

.buttonWrap {
        position:relative;
        float:left;
        overflow:hidden;
        cursor:pointer;
        border-radius: 7px;
        background-image:url('<%=cp%>/res/images/button.png');
        margin-left:20px;

    }
    
    
.buttonWrap input {
        position：absolute;
        filter:alpha(opacity=0);
        opacity:0;
        -moz-opacity:0;
        cursor:pointer;
        width:85px;
        height:32px;
   }

/* 버튼꾸미기 */

.myButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #3dc21b;
	-webkit-box-shadow:inset 0px 1px 0px 0px #3dc21b;
	box-shadow:inset 0px 1px 0px 0px #3dc21b;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #38a354), color-stop(1, #2c8f4f));
	background:-moz-linear-gradient(top, #38a354 5%, #2c8f4f 100%);
	background:-webkit-linear-gradient(top, #38a354 5%, #2c8f4f 100%);
	background:-o-linear-gradient(top, #38a354 5%, #2c8f4f 100%);
	background:-ms-linear-gradient(top, #38a354 5%, #2c8f4f 100%);
	background:linear-gradient(to bottom, #38a354 5%, #2c8f4f 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#38a354', endColorstr='#2c8f4f',GradientType=0);
	background-color:#38a354;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #18ab29;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	padding:6px 37px;
	text-decoration:none;
	text-shadow:0px 1px 0px #3b6b33;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #2c8f4f), color-stop(1, #38a354));
	background:-moz-linear-gradient(top, #2c8f4f 5%, #38a354 100%);
	background:-webkit-linear-gradient(top, #2c8f4f 5%, #38a354 100%);
	background:-o-linear-gradient(top, #2c8f4f 5%, #38a354 100%);
	background:-ms-linear-gradient(top, #2c8f4f 5%, #38a354 100%);
	background:linear-gradient(to bottom, #2c8f4f 5%, #38a354 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#2c8f4f', endColorstr='#38a354',GradientType=0);
	background-color:#2c8f4f;
}
.myButton:active {
	position:relative;
	top:1px;
}


</style>
 </head>

 <body>
 
 <section class="finalize-join-page">
 <div class="page-background">
 	<div class="field-box-wrapper">
 	  		<img src="<%=cp%>/icon/c.png" style="width:80px; height:80px; margin-top:10px">
 	<div class="field-box">
    <form name="memberForm" method="post" onsubmit="return check();" enctype="multipart/form-data">
    	<div id="image" style="float:left;">
		        <dl>
		           <dt></dt>
		          <dd id=prImg style="margin-right:40px;"><img src="<%=cp%>/res/images/profileimage.jpg">
		          <span class="buttonWrap" ><input type="file" name="profile" id="profile" ></span></dd>	        	  
		        	  <dd><div id="image_preview" style="margin-right:40px;"><img src="#" /><br/>
		       		 <a href="#">삭제</a>
	    				</div></dd>   			
		        </dl>
        </div>
	    
	    <div id="membeItem" style="float:left;">
    	<div id="memberCreated">
				<dl>
					<dt>아이디</dt>
					<dd>
						<input type="text" name="userId" id="userId" size="25" class="boxTF" placeholder="아이디를 입력하세요" value="${dto.userId}">
					</dd>			
				</dl>
		</div>
		<div id="memberCreated">
						<dl>
							<dt>패스워드</dt><dd><input type="password" name="userPwd" id="userPwd" size="25" placeholder="패스워드를 입력하세요" maxlength="10"  class="boxTF"></dd>
						</dl>
		</div>
		
		<div id="memberCreated">
						<dl>
							<dt>패스워드</dt>
							<dd>
								  <input type="password" name="userPwd1" id="userPwd1" size="25" maxlength="10"  placeholder="패스워드를 재입력하세요" class="boxTF">
							</dd>
						</dl>
		</div>
		<div id="memberCreated">
						<dl>
							<dt>이메일</dt>
							<dd>
								  <input type="email" name="email" size="25" maxlength="30"  class="boxTF"  placeholder="이메일을 입력하세요"
											value="${dto.email}"/>
							</dd>
						</dl>
		</div>
		<div id="memberCreated">
			<dl>
				<dt>국가 선택</dt>
				<dd>
					<select name="country" style="margin-top:7px">
						<option value="">국가</option>
						<option value="korea">대한민국 Korea, Republic of</option>
						<option value="canada">캐나다 Canada</option>
						<option value="china">중국 China</option>
						<option value="france">프랑스 France</option>		
						<option value="germany">독일 Germany</option>
						<option value="india">인도 India</option>
						<option value="japan">일본 Japan</option>					
						<option value="russia">러시아 Russia</option>	
						<option value="usa">미국 United-State</option>
						<option value="uk">영국 United-Kingdom</option>					
					</select>
				</dd>
			</dl>
		</div>
		</div>
				<div style="clear:both">
					<div id="memberCreated_footer">
							<input type="button" style="align:left; margin:10px" value=" sign in " class="myButton" onclick="memberOk();"/>
					</div>
				</div>
    </form>
    </div>
	</div>    
</div><!-- "page-background" -->
</section>
</body>

<!-- 제이쿼리 불러옴 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
    /** 
         사진 업로드
    * If the filename passes validation it will show the image 
    using it's blob URL and will hide the input field and show a delete
    button to allow the user to remove the image
    */
    jQuery('#profile').on('change', function () {
        ext = jQuery(this).val().split('.').pop().toLowerCase();
        if (jQuery.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
            resetFormElement(jQuery(this));
            window.alert('이미지 파일이 아닙니다.');
        } else {
            file = jQuery('#profile').prop("files")[0];
            blobURL = window.URL.createObjectURL(file);
            jQuery('#image_preview img').attr('src', blobURL);
            jQuery('#prImg').hide();
            jQuery('#image_preview').show();
            jQuery(this).hide();
            }
    });
    /**
    	삭제 클릭하면 그림 사라지고 파일 넣는 필드 숨김 해제
    */
    jQuery('#image_preview a').bind('click', function () {
        resetFormElement(jQuery('#profile'));
        jQuery('#profile').show();
        jQuery(this).parent().hide();
        jQuery('#prImg').show();
        return false;
    });
    
    /* 폼 리셋
     * @param e jQuery object
     */
    function resetFormElement(e) {
        e.wrap('<form>').closest('form').get(0).reset();
        e.unwrap();
    }
    
    /* 항목검사 */
    function memberOk() {
        f = document.memberForm;

    	str = f.userId.value;
        if(!str) {
            alert("\n아이디를 입력하세요. ");
            f.userId.focus();
            return false;
        }
    	if(!/^[a-z][a-z0-9_]{4,9}$/i.test(str)) { 
    		alert("아이디는 5~10자이며 첫글자는 영문자이어야 합니다.");
    		f.userId.focus();
    		return false;
    	}

    	str = f.userPwd.value;
    	if(!str) {
    		alert("\n패스워드를 입력하세요. ");
    		f.userPwd.focus();
    		return false;
    	}
    	if(!/^(?=.*[a-z])(?=.*[!@#$%^*+=-]|.*[0-9]).{5,10}$/i.test(str)) { 
    		alert("패스워드는 5~10자이며 하나 이상 영문자와 숫자나 특수문자가 포함되어야 합니다.");
    		f.userPwd.focus();
    		return false;
    	}
    	
    	if(str!=f.userPwd1.value){
    		alert("\n패스워드가 일치하지 않습니다.");
    		f.userPwd1.focus();
    		return false;
    	}
    	
        str = f.email.value;
        if(!str) {
            alert("\n이메일을 입력하세요. ");
            f.email1.focus();
            return false;
        }

        if(!/^[\w-]{4,}@[\w-]+(\.\w+){1,3}$/i.test(str)){
        	alert("잘못된 이메일 형식입니다");
        	f.email.focus();
        	return false;
        }
        
        str=f.country.value;
        if(!str){
        	alert("\n국가를 선택하세요. ");
            f.country.focus();
            return false;
        }
        
    	var mode="${mode}";
    	if(mode=="member")
     		f.action = "<%=cp%>/member/member_ok.do";
     	else if(mode=="update")
     		f.action = "<%=cp%>/member/update_ok.do";
     		
        f.submit();
    }
  </script>
    