import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;

class Wordcount{
/* this class calculates the word frequency and returns loglikelihood*/
    int vocabp=89366;//no of words in vocab
    int vocabn=89366;//no of words in vocab
    double pos=0.0;
    double neg=0.0;
    int sizep=0;
    int sizen=0;
    HashMap<String,Integer> posfreq()throws Exception{  // hashmap for finding word frequency in positive training data
      HashMap<String,Integer> wfp=new HashMap<String, Integer>();
      Scanner scan=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/appendedpos.txt"));
      String word=scan.nextLine();
      word=word.replaceAll("\\s+"," ");// it replaces multiple whitespaces which are adjacent and replaces it with single whitespace
      String[] words=word.split(" ");// splits the total string at whitespace
      sizep=words.length;// calculate the no of words in the appended file
      for(String ewc:words){ // takes every word from the appended file and adds it to hashmap
        ewc=ewc.toLowerCase();
        if(wfp.containsKey(ewc)){
            wfp.replace(ewc,wfp.get(ewc)+1);
          }
          else{
              wfp.put(ewc,1);
          }
        }
        System.out.println(wfp.size());
        Scanner scanv=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/vocab.txt"));
        while(scanv.hasNextLine()){
          String ew=scanv.nextLine();
          if(!wfp.containsKey(ew)){// if any word in the vocab which isnt there in the appended class file it gives the key as zero
            wfp.put(ew,0);
          }
        }
          System.out.println(wfp.size());
        return wfp;//word frequency,hashmao contains different words in positive training data and their frequencies
      }
      HashMap<String,Integer> negfreq()throws Exception{ // hashmap for finding word frequency in negative training data
        HashMap<String,Integer> wfn=new HashMap<String, Integer>();
        Scanner scan=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/appendedneg.txt"));
        String word=scan.nextLine();
        word=word.replaceAll("\\s+"," ");// it replaces multiple whitespaces which are adjacent and replaces it with single whitespace
        String[] words=word.split(" ");// splits the total string at whitespace
        sizen=words.length;// calculate the no of words in the appended file
        for(String ewc:words){ // takes every word from the appended file and adds it to hashmap
          ewc=ewc.toLowerCase();
          if(wfn.containsKey(ewc)){
              wfn.replace(ewc,wfn.get(ewc)+1);
            }
            else{
                wfn.put(ewc,1);
            }
          }
          System.out.println(wfn.size());
          Scanner scanv=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/vocab.txt"));
          while(scanv.hasNextLine()){// if any word in the vocab which isnt there in the appended class file it gives the key as zero
            String ew=scanv.nextLine();
            if(!wfn.containsKey(ew)){
              wfn.put(ew,0);
            }
          }
          System.out.println(wfn.size());
          return wfn;//word frequency,hashmap contains different words in negative training data and their frequencies
        }
    double ggp(String[] wordst,HashMap<String,Integer> wfp)throws Exception{// this method returns loglikelihood for each file and classifies it as positive
      /*
        param wordst : the string which is being processed
        wfp : The hashmap which contains word counts of various words.
      */
        pos=0.0;
        for(String lol:wordst){ // checks the word in the respective class hashmap and calculates the loglikelihood
            if(wfp.containsKey(lol)){
              double temp=wfp.get(lol);
              pos=pos+Math.log10(((temp+1)/(sizep+vocabp)));
            }
            else{
              pos=pos+Math.log10(1.0/(sizep+vocabp));
            }
          }

      return pos;
  }
    double ggn(String[] wordst,HashMap<String,Integer> wfn)throws Exception{// this method returns loglikelihood for each file and classifies it as negative
        /*
          wordst : string which is being processed
          Wfn : a  hash map that contains word frequency of negative words
        */
        neg=0.0;
        for(String lol:wordst){ // checks the word in the respective class hashmap and calculates the loglikelihood
            if(wfn.containsKey(lol)){
              double temp=wfn.get(lol);
              neg=neg+Math.log10(((temp+1)/(sizen+vocabn)));
            }
            else{
              neg=neg+Math.log10(1.0/(sizen+vocabn));
            }
          }

      return neg;
}

}
class Main{
  public static void main(String args[])throws Exception{
    String Class[]={"pos","neg"};
    double pose[]={0.0,0.0};
    double nege[]={0.0,0.0};

    for(int i=0;i<2;i++){//runs through two classes positive and negative
      File folder=new File("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/test/"+Class[i]+"/");
      File[] listoffiles=folder.listFiles();
      int posa=0;
      int nega=0;
      Wordcount wc=new Wordcount();
      // hashmaps to store word counts in postive and negative test cases.
      HashMap<String,Integer> wfp=wc.posfreq();
      HashMap<String,Integer> wfn=wc.negfreq();
      for(File files:listoffiles){
        // reads every file in the test data and checks whether they are positive or negative
        Scanner scant=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/test/"+Class[i]+"/"+files.getName()));
        String wordt=scant.nextLine();
        Remove removet=new Remove();
        String testw=removet.remove(wordt);//removes symbols from the words
        testw=testw.replaceAll("\\s+"," ");
        String[] wordst=testw.split(" ");
        double pos=wc.ggp(wordst,wfp);
        double neg=wc.ggn(wordst,wfn);
        if(pos>neg){
          posa=posa+1;
          System.out.println(posa);
        }
        else{
          System.out.println(nega);
          nega=nega+1;
        }

    }
    pose[i]=posa;
    nege[i]=nega;

  }
  System.out.println(pose[0]);
  System.out.println(nege[0]);
  System.out.println(pose[1]);

  System.out.println(nege[1]);
  double precisionp=pose[0]/(pose[0]+pose[1]);//calculates precision for positive sentimental analysis,formulae=tp/tp+fp
  double precisionn=nege[1]/(nege[1]+nege[0]);//calculates precision for negative sentimental analysis,formulse=tn/tn+fn
  double recallp=pose[0]/(pose[0]+nege[0]);//tp/tp+fn
  double recalln=nege[1]/(nege[1]+pose[1]);//tn/tn+fp
  double f1p=2*(precisionp*recallp)/(precisionp+recallp);//2*pr/p+r
  double f1n=2*(precisionn*recalln)/(precisionn+recalln);//2*pr/P+r

  System.out.println("precision for positive"+ precisionp);
  System.out.println("precision for negative"+ precisionn);
  System.out.println("recall for positive"+ recallp);
  System.out.println("recall for negative"+ recalln);
  System.out.println("f1p for positive"+ f1p);
  System.out.println("f1n for negative"+ f1n);
}
}
