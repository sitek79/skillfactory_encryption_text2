package utils3.textfile;

import utils3.Hex;
import utils3.time.Time;

import java.io.*;
import java.util.*;

public class TextFile extends ArrayList<String> {
  // Чтение всего файла как одной строки:
  public static String read(String fileName) {
    Time time = new Time();
    StringBuilder sb = new StringBuilder();
    try {
      BufferedReader in= new BufferedReader(new FileReader(
        new File(fileName).getAbsoluteFile()));
      try {
        String s;
        while((s = in.readLine()) != null) {
          // распечатаем весь прочитанный набор
          //System.out.println(s);
          //
          // преобразуем файл в шестнадцатеричный формат
          s.format(s);

          //
          sb.append(s);
          sb.append("\n");
        }
      } finally {
        in.close();
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
    // добавим временную метку в конце сообщения. Для этого мы создали класс Time
    sb.append(time.getTime());
    // и вернем весь набор
    return sb.toString();
  }
  // Запись файла одним вызовом метода:
  public static void write(String fileName, String text) {
    try {
      PrintWriter out = new PrintWriter(
        new File(fileName).getAbsoluteFile());
      try {
        out.print(text);
      } finally {
        out.close();
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
  // Read a file, split by any regular expression:
  public TextFile(String fileName, String splitter) {
    super(Arrays.asList(read(fileName).split(splitter)));
    // Regular expression split() often leaves an empty
    // String at the first position:
    if(get(0).equals("")) remove(0);
  }
  // Normally read by lines:
  // Вызываем другой конструктор TextFile
  public TextFile(String fileName) {
    this(fileName, "\n");
  }
  public void write(String fileName) {
    try {
      PrintWriter out = new PrintWriter(
        new File(fileName).getAbsoluteFile());
      try {
        for(String item : this)
          out.println(item);
      } finally {
        out.close();
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
