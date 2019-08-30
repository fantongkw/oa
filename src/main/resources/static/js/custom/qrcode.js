(function ($) {
  'use strict';
  $(function () {
    $('.qrcode').qrcode("hello world!" + new Date());
    $('#other').on('click', () => {
      $('#login').toggle();
      $('.form-qrcode').toggle();
    });
  })
})(jQuery);