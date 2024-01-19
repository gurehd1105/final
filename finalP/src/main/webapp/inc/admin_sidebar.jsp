<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<div id="side" class="h-[calc(100vh-60px)]">
    <el-aside width="200px">
        <el-scrollbar>
            <el-menu :default-active="activeIndex" @select="handleSelect">
                <el-sub-menu index="1">
                    <template #title> 관리 </template>
                    <el-sub-menu index="1-1">
                        <template #title>지점 관리</template>
                        <el-menu-item index="1-1-1"> 지점 추가 </el-menu-item>
                        <el-menu-item index="1-1-2">
                            지점 목록 조회
                        </el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="1-2">
                        <template #title>직원 관리</template>
                        <el-menu-item index="1-2-1"> 직원 추가 </el-menu-item>
                        <el-menu-item index="1-2-2">
                            직원 목록 조회
                        </el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="1-3">
                        <template #title>공지 관리</template>
                        <el-menu-item index="1-3-1"> 공지 추가 </el-menu-item>
                        <el-menu-item index="1-3-2"> 공지 목록 </el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="1-4">
                        <template #title>프로그램 관리</template>
                        <el-menu-item index="1-4-1">
                            프로그램 추가
                        </el-menu-item>
                        <el-menu-item index="1-4-2">
                            프로그램 목록 조회
                        </el-menu-item>
                        <el-menu-item index="1-4-3">
                            프로그램 진행일 조회 및 추가
                        </el-menu-item>
                    </el-sub-menu>
                </el-sub-menu>
                <el-sub-menu index="2">
                    <template #title> 스포츠 장비 </template>
                    <el-menu-item-group>
                        <template #title>본사</template>
                        <el-menu-item index="2-1">
                            장비리스트 추가
                        </el-menu-item>
                        <el-menu-item index="2-2"> 발주내역 </el-menu-item>
                        <el-menu-item index="2-3"> 재고 </el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="지점">
                        <el-menu-item index="2-4"> 장비리스트 </el-menu-item>
                        <el-menu-item index="2-5">
                            발주내역(지점:부산점)
                        </el-menu-item>
                        <el-menu-item index="2-6">
                            재고(지점:부산점)
                        </el-menu-item>
                    </el-menu-item-group>
                </el-sub-menu>
            </el-menu>
        </el-scrollbar>
    </el-aside>
</div>
<script>
    const side = Vue.createApp({
        data() {
            return {
                activeIndex: "1", // 메뉴 초기 선택 항목,
                urls: {
                    "1-1-1": ["${ctp}/branch/insert"],
                    "1-1-2": [
                        "${ctp}/branch/list",
                        "${ctp}/branch/update",
                        "${ctp}/branch/insert",
                    ],
                    "1-2-1": ["${ctp}/employee/insert"],
                    "1-2-2": ["${ctp}/employee/list"],
                    "1-3-1": ["${ctp}/notice/insert"],
                    "1-3-2": [
                        "${ctp}/notice/list",
                        "${ctp}/notice/read",
                        "${ctp}/notice/update",
                    ],
                    "1-4-1": ["${ctp}/program/insert"],
                    "1-4-2": ["${ctp}/program/list", "${ctp}/program/update"],
                    "1-4-3": [
                        "${ctp}/programDate/list",
                        "${ctp}/programDate/update",
                        "${ctp}/programDate/insert",
                    ],
                    "2-1": ["${ctp}/sportsEquipment/insert"],
                    "2-2": ["${ctp}/sportsEquipment/orderByHead"],
                    "2-3": ["${ctp}/sportsEquipment/inventoryByHead"],
                    "2-4": [
                        "${ctp}/sportsEquipment/list",
                        "${ctp}/sportsEquipment/sportsEquipmentOne",
                    ],
                    "2-5": ["${ctp}/sportsEquipment/orderByBranch"],
                    "2-6": ["${ctp}/sportsEquipment/inventoryByBranch"],
                },
            };
        },
        mounted() {
            const pathname = "${ctp}" + location.pathname;
            const idx = Object.keys(this.urls).find((key) =>
                this.urls[key].find((url) => pathname.startsWith(url))
            );
            if (idx) {
                this.activeIndex = idx;
            }
        },
        methods: {
            handleSelect(key, keyPath) {
                const href = this.urls[key];
                if (href) {
                    location.href = href[0];
                }
            },
        },
    });

    side.use(ElementPlus, {
        locale: ElementPlusLocaleKo,
    });

    side.mount("#side");
</script>
