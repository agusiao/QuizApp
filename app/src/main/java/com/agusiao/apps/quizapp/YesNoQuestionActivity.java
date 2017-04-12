package com.agusiao.apps.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.agusiao.apps.quizapp.questions.Questions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.agusiao.apps.quizapp.Const.EXTRAS_QUESTION;
import static com.agusiao.apps.quizapp.Const.EXTRAS_RESULT;
import static com.agusiao.apps.quizapp.Const.QUESTION_0;
import static com.agusiao.apps.quizapp.Const.QUESTION_1;

public class YesNoQuestionActivity extends AppCompatActivity {
    @BindView(R.id.YNA_TV_Question)
    TextView mQuestionTV;

    int mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_no_question);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mQuestion = bundle.getInt(EXTRAS_QUESTION, QUESTION_1);
            if (mQuestion == QUESTION_1) {
                mQuestionTV.setText(getString(R.string.q2_question));
            }
        }
    }

    @OnClick(R.id.YNA_BTN_Yes)
    protected void answerYes(View view) {
        if (mQuestion == QUESTION_1) {
            Questions.setQuestionAnswerredCorrect(1);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(EXTRAS_RESULT, true);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    @OnClick(R.id.YNA_BTN_No)
    protected void answerNo(View view) {
        if (mQuestion == QUESTION_1) {
            Questions.setQuestionAnswerredIncorrect(1);
            ;
            Intent returnIntent = new Intent();
            returnIntent.putExtra(EXTRAS_RESULT, false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}
