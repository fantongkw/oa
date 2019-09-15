(function ($) {
  'use strict';
  $(function () {
    let sidebar = $('.sidebar');

    //Add active class to nav-link based on url dynamically
    //Active class can be hard coded directly in html file also as required
    if (!$('#sidebar').hasClass("dynamic-active-class-disabled")) {
      let current = location.pathname.split("/").slice(-1)[0].replace(/^\/|\/$/g, '');
      $('#sidebar >.nav > li:not(.not-navigation-link) a').each(function () {
        let $this = $(this);
        if (current === "") {
          //for root url
          if ($this.attr('href').indexOf("index.html") !== -1) {
            $(this).parents('.nav-item').last().addClass('active');
            if ($(this).parents('.sub-menu').length) {
              $(this).addClass('active');
            }
          }
        } else {
          //for other url
          if ($this.attr('href').indexOf(current) !== -1) {
            $(this).parents('.nav-item').last().addClass('active');
            if ($(this).parents('.sub-menu').length) {
              $(this).addClass('active');
            }
            if (current !== "index.html") {
              $(this).parents('.nav-item').last().find(".nav-link").attr("aria-expanded", "true");
              if ($(this).parents('.sub-menu').length) {
                $(this).closest('.collapse').addClass('show');
              }
            }
          }
        }
      })
    }

    // Themeswitch function
    $(".email-wrapper .mail-list-container .mail-list").on("click", function () {
      $(".email-wrapper .mail-list-container").addClass("d-none");
      $(".email-wrapper .mail-view").addClass("d-block");
    });
    $(".email-wrapper .mail-back-button").on("click", function () {
      $(".email-wrapper .mail-list-container").removeClass("d-none");
      $(".email-wrapper .mail-view").removeClass("d-block");
    });

    //Close other submenu in sidebar on opening any

    $("#sidebar > .nav > .nav-item > a[data-toggle='collapse']").on("click", function () {
      $("#sidebar > .nav > .nav-item").find('.collapse.show').collapse('hide');
    });


    //Change sidebar and content-wrapper height
    applyStyles();

    function applyStyles() {
      //Applying perfect scrollbar
      if ($('.scroll-container').length) {
        const ScrollContainer = new PerfectScrollbar('.scroll-container');
      }
    }

    //checkbox and radios
    $(".form-check label,.form-radio label").append('<i class="input-helper"></i>');


    $(".purchace-popup .popup-dismiss").on("click", function () {
      $(".purchace-popup").slideToggle();
    });
  });
})(jQuery);