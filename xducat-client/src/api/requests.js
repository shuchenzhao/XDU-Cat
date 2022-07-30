// 对axios进行二次封装
import axios from 'axios'

// 创建一个axios实例（？
const requests = axios.create({
    // 设置发送请求的baseURL
    // baseURL: '/api',
    // 隔了5s还没收到响应就认为请求超时
    timeout: 5000,
})

// 请求拦截器
requests.interceptors.request.use((config) => {
    // config是一个配置对象,该对象里有请求头headers
    return config
})

// 响应拦截器
requests.interceptors.response.use((res) => {
    // 响应成功的回调函数
    return res.data
}, (error) => {
    // 响应失败的回调函数
    return Promise.reject(new Error(error))
})

export default requests