import httpMethod from './httpMethod'
export default {
  login(data) {
    return httpMethod({
      url: '/users/login',
      data,
      method: 'POST'
    })
  }
}
