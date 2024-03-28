import axios from 'axios'
console.log(import.meta.env.VITE_APP_ENV)
const requestConfig = (repName, config = {}) => {
  const envUrl = {
    development: {//定義後端接口路經
      apiBase: `/api`
    },
    sit: {//引入後端服務地址和接口
      apiBase: `${import.meta.env.VITE_APP_AXIOS_BASE_URL}:${import.meta.env.VITE_APP_APIPORT}`
    }
  }
  
  const repList = {//獲取對應環境配置訊息
    ...envUrl[import.meta.env.VITE_APP_ENV]
  }
  const params = {
    baseURL: repList[repName],
    ValidityState: (status) => status >= 200 && status < 500,//判斷http狀態是否有效
    ...config
  }
  return params
}
const service = {
  apiBase: axios.create(requestConfig('apiBase'))
}
export const responseHandler = (response) => {//判斷http status
  console.log('responnse status:' + response.status)
  if (response.status !== 200) {
    console.log('http response not 200:', response)
  }
  if (response.status === 401) {
    // this.$router.push("/login");
  }
  return response
}
export const errorHandler = (error) => {
  console.log('errorHandler:', error)
}
export const requestHandler = (request) => {
  // console.log(request)
  // 檢查是否有 token，有的話就加到 headers 內
  const token = localStorage.getItem('token')
  if (token) {
    request.headers['Authorization'] = `Bearer ${token}`
  } else {
    // throw "no token exception";
    // console.log('no token exception')
  }
}
// request interceptor
service.apiBase.interceptors.request.use(
  (request) => {
    requestHandler(request)
    return request
  },
  (error) => {
    errorHandler(error)
    return Promise.reject(error)
  }
)
// response interceptor
service.apiBase.interceptors.response.use(
  (response) => {
    responseHandler(response)
    return response
  },
  (error) => {
    errorHandler(error)
    return Promise.reject(error)
  }
)
export default service
