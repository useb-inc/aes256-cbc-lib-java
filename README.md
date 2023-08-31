## AES256.jar Library

### 샘플코드의 빌드 및 실행

#### 빌드명령어 
```bash
$ javac -cp libs/AES256.jar -encoding UTF-8 Main.java
```

#### 실행명령어
리눅스 / Mac
```bash
java -cp .:libs/AES256.jar Main
```

윈도우
```bash
java -cp .;libs/AES256.jar Main
```
 

### 라이브러리 사용법

#### 암호화 key / iv 설정 관련 설명
AES256 모듈을 사용하기 위해서는 aes_key값과 aes_iv값이 필요합니다.
aes_key, aes_iv 입력 방법은 3가지 입니다.

1. 사용하는 곳에서는 별도 키관리를 하지 않고 lib가 난독화하여 관리하고 있는 암호화키를 그대로 사용하는 경우
```java
/* 
 AES256 객체 생성하기  전에 환경변수에 aes_key, aes_iv 값을 셋팅하지 않거나 ""(빈값)으로 셋팅한 상태에서, 제목 없는 문서
 default 생성자로 객체를 생성하여서 사용하시면 됩니다.
*/
new AES256();
```

2. key와 iv를 환경 변수로 전달하여 사용하는 방법
```java
/* 
 AES256 객체 생성하기 전에 환경변수에 aes_key, aes_iv 값을 셋팅하시고 제목 없는 문서
 default 생성자로 객체를 생성하여서 사용하시면 됩니다.
*/
new AES256();
```

3. key와 iv를 생성자로 인자로 전달하여 사용하는 방법
```java
new AES256("aes_key", "aes_iv");
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
