import axios from 'axios';

const baseURL= "http://localhost:9000/profile";

export default {

profile() {
    return axios.get(baseURL+'/profile')
},

listFavorites(userID) {
    return axios.get(baseURL+`/favorites/${userID}/`)
},

postFavorites(userID, favorites){
    return axios.post(baseURL+`/favorites/save/${userID}/`,favorites)
}

}