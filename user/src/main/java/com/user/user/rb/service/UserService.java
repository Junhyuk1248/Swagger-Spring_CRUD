package com.user.user.rb.service;

import com.user.user.rb.DTO.ApiResponse;
import com.user.user.rb.domain.User;
import com.user.user.rb.repository.InMemoryUserRepository;
import com.user.user.rb.repository.UserRepositroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepositroy userRepositroy = new InMemoryUserRepository();
    //UsserSerivce클래스 내에서 데이터 접근을 위해 UserRepository 인터페이스를 사용하는 인스턴스 초기화
    //구현 클래스를 인터페이스 타입으로 참조 -> 의존성 주입 수행
    //private 멤버변수가 해당 클래스 내부에서만 접근 가능, 외부 접근 x , 클래스 캡슐화 강화
    //final: 변수가 초기화된 이후 변경될 수 없음을 의미,객체 생성 될 때 한번 초기화, 그 이후 참조 변경 x 불변성 보장
    //멀티스레드 환경 변수 안정성 보장,객체 지향 프로그래밍 안전 예측가능한 코드 작성 중요
//    public List<User> findUser(){
//        return userRepositroy.findAll();
//    }


    //User Create
    public ApiResponse saveUser(List<User> users) {
        //유저클래스를 참조하는 user key와 users 값으로 반복한다.
        // ApiResponse 클래스를 참조하는 User 객체를 userRespoitory의 save객체를 사용해서 생성한다.
        // 리스트에대한 이해도 부족

        for(User user : users){
            ApiResponse User = userRepositroy.save(user);
            log.info("info log={}", User);
        }

        //HTTP가 200일 경우 아래의 메시지가 출력됨
        return new ApiResponse(200,"회원이 등록되었습니다.","9223372036854774549");
    }



    //List 안에서 User
    public Optional<User> findByUsername(String username){
        //리스트내에서 특정 유저를 찾아내기


        return  userRepositroy.findByName(username);
    }

/* Map getUser
    public Optional<User> getUser(String username){
        return  userRepositroy.findByName(username);
    }
*/

    public boolean deleteByName(String username){

        return userRepositroy.deleteByName(username);
    }



//    public ApiResponse deleteUser(String username){
//        //유저 삭제 비즈니스 로직 구현
//        ApiResponse User = userRepositroy.deleteByName(username);
//        log.info("info log={}", User);
//        return new ApiResponse(400,"유저가 삭제되었습니다.","213124");
//    }



}
