import axios from 'axios';

const baseURL= "http://localhost:9000/profile";

export default {

profile(userId) {
    return axios.get(baseURL+`/get/${userId}`)
},

createProfile(userId){
    return axios.get(baseURL+`/create/${userId}`)
},

updateProfile(profile){
    return axios.post(baseURL+`/update/`,profile)
}
}