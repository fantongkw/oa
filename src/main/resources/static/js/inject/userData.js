
const app = new Vue({
  el: '#app',
  props: {
    username: String,
    role: String,
    avatar: String,
    display: Boolean
  },
  data: function () {
    return {
      username: '',
      role: '',
      avatar: '',
      display: false
    }
  },
  mounted () {
    axios.get('/api/user')
        .then(response => {
          if (response.data.status === 200) {
            this.username = response.data.data.username;
            this.role = response.data.data.role;
            this.avatar = response.data.data.avatar;
            this.display = true
          }
        })
        .catch(function (error) {
          console.log(error);
        });
  }
});

