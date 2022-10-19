package com.mycom.mydb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycom.mydb.dto.UserDto;


@Repository
public class DBDaoImpl implements DBDao{
	@Autowired
	DataSource dataSource;

	@Override
	public List<UserDto> GetList() {
		List<UserDto>list=new ArrayList<UserDto>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=dataSource.getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append(" select * from user ");
			pstmt=con.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				UserDto dto=new UserDto();
				dto.setUserId(rs.getString("userId"));
				dto.setUserName(rs.getString("userName"));
				dto.setEmail(rs.getString("email"));
				dto.setUserAge(rs.getInt("userAge"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}		
		}
		
		return list;
	}

	@Override
	public int Register(UserDto userDto) {
		int ret=-1;
		UserDto dto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
			con=dataSource.getConnection();
			
			StringBuilder sb=new StringBuilder();
			sb.append(" insert into user (userId, userName, email, userAge) ")
			.append("  values (?,?,?,?,?) ");
			
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1,userDto.getUserId());
			pstmt.setString(2, userDto.getUserName());
			pstmt.setString(3, userDto.getEmail());
			pstmt.setInt(4, userDto.getUserAge());
			
			
			ret=pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public UserDto UserInfo(String userId) {
		UserDto dto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
			con=dataSource.getConnection();
			
			StringBuilder sb=new StringBuilder();
			sb.append(" select userId, userName, email, userAge ")
			.append("  from emp where employeeId=? ");
			
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, userId);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto=new UserDto();
				dto.setUserId(userId);
				dto.setUserName(rs.getString("userName"));
				dto.setEmail(rs.getString("email"));
				dto.setUserAge(rs.getInt("userAge"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return dto;
		}
	
}
