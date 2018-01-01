package com.xuebusi.springboot.maven.controller;

import com.alibaba.fastjson.JSON;
import com.xuebusi.springboot.maven.entity.Person;
import com.xuebusi.springboot.maven.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
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
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            ops.set(key, jsonString);
        } catch (Exception e) {
            return "缓存失败";
        }
        return jsonString;
    }

    @GetMapping(value = "/get")
    public String get(@RequestParam("key") String key) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(key);
        return StringUtils.isEmpty(value) ? "空" : value;

    }

    @GetMapping(value = "/set")
    public Boolean set(@RequestParam("key") String key, @RequestParam("value") String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
