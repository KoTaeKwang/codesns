package com.bbs;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.MyUtil;

@WebServlet("/bbs/*")
	public class bbsServlet extends HttpServlet {
		private String basicnum="-1";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // �ѱ�
		String cp=req.getContextPath();
		String uri=req.getRequestURI();
		
		bbsDAO dao = new bbsDAO();
		bbs_commentDAO cdao = new bbs_commentDAO();
		MyUtil util=new MyUtil();
		
		HttpSession session = req.getSession(); // 
		String root = session.getServletContext().getRealPath("/"); // �̹��� �н� ����
		String pathname = root + File.separator + "uploads" + File.separator + "bbs"; // ��μ���
		File f = new File(pathname); //pathname�� ���� ���ϰ�ü ����  
		if (!f.isFile()) // �н����Ӱ� ������ ������ ������
			f.mkdirs();  // ���� ����� 
		
		
		if(uri.indexOf("created.do")!=-1){  //�߰��������� 

			req.setAttribute("mode", "created");
			forward(req,resp, "/WEB-INF/views/bbs/bbsCreated.jsp");
			
		}else if(uri.indexOf("created_ok.do")!=-1){  //�Ϸ� ��������
			
			String encType = "utf-8";
			int maxSize = 10 * 1024 * 1024;

			MultipartRequest mreq = new MultipartRequest(req, pathname, maxSize, encType,
					new DefaultFileRenamePolicy()); // �̹������� �ҷ����� ���� ��Ƽ��Ʈ������Ʈ ��ü ���� 
			
			bbsDTO dto = new bbsDTO();
			
			dto.setSubject(mreq.getParameter("subject"));
			dto.setUserid(mreq.getParameter("userid"));
			dto.setImagefilename(mreq.getFilesystemName("imagefilename"));
			dto.setContent(mreq.getParameter("content"));
			
			dao.insertBbs(dto);
			
			resp.sendRedirect(cp+"/bbs/list.do");
		}else if(uri.indexOf("list.do")!=-1){
			// ��ũ�� ���� �κ�
			String pageNum = req.getParameter("pageNum");
			String scrollHeight = req.getParameter("scrollHeight");
			
			int current_page = 1;
			
			if (pageNum != null)
				current_page = Integer.parseInt(pageNum);
			
			
			// ��ũ�� ���� �κ�
			if(scrollHeight==null)
				scrollHeight="0";
			
			
			String searchValue=req.getParameter("searchValue");
			if(searchValue==null) searchValue="";   //searchValue ���϶� 
			
			if(req.getMethod().equalsIgnoreCase("GET")) //��ҹ��ڹ���
				searchValue=URLDecoder.decode(searchValue,"UTF-8");  //Ȥ�ø𸣴� decode�Ѵ�.
			
			// ��ũ�� ���� �κ�
		int dataCount;
			
			if (searchValue.length() == 0)
				dataCount = dao.dataCount();
			else
				dataCount = dao.dataCount(searchValue);
				
			int numPerPage = 10;
			int total_page = util.getPageCount(numPerPage, dataCount);
			
			if (current_page > total_page)
				current_page = total_page;
			
			// �Խù��� ������ ���۰� ��
			int start = 1;
			int end = current_page * numPerPage;
			// ��ũ�� ���� �κ�
			
			List<bbsDTO> list;
			
			if (searchValue.length() == 0)
				list = dao.listBbs(start, end);
			else
				list = dao.listBbs(start, end, searchValue);
			
//			if(searchValue.length()==0)  //�˻� �������  ��ü �ڵ� �Խ��� ������ �ҷ���
//				list=dao.ListCode();
//			else                         //�ƴҰ�� �˻����ǿ� �´� �ڵ� �Խ��� ������ �ҷ��� 
//				list=dao.ListSearchCode(searchValue);  
		
			
			Date endDate = new Date(); //���ó�¥
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

			Iterator<bbsDTO> it = list.iterator();
			
			while(it.hasNext())
			{
				bbsDTO dto = it.next();
	
				String today=sdf.format(endDate).substring(0,10);
				String [] arr=dto.getCreated().split(" ");
				
				if(today.equals(arr[0]))
				{
					try {
						String Dtime = null;
						
						if ((endDate.getTime() - sdf.parse(dto.getCreated()).getTime()) / (1000) < 60)
							Dtime = (endDate.getTime() - sdf.parse(dto.getCreated()).getTime()) / (1000) + "�� ��";
						else if ((endDate.getTime() - sdf.parse(dto.getCreated()).getTime()) / (60 * 1000) < 60)
							Dtime = (endDate.getTime() - sdf.parse(dto.getCreated()).getTime()) / (60 * 1000) + "�� ��";
						else if ((endDate.getTime() - sdf.parse(dto.getCreated()).getTime()) / (60 * 60 * 1000) < 24)
							Dtime = (endDate.getTime() - sdf.parse(dto.getCreated()).getTime()) / (60 * 60 * 1000) + "�ð� ��";
						
						dto.setCreated(Dtime);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				else
					dto.setCreated(arr[0]);
			}
			
			List<List<bbs_commentDTO>> comment_list_list= new ArrayList<>();
			List<bbs_commentDTO> comment_list=null;
			Date cendDate = new Date(); //���ó�¥
			SimpleDateFormat csdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			
			for(int i=0; i<list.size(); i++) {
			int cnum = list.get(i).getNum();

			comment_list=cdao.ListbbsComment(cnum);
			
			Iterator<bbs_commentDTO> cit = comment_list.iterator();
			while(cit.hasNext())
			{
				bbs_commentDTO dto = cit.next();
	
				String today=csdf.format(cendDate).substring(0,10);
				String [] arr=dto.getCreated().split(" ");
				
				if(today.equals(arr[0]))
				{
					try {
						String Dtime = null;
						
						if ((cendDate.getTime() - csdf.parse(dto.getCreated()).getTime()) / (1000) < 60)
							Dtime = (cendDate.getTime() - csdf.parse(dto.getCreated()).getTime()) / (1000) + "�� ��";
						else if ((cendDate.getTime() - csdf.parse(dto.getCreated()).getTime()) / (60 * 1000) < 60)
							Dtime = (cendDate.getTime() - csdf.parse(dto.getCreated()).getTime()) / (60 * 1000) + "�� ��";
						else if ((cendDate.getTime() - csdf.parse(dto.getCreated()).getTime()) / (60 * 60 * 1000) < 24)
							Dtime = (cendDate.getTime() - csdf.parse(dto.getCreated()).getTime()) / (60 * 60 * 1000) + "�ð� ��";
						
						dto.setCreated(Dtime);
						
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
				
				else
					dto.setCreated(arr[0]);
			}
			
			comment_list_list.add(comment_list);
						
			}
			
			
			
			String pageIndexList = util.pageIndexList(current_page, total_page);
			
			String num=req.getParameter("num");
			String translatedText = req.getParameter("translatedText");
			
			 if(translatedText!=null)
		         translatedText=URLDecoder.decode(translatedText,"UTF-8"); 
		            

		         if(translatedText!=null)
		         req.setAttribute("num", num);
		         
		         if(translatedText==null||translatedText=="")
		         req.setAttribute("num", "-1");

			
			
			req.setAttribute("num", num);
			req.setAttribute("translatedText", translatedText);
			req.setAttribute("comment_list_list", comment_list_list);	
			req.setAttribute("scrollHeight", scrollHeight);
			req.setAttribute("list", list);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("mode2", "bbs");
			forward(req,resp,"/WEB-INF/views/bbs/bbs.jsp");
		}else if (uri.indexOf("updateLike.do") != -1) {
			int num = Integer.parseInt(req.getParameter("num"));
			String scrollHeight =req.getParameter("scrollHeight");
			SessionInfo info=(SessionInfo)session.getAttribute("member");
			String translatedText = req.getParameter("translatedText");
			String searchValue=req.getParameter("searchValue");
			searchValue=URLEncoder.encode(searchValue, "utf-8"); //���ڵ�
			translatedText=URLEncoder.encode(translatedText,"UTF-8");
			dao.updateLike(num, info.getUserId());
			if(Integer.parseInt(basicnum)!=num)
	            num=-1;
			resp.sendRedirect(cp+"/bbs/list.do?scrollHeight="+scrollHeight+"&num="+num+"&translatedText="+translatedText+"&searchValue="+searchValue);
		}else if (uri.indexOf("updateDislike.do") != -1) {
			int num = Integer.parseInt(req.getParameter("num"));
			String scrollHeight =req.getParameter("scrollHeight");
			SessionInfo info=(SessionInfo)session.getAttribute("member");
			String translatedText = req.getParameter("translatedText");
			String searchValue=req.getParameter("searchValue");
			searchValue=URLEncoder.encode(searchValue, "utf-8"); //���ڵ�
			translatedText=URLEncoder.encode(translatedText,"UTF-8");
			dao.updateDislike(num, info.getUserId());
			if(Integer.parseInt(basicnum)!=num)
	            num=-1;
			resp.sendRedirect(cp+"/bbs/list.do?scrollHeight="+scrollHeight+"&num="+num+"&translatedText="+translatedText+"&searchValue="+searchValue);
		}else if(uri.indexOf("change.do")!=-1){
			
			Translate.setClientId("ktk815");
			Translate.setClientSecret("YYm6C6+kN1Weivq/pnu1S/DgjxsE181QtKx09WMszG4=");
		
			try {
				String scrollHeight = req.getParameter("scrollHeight");
				if(scrollHeight==null)
					scrollHeight="0";
				String searchValue=req.getParameter("searchValue");
				searchValue=URLEncoder.encode(searchValue, "utf-8"); //���ڵ�
				 
				if(searchValue==null) searchValue="";   //searchValue ���϶� 
				/*List<bbsDTO> list;
				
				if(searchValue.length()==0)  //�˻� �������  ��ü �ڵ� �Խ��� ������ �ҷ���
					list=dao.ListBbs();
				else                         //�ƴҰ�� �˻����ǿ� �´� �ڵ� �Խ��� ������ �ҷ��� 
					list=dao.ListSearchBbs(searchValue);*/  
			
				String country=req.getParameter("country");
				String num=req.getParameter("num");
				String content=req.getParameter("content");
				String translatedText;
				
				Language prev=Language.KOREAN;
				Language after=Language.ENGLISH;
				
				switch(country)
				{
				case "korea" : after=Language.KOREAN; break;
				case "japan" : after=Language.JAPANESE; break;
				case "china" : after=Language.CHINESE_SIMPLIFIED; break;
				case "france" : after=Language.FRENCH; break;
				case "germany" : after=Language.GERMAN; break;
				case "india" : after=Language.INDONESIAN; break;
				case "usa" : after=Language.ENGLISH; break;
				case "canada" : after=Language.ENGLISH; break;
				case "uk" : after=Language.ENGLISH; break;
				case "russia" : after=Language.RUSSIAN; break;
				default : break;
				}
				
				if(content.matches(".*[��-����-�Ӱ�-�R]+.*")) 
					prev=Language.KOREAN;
				
				if(content.matches(".*[a-zA-Z]+.*")){
					prev=Language.ENGLISH;
				}

				
				System.out.println("content: "+content);
				basicnum=num;
				translatedText = Translate.execute(content, prev , after);
				System.out.println("trans:"+translatedText);
				translatedText=URLEncoder.encode(translatedText,"UTF-8");
				
				req.setAttribute("translatedText", translatedText);
				req.setAttribute("num", num);
				
				req.setAttribute("scrollHeight", scrollHeight);
				req.setAttribute("serachValue", searchValue);
				req.setAttribute("mode2", "bbs");
				
				//forward(req,resp,"/WEB-INF/views/bbs/bbs.jsp");
				resp.sendRedirect(cp+"/bbs/list.do?num="+num+"&translatedText="+translatedText+"&mode2=code&searchValue="+searchValue+"&scrollHeight="+scrollHeight);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(uri.indexOf("comment.do")!=-1) {
			int num = Integer.parseInt(req.getParameter("num"));
			bbs_commentDTO dto = new bbs_commentDTO();
			String translatedText=req.getParameter("translatedText");
			translatedText=URLEncoder.encode(translatedText,"UTF-8");
			String scrollHeight=req.getParameter("scrollHeight");
			String searchValue=req.getParameter("searchValue");
			dto.setNum(num);
			dto.setContent(req.getParameter("commentcontent"));
			dto.setUserid(req.getParameter("userid"));
			searchValue=URLEncoder.encode(searchValue, "utf-8"); //���ڵ�
			 
			if(Integer.parseInt(basicnum)!=num)
		            num=-1;
			
			cdao.insertbbs_comment(dto);

			resp.sendRedirect(cp+"/bbs/list.do?num="+num+"&translatedText="+translatedText+"&scrollHeight="+scrollHeight+"&searchValue="+searchValue);
		} else if(uri.indexOf("commemt_delete.do")!=-1) {
			int num=Integer.parseInt(req.getParameter("num"));
			int comment_num=Integer.parseInt(req.getParameter("comment_num"));
			String translatedText=req.getParameter("translatedText");
			translatedText=URLEncoder.encode(translatedText,"UTF-8");
			String scrollHeight=req.getParameter("scrollHeight");
			String searchValue=req.getParameter("searchValue");
			searchValue=URLEncoder.encode(searchValue,"utf-8");
			if(Integer.parseInt(basicnum)!=num)
	            num=-1;
			
			cdao.deleteComment(comment_num);
			
			resp.sendRedirect(cp+"/bbs/list.do?num="+num+"&translatedText="+translatedText+"&scrollHeight="+scrollHeight+"&searchValue="+searchValue);
		}

	}
	
}
