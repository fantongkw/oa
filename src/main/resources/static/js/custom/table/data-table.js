(function ($) {
  'use strict';
  $(function () {
    $('#order-listing').DataTable({
      "aLengthMenu": [
        [5, 10, 15, -1],
        [5, 10, 15, "全部"]
      ],
      "iDisplayLength": 10,
      "language": {
        paginate: {
          previous: "上一页",
          next: "下一页",
        },
        "lengthMenu": "显示 _MENU_ 页",
        "zeroRecords": "抱歉，没有找到",
        "info": "显示 _TOTAL_ 个条目中的 _START_ 到 _END_ 个",
        "infoEmpty": "未找到相关数据",
        "infoFiltered": "(从共 _MAX_ 个条目中筛选)",
        search: ""
      }
    });
    $('#order-listing').each(function () {
      let datatable = $(this);
      // SEARCH - Add the placeholder for Search and Turn this into in-line form control
      let search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
      search_input.attr('placeholder', '搜索');
      search_input.removeClass('form-control-sm');
      // LENGTH - Inline-Form control
      let length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
      length_sel.removeClass('form-control-sm');
    });
  });
})(jQuery);