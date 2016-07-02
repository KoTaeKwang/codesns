package com.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class bbs_commentDAO {
	private Connection conn=DBConn.getConnection();
	
	public int insertbbs_comment(bbs_commentDTO dto){  //게시판 추가
		int result=0;
		PreparedStatement pstmt=null;
		 StringBuffer sb = new StringBuffer();
		 try{
			 
			 sb.append("INSERT INTO bbs_comment (comment_num, content, userid, created, num)");
			 sb.append(" VALUES(bbs_comment_seq.NEXTVAL, ?, ?, SYSDATE, ?)");
			 
			 pstmt=conn.prepareStatement(sb.toString());
			 
			 pstmt.setString(1, dto.getContent());
			 pstmt.setString(2, dto.getUserid());
			 pstmt.setInt(3, dto.getNum());


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
	
	
	public List<bbs_commentDTO> ListbbsComment(int num){   //전체 리스트 보여주기
		
		List<bbs_commentDTO> list =new ArrayList<>();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		ResultSet rs=null;
		
		try {
			sb.append("SELECT comment_num, num, content, c.userid, created, profile");
			sb.append("    FROM bbs_comment c");
			sb.append("    JOIN member m ON c.userId=m.userId");
			sb.append("    WHERE num=? ORDER BY comment_num DESC");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				bbs_commentDTO dto=new bbs_commentDTO();

					dto.setComment_num(rs.getInt("comment_num"));
		            dto.setUserid(rs.getString("userid"));
		            dto.setContent(rs.getString("content"));
		            dto.setCreated(rs.getString("created"));
		            dto.setNum(rs.getInt("num"));
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
	
	public bbs_commentDTO readComment(int num) {
		bbs_commentDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT num, userid, content, created");
			sb.append("   FROM bbs_comment");
			sb.append("   WHERE num = ? ");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, num);

			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto=new bbs_commentDTO();
				dto.setNum(rs.getInt("num"));
				dto.setUserid(rs.getString("userid"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
				
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return dto;
	}
	
	
	public int deleteComment(int num) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		sql="DELETE FROM bbs_comment WHERE comment_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}		
		return result;
	}	
}
