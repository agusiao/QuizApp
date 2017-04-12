package com.agusiao.apps.quizapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.agusiao.apps.quizapp.questions.EAnswerStatus;
import com.agusiao.apps.quizapp.questions.EQuestionType;
import com.agusiao.apps.quizapp.questions.Question;


public class QuestionViewHolder extends RecyclerView.ViewHolder {
    private final TextView mQuestionTitle;
    private final TextView mQuestionType;
    private final ImageView mQuestionTypeIV;
    private final ImageView mQuestionAnswered;

    public QuestionViewHolder(View itemView) {
        super(itemView);
        mQuestionTitle = (TextView) itemView.findViewById(R.id.LRQ_TV_QuestionName);
        mQuestionType = (TextView) itemView.findViewById(R.id.LRQ_TV_Type);
        mQuestionTypeIV = (ImageView) itemView.findViewById(R.id.LRQ_IV_Type);
        mQuestionAnswered = (ImageView) itemView.findViewById(R.id.LRQ_IV_Answered);
    }

    public void initView(Question question) {
        initView(question.getTitle(), question.getType(), question.getStatus());
    }

    public void initView(String title, EQuestionType type, EAnswerStatus status) {
        mQuestionTitle.setText(title);
        showType(type);
        showAnswer(status);
    }

    private void showAnswer(EAnswerStatus answer) {
        switch (answer) {
            case ANSWEERED_CORRECT:
                mQuestionAnswered.setImageResource(R.mipmap.ic_answer_correct);
                break;
            case ANSWERED_INCORRECT:
                mQuestionAnswered.setImageResource(R.mipmap.ic_answer_incorrect);
                break;
            case UNKNOWN:
                mQuestionAnswered.setImageResource(R.mipmap.ic_answer_none);
                break;

        }
    }

    private void showType(EQuestionType type) {
        switch (type) {
            case ONE_ANSWER:
                mQuestionTypeIV.setImageResource(R.mipmap.ic_question_one);
                break;
            case MULTIPLE_CHOICE:
                mQuestionTypeIV.setImageResource(R.mipmap.ic_question_multiple);
                break;
            case OPEN:
                mQuestionTypeIV.setImageResource(R.mipmap.ic_question_open);
                break;
            case YES_NO:
                mQuestionTypeIV.setImageResource(R.mipmap.ic_question_yesno);
                break;
        }
    }
}