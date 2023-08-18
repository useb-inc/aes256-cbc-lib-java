## AES256.jar Library
AES256 모듈을 사용하기 위해서는 aes_key값과 aes_iv값이 필요합니다.
aes_key, aes_iv 입력 방법은 2가지 입니다.

1. 생성자로 전달
```java
new AES256("aes_key", "aes_iv");
```
2. 환경 변수로 전달
```java
/* 
 AES256 객체 생성하기 전에 환경변수에 aes_key, aes_iv 값을 셋팅하시고 제목 없는 문서
 default 생성자로 객체를 생성하여서 사용하시면 됩니다.
*/
new AES256();
```

aes_key와 aes_iv 값이 제대로 입력되지 않은 상태에서 관련 method 사용시 예외발생
```java
java.lang.Exception: To use encryption, you need the AES key and AES IV values. 
You can either populate values in the environment variables 'aes_key' and 'aes_iv' or utilize the AES256 constructor parameters.
```

### Method 사용법
#### encrypt
문자열을 AES256 알고리즘을 이용하여 암호화한 문자열을 반환하는 함수 
```java
String encrypted = AES256.encrypt("암호화할 문자열");
```
#### decrypt
AES256 알고리즘을 이용하여 암호화한 문자열을 복호화하여 반환하는 함수 
```java
String decrypted = AES256.decrypt("복호화할 문자열");
```
#### decryptFromFile
암호화한 문자열파일 경로를 입력받아 복호화하여 반환하는 함수
```java
String decrypted = AES256.decryptFromFile("파일 경로");
```
#### encryptFromFile
평문 파일 경로를 입력받아 암호화하여 반환하는 함수
```java
String encrypted = AES256.encryptFromFile("파일 경로");
```
