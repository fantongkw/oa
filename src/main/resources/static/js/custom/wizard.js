(function ($) {
  'use strict';
  let form = $('#reset');
  $(function () {
    form.validate({
      rules: {
        email: {
          required: true,
          email: true
        },
        password: {
          required: true,
          minlength: 8
        },
        confirm_password: {
          required: true,
          minlength: 8,
          equalTo: "#password"
        }
      },
      messages: {
        email: {
          required: "请输入电子邮箱",
          email: "请输入有效的电子邮件地址"
        },
        password: {
          required: "请输入密码",
          minlength: "密码长度必须至少为8个字符"
        },
        confirm_password: {
          required: "请输入密码",
          minlength: "密码长度必须至少为8个字符",
          equalTo: "请输入与上面相同的密码"
        },

      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element);
      },
    });
  });
  $(function () {
    form.children("div").steps({
      headerTag: "h3",
      bodyTag: "section",
      transitionEffect: "slideLeft",
      labels: {
        finish: "完成",
        next: "下一步",
        previous: "上一步",
      },
      onStepChanging: function (event, currentIndex, newIndex) {
        form.validate.settings.ingore = ":disabled,:hidden";
        return form.valid();
      },
      onFinishing: function (event, currentIndex)
      {
        form.validate().settings.ignore = ":disabled";
        return form.valid();
      },
    });
  });
})(jQuery);