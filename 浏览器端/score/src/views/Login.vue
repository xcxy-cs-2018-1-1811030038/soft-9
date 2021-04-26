<template>
    <div class="login">        
        <el-form label-position="right" label-width="50px" :model="userForm" class="login-card">
            <el-form-item>
                <el-radio-group v-model="userForm.userType">
                <el-radio label="student">学生</el-radio>
                <el-radio label="teacher">教师</el-radio>
                <el-radio label="manager">教学管理员</el-radio>
            </el-radio-group>
            </el-form-item>
            <el-form-item label="账号">
                 <el-input typr="text" v-model="userForm.account" placeholder="请输入账号" maxlength="9" required></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input type="password" v-model="userForm.password"  placeholder="请输入密码" minlength="6" maxlength="26" required></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login">登录</el-button>
                <el-link type="danger" :underline="false" style="margin-left: 200px">忘记密码</el-link>
            </el-form-item>
         </el-form>
     </div>
</template>

<script>
import {post} from '@/network/request';

export default {
    name: "login",
    data() {
        return {
            userForm: {
                account: '',
                password: '',
                userType: "student",
            },
        }
    },
    methods: {
        login() {
            // 在发送请求后，this代表promise对象
            // 将this赋值给that，用that来调用vue对象
            const that = this;

            post('/login/'+this.userForm.userType, this.userForm).then(
                (res) => {
                    let data = res.data;
                    if(res.status == 200){
                        that.$store.commit("setToken", data.tokenHead + " " + data.token);
                        that.$store.commit("setUserType", that.userForm.userType);
                        that.$store.commit("setName", data.user.username);
                        that.$store.commit("setId", data.user.account);
                        if(that.userForm.userType == "student") {
                            that.$router.push("/main/student");
                        } else if (that.userForm.userType == "teacher") {
                            that.$router.push("/main/teacher");
                        } else if (that.userForm.userType == "manager") {
                            that.$router.push("/main/manager");
                        }
                    }
                    // console.log(res);
                    // console.log(that.$store.state.token);
                }
            )
        },
    }
}
</script>

<style scoped>
.login {
    width: 450px;
    height: 300px;
    margin: 150px auto;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.login-card {
    margin: 20px;
    padding-top: 50px;
}
</style>