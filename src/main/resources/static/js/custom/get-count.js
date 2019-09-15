(function ($) {
  'use strict';
  let data = {
    userOnLineCount: 0,
    deptCount: 0,
    userCount: 0,
    roleCount: 0
  };
  $(function () {
    axios.get('/api/data')
        .then(response => {
          if (response.data.status === 200) {
            let data = response.data.data;
            $('[data-id="userOnLineCount"]').text(data.userOnLineCount);
            $('[data-id="deptCount"]').text(data.deptCount);
            $('[data-id="userCount"]').text(data.userCount);
            $('[data-id="roleCount"]').text(data.roleCount);
          }
        })
        .catch(error => {
          console.log(error);
        });

  })
})(jQuery);