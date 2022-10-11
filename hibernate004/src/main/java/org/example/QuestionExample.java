package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

public class QuestionExample {
    public static void main(String[] args) {

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().
                                                    configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Question question = new Question("2+2 ? ");
        Answer answer1 = new Answer("4",true);
        Answer answer2 = new Answer("1",false);
        Answer answer3 = new Answer("5",false);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        question.setAnswers(answers);
       // session.persist(question);

        Question question2 = new Question("5-4");
        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("0",false));
        answers2.add(new Answer("1",true));
        question2.setAnswers(answers2);
       // session.persist(question2);

       //Reading info from database

        Query query = session.createQuery("FROM Question",Question.class);
        List<Question> questions = query.list();
        questions.stream().forEach(q -> {
            System.out.println("\n" + q + ":");
            q.getAnswers().stream().forEach(a -> System.out.println("-" + a)  );
        } );



        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
