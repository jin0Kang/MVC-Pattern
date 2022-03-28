package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.BDto;

public class BDao {

	//datasource javax.sql 꺼로 불러와야 함. 아까 설정 여기로 했으니까. 아무거나 하지말고. 
	DataSource dataSource; 
	
	public BDao() {
		// TODO Auto-generated constructor stub
		
		try {
			//servers - context에서 정보 가져와서 db 연결 해라. 오류 나면 알려주고. 
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mvc");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//전체 검색 메소드 
	// 검색해서 받아올 통(포맷)이 필요하니까 dto 도 만들어야. 
	
	public ArrayList<BDto> list(){
		//dto 틀에 맞춰서 가져올 것임. 제너릭을 BDto로 했으니까. 
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null; 
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null; 
		
		try {
			connection = dataSource.getConnection();
			String query = "select bId, bName, bTitle, bContent, bDate from mvc_board";
			preparedStatement = connection.prepareStatement(query); 
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName"); 
				String bTitle = resultSet.getString("bTitle"); 
				String bContent = resultSet.getString("bContent"); 
				Timestamp bDate = resultSet.getTimestamp("bDate");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate);
				dtos.add(dto);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		//메모리 정리 해주는 게 좋기 때문에 finally 필요 
		}finally {
			try {
				//보통 연 순서의 역으로 닫음 
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
		
		
	}//list
	
	
	public void write(String bName, String bTitle, String bContent) {
	
			Connection connection = null; 
			PreparedStatement preparedStatement = null; 
			
			
			try {
				connection = dataSource.getConnection();
				String query = "insert into mvc_board (bName, bTitle, bContent, bDate) values (?,?,?,now())";
				preparedStatement = connection.prepareStatement(query); 
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				
				preparedStatement.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			//메모리 정리 해주는 게 좋기 때문에 finally 필요 
			}finally {
				try {
					//보통 연 순서의 역으로 닫음 
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		
	}//write 
	
	
	public BDto contentView(String sbId) {
		//밑에 받아오는 bId랑 한 공간 내에서 이름 겹쳐서 바꾼 거임 그냥 bId라고 생각하면 됨 
		BDto dto = null;
		Connection connection = null; 
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null; 
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			preparedStatement = connection.prepareStatement(query); 
			preparedStatement.setInt(1, Integer.parseInt(sbId));
			resultSet = preparedStatement.executeQuery();
			
			//어차피 record 한 줄 받아오는 거니까 while 안해도 됨. 해도 되고. 
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName"); 
				String bTitle = resultSet.getString("bTitle"); 
				String bContent = resultSet.getString("bContent"); 
				Timestamp bDate = resultSet.getTimestamp("bDate");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		//메모리 정리 해주는 게 좋기 때문에 finally 필요 
		}finally {
			try {
				//보통 연 순서의 역으로 닫음 
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;

	}//contentView 
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		
		Connection connection = null; 
		PreparedStatement preparedStatement = null; 
	
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
			preparedStatement = connection.prepareStatement(query); 
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setString(4, bId);
			
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		//메모리 정리 해주는 게 좋기 때문에 finally 필요 
		}finally {
			try {
				//보통 연 순서의 역으로 닫음 
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	 }//modify
	
		
		public void delete(String bId) {
			
			Connection connection = null; 
			PreparedStatement preparedStatement = null; 
		
			
			try {
				connection = dataSource.getConnection();
				String query = "delete from mvc_board where bId = ?";
				preparedStatement = connection.prepareStatement(query); 
				preparedStatement.setString(1, bId);
				
				preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			//메모리 정리 해주는 게 좋기 때문에 finally 필요 
			}finally {
				try {
					//보통 연 순서의 역으로 닫음 
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}//delete
	
}//
