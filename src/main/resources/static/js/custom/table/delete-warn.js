(function ($) {
  'use strict';
  $(function () {
    let csrf = {
      headerName: "",
      token: ""
    };
    getCsrf(csrf);
    $(document).on('click', '.btn-outline-danger', function () {
      let eleType = this.name;
      let eleId = this.value;
      swal({
        title: '你确定吗?',
        text: '你将无法恢复！',
        icon: 'warning',
        buttons: {
          cancel: {
            text: '取消',
            value: null,
            visible: true,
            className: 'btn btn-outline-secondary',
            closeModal: true,
          },
          confirm: {
            text: '确定',
            value: true,
            visible: true,
            className: 'btn btn-outline-danger',
            closeModal: true
          }
        }
      }).then(value => {
            if (value) {
              if (eleType === null || eleId === null) return;
              let url = '/' + eleType + '/' + eleType + '_delete/' + eleId;
              $.ajax({
                url: url,
                type: 'post',
                dataType: "json",
                beforeSend: (xhr) => {
                  xhr.setRequestHeader(csrf.headerName, csrf.token);
                },
                success: function (response) {
                  if (response) {
                    swal({
                      title: '删除成功',
                      icon: 'success',
                      button: {
                        text: '刷新',
                        value: true,
                        visible: true,
                        className: 'btn btn-outline-success'
                      }
                    }).then((ok) => {
                      if (ok) {
                        window.location.reload();
                      }
                    });
                  } else {
                    swal({
                      title: '删除失败',
                      icon: 'error',
                      button: {
                        text: '请稍后再试',
                        value: true,
                        visible: true,
                        className: 'btn btn-outline-danger'
                      }
                    });
                  }
                }
              });
            }
          }
      ).catch(err => {
        if (err) {
          swal({
            title: '发生错误',
            text: 'Ajax请求失败！',
            icon: 'error',
            button: {
              text: "继续",
              className: 'btn btn-outline-danger'
            }
          });
        } else {
          swal.stopLoading();
          swal.close();
        }
      })
    });
  });
})(jQuery);
