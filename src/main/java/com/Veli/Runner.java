package com.Veli;

import com.Veli.repository.MusteriRepository;
import com.Veli.repository.entity.Musteri;
import com.Veli.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {
      /*
      KAYIT V.1
      Musteri mst = new Musteri();
      mst.setAd("Veli");
      mst.setSoyad("CANLI");
      mst.setAdres("Ankara");
      MusteriRepository dbmusteri =new MusteriRepository();
      dbmusteri.save(mst);
        */
        // KAYIT V.2
        new MusteriRepository().save(new Musteri("Emre","YILMAZ","Ankara"));
        /**
         * foreach -> iterable bir liste içindeki değerlere tek tek ulaşır.
         * her seferinde liste/array içindeki bir değere ulaşır. döngü listenin son gelinceye kadar devam eder.
         * Array -> String[] siniflistesi =new String[15];
         * Liste -> List<String> sinifListesi = new ArrayList();
         *
         * Öncelikle yeni bir repositaory nesnesi oluşturuyorsunuz.
         * findAll ile tüm listeyi çekmek istiyorum.
         * DİKKAT!! findAll içinde new Musteri() şeklinde bir kullanım vardır.
         * neden? ilgili method sorgulama yapabilmek için gerekli olan sınıf öğrendiği alsın diye
         * boş bir nesne veriyoruz.
         */
        for(Musteri mst : new MusteriRepository().findAll(new Musteri())){
            System.out.println("Müşteri id...................: "+mst.getId());
            System.out.println("Müşteri Adı...................: "+mst.getAd());
            System.out.println("Müşteri Soyadı...................: "+mst.getSoyad());
            System.out.println("Müşteri Adresi...................: "+mst.getAdres());
        }

    }
    public static void SaveHibernate(){
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
