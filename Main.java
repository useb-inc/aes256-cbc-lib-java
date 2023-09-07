import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import kr.co.useb.AES256;

public class Main {
    public static void main(String[] args) throws Exception {
        AES256 aes = new AES256();

        // 평문 string값을 암호화하는 예시
        String src = "{\"idType\":\"2\",\"userName\":\"홍길동\",\"driverNo\":\"13-03-123456-11\",\"juminNo1\":\"910101\",\"juminNo2\":\"1001234\",\"_juminNo2\":\"1******\",\"issueDate\":\"20170131\",\"transaction_id\":\"14740463576391927d859b91670484605\"}";

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

        String currentPath = Paths.get(System.getProperty("user.dir")).toString();
        try {
            // 암호화된 text 파일(을 읽어서 복호화 하는 예시
            String filePath = Paths.get(currentPath, "testset", "encrypted.txt").toString();
            String decryptedFromFile = aes.decryptFromFile(filePath);
            System.out.println("decrypted from file : ");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(decryptedFromFile);
            System.out.println("==========================================================================================");
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            // 평문 text 파일을 읽어서 암호화 하는 예시
            String filePath = Paths.get(currentPath, "testset", "plainData.txt").toString();
            String encryptedFromFile = aes.encryptFromFile(filePath);
            System.out.println("encrypted from file : ");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(encryptedFromFile);
            System.out.println("==========================================================================================");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}