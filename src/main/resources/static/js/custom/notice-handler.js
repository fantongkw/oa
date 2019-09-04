(function($) {
  'use strict';

  let noticeListItem = $('.notice-list');
  let noticeListInput = $('.notice-list-input');

  let csrf = {
    headerName: "",
    token: ""
  };

  function noticeTemplate(notice) {
    let date = new Date(notice.created);
    let y = date.getFullYear();
    let M = date.getMonth() + 1;
    let d = date.getDate();
    let h = date.getHours();
    let m = date.getMinutes();
    let s = date.getSeconds();
    if (M < 10) {
      M = "0" + M;
    }
    if (d < 10) {
      d = "0" + d;
    }
    if (h > 12) {
      h %= 12;
    }
    if (h < 10) {
      h = "0" + h;
    }
    if (m < 10) {
      m = "0" + m;
    }
    if (s < 10) {
      s = "0" + s;
    }
    noticeListItem.append("<li><div class='notice-title'><label class='form-check-label'><i class='notice-icon mdi mdi-message-text-outline'></i>" + notice.title + "</label></div><div class='notice-time'>"+ y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s +"</div><i data-id='"+ notice.id +"' class='remove mdi mdi-close-circle-outline'></i></li>");
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

    $(document).on('click', '.remove', function() {
      let ele = $(this);
      $.ajax({
        url: "/notice/delete",
        type: "post",
        data: {id: ele.attr("data-id")},
        beforeSend: function(xhr){
          xhr.setRequestHeader(csrf.headerName, csrf.token);
        },
        success: function () {
          ele.parent().remove();
        },
        error: function () {
          console.log(data)
        }
      });
    });
  });
})(jQuery);