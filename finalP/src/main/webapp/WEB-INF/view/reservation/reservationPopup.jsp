<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="예약 페이지" />
<c:set var="description" value="예약 페이지입니다." />
<c:set var="keywords" value="예약, 날짜, 헬스장" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="body">
  <el-select v-model="value" placeholder="Select">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
      :disabled="item.disabled"
    />
  </el-select>
</c:set>
	
<c:set var="script">
	import { ref } from 'vue'
	
	const value = ref('')
	const options = [
	  {
	    value: 'Option1',
	    label: 'Option1',
	  },
	  {
	    value: 'Option2',
	    label: 'Option2',
	    disabled: true,
	  },
	  {
	    value: 'Option3',
	    label: 'Option3',
	  },
	  {
	    value: 'Option4',
	    label: 'Option4',
	  },
	  {
	    value: 'Option5',
	    label: 'Option5',
	  },
	]
</c:set>
<%@ include file="/inc/admin_layout.jsp" %>



