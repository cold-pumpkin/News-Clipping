package com.coderby.ch.dao;

import java.util.List;

import com.coderby.ch.model.PressVO;
import com.coderby.ch.model.RepVO;
import com.coderby.ch.model.NewsVO;
import com.coderby.ch.model.OpinionVO;

public interface INewsDAO {
	   int insertNews(NewsVO news);
	   int insertRep(RepVO rep);
	   int updateNews(NewsVO news);
	   int deleteNews(int newsId);
	   NewsVO selectNewsByNewsid(int newsId);
	   List<NewsVO> selectSearchNews(String pressId, String count);
	   List<NewsVO> selectAllNews();
	   List<RepVO> selectAllReporter();
	   List<PressVO> selectAllPress();
}
