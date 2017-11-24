import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;

class Wordcount{
    /* this is also similar to Main.java but it removes the stop words from vocabulary and test document*/
    int vocabp=89527;
    int vocabn=89527;
    double pos=0.0;
    double neg=0.0;
    int sizep=0;
    int sizen=0;
    HashMap<String,Integer> posfreq()throws Exception{
      HashMap<String,Integer> wfp=new HashMap<String, Integer>();
      Scanner scan=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/appendedspos.txt"));
      String word=scan.nextLine();
      word=word.replaceAll("\\s+"," ");
      String[] words=word.split(" ");
      sizep=words.length;
      for(String ewc:words){
        ewc=ewc.toLowerCase();
        if(wfp.containsKey(ewc)){
            wfp.replace(ewc,wfp.get(ewc)+1);
          }
          else{
              wfp.put(ewc,1);
          }
        }
        return wfp;
      }
      HashMap<String,Integer> negfreq()throws Exception{
        HashMap<String,Integer> wfn=new HashMap<String, Integer>();
        Scanner scan=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/appendedsneg.txt"));
        String word=scan.nextLine();
        word=word.replaceAll("\\s+"," ");
        String[] words=word.split(" ");
        sizen=words.length;
        for(String ewc:words){
          ewc=ewc.toLowerCase();
          if(wfn.containsKey(ewc)){
              wfn.replace(ewc,wfn.get(ewc)+1);
            }
            else{
                wfn.put(ewc,1);
            }
          }
          return wfn;
        }
    double ggp(ArrayList<String> wos,HashMap<String,Integer> wfp)throws Exception{
        pos=0.0;
        String[] wordst=wos.toArray(new String[wos.size()]);
        for(String lol:wordst){
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
    double ggn(ArrayList<String> wos,HashMap<String,Integer> wfn)throws Exception{
        neg=0.0;
        String[] wordst=wos.toArray(new String[wos.size()]);
        for(String lol:wordst){
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
class Smain{
  public static void main(String args[])throws Exception{
    String Class[]={"pos","neg"};
    double pose[]={0.0,0.0};
    double nege[]={0.0,0.0};

    for(int i=0;i<2;i++){
      File folder=new File("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/test/"+Class[i]+"/");
      File[] listoffiles=folder.listFiles();
      int posa=0;
      int nega=0;
      Wordcount wc=new Wordcount();
      HashMap<String,Integer> wfp=wc.posfreq();
      HashMap<String,Integer> wfn=wc.negfreq();
      Scanner scants=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/stopwords.txt"));
      String[] wordsst=new String[175];
      int si=0;
      while(scants.hasNextLine()){
        wordsst[si]=scants.nextLine();
        si=si+1;
      }

      for(File files:listoffiles){
        Scanner scant=new Scanner(new FileReader("C:/Users/Ganesh/Desktop/ganesh/ml/aclImdb/test/"+Class[i]+"/"+files.getName()));
        String wordt=scant.nextLine();
        Remove removet=new Remove();
        String testw=removet.remove(wordt);
        testw=testw.replaceAll("\\s+"," ");
        String[] wordst=testw.split(" ");
        ArrayList<String> wos=new ArrayList<String>();
        for(String add:wordst){
          wos.add(add);
        }
        for(String remove:wordsst){
          wos.remove(remove);
        }


        double pos=wc.ggp(wos,wfp);
        double neg=wc.ggn(wos,wfn);
        if(pos>neg){
          posa=posa+1;
          System.out.println(posa);
        }
        else{
          nega=nega+1;
          System.out.println(nega);
        }

    }
    pose[i]=posa;
    nege[i]=nega;

  }
  System.out.println(pose[0]);
  System.out.println(nege[0]);
  System.out.println(pose[1]);
  System.out.println(nege[1]);
  double precisionp=pose[0]/(pose[0]+pose[1]);
  double precisionn=nege[1]/(nege[1]+nege[0]);
  double recallp=pose[0]/(pose[0]+nege[0]);
  double recalln=nege[1]/(nege[1]+pose[1]);
  double f1p=2*(precisionp*recallp)/(precisionp+recallp);
  double f1n=2*(precisionn*recalln)/(precisionn+recalln);

  System.out.println("precision for positive"+ precisionp);
  System.out.println("precision for negative"+ precisionn);
  System.out.println("recall for positive"+ recallp);
  System.out.println("recall for negative"+ recalln);
  System.out.println("f1p for positive"+ f1p);
  System.out.println("f1n for negative"+ f1n);
}
}
