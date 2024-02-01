<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="title" value="프로그램 일정" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

<c:set var="body">
    <el-table :data="programDateList" class="w-fit" @row-click="rowClick"
      class-name="cursor-pointer"> 
      <el-table-column label="번호" type="index" width="60"></el-table-column>
      <el-table-column prop="programDateNo" label="programDateNo" v-if="shouldShowProgramDateNo"></el-table-column>
      <el-table-column prop="programName" label="프로그램명" width="120"></el-table-column> 
      <el-table-column prop="programDate" label="진행일" :formatter="formatDate" width="150"></el-table-column> 
      <el-table-column prop="createdate" label="등록일" :formatter="formatDate" ></el-table-column> 
      <el-table-column prop="updatedate" label="수정일" :formatter="formatDate" ></el-table-column>       
      
      <el-table-column label="수정/삭제">
          <template #default="scope">
             <el-button type="success" @click="update(scope.row)">수정</el-button>      		
             <el-button type="danger" @click="remove(scope.row)">삭제</el-button>
          </template>          
   	 </el-table-column>
   </el-table>
   	 <el-button type="primary" @click="insert()">추가</el-button>
</c:set>

<c:set var="script">
    data() {
        return {        
            programDateList: JSON.parse('${programDateList}'),  
        }
    },
    
    methods: {
    	update(row) {
           location.href = '${ctp}/programDate/update?programDateNo=' + row.programDateNo;
       },
       remove(row){
           const self = this;
           const programDate={programDateNo: row.programDateNo}
           axios.post('${ctp}/programDate/delete', programDate)
            .then((res) => {
               console.log(res.data);
               if(res.data == 1){
                  alert('삭제 성공.')
                  
               } else {
                  alert('삭제 실패.')
               }
            }).catch((res) => {
               alert('error');
            })
       },       
        insert(){
        	location.href = '${ctp}/programDate/insert';
       },
        formatDate(row, column, cellValue) {
       		// 진행일 열은 시간을 표시하지 않고 날짜만 표시
            if (column.property === 'programDate') {
                return new Date(cellValue).toLocaleDateString();
            }
            return new Date(cellValue).toLocaleString();
        },
       
       
    },
</c:set>

<%@ include file="/inc/admin_layout.jsp" %>
