package com.xuebusi.springboot.maven.controller;

import com.xuebusi.springboot.maven.common.ResultModel;
import com.xuebusi.springboot.maven.entity.Person;
import com.xuebusi.springboot.maven.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 测试类
 * Created by SYJ on 2017/4/13.
 */
@RestController
@RequestMapping
public class PersonController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personService;

	/**
	 * 测试
	 * @return
	 */
	@GetMapping
	public ResultModel test() {
		long time = System.currentTimeMillis();
		ResultModel<Object> resultModel = new ResultModel<>();
		resultModel.setCode(200);
		resultModel.setMessage("测试");
		resultModel.setData(time);
		return resultModel;
	}

	/**
	 * 根据id查询
	 * @param personId
	 * @return
	 */
	@GetMapping("/person")
	public ResultModel selectById(@RequestParam("id") Integer personId) throws Exception {
		Person person = personService.selectByPrimaryKey(personId);
		ResultModel<Object> resultModel = new ResultModel<>();
		resultModel.setCode(200);
		resultModel.setData(person);
		return resultModel;
	}

	/**
	 * 根据id查询
	 * @param personId
	 * @return
	 */
	@GetMapping("/person/{id}")
	public ResultModel selectOne(@PathVariable("id") Integer personId) throws Exception {
		Person person = personService.selectByPrimaryKey(personId);
		ResultModel<Object> resultModel = new ResultModel<>();
		resultModel.setCode(200);
		resultModel.setData(person);
		return resultModel;
	}

	/**
	 * 查询所有
	 * @param request
	 * @return
	 */
	@GetMapping("/person/list")
	public ResultModel list(HttpServletRequest request) {
		List<Person> pList = personService.findAll();
		ResultModel<Object> resultModel = new ResultModel<>();
		resultModel.setCode(200);
		resultModel.setData(pList);
		return resultModel;
	}

	/**
	 * 添加
	 * @param person
	 * @return
	 */
	@PostMapping("/person/insert")
	public ResultModel insert(Person person) {
		personService.insert(person);
		ResultModel<Object> resultModel = new ResultModel<>();
		resultModel.setCode(200);
		resultModel.setData(person);
		return resultModel;
	}

	/**
	 * 修改
	 * @param person
	 * @return
	 */
	@PostMapping("/person/update")
	public ResultModel updateByPrimaryKey(Person person) {
		int count = personService.updateByPrimaryKey(person);
		ResultModel<Object> resultModel = new ResultModel<>();
		resultModel.setCode(200);
		resultModel.setData(count);
		return resultModel;
	}

	/**
	 * 根据id删除
	 * 该接口要求参数是Form表单的形式;
	 * 即Content-Type为application/x-www-form-urlencoded;
	 * 且请求body中的参数为id=12的形式;
	 * @param personId
	 * @return
	 */
	@PostMapping("/person/del/{id}")
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ResultModel deleteById(@PathVariable("id") Integer personId) {
		int count = personService.deleteByPrimaryKey(personId);
		ResultModel<Object> resultModel = new ResultModel<>();
		resultModel.setCode(200);
		resultModel.setData(count);
		return resultModel;
	}

	/**
	 * 根据id删除
	 * 该接口要求参数是json的形式
	 * @param person
	 * @return
	 */
	@PostMapping("/person/del")
	public ResultModel deleteById(Person person) {
		int count = personService.deleteByPrimaryKey(person.getPersonId());
		ResultModel<Object> resultModel = new ResultModel<>();
		resultModel.setCode(200);
		resultModel.setData(count);
		return resultModel;
	}

}
