package utils5fin;

import java.io.*;
import java.util.ArrayList;

public class TextFile extends ArrayList<String> {
  String fileName;

  public TextFile(String fileName) {
    this.fileName = fileName;
  }
  // Чтение всего файла как одной строки:
  public static String read(String fileName) {
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
          sb.append(s);
          sb.append("\n");
        }
      } finally {
        in.close();
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
    // и вернем весь набор
    return sb.toString();
  }
  // Запись файла одним вызовом метода:
  public void write(String text) {
    try {
      PrintWriter out = new PrintWriter(
        new File(this.fileName).getAbsoluteFile());
      try {
        out.print(text);
      } finally {
        out.close();
      }
    } catch(IOException e) { throw new RuntimeException(e);
    }
  }
}
