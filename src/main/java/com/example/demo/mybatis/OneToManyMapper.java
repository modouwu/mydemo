package com.example.demo.mybatis;

import com.example.demo.mybatis.model.Clazz;
import com.example.demo.mybatis.model.Student;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OneToManyMapper {
    @Select("select * from tb_student where clazz_id=#{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="sex",property="sex"),
            @Result(column="age",property="age")
    })
    List<Student> selectByClazzId(Integer id);

    @Select("select * from tb_clazz where id = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="code",property="code"),
            @Result(column="name",property="name"),
            @Result(column="id",property="students",
                    many=@Many(
                            select="com.example.demo.mybatis.OneToManyMapper.selectByClazzId",
                            fetchType= FetchType.LAZY
                    )
            )
    })
    Clazz selectClazzByIdToManyStudent(Integer id);
}
