<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="직원 정보수정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg" 
			action="${ctp}/employee/updateOne" method="post"  id="updateOne" enctype="multipart/form-data">
		
			
	    <el-form-item label="프로필">
			<el-upload
			    class="avatar-uploader"
			    action="/file/upload/employee"
			    :show-file-list="false"
			    :on-success="handleSuccess"
			  >
			    <img v-if="employee.employeeImg" :src="employee.employeeImg" class="avatar" />
			    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
			  </el-upload>
	    </el-form-item>

	    <el-form-item label="이름">
	        <el-input v-model="employee.name" name="employeeName" placeholder="NAME"/>
	    </el-form-item>
	    
	    <el-form-item label="성별">
		    <el-radio-group v-model="employee.gender" name="employeeGender" class="ml-4" >
		      <el-radio label="남">남자</el-radio>
		      <el-radio label="여">여자</el-radio>
		    </el-radio-group>
	    </el-form-item>
	    
	    <el-form-item label="연락처">
	        <el-input v-model="employee.phone" name="employeePhone" placeholder="PHONE"/>
	    </el-form-item>
	    
	    <el-form-item label="이메일">
	    	<el-col :span="14">
		        <el-input v-model="employee.employeeEmailId" placeholder="EMAILID"/>
	        </el-col>
	        <el-col :span="2" class="text-center">@</el-col>
	        <el-col :span="8">
	        	<el-autocomplete
			        v-model="employee.employeeEmailJuso"
			        :fetch-suggestions="getSuggestion"
			        clearable
			        class="inline-input w-full"
			        placeholder="EMAIL ADDRESS"
			      />
	        </el-col>	      
	    </el-form-item>
	    <el-form-item>
	    	  <el-input type="hidden" name="employeeEmail" :value="employee.employeeEmailId + '@' +employee.employeeEmailJuso">
	    </el-input>
	    
	    <el-form-item>
	      	<el-button type="primary" @click="onSubmit()">완료</el-button>
	    </el-form-item>

	</el-form>
</c:set>
<c:set var="script">
	data() {
		return{
	    	employee: {
	    		no: ${resultMap.employeeNo } ,
	    		id: '${ resultMap.employeeId }',
	    		name: '${ resultMap.employeeName }',
	    		gender: '${ resultMap.employeeGender }',
	    		phone: '${ resultMap.employeePhone }',
	    		employeeEmailId: '${ resultMap.emailId }',
	    		employeeEmailJuso: '${ resultMap.emailJuso }',
	    		employeeImg: '${ resultMap.employeeImgSrc }',
	    	},
	    	emailSuggestion: [
	    		'naver.com',
	    		'gmail.com',
	    		'hanmail.net',
	    		'nate.com',
	    		'daum.net',
	    		'icloud.com'
	    	]
	    }		
	},
	methods: {
		validCheck() {
			return true;
		},
		onSubmit() {
			const self = this;
			const employee = {
				employeeNo: this.employee.no,
				employeeId: this.employee.id,
				employeePw: this.employee.pw,
				employeeName: this.employee.name,
				employeeGender: this.employee.gender,
				employeePhone: this.employee.phone,
				employeeEmail: this.employee.employeeEmailId + '@' + this.employee.employeeEmailJuso,
				employeeImg: this.employee.employeeImg,
			}
			
			axios.post('../employee/updateOne', employee)
				.then((res) => {
					self.$notify({
					  title: '직원 수정 성공',
					  message: '데이터베이스에 저장 되었습니다.',
					  type: 'success',
					})
					location.href = '/employee/mypage';
				}).catch((res) => {
					self.$notify({
					  title: '직원 수정 실패',
					  message: res.message,
					  type: 'error',
					})
				})
		},
		getSuggestion(query, cb) {
			const result = this.emailSuggestion.filter(x => x.indexOf(query) !== -1);
			cb(result.map(x => { return { value: x } }));
			console.log(query, result);
		},
		handleSuccess(response, uploadFile) {
			this.employee.employeeImg = '${ctp}/upload/employee/' + response;
		},
	}
</c:set>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
  margin-left: 40%;
  width: 100%;
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>


<%@ include file="/inc/admin_layout.jsp" %>