<template>
    <div class="cotntain">
        <el-container>
        <el-main>
            <el-row type="flex" justify="center" align="middle">
              <el-col :span="6">
                  学号
                  <div style="width:150px;display:inline-block;">
                      <el-input v-model="studentId" placeholder="请输入学号"></el-input>
                  </div>
              </el-col>
              <el-col :span="4"><el-button type="primary" icon="el-icon-search" @click="getData()" round plain></el-button></el-col>
            </el-row>
            <el-table :data="dataTable" height="400" border>
            <el-table-column label="学号">
                <template #default="scope">
                    <el-input v-model="scope.row.studentId" placeholder="请输入学号"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="姓名">
                <template #default="scope">
                    <el-input v-model="scope.row.studentName" placeholder="请输入姓名"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="年级">
                <template #default="scope">
                    <el-input v-model="scope.row.grade" placeholder="请输入年级"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="班级">
                <template #default="scope">
                    <el-input v-model="scope.row.studentClass" placeholder="请输入班级"></el-input>
                </template>
            </el-table-column>
            <el-table-column label="初始密码">
                <template #default="scope">
                    <el-input v-model="scope.row.password" placeholder="初始密码"></el-input>
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
        </el-main>
        
    </el-container>
    </div>
</template>

<script>
import {get, putJson, del} from '@/network/request'

export default {
    name: "ChangeStudent",
    data(){
        return {
            studentId: '',
            dataTable: [
                
            ],
}
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
    },
    
}
</script>

<style scoped>
.contian {
    width: 100%;
    height: 100%;
}
</style>