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
					<el-icon v-html="svg.home"></el-icon>
                    <span>HOME</span>
                </template>
            </el-menu-item>

            <div class="flex-grow"></div>

            <el-menu-item index="3">
                <el-icon v-html="svg.membership"></el-icon>
                <span>멤버쉽</span>
            </el-menu-item>
            <el-menu-item index="4">
                <el-icon v-html="svg.program"></el-icon>
                <span>프로그램</span>
            </el-menu-item>
            <el-menu-item index="5">
                <template #title>
                <el-icon v-html="svg.question"></el-icon>
                <span>문의하기</span>
                </template>
            </el-menu-item>
            <el-menu-item index="6">
            	<el-icon v-html="svg.review"></el-icon>
                <span>리뷰</span>
            </el-menu-item>
            <el-menu-item index="7">
                <el-icon v-html="svg.notice"></el-icon>
                <span>공지사항</span>
            </el-menu-item>

            <div class="flex-grow"></div>

            <el-menu-item index="8" v-if="loginCustomer">
            	<template #title>
					<el-icon v-html="svg.reservation"></el-icon>
                    <span>예약</span>
                </template>
            </el-menu-item>
            <el-menu-item index="9" v-if="loginCustomer">
               <template #title>
					<el-icon v-html="svg.myinfo"></el-icon>
                    <span>내정보</span>
                </template>
            </el-menu-item>
            <el-menu-item index="2" v-if="!loginCustomer">
                <template #title>
                	<el-icon v-html="svg.login"></el-icon>
                    <span>로그인</span>
                </template>
            </el-menu-item>
            <el-menu-item index="10" v-else>
                <template #title>
					<el-icon v-html="svg.logout"></el-icon>
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
                    program: ` <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M256 832a128 128 0 1 0 0-256 128 128 0 0 0 0 256m0 64a192 192 0 1 1 0-384 192 192 0 0 1 0 384"></path><path fill="currentColor" d="M288 672h320q32 0 32 32t-32 32H288q-32 0-32-32t32-32"></path><path fill="currentColor" d="M768 832a128 128 0 1 0 0-256 128 128 0 0 0 0 256m0 64a192 192 0 1 1 0-384 192 192 0 0 1 0 384"></path><path fill="currentColor" d="M480 192a32 32 0 0 1 0-64h160a32 32 0 0 1 31.04 24.256l96 384a32 32 0 0 1-62.08 15.488L615.04 192zM96 384a32 32 0 0 1 0-64h128a32 32 0 0 1 30.336 21.888l64 192a32 32 0 1 1-60.672 20.224L200.96 384z"></path><path fill="currentColor" d="m373.376 599.808-42.752-47.616 320-288 42.752 47.616z"></path></svg>`,
                	review: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="m199.04 672.64 193.984 112 224-387.968-193.92-112-224 388.032zm-23.872 60.16 32.896 148.288 144.896-45.696zM455.04 229.248l193.92 112 56.704-98.112-193.984-112-56.64 98.112zM104.32 708.8l384-665.024 304.768 175.936L409.152 884.8h.064l-248.448 78.336zm384 254.272v-64h448v64h-448z"></path></svg>`,
                	question: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M736 504a56 56 0 1 1 0-112 56 56 0 0 1 0 112m-224 0a56 56 0 1 1 0-112 56 56 0 0 1 0 112m-224 0a56 56 0 1 1 0-112 56 56 0 0 1 0 112M128 128v640h192v160l224-160h352V128z"></path></svg>`,
                	notice: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M832 384H576V128H192v768h640zm-26.496-64L640 154.496V320zM160 64h480l256 256v608a32 32 0 0 1-32 32H160a32 32 0 0 1-32-32V96a32 32 0 0 1 32-32m160 448h384v64H320zm0-192h160v64H320zm0 384h384v64H320z"></path></svg>`,
                	login: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M77.248 415.04a64 64 0 0 1 90.496 0l226.304 226.304L846.528 188.8a64 64 0 1 1 90.56 90.496l-543.04 543.04-316.8-316.8a64 64 0 0 1 0-90.496z"></path></svg>`,
                	logout: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M195.2 195.2a64 64 0 0 1 90.496 0L512 421.504 738.304 195.2a64 64 0 0 1 90.496 90.496L602.496 512 828.8 738.304a64 64 0 0 1-90.496 90.496L512 602.496 285.696 828.8a64 64 0 0 1-90.496-90.496L421.504 512 195.2 285.696a64 64 0 0 1 0-90.496z"></path></svg>`,
                	myinfo: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M288 320a224 224 0 1 0 448 0 224 224 0 1 0-448 0m544 608H160a32 32 0 0 1-32-32v-96a160 160 0 0 1 160-160h448a160 160 0 0 1 160 160v96a32 32 0 0 1-32 32z"></path></svg>`,
                	reservation: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M128 384v512h768V192H768v32a32 32 0 1 1-64 0v-32H320v32a32 32 0 0 1-64 0v-32H128v128h768v64zm192-256h384V96a32 32 0 1 1 64 0v32h160a32 32 0 0 1 32 32v768a32 32 0 0 1-32 32H96a32 32 0 0 1-32-32V160a32 32 0 0 1 32-32h160V96a32 32 0 0 1 64 0zm-32 384h64a32 32 0 0 1 0 64h-64a32 32 0 0 1 0-64m0 192h64a32 32 0 1 1 0 64h-64a32 32 0 1 1 0-64m192-192h64a32 32 0 0 1 0 64h-64a32 32 0 0 1 0-64m0 192h64a32 32 0 1 1 0 64h-64a32 32 0 1 1 0-64m192-192h64a32 32 0 1 1 0 64h-64a32 32 0 1 1 0-64m0 192h64a32 32 0 1 1 0 64h-64a32 32 0 1 1 0-64"></path></svg>`,
                	home: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" data-v-ea893728=""><path fill="currentColor" d="M512 128 128 447.936V896h255.936V640H640v256h255.936V447.936z"></path></svg>`,
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
