import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000/profile"
});

export default {

profile() {
    return http.get('/profile');
}

}