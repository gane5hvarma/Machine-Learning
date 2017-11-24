class Remove{
  /* this class is remove to the below mentioned symbols */
  String remove(String append){
    append=append.replace("<br /><br />", " ");
    append=append.replace(".", " ");
    append=append.replace(",", " ");
    append=append.replace("(", " ");
    append=append.replace(")", " ");
    //append=append.replace("!", " !");
    //append=append.replace("?", " ?");
    append=append.replace(":", " ");
    append=append.replace(";", " ");
    append=append.replace("*", " ");
    //append=append.replace("-", " ");
    append=append.replace("\"", " ");
    append=append.replace("/", " ");

    return append;

  }
}
