# cloud_practice
네이버 클라우드를 이용한 클라우드 실습 repository입니다.

## Mission 1
네이버 클라우드에서 서버를 발급 받은 후, Spring Boot 기반으로 누구나 요청했을 때 아래 JSON 응답을 받을 수 있는 API를 만들어서 배포 후, URL을 공유한다.

```json
{
  "result" : "hello-world"
}
```

## Mission 2
Mission 1 에서 만든 프로젝트에 각자가 정한 룰에 맞는 CI 를 자유롭게 적용한다. (Github Actions, Jenkins 또는 다른 툴 이용)
* Github Actions 이용
* Gradle Cache 적용해서 빌드 시간 감소
* 빌드 및 테스트 코드 실행해서 정상 작동 확인

## Mission 3
* 간단히 재고 시스템
* Product 엔터티를 기반으로 간단한 재고 관리 시스템을 구현
````
[Product]
- id (자동 생성)
- name
- price
- stockQuantity
````
* 구매 API 구현: API 를 통해 상품을 구매하면 stockQuantity가 줄어드는 API를 구현

