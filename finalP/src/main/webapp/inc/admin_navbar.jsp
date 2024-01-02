<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
	<el-header style="background-color:#c6e2ff">{{ msg }}</el-header>
</div>
<script>
	const header = Vue.createApp({
		data() {
			return {
				msg: 'hello world!'
			}
		}
	});
	header.use(ElementPlus, {
	  locale: ElementPlusLocaleKo,
	});
	header.mount("#header");
</script>