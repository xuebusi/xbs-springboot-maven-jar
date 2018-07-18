package com.xuebusi.springboot.maven.mapper;

import com.xuebusi.springboot.maven.config.RedisCache;
import com.xuebusi.springboot.maven.entity.Person;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by xuebusi.com on 2017/8/16.
 */
@Mapper
@CacheNamespace(implementation = com.xuebusi.springboot.maven.config.RedisCache.class)
public interface PersonMapper {

    @Delete({
            "delete from person",
            "where person_id = #{personId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer personId);

    /**
     * 注意数据库person表的主键id是自增的
     * 所以插入时不需要插入主键字段
     * @param record
     * @return
     */
    @Insert({
            "insert into person (person_name, gender, ",
            "person_addr, birthday)",
            "values (#{personName,jdbcType=VARCHAR}, #{gender,jdbcType=INTEGER}, ",
            "#{personAddr,jdbcType=VARCHAR}, #{birthday,jdbcType=DATE})"
    })
    @Options(useGeneratedKeys=true, keyProperty="personId",keyColumn = "person_id")
    int insert(Person record);

    @Select({
            "select",
            "person_id, person_name, gender, person_addr, birthday",
            "from person",
            "where person_id = #{personId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="person_id", property="personId", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="person_name", property="personName", jdbcType= JdbcType.VARCHAR),
            @Result(column="gender", property="gender", jdbcType= JdbcType.INTEGER),
            @Result(column="person_addr", property="personAddr", jdbcType= JdbcType.VARCHAR),
            @Result(column="birthday", property="birthday", jdbcType= JdbcType.DATE)
    })
    Person selectByPrimaryKey(Integer personId);

    @Select({
            "select",
            "person_id, person_name, gender, person_addr, birthday",
            "from person"
    })
    @Results({
            @Result(column="person_id", property="personId", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="person_name", property="personName", jdbcType= JdbcType.VARCHAR),
            @Result(column="gender", property="gender", jdbcType= JdbcType.INTEGER),
            @Result(column="person_addr", property="personAddr", jdbcType= JdbcType.VARCHAR),
            @Result(column="birthday", property="birthday", jdbcType= JdbcType.DATE)
    })
    List<Person> findAll();

    @Update({
            "update person",
            "set person_name = #{personName,jdbcType=VARCHAR},",
            "gender = #{gender,jdbcType=INTEGER},",
            "person_addr = #{personAddr,jdbcType=VARCHAR},",
            "birthday = #{birthday,jdbcType=DATE}",
            "where person_id = #{personId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Person record);
}
