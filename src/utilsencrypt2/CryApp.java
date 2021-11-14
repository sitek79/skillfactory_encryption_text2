package utilsencrypt2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CryApp {
    static String fileIn = "src/utilsencrypt2/message.txt";
    static String fileOut = "src/utilsencrypt2/encrypt.txt";

    //List<String> listMsg = Files.readAllLines(Path.of(fileIn), StandardCharsets.UTF_8);

    public CryApp() throws IOException {
        //String fileIn = "src/utilsencrypt2/message.txt";
        //String fileOut = "src/utilsencrypt2/encrypt.txt";
    }

    public static void main(String[] args) throws IOException {
        CryApp app = new CryApp();
        Time tm = new Time();

        // 1. прочитаем текстовый файл сообщения
        System.out.println("Читаем текст сообщения из файла <--");
        List<String> listMsg = Files.readAllLines(Path.of(fileIn), StandardCharsets.UTF_8);

        // 2. добавим метку времени
        System.out.println("Добавляем метку времени [--]");
        listMsg.add(tm.getTime());
        System.out.println(listMsg);

        // 3. зашифруем
        // listMsg.replaceAll();
        System.out.println("Шифруем [--]");
        int i = 0;
        for (String str : listMsg) {
            listMsg.set(i, str.replace("a", "j"));
            //
            listMsg.set(i, str.replace("а", "я"));
            listMsg.set(i, str.replace("б", "ю"));
            listMsg.set(i, str.replace("в", "ш"));
            listMsg.set(i, str.replace("г", "ч"));
            listMsg.set(i, str.replaceAll("д", "ц"));
            listMsg.set(i, str.replaceAll("е", "х"));
            listMsg.set(i, str.replaceAll("ж", "ф"));
            listMsg.set(i, str.replaceAll("з", "у"));
            listMsg.set(i, str.replaceAll("и", "т"));
            listMsg.set(i, str.replaceAll("к", "с"));
            listMsg.set(i, str.replaceAll("л", "р"));
            listMsg.set(i, str.replaceAll("м", "п"));
            listMsg.set(i, str.replaceAll("н", "о"));
            listMsg.set(i, str.replaceAll("о", "н"));
            listMsg.set(i, str.replaceAll("п", "м"));
            listMsg.set(i, str.replaceAll("р", "л"));
            listMsg.set(i, str.replaceAll("с", "к"));
            listMsg.set(i, str.replaceAll("т", "и"));
            listMsg.set(i, str.replaceAll("у", "з"));
            listMsg.set(i, str.replaceAll("ф", "ж"));
            listMsg.set(i, str.replaceAll("х", "е"));
            listMsg.set(i, str.replaceAll("ц", "д"));
            listMsg.set(i, str.replaceAll("ч", "г"));
            listMsg.set(i, str.replaceAll("ш", "в"));
            listMsg.set(i, str.replaceAll("ю", "б"));
            listMsg.set(i, str.replaceAll("я", "а"));

            //System.out.println(str);
            System.out.println(listMsg);
            //
        }

        // 4. сохраним зашифрованное сообщение в отдельный текстовый файл
        System.out.println("Сохраняем зашифрованное сообщение в файл -->");
        FileWriter writer = new FileWriter(fileOut);
        for (String str : listMsg) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();

        // 5. прочитаем зашифрованный текстовый файл
        System.out.println("Читаем зашифрованное сообщение из файла <--");
        List<String> listEncryptMsg = Files.readAllLines(Path.of(fileOut), StandardCharsets.UTF_8);
        System.out.println(listEncryptMsg);

        // 6. почитаем зашифрованный текстовый файл и выведем на консоль расшифрованное сообщение
        System.out.println("Расшифровываем текст сообщения [--]");
        FileReader reader = new FileReader(fileOut);
        //for(String str: listEncryptMsg) {
        //    reader.read(CharBuffer.wrap(str + System.lineSeparator()));
        //}
        String s;
        try {
            BufferedReader br = new BufferedReader(reader);
            while ((s = br.readLine()) != null) {
                s.replaceAll("a", "j");
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла");
        }
    }
}