import axios from 'axios';

export default {
    
    flickfinder() {
        return axios.get('/flickfinder');
    }
}