package com.Veli;

import com.Veli.repository.entity.Musteri;
import com.Veli.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {
        /**
         * Hibernate ile Veri eklemek
         * 1. Bağlantıyı aç (Oturum açmak-Session)
         * hibernate yönetici dosyası olan utility, bağlantıyı açar ve yeni bir oturum oluşturur.
         */
        Session session = HibernateUtility.getSessionFactory().openSession();
        /**
         * 2. Transaction oluştur ve sistemi izlemeye başla.
         * (İzleyici -Yapılan işlemleri takip edip hata oluştuğunda,
         * geriye doğru tüm işlemleri iptal edip eski haline getiren işlemler bütünü.)
         *
         */
        Transaction transaction = session.beginTransaction();
        Musteri mst = new Musteri("Veli","CANLI","Ankara");
        session.save(mst);
        transaction.commit();
        session.close();

    }
}
