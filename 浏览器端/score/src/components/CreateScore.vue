<template>
    <div class="cotntain">
        <el-row type="flex" justify="center" align="middle">
              <el-col :span="6">
                  课程号
                  <div style="width:150px;display:inline-block;">
                      <el-input v-model="courseId" placeholder="请输入课程号"></el-input>
                  </div>
              </el-col>
              <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="getDataByCourseId" round plain></el-button></el-col>
            </el-row>
        <el-table :data="courseTable" height="200" border>
            <el-table-column label="课程号" prop="courseId">
            </el-table-column>
            <el-table-column label="课程名" prop="courseName">
            </el-table-column>
            <el-table-column label="教师号" prop="teacherId">
            </el-table-column>
            <el-table-column label="年份" prop="year">
            </el-table-column>
            <el-table-column label="学期" prop="term">
            </el-table-column>
        </el-table>
        <el-container>
        <el-main>
            <el-row type="flex" justify="center" align="middle">
              <el-col :span="6">
                  学号
                  <div style="width:150px;display:inline-block;">
                      <el-input v-model="studentId" placeholder="请输入学号"></el-input>
                  </div>
              </el-col>
              <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="getDataByStudentId" round plain></el-button></el-col>
            </el-row>
            <el-table :data="studentTable" height="400" border>
            <el-table-column label="学号" prop="studentId">
            </el-table-column>
            <el-table-column label="姓名" prop="studentName">
            </el-table-column>
            <el-table-column label="年级" prop="grade">
            </el-table-column>
            <el-table-column label="班级" prop="studentClass">
            </el-table-column>
        </el-table>
        </el-main>
        
    </el-container>
    <p/>
        <el-row type="flex" justify="center">
          <el-col :span="6"><el-button type="danger" @click="commit">提交</el-button></el-col>
        </el-row>
    </div>
</template>

<script>
import {postJson, get} from '@/network/request'

export default {
    name: "ChangeStudent",
    data(){
        return {
            courseId: '',
            studentId: '',
            studentTable: [
                {
                    studentId: '',
                }
            ],
            courseTable: [

            ],
}
    },
    methods: {
        getDataByCourseId() {
            var params = {
                courseId: this.courseId,
            }

            var that = this;
            get("/manager/course", params).then(
                res => {
                    if(res.status == 200) {
                        that.courseTable[0] = res.data;
                    } else {
                        alert(that.courseId+ "不存在");
                    }
                }
            )
        },
        getDataByStudentId() {
            var that = this;
            get("/student/" + this.studentId).then(
                res => {
                    if (res.status == 200) {
                        that.studentTable[0] = res.data;
                    } else {
                        alert(that.studentId + "不存在");
                    }
                }
            )
        },

        commit() {
            var array = new Array();
            array[0] = {"courseId": this.courseTable[0].courseId, "teacherId": this.courseTable[0].teacherId, "studentId": this.studentTable[0].studentId};
            var json = JSON.stringify(array);
            postJson("/manager/scores", json).then(
                res => {
                    if (res.status == 200) {
                        alert("选课成功");
                    } else {
                        alert("选课失败");
                    }
                }
            )
        }
    },
    
}
</script>

<style scoped>
.contian {
    width: 100%;
    height: 100%;
}
</style>