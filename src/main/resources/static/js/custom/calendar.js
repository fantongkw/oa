(function ($) {
  "use strict";
  $(function () {
    let initialLocaleCode = 'zh-cn';
    let date = new Date();
    let y = date.getFullYear(),
        m = date.getMonth(),
        d = date.getDate();

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

    //初始化外部事件
    function init_events(ele) {
      ele.each(function () {
        let eventObject = {
          title: $.trim($(this).text())
        };

        $(this).data('eventObject', eventObject);

        $(this).draggable({
          zIndex: 999,
          revert: true,
          revertDuration: 0
        })

      })
    }

    init_events($('#external-events div.fc-event'));

    let title;
    let start;
    let backgroundColor;

    //获取日历标题和时间
    function getCalendarData(title, time) {
      $('#event-title').val(title);
      $('#datetimepicker').val(time);
    }

    //时间格式化
    function dateFormat(date) {
      date = date.substring(0, 16).replace('T', ' ');
      return date;
    }

    //初始化日历
    $('#calendar').fullCalendar({
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      eventClick: function (calEvent) {
        $(this).attr({"data-toggle": "modal", "data-target": "#op-event"});
        let edit = '<button id="edit-event" type="button" class="btn btn-warning">修改</button>';
        let delete1 = '<button id="delete-event" type="button" class="btn btn-danger" data-dismiss="modal">删除</button>';
        $('.modal-footer').empty().append(edit).append(delete1);
        getCalendarData(calEvent.title, dateFormat(calEvent.start.format()));

        $('#edit-event').on('click', function () {
          calEvent.title = $("#event-title").val();
          calEvent.start = $("#datetimepicker").val();
          calEvent.backgroundColor = $("#colorpicker").val();
          if (title !== "" && start !== "" && backgroundColor !== "") {
            $(this).attr('data-dismiss', "modal");
            $('#calendar').fullCalendar('updateEvent', calEvent);
          }
        });
        $('#delete-event').on('click', function () {
          if (calEvent.id === 0) {
            return;
          }
          $('#calendar').fullCalendar('removeEvents', calEvent.id);
        })
      },
      locale: initialLocaleCode,
      timeFormat: 'H(:mm)',
      editable: true,
      eventLimit: 2,
      events: [
        {
          id: getId(),
          title: '全天的事件',
          start: '2019-5-22',
          backgroundColor: '#8862e0', //red
        },
        {
          id: getId(),
          title: '长的事件',
          start: new Date(y, m, d - 5),
          end: new Date(y, m, d - 2),
          backgroundColor: '#ffaf00', //yellow
        },
        {
          id: getId(),
          title: '开会',
          start: new Date(y, m, d + 2, 10, 30),
          allDay: false,
          backgroundColor: '#2196f3', //Blue
        },
        {
          id: getId(),
          title: '午餐',
          start: new Date(y, m, d, 12, 0),
          end: new Date(y, m, d, 14, 0),
          allDay: false,
          backgroundColor: '#00c0ef', //Info (aqua)
        },
        {
          id: getId(),
          title: '生日派对',
          start: new Date(y, m, d + 1, 19, 0),
          end: new Date(y, m, d + 1, 22, 30),
          allDay: false,
          backgroundColor: '#19d895', //Success (green)
        },
        {
          id: getId(),
          title: '访问网页',
          start: new Date(y, m, 28),
          end: new Date(y, m, 29),
          url: 'https://www.baidu.com/',
          backgroundColor: '#ff6258', //Primary (light-blue)
        }
      ],
      //开启拖动
      droppable: true,
      drop: function (date, allDay) {
        let originalEventObject = $(this).data('eventObject');
        let copiedEventObject = $.extend({}, originalEventObject);
        copiedEventObject.id = getId();
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        copiedEventObject.backgroundColor = $(this).css('background-color');
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
        if ($('#drop-remove').is(':checked')) {
          $(this).remove()
        }
      }
    });

    //添加事件
    let currentColor = '#2196f3';

    $('#color-chooser > li > a').on('click', function (e) {
      e.preventDefault();
      currentColor = $(this).css('color');

      $('#add-new-event').css({'background-color': currentColor});
    });
    $('#add-new-event').on('click', function (e) {
      e.preventDefault();

      let val = $('#new-event').val();
      if (val.length === 0) {
        return
      }

      let event = $('<div />');
      event.css({
        'background-color': currentColor,
        'color': '#fff'
      }).addClass('fc-event');
      event.html(val);
      $('#external-events').prepend(event);

      init_events(event);

      $(`#new-event`).val('')
    });

    $(`#datetimepicker`).datetimepicker({
      language: 'zh-CN',
      weekStart: 1,
      todayBtn: 1,
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
          $(this).attr('data-dismiss', "modal");
          $('#calendar').fullCalendar('renderEvent', {
            id: getId(),
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

    $('#colorpicker').spectrum({
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