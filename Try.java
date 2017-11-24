import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;
class Try{
  public static void main(String args[])throws Exception{
    Scanner scan=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/vocab.txt"));
    ArrayList<String> gg=new ArrayList<String>();
    while(scan.hasNextLine()){
      gg.add(scan.nextLine());
    }
    System.out.println(gg.size());
    String[] stopwords=new String[175];
    Scanner scans=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/stopwords.txt"));
    while(scans.hasNextLine()){
      gg.remove(scans.nextLine());
    }
    System.out.println(gg.size());

  }
}
