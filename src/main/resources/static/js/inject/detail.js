const app = new Vue({
  el: '#app',
  data () {
    return {
      username: "default",
      role: "default",
      profilePicture: '/images/faces-clipart/default.jpg'
    }
  },
  mounted () {
    axios
      .get('/api/user')
      .then(response => {
        if (response.data.data != null) {
          this.username = response.data.data.username;
          this.role = response.data.data.role.name;
          this.profilePicture = response.data.data.profilePicture
        }
      })
  }
});
