<template>
    <div class="header">
        <el-row type="flex" align="middle" class="el-row1">
            <el-col :span="1" :push="1" :class="tabs[0]" ><span @click="checkTab(0)"><router-link to="/main/manager/managerInput">录入</router-link></span></el-col>
            <el-col :span="1" :push="1" :class="tabs[1]" ><span @click="checkTab(1)"><router-link to="/main/manager/managerChange">修改</router-link></span></el-col>
            <el-col :span="1" :push="1" :class="tabs[2]" ><span @click="checkTab(2)"><router-link to="/main/manager/authority">权限</router-link></span></el-col>
        </el-row>
    </div>
    <router-view/>
</template>

<script>


export default {
    name: "Teacher",
    data(){
        return {
            tabs: [
                'el-col1',
                '',
                '',
            ],
        }
    },
    methods: {
        checkClass(studentClass) {
            this.studentClass = studentClass;
        },
        checkGrade(grade) {
            this.grade = grade;
        },
        getData() {
            instance.get("/student/100001/scores")
                .then(res => console.log(JSON.stringify(res.data)));
        },
        checkTab(num) {
          this.tabs[0] = "";
          this.tabs[1] = "";
          this.tabs[2] = "";
          this.tabs[num] = "el-col1";
      }
    }
}
</script>

<style scoped>
.header {
    height: 30px;
    width: 100%;
    background: #DCDFE6;
    overflow: hidden;
}

.el-row1 {
    height: 30px;
    text-align: center;
    line-height: 30px;
    font-size: 16px;
    font-family: "黑体";
    height: 30px;
}

.el-col1 {
    background: white;
}

a {
    text-decoration: none;
    font-weight: bold;
    color: #2c3e50;
}
    
</style>