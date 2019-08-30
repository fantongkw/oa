function getCsrf(csrf) {
  axios.get('/api/csrf')
      .then(response => {
        csrf.headerName = response.data.headerName;
        csrf.token = response.data.token;
      });
}