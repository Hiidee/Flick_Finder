import axios from 'axios';

const http = axios.create({
    baseURL: "http://www.omdbapi.com/?apikey=5a8ac6e3&t=",
    
});
delete http.defaults.headers.common['Authorization'];

export default {
    getRatings(title) {
        return http.get(`${title}`)
    }
}