<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<c:set var="title" value="메인페이지" />
<c:set var="description" value="헬스 관련 업무들을 할 수 있는 사이트" />
<c:set var="keywords" value="운동,헬스,헬스장,예약" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="body">
    <template class="grid grid-cols-1 lg:grid-cols-2 gap-4 lg:gap-6">
        <div class="block w-full lg:col-span-2">
            <el-carousel
                :interval="1000 * 4"
                arrow="always"
                height="400px"
                class="w-full"
            >
                <el-carousel-item v-for="image of images" :key="image">
                    <img :src="image.src" width="100%"/>
                    <h3>{{ image.title }}</h3>
                </el-carousel-item>
            </el-carousel>
        </div>

        <!-- 프로그램일정 -->
        <div class="space-y-4">
            <el-text size="large" tag="b">프로그램 일정</el-text>
            <el-row :gutter="10">
                <el-col :span="12">
                    <el-select
                        v-model="selectBranch"
                        id="selectBranch"
                        clearable
                        placeholder="지점 선택"
                    >
                        <el-option
                            v-for="(branch, b) in branchList"
                            :key="b"
                            :label="branch.branchName"
                            :value="branch.branchNo"
                            :selected="b === 0"
                        >
                        </el-option>
                    </el-select>
                </el-col>
                <el-col :span="12">
                   <el-select v-model="selectProgram" id="selectProgram" clearable placeholder="프로그램 선택">
		                <el-option v-for="(program, p) in programList" 
		                    :key="p" 
		                    :label="program.programName" 
		                    :value="program.programNo"
		                    :selected="p === 0">
		                </el-option> 
		            </el-select>
                </el-col>
            </el-row>

            <el-calendar v-model="selectDate">
                <template #date-cell="{ data }">
                    <p
                        :key="selectProgram"
                        :class="[
                        data.isSelected ? 'is-selected' : '',
                        getInfo(data.day) ? 'hover:font-bold hover:underline' : 'text-gray-200 hover:cursor-not-allowed',
                    ]"
                    >
                        {{ getDayString(data.day) }}
                    </p>
                </template>
            </el-calendar>
        </div>

        <!-- 공지사항 -->
        <div class="space-y-4">
            <div class="flex flex-row justify-between">
                <el-text size="large" tag="b">공지사항</el-text>
                <el-link href="/notice/list" type="primary" class="h-fit"
                    >공지사항 전체보기 >
                </el-link>
            </div>
            <el-table :data="notices" border class="w-fit">
                <el-table-column
                    prop="noticeTitle"
                    label="제목"
                ></el-table-column>
                <el-table-column
                    prop="employeeName"
                    label="작성자"
                    width="180"
                ></el-table-column>
                <el-table-column
                    prop="createdate"
                    label="작성일"
                    width="180"
                ></el-table-column>
                <el-table-column fixed="right" label="관리" width="100">
                    <template #default="scope">
                        <el-button
                            plain
                            size="small"
                            @click="move(scope.row, 'read')"
                            >보기</el-button
                        >
                        <el-button
                            plain
                            type="primary"
                            v-if="isEmployee"
                            @click="move(scope.row, 'update')"
                            size="small"
                            >수정</el-button
                        >
                        <el-button
                            plain
                            type="danger"
                            v-if="isEmployee"
                            @click="move(scope.row, 'delete')"
                            size="small"
                            >삭제</el-button
                        >
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </template>
</c:set>

<!-- prettier-ignore -->
<c:set var="script">
    data() { 
        return { 
            images: [ 
                { title: '본점', src: '/place0.png' }, 
                { title: '본점', src: '/place1.png' }, 
                { title: '부산점', src: '/place2.png' }, 
                { title: '부산점', src: '/place6.png' }, 
                { title: '대전점', src: '/place3.png' }, 
                { title: '여수점', src: '/place4.png' }, 
                { title: '까치산점', src: '/place5.png' }, 
            ], 
            branchList: JSON.parse('${branchList}'), 
            programList: JSON.parse('${programList}'), 
            selectBranch: null, 
            selectProgram: null,
            reservationInfos: [], 
            selectDate: new Date(), 
            notices: JSON.parse('${noticeList}'), 
            isEmployee: Boolean('${loginEmployee}'), 
        } 
    },
    watch: { 
        selectProgram: function(now, before) { 
            const self = this; 
            if (now) { 
                axios.get('${ctp}/reservation/program/' + now + '/reservationInfo')
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
        },
        move(row, path) {
		if (row.noticeNo != null) {
		   	location.href = ['/notice', path, row.noticeNo].join('/');
		}
	  },
	  
	  
    }
</c:set>

<style>
    .el-carousel__item h3 {
        color: #475669;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
        text-align: center;
    }
</style>

<!-- prettier-ignore -->
<%@include file="/inc/user_layout.jsp" %>
