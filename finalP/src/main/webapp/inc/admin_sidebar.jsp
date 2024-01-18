<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<div id="side" class="bg-gray-50">
    <el-aside width="200px">
        <el-scrollbar class="h-[calc(100vh-60px)]">
            <el-menu :default-openeds="['1', '2']">
                <el-sub-menu index="1">
                    <template #title> 관리 </template>
                    <el-sub-menu index="1-1">
                        <template #title>지점 관리</template>
                        <a href="${ctp}/branch/insert">
                            <el-menu-item index="1-1-1">
                                지점 추가
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/branch/update">
                            <el-menu-item index="1-1-2">
                                지점 정보 수정
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/branch/delete">
                            <el-menu-item index="1-1-3">
                                지점 삭제
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/branch/list">
                            <el-menu-item index="1-1-4">
                                지점 목록 조회
                            </el-menu-item>
                        </a>
                    </el-sub-menu>
                    <el-sub-menu index="1-2">
                        <template #title>직원 관리</template>
                        <a href="${ctp}/employee/insert">
                            <el-menu-item index="1-2-1">
                                직원 추가
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/employee/update">
                            <el-menu-item index="1-2-2">
                                직원 정보 수정
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/employee/list">
                            <el-menu-item index="1-2-3">
                                직원 목록 조회
                            </el-menu-item>
                        </a>
                    </el-sub-menu>
                    <el-sub-menu index="1-3">
                        <template #title>공지 관리</template>
                        <a href="${ctp}/notice/insert">
                            <el-menu-item index="1-3-1">
                                공지 추가
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/notice/update">
                            <el-menu-item index="1-3-2">
                                공지 수정
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/notice/list">
                            <el-menu-item index="1-3-3">
                                공지 목록 조회
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/notice/list">
                            <el-menu-item index="1-3-4">
                                공지 삭제
                            </el-menu-item>
                        </a>
                    </el-sub-menu>
                    <el-sub-menu index="1-4">
                        <template #title>프로그램 관리</template>
                       	<a href="${ctp}/program/insert">
                            <el-menu-item index="1-4-1">
                                프로그램 추가
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/program/list">
                            <el-menu-item index="1-4-2">
                                프로그램 목록 조회
                            </el-menu-item>
                        </a>
                        <a href="${ctp}/programDate/list">
                            <el-menu-item index="1-4-2">
                                프로그램 진행일 조회 및 추가
                            </el-menu-item>
                        </a>                        
                    </el-sub-menu>
                </el-sub-menu>
                <el-sub-menu index="2">
                    <template #title> 스포츠 장비 </template>
                    <el-menu-item-group>
                        <template #title>본사</template>
                        <a href="${ctp}/sportsEquipment/insert">
                            <el-menu-item index="2-1">
                                장비리스트 추가
                            </el-menu-item>
                        </a>
                        <a
                            href="${ctp}/sportsEquipment/orderByHead"
                        >
                            <el-menu-item index="2-2"> 발주내역 </el-menu-item>
                        </a>
                        <a
                            href="${ctp}/sportsEquipment/inventoryByHead"
                        >
                            <el-menu-item index="2-3"> 재고 </el-menu-item>
                        </a>
                    </el-menu-item-group>
                    <el-menu-item-group title="지점">
                        <a href="${ctp}/sportsEquipment/list">
                            <el-menu-item index="2-4">
                                장비리스트
                            </el-menu-item>
                        </a>
                        <a
                            href="${ctp}/sportsEquipment/orderByBranch"
                        >
                            <el-menu-item index="2-5">
                                발주내역(지점:부산점)
                            </el-menu-item>
                        </a>
                        <a
                            href="${ctp}/sportsEquipment/inventoryByBranch"
                        >
                            <el-menu-item index="2-6">
                                재고(지점:부산점)
                            </el-menu-item>
                        </a>
                    </el-menu-item-group>
                </el-sub-menu>
            </el-menu>
        </el-scrollbar>
    </el-aside>
</div>
<script>
    const side = Vue.createApp({
        data() {
            return {};
        },
    });

    side.use(ElementPlus, {
        locale: ElementPlusLocaleKo,
    });

    side.mount("#side");
</script>
