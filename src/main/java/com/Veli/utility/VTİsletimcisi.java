package com.Veli.utility;

import com.Veli.repository.entity.Musteri;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
    public List<T> findAll(){
        List<T> result=null;
        Open();
        /**
         * sorgu neticesinde dönen değeri bir listeye atayacak.
         */
        Query query =session.createQuery("FROM tblmusteri");
        result = query.getResultList();
        return result;
    }
}
