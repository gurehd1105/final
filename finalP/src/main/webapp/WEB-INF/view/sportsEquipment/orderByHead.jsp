<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비발주 리스트" />
<c:set var="description" value="본사에서 현재 발주요청, 처리 된 이력을 알 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,발주" />
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<c:set var="body">

	<!-- 검색창 -->
	<el-text size="large" tag="b">검색</el-text>
	<el-form label-position="right" 
			 ref="form" 
			 label-width="150px" 
			 status-icon class="max-w-lg" 
			 action="${ctp}/sportsEquipment/orderByHead" 
			 method="get" 
			 id="searchForm"
             class="border rounded-sm p-3"
	>
	
		<el-form-item label="지점검색">
				<el-input v-model="model.searchBranch" name="searchBranch" placeholder="지점을 입력하세요"/>
	   	</el-form-item>
		<el-form-item label="아이템검색">
				<el-input v-model="model.query" name="query" placeholder="아이템을 입력하세요"/>
	   	</el-form-item>
		<el-form-item label="시작일">
				<el-input type="date" v-model="model.beginDate" name="beginDate"/>
	   	</el-form-item>
		<el-form-item label="마지막일">
				<el-input type="date" v-model="model.endDate" name="endDate" />
	   	</el-form-item>
	   	
	   	<el-form-item>
				<el-button type="info" @click="resetSearchSubmit()">전체보기</el-button>
				<el-button type="primary" @click="searchSubmit(form)">검색</el-button>
	   	</el-form-item>
	</el-form>
	
    <!-- 발주 리스트 출력 -->
    <el-table
        :data="list"
        :key="loading"
        class="w-full"
        align="center"
        border
    >
        <el-table-column width="100" label="발주/폐기">
            <template #default="scope">
                <el-text :type="scope.row.quantity > 0 ? 'primary' : 'danger'" tag="b">
                    {{ scope.row.quantity > 0 ? '발주' : '폐기' }}
                </el-text>
            </template>
        </el-table-column>
        <el-table-column width="100" prop="branchName" label="지점"></el-table-column>
        <el-table-column label="이미지">
            <template #default="scope">
                <img :src="'/upload/sportsEquipment/' + scope.row.sportsEquipmentImgFileName" class="image" style="height:auto" />
            </template>
        </el-table-column>
        <el-table-column width="180" prop="itemName" label="아이템"></el-table-column>
        <el-table-column width="120" prop="itemPrice" label="가격"></el-table-column>
        <el-table-column width="100" prop="quantity" label="수량"></el-table-column>
        <el-table-column width="120" prop="totalPrice" label="총가격"></el-table-column>
        <el-table-column label="발주일">
            <template #default="scope">
                <span>{{ moment(scope.row.createdate).format('yyyy-MM-DD HH:mm') }}</span>
            </template>
        </el-table-column>
        <el-table-column label="결재일">
            <template #default="scope">
                <span v-if="scope.row.updatedate == scope.row.createdate">대기중</span>
                <span v-else>{{ moment(scope.row.updatedate).format('yyyy-MM-DD HH:mm') }}</span>
            </template>
        </el-table-column>
        <el-table-column label="현재상태">
            <template #default="scope">
                <el-tag class="ml-2" :type="scope.row.orderStatus === '승인' ? '' : 'danger'">{{ scope.row.orderStatus }}</el-tag>
            </template>
        </el-table-column>
        <el-table-column label="승인/거부">
            <template #default="scope">
                <span v-if="scope.row.orderStatus === '대기'">
					<el-button type="success" plain round @click="approveOrder(scope.row.orderNo)">승인</el-button>
            		<el-button type="danger" plain round @click="rejectOrder(scope.row.orderNo)">거부</el-button>
              	</span>     
                <el-button type="primary" plain round v-else >{{ scope.row.orderStatus }} 완료</el-button>
            </template>
        </el-table-column>
    </el-table>
 	
    <!-- 페이징 네비게이션 -->
	<%@ include file="/inc/pagination.jsp" %>
   	

</c:set>
<c:set var="script">
	data() {
		const model = JSON.parse('${result}');
	  	return {
		    model: {
			    query: model.param.query ?? '', 
			    searchBranch: model.param.searchBranch ?? '',
			    beginDate: model.param.beginDate ?? '',
			    endDate: model.param.endDate ?? '',
		    },
		    
		    list: model.list, 
		    rowPerPage: model.param.rowPerPage,
		    totalCount: model.param.totalCount,
			pageNum: model.param.pageNum,
			query: model.param.query,
			searchBranch: model.param.searchBranch,
			beginDate: model.param.beginDate,
			endDate: model.param.endDate,
            loading: false,
	  		renderTrigger: 0,
	  	};
	},
	mounted() {
        this.loading = true;
    },
	methods: {
		searchSubmit() {
			document.getElementById('searchForm').submit();
		},
		
		resetSearchSubmit() {
			location.href = `${ctp}/sportsEquipment/orderByHead`;

        },
        
	    approveOrder(orderNo) {
	   		const formData = new FormData();
  			formData.append('orderNo', orderNo);
  			formData.append('orderStatus', '승인');
	   		
 		  axios.post(`${ctp}/sportsEquipment/updateOrder`, formData)
	      .then(response => {
	        this.$notify({
	          title: '발주 승인 성공',
	          message: '발주가 성공적으로 승인되었습니다.',
	          type: 'success',
	        });
	        	location.href = `${ctp}/sportsEquipment/orderByHead`;
	      })
	      .catch(error => {
	        this.$notify({
	          title: '발주 승인 실패',
	          message: error.message,
	          type: 'error',
	        });
	      });
	    },
	
	    rejectOrder(orderNo) {
	    	const formData = new FormData();
  			formData.append('orderNo', orderNo);
  			formData.append('orderStatus', '거부');
	   		
 		  axios.post(`${ctp}/sportsEquipment/updateOrder`, formData)
	      .then(response => {
	        this.$notify({
	          title: '발주 거부 성공',
	          message: '발주가 성공적으로 거부되었습니다.',
	          type: 'success',
	        });
	        location.href = `${ctp}/sportsEquipment/orderByHead`;
	      })
	      .catch(error => {
	        this.$notify({
	          title: '발주 거부 실패',
	          message: error.message,
	          type: 'error',
	        });
	      });
	    },
        	
  	    loadPage(pageNum) {
      		const param = new URLSearchParams();
      		param.set('pageNum', pageNum);
      		param.set('query', this.query);
      		param.set('searchBranch', this.searchBranch);
      		param.set('beginDate', this.beginDate);
      		param.set('endDate', this.endDate);

			location.href = '/sportsEquipment/orderByHead?' + param.toString();
      	},
        moment(date) {
            return moment(date);
        }
	}
</c:set>

<%@ include file="/inc/admin_layout.jsp"%>