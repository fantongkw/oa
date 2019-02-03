(function ($) {
  "use strict";
  $(function () {
  let initialLocaleCode = 'zh-cn';
  let date = new Date();
  let y = date.getFullYear(),
      m = date.getMonth(),
      d = date.getDate();

  function init_events(ele) {
    ele.each(function () {
      let eventObject = {
        title: $.trim($(this).text())
      };

      $(this).data('eventObject', eventObject);

      $(this).draggable({
        zIndex        : 999,
        revert        : true,
        revertDuration: 0
      })

    })
  }

  init_events($('#external-events div.fc-event'));

  let title;
  let start;
  let backgroundColor;

  function getCalendarData(title, time) {
    $("#event-title").val(title);
    $("#datetimepicker").val(time);
  }

  function dateFormat(date) {
    date = date.substring(0, 16).replace('T', ' ');
    return date;
  }

  $('#calendar').fullCalendar({
    header    : {
      left  : 'prev,next today',
      center: 'title',
      right : 'month,agendaWeek,agendaDay'
    },
    eventClick: function(calEvent) {
      $(this).attr({"data-toggle":"modal","data-target":"#op-event"});
      let edit = '<button id="edit-event" type="button" class="btn btn-warning">修改</button>';
      let delete1 = '<button id="delete-event" type="button" class="btn btn-danger" data-dismiss="modal">删除</button>';
      $('.modal-footer').empty().append(edit).append(delete1);
      getCalendarData(calEvent.title, dateFormat(calEvent.start.format()));


      $('#edit-event').on('click', function () {
        calEvent.title = $("#event-title").val();
        calEvent.start = $("#datetimepicker").val();
        calEvent.backgroundColor = $("#colorpicker").val();
        if (title !== "" && start !== "" && backgroundColor !== "") {
          $(this).attr('data-dismiss',"modal");
          $('#calendar').fullCalendar('updateEvent', calEvent);
        }
      });
      $('#delete-event').on('click', function () {
        $('#calendar').fullCalendar( 'removeEvents' , calEvent.id );
      })
    },
    locale: initialLocaleCode,
    timeFormat: 'H(:mm)',
    editable: true,
    eventLimit: 2,
    events    : [
      {
        id             : 1,
        title          : 'All Day Event',
        start          : '2018-11-01',
        backgroundColor: '#f56954', //red
        borderColor    : '#f56954' //red
      },
      {
        id             : 2,
        title          : 'Long Event',
        start          : new Date(y, m, d - 5),
        end            : new Date(y, m, d - 2),
        backgroundColor: '#f39c12', //yellow
        borderColor    : '#f39c12' //yellow
      },
      {
        id             : 3,
        title          : 'Meeting',
        start          : new Date(y, m, d + 2, 10, 30),
        allDay         : false,
        backgroundColor: '#0073b7', //Blue
        borderColor    : '#0073b7' //Blue
      },
      {
        id             : 4,
        title          : 'Lunch',
        start          : new Date(y, m, d, 12, 0),
        end            : new Date(y, m, d, 14, 0),
        allDay         : false,
        backgroundColor: '#00c0ef', //Info (aqua)
        borderColor    : '#00c0ef' //Info (aqua)
      },
      {
        id             : 5,
        title          : 'Birthday Party',
        start          : new Date(y, m, d + 1, 19, 0),
        end            : new Date(y, m, d + 1, 22, 30),
        allDay         : false,
        backgroundColor: '#00a65a', //Success (green)
        borderColor    : '#00a65a' //Success (green)
      },
      {
        id             : 6,
        title          : 'Click for Google',
        start          : new Date(y, m, 28),
        end            : new Date(y, m, 29),
        url            : 'http://google.com/',
        backgroundColor: '#3c8dbc', //Primary (light-blue)
        borderColor    : '#3c8dbc' //Primary (light-blue)
      }
    ],
    droppable : true,
    drop      : function (date, allDay) {

      let originalEventObject = $(this).data('eventObject');

      let copiedEventObject = $.extend({}, originalEventObject);

      copiedEventObject.start           = date;
      copiedEventObject.allDay          = allDay;
      copiedEventObject.backgroundColor = $(this).css('background-color');
      copiedEventObject.borderColor     = $(this).css('border-color');

      $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

      if ($('#drop-remove').is(':checked')) {
        $(this).remove()
      }
    }
  });

  let currentColor;

  $('#color-chooser > li > a').on('click', function (e) {
    e.preventDefault();
    currentColor = $(this).attr('class');
    /*currentColor = $(this).css('color');*/

    $('#add-new-event').removeClass().addClass('btn '+ currentColor)
  });
  $('#add-new-event').on('click', function (e) {
    e.preventDefault();

    let val = $('#new-event');
    if (val.val().length === 0) {
      return
    }

    let event = $('<div />');
    event.addClass('fc-event '+ currentColor);
    event.html(val);
    $('#external-events').prepend(event);

    init_events(event);

    val.val('')
  });

  $('#datetimepicker').datetimepicker({
    language: 'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    format: 'yyyy-mm-dd hh:ii',
    showMeridian: 1
  });

  $('#button-create').on('click', function () {
    let success = '<button id="datetime-color" type="button" class="btn btn-success">确定</button>';
    let cancel = '<button type="button" class="btn btn-light" data-dismiss="modal">取消</button>';
    $('.modal-footer').empty().append(success).append(cancel);

    $('#datetime-color').on('click', function () {
      title = $("#event-title").val();
      start = $("#datetimepicker").val();
      backgroundColor = $("#colorpicker").val();
      if (title !== "" && start !== "" && backgroundColor !== "") {
        $(this).attr('data-dismiss',"modal");
        $('#calendar').fullCalendar('renderEvent', {
          title: title,
          start: start,
          backgroundColor: backgroundColor,
        });
      }
    });
  });

  $('#remove-calendar').on('click', function () {
    $('#datetimepicker').val('');
  });

  $('#add-calendar').on('click', function () {
    $('#datetimepicker').datetimepicker('show');
  });

  let defaultColor = "#19d895";

  $("#colorpicker").spectrum({
    color: defaultColor,
    showInput: true,
    containerClassName: "full-spectrum",
    showInitial: true,
    showPalette: true,
    showSelectionPalette: true,
    showAlpha: true,
    maxPaletteSize: 10,
    preferredFormat: "hex",
    localStorageKey: "spectrum",
    cancelText: '取消',
    chooseText: '确定',
    move: function () {

    },
    show: function () {

    },
    beforeShow: function () {

    },
    hide: function () {

    },

    palette: [
      ["rgb(0, 0, 0)", "rgb(67, 67, 67)", "rgb(102, 102, 102)", /*"rgb(153, 153, 153)","rgb(183, 183, 183)",*/
        "rgb(204, 204, 204)", "rgb(217, 217, 217)", /*"rgb(239, 239, 239)", "rgb(243, 243, 243)",*/ "rgb(255, 255, 255)"],
      ["rgb(152, 0, 0)", "rgb(255, 0, 0)", "rgb(255, 153, 0)", "rgb(255, 255, 0)", "rgb(0, 255, 0)",
        "rgb(0, 255, 255)", "rgb(74, 134, 232)", "rgb(0, 0, 255)", "rgb(153, 0, 255)", "rgb(255, 0, 255)"],
      ["rgb(230, 184, 175)", "rgb(244, 204, 204)", "rgb(252, 229, 205)", "rgb(255, 242, 204)", "rgb(217, 234, 211)",
        "rgb(208, 224, 227)", "rgb(201, 218, 248)", "rgb(207, 226, 243)", "rgb(217, 210, 233)", "rgb(234, 209, 220)",
        "rgb(221, 126, 107)", "rgb(234, 153, 153)", "rgb(249, 203, 156)", "rgb(255, 229, 153)", "rgb(182, 215, 168)",
        "rgb(162, 196, 201)", "rgb(164, 194, 244)", "rgb(159, 197, 232)", "rgb(180, 167, 214)", "rgb(213, 166, 189)",
        "rgb(204, 65, 37)", "rgb(224, 102, 102)", "rgb(246, 178, 107)", "rgb(255, 217, 102)", "rgb(147, 196, 125)",
        "rgb(118, 165, 175)", "rgb(109, 158, 235)", "rgb(111, 168, 220)", "rgb(142, 124, 195)", "rgb(194, 123, 160)",
        "rgb(166, 28, 0)", "rgb(204, 0, 0)", "rgb(230, 145, 56)", "rgb(241, 194, 50)", "rgb(106, 168, 79)",
        "rgb(69, 129, 142)", "rgb(60, 120, 216)", "rgb(61, 133, 198)", "rgb(103, 78, 167)", "rgb(166, 77, 121)",
        /*"rgb(133, 32, 12)", "rgb(153, 0, 0)", "rgb(180, 95, 6)", "rgb(191, 144, 0)", "rgb(56, 118, 29)",
        "rgb(19, 79, 92)", "rgb(17, 85, 204)", "rgb(11, 83, 148)", "rgb(53, 28, 117)", "rgb(116, 27, 71)",*/
        "rgb(91, 15, 0)", "rgb(102, 0, 0)", "rgb(120, 63, 4)", "rgb(127, 96, 0)", "rgb(39, 78, 19)",
        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)"]
    ]
  }).val(defaultColor);
});
})(jQuery);