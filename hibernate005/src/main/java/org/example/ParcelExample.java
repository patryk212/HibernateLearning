package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class ParcelExample {
    public static void main(String[] args) {

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().
                                                            configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Parcel parcel = new Parcel("Warsaw",10);
        ParcelAddress parcelAddress = new ParcelAddress("Poland","Warsaw","Wilcza","0001");

        parcel.setParcelAddress(parcelAddress);

        //session.persist(parcel);

        Query query = session.createQuery("FROM Parcel",Parcel.class);
        List<Parcel> parcels = query.list();

        parcels.stream().forEach(p -> System.out.println(p));

        transaction.commit();
        sessionFactory.close();
        session.close();


    }
}
