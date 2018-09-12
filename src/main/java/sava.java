

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class sava {

    public static void write(String s) {
        // TODO 自动生成的方法存根
        PrintWriter outputStream = null;
        try
        {
//            outputStream = new PrintWriter(new
//                    FileOutputStream("stuff.txt"));
            outputStream = new PrintWriter(new
                    FileOutputStream("stuff.txt", true));
        }catch (FileNotFoundException e)
        {
            System.out.println("Error opening the file stuff.txt");
                    System.exit(0);
        }
        System.out.println("Writing to file");

        outputStream.println(s);



                outputStream.close();
        System.out.println("End of program");
    }

//    public static void main(String[] args) {
//        write("The quick brown fox");
//    }
}