<template>
    <div class="contain">
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
            <el-table-column label="增添" width="80">
                <template #default="scope">
                    <el-button type="primary" size="mini" @click="add(scope.$index)">+</el-button>
                </template>
            </el-table-column>
            <el-table-column label="删除" width="80">
                <template #default="scope">
                    <el-button type="primary" size="mini" @click="del(scope.$index)">-</el-button>
                </template>
            </el-table-column>
        </el-table>
        <p/>
        <el-row type="flex" justify="center">
          <el-col :span="6"><el-button type="danger" @click="commited">提交</el-button></el-col>
        </el-row>
    </div>
</template>

<script>
import {postJson} from '@/network/request'

export default {
    name: "CreateCourse",
    data() {
        return {
            dataTable: [
                {
                    
                }
            ]
        };
    },
    methods: {
        commited() {
            // console.log(row);
            //转换为json字符串
            var json = JSON.stringify(this.dataTable);
            
            var that = this;

            postJson('/manager/courses', json).then(
                res => {
                    if(res.status == 200) {
                        that.dataTable = [];
                        alert("创建成功");
                    } else {
                        alert("创建失败");
                    }
                }
            )
            
        },

        // 新增一行
        add(index) {
            this.dataTable.splice(index+1, 0, {});
        },
        // 删除一行
        del(index) {
            this.dataTable.splice(index, 1);
        }
    }
}
</script>

<style scoped>
.contain {
    margin-top: 100px;
}
</style>