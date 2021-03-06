package utils5fin;

import java.io.*;

public class FileInOut {
    static String outFile;
    Time time = new Time();

    FileInOut(String outFile) {
        this.outFile = outFile;
    }
    //static String file = "src/utils5fin/prepare.out";
    // скопируем оригинальное сообщение в промежуточный файл, добавив нумерацию строк для дополнительного контроля
    public void copyOriginalMessage(String srcFile) throws IOException {
        //BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("src/utils5fin/message.txt")));
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(srcFile)));
        // Here's the shortcut:
        PrintWriter out = new PrintWriter(outFile);
        int lineCount = 1;
        String s;
        while((s = in.readLine()) != null )
            out.println(lineCount++ + ": " + s);
        // добавим временную метку в конце сообщения. Для этого мы создали класс Time
        out.append(time.getTime());
        out.close();
        // Show the stored file:
        //System.out.println(BufferedInputFile.read(file));
    }
    public String showEncryptedFile() throws IOException {
        // Show the stored file:
        //System.out.println(BufferedInputFile.read(file));
        return BufferedInputFile.read(outFile);
    }
}

