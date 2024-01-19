package com.example.gym.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.gym.vo.Notice;
import com.example.gym.vo.Page;

@Mapper
public interface NoticeMapper {
	// 공지사항 목록 조회
	List<Notice> noticeList(Page page);
	// 페이징 위한 전체 노티스 갯수 조회
	int selectNoticeTotal();
	// 공지사항 상세보기 
	Map<String, Object> selectNoticeOne(int noticeNo);
	// 공지사항 입력
	int insertNotice(Map<String, Object> map);
	// 공지 수정
	int updateNotice(Notice notice);
	// 공지 삭제.
	int deleteNotice(int noticeNo);
}
