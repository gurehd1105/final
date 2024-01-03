<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Vue 3 + Element Plus Example</title>
        <!-- Import style -->
        <link
            rel="stylesheet"
            href="https://unpkg.com/element-plus/dist/index.css"
        />
        <!-- Import Vue 3 -->
        <script src="https://unpkg.com/vue@3"></script>
        <!-- Import component library -->
        <script src="https://unpkg.com/element-plus"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    </head>
    <body>
        <div id="app" style="height: 100vh">
		  <div class="common-layout" style="height: 100%">
		    <el-container style="height: 100%">
		      <el-header style="background-color:#c6e2ff">Header</el-header>
		      <el-main>
		      	<el-calendar v-model="date" />
		      </el-main>
		      <el-footer style="background-color:#c6e2ff">Footer</el-footer>
		    </el-container>
		  </div>
        </div>
        <script>
            const app = Vue.createApp({
                data() {
                    return {
                        date: new Date(),
                        reservation: [],
                    };
                },
                watch: {
                	date(newvalue, prev) {
                		this.getReservation(newvalue);
                	}
                },
                methods: {
                    getReservation(date) {
                    	console.log('대충 예약 목록 가져옴~~ : ', date)
                    }
                },
            });

            app.use(ElementPlus);

            app.mount("#app");
        </script>
    </body>
</html>
