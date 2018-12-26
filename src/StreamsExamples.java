import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class StreamsExamples {
    public static void justRead(){
        FileInputStream fis = null;
        try {
//            fis = new FileInputStream("test/test.txt");
            fis = new FileInputStream("C:\\Users\\Java Core Student 1\\IdeaProjects\\Lesson17\\test\\test.txt");
            System.out.println("file size in bytes: " + fis.available());
            int content;
            while ((content = fis.read()) != -1){
                System.out.print((char)content);//в одну строку
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            if (fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void readWithResources(){
        try(FileInputStream fis = new FileInputStream("test/test.txt")) {//с ресурсами, автозакрытие потоков ввода и вывода
            System.out.println("file size " + fis.available());
            int content;
            while ((content = fis.read()) != -1){
                System.out.print((char)content);//в строку
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readAndWrite(){
        try (FileInputStream fis = new FileInputStream("test/test.txt");//с ресурсами, автозакрытие потоков ввода и вывода
            FileOutputStream fos = new FileOutputStream("test/result.txt")){
            int content;
            while ((content = fis.read()) != -1){
                System.out.println((char)content);
                fos.write(content);
            }
        }catch (IOException e){
                e.printStackTrace();
        }
    }

    public static void readWriteWithoutClosing(){//специфика: при не закрытом потоке, файл записывется
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("test/test.txt");
            fos = new FileOutputStream("test/result.txt");
            int content;
            while ((content = fis.read()) != -1){
                System.out.println((char)content);
                fos.write(content);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void bufferedInputStream(){
        InputStream inStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            inStream = new FileInputStream("test/test.txt");
            bis = new BufferedInputStream(inStream);
            bos = new BufferedOutputStream(new FileOutputStream("test/buff_res.txt"));
            while (bis.available() > 0){
                char c = (char)bis.read();
                System.out.println("char: " + c);
                bos.write(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ( inStream != null && bis != null && bos != null ){
                try {
                    inStream.close();
                    bis.close();
                    bos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void bufferedInputStreamWithResources(){
        try (FileInputStream fis = new FileInputStream("test/test.txt");
             BufferedInputStream bis = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream("test/buf_res.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)){
            while (bis.available() > 0){
                char c =(char)bis.read();
                System.out.println("char " + c);
                bos.write(c);
            }
        }catch (Exception e){
                 e.printStackTrace();
        }
    }

    public static List<String> getListFromFile(){
        File file = new File("test/test.txt");
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                result.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void writeByLines(){
        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new FileWriter("test/buff_string.res.txt"));
            for (String s: strings) {
                writer.write(s);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
//        justRead();
//        readWithResources();
//        readAndWrite();
//        readWriteWithoutClosing();
//        bufferedInputStream();
//        bufferedInputStreamWithResources();

//        List<String> file = getListFromFile();
//        for (String s: file) {
//            System.out.println(s);
//        }
        writeByLines();




    }
}
