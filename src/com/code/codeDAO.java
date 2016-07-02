package com.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class codeDAO { 
	private Connection conn=DBConn.getConnection();
	
	public int insertCode(codeDTO dto){  //게시판 추가
		int result=0;
		PreparedStatement pstmt=null;
		 StringBuffer sb = new StringBuffer();
		 try{
			 
			 sb.append("INSERT INTO code (num, subject, userid, content, imagefilename)");
			 sb.append(" VALUES(code_seq.NEXTVAL, ?, ?, ?, ?)");
			 
			 pstmt=conn.prepareStatement(sb.toString());
			 
			 pstmt.setString(1, dto.getSubject());
			 pstmt.setString(2, dto.getUserid());
			 pstmt.setString(3, dto.getContent());
			 pstmt.setString(4, dto.getImagefilename());

	         result=pstmt.executeUpdate();	 
	         
		 } catch (Exception e) {
			 
	         System.out.println(e.toString());
	      }finally{
	         try {
	            if(pstmt!=null)
	               pstmt.close();
	         } catch (Exception e2) {
	        	
	            System.out.println(e2.toString());
	         }
	      }
		return result;
	}
	
	
	
/*	public List<codeDTO> ListCode(){   //전체 리스트 보여주기
		
		List<codeDTO> list =new ArrayList<>();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		ResultSet rs=null;
		
		try {
			sb.append(" SELECT num, subject, userid, created, content, imagefilename, liked, dislike, profile");
			sb.append("    FROM code c");
			sb.append("    JOIN member m ON c.userId=m.userId");
			sb.append("    WHERE num=? ORDER BY num DESC");
			pstmt=conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			
			while(rs.next())
			{
				codeDTO dto=new codeDTO();
				
					dto.setNum(rs.getInt("num"));
		            dto.setSubject(rs.getString("subject"));
		            dto.setUserid(rs.getString("userid"));
		            dto.setContent(rs.getString("content"));
		            dto.setCreated(rs.getString("created"));
		            dto.setImagefilename(rs.getString("imagefilename"));
		            dto.setDislike(rs.getInt("dislike"));
					dto.setLiked(rs.getInt("liked"));
		            dto.setProfile(rs.getString("profile"));

		            list.add(dto);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally{
			
			
				try {
					if(rs!=null)
					rs.close();
					if(pstmt!=null)
						pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}

		return list;
	}
	
	
	public List<codeDTO> ListSearchCode(String searchValue)  //검색리스트 보여주기
	{
		List<codeDTO> listName = new ArrayList<>();
		 PreparedStatement pstmt=null;
		 StringBuffer sb = new StringBuffer();
		 ResultSet rs = null;
		 
		 try {
			 sb.append(" SELECT num, subject, userid, created, content, liked, dislike ,imagefilename, profile FROM code c JOIN member m ON c.userId=m.userId WHERE ");
			 sb.append(" userid || subject || content LIKE '%' || ? || '%' ORDER BY num DESC ");
			 
			 pstmt=conn.prepareStatement(sb.toString());
			 pstmt.setString(1, searchValue);

			 rs=pstmt.executeQuery();
			 
			 while(rs.next()){
				 
				 codeDTO dto = new codeDTO();
				 dto.setNum(rs.getInt("num"));
				 dto.setSubject(rs.getString("subject"));
				 dto.setUserid(rs.getString("userid"));
				 dto.setCreated(rs.getString("created"));
				 dto.setContent(rs.getString("content"));
				 dto.setDislike(rs.getInt("dislike"));
				 dto.setLiked(rs.getInt("liked"));
				 dto.setImagefilename(rs.getString("imagefilename"));
				 listName.add(dto);
			 }
			 
			 
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	
		return listName;
	}*/
	
	public int updateLike(int num, String userid){   //LIKE 올리기
		int result=0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = null;
			sql = " INSERT INTO code_liked(num, userid) VALUES(?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, userid);
			
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
			sql = null;
			sql = " UPDATE code SET liked=liked+1 WHERE num = ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
			result = 1;
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if(pstmt!=null)
					pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return result;
	}

	public int updateDislike(int num, String userid){  //DisLike 올리기
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
	
		try {
			sql = null;
			sql = " INSERT INTO code_dislike(num, userid) VALUES(?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, userid);
			
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			sql = null;
			sql = " UPDATE code SET dislike = dislike + 1 WHERE num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
			result = 1;
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

	public codeDTO readCode(int num){   //해당 게시판 
		codeDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT num, subject, content, liked, dislike, created, imagefilename, userid ");
			sb.append("FROM code WHERE num = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
		
			if(rs.next()){
				dto = new codeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setLiked(rs.getInt("liked"));
				dto.setDislike(rs.getInt("dislike"));
				dto.setCreated(rs.getString("created"));
				dto.setImagefilename(rs.getString("imagefilename"));
				dto.setUserid(rs.getString("userid"));
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
		return dto;
	}

	public int dataCount() {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT NVL(count(*), 0) FROM code";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				result = rs.getInt(1);
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	public int dataCount(String searchValue) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM code WHERE userid || subject || content LIKE '%' || ? || '%' ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				result = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e2) {
			}
		}
		
		return result;
	}
	public List<codeDTO> listCode(int start, int end) {
		List<codeDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("SELECT * FROM (");
			sb.append(" SELECT ROWNUM rnum, tb.* FROM (");
			sb.append("  SELECT c.num, c.subject, c.content, c.liked, c.dislike, c.created, c.imagefilename, c.userid, m.profile");
			sb.append("  FROM code c ");
			sb.append("	 JOIN member m ON c.userId=m.userId");
			sb.append("  ORDER BY num DESC ");
			sb.append(" ) tb WHERE ROWNUM <= ? ");
			sb.append(") WHERE rnum >= ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				codeDTO dto = new codeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setLiked(rs.getInt("liked"));
				dto.setDislike(rs.getInt("dislike"));
				dto.setCreated(rs.getString("created"));
				dto.setImagefilename(rs.getString("imagefilename"));
				dto.setUserid(rs.getString("userid"));
	            dto.setProfile(rs.getString("profile"));
				list.add(dto);
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}
	
	public List<codeDTO> listCode(int start, int end, String searchValue) {
		List<codeDTO> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("SELECT * FROM (");
			sb.append(" SELECT ROWNUM rnum, tb.* FROM (");
			sb.append("  SELECT c.num, c.subject, c.content, c.liked, c.dislike, c.created, c.imagefilename, c.userid, m.profile");
			sb.append("  FROM code c JOIN member m ON c.userId=m.userId WHERE c.userid || c.subject || c.content LIKE '%' || ? || '%' ");	
			sb.append("  ORDER BY num DESC");
			sb.append(" ) tb WHERE ROWNUM <= ?");
			sb.append(") WHERE rnum >= ?");
			
			ps = conn.prepareStatement(sb.toString());
			ps.setString(1, searchValue);
			ps.setInt(2, end);
			ps.setInt(3, start);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				codeDTO dto = new codeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setLiked(rs.getInt("liked"));
				dto.setDislike(rs.getInt("dislike"));
				dto.setCreated(rs.getString("created"));
				dto.setImagefilename(rs.getString("imagefilename"));
				dto.setUserid(rs.getString("userid"));
	            dto.setProfile(rs.getString("profile"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
			}
		}
		
		return list;
	}
}
