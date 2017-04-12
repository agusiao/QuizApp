package com.agusiao.apps.quizapp.questions;

import android.content.res.Resources;

import com.agusiao.apps.quizapp.R;

import java.util.ArrayList;
import java.util.List;


public class Questions {
    static private List<Question> sQuestions = new ArrayList<Question>();

    static public void initQuestions(Resources resources) {
        if(sQuestions.isEmpty()){
            addQuestion(0, resources, R.string.q1_question, EQuestionType.OPEN);
            addQuestion(1, resources, R.string.q2_question, EQuestionType.YES_NO);
            addQuestion(2, resources, R.string.q3_question, EQuestionType.MULTIPLE_CHOICE);
            addQuestion(3, resources, R.string.q4_question, EQuestionType.ONE_ANSWER);
        }

    }

    static private void addQuestion(int id, Resources resources, int questionRid, EQuestionType type) {
        String questionText = resources.getString(questionRid);
        Question question = new Question(id, questionText, type);
        sQuestions.add(question);
    }

    static public List<Question> getsQuestions() {
        return sQuestions;
    }

    static public void setQuestionAnswerredCorrect(int i){
        if(i>=0 && i<sQuestions.size()) {
            sQuestions.get(i).setStatus(EAnswerStatus.ANSWEERED_CORRECT);
        }
    }

    static public void setQuestionAnswerredIncorrect(int i){
        if(i>=0 && i<sQuestions.size()) {
            sQuestions.get(i).setStatus(EAnswerStatus.ANSWERED_INCORRECT);
        }
    }

    static public int getScore() {
        int score =0;
        for(Question question: sQuestions) {
            if(question.getStatus().equals(EAnswerStatus.ANSWEERED_CORRECT)){
                score++;
            }
        }
        return score;
    }
}
