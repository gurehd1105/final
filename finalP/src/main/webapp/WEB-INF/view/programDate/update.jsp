<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="프로그램 일정수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">

    <el-form label-position="right" 
             ref="form" 
             label-width="150px" 
             status-icon 
             class="max-w-lg"
             action="${ctp}/programDate/update"
             method="post" 
             id="updateForm">
             
       
        <el-form-item label="프로그램">
            <el-input type="text" v-model="programDateList.programName" name="programName" readonly="readonly" />
        </el-form-item>

        <el-form-item label="진행일">
            <el-date-picker v-model="programDateList.programDate" name="programDate">
            </el-date-picker>
        </el-form-item>

        <el-form-item label="등록일">
            <el-input type="text" v-model="formattedCreateDate" name="createdate" readonly="readonly"></el-input>
        </el-form-item>

        <el-form-item label="수정일">
            <el-input type="text" v-model="formattedUpdateDate" name="updatedate" readonly="readonly"></el-input>
        </el-form-item>
        
        <el-form-item>
        	<input type="hidden" name="programDateNo" :value="programDateList.programDateNo">
        </el-form-item>
        
        <el-form-item>
            <el-button type="primary" @click="updateSubmit">수정</el-button>
        </el-form-item> 
    </el-form>
</c:set>

<c:set var="script">
   data() {
        return {
            programDateList: JSON.parse('${resultMap}'),  
        }
    },
   computed: {
        formattedCreateDate() {
            return this.formatDate(this.programDateList.createdate);
        },
        formattedUpdateDate() {
            return this.formatDate(this.programDateList.updatedate);
        },
    },
   methods: {
        formatDate(timestamp) {
            const date = new Date(timestamp);
            return date.toLocaleString(); // 원하는 형식으로 조정하세요
        },
        updateSubmit() {
        	document.getElementById('updateForm').submit();
        	}
        
    }
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
