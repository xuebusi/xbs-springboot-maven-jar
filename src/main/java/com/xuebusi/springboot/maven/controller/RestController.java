package com.xuebusi.springboot.maven.controller;

import com.alibaba.fastjson.JSON;
import com.xuebusi.springboot.maven.common.util.FileUtil;
import com.xuebusi.springboot.maven.dto.MmsDto;
import com.xuebusi.springboot.maven.dto.UserDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/rest")
public class RestController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 测试get请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}")
    public String getUserById(@PathVariable("id") Integer id) {
        // 根据id查询用户信息
        String reqUrl = "http://localhost:8088/user/" + id;
        String result = restTemplate.getForObject(reqUrl, String.class);
        return result;
    }

    /**
     * 测试get请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getForObject1")
    public String getForObject1() {
        // 查询所有用户信息
        String reqUrl = "http://localhost:8088/user/findAll";
        String result = restTemplate.getForObject(reqUrl, String.class);
        return result;
    }

    /**
     * 测试get请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getForObject2")
    public String getForObject2(@RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/user/findPage?pageNum=" + pageNum + "&pageSize=" + pageSize;
        String result = restTemplate.getForObject(reqUrl, String.class);
        return result;
    }

    /**
     * 测试get请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getForObject3")
    public String getForObject3(@RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/user/findPage";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        String result = restTemplate.getForObject(reqUrl, String.class, paramMap);
        return result;
    }

    /**
     * 测试get请求
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getForObject4")
    public String getForObject4(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize) {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/user/findPage";
        String result = restTemplate.getForObject(reqUrl, String.class, pageNum, pageSize);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getForEntity1")
    public String getForEntity1(@RequestParam(value = "pageNum") Integer pageNum,
                                @RequestParam(value = "pageSize") Integer pageSize) {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/user/findPage?pageNum=" + pageNum + "&pageSize=" + pageSize;
        String result = "";
        ResponseEntity<String> entity = restTemplate.getForEntity(reqUrl, String.class);
        if (entity != null && entity.getStatusCode() == HttpStatus.OK) {
            result = entity.getBody();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getForEntity2")
    public String getForEntity2(@RequestParam(value = "pageNum") Integer pageNum,
                                @RequestParam(value = "pageSize") Integer pageSize) {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/user/findPage";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        String result = "";
        ResponseEntity<String> entity = restTemplate.getForEntity(reqUrl, String.class, paramMap);
        if (entity != null && entity.getStatusCode() == HttpStatus.OK) {
            result = entity.getBody();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getForEntity3")
    public String getForEntity3(@RequestParam(value = "pageNum") Integer pageNum,
                                @RequestParam(value = "pageSize") Integer pageSize) {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/user/findPage";
        String result = "";
        ResponseEntity<String> entity = restTemplate.getForEntity(reqUrl, String.class, pageNum, pageSize);
        if (entity != null && entity.getStatusCode() == HttpStatus.OK) {
            result = entity.getBody();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/postForObject1")
    public String postForObject1(@RequestParam(value = "pageNum") Integer pageNum,
                                @RequestParam(value = "pageSize") Integer pageSize) {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/user/findPage";
        String reqBody = "pageNum=" + pageNum + "&pageSize=" + pageSize;
        HttpEntity requestEntity = new HttpEntity(reqBody);
        String result = restTemplate.postForObject(reqUrl, requestEntity, String.class);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/postForObject2")
    public String postForObject2() {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/user/add";
        UserDto userDto = new UserDto();
        userDto.setAddress("北京");
        userDto.setAge("18");
        userDto.setName("张三凤");
        userDto.setPhone("15801081566");
        userDto.setPrice("256");
        userDto.setStatus("created");
        String reqBody = JSON.toJSONString(userDto);

        // 请求头
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Content-type", "application/json;charset=utf-8");

        // 请求body
        HttpEntity requestEntity = new HttpEntity(reqBody, headers);
        String result = restTemplate.postForObject(reqUrl, requestEntity, String.class);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/exchange1")
    public String exchange1() {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/user/add";
        UserDto userDto = new UserDto();
        userDto.setAddress("北京");
        userDto.setAge("18");
        userDto.setName("张三凤");
        userDto.setPhone("15801081566");
        userDto.setPrice("256");
        userDto.setStatus("created");
        String reqBody = JSON.toJSONString(userDto);

        // 请求头
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Content-type", "application/json;charset=utf-8");

        // 请求body
        HttpEntity requestEntity = new HttpEntity(reqBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(reqUrl, HttpMethod.POST, requestEntity, String.class);
        String result = null;
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/sendMms2")
    public String sendMms2() {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/mms/sendMms2";
        String requestBody = "title=测试发送彩信&content=彩信内容xxx&mobile=15901081566&sendType=1";

        // 请求头
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Content-type", "application/x-www-form-urlencoded");

        // 请求body
        HttpEntity requestEntity = new HttpEntity(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(reqUrl, HttpMethod.POST, requestEntity, String.class);
        String result = null;
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/sendMms3")
    public String sendMms3() {
        // 分页查询用户信息
        String reqUrl = "http://localhost:8088/mms/sendMms2";
//        MmsDto mmsDto = new MmsDto();
//        mmsDto.setTitle("测试发送彩信2");
//        mmsDto.setContent("彩信内容xxx2");
//        mmsDto.setMobile("15901081566");
//        mmsDto.setSendType("1");
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("title", "测试发送彩信2");
        reqMap.put("content", "彩信内容xxx2");
        reqMap.put("mobile", "15901081566");
        reqMap.put("sendType", "1");

        // 请求头
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Content-type", "application/x-www-form-urlencoded");

        // 请求body
        HttpEntity requestEntity = new HttpEntity(reqMap, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(reqUrl, requestEntity, String.class);
        String result = null;
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/sendMms4")
    public String sendMms4() {

        // 读取zip
        byte[] binaryData = FileUtil.readContent("Report.zip");
        // 使用Base64编码
        String base64Data = Base64.encodeBase64String(binaryData);

        // 分页查询用户信息
//        String reqUrl = "http://localhost:8088/mms/sendMms2";
        String reqUrl = "http://mmsplat.eucp.b2m.cn/MMSCenterInterface/MMS.asmx/SendMMS";
        String requestBody = "userName=bf-test&password=c5f1213ce8&title=testmms&userNumbers=15801081566&sendType=1&MMSContent=" + base64Data;

        // 请求头
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Content-type", "application/x-www-form-urlencoded");
        headers.add("Content-Length", String.valueOf(requestBody.length()));

        // 请求body
        HttpEntity requestEntity = new HttpEntity(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(reqUrl, HttpMethod.POST, requestEntity, String.class);
        String result = null;
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/sendMms5")
    public String sendMms5() {

        // 读取zip
        byte[] binaryData = FileUtil.readContent("Report.zip");
        // 使用Base64编码
        String base64Data = Base64.encodeBase64String(binaryData);

        // 发送彩信url
        String reqUrl = "http://mmsplat.eucp.b2m.cn/MMSCenterInterface/MMS.asmx/SendMMS";
        // 请求body
        String requestBody = "userName=bf-test&password=c5f1213ce8&title=testmms&userNumbers=15801081566&sendType=1&MMSContent=" + base64Data;

        // 请求头
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Content-type", "application/x-www-form-urlencoded");

        // 请求body
        HttpEntity requestEntity = new HttpEntity(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(reqUrl, HttpMethod.POST, requestEntity, String.class);
        String result = null;
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getMMSCount")
    public String getMMSCount() {

        // 查询账户余额接口地址
        String reqUrl = "http://mmsplat.eucp.b2m.cn/MMSCenterInterface/MMS.asmx/GetMMSCount";
        String requestBody = "userName=bf-test&password=c5f1213ce8&sendType=1";

        // 请求头
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Content-type", "application/x-www-form-urlencoded");
        headers.add("Content-Length", String.valueOf(requestBody.length()));

        // 请求body
        HttpEntity requestEntity = new HttpEntity(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(reqUrl, HttpMethod.POST, requestEntity, String.class);
        String result = null;
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getStatusReport")
    public String getStatusReport() {

        // 查询账户状态接口地址
        String reqUrl = "http://mmsplat.eucp.b2m.cn/MMSCenterInterface/MMS.asmx/GetStatusReport";
        String requestBody = "userName=bf-test&password=c5f1213ce8";

        // 请求头
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("Content-type", "application/x-www-form-urlencoded");
        headers.add("Content-Length", String.valueOf(requestBody.length()));

        // 请求body
        HttpEntity requestEntity = new HttpEntity(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(reqUrl, HttpMethod.POST, requestEntity, String.class);
        String result = null;
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
        }
        return result;
    }

}
