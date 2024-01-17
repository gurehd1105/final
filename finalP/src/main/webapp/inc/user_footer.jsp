<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<div id="footer" class="border-t p-10 bg-gray-50" v-cloak>
    <el-footer class="!h-auto">
        <div class="flex flex-row justify-between">
            <div>대충 이미지나 로고 때려박는 곳</div>
            <div class="flex flex-row space-x-10">
                <el-link href="/employee/login" type="info" class="h-fit"
                    >관리자 로그인</el-link
                >

                <div class="flex flex-col space-y-8">
                    <el-text size="large" tag="b">참여자</el-text>
                    <div class="flex flex-col space-y-4">
                        <el-text v-for="name of contributors">
                            {{ name }}
                        </el-text>
                    </div>
                </div>

                <div class="flex flex-col space-y-8">
                    <el-text size="large" tag="b">깃허브</el-text>
                    <div class="flex flex-col space-y-4">
                        <el-text v-for="url of github_links">
                            <el-link
                                v-bind:href="url"
                                target="_blank"
                                type="primary"
                                >{{ url }}</el-link
                            >
                        </el-text>
                    </div>
                </div>

                <div class="flex flex-col space-y-8">
                    <el-text size="large" tag="b">몰라</el-text>
                    <div class="flex flex-col space-y-4">
                        <el-text v-for="url of github_links">
                            <el-link
                                v-bind:href="url"
                                target="_blank"
                                type="primary"
                                >{{ url }}</el-link
                            >
                        </el-text>
                    </div>
                </div>
            </div>
        </div>
    </el-footer>
</div>
<script>
    const footer = Vue.createApp({
        data() {
            return {
                contributors: ["박겨레", "주가희", "김지산", "권도헌"],
                github_links: ["알아서", "각자의", "링크를", "넣기"],
                blog_links: ["알아서", "각자의", "링크를", "넣기"],
            };
        },
    });
    footer.use(ElementPlus, {
        locale: ElementPlusLocaleKo,
    });
    footer.mount("#footer");
</script>
