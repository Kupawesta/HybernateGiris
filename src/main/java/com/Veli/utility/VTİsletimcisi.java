package com.Veli.utility;

import com.Veli.repository.entity.Musteri;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.lang.reflect.Field;
import java.util.List;

/**
 * JAVA Generic Types
 */

public class VTİsletimcisi<T> {
    private Session session;
    private Transaction transaction;

    private void Open(){
        session= HibernateUtility.getSessionFactory().openSession();
        transaction=session.beginTransaction();
    }
    private void CloseCommit(){
        transaction.commit();
        session.close();
    }
    private void CloseRollBack(){
        transaction.rollback();
        session.close();
    }

    public void save(T t){
        try {
            Open();
            session.save(t);//Kaydetme esnasında sorun olursa rollback yapılacak
            CloseCommit();
        }catch (Exception e){
            CloseRollBack();
        }
    }
    public void update(T t){
        try {
            Open();
            session.update(t);//Update esnasında sorun olursa rollback yapılacak
            CloseCommit();
        }catch (Exception e){
            CloseRollBack();
        }
    }
    public void delete(T t){
        try {
            Open();
            session.delete(t);//Delete esnasında sorun olursa rollback yapılacak
            CloseCommit();
        }catch (Exception e){
            CloseRollBack();
        }
    }
    /**
    public List<T> findAll(){
        List<T> result=null;
        Open();

        //sorgu neticesinde dönen değeri bir listeye atayacak.

        Query query =session.createQuery("FROM tblmusteri");
        result = query.getResultList();
        return result;
    }
    */
    public List<T> findAll(T t){
        Open();
        /**
         * Boş bir müşteri geri dönüş listesi oluşturduk.
         */
        List<T> result =null;
        /**
         * İlgili sınıf üzerinden sorgulama yapabilmek için bir kriter oluşturuyoruz.
         * bu kriter select * from .... kısmını oluşturuyor.
         * t.getClass() boşluk kısmındaki alanı doldurur.
         */
        Criteria cr = session.createCriteria(t.getClass());
        /**
        t nin türüne göre geri dönen değeri bir listenin içine atar.
         */
        result=cr.list();
        CloseCommit();
        return result;
    }

    public T findById(long id,T t){
        T result = null;
        Open();
        //select * from ......
        Criteria cr = session.createCriteria(t.getClass());
        //where id=?
        cr.add(Restrictions.eq("id",id));
        //2 durum aar id sini aradığım değer vardır ya da yoktur.
        //kriter ile sorgu yaptığında gelen listenin boyutu 0 dan büyük ise işlem yap.
        if(cr.list().size()>0){
            //list.get() -> bir liste içindeki verilen index numarasından lan kaydı getirir.
            result = (T)cr.list().get(0);
        }
        return result;
    }

    public List<T> findByColumn(String columnName,String  value,boolean isEquals, T t){
        //isEquals true ise adı veli olanları getirir
        //isEquals false is adı ve ile başlayanları getirir.
        List<T> result = null;
        Open();
        Criteria cr = session.createCriteria(t.getClass());
        if(isEquals)
            cr.add(Restrictions.eq(columnName,value));
        else
            //ilike küçük büyük harf duyarlı olmazsızın arama yapar.
            cr.add(Restrictions.ilike(columnName,"%"+value+"%"));
        result=cr.list();
        return result;
    }

    public List<T> findAnyItem(T t){
        /**
         * Java reflection
         *
         */
        List<T> result = null;
        /**
         * t ile gelen nesnenin sınıf olarak alınmasını sağlıyoruz.
         */
        Class cl = t .getClass();
        /**
         * Bu sınıf içinde değişken olarak tanımlanmış alanların tümünü bir liste
         * içinde ıkumamızı sağlar.
         */
        Field[] fields = cl.getDeclaredFields();
        /*
        for ile değişkenleri tek tek dönüyoruz.
         */
        Open();
        Criteria cr =session.createCriteria(t.getClass());

        try{
            for(int i=0; i<fields.length;i++){
                fields[i].setAccessible(true);

                if (fields[i].getType().equals("long")){
                    if((long)fields[i].get(t)>0){
                        cr.add(Restrictions.eq(fields[i].getName(),(long)fields[i].get(t)));
                    }
                }else if(fields[i].getType().equals("class java.lang.String")){
                    if(fields[i].get(t) !=null)
                    cr.add(Restrictions.ilike(fields[i].getName(),"%"+fields[i]+"%"));
                }
                System.out.println("Alanlar "+i+".... : "+fields[i].getName());
                System.out.println("Değer ........... : "+fields[i].get(t));
                System.out.println("Type  ........... : "+fields[i].getType());

            }
        }catch (Exception e){
            System.out.println("Hata......: "+e.toString());
        }

        return null;
    }
}
