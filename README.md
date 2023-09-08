# AES256.jar Library
## 샘플코드의 빌드 및 실행

### 빌드명령어 
```bash
$ javac -cp libs/AES256.jar -encoding UTF-8 Main.java
```

### 실행명령어
리눅스 / Mac
```bash
java -cp .:libs/AES256.jar Main
```

윈도우
```bash
java -cp .;libs/AES256.jar Main
```
 
---
## 라이브러리 사용법
### 암호화 key / iv 설정 관련 설명

> AES256 모듈을 사용하기 위해서는 aes_key값과 aes_iv값이 필요합니다.
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

---
## Method 사용법
### encrypt
문자열을 AES256 알고리즘을 이용하여 암호화한 문자열을 반환하는 함수 
```java
String encrypted = AES256.encrypt("암호화할 문자열");
```
### decrypt
AES256 알고리즘을 이용하여 암호화한 문자열을 복호화하여 반환하는 함수 
```java
String decrypted = AES256.decrypt("복호화할 문자열");
```
### decryptFromFile
암호화한 문자열파일 경로를 입력받아 복호화하여 반환하는 함수
```java
String decrypted = AES256.decryptFromFile("파일 경로");
```
### encryptFromFile
평문 파일 경로를 입력받아 암호화하여 반환하는 함수
```java
String encrypted = AES256.encryptFromFile("파일 경로");
```

---
## 참고 사항 ###
> JDK 버전에 따라 (JDK 8u151 이전버전인 경우)
암호화시 아래와 같이 Illegal key size 에러가 나는 경우가 있습니다. 
```
java.security.InvalidKeyException: Illegal key size
  at javax.crypto.Cipher.checkCryptoPerm(Cipher.java:1039)
  at javax.crypto.Cipher.implInit(Cipher.java:805)
  at javax.crypto.Cipher.chooseProvider(Cipher.java:864)
  at javax.crypto.Cipher.init(Cipher.java:1396)
  at javax.crypto.Cipher.init(Cipher.java:1327)
  ...
```

### 사유 
> Java의 JCE(Java Cryptography Extension)는 암호화 기능을 제공하는 확장 라이브러리입니다. 그러나 미국의 수출 통상법에 따라, 기본적으로 128bit만 지원되는데, 이는 AES-128 암호화까지만 지원한다는 것을 의미합니다. 따라서, AES-256 암호화를 사용하려 할 때, Java는 Illegal key size 에러를 반환합니다.


### 해결방법
#### JDK 8u151 이전 버전인 경우
> 아래 링크의 압축 파일을 다운받아 압축을 푼뒤 "local_policy.jar" 파일과 "US_export_policy.jar" 파일을 $JAVA_HOME/jre/lib/security 폴더에 복사합니다. 
- [JDK 7 (1.7)](./download/UnlimitedJCEPolicyJDK7.zip) 
- [JDK 8 (1.8)](./download/jce_policy-8.zip)

#### JDK 8u151 이후 버전인 경우
> JDK 8u151부터는 JCE 정책이 unlimited policy로 변경 되었습니다. 이로 인해 Java 설치 시 JCE Unlimited Strength 정책 파일이 기본적으로 포함됩니다. 

- 암호 키 길이 제한 해제하기
  - $JAVA_HOME\jre\lib\security 폴더로 이동 후 java.security 파일을 편집합니다. 그리고 #crypto.policy=unlimited 속성을 찾아 주석을 제거합니다.
