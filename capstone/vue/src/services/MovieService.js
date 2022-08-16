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

    saveFavorites(userID, favorites) {
        return http.post(`/favorites/save/${userID}`, favorites)
    }

}