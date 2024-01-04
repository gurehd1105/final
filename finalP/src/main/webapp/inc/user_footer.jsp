<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="footer">
	<el-footer style="background-color:#c6e2ff">Footer</el-footer>
</div>
<script>
	const footer = Vue.createApp({
		data() {
			return {
				msg: 'hello world!'
			}
		}
	});
	footer.use(ElementPlus, {
	  locale: ElementPlusLocaleKo,
	});
	footer.mount("#footer");
</script>