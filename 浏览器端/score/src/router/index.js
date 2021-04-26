import { createRouter, createWebHistory } from 'vue-router'

const Login = () => import('@/views/Login.vue');
const Main = () => import('@/views/Main.vue');
const Student = () => import('@/views/Student.vue');
const Teacher = () => import('@/views/Teacher.vue');
const Manager = () => import('@/views/Manager.vue');
const ManagerChange = () => import('@/components/ManagerChange.vue');
const ManagerInput = () => import('@/components/ManagerInput.vue');
const Authority = () => import('@/components/Authority.vue');

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/main',
    name: 'Main',
    component: Main,
    children: [
      {
        path: 'student',
        component: Student,
      },
      {
        path: 'teacher',
        component: Teacher
      },
      {
        path: 'manager',
        component: Manager,
        children: [
          {
            path: '',
            component: ManagerInput,
          },{
            path: 'managerInput',
            component: ManagerInput,
          },{
            path: 'managerChange',
            component: ManagerChange,
          },{
            path: 'authority',
            component: Authority,
          },
        ]
      },
    ]
  },
  // {
  //   path: '/student',
  //   name: 'Student',
  //   component: Student
  // },
  
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
