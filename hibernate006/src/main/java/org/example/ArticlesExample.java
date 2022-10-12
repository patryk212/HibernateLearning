package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.swing.plaf.basic.BasicTreeUI;
import java.util.List;

public class ArticlesExample {
    public static void main(String[] args) {

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().
                                                        configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Article article = new Article("ArticleTitle #1","content");
        Tag tag1 = new Tag("Sport");
        article.addTag(tag1);
        Tag tag2 = new Tag("Java");
        article.addTag(tag2);

        //session.persist(article);

        Query query = session.createQuery("FROM Article", Article.class);
        List<Article> articles = query.list();

        articles.stream().forEach(a ->{
            System.out.println(a + ": ");
            a.getTags().stream().forEach(t -> System.out.println("-" + t));
        });

        transaction.commit();
        sessionFactory.close();
        session.close();
    }
}
