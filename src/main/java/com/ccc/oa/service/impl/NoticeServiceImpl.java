package com.ccc.oa.service.impl;

import com.ccc.oa.model.Notice;
import com.ccc.oa.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service(value = "noticeService")
public class NoticeServiceImpl implements NoticeService {
    private final String namespace = "notice:";

    private final RedisTemplate<String, Notice> redisTemplate;

    @Autowired
    public NoticeServiceImpl(RedisTemplate<String, Notice> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void deleteAll() {
        Set<String> keys = redisTemplate.keys(getAllNotice());
        if (keys == null) return;
        redisTemplate.delete(keys);
    }

    @Override
    public void delete(String id) {
        redisTemplate.delete(getNoticeKey(id));
    }

    @Override
    public void add(String id, Notice notice) {
        redisTemplate.opsForValue().set(getNoticeKey(id), notice, 30, TimeUnit.DAYS);
    }

    @Override
    public List<Notice> getAll() {
        List<Notice> res = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(getAllNotice());
        if (keys != null) {
            keys.forEach(e -> {
                Notice notice = redisTemplate.opsForValue().get(e);
                res.add(notice);
            });
        }
        return res;
    }

    @Override
    public Notice get(String id) {
        return redisTemplate.opsForValue().get(getNoticeKey(id));
    }

    @Override
    public void update(String id, Notice notice) {
        redisTemplate.opsForValue().set(getNoticeKey(id), notice, 30, TimeUnit.DAYS);
    }

    private String getAllNotice() {
        return this.namespace + "*";
    }

    private String getNoticeKey(String id) {
        return this.namespace + id;
    }
}
