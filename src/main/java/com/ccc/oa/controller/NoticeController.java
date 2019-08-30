package com.ccc.oa.controller;

import com.ccc.oa.model.Notice;
import com.ccc.oa.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping(value = "/list")
    public List<Notice> list() {
        List<Notice> res = noticeService.getAll();
        res.sort(Comparator.comparingLong(Notice::getCreated));
        return res;
    }

    @PostMapping(value = "/add")
    public void add(Notice notice) {
        noticeService.add(notice.getId(), notice);
    }

    @PostMapping(value = "/delete")
    public void delete(String id) {
        noticeService.delete(id);
    }

    @PostMapping(value = "/update")
    public void update(String id, Notice notice) {
        noticeService.update(id, notice);
    }
}
