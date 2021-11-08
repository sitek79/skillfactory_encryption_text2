package utils4;

import utils3.textfile.TextFile;
import utils3.time.Time;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class App {

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        //System.out.println("------- Временная метка: " + calendar.getTime() + " --------");
        return "+------ Временная метка: " + calendar.getTime() + " -------+";
    }

    public static void main(String[] args) {
        System.out.println(getTime());

        // а теперь из образцового файла скопируем сообщение в файл encryption.txt предназначенный для шифрования
        TextFile text = new TextFile("src/utils4/message.txt");
        text.write("src/utils4/copy.txt");
        //
        Hex hex = new Hex();
        try {
            // читаем файл copy.txt (методом read класса BinaryFile) как бинарный, преобразуя в классе Hex в шестнадцатеричный формат
            // encryption.txt <- Hex <- BinaryFile <- copy.txt
            //System.out.println(hex.format(BinaryFile.read("src/utils4/copy.txt")));
            // сохраним строку в файл
            //String text2 = "Hello world!"; // строка для записи
            String text2 = hex.formatToHex(BinaryFile.read("src/utils4/copy.txt"));
            try (FileOutputStream fos = new FileOutputStream("src/utils4/encryption.txt")) {
                // перевод строки в байты
                byte[] buffer = text2.getBytes();
                fos.write(buffer, 0, buffer.length);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            //
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла");
        }
        // преобразуем в текст
        try {
            System.out.println(hex.formatToText(BinaryFile.read("src/utils4/copy.txt")));
        } catch (Exception e) {

        }
    }
}
