/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generalise;
import java.io.File;
import java.lang.reflect.*;
import java.lang.reflect.Array;
import java.sql.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.nio.file.Files;
import java.io.IOException;
/**
 *
 * @author Mirado
 */
public class GenericDao<T>
{ 
  Statement statement;
    public Statement getStatement(){    
        return statement;   
    }
    public void setStatement(Statement newStmt){     
        statement=newStmt; 
    }
  //---------------------------------------------------------------------------------------------------
    public void createStatementIfNull(Connection connection)throws Exception{
      if(statement==null){
        statement=connection.createStatement();
      }else if(statement.isClosed()==true){
        statement=connection.createStatement();
      }if(connection==null){
        throw new Exception("la connection est null");
      }
    }
//-----------------------------------------------------------------------------------------------------
    public void update(String sqlupdate,Connection connection)throws Exception{
      createStatementIfNull(connection);
      int nb=statement.executeUpdate(sqlupdate);
    }
    public void insert(String sqlinsert,Connection connection)throws Exception{
      createStatementIfNull(connection);
      int nb=statement.executeUpdate(sqlinsert);
    }
    public void delete(String sqldelete,Connection connection)throws Exception{
      createStatementIfNull(connection);
      int nb=statement.executeUpdate(sqldelete);
    }
//----------------------------------------------------------------------------------------------
//     public int getSequence(String nom)throws Exception{
//       ResultSet resultSet=statement.executeQuery("select "+nom+".NEXTVAL from dual");
//       resultSet.next();
//       String s=resultSet.getString(1);
//       resultSet.close();
//       return Integer.valueOf(s);
//     }
//-----------------------------------------------------------------------------------------------
public String DatetoFormatDataBase(String sDate){
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  return simpleDateFormat.format(Date.valueOf(sDate)).toString();
}
public String DatetoFormatDataBase(Date date){
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  return simpleDateFormat.format(date).toString();
}
//---
public String toLowerCaseFirst(String str){ return str.toLowerCase().substring(0,1)+str.substring(1,str.length()); }
//-----------------------------------------------------------------------------------------------recupere les valeur d'une table
    public HashMap<String,String> columnsInDataBase(String nameTable,DatabaseMetaData metaData)throws Exception{
      ResultSet columns=metaData.getColumns(null, null,toLowerCaseFirst(nameTable) , null);
      HashMap<String,String> hashMap=new HashMap<String,String>();
      String strtemp="";
      while (columns.next()) {
        strtemp = columns.getString("COLUMN_NAME");
        System.out.println(strtemp);
        hashMap.put(strtemp, strtemp);
      }
      columns.close();
      return hashMap;
    }
    public String[][] valueSelectToString(String sqlSelect,Connection connection)throws Exception{
      createStatementIfNull(connection);
      Vector vresultat=new Vector();
      ResultSet resultSet=statement.executeQuery(sqlSelect);//
      ResultSetMetaData rsmd=resultSet.getMetaData();
      int nbcolumn=rsmd.getColumnCount(); //nb colonne 

      boolean encore=resultSet.next(); //mbola misy ve le ligne
      String[] col=null;
      while(encore){//if==true
          col=new String[nbcolumn];
          for(int i=1;i<=nbcolumn;i++){
            col[i-1]=resultSet.getString(i); //l'element en String du colonne numero i dans ce ligne
          }
          vresultat.add(col);
          encore=resultSet.next();//resultSet.next() : ligne suivante / encore= false si plus de ligne
      }
      if(col==null){ return null; }
      String[][] resultat=new String[vresultat.size()][nbcolumn];
      for(int i=0;i<vresultat.size();i++){
        resultat[i]=(String[])vresultat.elementAt(i);
      }
      resultSet.close();
      return resultat;
    }
    public List<HashMap<String,String>> valueSelectToListHashMap(String sqlSelect,Connection connection)throws Exception{
      List<HashMap<String,String>> lstdata=new ArrayList<HashMap<String,String>>();
      HashMap<String,String> data=null;
      createStatementIfNull(connection);
      ResultSet resultSet=statement.executeQuery(sqlSelect);//
      ResultSetMetaData rsmd=resultSet.getMetaData();
      int nbcolumn=rsmd.getColumnCount(); //nb colonne 
      boolean encore=resultSet.next(); //mbola misy ve le ligne
      while(encore){//if==true
          data=new HashMap<String,String>();
          for(int i=1;i<=nbcolumn;i++){
            data.put(rsmd.getColumnLabel(i), resultSet.getString(i)); //key:nom du colonne / value : valeur du colonne
            //System.out.println("nom: "+rsmd.getColumnLabel(i)+"---->"+resultSet.getString(i));
          }
          lstdata.add(data);
          encore=resultSet.next();
      }
      resultSet.close();
      return lstdata;
    }
//-----------------
public static byte[] convertFileToByteArray(File file) throws IOException {
  return Files.readAllBytes(file.toPath());
}
//------------------------------------------------------------------------------------------------------
public Object valuOfString(String type, String value){
  Object object=value;
  if(type.compareTo("int")==0){
        object=Integer.valueOf(value);
  }else if(type.compareTo("long")==0){
        object=Long.valueOf(value);
  }else if(type.compareTo("float")==0){
        object=Float.valueOf((value));
  }else if(type.compareTo("char")==0){
        String str=value;
        object=str.charAt(0);
  }else if(type.compareTo("double")==0){
        object=Double.valueOf(value);
  }else if(type.compareTo("LocalDate")==0){ //annee-mois-jours
        String [] strDt=value.split("-");
        String[] forJour=strDt[2].split(" ");
        LocalDate date=LocalDate.of(Integer.valueOf(strDt[0]), Integer.valueOf(strDt[1]) , Integer.valueOf(forJour[0]) );
        object=date;
  }else if(type.compareTo("Date")==0){
        Date date=Date.valueOf(value);
        object=date;
  }else if(type.compareTo("LocalDateTime")==0){ //annee-mois-jours
      String[] ld1=value.split(" ");
      String[] ld2=ld1[0].split("-");
      String[] ld3=ld1[1].split(":");
      object=LocalDateTime.of(Integer.valueOf(ld2[0]),Integer.valueOf(ld2[1]),Integer.valueOf(ld2[2]),Integer.valueOf(ld3[0]),Integer.valueOf(ld3[1]),Integer.valueOf(ld3[2]));
  }else if(type.compareTo("Time")==0){
        Time time=Time.valueOf(value);
        object=time;
  }else if(type.compareTo("LocalTime")==0){
        String[] temps=value.split(":");
        object= LocalTime.of(Integer.valueOf(temps[0]), Integer.valueOf(temps[1]),Integer.valueOf(temps[2]));
  }else if(type.compareTo("boolean")==0){
        object=Boolean.valueOf(value);
  }
  return object;
}
//------
public Object creerObject(Object objexample,HashMap<String,String> hashMapData)throws Exception{
  Object obj =objexample.getClass().getDeclaredConstructor().newInstance();
  Field[] fields=objexample.getClass().getDeclaredFields();
  Method method=null;
  String stemp=null;
  Object paramtemp=null;
  for(int i=0;i<fields.length;i++){
    stemp=hashMapData.get(fields[i].getName());
    if(stemp!=null){ //seul les noms de fields dans le cle valeur qu'on set
      if(stemp.compareTo("")!=0){
        paramtemp=valuOfString(fields[i].getType().getSimpleName(),stemp);
        fields[i].setAccessible(true);
        fields[i].set( obj , paramtemp );  //set field of obj
        fields[i].setAccessible(false);
      }
    }
  }
  return obj;    
}
//---------------------------------------------------creation du tableau d'objet dont leurs valeurs est dans un String[][]
public Object[] creerLstObjects(Object objexample,List<HashMap<String,String>> lstValueField)throws Exception{
    if(lstValueField==null){ return null; }
    Object[] lstobj=new Object[lstValueField.size()];
    for (int i=0;i<lstValueField.size();i++){ lstobj[i]=creerObject(objexample, lstValueField.get(i));  }
    return lstobj;
}
public T[] creerLstObjectsT(T tobj,List<HashMap<String,String>> lstValueField)throws Exception{
    if(lstValueField==null){ return null; }
    T[] lstobj=(T[]) Array.newInstance(tobj.getClass(), lstValueField.size());
    for (int i=0;i<lstValueField.size();i++){ lstobj[i]=(T)creerObject(tobj, lstValueField.get(i));  }
    return lstobj;
}
//----------------------------------------------------
public Object[] creerLstObjects(Object objexample,Connection connection,String sqlSelect)throws Exception{
  List<HashMap<String,String>> lstValueField=valueSelectToListHashMap(sqlSelect,connection);
  Object[] lstobj=creerLstObjects(objexample, lstValueField);
  return lstobj;
}
public T[] creerLstObjectsT(T tobj,Connection connection,String sqlSelect)throws Exception{
  List<HashMap<String,String>> lstValueField=valueSelectToListHashMap(sqlSelect,connection);
  T[] lstobj=creerLstObjectsT(tobj, lstValueField);
  return lstobj;
}
//-----------------------------------------------------

