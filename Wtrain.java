import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;

class Wtrain{
  public static void main(String[] args)throws Exception{
    File folder=new File("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/train/pos/");
    File[] listoffiles=folder.listFiles();
    Concatenate concatenate=new Concatenate();
    String conc="";
    ArrayList files=new ArrayList();
    int k=0;
    FileWriter fw=new FileWriter("C:/Users/Ganesh/Desktop/ganesh/ml/appendedppos.txt");
    for(File file:listoffiles){
      k++;
      System.out.println(k);
      Scanner scan=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/train/pos/"+ file.getName()));
      String review=scan.nextLine();

      String[] wordst=review.split(" ");
      HashMap<String,Integer> wwg=new HashMap<String,Integer>();
      for(String ewc:wordst){
        if(!wwg.containsKey(ewc)){
          wwg.put(ewc,1);
          conc=" "+ewc;
          fw.write(conc);
          //System.out.println(conc);
        }
    }
  }

    //String append=concatenate.concatenate(files);
    Remove remove=new Remove();
    Scanner scann=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/appendedppos.txt"));
    String editappend=scann.nextLine();
    editappend=remove.remove(editappend);
    FileWriter fwa=new FileWriter("C:/Users/Ganesh/Desktop/ganesh/ml/cp.txt");
    fwa.write(editappend);
    fwa.close();



  }

}
