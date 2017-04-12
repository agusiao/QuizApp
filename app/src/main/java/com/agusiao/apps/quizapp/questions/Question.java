package com.agusiao.apps.quizapp.questions;

/**
 * Created by User on 4/11/2017.
 */

public class Question {
    private int id;
    private String title;
    private EQuestionType type;
    private EAnswerStatus status;

    public Question(int id, String title, EQuestionType type) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.status = EAnswerStatus.UNKNOWN;
    }

    public String getTitle() {
        return title;
    }

    public EQuestionType getType() {
        return type;
    }

    public EAnswerStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setStatus(EAnswerStatus status) {
        this.status = status;
    }
}
