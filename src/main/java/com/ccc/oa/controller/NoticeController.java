package com.ccc.oa.controller;

import com.ccc.oa.model.Notice;
import com.ccc.oa.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PreAuthorize("hasRole('ROLE_NOTICE')")
    @PostMapping(value = "/add")
    public void add(Notice notice) {
        notice.setTitle(HtmlUtils.htmlEscape(notice.getTitle()));
        noticeService.add(notice.getId(), notice);
    }

    @PreAuthorize("hasRole('ROLE_NOTICE')")
    @PostMapping(value = "/delete")
    public void delete(String id) {
        noticeService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_NOTICE')")
    @PostMapping(value = "/update")
    public void update(String id, Notice notice) {
        noticeService.update(id, notice);
    }
}
