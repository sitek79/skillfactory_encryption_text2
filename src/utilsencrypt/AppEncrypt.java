package utilsencrypt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class AppEncrypt {
    static String fin = "src/utilsencrypt/message.txt";
    static String fout = "src/utilsencrypt/encrypt.txt";

    public static void main(String[] args) throws IOException {
        // вызываем метод чтения из консоли с сохранением в файл
        readConsole(fin);
        System.out.println("Сообщение сохранено.");
        // вызываем метод чтения файла, кодирования и сохранения в другой файл
        readFile2(fin);
        // читаем закодированный файл
        readEncryptedFile(fout);
        // читаем и раскодируем
        encodeFile(fout);
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        //System.out.println("------- Временная метка: " + calendar.getTime() + " --------");
        return "+------ Временная метка: " + calendar.getTime() + " -------+\n";
    }

    // чтение строк из консоли и запись их в файл
    public static void readConsole(String fin) throws IOException {
        InputStreamReader sr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(sr);
        FileOutputStream fos = new FileOutputStream(fin);
        PrintStream ps = new PrintStream(fos);

        String s;

        System.out.println("Введите в консоли текст сообщения. Можно вставить текст из буфера обмена.\nЗатем нажмите Enter." +
                "Для сохранения в файл и продолжения работы введите 'end'");
        do {
            s = br.readLine(); // считать одну строку
            //System.out.println(s);
            // добавим штамп времени. Для этого мы написали метод getTime.
            // тогда добавим строку к массиву символов
            String string = getTime();
            for(char tm : string.toCharArray()){
                ps.append(tm);
            }
            // произведем замену букв по шаблону регулярного выражения
            s.replaceAll("a", "f");
            //
            ps.println(s); // записать эту строку в поток ps => записать в файл
        } while (!s.equals("end")); // пока не будет введена строка "end"
        ps.close();
    }

    public static void readFile2(String fin) {
        File file = new File(fin);

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)
        ) {
            String str;
            while ((str = reader.readLine()) != null) {
                // getBytes. возьмем байты строки и распечатаем их
                byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
                for (byte b : bytes) {
                    // дополнительно сдвигаем коды байтов на 2
                    b = (byte) (b + 2);
                    System.out.print(String.format("%s ", b));
                    // запишем строку в файл
                    try(FileWriter writer = new FileWriter(fout, true))
                    {
                        // запись всей строки
                        //String text = b;
                        writer.write(b);
                        // запись по символам
                        //writer.append('\n');
                        //writer.append('E');
                        writer.flush();
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                    //
                }
                // печатаем строки
                //System.out.println(str);
                // конвертируем байты символов обратно в строки
                String utf8String = new String(bytes);
                System.out.println(utf8String);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // чтение файла
    public static void readEncryptedFile(String fout) throws IOException {
        try {
            FileInputStream fileEnc = new FileInputStream(fout);
            System.out.println("Размер файла: " + fileEnc.available() + " байт(а)");
            byte[] buffer = new byte[fileEnc.available()];
            // чтение файла в буфер
            fileEnc.read(buffer, 0, fileEnc.available());
            System.out.println ("Содержимое закодированного файла:");
            for(int i = 0; i < buffer.length; i++){
                System.out.print((char)buffer[i]);
            }
            //
            fileEnc.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void encodeFile(String fout) throws IOException {
        System.out.println ("\nРаскодируем зашифрованный файл:");
        File file = new File(fout);

        try(FileInputStream fin=new FileInputStream(file))
        {
            System.out.printf("Размер файла: %d байт(а) \n", fin.available());
            int i=-1;
            while((i=fin.read())!=-1){
                // сдвинем код символа обратно
                i = i - 2;
                //char bytes = (char) i;
                //String utf8String = new String(String.valueOf(bytes));
                //System.out.print(utf8String);
                System.out.print((char)i);

                //
            }
            //
            // произведем замену букв по шаблону регулярного выражения
            //s.replaceAll("a", "f");
            //
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
