import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000/movies"
    
});

export default {

    
    flickfinder() {
        return http.get('/flickFinder');
    },

    listByActor(personID) {
        return http.get(`/actor/${personID}`);
    },

    listByGenre(genreName) {
        return http.get(`/genre/${genreName}`);
    },

    listByDirector(directorName) {
        return http.get(`/director/${directorName}`)
    },

    listByTitle(title) {
        return http.get(`/title/${title}`)
    },

    listRandom() {
        return http.get(`/random`)
    },

    listRecommendations(userID, swipes) {
        return http.post(`/recommendations/${userID}/`, swipes)
    },

<<<<<<< HEAD
    saveFavorites(userID) {
        return http.post(`/flickfinder/save/${userID}`)
    }
=======
>>>>>>> 9da62a51fb28a287fcf2d7ef63d0487f8d6466e3
}