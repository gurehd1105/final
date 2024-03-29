package com.example.gym.service;

import com.example.gym.mapper.QuestionMapper;
import com.example.gym.vo.Page;
import com.example.gym.vo.Question;
import com.example.gym.vo.QuestionReply;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    // Question
    // question 등록 (insert)
    public int insertQuestion(Question paramQuestion) {
        int result = 0;
        result = questionMapper.insertQuestion(paramQuestion);
        return result;
    }

    // questionList 조회
    public List<Map<String, Object>> selectQuestionList(Page page) {
        List<Map<String, Object>> list = questionMapper.selectQuestionList(
            page
        );
        return list;
    }

    // tatalCount 조회
    public int totalCount() {
        int result = questionMapper.selectCountOfQuestion();
        return result;
    }

    // questionOne 조회
    public Map<String, Object> selectQuestionOne(Question paramQuestion) {
        Map<String, Object> questionMap = questionMapper.selectQuestionOne(
            paramQuestion
        );
        Map<String, Object> questionReplyMap = questionMapper.selectQuestionReply(
            paramQuestion
        );

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("questionMap", questionMap);
        resultMap.put("questionReplyMap", questionReplyMap);
        return resultMap;
    }

    // question 수정 (update)
    public int updateQuestion(Question paramQuestion) { // question_reply 없을 시에만 수정가능 -> 추후 코드변경 예정
        int result = 0;

        result = questionMapper.updateQuestion(paramQuestion);
        return result;
    }

    // question 삭제 (delete)
    public int deleteQuestion(Question paramQuestion) {
        int result = 0;
        result = questionMapper.deleteQuestion(paramQuestion);
        return result;
    }

    public int countNotYet() {
        return questionMapper.countNotYet();
    }

    // Question	Reply

    // reply 등록 (insert)
    public int insertQuestionReply(QuestionReply paramReply) {
        int result = 0;
        result = questionMapper.insertQuestionReply(paramReply);
        return result;
    }

    // reply 수정 (update)
    public int updateQuestionReply(QuestionReply paramReply) {
        int result = 0;
        result = questionMapper.updateQuestionReply(paramReply);
        return result;
    }

    // reply 삭제 (delete)
    public int deleteQuestionReply(QuestionReply paramReply) {
        int result = 0;
        result = questionMapper.deleteQuestionReply(paramReply);
        return result;
    }
}
