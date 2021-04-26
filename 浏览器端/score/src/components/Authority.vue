<template>
    <el-container class="el-container1">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu :default-openeds="['增加', '课程']" default-active="1-1" @select="t">
                <el-submenu index="增加">
                    <template #title>增加</template>
                    <el-menu-item index="增加学生" >学生</el-menu-item>
                    <el-menu-item index="增加教师" >教师</el-menu-item>
                </el-submenu>
                <el-submenu index="修改">
                    <template #title>修改</template>
                    <el-menu-item index="修改学生">学生</el-menu-item>
                    <el-menu-item index="修改教师">教师</el-menu-item>
                </el-submenu>
                <el-submenu index="课程">
                    <template #title>课程</template>
                    <el-menu-item index="课程创建">创建</el-menu-item>
                    <el-menu-item index="课程修改">修改</el-menu-item>
                    <el-submenu index="课程学生">
                        <template #title>学生</template>
                        <el-menu-item index="课程学生添加">添加</el-menu-item>
                        <el-menu-item index="课程学生修改">修改</el-menu-item>
                    </el-submenu>
                </el-submenu>
            </el-menu>
        </el-aside>

        <el-container>
            <el-header style="text-align: center; font-size: 24px">
                <span>{{msg}}</span>
            </el-header>

            <el-main style="height: 500px">
                <CreateStudent :class="show['增加学生']"/>
                <CreateTeacher :class="show['增加教师']"/>
                <ChangeStudent :class="show['修改学生']"/>
                <ChangeTeacher :class="show['修改教师']"/>
                <CreateCourse :class="show['课程创建']"/>
                <ChangeCourse :class="show['课程修改']"/>
                <CreateScore :class="show['课程学生添加']"/>
                <ChangeScore :class="show['课程学生修改']"/>
            </el-main>
        </el-container>
    </el-container>
</template>


<script>
import CreateStudent from './CreateStudent.vue'
import CreateTeacher from './CreateTeacher.vue'
import ChangeStudent from './ChangeStudent.vue'
import ChangeTeacher from './ChangeTeacher.vue'
import CreateCourse from './CreateCourse.vue'
import ChangeCourse from './ChangeCourse.vue'
import CreateScore from './CreateScore.vue'
import ChangeScore from './ChangeScore.vue'
export default {
  components: { CreateStudent, CreateTeacher, ChangeStudent, ChangeTeacher, CreateCourse, ChangeCourse, CreateScore, ChangeScore },
      name: "Authority",
      data() {
          return {
              msg: "增加学生",
              show: {
                  "增加学生": "show",
                  "增加教师": "hide",
                  "修改学生": "hide",
                  "修改教师": "hide",
                  "课程创建": "hide",
                  "课程修改": "hide",
                  "课程学生添加": "hide",
                  "课程学生修改": "hide",
              } 
          }
      },
    methods: {
        t(index) {
            this.msg = index;
            this.show['增加学生'] = "hide";
            this.show['增加教师'] = "hide";
            this.show['修改学生'] = "hide";
            this.show['修改教师'] = "hide";
            this.show['课程创建'] = "hide";
            this.show['课程修改'] = "hide";
            this.show['课程学生添加'] = "hide";
            this.show['课程学生修改'] = "hide";
            this.show[index] = "show";
        }
     }
  }
</script>

<style scoped>
.el-container1 {
    width: 80%;
    height: 500px;
    margin: auto;
    margin-top: 20px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
}

.el-aside {
    color: #333;
}

.show {
    display: block;
}

.hide {
    display: none;
}
</style>