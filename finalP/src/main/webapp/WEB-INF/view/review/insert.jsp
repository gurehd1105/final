<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="리뷰입력" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
<el-row :gutter="30">
	<el-col :span="12">	
		<el-table :data="attendanceList" class="w-fit" @row-click="rowClick"
					class-name="cursor-pointer">	     
			<el-table-column type="index" label="No"></el-table-column>
			<el-table-column prop="branchName" label="방문지점" width="140"></el-table-column>   
			<el-table-column prop="programName" label="프로그램명" width="140"></el-table-column> 
			<el-table-column prop="customerAttendanceEnterTime" label="방문시간"></el-table-column> 
			<el-table-column label="check">
				<el-checkbox id="attendanceNo">{{ customerAttendanceNo }}</el-checkbox>
			</el-table-column> 
		</el-table>
   	<br>
		<p style="text-align: center;">작성하실 리뷰의 출석정보를 선택 후 작성해주세요</p>
		
	</el-col>
	
	<el-col :span="12">
		<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
				 action="${ctp}/review/insert" method="post" id="insert">
			
			<el-form-item label="작성자">
				<el-input v-model="customerId" readonly/>				
			</el-form-item>
			
			<el-form-item>
				<el-input type="hidden" name="customerAttendanceNo" v-model="customerAttendanceNo"/>
			</el-form-item>
			
			<el-form-item label="제목">
				<el-input v-model="reviewTitle" name="reviewTitle"/>
			</el-form-item>
				<br>
			<el-form-item label="리뷰내용">
				<textarea cols="50" rows="15" style="resize: none;" v-model="reviewContent" name="reviewContent"></textarea>
			</el-form-item>
			
			<el-form-item>
				<el-button type="primary" @click="submit()">등록</el-button>
			</el-form-item>
		</el-form>
	</el-col>
</el-row>
	
</c:set>

<c:set var="script">
	data() {
		return {
			customerId: '${ loginCustomer.customerId }',
			reviewTitle: '',
			reviewContent: '',
			attendanceList: JSON.parse('${ attendanceList }'),
		}
	},
	
	methods: {
		submit() {
			const attNo = document.getElementById('attendanceNo').value;
			console.log(attNo);
			//<!-- document.getElementById('insert').submit(); -->
		},
	},
</c:set>
<%@ include file="/inc/user_layout.jsp" %>