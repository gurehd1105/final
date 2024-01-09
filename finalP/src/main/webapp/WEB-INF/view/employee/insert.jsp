<!-- prettier-ignore -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="직원 추가" />
<c:set var="description" value="직원 추가 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />

<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
    <el-form
        label-position="right"
        ref="form"
        label-width="150px"
        status-icon
        class="max-w-lg"
        action="${ctp}/employee/insert"
        method="post"
        enctype="multipart/form-data"
        id="insertForm"
    >
        <el-form-item label="아이디">
            <el-input v-model="model.id" name="employeeId"/>
        </el-form-item>

        <el-form-item label="비밀번호">
            <el-input v-model="model.pw" type="password" show-password />
        </el-form-item>

        <el-form-item label="비밀번호 확인">
            <el-input v-model="model.pwChk" type="password" show-password />
        </el-form-item>

        <el-form-item label="이름">
            <el-input v-model="model.name" />
        </el-form-item>

        <el-form-item label="성별">
            <el-radio-group v-model="model.gender" class="ml-4">
                <el-radio label="남">남자</el-radio>
                <el-radio label="여">여자</el-radio>
            </el-radio-group>
        </el-form-item>

        <el-form-item label="연락처">
            <el-input v-model="model.phone" />
        </el-form-item>

        <el-form-item label="이메일">
            <el-col :span="14">
                <el-input v-model="model.employeeEmailId" />
            </el-col>
            <el-col :span="2" class="text-center">@</el-col>
            <el-col :span="8">
                <el-autocomplete
                    v-model="model.employeeEmailJuso"
                    :fetch-suggestions="getSuggestion"
                    clearable
                    class="inline-input w-full"
                />
            </el-col>
        </el-form-item>
        <el-form-item label="지점 선택">
            <el-select
                v-model="model.branchNo"
                placeholder="지점을 선택하세요"
            >
                <el-option
                    v-for="branch in branches"
                    :key="branch.branchNo"
                    :label="branch.branchNo"
                    :value="branch.branchNo"
                ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit(form)"
                >회원가입</el-button
            >
        </el-form-item>
    </el-form>
</c:set>

<!-- prettier-ignore -->
<c:set var="script">
	data() {
	    return {
	    	model: {
	    		id: '',
	    		pw: '',
	    		pwChk: '',
	    		name: '',
	    		gender: '',
	    		phone: '',
	    		employeeEmailId: '',
	    		employeeEmailJuso: '',
	    		branchNo: '' 
	    	},
	    	emailSuggestion: [
	    		'naver.com',
	    		'gmail.com',
	    		'hanmail.net',
	    		'nate.com',
	    		'icloud.com'
	    	],
            branches: [],
	    }
	},
    mounted() {
        const self = this;
        axios.get('../branch/list').then((res) => self.branches = res.data);
    },
	watch: {
	},
	methods: {
		validCheck() {
			return true;
		},
		onSubmit() {
			const self = this;
			const employee = {
				branchNo: this.model.branchNo,
				employeeId: this.model.id,
				employeePw: this.model.pw,
				employeeName: this.model.name,
				employeeGender: this.model.gender,
				employeePhone: this.model.phone,
				employeeEmail: this.model.employeeEmailId + '@' + this.model.employeeEmailJuso,
			}
			axios.post('../employee/insert', employee)
				.then((res) => {
					self.$notify({
					  title: '직원 추가 성공',
					  message: '데이터베이스에 저장 되었습니다.',
					  type: 'success',
					})
				}).catch((res) => {
					self.$notify({
					  title: '직원 추가 실패',
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
	}
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
