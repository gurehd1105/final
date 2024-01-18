<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="회원상세" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
    <table>
        <tr>
            <th>번호</th>
            <th style="display: none;">프로그램 번호</th>
            <th>프로그램</th>        
            <th>진행일</th> 
            <th>등록일</th>
            <th>수정일</th>
            <th>수정</th>
            
        </tr>
        
        <tbody v-for="(programDate, pd) in programDateList" :key="pd" id="programDateList">
            <tr>
                <th>{{pd+1}}</th>  
                <th style="display: none;">{{programDate.programDateNo}}</th>     
                <th>{{programDate.programName}}</th>
                <th>{{new Date(programDate.programDate).toLocaleDateString()}}</th> 
                <th>{{new Date(programDate.createdate).toLocaleString()}}</th> 
                <th>{{new Date(programDate.updatedate).toLocaleString()}}</th>
                
                <th><el-button type="primary" @click="update(programDate)">수정</el-button></th>
            </tr>            
        </tbody>   
        <el-button type="primary" @click="insertDate()">일정 추가</el-button>         
    </table>
</c:set>

<c:set var="script">
    data() {
        return {        
            programDateList: JSON.parse('${programDateList}'),  
        }
    },
    
    methods: {
    	update(programDate) {
           location.href = '${ctp}/updateProgramDate?programDateNo=' + programDate.programDateNo;
       },
        insertDate(){
        	location.href = '${ctp}/insertProgramDate';
       }
    },
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
