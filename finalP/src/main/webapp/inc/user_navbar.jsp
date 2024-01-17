<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<div id="header" v-cloak>
    <el-header class="!p-0 mx-auto font-bold">
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            :ellipsis="false"
            @select="handleSelect"
        >
            <el-menu-item index="1">
                <template #title>
                    <el-icon>
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 1024 1024"
                            data-v-ea893728=""
                        >
                            <path
                                fill="currentColor"
                                d="M512 128 128 447.936V896h255.936V640H640v256h255.936V447.936z"
                            ></path>
                        </svg>
                    </el-icon>
                    <span>HOME</span>
                </template>
            </el-menu-item>

            <div class="flex-grow"></div>

            <el-menu-item index="3">
                <el-icon v-html="svg.membership"></el-icon>
                <span>멤버쉽</span>
            </el-menu-item>
            <el-menu-item index="4">프로그램</el-menu-item>
            <el-menu-item index="5">
                <template #title>
                    <el-icon>
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 1024 1024"
                            data-v-ea893728=""
                        >
                            <path
                                fill="currentColor"
                                d="M736 504a56 56 0 1 1 0-112 56 56 0 0 1 0 112m-224 0a56 56 0 1 1 0-112 56 56 0 0 1 0 112m-224 0a56 56 0 1 1 0-112 56 56 0 0 1 0 112M128 128v640h192v160l224-160h352V128z"
                            ></path>
                        </svg>
                    </el-icon>
                    <span>문의하기</span>
                </template>
            </el-menu-item>
            <el-menu-item index="6">리뷰보기</el-menu-item>
            <el-menu-item index="7">공지사항</el-menu-item>

            <div class="flex-grow"></div>

            <el-menu-item index="8" v-if="loginCustomer">예약</el-menu-item>
            <el-menu-item index="9" v-if="loginCustomer">내정보</el-menu-item>
            <el-menu-item index="2" v-if="!loginCustomer">
                <template #title>
                    <span>로그인</span>
                </template>
            </el-menu-item>
            <el-menu-item index="10" v-else>
                <template #title>
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 1024 1024"
                        data-v-ea893728=""
                    >
                        <path
                            fill="currentColor"
                            d="M288 320a224 224 0 1 0 448 0 224 224 0 1 0-448 0m544 608H160a32 32 0 0 1-32-32v-96a160 160 0 0 1 160-160h448a160 160 0 0 1 160 160v96a32 32 0 0 1-32 32z"
                        ></path>
                    </svg>
                    <span>로그아웃</span>
                </template>
            </el-menu-item>
        </el-menu>
    </el-header>
</div>
<script>
    const header = Vue.createApp({
        data() {
            return {
                activeIndex: "1", // 메뉴 초기 선택 항목,
                loginCustomer: Boolean("${ loginCustomer }"), // truthy
                urls: [
                    "${ctp}/home",
                    "${ctp}/customer/login",
                    "${ctp}/membership/list",
                    "${ctp}/program/list",
                    "${ctp}/question/list",
                    "${ctp}/review/list",
                    "${ctp}/notice/list",
                    "${ctp}/calendar",
                    "${ctp}/customer/customerOne",
                    "${ctp}/customer/logout",
                ],

                svg: {
                    membership: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M896 324.096c0-42.368-2.496-55.296-9.536-68.48a52.352 52.352 0 0 0-22.144-22.08c-13.12-7.04-26.048-9.536-68.416-9.536H228.096c-42.368 0-55.296 2.496-68.48 9.536a52.352 52.352 0 0 0-22.08 22.144c-7.04 13.12-9.536 26.048-9.536 68.416v375.808c0 42.368 2.496 55.296 9.536 68.48a52.352 52.352 0 0 0 22.144 22.08c13.12 7.04 26.048 9.536 68.416 9.536h567.808c42.368 0 55.296-2.496 68.48-9.536a52.352 52.352 0 0 0 22.08-22.144c7.04-13.12 9.536-26.048 9.536-68.416zm64 0v375.808c0 57.088-5.952 77.76-17.088 98.56-11.136 20.928-27.52 37.312-48.384 48.448-20.864 11.136-41.6 17.088-98.56 17.088H228.032c-57.088 0-77.76-5.952-98.56-17.088a116.288 116.288 0 0 1-48.448-48.384c-11.136-20.864-17.088-41.6-17.088-98.56V324.032c0-57.088 5.952-77.76 17.088-98.56 11.136-20.928 27.52-37.312 48.384-48.448 20.864-11.136 41.6-17.088 98.56-17.088H795.84c57.088 0 77.76 5.952 98.56 17.088 20.928 11.136 37.312 27.52 48.448 48.384 11.136 20.864 17.088 41.6 17.088 98.56z"></path><path fill="currentColor" d="M64 320h896v64H64zm0 128h896v64H64zm128 192h256v64H192z"></path></svg>`,
                },
            };
        },
        mounted() {
            const idx = this.urls.indexOf("${ctp}" + location.pathname) + 1;
            console.log(idx);
            if (idx) {
                this.activeIndex = "" + idx;
            }
        },
        methods: {
            handleSelect(key, keyPath) {
                const href = this.urls[parseInt(key) - 1];
                if (href) {
                    location.href = href;
                }
            },
        },
    });
    header.use(ElementPlus, {
        locale: ElementPlusLocaleKo,
    });
    header.use(ElementPlusIconsVue);
    header.mount("#header");
</script>
