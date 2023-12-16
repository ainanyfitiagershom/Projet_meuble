/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generalise;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.lang.reflect.Array;
/**
 *
 * @author Mirado
 */
public class Motherobj<T>
{ 
  public void create(Connection connection,String primarykey)throws Exception{
      GenericDao<T> gnDao=new GenericDao<T>();
      gnDao.save(this, connection,primarykey);
  }
  public T[] read(Connection connection)throws Exception{
    GenericDao<T> gnDao=new GenericDao<T>();
    T[] obj= gnDao.findAllT((T)this, connection);
    return obj;
  }
  public T readById(Connection connection,String primarykey)throws Exception{
      GenericDao<T> gnDao=new GenericDao<T>();
      T ob=gnDao.findT((T)this, connection, primarykey);
      return ob;
  }
  public void update(Connection connection,String primarykey)throws Exception{
      GenericDao<T> gnDao=new GenericDao<T>();
      gnDao.update(this, connection, primarykey);
  }
  public void delete(Connection connection,String primarykey)throws Exception{
      GenericDao<T> gnDao=new GenericDao<T>();
      gnDao.delete(this, connection, primarykey);
  }

  public T[] readByQueryConvenable(Connection connection,String query)throws Exception{
    GenericDao<T> gnDao=new GenericDao<T>();
    T[] objs=gnDao.creerLstObjectsT((T)this,connection,query);
      return objs;
  }
  public T readOneByQueryConvenable(Connection connection,String query)throws Exception{
    T[] objs=readByQueryConvenable(connection, query);
    if(objs==null){return null;}
    return objs[0];
  }  
}
