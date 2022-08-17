import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000/profile"
});

export default {

profile() {
    return http.get('/profile')
},

listFavorites(userID) {
    return http.get(`/favorites/save/${userID}/`)
},

postFavorites(userID, favorites){
    return http.post(`/favorites/save/${userID}/`,favorites)
}

}