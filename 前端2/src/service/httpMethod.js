import request from './request';
const httpHandler = (service) => {
    const requestService = service;
    // console.log(localStorage.getItem("token"));
    return async function httpMethod({
        method = "POST",
        url,
        data,
        params,
        headers = {
            "Content-Type": "application/json",
            // "Authorization": `Bearer ${localStorage.getItem("token")}`
        }
    }) {
        const response = await requestService({
            method,
            url,
            data,
            params,
            headers 
        });
        return response;
    }

}

const httpMethod = httpHandler(request["apiBase"]);
export default httpMethod;