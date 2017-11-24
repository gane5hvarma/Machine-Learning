import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;

class Strain{
  /* this class appends all the files and removes stop words*/
  public static void main(String[] args)throws Exception{
    File folder=new File("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/train/neg/");
    File[] listoffiles=folder.listFiles();
    ArrayList files=new ArrayList();
    for(File file:listoffiles){
      Scanner scan=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/train/neg/"+ file.getName()));
      String review=scan.nextLine();
      files.add(review);
    }
    Concatenate concatenate=new Concatenate();
    String append=concatenate.concatenate(files);
    Remove remove=new Remove();
    String editappend=remove.remove(append);
    editappend=editappend.replaceAll("\\s+"," ");
    String[] words=editappend.split(" ");
    ArrayList<String> gg=new ArrayList<String>();
    int i=0;
    for(String a:words){
      gg.add(words[i]);
      i=i+1;
    }
    System.out.println(gg.size());
    Scanner scana=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/stopwords.txt"));
    String stopwords[]=new String[175];
    int si=0;
    while(scana.hasNextLine()){
      stopwords[si]=scana.nextLine();
      si=si+1;
    System.out.println(scana.nextLine());
    }
    int j=0;
    while(j<stopwords.length){
      gg.remove(stopwords[j]);
      j=j+1;
    }
    System.out.println(gg.size());
    FileWriter fw=new FileWriter("C:/Users/Ganesh/Desktop/ganesh/ml/appendedsneg.txt");
    fw.write(editappend);
    fw.close();

  }

}
