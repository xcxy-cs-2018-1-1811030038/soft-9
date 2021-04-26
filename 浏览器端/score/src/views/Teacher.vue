<template>
    <div class="header">
        <el-row type="flex" align="middle" class="el-row1">
            <el-col :span="1" :push="1" :class="tabs[0]" @click="checkTab(0)"><span>录入</span></el-col>
            <el-col :span="1" :push="1" :class="tabs[1]" @click="checkTab(1)"><span>修改</span></el-col>
        </el-row>
    </div>
    <teacher-input :class="show[0]" />
    <teacher-change :class="show[1]" />

        
</template>

<script>
import TeacherChange from '../components/TeacherChange.vue';
import TeacherInput from '../components/TeacherInput.vue';
export default {
    name: "Teacher",
    components: {
        TeacherInput,
        TeacherChange
    },
    data(){
        return {
            grade: 2018,
            studentClass: 1,
            courseId: '',
            tableData: [
                {
                    year: '1',
                    term: '2',
                    courseName: '3',
                    courseId: '4',
                    score: '15',
                },
            ],
            tabs: [
                'el-col1',
                '',
            ],
            show: [
                "show",
                "hide"
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
          this.tabs[num] = "el-col1";
          this.show[0] = "hide";
          this.show[1] = "hide";
          this.show[num] = "show";
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

.hide {
    display: none;
}

.show {
    display: block;
}
</style>