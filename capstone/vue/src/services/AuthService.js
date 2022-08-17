import axios from 'axios';
import ProfileService from './ProfileService';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    ProfileService.createProfile(this.$store.state.user.id)
    return axios.post('/register', user)
  }

}
