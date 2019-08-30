package com.ccc.oa.service;

import com.ccc.oa.model.Notice;

import java.util.List;

public interface NoticeService {
    void deleteAll();

    void delete(String id);

    void add(String id, Notice notice);

    List<Notice> getAll();

    Notice get(String id);

    void update(String id, Notice notice);
}
