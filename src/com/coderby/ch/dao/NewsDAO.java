package com.coderby.ch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coderby.ch.model.NewsVO;
import com.coderby.ch.model.PressVO;
import com.coderby.ch.model.RepVO;

public class NewsDAO implements INewsDAO {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버클래스가 로드되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 로드할 수 없습니다.");
		}
	}
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
	public static final String ID = "ch";
	public static final String PW = "ch";

	
	
	@Override
	public int insertNews(NewsVO news) {
		String sql = "insert into news (news_id, press_name, topic, title, "
				+ "reporter_name, news_date, hits, comments, likes, link)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, news.getNewsId());
			stmt.setString(2, news.getPressName());
			stmt.setString(3, news.getTopic());
			stmt.setString(4, news.getTitle());
			stmt.setString(5, news.getReporterName());
			stmt.setDate(6, news.getNewsDate());
			stmt.setInt(7, news.getHits());
			stmt.setInt(8, news.getComments());
			stmt.setInt(9, news.getLikes());
			stmt.setString(10, news.getLink());

			int rowCount = stmt.executeUpdate(); //행의 수
			if(rowCount>=0) {
				return rowCount;
			}else {
				throw new RuntimeException("No row inserted");
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) {
				try {con.close();} catch(SQLException e) {}
			}
		}
		//return 0;
	}

	
	
	
	
	@Override
	public int insertRep(RepVO rep) {
		String sql = "insert into reporter (reporter_id, reporter_name, "
				+ "email, press_name)"
				+ "values (?, ?, ?, ?)";
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, rep.getReporterId());
			stmt.setString(2, rep.getReporterName());
			stmt.setString(3, rep.getEmail());
			stmt.setString(4, rep.getPressName());
			
			int rowCount = stmt.executeUpdate(); //행의 수
			if(rowCount>=0) {
				return rowCount;
			}else {
				throw new RuntimeException("No row inserted");
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) {
				try {con.close();} catch(SQLException e) {}
			}
		}
		//return 0;
	}

	
	
	
	
	
	@Override
	public int updateNews(NewsVO news) {
		
		Connection con = null;
		try {
			String sql = "update employees "
					+ "set press_id=?, topic=?, title=?, reporter_id=?, news_date=?,"
					+ "hits=?, comments=?, likes=?, link=?";
			con = DriverManager.getConnection(URL, ID, PW);

			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, news.getPressName());
			
			stmt.setString(2, news.getTopic());
			stmt.setString(3, news.getTitle());
			stmt.setString(4, news.getReporterName());
			stmt.setDate(5, news.getNewsDate());
			stmt.setInt(6, news.getHits());
			stmt.setInt(7, news.getComments());
			stmt.setInt(8, news.getLikes());
			stmt.setString(9, news.getLink());
			stmt.setInt(10, news.getNewsId());
			
			return stmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) {
				try {con.close();} catch(SQLException e) {}
			}
		}
		//return 0;
	}

	
	
	
	
	
	@Override
	public int deleteNews(int newsId) {
		Connection con = null;
		try {
			String sql = "delete news where news_id=?";
			con = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, newsId);
			return stmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) {
				try {con.close();} catch(SQLException e) {}
			}
		}
		//return 0;
	}

	
	
	
	
	
	@Override
	public NewsVO selectNewsByNewsid(int newsId) {
		Connection con = null;
		try {
		String sql = "select n.news_id, n.title, n.topic, p.press_name, "
				+ "r.reporter_name, n.news_date, n.hits, n.comments, n.likes, n.link" +
				"from news n " + 
				"join press p on n.press_id=p.press_id"+ 
				"join reporter r on n.reporter_id=r.reporter_id "+
				"where news_id=?";
		con = DriverManager.getConnection(URL, ID, PW);
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, newsId);
		ResultSet rs = stmt.executeQuery();
		NewsVO News = new NewsVO();
		News.setNewsId(rs.getInt("news_id"));
		News.setPressName(rs.getString("press_name"));
		News.setTopic(rs.getString("topic"));
		News.setTitle(rs.getString("title"));
		News.setReporterName(rs.getString("reporter_name"));
		News.setNewsDate(rs.getDate("news_date"));
		News.setHits(rs.getInt("hits"));
		News.setComments(rs.getInt("comments"));
		News.setLikes(rs.getInt("likes"));
		News.setLink(rs.getString("link"));
		return News;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) {
				try {con.close();} catch(SQLException e) {}
			}
		}
		//return null;
	}
	

	
	public List<NewsVO> selectSearchNews(String pressId, String count) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, ID, PW);
			String sql="select n.news_id, n.title, n.topic, p.press_name, "
					+ "r.reporter_name, n.news_date, n.hits, n.comments, n.likes, n.link" +
					"from news n " + 
					"join press p on n.press_id=p.press_id"+ 
					"join reporter r on n.reporter_id=r.reporter_id"+ 
					"where press_name=?"+ 
					"order by ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			List<PressVO> pressList = this.selectAllPress();
			String pressName = new String();
			for(PressVO pre : pressList) {
				if (pre.getPressId()==pressId) {
					pressName = pre.getPressName();
				}
			}
			stmt.setString(1, pressName);
			String countStr = count.toLowerCase();
			stmt.setString(2, countStr);
			
			List<NewsVO> newsList = new ArrayList<>(); 
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				NewsVO news = new NewsVO();
				news.setNewsId(rs.getInt("news_id"));
				news.setPressName(rs.getString("press_name"));
				news.setTopic(rs.getString("topic"));
				news.setTitle(rs.getString("title"));
				news.setReporterName(rs.getString("reporter_name"));
				news.setNewsDate(rs.getDate("news_date"));
				news.setHits(rs.getInt("hits"));
				news.setComments(rs.getInt("comments"));
				news.setLikes(rs.getInt("likes"));
				news.setLink(rs.getString("link"));
				newsList.add(news);
			}return newsList;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) {
				try {con.close();} catch(SQLException e) {}
			}
		}
	}

	@Override
	public List<NewsVO> selectAllNews() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, ID, PW);
			String sql="select n.news_id, n.title, n.topic, p.press_name, "
					+ "r.reporter_name, n.news_date, n.hits, n.comments, n.likes, n.link" +
					"from news n " + 
					"join press p on n.press_id=p.press_id"+ 
					"join reporter r on n.reporter_id=r.reporter_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			List<NewsVO> newsList = new ArrayList<>(); 
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				NewsVO news = new NewsVO();
				news.setNewsId(rs.getInt("news_id"));
				news.setPressName(rs.getString("press_name"));
				news.setTopic(rs.getString("topic"));
				news.setTitle(rs.getString("title"));
				news.setReporterName(rs.getString("reporter_name"));
				news.setNewsDate(rs.getDate("news_date"));
				news.setHits(rs.getInt("hits"));
				news.setComments(rs.getInt("comments"));
				news.setLikes(rs.getInt("likes"));
				news.setLink(rs.getString("link"));
				newsList.add(news);
			}return newsList;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) {
				try {con.close();} catch(SQLException e) {}
			}
		}
	}
	
	public List<RepVO> selectAllReporter() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, ID, PW);
			String sql="select r.reporter_name, r.reporter_id, p.press_name, r.email"
					+ "from reporter r"
					+ "join press p on r.press_id = p.press_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			List<RepVO> reporterList = new ArrayList<>(); 
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				RepVO reporter = new RepVO();
				reporter.setReporterId(rs.getInt("reporter_id"));
				reporter.setPressName(rs.getString("reporter_name"));
				reporter.setEmail(rs.getString("email"));
				reporter.setPressName(rs.getString("press_name"));
				reporterList.add(reporter);
			}return reporterList;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) {
				try {con.close();} catch(SQLException e) {}
			}
		}
	}
	
	@Override
	public List<PressVO> selectAllPress() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, ID, PW);
			String sql="select * from press";
			PreparedStatement stmt = con.prepareStatement(sql);
			List<PressVO> pressList = new ArrayList<>(); 
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				PressVO press = new PressVO();
				press.setPressId(rs.getString("press_id"));
				press.setPressName(rs.getString("press_name"));
				press.setPressDate(rs.getDate("press_date"));
				press.setAddress(rs.getString("address"));
				press.setEmployees(rs.getInt("employees"));
				pressList.add(press);
			}return pressList;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) {
				try {con.close();} catch(SQLException e) {}
			}
		}
	}

	
	

}
