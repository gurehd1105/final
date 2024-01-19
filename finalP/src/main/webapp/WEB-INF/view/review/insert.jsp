<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="리뷰입력" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
<el-row :gutter="20">
	<el-col :span="10">	
		<el-table :data="attendanceList" class="w-fit" @row-click="rowClick"
					class-name="cursor-pointer">			    
			<el-table-column label="해당하는 출석정보를 선택 후 리뷰를 작성해주세요."/>
			<el-table-column prop="customerAttendanceNo" label="방문번호"></el-table-column>
			<el-table-column prop="branchName" label="방문지점" width="140"></el-table-column>   
			<el-table-column prop="programName" label="프로그램명" width="140"></el-table-column> 
			<el-table-column prop="customerAttendanceEnterTime" label="방문일자" :formatter="formatDate"></el-table-column>		
			<el-table-column label="선택"><input type="radio" v-model="check" name="check" /></el-table-column>
		</el-table>
   	<br>
   	

	<el-alert title="작성 시 확인해주세요." type="warning" >
	   	<p>하나의 출석정보는 하나의 리뷰만 작성할 수 있습니다.</p><br>
	   	<p>이미 작성된 리뷰가 있는 출석정보는 조회되지 않습니다.</p><br>
	   	<p>출석 정보는 최근 5개의 정보만 조회됩니다.</p>
	</el-alert>

       
	  
	
		
	</el-col>
	
	<el-col :span="14">
		<el-form label-position="right" ref="form" label-width="200px" status-icon class="max-w-lg"
				 action="${ctp}/review/insert" method="post" id="insert">
			
			<el-form-item label="작성자">
				<el-input readonly v-model="customerId"/>				
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
			
			<el-form-item label="방문번호">
				<el-input type="hidden" name="customerAttendanceNo" v-model="customerAttendanceNo"/>
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
			customerAttendanceEnterTime: '',
			customerAttendanceNo: '',
			check: '',
		}
	},
position: 'top-left',
	
	methods: {
		submit() {
			if(this.reviewTitle.length < 5 || this.reviewContent.length < 10){
				alert('제목 및 내용은 각 5자 , 10자 이상 입력해주세요.');
			} else if(this.customerAttendanceNo.length < 1 || this.check == ''){
				alert('출석정보를 선택해주세요');
			} else {
				document.getElementById('insert').submit();
			}
		},
		
		formatDate(row, column, cellValue) {
            if (column.property === 'programDate') {
                return new Date(cellValue).toLocaleString();
            }
            
            return new Date(cellValue).toLocaleString();
        },
		
		rowClick(row, column){
			console.log(row);
			if(column.property){
				this.customerAttendanceNo = row.customerAttendanceNo;				
			}
     	},
     	
     	 
     	
	},	
	
	
	

</c:set>
<%@ include file="/inc/user_layout.jsp" %>