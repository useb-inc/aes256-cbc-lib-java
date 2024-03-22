import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import kr.co.useb.AES256;

public class Main {
    public static void main(String[] args) throws Exception {
        // 암복호화 : 개발키 사용
        encrypt_decrypt_test_using_dev_key();

        // 암복호화 : 운영키 사용
        encrypt_decrypt_test_using_prod_key();

        // 파일 암호화 : 개발키 사용
        encrypt_file_test_using_dev_key();

        // 파일 암호화 : 운영키 사용
        encrypt_file_test_using_prod_key();

        // 파일 복호화 : 개발키 사용
        decrypt_file_test_using_dev_key();

        // 파일 복호화 : 운영키 사용
        decrypt_file_test_using_prod_key();
    }

    private static void encrypt_file_test_using_dev_key() {
        AES256 aes = new AES256();
        encrypt_file_test_impl(aes);
    }

    private static void encrypt_file_test_using_prod_key() {
        AES256 aes = new AES256("PROD");
        encrypt_file_test_impl(aes);
    }

    private static void encrypt_file_test_impl(AES256 aes) {
        String currentPath = Paths.get(System.getProperty("user.dir")).toString();

        try {
            // 평문 text 파일을 읽어서 암호화 하는 예시
            String filePath = Paths.get(currentPath, "testset", aes.getProfile().toLowerCase(), "input-plainData.txt").toString();
            String encryptedFromFile = aes.encryptFromFile(filePath);
            System.out.println("encrypted from file : ");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(encryptedFromFile);
            System.out.println("==========================================================================================");

            // 암호화된 내용을 파일로 쓰는 예시
            String encryptedFilePath = Paths.get(currentPath, "testset", aes.getProfile().toLowerCase(), "output-encrypted.txt").toString();
            writeOutputFile(encryptedFromFile, encryptedFilePath);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void decrypt_file_test_using_dev_key() {
        AES256 aes = new AES256();
        decrypt_file_test_impl(aes);
    }

    private static void decrypt_file_test_using_prod_key() {
        AES256 aes = new AES256("PROD");
        decrypt_file_test_impl(aes);
    }

    private static void decrypt_file_test_impl(AES256 aes) {
        String currentPath = Paths.get(System.getProperty("user.dir")).toString();
        try {
            // 암호화된 text 파일(을 읽어서 복호화 하는 예시
            String filePath = Paths.get(currentPath, "testset", aes.getProfile().toLowerCase(), "input-encryptedData.txt").toString();
            String decryptedFromFile = aes.decryptFromFile(filePath);
            System.out.println("decrypted from file : ");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(decryptedFromFile);
            System.out.println("==========================================================================================");

            // 복호화된 내용을 파일로 쓰는 예시
            String decryptedFilePath = Paths.get(currentPath, "testset", aes.getProfile().toLowerCase(), "output-decrypted.txt").toString();
            writeOutputFile(decryptedFromFile, decryptedFilePath);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void writeOutputFile(String decryptedFromFile, String decryptedFilePath) {
        try {
            File file = new File(decryptedFilePath); // File객체 생성
            if(!file.exists()){ // 파일이 존재하지 않으면
                file.createNewFile(); // 신규생성
            }

            // BufferedWriter 생성
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(decryptedFilePath), "UTF8"));

            // 파일에 쓰기
            writer.write(decryptedFromFile);
            writer.newLine();

            // 버퍼 및 스트림 뒷정리
            writer.flush(); // 버퍼의 남은 데이터를 모두 쓰기
            writer.close(); // 스트림 종료
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void encrypt_decrypt_test_using_dev_key() {
        AES256 aes = new AES256();
        encrypt_test_impl(aes);
    }

    private static void encrypt_decrypt_test_using_prod_key() {
        AES256 aes = new AES256("PROD");
        encrypt_test_impl(aes);
    }


    private static void encrypt_test_impl(AES256 aes) {
        // 평문 string값을 암호화하는 예시
        String src = "{\"idType\":\"2\",\"userName\":\"홍길동\",\"driverNo\":\"13-03-123456-11\",\"juminNo1\":\"910101\",\"juminNo2\":\"1001234\",\"_juminNo2\":\"1******\",\"issueDate\":\"20170131\",\"transaction_id\":\"14740463576391927d859b91670484605\"}";

        try {
            String encrypted = aes.encrypt(src);
            System.out.println("encrypted : ");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(encrypted);
            System.out.println("==========================================================================================");

            // 암호화된 string값을 복호화하는 예시
            String decrypted = aes.decrypt(encrypted);
            System.out.println("decrypted : ");

            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(decrypted);
            System.out.println("==========================================================================================");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}