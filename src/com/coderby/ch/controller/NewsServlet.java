package com.coderby.ch.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coderby.ch.dao.INewsDAO;
import com.coderby.ch.dao.NewsDAO;
import com.coderby.ch.model.NewsVO;
import com.coderby.ch.model.PressVO;
import com.coderby.ch.model.RepVO;


@WebServlet("/News.do")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewsServlet() {
		super();

	}

	INewsDAO dao = new NewsDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		String url = "/";
		PrintWriter out = response.getWriter();
		if("list".equals(cmd)) {
			List<NewsVO> newsList = new ArrayList<>(); 
			if(request.getParameter("pressid")=="All") {
				newsList= dao.selectAllNews();
			}else {
				newsList = dao.selectSearchNews(request.getParameter("pressid"), request.getParameter("count"));
			}
			request.setAttribute("newsList", newsList);
			url = "/ch/list.jsp";

		}else if("details".equals(cmd)) {
			String newsIdStr= request.getParameter("newsid");
			int newsId = Integer.parseInt(newsIdStr);
			NewsVO news = dao.selectNewsByNewsid(newsId);
			request.setAttribute("news", news);
			url = "/ch/details.jsp";

		}else if("search".equals(cmd)) {
			List<PressVO> pressList = dao.selectAllPress();
			request.setAttribute("pressList", pressList);
			url = "/ch/search.jsp";
			
		}else if("insert".equals(cmd)) {
			List<PressVO> pressList = dao.selectAllPress();
			List<RepVO> reporterList = dao.selectAllReporter();
			request.setAttribute("reporterList", reporterList);
			request.setAttribute("pressList", pressList);
			url = "/ch/insert.jsp";
			
		}else if("repinsert".equals(cmd)) {
			List<PressVO> pressList = dao.selectAllPress();
			request.setAttribute("pressList", pressList);
			url = "/ch/repinsert.jsp";
			
		}else if("update".equals(cmd)) {
			request.setAttribute("pressList", dao.selectAllPress());
			int newsId = Integer.parseInt(request.getParameter("newsid"));
			request.setAttribute("news", dao.selectNewsByNewsid(newsId));
			url = "/ch/update.jsp";
		}else if("delete".equals(cmd)) {
			int newsId = Integer.parseInt(request.getParameter("newsid"));
			NewsVO news = dao.selectNewsByNewsid(newsId);
			request.setAttribute("news", news);
			url = "/ch/delete.jsp";
		}

		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request,  response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "/";
		String cmd = request.getParameter("cmd");

		if("insert".equals(cmd)) {
			NewsVO news = new NewsVO();
			List<RepVO> reporterList = new ArrayList<>();
			reporterList = dao.selectAllReporter();
			try {
				news.setNewsId(Integer.parseInt(request.getParameter("newsId")));
				news.setPressName(request.getParameter("pressName"));
				news.setTopic(request.getParameter("topic"));
				news.setTitle(request.getParameter("title"));
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				String newsDateStr = request.getParameter("newsDate");
				java.sql.Date newsDate = new java.sql.Date(new java.util.Date().getTime());
				newsDate = new java.sql.Date(transFormat.parse(newsDateStr).getTime());
				news.setNewsDate(newsDate);
				news.setHits(Integer.parseInt(request.getParameter("hits")));
				news.setComments(Integer.parseInt(request.getParameter("comments")));
				news.setLikes(Integer.parseInt(request.getParameter("likes")));
				news.setLink(request.getParameter("link"));
				for(RepVO rep : reporterList) {
					if(request.getParameter("reporterName")!=rep.getReporterName()) {
						url = "/ch/News.do?cmd=repinsert";
					}else {
					news.setReporterName(request.getParameter("reporterName"));
					dao.insertNews(news); 
					url = "/ch/News.do?cmd=list";
					}
				}
			}catch(RuntimeException | ParseException e) {
				request.setAttribute("message", e.getMessage());
				url = "/error.jsp";
				RequestDispatcher disp = request.getRequestDispatcher(url);
				disp.forward(request,  response);
				return;
			}
		}else if("repinsert".equals(cmd)) {
			RepVO rep = new RepVO();
			try {
				rep.setReporterId(Integer.parseInt(request.getParameter("newsId")));
				rep.setPressName(request.getParameter("pressName"));
				rep.setReporterName(request.getParameter("topic"));
				rep.setEmail(request.getParameter("title"));
				dao.insertRep(rep); 
				url = "/ch/News.do?cmd=list";
				
			}catch(RuntimeException e) {
				request.setAttribute("message", e.getMessage());
				url = "/error.jsp";
				RequestDispatcher disp = request.getRequestDispatcher(url);
				disp.forward(request,  response);
				return;
			}
		}else if("search".equals(cmd)) {
			url = "/ch/News.do?cmd=list&pressid="+
					request.getParameter("pressId")+
					"&count="+request.getParameter("count");
			
		}else if("update".equals(cmd)){
			NewsVO news = new NewsVO();
			try {
				news.setNewsId(Integer.parseInt(request.getParameter("newsId")));
				news.setPressName(request.getParameter("pressName"));
				news.setTopic(request.getParameter("topic"));
				news.setTitle(request.getParameter("title"));
				news.setReporterName(request.getParameter("reporterName"));
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				String newsDateStr = request.getParameter("newsDate");
				java.sql.Date newsDate = new java.sql.Date(new java.util.Date().getTime());
				newsDate = new java.sql.Date(transFormat.parse(newsDateStr).getTime());
				news.setNewsDate(newsDate);
				news.setHits(Integer.parseInt(request.getParameter("hits")));
				news.setComments(Integer.parseInt(request.getParameter("comments")));
				news.setLikes(Integer.parseInt(request.getParameter("likes")));
				news.setLink(request.getParameter("link"));
				dao.updateNews(news);
				url="/ch/News.do?cmd=details&newsid="+news.getNewsId();
			}catch(RuntimeException | ParseException e) {
				request.setAttribute("message", e.getMessage());
				url = "/error.jsp";
				RequestDispatcher disp = request.getRequestDispatcher(url);
				disp.forward(request,  response);
				return;
			}
		}else if("delete".equals(cmd)) {
			int newsId=Integer.parseInt(request.getParameter("newsid"));
			try {
				dao.deleteNews(newsId);
				url="/ch/News.do?cmd=list";
			}catch(RuntimeException e) {
				request.setAttribute("message", e.getMessage());
				url = "/error.jsp";
				RequestDispatcher disp = request.getRequestDispatcher(url);
				disp.forward(request,  response);
				return;
			}
		}else {
			request.setAttribute("message",  "요청한 명령이 없습니다.");
			url = "/error.jsp";
			RequestDispatcher disp = request.getRequestDispatcher(url);
			disp.forward(request, response);
			return;
		}

		response.setContentType("text/html;charset=utf-8");
		response.sendRedirect(url);


	}
}
