<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" value="예약 페이지" />
<c:set var="description" value="예약 페이지입니다." />
<c:set var="keywords" value="예약, 날짜, 헬스장" />
<c:set var="ctp" value="${pageContext.request.contextPath}" />

<c:set var="body">
    <el-form id="reservationForm" action="${ctp}/insertReservation" method="post" class="">
    	<el-form-item>
		    <el-text size="large" tag="b">예약하기</el-text>
    	</el-form-item>
    	<el-form-item>
	        <el-select v-model="selectBranch" id="selectBranch" clearable placeholder="지점 선택">
	    		<el-option v-for="(branch, b) in branchList" 
	    			:key="b" 
	    			:label="branch.branchName" 
	    			:value="branch.branchNo"
	    			:selected="b === 0">          
	    		</el-option>
	        </el-select>
    	</el-form-item>
    	
    	<el-form-item>
	        <el-select v-model="selectProgram" id="selectProgram" clearable placeholder="프로그램 선택">
	            <el-option v-for="(program, p) in programList" 
	            	:key="p" 
	                :label="program.programName" 
	                :value="program.programNo"
	                :selected="p === 0">
	            </el-option> 
	        </el-select>
    	</el-form-item>
    	
    	<el-form-item>

    		<el-calendar v-model="selectDate" >
    			<template #date-cell="{ data }">
			      <p
			      	:key="selectProgram" 
			      	:class="[
			      		data.isSelected ? 'is-selected' : '',
			      		getInfo(data.day) ? '' : 'text-gray-200 hover:cursor-not-allowed',
			      	]"
			     
			      	>
			        {{ getDayString(data.day) }}
			      </p>
			    </template>
    		</el-calendar>
    	</el-form-item>
    	
    	<el-form-item>
	        <el-button type="primary" @click="insert">예약하기</el-button>
    	</el-form-item>
    </el-form>
    
</c:set>

<c:set var="script">
    data() {
        console.log('Program List:', this.programList);

        return {         
            branchList: JSON.parse('${branchList}'),           
            programList: JSON.parse('${programList}'),
            selectBranch: null,
            selectProgram: null,
            reservationInfos: [],
            selectDate: new Date(),
        }
    },
    watch: { 
    	selectProgram: function(now, before) {
    		const self = this;
    		if (now) {
    			axios.get('${ctp}/program/' + now + '/reservationInfo')
    				.then((res) => self.reservationInfos = res.data);
    		}
    	},
    },
    methods: {
    	validCheck() {
    		let message = '';
    		const day = moment(this.selectDate).format('yyyy-MM-DD');
    		if (!this.getInfo(day)) {
    			message = '예약 가능한 일자를 선택해 주세요.';
        	}
        	if (!this.selectProgram) {
        		message = "프로그램을 선택해 주세요.";
        	}
        	if (!this.selectBranch) {
        		message = "지점을 선택해 주세요.";
        	}
        	return message;
    	},
        insert() {
        	const self = this;
        	const message = this.validCheck();
        	if (message) {
        		this.$notify({
        			title: '예약 불가',
        			message,
        			type: 'error',
        		})
        		return;
        	}
        	
        	const day = moment(this.selectDate).format('yyyy-MM-DD');
            // 예약 정보를 서버로 전송하는 코드
            const reservationData = {
                branchNo: self.selectBranch,
                programNo: self.selectProgram,
                programDateNo: self.getInfo(day).programDateNo
            };

            // 실제로는 Ajax 또는 fetch를 사용하여 서버로 데이터를 전송합니다.
            fetch('${ctp}/insertReservation', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(reservationData),
            })
            .then(data => {
                self.$notify({
                	title: '예약 성공',
                	message: '예약이 정상적으로 완료되었습니다.',
                	type: 'success',
                })
            })
            .catch(error => {
                self.$notify({
                	title: '예약 실패',
                	message: '서버 오류로 인해 예약이 실패하였습니다. 잠시 후 다시 시도해 주세요.',
                	type: 'error',
                })
            });
        },
        getDayString(date) {
        	const [year, month, day] = date.split('-');
        	const dateString = [month, day].join('-');
        	const info = this.getInfo(date);
        	let reservationString = '';
        	if (info) {
        		reservationString = '(' + (info.maxCustomer - info.cntCustomer) + '개 남음)';
        	}
        	return dateString + reservationString;
        },
        getInfo(date) {
        	const info = this.reservationInfos.find(info => info.programDate === date);
        	return info;
        }
    },
</c:set>

<%@ include file="/inc/user_layout.jsp"%>