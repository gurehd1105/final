package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Notice;
import com.example.gym.vo.Page;

@Mapper
public interface NoticeMapper {
	
	List<Notice> selectNotice(Page page);
	
	int selectNoticeTotal();
	
	Map<String, Object> selectNoticeOne(int noticeNo);
	
	int insertNotice(Map<String, Object> map);
	
	int updateNotice(Notice notice);
	
	int deleteNotice(int noticeNo);
}
