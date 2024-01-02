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
  </head>
  <body>
    <div id="app">
      <el-button @click="showMessage">Click me</el-button>
    </div>

    <script>
      // Vue 3 인스턴스 생성
      const app = Vue.createApp({
        data() {
          return {
            message: "Hello, Element Plus!",
          };
        },
        methods: {
          showMessage() {
            this.$message({
              message: this.message,
              type: "success",
            });
          },
        },
      });

      // Element Plus를 사용하기 위해 전역으로 등록
      app.use(ElementPlus);

      // Vue 인스턴스를 마운트
      app.mount("#app");
    </script>
  </body>
</html>
