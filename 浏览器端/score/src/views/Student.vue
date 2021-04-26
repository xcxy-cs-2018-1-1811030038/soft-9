<template>
    <div class="header">
        <el-row type="flex" align="middle" class="el-row1">
            <el-col :span="1" :push="1" class="el-col1"><span>查询</span></el-col>
        </el-row>
    </div>
    <el-container>
        <el-main>
            <div class="table">
                <el-table :data="tableData" border height="550" empty-text="暂无成绩">
                  <el-table-column label="年份" prop="year"></el-table-column>
                  <el-table-column label="学期" prop="term"></el-table-column>
                  <el-table-column label="课程" prop="courseName"></el-table-column>
                  <el-table-column label="课程号" prop="courseId"></el-table-column>
                  <el-table-column label="成绩" prop="score"></el-table-column>
                </el-table>
            </div>
        </el-main>
        <el-footer>
            <el-row type="flex" justify="center" align="middle">
                <el-col :span="6">
                    <span>学年</span>
                    <el-date-picker
                        v-model="year"
                        type="year"
                        placeholder="选择年"
                        size="medium">
                    </el-date-picker>
                </el-col>
                <el-col :span="4">
                    <span>学期</span>
                    <el-dropdown trigger="click">
                        <el-button v-model="term" >
                            {{term}}<i class="el-icon-arrow-down el-icon--right"></i>
                        </el-button>
                        <template #dropdown>
                            <el-dropdown-menu>
                            <el-dropdown-item @click="checkTerm(1)">1</el-dropdown-item>
                            <el-dropdown-item @click="checkTerm(2)">2</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </el-col>
                <el-col :span="4"><el-button type="primary" @click="getData">查询</el-button></el-col>
            </el-row>
        </el-footer>

    </el-container>

        
</template>

<script>
import {get} from '@/network/request';

export default {
    name: "Student",
    data(){
        return {
            year: new Date(),
            term: 1,
            tableData: [
                
            ],
        }
    },
    methods: {
        checkTerm(term) {
            this.term = term;
        },
        getData() {
            let that = this;
            if(that.year != null) {
                var year = that.year.getFullYear();
                var params = {
                    year,
                    term: that.term,
                };
            }
            console.log(params);
            get('/student/' + this.$store.state.id +'/scores', params)
                .then(
                    res => {
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
}

.el-col1 {
    text-align: center;
    line-height: 30px;
    font-size: 16px;
    font-family: "黑体";
    height: 30px;
    background: white;
}

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
</style>