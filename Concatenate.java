import java.util.*;
class Concatenate{
  /*
  A very simple class that defines a concatenate function to combine all Files
  into one document.
  */
  String append="";
  String concatenate(ArrayList files){
    for(int i=0;i<files.size();i++){
      append=append+files.get(i);

    }
    return append;

  }

}
