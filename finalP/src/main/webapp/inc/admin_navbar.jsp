<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<div id="header" v-cloak class="sticky top-0 shadow-md z-10">
    <el-header class="flex flex-row justify-between items-center bg-gray-800">
        <a :class="a_class" href="/home">HOME</a>

        <div class="flex flex-row space-x-3 items-center">
            <a :class="a_class" href="/question/list" v-if="isLogin">
                <el-badge :value="question" class="item">
                    미답변 문의
                </el-badge>
            </a>

            <a
                :class="a_class"
                href="/sportsEquipment/orderByHead"
                v-if="isLogin"
            >
                <el-badge :value="order" class="item"> 발주내역 </el-badge>
            </a>
        </div>

        <div class="flex flex-row space-x-3 items-center">
            <a :class="a_class" href="/employee/mypage" v-if="isLogin"
                >내정보</a
            >

            <a :class="a_class" href="/employee/login" v-if="!isLogin"
                >로그인</a
            >
            <a :class="a_class" href="/employee/logout" v-else>로그아웃</a>
        </div>
    </el-header>
</div>
<script>
    const header = Vue.createApp({
        data() {
            return {
                a_class:
                    "hover:bg-gray-700 text-lg text-white font-bold py-2 px-4 rounded-md cursor-pointer",
                isLogin: Boolean("${loginEmployee}"),
                question: Number(${question_notyet}),
                order: Number(${order}),
            };
        },
    });
    header.use(ElementPlus, {
        locale: ElementPlusLocaleKo,
    });
    header.mount("#header");
</script>
