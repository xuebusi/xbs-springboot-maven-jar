package com.xuebusi.springboot.maven.service;

import com.xuebusi.springboot.maven.common.ServiceException;
import com.xuebusi.springboot.maven.common.ViewHint;
import com.xuebusi.springboot.maven.entity.Person;
import com.xuebusi.springboot.maven.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by xuebusi.com on 2017/4/13.
 */
@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    public List<Person> findAll() {
        List<Person> personList = personMapper.findAll();
        return personList;
    }

    public int updateByPrimaryKey(Person record) {
        return personMapper.updateByPrimaryKey(record);
    }

    public int deleteByPrimaryKey(Integer personId) {
        return personMapper.deleteByPrimaryKey(personId);
    }

    public Person selectByPrimaryKey(Integer personId) throws Exception{
        if (StringUtils.isEmpty(personId)) {
            throw new ServiceException(ViewHint.KeyNotIsNull);
        }
        return personMapper.selectByPrimaryKey(personId);
    }

    public int insert(Person person) {
        return personMapper.insert(person);
    }

}
