import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;

class Train{
/*this class appends the words in each document and removes symbols  */
  public static void main(String[] args)throws Exception{
    File folder=new File("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/train/neg/");
    File[] listoffiles=folder.listFiles();
    ArrayList files=new ArrayList();
    for(File file:listoffiles){
        // reads every file and add every string to ArrayList files
      Scanner scan=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/train/neg/"+ file.getName()));
      String review=scan.nextLine();
      files.add(review);
    }
    Concatenate concatenate=new Concatenate();
    String append=concatenate.concatenate(files);// appends every file in the arraylist
    Remove remove=new Remove();
    String editappend=remove.remove(append);// removes symbols
    FileWriter fw=new FileWriter("C:/Users/Ganesh/Desktop/ganesh/ml/appendedneg.txt");
    fw.write(editappend);
    fw.close();

  }

}
