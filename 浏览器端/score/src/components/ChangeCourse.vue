<template>
    <div class="contain">
        <el-row type="flex" justify="center" align="middle">
              <el-col :span="6">
                  课程号
                  <div style="width:150px;display:inline-block;">
                      <el-input v-model="courseId" placeholder="请输入学号"></el-input>
                  </div>
              </el-col>
              <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="getData" round plain></el-button></el-col>
            </el-row>
        <el-table :data="dataTable" height="200" border>
            <el-table-column label="课程号">
                <template #default="scope">
                    <el-input v-model="scope.row.courseId" placeholder="请输入课程号"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="课程名">
                <template #default="scope">
                    <el-input v-model="scope.row.courseName" placeholder="请输入课程名"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="教师号">
                <template #default="scope">
                    <el-input v-model="scope.row.teacherId" placeholder="请输入教师号"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="年份">
                <template #default="scope">
                    <el-input v-model="scope.row.year" placeholder="请输入年份"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="学期">
                <template #default="scope">
                    <el-input v-model="scope.row.term" placeholder="学期"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="修改" width="80">
                <template #default="scope">
                    <el-button type="primary" size="mini" @click="update(scope.$index)">修改</el-button>
                </template>
            </el-table-column>
            <el-table-column label="删除" width="80">
                <template #default="scope">
                    <el-button type="danger" size="mini" @click="del(scope.$index)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    name: "CreateStudent",
    data() {
        return {
            courseId: '',
            dataTable: [
                
            ]
        };
    },
    methods: {
        getData() {
            var that = this;
            get("/student/" + that.studentId)
                .then(
                    res => {
                        if (res.status == 200) {
                            that.dataTable = [];
                            that.dataTable.splice(0,0, res.data);
                        } else {
                            alert(that.studentId+"不存在")
                        }
                    }
                )

                console.log(that.dataTable);
        },
        update(index) {
            var json = JSON.stringify(this.dataTable);
            console.log(json);
            putJson('/manager/students', json).then(
                res => {
                    console.log(res);
                    if(res.status == 200) {
                        alert("修改成功")
                    } else {
                        alert("修改失败");
                    }
                }
            )
        },
        del(index) {
            var that = this;

            var params = {
                studentId: this.studentId,
            }

            del("/manager/student", params).then(
                res => {
                    if (res.status == 200) {
                        that.dataTable = [];
                        alert("删除成功");
                    } else {
                        alert("删除失败");
                    }
                }
            )
        }
    }
}
</script>

<style scoped>
.contain {
    margin-top: 100px;
}
</style>