(function ($) {
  'use strict';
  let resubmit = false;
  let decide = false;

  function verify() {
    let csrf = {
      headerName: "",
      token: ""
    };
    getCsrf(csrf);
    let handler = function (captchaObj) {
      captchaObj.appendTo("#captcha");
      captchaObj.onReady(function () {
        $("#wait").hide();
      });
      captchaObj.onSuccess(function () {
        let result = captchaObj.getValidate();
        $.ajax({
          url: "/verify/ajax-validate",
          type: "post",
          dataType: "json",
          data: {
            geetest_challenge: result.geetest_challenge,
            geetest_validate: result.geetest_validate,
            geetest_seccode: result.geetest_seccode
          },
          beforeSend: function(xhr){
            xhr.setRequestHeader(csrf.headerName, csrf.token);
          },
          success: function (data) {
            if (data.status === "success") {
              decide = true;
            }
          }
        })
      });
    };
    $.ajax({
      url: "/verify/register",
      type: "get",
      dataType: "json",
      success: function (data) {
        initGeetest({
          gt: data.gt,
          challenge: data.challenge,
          new_captcha: data.new_captcha,
          offline: !data.success,
          https: true,
          product: "popup",
          width: "100%"
        }, handler);
      }
    });
  }
  function duplicateLogin(data) {
    if (resubmit) {
      return false;
    } else {
      resubmit = true;
      data.submit();
    }
  }

  $(function () {
    $.validator.addMethod("isPhone", function(value, element) {
      let length = value.length;
      let mobile = /^1[356789]\d{9}$/;
      return this.optional(element) || (length === 11 && mobile.test(value));
    });
    $.validator.addMethod("isEmail", function(value, element) {
      let email = /^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,4}$/i;
      return this.optional(element) || (email.test(value));
    });
    $.validator.addMethod("isRule", function(value, element) {
      let rule = /^[A-Z0-9a-z]*$/;
      return this.optional(element) || (rule.test(value));
    });

    let form = $(this).find('.forms-sample').attr('id');
    if (form === "login") {
      verify();
      $('#login').validate({
        submitHandler: function(data) {
          if (decide) {
            duplicateLogin(data);
          } else {
            $("#notice").show();
            setTimeout(function () {
              $("#notice").hide();
            }, 3000);
          }
        },
        onfocusout: function(element) {
          $(element).valid();
        },
        rules: {
          username: {
            required: true,
            minlength: 4,
            maxlength: 20
          },
          password: {
            required: true,
            minlength: 8,
            maxlength: 20,
          }
        },
        messages: {
          username: {
            required: "请输入用户名",
            minlength: "用户名必须至少包含4个字符",
            maxlength: "用户名最多包含20个字符"
          },
          password: {
            required: "请输入密码",
            minlength: "密码长度必须至少为8个字符",
            maxlength: "密码最多包含20个字符",
          }
        },
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element.parent());
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
          $(element).next().children().addClass(errorClass).removeClass(validClass)
              .children().addClass('mdi-close-circle-outline').removeClass('mdi-check-circle-outline');
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
          $(element).next().children().removeClass(errorClass).addClass(validClass)
              .children().removeClass('mdi-close-circle-outline').addClass('mdi-check-circle-outline');
        }
      });
    }
    if (form === "register") {
      verify();
      $('#register').validate({
        submitHandler: function(data) {
          if (decide) {
            duplicateLogin(data);
          } else {
            $("#notice").show();
            setTimeout(function () {
              $("#notice").hide();
            }, 3000);
          }
        },
        onfocusout: function(element) {
          $(element).valid();
        },
        rules: {
          username: {
            required: true,
            minlength: 4,
            maxlength: 20,
            isRule: true,
            remote: {
              type: "get",
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
            minlength: 8,
            maxlength: 20,
            isRule: true
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
            maxlength: "用户名最多包含20个字符",
            isRule: "用户名不符合规则(只允许大小写字母和数字)",
            remote: "用户名已存在"
          },
          password: {
            required: "请输入密码",
            minlength: "密码长度必须至少为8个字符",
            maxlength: "密码最多包含20个字符",
            isRule: "密码不符合规则(只允许大小写字母和数字)"
          },
          confirm_password: {
            required: "请输入密码",
            equalTo: "请输入与上面相同的密码"
          }
        },
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element.parent());
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
          $(element).next().children().addClass(errorClass).removeClass(validClass)
              .children().addClass('mdi-close-circle-outline').removeClass('mdi-check-circle-outline');
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
          $(element).next().children().removeClass(errorClass).addClass(validClass)
              .children().removeClass('mdi-close-circle-outline').addClass('mdi-check-circle-outline');
        }
      });
    }
    if (form === "editable-form") {
      $('#editable-form').validate({
        submitHandler: function(data) {
          duplicateLogin(data);
        },
        onfocusout: function(element) {
          $(element).valid();
        },
        rules: {
          department: {
            required: true
          },
          name: {
            minlength: 4,
            maxlength: 10
          },
          gender: {
            required: true
          },
          phone: {
            isPhone: true
          },
          email: {
            isEmail: true
          },
          description: {
            maxlength: 100
          }
        },
        messages: {
          department: {
            required: "请选择部门"
          },
          name: {
            minlength: "账户名称必须至少包含4个字符",
            maxlength: "账户名称必须最多包含10个字符"
          },
          gender: {
            required: "请选择性别"
          },
          phone: {
            isPhone: "请填写正确的手机号码"
          },
          email: {
            isEmail: "请输入有效的电子邮件地址"
          },
          description: {
            maxlength: "描述必须最多包含100个字符"
          }
        },
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element);
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
        }
      });
    }
    if (form === "change_password") {
      $.validator.addMethod("isEquals", function (value, element) {
        let old_password = $('#old_password').val();
        return this.optional(element) || value !== old_password;
      });
      $('#change_password').validate({
        submitHandler: function(data) {
          duplicateLogin(data);
        },
        onfocusout: function(element) {
          $(element).valid();
        },
        rules: {
          old_password: {
            required: true,
            minlength: 8,
            remote: {
              type: "get",
              url: "/api/password",
              data: {
                password: () => {
                  return $('#old_password').val();
                  }
                }
              }
            },
          new_password: {
            required: true,
            minlength: 8,
            isEquals: true,
            isRule: true
          },
          confirm_new_password: {
            required: true,
            equalTo: "#new_password"
          }
        },
        messages: {
          old_password: {
            required: "请输入旧密码",
            minlength: "密码长度必须至少为8个字符",
            remote: "旧密码不正确"
          },
          new_password: {
            required: "请输入新密码",
            minlength: "密码长度必须至少为8个字符",
            isEquals: "新密码与旧密码相同",
            isRule: "新密码不符合规则(只允许大小写字母和数字)"
          },
          confirm_new_password: {
            required: "请再次输入新密码",
            equalTo: "请输入与上面相同的密码"
          },
        },
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element);
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
        }
      });
    }
    if (form === "dept-add-validation") {
      $('#dept-add-validation').validate({
        submitHandler: function(data) {
          duplicateLogin(data);
        },
        onfocusout: function(element) {
          $(element).valid();
        },
        rules: {
          name: {
            required: true,
            minlength: 4,
            maxlength: 12
          },
          description: {
            maxlength: 100
          }
        },
        messages: {
          name: {
            required: "请输入部门名称",
            minlength: "部门名称必须至少包含4个字符",
            maxlength: "部门名称必须最多包含12个字符"
          },
          description: {
            maxlength: "部门描述必须最多包含100个字符"
          }
        },
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element);
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
        }
      });
    }
    if (form === "dept-update-validation") {
      $('#dept-update-validation').validate({
        submitHandler: function(data) {
          duplicateLogin(data);
        },
        onfocusout: function(element) {
          $(element).valid();
        },
        rules: {
          id: {
            required: true,
          },
          name: {
            minlength: 4,
            maxlength: 12
          },
          description: {
            maxlength: 100
          }
        },
        messages: {
          id: {
            required: "请选择部门编号",
          },
          name: {
            minlength: "部门名称必须至少包含4个字符",
            maxlength: "部门名称必须最多包含12个字符"
          },
          description: {
            maxlength: "部门描述必须最多包含100个字符"
          }
        },
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element);
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
        }
      });
    }
    if (form === "role-add-validation") {
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
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element);
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
        }
      });
    }
    if (form === "role-update-validation") {
      $('#role-update-validation').validate({
        submitHandler: function(data) {
          duplicateLogin(data);
        },
        onfocusout: function(element) {
          $(element).valid();
        },
        rules: {
          id: {
            required: true,
          },
          name: {
            minlength: 4,
            maxlength: 20
          },
          description: {
            maxlength: 100
          }
        },
        messages: {
          id: {
            required: "请选择角色编号",
          },
          name: {
            minlength: "角色名称必须至少包含4个字符",
            maxlength: "角色名称必须最多包含20个字符"
          },
          description: {
            maxlength: "角色描述必须最多包含100个字符"
          }
        },
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element);
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
        }
      });
    }
    if (form === "user-add-validation") {
      $('#user-add-validation').validate({
        submitHandler: function(data) {
          duplicateLogin(data);
        },
        onfocusout: function(element) {
          $(element).valid();
        },
        rules: {
          username: {
            required: true,
            minlength: 4,
            maxlength: 20,
            isRule: true
          },
          password: {
            required: true,
            minlength: 8,
            maxlength: 20,
            isRule: true
          },
          departmentId: {
            required: true,
          },
          name: {
            minlength: 4,
            maxlength: 10
          },
          gender: {
            required: true
          },
          phone: {
            isPhone: true
          },
          email: {
            isEmail: true
          },
          description: {
            maxlength: 100
          }
        },
        messages: {
          username: {
            required: "请输入账户名称",
            minlength: "账户名称必须至少包含4个字符",
            maxlength: "账户名称必须最多包含20个字符",
            isRule: "用户名不符合规则(只允许大小写字母和数字)"
          },
          password: {
            required: "请输入账户密码",
            minlength: "账户密码必须至少包含8个字符",
            maxlength: "账户密码必须最多包含20个字符",
            isRule: "密码不符合规则(只允许大小写字母和数字)"
          },
          departmentId: {
            required: "请选择部门",
          },
          name: {
            minlength: "员工名称必须至少包含4个字符",
            maxlength: "员工名称必须最多包含10个字符"
          },
          gender: {
            required: "请选择员工性别",
          },
          phone: {
            isPhone: "请填写正确的手机号码"
          },
          email: {
            isEmail: "请输入有效的电子邮件地址"
          },
          description: {
            maxlength: "员工描述必须最多包含100个字符"
          }
        },
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element);
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
        }
      });
    }
    if (form === "user-update-validation") {
      $('#user-update-validation').validate({
        submitHandler: function(data) {
          duplicateLogin(data);
        },
        onfocusout: function(element) {
          $(element).valid();
        },
        rules: {
          departmentId: {
            required: true,
          },
          name: {
            minlength: 4,
            maxlength: 10
          },
          gender: {
            required: true
          },
          phone: {
            isPhone: true
          },
          email: {
            isEmail: true
          },
          description: {
            maxlength: 100
          }
        },
        messages: {
          departmentId: {
            required: "请选择部门"
          },
          name: {
            minlength: "员工名称必须至少包含4个字符",
            maxlength: "员工名称必须最多包含10个字符"
          },
          gender: {
            required: "请输入员工性别",
          },
          phone: {
            isPhone: "请填写正确的手机号码"
          },
          email: {
            isEmail: "请输入有效的电子邮件地址"
          },
          description: {
            maxlength: "员工描述必须最多包含100个字符"
          }
        },
        errorClass: 'border-danger',
        validClass: 'border-success',
        errorPlacement: function (label, element) {
          label.addClass('mt-2 text-danger');
          label.insertAfter(element);
        },
        highlight: function (element, errorClass, validClass) {
          $(element).addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function(element, errorClass, validClass) {
          $(element).removeClass(errorClass).addClass(validClass);
        }
      });
    }
  });
})(jQuery);