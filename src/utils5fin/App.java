package utils5fin;

import utils.CryptoUtils;
import utils.EncryptorAesGcm;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class App {

    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    private static final int AES_KEY_BIT = 256;

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    // AES-GCM needs GCMParameterSpec
    public static byte[] encrypt(byte[] pText, SecretKey secret, byte[] iv) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] encryptedText = cipher.doFinal(pText);
        return encryptedText;
    }

    // prefix IV length + IV bytes to cipher text
    public static byte[] encryptWithPrefixIV(byte[] pText, SecretKey secret, byte[] iv) throws Exception {

        byte[] cipherText = encrypt(pText, secret, iv);

        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length)
                .put(iv)
                .put(cipherText)
                .array();
        return cipherTextWithIv;

    }

    public static String decrypt(byte[] cText, SecretKey secret, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] plainText = cipher.doFinal(cText);
        return new String(plainText, UTF_8);
    }

    public static String decryptWithPrefixIV(byte[] cText, SecretKey secret) throws Exception {
        ByteBuffer bb = ByteBuffer.wrap(cText);

        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);
        //bb.get(iv, 0, iv.length);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        String plainText = decrypt(cipherText, secret, iv);
        return plainText;

    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        //System.out.println("------- Временная метка: " + calendar.getTime() + " --------");
        return "+------ Временная метка: " + calendar.getTime() + " -------+";
    }

    public static void main(String[] args) throws Exception {
        // скопируем оригинальное сообщение из файла message.txt в файл prepare.out
        FileInOut fio = new FileInOut("src/utils5fin/prepare.out");
        fio.copyOriginalMessage("src/utils5fin/message.txt");
        // и сделаем его вывод в консоли
        //System.out.println(fio.showEncryptedFile());

        // определяем что мы будем шифровать. Для этого мы прочитаем файл prepare.out
        //String pText = "Hello World AES-GCM, Welcome to Cryptography!";
        String pText = fio.showEncryptedFile();
        // Добавим в конце временную метку в следующем формате: "+------ Временная метка: Sun Nov 07 16:13:19 MSK 2021 -------+"
        pText = pText + getTime();

        String OUTPUT_FORMAT = "%-30s:%s";
        // для шифрования и дешифрования используем один и тот же ключ
        // get AES 256 bits (32 bytes) key
        SecretKey secretKey = utils.CryptoUtils.getAESKey(AES_KEY_BIT);

        // encrypt and decrypt need the same IV.
        // AES-GCM needs IV 96-bit (12 bytes)
        byte[] iv = utils.CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);
        byte[] encryptedText = EncryptorAesGcm.encryptWithPrefixIV(pText.getBytes(UTF_8), secretKey, iv);

        System.out.println("\n------ AES GCM Encryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "На входе (plain text)", pText));
        System.out.println(String.format(OUTPUT_FORMAT, "Ключ шифрования Key (hex)", utils.CryptoUtils.hex(secretKey.getEncoded())));
        //System.out.println(String.format(OUTPUT_FORMAT, "IV  (hex)", utils.CryptoUtils.hex(iv)));
        System.out.println(String.format(OUTPUT_FORMAT, "Зашифрованный (hex) ", utils.CryptoUtils.hex(encryptedText)));
        //System.out.println(String.format(OUTPUT_FORMAT, "Зашифрованный (hex) (block = 16)", utils.CryptoUtils.hexWithBlockSize(encryptedText, 16)));
        // сохраним полученный зашифрованный текст в файл. Для этого используем метод write класса TextFile
        TextFile text = new TextFile("src/utils5fin/encryption.txt");
        text.write(utils.CryptoUtils.hex(encryptedText));

        System.out.println("\n------ AES GCM Decryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "На входе (hex)", utils.CryptoUtils.hex(encryptedText)));
        //System.out.println(String.format(OUTPUT_FORMAT, "На входе (hex) (block = 16)", utils.CryptoUtils.hexWithBlockSize(encryptedText, 16)));
        System.out.println(String.format(OUTPUT_FORMAT, "Ключ шифрования (hex)", CryptoUtils.hex(secretKey.getEncoded())));

        String decryptedText = EncryptorAesGcm.decryptWithPrefixIV(encryptedText, secretKey);

        System.out.println(String.format(OUTPUT_FORMAT, "Расшифрованный текст:\n", decryptedText));
        // у нас есть secretKey, тогда сначала прочитаем зашифрованный файл
        //text.read
        // и, затем, раскодируем его содержимое
        //System.out.println(EncryptorAesGcm.decryptWithPrefixIV(text.read("src/utils5fin/prepare.out"), secretKey));
    }
}
