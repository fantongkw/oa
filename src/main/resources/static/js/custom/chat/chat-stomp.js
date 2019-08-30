let to = {
  username: null,
  avatar: null
};

let from = {
  username: $('.profile-name').text(),
  avatar: $('.profile-image > img').attr('src')
};

let prev = "";
let chatArea = $('.chat-container-wrapper');

let stomp = null;

function connect() {
  let socket = new SockJS("/private");
  stomp = Stomp.over(socket);
  stomp.heartbeat.outgoing = 100000;
  stomp.heartbeat.incoming = 100000;
  let headers = {
    Authorization:''
  };
  stomp.connect(headers, () => {
    stomp.subscribe("/topic/status/connect", message => {
      let user = JSON.parse(message.body);
      userTemplate(user);
    });
    stomp.subscribe("/topic/status/disconnect", message => {
      $('.user-name').each(function () {
        if ($(this).text().trim() === message.body) {
          $(this).parent().remove();
          return false;
        }
      });
    });
    stomp.subscribe("/user/queue/message", message => {
      if ($(".list-item.active > p.user-name").text() === JSON.parse(message.body).from) {
        showMessage(message);
      }
    });
  });
}

function getUsers() {
  $.ajax({
    url: "/app/users",
    type: "get",
    dataType: "json",
    success: function (response) {
      console.log(response);
      for (let i = 0; i < response.length; i++) {
        userTemplate(response[i]);
      }
    },
    error: function (response) {
      console.log(response);
    }
  });
}

function selectChatUser(user) {
  if (prev !== "") {
    prev.removeClass('active');
    chatArea.empty();
  }
  to.username = user.find('.user-name').text().trim();
  to.avatar = user.find('img').attr('src');
  $('.chat-app-tip').remove();
  $('.chat-text-field').show();
  user.addClass('active');
  prev = user;
}

function selectChatMessage() {
  axios.get("/app/getMessage", {
    params: {
      from: from.username,
      to: to.username
    }
  })
      .then(response => {
        let message = response.data;
        for (let i = 0; i < message.length; i++) {
          console.log(message[i]);
          if (message[i].from === from.username) {
            messageOutTemplate(message[i]);
          } else {
            messageInTemplate(message[i]);
          }
        }
      })
}

function userTemplate(user) {
  $('.chat-list-item-wrapper').append("<div class=\"list-item\">\n" +
      "                        <div class=\"profile-image\">\n" +
      "                          <div class=\"dot-indicator sm bg-success\"></div>\n" +
      "                          <img class=\"img-sm rounded-circle\" src=\""+ user.avatar +" \" alt=\"profile image\">\n" +
      "                        </div> \n" +
      "                        <p class=\"user-name\">"+ user.username +"</p>\n" +
      "                        <p class=\"chat-time\">"+ new Date(user.connectionTime).toLocaleString() +"</p>\n" +
      "                     <!--<p class=\"chat-text\">最后一次的消息</p>-->\n" +
      "                      </div>")
}

function messageInTemplate(message) {
  chatArea.append("<div class=\"chat-bubble incoming-chat\">\n" +
      "                                  <div class=\"sender-details\">\n" +
      "                                    <img class=\"sender-avatar img-xs rounded-circle\" src=\""+ to.avatar +"\" alt=\"profile image\">\n" +
      "                                    <p class=\"seen-text\">"+ new Date(message.created).toLocaleString() +"</p>\n" +
      "                                  </div>\n" +
      "                                  <div class=\"chat-message\">\n" +
      "                                    <p class=\"font-weight-bold\">"+ message.from +"</p>\n" +
      "                                    <p>" + message.message + "</p>\n" +
      "                                  </div>\n" +
      "                                </div>");
}

function messageOutTemplate(message) {
  chatArea.append("<div class=\"chat-bubble outgoing-chat\">\n" +
      "                                  <div class=\"sender-details\">\n" +
      "                                    <img class=\"sender-avatar img-xs rounded-circle\" src=\""+ from.avatar +"\" alt=\"profile image\">\n" +
      "                                    <p class=\"seen-text\">"+ new Date(message.created).toLocaleString() +"</p>\n" +
      "                                  </div>\n" +
      "                                  <div class=\"chat-message\">\n" +
      "                                    <p class=\"font-weight-bold\">"+ message.from +"</p>\n" +
      "                                    <p>"+ message.message +"</p>\n" +
      "                                  </div>\n" +
      "                                </div>");
}

function showMessage(message) {
  let chatMessage = JSON.parse(message.body);
  messageInTemplate(chatMessage);
}

function sendMessage() {
  let chatMessage = {
    to: to.username,
    from: from.username,
    message: $('#input').val(),
    created: new Date().getTime()
  };
  console.log(chatMessage);
  messageOutTemplate(chatMessage);
  stomp.send("/app/chat", {}, JSON.stringify(chatMessage));
}

$(function () {
  getUsers();
  $(document).on('click', ".list-item", function() {
    selectChatUser($(this));
    selectChatMessage();
  });
  $('#chat').on('submit', e => { e.preventDefault();});
  $('#send').on('click', function () {
    let value = $('#input').val();
    if (to.username !== null || value !== '') {
      sendMessage();
    }
  });
  connect();
});