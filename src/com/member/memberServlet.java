package com.member;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.bbsDTO;
import com.code.codeDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.FileManager;



@WebServlet("/member/*")

public class memberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	memberDAO dao=new memberDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}
	
	@SuppressWarnings("null")
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String cp = req.getContextPath();
		req.setCharacterEncoding("utf-8");

		memberDAO dao = new memberDAO();
		
		HttpSession session = req.getSession();
		
		//이미지 파일저장경로
		String root = session.getServletContext().getRealPath("/");
		String pathname = root+File.separator+"uploads"+File.separator+"profile";
				
		File f = new File(pathname);
				
		if(!f.exists())
			f.mkdirs();
		
		
		
		if(uri.indexOf("login.do")!=-1) {
			
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
		} else if(uri.indexOf("login_ok.do")!=-1) {
			String userId = req.getParameter("userId");
			String userPwd = req.getParameter("userPwd");
			String pageNum = req.getParameter("pageNum");
			String scrollHeight = req.getParameter("scrollHeight");
			
			memberDTO dto=dao.readMember(userId);
			if(dto==null || ! dto.getUserpwd().equals(userPwd)) {
				String msg="아이디 또는 패스워드가 일치하지 않습니다.";
				req.setAttribute("message", msg);
				
				forward(req, resp, "/WEB-INF/views/member/login.jsp");
				return;
			}
			
			// 세션에 member 이름으로 로그인 정보 저장하기
			SessionInfo info=new SessionInfo();
			info.setUserId(dto.getUserid());
			info.setCountry(dto.getCountry());
			info.setProfile(dto.getProfile());
			session.setMaxInactiveInterval(20*60); // 세션유지시간 20분
			session.setAttribute("member", info);
			
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("scrollHeight", scrollHeight);
			req.setAttribute("mode", "code");
			//forward(req, resp, "/WEB-INF/views/code/code.jsp");
			forward(req, resp, "/code/list.do");
			
		}else if(uri.indexOf("member.do")!=-1)
		{
			req.setAttribute("mode", "member");
			forward(req, resp, "/WEB-INF/views/member/member.jsp");
		}else if(uri.indexOf("member_ok.do")!=-1){
			
			String encType="utf-8";
			int maxSize=10*1024*1024;
			
			MultipartRequest mreq=new MultipartRequest(req, pathname,maxSize,encType,new DefaultFileRenamePolicy());
	
			memberDTO dto=new memberDTO();

			String userid=mreq.getParameter("userId");
			String userpwd=mreq.getParameter("userPwd");
			String email=mreq.getParameter("email");
			String country=mreq.getParameter("country");
			String profile=mreq.getFilesystemName("profile");
			
			dto.setCountry(country);
			dto.setUserid(userid);
			dto.setUserpwd(userpwd);
			dto.setEmail(email);
			dto.setProfile(profile);
			
			dao.insertMember(dto);
		
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
		} else if (uri.indexOf("mypage.do") != -1) {
			String back = req.getParameter("back");
			SessionInfo info = (SessionInfo)session.getAttribute("member");
			String userid = req.getParameter("userid");
			try {
				List<memberDTO> list1 = dao.loadFriendsProfile(userid);	// 친구 목록
				List<codeDTO> list2 = dao.readLikeCode(info.getUserId());	// 내가 좋아한 코드 게시판 글
				List<bbsDTO> list5 = dao.readLikeBbs(info.getUserId());		// 내가 좋아한 자유 게시판 글
				List<codeDTO> list3 = dao.readMyCode(info.getUserId());		// 내가 쓴 코드 게시판 글 목록 
				List<bbsDTO> list4 = dao.readMyBbs(info.getUserId());		// 내가 쓴 자유 게시판 글 목록
				memberDTO dto = dao.readMember(info.getUserId());
				String beforepage = req.getParameter("beforepage");
				
				req.setAttribute("scrollHeight", 0);
				req.setAttribute("dto", dto);
				req.setAttribute("msg", "회원님의 ");
				req.setAttribute("userid", userid);
				req.setAttribute("list1", list1);
				req.setAttribute("list2", list2);
				req.setAttribute("list3", list3);
				req.setAttribute("list4", list4);
				req.setAttribute("list5", list5);
				req.setAttribute("userid", userid);
				req.setAttribute("back", back);
				req.setAttribute("beforepage", beforepage);
			} catch (Exception e) {
				// TODO: handle exception
			}
			forward(req, resp, "/WEB-INF/views/member/mypage.jsp");
		}else if (uri.indexOf("yourpage.do") != -1) {
			
			try {			
			SessionInfo info = (SessionInfo)session.getAttribute("member");
			String scrollHeight = req.getParameter("scrollHeight");
			String userid = req.getParameter("userid");
			memberDTO dto2 = dao.readMember(userid);
			List<memberDTO> list1 = dao.loadFriendsProfile(userid);	// 친구의 친구 목록
			/*List<memberDTO> listFriend=null;
				for(memberDTO dto : list1){
					listFriend.add(dao.readMember(dto.getUserid()));
				}*/
			for(memberDTO d : list1){
				System.out.println(d.getUserid());
			}
			List<codeDTO> list3 = dao.readMyCode(userid);		// 친구가 쓴 코드 게시판 글 목록
			List<bbsDTO> list4 = dao.readMyBbs(userid);			// 친구가 쓴 자유 게시판 글 목록
			List<memberDTO> list6 = dao.readFriends(info.getUserId());	// 나의 친구 목록
			String beforepage = req.getParameter("beforepage");
			
			for (memberDTO dto : list6) {
				if (dto.getUserid().equals(userid)){
					req.setAttribute("msg2", 1000);
					break;
				}
				req.setAttribute("msg2", 0);				
			}
			
			req.setAttribute("scrollHeight", scrollHeight);
			req.setAttribute("msg", userid + "님의 ");
			req.setAttribute("userid", userid);
			req.setAttribute("list1", list1);
			req.setAttribute("list6", list6);
			req.setAttribute("list3", list3);
			req.setAttribute("list4", list4);
			req.setAttribute("dto", dto2);
			req.setAttribute("beforepage", beforepage);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			forward(req, resp, "/WEB-INF/views/member/mypage.jsp");
		} else if (uri.indexOf("addFriend.do") != -1) {
			SessionInfo info = (SessionInfo)session.getAttribute("member");
			String friendid = req.getParameter("friendid");
			
			dao.insertFriend(friendid, info.getUserId());
			
			resp.sendRedirect(cp + "/code/list.do?pageNum=1&scrollHeight=0");
		} else if (uri.indexOf("logout.do") != -1) {
			session.removeAttribute("member");
			session.invalidate();
			
			resp.sendRedirect(cp + "/start.do");
		} else if (uri.indexOf("updateProfile.do") != -1) {
			forward(req, resp, "/WEB-INF/views/member/updateProfile.jsp");
		} else if (uri.indexOf("updateProfile_ok.do") != -1) {
			String encType="utf-8";
			int maxSize=10*1024*1024;
			
			MultipartRequest mreq=new MultipartRequest(req, pathname,maxSize,encType,new DefaultFileRenamePolicy());
			
			SessionInfo info = (SessionInfo)session.getAttribute("member");
			String profile = mreq.getFilesystemName("imgInp");
			String userid = mreq.getParameter("userid");
			String beforepage = req.getParameter("beforepage");
			
			if(profile!=null)
			{
			profile = FileManager.doFilerename(pathname, profile);
			
			memberDTO dto=new memberDTO();
			dto.setProfile(profile);
			dao.updateProfile(profile, info.getUserId());
			info.setProfile(dto.getProfile());
			}
			
			//resp.sendRedirect(cp + "/member/mypage.do?userid="+userid);
			resp.sendRedirect(cp + "/member/mypage.do?userid="+userid+"&beforepage="+beforepage);
		}
	}
	
	
}
