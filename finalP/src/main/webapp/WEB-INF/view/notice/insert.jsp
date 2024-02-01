<!-- prettier-ignore -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="공지추가" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />

<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
    <el-form
        label-position="right"
        ref="form"
        label-width="150px"
        status-icon
        class="max-w-lg"
        action="${ctp}/notice/insert"
        method="post"
        enctype="multipart/form-data"
        id="insertForm"
    >
        <el-form-item label="제목">
            <el-input v-model="model.title" name="noticeTitle"/>
        </el-form-item>

        <el-form-item label="내용">
            <el-input v-model="model.content" type="textarea" name="noticeContent"/>
        </el-form-item>

        
        <el-form-item>
            <el-button type="primary" @click="onSubmit(form)"
                >공지등록</el-button
            >
        </el-form-item>
    </el-form>
</c:set>

<!-- prettier-ignore -->
<c:set var="script">
	data() {
	    return {
	    	model: {
	    		title: '',
	    		content: ''
	    	},
	    }
	},
    mounted() {
        const self = this;
    },
	watch: {
	},
	methods: {
		validCheck() {
			return true;
		},
		onSubmit() {
			document.getElementById('insertForm').submit();
		},
		getSuggestion(query, cb) {
			const result = this.emailSuggestion.filter(x => x.indexOf(query) !== -1);
			cb(result.map(x => { return { value: x } }));
			console.log(query, result);
		},
	}
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
