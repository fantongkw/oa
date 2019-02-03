(function ($) {
  'use strict';
  $.validator.addMethod("isPhone", function(value, element) {
    let length = value.length;
    let mobile = /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$/;
    return this.optional(element) || (length === 11 && mobile.test(value));
  });
  $(function () {
    $('#login').validate({
      rules: {
        username: {
          required: true,
          minlength: 4
        },
        password: {
          required: true,
          minlength: 8
        },
      },
      messages: {
        username: {
          required: "请输入用户名",
          minlength: "用户名必须至少包含4个字符"
        },
        password: {
          required: "请输入密码",
          minlength: "密码长度必须至少为8个字符"
        }
      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element.parent());
      },
    });
  });
  $(function () {
    $('#register').validate({
      rules: {
        username: {
          required: true,
          minlength: 4,
          remote: {
            type: "GET",
            url: "/api/username",
            data: {
              username: () => {
                return $('#username').val();
              }
            }
          }
        },
        password: {
          required: true,
          minlength: 8
        },
        confirm_password: {
          required: true,
          equalTo: "#password"
        }
      },
      messages: {
        username: {
          required: "请输入用户名",
          minlength: "用户名必须至少包含4个字符",
          remote: "用户名已存在"
        },
        password: {
          required: "请输入密码",
          minlength: "密码长度必须至少为8个字符"
        },
        confirm_password: {
          required: "请输入密码",
          equalTo: "请输入与上面相同的密码"
        },

      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element.parent());
      },
    });
  });
  $(function () {
    $('#change_password').validate({
      rules: {
        old_password: {
          required: true,
          remote: {
            type: "GET",
            url: "/api/password",
            data: {
              old_password: () => {
                return $('#old_password').val();
              }
            }
          }
        },
        new_password: {
          required: true,
          minlength: 8
        },
        confirm_new_password: {
          required: true,
          equalTo: "#password"
        }
      },
      messages: {
        old_password: {
          required: "请输入密码",
          remote: "旧密码错误"
        },
        new_password: {
          required: "请输入密码",
          minlength: "密码长度必须至少为8个字符"
        },
        confirm_new_password: {
          required: "请输入密码",
          equalTo: "请输入与上面相同的密码"
        },

      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element.parent());
      },
    });
  });
  $(function () {
    $('#dept-add-validation').validate({
      rules: {
        name: {
          required: true,
          minlength: 4,
          maxlength: 20
        },
        description: {
          maxlength: 100
        }
      },
      messages: {
        name: {
          required: "请输入部门名称",
          minlength: "部门名称必须至少包含4个字符",
          maxlength: "部门名称必须最多包含20个字符"
        },
        description: {
          maxlength: "部门描述必须最多包含100个字符"
        }
      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element);
      },
    });
  });
  $(function () {
    $('#dept-update-validation').validate({
      rules: {
        id: {
          required: true,
          minlength: 1
        },
        name: {
          required: true,
          minlength: 4,
          maxlength: 20
        },
        description: {
          maxlength: 100
        }
      },
      messages: {
        id: {
          required: "请输入部门编号",
          minlength: "部门编号必须至少包含1个字符"
        },
        name: {
          required: "请输入部门名称",
          minlength: "部门名称必须至少包含4个字符",
          maxlength: "部门名称必须最多包含20个字符"
        },
        description: {
          maxlength: "部门描述必须最多包含100个字符"
        }
      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element);
      },
    });
  });
  $(function () {
    $('#role-add-validation').validate({
      rules: {
        name: {
          required: true,
          minlength: 4,
          maxlength: 20
        },
        description: {
          maxlength: 100
        }
      },
      messages: {
        name: {
          required: "请输入角色名称",
          minlength: "角色名称必须至少包含4个字符",
          maxlength: "角色名称必须最多包含20个字符"
        },
        description: {
          maxlength: "角色描述必须最多包含100个字符"
        }
      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element);
      },
    });
  });
  $(function () {
    $('#role-update-validation').validate({
      rules: {
        id: {
          required: true,
          minlength: 1
        },
        name: {
          required: true,
          minlength: 4,
          maxlength: 20
        },
        description: {
          maxlength: 100
        }
      },
      messages: {
        id: {
          required: "请输入角色编号",
          minlength: "角色编号必须至少包含1个字符"
        },
        name: {
          required: "请输入角色名称",
          minlength: "角色名称必须至少包含4个字符",
          maxlength: "角色名称必须最多包含20个字符"
        },
        description: {
          maxlength: "角色描述必须最多包含100个字符"
        }
      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element);
      },
    });
  });
  $(function () {
    $('#user-add-validation').validate({
      rules: {
        department: {
          required: true,
          minlength: 4,
          maxlength: 20
        },
        name: {
          required: true,
          minlength: 4,
          maxlength: 20
        },
        gender: {
          required: true
        },
        phoneNumber: {
          isPhone: true
        },
        email: {
          required: true,
          email: true
        },
        description: {
          maxlength: 100
        }
      },
      messages: {
        department: {
          required: "请输入部门名称",
          minlength: "部门名称必须至少包含4个字符",
          maxlength: "部门名称必须至少包含4个字符"
        },
        name: {
          required: "请输入员工名称",
          minlength: "员工名称必须至少包含4个字符",
          maxlength: "员工名称必须最多包含20个字符"
        },
        gender: {
          required: "请输入员工性别",
        },
        phoneNumber: {
          isPhone: "请填写正确的手机号码"
        },
        email: {
          required: "请输入员工电子邮箱",
          email: "请输入有效的电子邮件地址"
        },
        description: {
          maxlength: "员工描述必须最多包含100个字符"
        }
      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element);
      },
    });
  });
  $(function () {
    $('#user-update-validation').validate({
      rules: {
        department: {
          required: true,
          minlength: 4,
          maxlength: 20
        },
        name: {
          required: true,
          minlength: 4,
          maxlength: 20
        },
        gender: {
          required: true
        },
        phoneNumber: {
          isPhone: true
        },
        email: {
          required: true,
          email: true
        },
        description: {
          maxlength: 100
        }
      },
      messages: {
        department: {
          required: "请输入部门名称",
          minlength: "部门名称必须至少包含4个字符",
          maxlength: "部门名称必须至少包含4个字符"
        },
        name: {
          required: "请输入员工名称",
          minlength: "员工名称必须至少包含4个字符",
          maxlength: "员工名称必须最多包含20个字符"
        },
        gender: {
          required: "请输入员工性别",
        },
        phoneNumber: {
          isPhone: "请填写正确的手机号码"
        },
        email: {
          required: "请输入员工电子邮箱",
          email: "请输入有效的电子邮件地址"
        },
        description: {
          maxlength: "员工描述必须最多包含100个字符"
        }
      },
      errorPlacement: function (label, element) {
        label.addClass('mt-2 text-danger');
        label.insertAfter(element);
      },
    });
  });
})(jQuery);