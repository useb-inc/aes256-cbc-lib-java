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

1-1. 암호화키를 개발용과 운영용을 별도로 적용하는 경우
```java
/* 
 AES256 객체 생성시 profile 인자에 "DEV" 또는 "PROD"를 지정하여 개발용 암호화키와 운영용 암호화키를 선택할 수 있습니다.
 미지정시에는 default로 profile이 "DEV"로 지정되며 개발용 암호화키를 사용합니다. 
*/
new AES256("DEV");      // 개발용 암호화키 사용
new AES256();           // 미지정시 개발용 암호화키 사용
        
// 운영용 암호화키 사용
new AES256("PROD");     // 운영용 암호화키 사용

/*
 환경변수로 ALCHERA_AES256_KEY 값을 "PROD"로 지정하는 경우, 
 new AES256() 생성자를 사용하더라도 운영키가 사용됩니다.
*/

$ export ALCHERA_AES256_PROFILE=PROD      // shell 에서 환경변수 셋팅
new AES256()               // 환경변수 셋팅된 경우 기본 생성자를 사용하더라도 운영용 암호화키 사용
```

2. key와 iv를 환경 변수로 전달하여 사용하는 방법
```java
/* 
 AES256 객체 생성하기 전에 환경변수에 ALCHERA_AES256_KEY, ALCHERA_AES256_IV 값을 셋팅하시고 제목 없는 문서
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
