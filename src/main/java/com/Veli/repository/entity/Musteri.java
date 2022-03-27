package com.Veli.repository.entity;

import javax.persistence.*;

/**
 * Entity ve Table => Annotaions dur.
 * Entity ve Table açarken persistence olması önemli...
 * Entity, sınıfın bir veritabanı varlığı olduğunu niteliyor.
 * Table, bu sınıfın bir tablo olduğunu ve özelliklerinin neler olduğunu belirtmek için kullanıyoruz.
 * Eğer name propery kullanılmaz ise sınıf adı tablo adı olarak kullanılır.
 * name -> sınıfının veritabanındaki adının ne olacağını belirtiyoruz.
 */
@Entity
@Table(name = "tblmusteri")
public class Musteri {
    // @Id annotaions primary key olduğunu işaretlemek için kullanılır.
    @Id
    /**
     * @id nin otomaik sayı olarak kullanılabilmesi için iki farklı yöntem vardır.
     *  1. bize sunulan otomatik sayı üreten strateji tipi
     *   @GeneratedValue(strategy = GenerationType.AUTO)
     *  2. Elle Squence oluşturabiliriz ve bunu generate edebiliriz.
     *  @SequenceGenerator(name = "sq_tblmusteri_id",
     *                         sequenceName = "sq_tblmusteri_id",
     *                         initialValue = 1,
     *                         allocationSize = 5)
     *     @GeneratedValue(generator ="sq_tblmusteri_id")
      */
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    /**
     * Eğer alanlarınızı hiçbir annotaions eklemeden bırakırsanız default değerler ile devam eder.
     * String bir alan-> varchar olur ve max uzunluğu 255 karakter olarak atanır.
     */
    @Column(name = "musteriadi",length = 100,nullable = false)
    String ad;
    String soyad;
    @Column(length = 500)
    String adres;

    public Musteri() {
    }

    public Musteri(String ad, String soyad, String adres) {
        this.ad = ad;
        this.soyad = soyad;
        this.adres = adres;
    }

    public Musteri(long id, String ad, String soyad, String adres) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.adres = adres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Musteri{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", adres='" + adres + '\'' +
                '}';
    }
}
