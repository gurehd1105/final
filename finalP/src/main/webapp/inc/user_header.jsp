<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<title><c:out value="${title}" /></title>
<meta content="${keywords}" name="keywords" />
<meta content="${description }" name="description" />

<meta property="og:title" content="${title}" />
<meta property="og:description" content="${description}" />
<meta property="og:image" content="/goodee_logo.png" />

<!-- Favicon -->
<link href="/favicon.ico" rel="icon" />

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
    href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap"
    rel="stylesheet"
/>

<!-- Icon Font Stylesheet -->
<link
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
    rel="stylesheet"
/>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
    rel="stylesheet"
/>
<script src="https://cdn.tailwindcss.com"></script>
<!-- Import Vue 3 -->
<script src="https://unpkg.com/vue@3"></script>
<!-- Import component library -->
<script src="https://unpkg.com/element-plus"></script>
<script src="https://unpkg.com/@element-plus/icons-vue"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/element-plus/dist/locale/ko"></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment-with-locales.min.js"
    integrity="sha512-4F1cxYdMiAW98oomSLaygEwmCnIP38pb4Kx70yQYqRwLVCs3DbRumfBq82T08g/4LJ/smbFGFpmeFlQgoDccgg=="
    crossorigin="anonymous"
    referrerpolicy="no-referrer"
></script>
<style>
    [v-cloak] {
        display: none;
    }
</style>
