import axios from 'axios';

const baseURL = "http://localhost:9000/movies";

export default {

    
    flickfinder() {
        return axios.get(baseURL+'/flickFinder');
    },

    listByActor(personID) {
        return axios.get(baseURL+`/actor/${personID}`);
    },

    listByGenre(genreName) {
        return axios.get(baseURL+`/genre/${genreName}`);
    },

    listByDirector(directorName) {
        return axios.get(baseURL+`/director/${directorName}`)
    },

    listByTitle(title) {
        return axios.get(baseURL+`/title/${title}`)
    },

    listRandom() {
        return axios.get(baseURL+`/random`)
    },

    listRecommendations(userID, swipes) {
        return axios.post(baseURL+`/recommendations/${userID}/`, swipes)
    },

    saveFavorites(userID, favorites) {
        return axios.post(baseURL+`/favorites/save/${userID}/`, favorites)
    },

    listFavorites(userID) {
        return axios.get(baseURL+`/favorites/${userID}/`)
    },
    addMovie(movie) {
        return axios.post(baseURL+`/add`, movie)
    }

    
       
    }

