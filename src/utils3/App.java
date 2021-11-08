package utils3;

import utils3.textfile.TextFile;
import utils3.time.Time;

import java.util.TreeSet;

import static utils3.print.Print.print;
import static utils3.textfile.TextFile.read;
import static utils3.textfile.TextFile.write;

public class App {
    // Простой тест:
    public static void main(String[] args) {
        // Время. Класс Time
        Time time = new Time();
        time.getTime();
        print(time.getTime());
        // Подготовка. Скопируем наше секретное сообщение в файл для отправки:
        //String file = read("G://prog/java/skillfactory_encryption_text2/src/utils3/textfile/TextFile.java");
        // скопируем сообщение из letter.txt в тестовый файл test.txt
        //String file = read("G://prog/java/skillfactory_encryption_text2/src/utils3/resources/letter.txt");
        //write("G://prog/java/skillfactory_encryption_text2/src/utils3/resources/test.txt", file);
        // а теперь из тестового файла скопируем в файл send.txt
        TextFile text = new TextFile("G://prog/java/skillfactory_encryption_text2/src/utils3/resources/test.txt");
        text.write("G://prog/java/skillfactory_encryption_text2/src/utils3/resources/send.txt");
        // Разбивка на сортированные списки уникальных слов:
        // И вывод в консоли

//        TreeSet<String> words = new TreeSet<String>(new TextFile("G://prog/java/skillfactory_encryption_text2/src/utils3/resources/letter.txt", "\\W+"));
//        // Display the capitalized words:
//        //System.out.println(words.headSet("a"));
//        // воткнем вывод даты в консоли
//        //words.add("Wremya");
//        words.add(time.getTime());
//        print("Количество слов: " + words.size());
//        print(words.headSet("a"));
    }
     /* Output:
[0, ArrayList, Arrays, Break, BufferedReader, BufferedWriter, Clean, Display, File, FileReader, FileWriter, IOException, Normally, Output, PrintWriter, Read, Regular, RuntimeException, Simple, Static, String, StringBuilder, System, TextFile, Tools, TreeSet, W, Write]
*///:~
}
