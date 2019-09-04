package com.ccc.oa.utils;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName: RedisSessionUtil
 * @Author: Administrator
 * @Description: 获取redis里session的信息
 * @Date: 2019/4/12 13:31
 * @Version: 1.0
 **/
@Component
public class RedisSessionUtil {
    private final String namespace = "spring:session:";

    private final RedisTemplate<String, String> redisTemplate;

    public RedisSessionUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @Description 获取所有的在线的session
     * @Date 2019/4/12 13:31
     * @Param []
     * @return int
     **/
    public int getAllSession() {
        Set<String> keys = redisTemplate.keys(getAllSessionKey());
        return keys == null ? 0 : keys.size();
    }

    /**
     * @Description 通过sessionId获取session信息
     * @Date 2019/4/12 13:32
     * @Param [sessionId]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    public Map<String, String> getSession(String sessionId) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.entries(getSessionKey(sessionId));
    }

    /**
     * @Description 通过过期时间获取session信息
     * @Date 2019/4/12 13:33
     * @Param [expiration]
     * @return java.util.Set<java.lang.String>
     **/
    public Set<String> getExpiration(long expiration) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        return set.members(getExpirationsKey(expiration));
    }

    /**
     * @Description 通过sessionId获取过期时间
     * @Date 2019/4/12 13:34
     * @Param [sessionId]
     * @return java.lang.String
     **/
    public String getExpired(String sessionId) {
        ValueOperations value = redisTemplate.opsForValue();
        return (String)value.get(getExpiredKey(sessionId));
    }

    /**
     * @Description 通过用户名获取session
     * @Date 2019/4/12 13:34
     * @Param [principalName]
     * @return java.util.Set<java.lang.String>
     **/
    public Set<String> getPrincipal(String principalName) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        return set.members(getPrincipalKey(principalName));
    }

    private String getAllSessionKey() {
        return this.namespace + "sessions:expires:*";
    }

    private String getSessionKey(String sessionId) {
        return this.namespace + "sessions:" + sessionId;
    }

    private String getPrincipalKey(String principalName) {
        return this.namespace + "index:" + FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME + ":" + principalName;
    }

    private String getExpirationsKey(long expiration) {
        return this.namespace + "expirations:" + expiration;
    }

    private String getExpiredKey(String sessionId) {
        return this.getExpiredKeyPrefix() + sessionId;
    }

    private String getExpiredKeyPrefix() {
        return this.namespace + "sessions:expires:";
    }
}
