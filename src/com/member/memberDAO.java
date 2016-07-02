package com.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbs.bbsDTO;
import com.code.codeDTO;
import com.util.DBConn;

public class memberDAO {

	public Connection conn=DBConn.getConnection();
	
	public int insertMember(memberDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("INSERT INTO member(userid,userpwd,email,country,profile) VALUES(?,?,?,?,?) ");
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getUserpwd());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getCountry());
			pstmt.setString(5, dto.getProfile());
			
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally{
			
				try {
					if(pstmt!=null)
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
		
		return result;
	}
	
	public memberDTO readMember(String userId) {
		memberDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT userId, userPwd, email, country, profile");
			sb.append("  FROM member ");
			sb.append("  WHERE userId = ? ");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto=new memberDTO();
				dto.setUserid(rs.getString("userId"));
				dto.setUserpwd(rs.getString("userPwd"));
				dto.setEmail(rs.getString("email"));
				dto.setCountry(rs.getString("country"));
				dto.setProfile(rs.getString("profile"));

/*				if(dto.getEmail()!=null) {
					String[] ss=dto.getEmail().split("@");
					if(ss.length==2){
						dto.setEmail1(ss[0]);
						dto.setEmail2(ss[1]);
					}
				}
				if(dto.getTel()!=null) {
					String[] ss=dto.getTel().split("-");
					if(ss.length==3) {
						dto.setTel1(ss[0]);
						dto.setTel2(ss[1]);
						dto.setTel3(ss[2]);
					}
				}*/
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}
	
	public List<memberDTO> readFriends(String userid) {
		List<memberDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" SELECT friendid FROM member m ");
			sb.append(" JOIN friend f ");
			sb.append(" ON m.userid = f.userid ");
			sb.append(" WHERE m.userid = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberDTO dto = new memberDTO();
				dto.setUserid(rs.getString("friendid"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return list;
	}
	
	public List<codeDTO> readLikeCode(String userid) {
		List<codeDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" SELECT c.num, c.subject, c.content, c.created, c.imagefilename, cl.userid  ");
			sb.append(" FROM code c ");
			sb.append(" JOIN code_liked cl ");
			sb.append(" ON c.num = cl.num ");
			sb.append(" WHERE cl.userid = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				codeDTO dto = new codeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setImagefilename(rs.getString("imagefilename"));
				dto.setUserid(rs.getString("userid"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return list;
	}
	
	public List<bbsDTO> readLikeBbs(String userid) {
		List<bbsDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" SELECT c.num, c.subject, c.content, c.created, c.imagefilename, cl.userid  ");
			sb.append(" FROM bbs c ");
			sb.append(" JOIN bbs_liked cl ");
			sb.append(" ON c.num = cl.num ");
			sb.append(" WHERE cl.userid = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bbsDTO dto = new bbsDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setImagefilename(rs.getString("imagefilename"));
				dto.setUserid(rs.getString("userid"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return list;
	}
	
	public List<codeDTO> readMyCode(String userid) {
		List<codeDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" SELECT num, subject, content, created, imagefilename ");
			sb.append(" FROM code ");
			sb.append(" WHERE userid = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				codeDTO dto = new codeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setImagefilename(rs.getString("imagefilename"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return list;
	}
	
	public List<bbsDTO> readMyBbs(String userid) {
		List<bbsDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" SELECT num, subject, content, created, imagefilename ");
			sb.append(" FROM bbs ");
			sb.append(" WHERE userid = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bbsDTO dto = new bbsDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setImagefilename(rs.getString("imagefilename"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return list;
	}
	
	public int insertFriend(String friendid, String userid) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO friend(friendid, userid) VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, friendid);
			pstmt.setString(2, userid);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return result;
	}
	
	public int updateProfile(String profile, String userid) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "UPDATE member SET profile = ? WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profile);
			pstmt.setString(2, userid);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return result;
	}
	
	public memberDTO loadProfile(String userid) {
		memberDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT profile FROM member WHERE userid = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new memberDTO();
				dto.setProfile(rs.getString("profile"));
			}
		} catch (Exception e) {
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return dto;
	}
	
	public List<memberDTO> loadFriendsProfile(String userid) {
		List<memberDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "select userid, profile from member where userid in (select friendid from friend where userid=?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberDTO dto = new memberDTO();
				dto.setProfile(rs.getString("profile"));
				dto.setUserid(rs.getString("userid"));
				
				list.add(dto);
			}
		} catch (Exception e) {
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return list;
	}
	
}
