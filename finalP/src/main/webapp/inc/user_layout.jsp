<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-gray-100">
    <head>
        <%@ include file="/inc/user_header.jsp" %>
    </head>
    <body class="m-0 min-h-[100vh] mx-auto container shadow-xl bg-white">
        <jsp:include page="/inc/user_navbar.jsp" />
        <div class="flex flex-col divide-y">
            <div id="app" class="w-full" v-cloak>
                <el-main
                    class="min-h-[calc(100vh-60px)] flex items-center justify-center"
                >
                    <c:out value="${body}" escapeXml="false" />
                </el-main>
            </div>
            <jsp:include page="/inc/user_footer.jsp" />
        </div>
    </body>
    <script>
        const main = Object.assign({
        	data() {
        		return {};
        	}
        }, { <c:out value="${script}" escapeXml="false"/> });

        const app = Vue.createApp(main);
        app.use(ElementPlus, {
          locale: ElementPlusLocaleKo,
        });
        app.mount("#app");
    </script>

    <!-- Import style -->
    <link
        rel="stylesheet"
        href="https://unpkg.com/element-plus/dist/index.css"
    />
</html>
