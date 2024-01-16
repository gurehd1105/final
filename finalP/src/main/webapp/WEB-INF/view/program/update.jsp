<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="프로그램 리스트" />
<c:set var="description" value="프로그램 목록을 보여주고 검색 할 수 있는 사이느" />
<c:set var="keywords" value="프로그램" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<!-- 프로그램 수정 폼 -->
	<el-form label-position="right" 
			 ref="form" 
			 label-width="150px" 
			 status-icon 
			 class="max-w-lg"
			 action="${ctp}/program/update"  
			 method="post" 
			 id="updateProgramForm"
	>
		<p>상태와 최대인원만 수정이 가능합니다.</p>
		<br>
			 
		<el-form-item label="이름">
			<el-input type="text" v-model="model.programName" name="ProgramName" readonly="readonly"/>			
		</el-form-item> 

		<el-form-item label="최대인원">
			<el-input type="number" v-model="model.maxCustomer" name="maxCustomer" :min="0" />			
		</el-form-item> 
		
		<el-form-item label="상태">
			<el-radio-group v-model="model.programActive" name="programActive" class="ml-4" >
				<el-radio label="Y">활성화</el-radio>
				<el-radio label="N">비활성화</el-radio>
			</el-radio-group>
		</el-form-item>

		<el-form-item label="관리자">
			<el-input type="text" v-model="model.employeeId" name="employeeId" readonly="readonly" />			
		</el-form-item>

		<el-form-item label="등록일">
			<el-input type="text" v-model="model.createdate" name="createdate" readonly="readonly" />			
		</el-form-item>
		
		<el-form-item label="수정일">
			<el-input type="text" v-model="model.updatedate" name="updatedate" readonly="readonly" />			
		</el-form-item>
		<input type="hidden" name="programNo" :value="model.programNo">
		<el-form-item>
			<el-button type="primary" @click="updateSubmit(form)">수정</el-button>
			<el-button type="info" @click="showList()">목록보기</el-button>
		</el-form-item> 
	</el-form>


</c:set>
<c:set var="script">
	data() {
	  	return {
		    model: {
	    			programNo: '${programNo}', 
	    			programNo: '${programNo}', 
				    employeeId: '${employeeId}',
				    programName: '${programName}',
				    maxCustomer: '${maxCustomer}',
				    programActive: '${programActive}',
				    createdate: '${createdate}',
				    updatedate: '${updatedate}',
		    },
	  	};
	},
	methods: {
	
		updateSubmit() {
			document.getElementById('updateProgramForm').submit();
		},
		
		showList() {
			location.href = `${ctp}/program/list`;
		},
		
	}
</c:set>

<%@ include file="/inc/admin_layout.jsp"%>