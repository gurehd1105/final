<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<div id="footer" class="border-t p-10 bg-gray-50" v-cloak>
    <el-footer class="!h-auto">
        <div
            class="flex flex-col space-y-6 items-center lg:flex-row lg:justify-between lg:space-y-0"
        >
            <img class="w-fit h-fit" src="/goodee_logo.png" />
            <div
                class="flex flex-col space-y-6 lg:flex-row lg:space-x-10 lg:space-y-0"
            >
                <el-link href="/employee/login" type="info" class="h-fit"
                    >관리자 로그인</el-link
                >

                <div class="flex flex-col space-y-6 lg:space-y-8">
                    <el-text size="large" tag="b">참여자</el-text>
                    <div
                        class="flex flex-row space-x-6 lg:flex-col lg:space-y-4 lg:space-x-0"
                    >
                        <el-text
                            class="hidden lg:block"
                            v-for="name of contributors"
                        >
                            {{ name }}
                        </el-text>

                        <el-link
                            class="block lg:hidden !text-blue-500"
                            v-for="(name, idx) of contributors"
                            :href="github_links[idx]"
                        >
                            {{ name }}
                        </el-link>
                    </div>
                </div>

                <div class="flex flex-col space-y-8 hidden lg:block">
                    <el-text size="large" tag="b">GitHub</el-text>
                    <div class="flex flex-col space-y-4">
                        <el-text
                            class="!self-start"
                            v-for="url of github_links"
                        >
                            <el-link
                                v-bind:href="url"
                                target="_blank"
                                type="primary"
                                >{{ url }}</el-link
                            >
                        </el-text>
                    </div>
                </div>

                <!-- <div class="flex flex-col space-y-8">
                    <el-text size="large" tag="b">몰라</el-text>
                    <div class="flex flex-col space-y-4">
                        <el-text v-for="url of blog_links">
                            <el-link
                                v-bind:href="url"
                                target="_blank"
                                type="primary"
                                >{{ url }}</el-link
                            >
                        </el-text>
                    </div>
                </div> -->
            </div>
        </div>
    </el-footer>
</div>
<script>
    const footer = Vue.createApp({
        data() {
            return {
                contributors: ["박겨레", "주가희", "권도헌", "김지산"],
                github_links: [
                    "https://github.com/Park8374",
                    "https://github.com/joogahee",
                    "https://github.com/gurehd1105",
                    "https://github.com/jssjssj",
                ],
                blog_links: ["알아서", "각자의", "링크를", "넣기"],
            };
        },
    });
    footer.use(ElementPlus, {
        locale: ElementPlusLocaleKo,
    });
    footer.mount("#footer");
</script>
