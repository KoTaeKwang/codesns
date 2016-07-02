package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {
    //****************************************
	// 총페이지 수 구하기
	public int getPageCount(int numPerPage, int dataCount) {
		int pageCount=0;
		
		if(dataCount > 0) {
			if(dataCount % numPerPage == 0)
				pageCount=dataCount/numPerPage;
			else
				pageCount=dataCount/numPerPage+1;
		}
		
		return pageCount;
	}
	
    //****************************************
	// 페이징(paging) 처리(GET 방식)
	public String pageIndexList(int current_page, int total_page, String list_url) {
		StringBuffer sb = new StringBuffer();
		int numPerBlock = 1;
		int currentPageSetup, n, page;
		
		if (current_page <= 0 || total_page <= 0)
			return "";
		
		sb.append("<style type='text/css'>");
		sb.append("* {margin: 0px; padding: 0px; font-size: 10pt; text-decoration: none; font-family: 맑은 고딕;}");
		sb.append(".list_number {padding-top: 25px; text-align: center;}");
		sb.append(".list_n_menu {padding: 3px; margin: 3px; text-align: center;}");
		sb.append(".list_n_menu .edge {border-color: transparent; border-width: 1px; border-style: solid; padding: 5px 8px 4px 8px; font-weight: bold; margin: 2px; color: #666666;}");
		sb.append(".list_n_menu .edge:HOVER {border: #e9e9e9 1px solid; color: #666666;}");
		sb.append(".list_n_menu .edge:ACTIVE {border: #e9e9e9 1px solid; color: #666666;}");
		sb.append(".list_n_menu .current {border: #4dc136 1px solid; padding: 5px 8px 4px 8px; font-weight: bold; margin: 2px; color: #1eb501;}");
		sb.append(".list_n_menu .disabled {border-color: transparent; border-width: 1px; border-style: solid; padding: 5px 8px 4px 8px; margin: 2px; color: #666666;}");
		sb.append(".list_n_menu .disabled:HOVER {border: #e9e9e9 1px solid;}");
		sb.append("</style>");
		
		sb.append("<div class='list_number'><div class='list_n_menu'>");
		
		if (list_url.indexOf("?") != -1)
			list_url += "&";
		else
			list_url += "?";
		
		// currentPageSetup : 표시 할 첫 페이지 - 1
		currentPageSetup = (current_page / numPerBlock) * numPerBlock;
		
		if (current_page % numPerBlock == 0)
			currentPageSetup = current_page - numPerBlock;
		
		// 1 [이전]
		n = current_page - numPerBlock;
		
		if (total_page > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href='" + list_url + "pageNum=1' class='edge'>1</a>");
			sb.append("&nbsp;<a href='" + list_url + "pageNum=" + n + "'><span class='disabled'>< 이전페이지</span></a>");
		}
		
		// 페이징 처리
		page = currentPageSetup + 1;
		
		while (page <= total_page && page <= (currentPageSetup + numPerBlock)) {
			if (page == current_page) {
				sb.append("&nbsp;");
			} else {
				sb.append("&nbsp;");
			}
			
			page++;
		}
		
		// [다음] 마지막 페이지
		n = current_page + numPerBlock;
		
		if (n > total_page) n = total_page;
		
		if (total_page - currentPageSetup > numPerBlock) {
			sb.append("&nbsp;<a href='" + list_url + "pageNum=" + n + "'><span class='disabled'>더보기</span></a>");
			sb.append("&nbsp;<a href='" + list_url + "pageNum=" + total_page + "' class='edge'>" + total_page + "</a>");
			sb.append("</div></div>");
		}
		
		return sb.toString();
	}

    //****************************************
	// javascript 페이지 처리(javascript listPage() 함수 호출)
    public String pageIndexList(int current_page, int total_page) {
    	if (current_page < 1 || total_page < 1)
			return "";
		
		StringBuffer sb = new StringBuffer();
		int numPerBlock = 1;
		int currentPageSetup, n;
		
		// 표시 할 첫 페이지
		currentPageSetup = (current_page / numPerBlock) * numPerBlock;
		if (current_page % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		sb.append("<style type='text/css'>");
		sb.append("* {margin: 0px; padding: 0px;}");
		sb.append("</style>");
		
		// 다음(10페이지 후), 마지막 페이지
		n = current_page + numPerBlock;
		if (n > total_page) n = total_page;
		if (total_page - currentPageSetup > numPerBlock)
			sb.append("<a onclick='listPage(" + n + ");' style='cursor: pointer;'><img src='/codesns2/res/images/more.png'></a>");
		else
			sb.append("<img src='/codesns2/res/images/nomorepage.png'>");
		
		return sb.toString();
    }

     //*****************************************
     // 특수문자를 HTML 문자로 변경
	public String escape(String str) {
		if(str==null||str.length()==0)
			return "";
		
		StringBuilder builder = new StringBuilder((int)(str.length() * 1.2f));

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
			case '<':
				builder.append("&lt;");
				break;
			case '>':
				builder.append("&gt;");
				break;
			case '&':
				builder.append("&amp;");
				break;
			case '\"':
				builder.append("&quot;");
				break;
			default:
				builder.append(c);
			}
		}
		return builder.toString();
	}

     //***********************************************
     // 특수문자를 HTML 문자로 변경 및 엔터를 <br>로 변경 
     public String htmlSymbols(String str) {
		if(str==null||str.length()==0)
			return "";

    	 str=str.replaceAll("&", "&amp;");
    	 str=str.replaceAll("\"", "&quot;");
    	 str=str.replaceAll(">", "&gt;");
    	 str=str.replaceAll("<", "&lt;");
    	 
    	 str=str.replaceAll(" ", "&nbsp;");
    	 str=str.replaceAll("\n", "<br>");
    	 
    	 return str;
     }

    //***********************************************
 	// 문자열의 내용중 원하는 문자열을 다른 문자열로 변환
 	// String str = replaceAll(str, "\n", "<br>"); // 엔터를 <br>로 변환
 	public String replaceAll(String str, String oldStr, String newStr) throws Exception {
 		if(str == null)
 			return "";

         Pattern p = Pattern.compile(oldStr);
         
         // 입력 문자열과 함께 매쳐 클래스 생성
         Matcher m = p.matcher(str);

         StringBuffer sb = new StringBuffer();
         // 패턴과 일치하는 문자열을 newStr 로 교체해가며 새로운 문자열을 만든다.
         while(m.find()) {
             m.appendReplacement(sb, newStr);
         }

         // 나머지 부분을 새로운 문자열 끝에 덫 붙인다.
         m.appendTail(sb);

 		return sb.toString();
 	}

    //***********************************************
 	// E-Mail 검사
     public boolean isValidEmail(String email) {
         if (email==null) return false;
         boolean b = Pattern.matches(
        	 "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", 
             email.trim());
         return b;
     }

     //***********************************************
 	// NULL 인 경우 ""로 
     public String checkNull(String str) {
         String strTmp;
         if (str == null)
             strTmp = "";
         else
             strTmp = str;
         return strTmp;
     }
}