    public String writeValueAccordingType(String type,Object value){ //---date--2022/02/03---> '2022/02/03' , int--3--> 3
      if(value==null){  return "NULL";  }
      int siString=type.compareTo("String");
      int siDate=type.compareTo("Date");
      int siLocalDate=type.compareTo("LocalDate");
      int siTime=type.compareTo("Time");
      int siLocalDateTime=type.compareTo("LocalDateTime");

      int valeurfinal=siString*siDate*siLocalDate*siTime*siLocalDateTime; //---> si 0 --> v-d-r que 1 de ces type est vrai
      if(type.compareTo("FileUpload")==0){
        String val="\'"+value.toString()+"\'";
        //raha date le izy de avadika format date
        return val;
      }
      if(siLocalDateTime==0){
         LocalDateTime ldatetime = (LocalDateTime)value;
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         String formattedDateTime = "\'"+ldatetime.format(formatter)+"\'";
          return formattedDateTime;
      }else if(siDate==0){
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
          Date date=(Date)value;
          return "\'"+simpleDateFormat.format(date).toString()+"\'";
      }else if(siLocalDate==0){
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
          LocalDate ldate=(LocalDate)value;
          return "\'"+simpleDateFormat.format(Date.valueOf(ldate)).toString()+"\'";
      }else if(valeurfinal==0){
        String val="\'"+value+"\'";
        return val;
      }else{ return value.toString()+""; }
    }
//------------------------------------------------------exemple= "nom"----to---->"getNom"  et   "nom"----to---->"setNom" 
      public String to_getAttribu(String nomAttribu,String typeField)
      {
            String getAttribMaj="get";
            if(typeField.compareToIgnoreCase("boolean")==0){  getAttribMaj="is";  }
            String attrib=nomAttribu.substring(0,1).toUpperCase(); //rendre en majuscul la premiere lettre du nomAtribu
            String restattrib=nomAttribu.substring(1,nomAttribu.length());  //prendre les lettres a partir du 2e lettre
            getAttribMaj=getAttribMaj.concat(attrib+restattrib);  //fusionner pour avoir le nom de fonction "getAtribu"
                         //Method m=c.getMethod("getVar1");
            return getAttribMaj;
      }
      public String to_setAttribu(String nomAttribu)
      {
            String getAttribMaj="set";
            String attrib=nomAttribu.substring(0,1).toUpperCase(); //rendre en majuscul la premiere lettre du nomAtribu
            String restattrib=nomAttribu.substring(1,nomAttribu.length());  //prendre les lettres a partir du 2e lettre=
            getAttribMaj=getAttribMaj.concat(attrib+restattrib); //fusionner pour avoir le nom de fonction "getAtribu"
             //Method m=c.getMethod("getVar1");
            return getAttribMaj;
      }
//---------------------------------------------------------------------------------------------------------------------save/Insert
  public void save(Object object,Connection connection,String nameprimaryKey)throws Exception{
    String siPkNull="##$$##$$;;";
    if(nameprimaryKey==null){ nameprimaryKey=siPkNull; }
    String nom=object.getClass().getSimpleName();  
    String requet="insert into "+nom + " (";
    
    System.out.println(requet+"mamde hono eh");
    Field[] fields=object.getClass().getDeclaredFields();
    String values=" values(";
    String typepk="";
    HashMap<String,String> hashMap=columnsInDataBase(object.getClass().getSimpleName(), connection.getMetaData());
    String column=null;
    for(int i=0;i<fields.length;i++){
      column=hashMap.get(fields[i].getName());
      if(fields[i].getName().compareToIgnoreCase(nameprimaryKey)!=0 && column!=null ){ //si il n'est pas le pk et qu'il existe comme column dans la table qui lui convient
        requet=requet+fields[i].getName(); //"insert into nomObj ( "+ ? +","+ ? + ...
        //String val=String.valueOf( object.getClass().getMethod( to_getAttribu(fields[i].getName(),fields[i].getType().toString()) ).invoke(object) );
        Object obj= object.getClass().getMethod( to_getAttribu(fields[i].getName(),fields[i].getType().toString()) ).invoke(object) ;
        if(obj!=null){ values=values+writeValueAccordingType(fields[i].getType().getSimpleName(),obj); }
        else{ values=values+"null";  }
        requet=requet+",";  values=values+",";
      }else if(fields[i].getName().compareToIgnoreCase(nameprimaryKey)==0 && column!=null ){//si c'est le pk et qu'il existe dans la table du bdd qui lui convient
        typepk=fields[i].getType().getSimpleName();
      }
    }
    requet=requet.substring(0, requet.length()-1);
    values=values.substring(0, values.length()-1);
    requet=requet+")"+values+")";
    ////////////
    System.out.println("before execut : "+requet); 
    this.insert(requet, connection);
    System.out.println("after execut : "+requet); 
    ////////////

    if(nameprimaryKey.compareToIgnoreCase(siPkNull)!=0){
      String[][] lastId=this.valueSelectToString("select max("+nameprimaryKey+") from "+object.getClass().getSimpleName(), connection);
      Method[] sesMeths=object.getClass().getDeclaredMethods();
      int u=-1;
      for(int i=0;i<sesMeths.length;i++){   if(sesMeths[i].getName().compareTo( to_setAttribu(nameprimaryKey))==0 &&  sesMeths[i].getParameterTypes()[0].getSimpleName().compareTo(typepk)==0){ u=i;}   }
      if(u>=0 && u<sesMeths.length){   sesMeths[u].invoke(object,Integer.valueOf(lastId[0][0])); }
    }
  }
//----------------------------------------------------------------------------------------------------------update
  public void update(Object object,Connection connection,String nameprimaryKey)throws Exception{ 
    //update emp set col=valCol where condition
    String nom=object.getClass().getSimpleName(); 
    String requete="update "+nom + " set ";
    String colAndVal="";
    Field[] fields=object.getClass().getDeclaredFields();
    HashMap<String,String> hashMap=columnsInDataBase(object.getClass().getSimpleName(), connection.getMetaData());
    String column=null;
    for(int i=0;i<fields.length;i++){
      column=hashMap.get(fields[i].getName());
      if(column!=null){//si l'attribu est un du column de la table de convenance
        Object val=object.getClass().getMethod( to_getAttribu(fields[i].getName(), fields[i].getType().getSimpleName() ) ).invoke( object );
        if(val!=null){ colAndVal=colAndVal+" "+fields[i].getName()+"="+writeValueAccordingType(fields[i].getType().getSimpleName(), val )+","; }
        else{ colAndVal=colAndVal+" "+fields[i].getName()+"="+"NULL"+",";  }  
      }
    }
    if(colAndVal.length()>0){ colAndVal=colAndVal.substring(0, colAndVal.length()-1); } //on prend en enlevant la virgule a la fin 
    if(nameprimaryKey!=null){
      Field fpk=object.getClass().getDeclaredField(nameprimaryKey);
      Object valpk=object.getClass().getMethod( to_getAttribu( nameprimaryKey, fpk.getType().getSimpleName() ) ).invoke(object); //le pk de this pour update 
      colAndVal=colAndVal+" where "+nameprimaryKey+"="+ writeValueAccordingType(fpk.getType().getSimpleName(), valpk);
    }else{ throw new Exception("pk null, nom primary key obligatoire pour update"); }
    requete=requete+colAndVal;
    ////////////
    System.out.println("before execut : "+requete); 
    this.update(requete, connection);
    System.out.println("after execut : "+requete); 
    ////////////
  }
//-------------------------------------------------------------------------------------------------------------delete
  public void delete(Object object,Connection connection,String nameprimaryKey)throws Exception{
    String nom=object.getClass().getSimpleName(); 
    String requet="delete from "+nom;
    String condition=" where";
    if(nameprimaryKey==null){
      Field[] fields=object.getClass().getDeclaredFields();
      HashMap<String,String> hashMap=columnsInDataBase(object.getClass().getSimpleName(), connection.getMetaData());
      String column=null;
      for(int i=0;i<fields.length;i++){
        column=hashMap.get(fields[i].getName());
        if(column!=null){
          condition=condition+" ";
          Object val=object.getClass().getMethod( to_getAttribu(fields[i].getName(), fields[i].getType().getSimpleName()) ).invoke( object );
          if(val!=null){ condition=condition+" "+fields[i].getName()+"="+writeValueAccordingType(fields[i].getType().getSimpleName(),val)+" and"; }
          else{ condition=condition+" "+fields[i].getName()+" is "+"NULL"+" and";  }
        }
      }
      if(condition.length()>0){ condition=condition.substring(0, condition.length()-3); } //on prend en enlevant la virgule a la fin
    }else{
      Field fpk=object.getClass().getDeclaredField(nameprimaryKey);
      Object valpk=object.getClass().getMethod( to_getAttribu( nameprimaryKey, fpk.getType().getSimpleName() ) ).invoke(object); 
      condition=condition+" "+nameprimaryKey +"="+writeValueAccordingType(fpk.getType().getSimpleName(), valpk);
    }
    requet=requet+condition;
    ////////////
    System.out.println("before execut : "+requet);
    this.delete(requet, connection);
    System.out.println("after execut : "+requet); 
    ////////////
  }
//---------------------------------------------------------------------------------------------------------------
  public Object find(Object object,Connection connection,String nameprimaryKey)throws Exception{
    String nom=object.getClass().getSimpleName(); 
    String requet="select * from "+nom;
    String condition=" where ";
    if(nameprimaryKey==null){
      Field[] fields=object.getClass().getDeclaredFields();
      for(int i=0;i<fields.length;i++){
        condition=condition+" ";
        Object val=object.getClass().getMethod( to_getAttribu(fields[i].getName(),fields[i].getType().getSimpleName()) ).invoke( object );
        if(val!=null){ condition=condition+" "+fields[i].getName()+"="+writeValueAccordingType(fields[i].getType().getSimpleName(),val)+" and"; }
        else{ condition=condition+"="+"NULL"+" and";  }
      }
      if(condition.length()>0){ condition=condition.substring(0, condition.length()-3); } //on prend en enlevant la virgule a la fin
    }else{
      Field fpk=object.getClass().getDeclaredField(nameprimaryKey);
      Object valpk=object.getClass().getMethod( to_getAttribu( nameprimaryKey, fpk.getType().getSimpleName() ) ).invoke(object); 
      condition=condition+" "+nameprimaryKey +"="+writeValueAccordingType(fpk.getType().getSimpleName(), valpk);
    }
    requet=requet+condition;
    ////////////
    System.out.println("before execut : "+requet);
    Object[] objs=this.creerLstObjects(object,connection, requet);
    System.out.println("after execut : "+requet); 
    ///////////
    if(objs==null){ return null; }
    return objs[0];
  }
  public T findT(T object,Connection connection,String nameprimaryKey)throws Exception{
    String nom=object.getClass().getSimpleName(); 
    String requet="select * from "+nom;
    String condition=" where ";
    if(nameprimaryKey==null){
      Field[] fields=object.getClass().getDeclaredFields();
      for(int i=0;i<fields.length;i++){
        condition=condition+" ";
        Object val=object.getClass().getMethod( to_getAttribu(fields[i].getName(),fields[i].getType().getSimpleName()) ).invoke( object );
        if(val!=null){ condition=condition+" "+fields[i].getName()+"="+writeValueAccordingType(fields[i].getType().getSimpleName(),val)+" and"; }
        else{ condition=condition+"="+"NULL"+" and";  }
      }
      if(condition.length()>0){ condition=condition.substring(0, condition.length()-3); } //on prend en enlevant la virgule a la fin
    }else{
      Field fpk=object.getClass().getDeclaredField(nameprimaryKey);
      Object valpk=object.getClass().getMethod( to_getAttribu( nameprimaryKey, fpk.getType().getSimpleName() ) ).invoke(object); 
      condition=condition+" "+nameprimaryKey +"="+writeValueAccordingType(fpk.getType().getSimpleName(), valpk);
    }
    requet=requet+condition;
    ////////////
    System.out.println("before execut : "+requet);
    T[] objs=this.creerLstObjectsT(object,connection, requet);
    System.out.println("after execut : "+requet); 
    ///////////
    if(objs==null){ return null; }
    return objs[0];
  }
  
//---------------------------------------------------------------------------------------------------------------find All 
  public Object[] findAll(Object object,Connection connection)throws Exception{
    Object[] objs=this.creerLstObjects(object,connection, "select * from "+object.getClass().getSimpleName());
    return objs;
  }
  public T[] findAllT(T object,Connection connection)throws Exception{
    T[] objs=this.creerLstObjectsT(object,connection, "select * from "+object.getClass().getSimpleName());
    return objs;
  }
  
}
