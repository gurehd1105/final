<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/inc/user_header.jsp" />
    </head>
    <body class="m-0 min-h-[100vh] mx-auto container shadow-xl">
        <jsp:include page="/inc/user_navbar.jsp" />
        <div class="flex flex-col divide-y">
            <div id="app" class="w-full" v-cloak>
                <el-scrollbar
                    class="min-h-[calc(100vh-60px)] flex items-center justify-center"
                >
                    <el-main class="min-h-[100%]">
                        <c:out value="${body}" escapeXml="false" />
                    </el-main>
                </el-scrollbar>
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
