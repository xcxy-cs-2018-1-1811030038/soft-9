<template>
    <div class="header">
        <el-row type="flex" justify="space-around" align="middle" >
            <el-col :span="2">
                <el-dropdown>
                    <el-button type="success" icon="el-icon-user" circle plain></el-button>
                    <template #dropdown>
                        <el-card shadow="always">
                            <div class="card">
                                <el-row>
                                  <el-col :span="12">{{judge()}}</el-col>
                                  <el-col :span="12">{{$store.state.id}}</el-col>
                                </el-row>
                                <el-row>
                                  <el-col :span="12">姓名:</el-col>
                                  <el-col :span="12">{{$store.state.name}}</el-col>
                                </el-row>
                            </div>
                        </el-card>
                    </template>
                </el-dropdown>
            </el-col>
            <el-col :span="2"><el-button type="danger" @click="logout" plain>退出登录</el-button></el-col>
        </el-row>
    </div>
    <router-link to="/main/student"></router-link>
    <router-link to="/main/teacher"></router-link>
    <router-link to="/main/manager"></router-link>
    <router-view />
</template>

<script>
export default {
    name: 'Main',
    methods: {
        judge() {
            let userType = this.$store.state.userType;
            if(userType == "student") {
                return "学号:";
            } else if (userType == "teacher") {
                return "教师号:";
            } else if (userType == "manager"){
                return "管理员号:";
            } else {
                return '';
            }
        },
        logout() {
            this.$store.state.token = '';
            this.$router.push("/");
        }
    }
}
</script>

<style scoped>
.header {
    height: 60px;
    width: 100%;
    background: #409EFF;
}

.el-row {
    height: 60px;
}

.card {
    width: 100px;
    height: 100px;
}
</style>