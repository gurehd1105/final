<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="예약 페이지" />
<c:set var="description" value="예약 페이지입니다." />
<c:set var="keywords" value="예약, 날짜, 헬스장" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="body">
  	<h1>${targetYear}년 ${targetMonth+1}월 ${targetDay}일 예약</h1>
  	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
		 action="${contextPath}/insertReservation" method="post" id="insertReservation">
	  	<div>
		  	<el-select v-model="selectBranch" id="selectBranch" clearable placeholder="지점선택">
		  		<el-option
			      v-for="item in options"
			      :key="item.value"
			      :label="item.label"
			      :value="item.value"
			      :disabled="item.disabled"
			    />
			</el-select>
		</div>
		<br>
		<el-form-item>
        <el-button type="primary" @click="submit">확인</el-button>
      	</el-form-item>
	</el-form>    	

</c:set>

<c:set var="script">
  data() {
    return {
      program:'',
      selectBranch:'',
      options: [
        {
          value: '1',
          label: '서울',
        },
        {
          value: '2',
          label: '대전',
        },
        {
          value: '3',
          label: '부산',
        },
      ],
      
      
    };
  },

  watch: {
    
  },

  methods: {
    submit() {
      const type = document.getElementById('type').value;
      const branch = document.getElementById('selectBranch').value;

      if (!type || !branch) {
		alert("종목과 지점을 모두 선택해주세요.")     
		return;
       }
 
    },
  }
</c:set>
<%@ include file="/inc/user_layout.jsp" %>
