package com.Veli.repository;

import com.Veli.repository.entity.Satis;
import com.Veli.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SatisRepository {
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

    public void save(Satis satis){
        try {
            Open();
            session.save(satis);//Kaydetme esnasında sorun olursa rollback yapılacak
            CloseCommit();
        }catch (Exception e){
            CloseRollBack();
        }
    }
    public void update(Satis satis){
        try {
            Open();
            session.update(satis);//Update esnasında sorun olursa rollback yapılacak
            CloseCommit();
        }catch (Exception e){
            CloseRollBack();
        }
    }
    public void delete(Satis satis){
        try {
            Open();
            session.delete(satis);//Delete esnasında sorun olursa rollback yapılacak
            CloseCommit();
        }catch (Exception e){
            CloseRollBack();
        }
    }
    public List<Satis> findAll(){
        List<Satis> result=null;
        Open();
        /**
         * sorgu neticesinde dönen değeri bir listeye atayacak.
         */
        Query query =session.createQuery("FROM tblmusteri");
        result = query.getResultList();
        return result;
    }
}
