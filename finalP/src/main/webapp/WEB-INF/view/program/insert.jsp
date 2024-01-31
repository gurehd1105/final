<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="프로그램 추가" />
<c:set var="description" value="현재 발주 할 수 있는 장비를 추가할 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,수정,삭제,추가" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">
	
	<!-- 장비 추가 폼 -->
	<h2>장비추가</h2>
	<el-form label-position="right" 
			 ref="form" 
			 label-width="150px" 
			 status-icon class="max-w-lg"
			 action="${ctp}/program/insert" 
			 method="post" 
			 id="insertForm"
			 style="border: 1px solid #ccc; padding: 10px; border-radius: 5px;"
	>
	
		<el-form-item label="이름">
			<el-input type="text" v-model="model.programName" name="programName" placeholder="이름을 입력하세요." />			
		</el-form-item> 
			
		<el-form-item label="최대인원">
			<el-input type="number" v-model="model.maxCustomer" name="maxCustomer" min="0" placeholder="최대인원을 입력하세요." />			
		</el-form-item> 
		
		<el-form-item label="상태">
			<el-radio-group name="programActive" v-model="model.programActive" class="ml-4" >
				<el-radio label="Y">활성화</el-radio>
				<el-radio label="N">비활성화</el-radio>
			</el-radio-group>
		</el-form-item> 
		
		<el-form-item label="내용">
    		<el-input type="textarea" v-model="model.programContent" name="programContent" :rows="5">
    		</el-input>
		</el-form-item>
		
		<el-form-item>
			<el-button type="primary" @click="onSubmit(form)">프로그램추가</el-button>
		</el-form-item> 
	</el-form>

</c:set>
<c:set var="script">
	data() {
		return {
			 model: {
			    programName: '', 
			    programActive: '', 
			    maxCustomer: '', 
			    programContent: '지점 : \n진행 : \n일자 :\n시간 : AM 00:00 - PM 00:00', 
		    },
	  	};

	},
	methods: {
     	
			onSubmit(){
				if (this.model.programName === '' || this.model.maxCustomer === '' ) {
				
	      			alert('이름, 최대인원을 입력하세요.');
	      			
	    		} else if(this.model.programActive === ''){
	    		
	    			alert('프로그램 활성화 여부를 선택하세요.');
	    			
	    		} else if(this.model.programContent === '지점 : \n진행 : \n일자 :\n시간 : AM 00:00 - PM 00:00'){
	    		
	    			alert('프로그램 내용을 수정하세요.');
	    			
	    		} else {
	
	      			document.getElementById('insertForm').submit();
	   			}
			},

	
	}
</c:set>


<%@ include file="/inc/admin_layout.jsp"%>