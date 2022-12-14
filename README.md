# demo-redis
* [블로그](https://velog.io/@hj20220908/Redis-%EC%84%A4%EC%B9%98-%EB%B0%8F-%EC%82%AC%EC%9A%A9%EB%B2%95)

## Redis 명령어
- Redis 실행

    ```
    C:\Program Files\Redis>redis-server.exe redis.windows.conf
    [5948] 15 Sep 07:43:34.062 # Creating Server TCP listening socket *:6379: bind: No error
    
    C:\Program Files\Redis>netstat -an|findstr 6379
      TCP    0.0.0.0:6379           0.0.0.0:0              LISTENING
      TCP    [::]:6379              [::]:0                 LISTENING
    
    C:\Program Files\Redis>redis-cli
    127.0.0.1:6379>
    ```

- 데이터 저장하기

    ```
    127.0.0.1:6379> set hello world
    OK
    127.0.0.1:6379> get hello
    "world"
    127.0.0.1:6379> keys *
    1) "hello"
    2) "key"
    127.0.0.1:6379>
    ```

- 데이터 여러개 저장하기

    ```
    127.0.0.1:6379> mset number_one 1111 number_two 2222
    OK
    127.0.0.1:6379> keys *
    1) "hello"
    2) "number_one"
    3) "number_two"
    4) "key"
    127.0.0.1:6379>
    ```

- 키 특정 단어 검색

    ```
    127.0.0.1:6379> keys *
    1) "hello"
    2) "number_one"
    3) "number_two"
    4) "key"
    127.0.0.1:6379> keys *number*
    1) "number_one"
    2) "number_two"
    ```

- 데이터 만료시간 설정

    ```
    127.0.0.1:6379> setex bye 30 bye
    OK
    ```

- 데이터 만료시간 확인

    ```
    127.0.0.1:6379> setex bye 30 bye
    OK
    127.0.0.1:6379> ttl bye
    (integer) 29
    127.0.0.1:6379> ttl bye
    (integer) -2
    ```

    - return 값 -2은 마이너스일 경우 키값이 존재하지 않거나 소멸된 것
    - return 값 -1은 기한이 없는 키값
- 여러 데이터 조회하기

    ```
    127.0.0.1:6379> mget number_one number_two
    1) "1111"
    2) "2222"
    ```

- key 이름 변경

    ```
    127.0.0.1:6379> rename hello hhh
    OK
    127.0.0.1:6379> keys *
    1) "number_one"
    2) "key"
    3) "hhh"
    4) "number_two"
    ```

- key 삭제

    ```
    127.0.0.1:6379> del hhh
    (integer) 1
    127.0.0.1:6379> del mmm
    (integer) 0
    ```

    - return 값 1은 삭제 성공
    - return 값 0은 데이터 없음
- 모든 데이터 삭제

    ```
    127.0.0.1:6379> flushall
    OK
    127.0.0.1:6379> keys *
    (empty list or set)
    ```
