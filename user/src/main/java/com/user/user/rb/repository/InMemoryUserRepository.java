package com.user.user.rb.repository;

import com.user.user.rb.DTO.ApiResponse;
import com.user.user.rb.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;


//인터페이스 구현화
public class InMemoryUserRepository implements UserRepositroy{

    //순수 자바 로그 활성화


    private final Logger log = LoggerFactory.getLogger(getClass());
    //private static final HashMap<Integer,User> store = new HashMap<>();
    private List<User> userList = new ArrayList<>();
    //메모리에 임시로 저장하기 위해 Map객체를 선언하고 키와 값을 Integer,User형태로 해쉬맵 메모리에 저장한다.
    //유저 객체를 저장할수록 sequence 값이 1씩 증가한다.
    //맵을 활용하는 방법,list를 활용하는 방법
    private static int sequence = 0;


    //일반 user 생성 List X
    /*
    @Override
    public ApiResponse save(User user) {
                user.setId(++sequence);
                store.put(user.getId(),user);
        //인트 키와 유저 값을 가지고 있는 store에 user의 id값과 user객체를 넣는다.
        //HashMap 메소드 V put (K Key, V value)
        //인자로 주어진 key=value 쌍을 HashMap에 추가한다. 만약 이미 HashMAp안에 Key가 존재할 경우, 나중에 put된 value가 들어간다.


        return new ApiResponse(HttpStatus.OK.value(),"9223372036854774549",user);
    }

     */
    @Override
    public ApiResponse save(User user) {

        userList.add(user);
        for(int i = 0;i<userList.size();i++){
            user.setId(++sequence);
        }
        System.out.println(userList);
        return new ApiResponse(HttpStatus.OK.value(),"9223372036854774549",user);
    }

/*  에러처리 연습 NullPointerExcption
        try{
            user.setId(++sequence);
            if(user.getId() != 0){

            }else{
                throw new NullPointerException("userId가 없습니다.");
            }

        }catch (NullPointerException e){
            e.getMessage();
        }
*/

    @Override
    public Optional<User> findById(int id) {


        return Optional.ofNullable(userList.get(id));
        //Optional NullPorintExpetion을 방지하여 null올 수 있는 감싸는 Wrapper클래스이다.
        //Optional.ofNullbale()-값이 null일수도 아닐수도 있는 경우
        //id의 데이터가 null일수도,null이 아닐수도 있다. 여기서는 store객체에 저장된 id값이 null일수도 아닐수도 있다.

    }
/* 맵을 활용하여 유저찾기
    @Override
    public Optional<User> findByName(String username) {

            return store.values().stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findAny();

    }

 */
/*
처음 작성한 User 비교 로직
@Override
public Optional<User> findByName(List<User> username) {

//    userList.equals(username);
        userList.indexOf(username);
        return  userList.stream()
                .filter(user -> userList.equals(username))
                .findAny();
    }

 */
    /*
    1. 파라미터 문제 메소드의 파라미터가 List<User> username 인데
    username 파라미터면 string을 기대하게 됨 단일 username을 찾는 과정이니 username을 스트링으로 받아내야함
    2.잘못된 비교 로직 -> userList.equals(username)는 list와 string을 비교할려고함 무조건 false가 나옴
    userList.indexOf(username)도 String을 List에서 찾으려하지만 타입이 맞지않음
    3.스트림 사용 ->  filter(user -> userList.equals(username))잘못된 비교,사용자 객체의 username속성을 검사
    userList와는 비교하면 안됨
     */
    @Override
    public  Optional<User> findByName(String username){
        return userList.stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();

    }
/* 첫번째 시도한 방법 리턴타입을 풀 방법을 찾지못함 for문으로 제거하는건 온전하게 삭제하질못함
    @Override
    public Optional<User> deleteByName(String username) {
        User deleteId = null;
        for(int i=userList.size()-1; i >=0; i--){
            deleteId = userList.get(i);
            userList.remove(deleteId);

        }

        return userList.remove(deleteId);


    }

 */
@Override
public boolean deleteByName(String username) {
    List<User> filteredList = userList.stream()
            .filter(user -> !user.getUsername().equals(username))
            .collect(Collectors.toList());
    boolean isRemoved = filteredList.size() < userList.size();
    userList = filteredList;

    return isRemoved;
}



/*
    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
        //findAll 메소드가 실행되면 store에 저장된 값을 ArrayList에 저장한다.
    }
*/
//    @Override
//    public ApiResponse deleteByName(String username) {
//        //스트림으로 필터링해서 같은 이름을 받아내고 스트림메서드 내에서 map.remove사용 가능?
//
//        return (ApiResponse) store.values().stream()
//                .filter(user -> user.getUsername().equals(username))
//                .map(user -> store.remove(username));
//
//
//    }

//    @Override
//    public ApiResponse deleteByName(String username) {
//        //Api 딜리트 신호를 던져줘야함
//
//        store.values().remove(username);
//        //먼저 delete 됬을 때 http 통신코드 400이 나오도록 해야함
//        return new ApiResponse(HttpStatus.OK.value(),"Invalid username supplied",username);
//    }

    public void clearList(){
        userList.clear();
    }
}
