<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/inc/admin_header.jsp" />
</head>
<body class="m-0 h-[100vh]">
	<jsp:include page="/inc/admin_navbar.jsp" />
	<div class="flex flex-row divide-x">
		<jsp:include page="/inc/admin_sidebar.jsp" />
		<div id="app">
			<el-scrollbar class="h-[calc(100vh-60px)]">
				<el-main>
					<c:out value="${body}" escapeXml="false"/>
				</el-main>
				<div>hello</div>
				<div>hello</div>
				<div>hello</div>
				<div>hello</div>
				<div>hello</div>
				<div>hello</div>
				<div>hello</div>
				<div>hello</div>
			</el-scrollbar>
		</div>
	</div>
</body>
<script>
	const main = Object.assign({
		data() {
			return {};
		}
	}, { <c:out value="${script}" /> });
	const app = Vue.createApp(main);
	app.use(ElementPlus);
	app.mount("#app");
</script>