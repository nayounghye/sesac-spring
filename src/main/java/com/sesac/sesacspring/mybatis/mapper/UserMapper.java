package com.sesac.sesacspring.mybatis.mapper;

import com.sesac.sesacspring.mybatis.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    // sql 문을 정의 or xml 파일 읽기 -> Mapper 는 두 작업 중 하나를 진행

    // xml 파일을 읽어서 실행한다! (@Selector 가 안붙으면 xml 파일로 읽는 거라 생각하면 됨)
    List<User> retreiveAll();

    // sql 문 정의
    // name, nickname 각각 보냄
    @Insert("insert into user(name, nickname) values(#{name}, #{nickname})")
    void createUser(
            String name, String nickname
    );

    // 이렇게 user에 전체 담아서 보내는 것도 가능
//    @Insert("insert into user(name, nickname) values(#{name}, #{nickname})")
//    void createUser(
//           User user
//    );

    @Update("UPDATE user SET nickname=#{nickname} WHERE id=#{id}")
    void editUser(
            int id, String nickname
    );

    @Delete("DELETE FROM user WHERE id=#{id}")
    void deleteUser(
            int id
    );
}

