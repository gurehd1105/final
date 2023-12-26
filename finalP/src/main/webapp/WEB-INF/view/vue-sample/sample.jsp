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
  </head>
  <body>
    <div id="app">
      <el-button @click="showMessage">Click me</el-button>
    </div>

    <script>
      // Vue 3 ì¸ì¤í´ì¤ ìì±
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

      // Element Plusë¥¼ ì¬ì©íê¸° ìí´ ì ì­ì¼ë¡ ë±ë¡
      app.use(ElementPlus);

      // Vue ì¸ì¤í´ì¤ë¥¼ ë§ì´í¸
      app.mount("#app");
    </script>
  </body>
</html>
