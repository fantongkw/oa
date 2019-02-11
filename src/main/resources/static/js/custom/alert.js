(function($) {
  $(function () {
    $('.btn-outline-danger').on('click', function () {
      let eleType = this.name;
      let eleId = this.value;
      swal({
        title: '你确定吗?',
        text: "你将无法恢复这个！",
        icon: 'warning',
        buttons: {
          cancel: {
            text: "取消",
            value: null,
            visible: true,
            className: "btn btn-secondary",
            closeModal: true,
          },
          confirm: {
            text: "确定",
            value: true,
            visible: true,
            className: "btn btn-danger",
            closeModal: true
          }
        }
      }).then(value => {
        if (value) {
          if (eleType === null && eleId === null) throw null;
          let url = '/' + eleType + '/'+ eleType + '_delete/'+ eleId;
          axios
              .get(url)
              .then(response => {
                if (response.status === 200) {
                  swal({
                    title: '删除成功',
                    icon: 'success',
                    button: {
                      text: "继续",
                      value: true,
                      visible: true,
                      className: "btn btn-primary"
                    }
                  }).then(value1 => {
                    if (value1) {
                      window.location.reload();
                    }
                  });
                } else {
                  swal({
                    title: '删除失败',
                    icon: 'error',
                    button: {
                      text: "请稍后再试",
                      value: true,
                      visible: true,
                      className: "btn btn-primary"
                    }
                  });
                }
              })
          }
        }
      ).catch(err => {
        if (err) {
          swal("Oh noes!", "The AJAX request failed!", "error");
        } else {
          swal.stopLoading();
          swal.close();
        }
      })
    })
  });
})(jQuery);
