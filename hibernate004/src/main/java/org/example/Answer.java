package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {
    public Answer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String answer;
    private boolean correctFlag;


    public Answer(String answer, boolean correctFlag) {
        this.answer = answer;
        this.correctFlag = correctFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrectFlag() {
        return correctFlag;
    }

    public void setCorrectFlag(boolean correctFlag) {
        this.correctFlag = correctFlag;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", correctFlag=" + correctFlag +
                '}';
    }
}

