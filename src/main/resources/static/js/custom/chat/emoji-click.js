(function ($) {
  'use strict';
  $(function () {
    $("#emoji").on("click", function (e) {
      $('#picker').toggle();
      e.stopPropagation();
    });
    $(document).on("click", function () {
      $("#picker").hide();
    });
  })
})(jQuery);