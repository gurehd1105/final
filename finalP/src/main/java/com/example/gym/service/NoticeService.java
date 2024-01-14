package com.example.gym.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gym.mapper.NoticeMapper;
import com.example.gym.vo.Notice;
import com.example.gym.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class NoticeService {
	@Autowired NoticeMapper noticeMapper;
	
	// 공지사항 조회 (목록)
	public List<Notice> getNoticeList(Page page) {
		return noticeMapper.noticeList(page);
	}
	
	public int getNoticeTotal() {
		return noticeMapper.selectNoticeTotal();
	}
	
	// 공지사항 상세보기
	public Map<String, Object> getNoticeOne(int noticeNo) {
		// 매개변수 디버깅
		log.debug("getNoticeOne", "noticeNo", noticeNo);
		return noticeMapper.selectNoticeOne(noticeNo);
	}
	
	// 공지사항 등록
	public int insertNotice(Map<String, Object> map) {
		// 매개변수 디버깅
		log.debug("insertNotice", "map", map.toString());
		return noticeMapper.insertNotice(map);
	}
	
	// 공지사항 수정
	public int updateNotice(Notice notice) {
		// 매개변수 디버깅
		log.debug("updateNotice", "notice", notice.toString());
		return noticeMapper.updateNotice(notice);
	}
	
	// 공지사항 삭제
	public int deleteNotice(int noticeNo) {
		// 매개변수 디버깅
		log.debug("deleteNotice", "noticeNo", noticeNo);
		return noticeMapper.deleteNotice(noticeNo);
	}
}
