<template>
    <el-container>
        <el-main>
            <el-row type="flex" justify="center" align="middle">
              <el-col :span="6">
                  学号
                  <div class="course-div">
                      <el-input v-model="studentId" placeholder="请输入学号"></el-input>
                  </div>
              </el-col>
              <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="getDataById" round plain></el-button></el-col>
            </el-row>
            <div class="table">
                <el-table :data="tableData" border height="550" empty-text="暂无成绩">
                  <el-table-column label="学号" prop="studentId"></el-table-column>
                  <el-table-column label="姓名" prop="studentName"></el-table-column>
                  <el-table-column label="年份" prop="year"></el-table-column>
                  <el-table-column label="学期" prop="term"></el-table-column>
                  <el-table-column label="课程" prop="courseName"></el-table-column>
                  <el-table-column label="课程号" prop="courseId"></el-table-column>
                  <el-table-column label="成绩">
                        <template #default="scope">
                            <el-input v-model="scope.row.score" placeholder="请输入成绩"></el-input>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-main>
        <el-footer>
            <el-row type="flex" justify="center" align="middle">
                <el-col :span="4">
                    <span>年级</span>
                    <el-dropdown trigger="click">
                        <el-button v-model="grade" >
                            {{grade}}<i class="el-icon-arrow-down el-icon--right"></i>
                        </el-button>
                        <template #dropdown>
                            <el-dropdown-menu>
                            <el-dropdown-item @click="checkGrade(2017)">2017</el-dropdown-item>
                            <el-dropdown-item @click="checkGrade(2018)">2018</el-dropdown-item>
                            <el-dropdown-item @click="checkGrade(2019)">2019</el-dropdown-item>
                            <el-dropdown-item @click="checkGrade(2020)">2020</el-dropdown-item>
                            <el-dropdown-item @click="checkGrade(2021)">2021</el-dropdown-item>
                            <el-dropdown-item @click="checkGrade(2022)">2022</el-dropdown-item>
                            <el-dropdown-item @click="checkGrade(2023)">2023</el-dropdown-item>
                            <el-dropdown-item @click="checkGrade(2024)">2024</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </el-col>
                <el-col :span="4">
                    <span>班级</span>
                    <el-dropdown trigger="click">
                        <el-button v-model="studentClass" >
                            {{studentClass}}<i class="el-icon-arrow-down el-icon--right"></i>
                        </el-button>
                        <template #dropdown>
                            <el-dropdown-menu>
                            <el-dropdown-item @click="checkClass(1)">1</el-dropdown-item>
                            <el-dropdown-item @click="checkClass(2)">2</el-dropdown-item>
                            <el-dropdown-item @click="checkClass(3)">3</el-dropdown-item>
                            <el-dropdown-item @click="checkClass(4)">4</el-dropdown-item>
                            <el-dropdown-item @click="checkClass(5)">5</el-dropdown-item>
                            <el-dropdown-item @click="checkClass(6)">6</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </el-col>
                <el-col :span="6">
                    课程号
                    <div class="course-div">
                        <el-input v-model="courseId" placeholder="请输入课程号" prefix-icon="el-icon-search"></el-input>
                    </div>
                </el-col>
                <el-col :span="3"><el-button type="primary" @click="getData">查询</el-button></el-col>
                <el-col :span="2"><el-button type="danger" @click="commit" plain>录入</el-button></el-col>
            </el-row>
        </el-footer>
    </el-container>
</template>

<script>
import {get, putJson} from '@/network/request';
export default {
    name: "TeacherInput",
    data(){
        return {
            grade: 2018,
            studentClass: 1,
            courseId: '',
            studentId: '',
            tableData: [
                
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
        getDataById() {
            var that = this;
            get('/student/' + that.studentId +'/scores').then(
                res => {
                    if (res.status == 200) {
                        that.tableData = res.data;
                    } else {
                        alert("学号不存在");
                    }
                }
            )
        }
        ,
        getData() {
            let that = this;
            if(that.courseId == '') {
                alert("课程号不能为空");
                return;
            }
            var params = {
                courseId: that.courseId,
            }
            if(that.grade != null) {
                
                // 设置参数
                params = {
                    courseId: that.courseId,
                    grade: that.grade,
                    studentClass: that.studentClass,
                };
            }
            console.log(params);
            get('/teacher/' + this.$store.state.id +'/scores', params)
                .then(
                    res => {
                        console.log(res);
                        console.log(res.data);
                        let scores = res.data.map(
                            item => {
                                if(item.score == -1) {
                                    item.score = '——';
                                }
                                return item;
                            }
                        )
                        console.log(scores);
                        that.tableData = scores;
                    }
                )
        },
        commit() {
            var json = JSON.stringify(this.tableData);
            console.log(json);
            putJson('/teacher/scores', json).then(
                res => {
                    console.log(res);
                    if(res.status == 200) {
                        alert("录入成功")
                    } else {
                        alert("录入失败");
                    }
                }
            )
        }
    }
}
</script>

<style scoped>

.el-container {
    width: 80%;
    margin: auto;
    margin-top: 20px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.table {
    width: 80%;
    height: 600px;
    margin: auto;
    margin-top: 50px;
}

.el-table {
    text-align: center;
}

.el-input1 {
  border: none;
}

.course-div {
    display: inline-block;
    width: 150px;
}
</style>