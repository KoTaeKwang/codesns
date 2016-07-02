package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {
    //****************************************
	// �������� �� ���ϱ�
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
	// ����¡(paging) ó��(GET ���)
	public String pageIndexList(int current_page, int total_page, String list_url) {
		StringBuffer sb = new StringBuffer();
		int numPerBlock = 1;
		int currentPageSetup, n, page;
		
		if (current_page <= 0 || total_page <= 0)
			return "";
		
		sb.append("<style type='text/css'>");
		sb.append("* {margin: 0px; padding: 0px; font-size: 10pt; text-decoration: none; font-family: ���� ���;}");
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
		
		// currentPageSetup : ǥ�� �� ù ������ - 1
		currentPageSetup = (current_page / numPerBlock) * numPerBlock;
		
		if (current_page % numPerBlock == 0)
			currentPageSetup = current_page - numPerBlock;
		
		// 1 [����]
		n = current_page - numPerBlock;
		
		if (total_page > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href='" + list_url + "pageNum=1' class='edge'>1</a>");
			sb.append("&nbsp;<a href='" + list_url + "pageNum=" + n + "'><span class='disabled'>< ����������</span></a>");
		}
		
		// ����¡ ó��
		page = currentPageSetup + 1;
		
		while (page <= total_page && page <= (currentPageSetup + numPerBlock)) {
			if (page == current_page) {
				sb.append("&nbsp;");
			} else {
				sb.append("&nbsp;");
			}
			
			page++;
		}
		
		// [����] ������ ������
		n = current_page + numPerBlock;
		
		if (n > total_page) n = total_page;
		
		if (total_page - currentPageSetup > numPerBlock) {
			sb.append("&nbsp;<a href='" + list_url + "pageNum=" + n + "'><span class='disabled'>������</span></a>");
			sb.append("&nbsp;<a href='" + list_url + "pageNum=" + total_page + "' class='edge'>" + total_page + "</a>");
			sb.append("</div></div>");
		}
		
		return sb.toString();
	}

    //****************************************
	// javascript ������ ó��(javascript listPage() �Լ� ȣ��)
    public String pageIndexList(int current_page, int total_page) {
    	if (current_page < 1 || total_page < 1)
			return "";
		
		StringBuffer sb = new StringBuffer();
		int numPerBlock = 1;
		int currentPageSetup, n;
		
		// ǥ�� �� ù ������
		currentPageSetup = (current_page / numPerBlock) * numPerBlock;
		if (current_page % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		sb.append("<style type='text/css'>");
		sb.append("* {margin: 0px; padding: 0px;}");
		sb.append("</style>");
		
		// ����(10������ ��), ������ ������
		n = current_page + numPerBlock;
		if (n > total_page) n = total_page;
		if (total_page - currentPageSetup > numPerBlock)
			sb.append("<a onclick='listPage(" + n + ");' style='cursor: pointer;'><img src='/codesns2/res/images/more.png'></a>");
		else
			sb.append("<img src='/codesns2/res/images/nomorepage.png'>");
		
		return sb.toString();
    }

     //*****************************************
     // Ư�����ڸ� HTML ���ڷ� ����
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
     // Ư�����ڸ� HTML ���ڷ� ���� �� ���͸� <br>�� ���� 
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
 	// ���ڿ��� ������ ���ϴ� ���ڿ��� �ٸ� ���ڿ��� ��ȯ
 	// String str = replaceAll(str, "\n", "<br>"); // ���͸� <br>�� ��ȯ
 	public String replaceAll(String str, String oldStr, String newStr) throws Exception {
 		if(str == null)
 			return "";

         Pattern p = Pattern.compile(oldStr);
         
         // �Է� ���ڿ��� �Բ� ���� Ŭ���� ����
         Matcher m = p.matcher(str);

         StringBuffer sb = new StringBuffer();
         // ���ϰ� ��ġ�ϴ� ���ڿ��� newStr �� ��ü�ذ��� ���ο� ���ڿ��� �����.
         while(m.find()) {
             m.appendReplacement(sb, newStr);
         }

         // ������ �κ��� ���ο� ���ڿ� ���� �� ���δ�.
         m.appendTail(sb);

 		return sb.toString();
 	}

    //***********************************************
 	// E-Mail �˻�
     public boolean isValidEmail(String email) {
         if (email==null) return false;
         boolean b = Pattern.matches(
        	 "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", 
             email.trim());
         return b;
     }

     //***********************************************
 	// NULL �� ��� ""�� 
     public String checkNull(String str) {
         String strTmp;
         if (str == null)
             strTmp = "";
         else
             strTmp = str;
         return strTmp;
     }
}
