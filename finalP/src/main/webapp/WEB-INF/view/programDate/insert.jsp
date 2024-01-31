<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="프로그램 일정추가" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<c:set var="body">

        <el-text size="large" tag="b">프로그램 진행일자 추가</el-text>        
        <el-form-item label="프로그램 진행일">
            <el-date-picker v-model="programDate" type="date" placeholder="날짜 선택"></el-date-picker>
        </el-form-item>

        <el-form-item label="프로그램 선택">
            <el-select v-model="selectProgram" id="selectProgram" clearable placeholder="프로그램 선택">
                <el-option v-for="(program, p) in programList.programList" 
                    :key="p" 
                    :label="program.programName" 
                    :value="program.programNo"
                    :selected="p === 0">
                </el-option> 
            </el-select>
        </el-form-item>
        
        <el-form-item>
            <el-button type="primary" @click="insertProgramDate">추가하기</el-button>
        </el-form-item>

</c:set>
<c:set var="script">
    data() {
        return {
            programDate: '', // 선택된 프로그램 날짜
            programList: JSON.parse('${programList}'),  // 입력된 프로그램명
            selectProgram: null,
        }
    },
    
    methods: {
        // 예약 정보를 서버로 전송하는 코드
        insertProgramDate() {
            // 날짜와 프로그램이 선택되지 않았을 때 경고 메시지 표시
            if (!this.programDate || !this.selectProgram) {
                this.$notify({
                    title: '입력 필요',
                    message: '프로그램 날짜와 프로그램을 선택하세요.',
                    type: 'warning',
                });
                return;
            }

            const programDateData = {
                programNo: this.selectProgram,
                programDate: this.programDate
            };

            // 실제로는 Ajax 또는 fetch를 사용하여 서버로 데이터를 전송합니다.
            axios.post('${ctp}/programDate/insert', programDateData)
                .then(response => {
                    this.$notify({
                        title: '추가 성공',
                        message: '프로그램 진행일자가 성공적으로 추가되었습니다.',
                        type: 'success',
                    });
                })
                .catch(error => {
                    this.$notify({
                        title: '추가 실패',
                        message: '서버 오류로 인해 추가가 실패하였습니다. 잠시 후 다시 시도해 주세요.',
                        type: 'error',
                    });
                });
        },
    }
</c:set>
<%@ include file="/inc/admin_layout.jsp"%>
