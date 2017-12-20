package com.xuebusi.springboot.maven.controller;

import com.alibaba.fastjson.JSON;
import com.xuebusi.springboot.maven.entity.Person;
import com.xuebusi.springboot.maven.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * redis测试
 * Created by xuebusi.com on 2017/4/13.
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/person")
    public String person() {
        String key = "person";
        List<Person> list = personService.findAll();
        String jsonString = JSON.toJSONString(list);
        try {
            redisTemplate.opsForValue().set(key, jsonString);
            logger.info("数据缓存:key={}, value={}", key, jsonString);
        } catch (Exception e) {
            return "数据缓存失败";
        }
        return "数据缓存成功";
    }

    @GetMapping(value = "/get")
    public String get(@RequestParam("key") String key) {
        String value = null;
        try {
            value = (String) redisTemplate.opsForValue().get(key);
            logger.info("查询缓存:key={}, value={}", key, value);
        } catch (Exception e) {
            return "查询缓存失败";
        }
        return value;

    }

    @GetMapping(value = "/set")
    public String set(@RequestParam("key") String key, @RequestParam("value") String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            logger.info("设置缓存:key={}, value={}", key, value);
        } catch (Exception e) {
            return "设置缓存失败";
        }
        return "数据缓存成功";
    }

    @GetMapping(value = "/del")
    public String del(@RequestParam("key") String key) {
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            redisTemplate.delete(key);
            logger.info("删除缓存:key={}, value={}", key, value);
        } catch (Exception e) {
            return "删除缓存失败";
        }
        return "删除缓存成功";

    }
}  