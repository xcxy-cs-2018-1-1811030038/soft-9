import axios from 'axios';
import store from '@/store'

/**
 * 为每次请求添加token
 */
axios.interceptors.request.use(config => {
    // console.log(config);
    config.headers = {
        "Authorization": store.state.token,
        'Content-Type': 'application/json;charset=UTF-8',
    }
    // console.log(config);

    return config;
}, err => {
    console.log(err)
})

////////////////////////////////////////////
//请求封装
///////////////////////////////////////////

function post(url, params) {
    return axios({
        url: '/api' + url,
        method: 'post',
        params: params,
    });
};

function get(url, params) {
    return axios({
        url: '/api' + url,
        method: 'get',
        params: params,
    })
}

function del(url, params) {
    return axios({
        url: '/api' + url,
        method: 'delete',
        params: params,
    })
}

/**
 * 发送post请求，携带json数据
 * @param {*} url 
 * @param {*} json 
 * @returns 
 */
function postJson(url, json) {
    return axios({
        url: '/api' + url,
        method: 'post',
        data: json,
        headers: {
            'Content-Type': 'application/json',
        }
    })
}

/**
 * 发送put请求，携带json数据
 * @param {*} url 
 * @param {*} json 
 * @returns 
 */
 function putJson(url, json) {
    return axios({
        url: '/api' + url,
        method: 'put',
        data: json,
        headers: {
            'Content-Type': 'application/json',
        }
    })
}

export {post, get, del, postJson, putJson}