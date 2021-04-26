<template>
    <div class="contain">
        <el-table :data="dataTable" height="200" border>
            <el-table-column label="教师号">
                <template #default="scope">
                    <el-input v-model="scope.row.studentId" placeholder="请输入教师号"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="姓名">
                <template #default="scope">
                    <el-input v-model="scope.row.studnetName" placeholder="请输入姓名"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="初始密码">
                <template #default="scope">
                    <el-input v-model="scope.row.password" placeholder="初始密码"></el-input>
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
import {postJson} from '@/network/request';

export default {
    name: "CreateTeacher",
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

            postJson('/manager/teachers', json).then(
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