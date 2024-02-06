package com.sesac.sesacspring.mybatis.service;

import com.sesac.sesacspring.mybatis.domain.User;
import com.sesac.sesacspring.mybatis.dto.UserDTO;
import com.sesac.sesacspring.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    // UserMapper 호출 방법 2가지
    // 1. 생성자 사용
//    private final UserMapper userMapper;
//    public UserService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    // 2, Autowired 사용
    @Autowired
    UserMapper userMapper;

    public List<UserDTO> retrieveAll(){
        // controller 에서 호출하는 메소드
        // 이 안에서는 usermapper 의 retrieveAll() 을 실행한 후 받아온 List<User> 를 리턴! 단, 이대로 리턴하지 않고
        // List<UserDTO> 로 DTO 에 담아서 반환할 것임!

        List<User> users = userMapper.retreiveAll();
        List<UserDTO> result = new ArrayList<>();

        // for 문을 이용해서 List<User> -> List<UserDTO> 로 변경
        for ( User user : users) {
            UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setNickname(user.getNickname());

        result.add(userDTO);
        }

        return result;

        // userMapper.retreiveAll();
        // 위 코드가 아래와 같은 절차로 작동됨.
        // 1) userService.retreiveAll() 에서 의존성을 주입받은 userMapper.retreiveAll() 호출
        // 2) UserMapper interface 파일에서 xml 파일 확인 필요 여부 체크!
        // 3) 정의된 mapper 폴더(application.properties 에서 정의)에서 namespace 가 UserMapper 인 xml 검색
        // 4) id 가 retrieveAll 인 태그를 찾고 그 안의 sql 문을 실행
        // 5) 실행 결과를 resultType 에 정의된 객체에 매핑해서 반환

    }

    public void createUser (String name, String nickname){
        userMapper.createUser(name,nickname);
    }

    public void editUser (int id, String nickname){
        userMapper.editUser(id, nickname);
    }

    public void deleteUser (int id){
        userMapper.deleteUser(id);
    }
}
