(function($) {
  'use strict';

  let noticeListItem = $('.notice-list');
  let noticeListInput = $('.notice-list-input');

  let csrf = {
    headerName: "",
    token: ""
  };

  function noticeTemplate(notice) {
    noticeListItem.append("<li><div class='notice-title'><label class='form-check-label'><i class='notice-icon mdi mdi-message-text-outline'></i>" + notice.title + "</label></div><div class='notice-time'>"+ new Date(notice.created).toLocaleString() +"</div><i data-id='"+ notice.id +"' class='remove mdi mdi-close-circle-outline'></i></li>");
  }

  function getId() {
    let s = [];
    let hexDigits = "0123456789abcdef";
    for (let i = 0; i < 36; i++) {
      s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
    s[8] = s[13] = s[18] = s[23] = "-";
    return s.join("");
  }

  $(function() {
    getCsrf(csrf);
    $.ajax({
      url: "/notice/list",
      type: "get",
      dataType: "json",
      success: function (response) {
        for (let i = 0; i < response.length; i++) {
          noticeTemplate(response[i]);
        }
      }
    });

    $('.notice-list-add-btn').on("click", function(event) {
      event.preventDefault();
      let item = $(this).prevAll('.notice-list-input').val();
      if (item) {
        let notice = {
          id: getId(),
          type: "warn",
          username: $('.profile-name').text(),
          title: item,
          created: new Date().getTime()
        };
        $.ajax({
          url: "/notice/add",
          type: "post",
          data: {id: notice.id, type: notice.type, username: notice.username, title: item, created: notice.created},
          beforeSend: function(xhr){
            xhr.setRequestHeader(csrf.headerName, csrf.token);
          },
          success: function () {
            noticeTemplate(notice);
            noticeListInput.val("");
          },
          error: function (data) {
            console.log(data)
          }
        });
      }
    });

    noticeListItem.on('click', '.remove', function() {
      $.ajax({
        url: "/notice/delete",
        type: "post",
        data: {id: $(this).attr("data-id")},
        beforeSend: function(xhr){
          xhr.setRequestHeader(csrf.headerName, csrf.token);
        },
        success: function () {
          $(this).parent().remove();
        }
      });
    });
  });
})(jQuery);