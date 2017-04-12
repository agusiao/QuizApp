package com.agusiao.apps.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.agusiao.apps.quizapp.questions.Questions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.agusiao.apps.quizapp.Const.EXTRAS_QUESTION;
import static com.agusiao.apps.quizapp.Const.EXTRAS_RESULT;
import static com.agusiao.apps.quizapp.Const.QUESTION_2;

public class MultiQuestionActivity extends AppCompatActivity {
    @BindView(R.id.MUL_TV_Question)
    TextView mQuestionTV;
    @BindView(R.id.MUL_CB_Answer1)
    CheckBox mAnswer1CB;
    @BindView(R.id.MUL_CB_Answer2)
    CheckBox mAnswer2CB;
    @BindView(R.id.MUL_CB_Answer3)
    CheckBox mAnswer3CB;
    @BindView(R.id.MUL_CB_Answer4)
    CheckBox mAnswer4CB;

    int mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_question);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mQuestion = bundle.getInt(EXTRAS_QUESTION, QUESTION_2);
            if (mQuestion == QUESTION_2) {
                mQuestionTV.setText(getString(R.string.q3_question));
                mAnswer1CB.setText(getString(R.string.q3_answer1));
                mAnswer2CB.setText(getString(R.string.q3_answer2));
                mAnswer3CB.setText(getString(R.string.q3_answer3));
                mAnswer4CB.setText(getString(R.string.q3_answer4));
            }
        }
    }

    @OnClick(R.id.MUl_BTN_Ok)
    protected void checkAnswer(View view) {
        if (mQuestion == QUESTION_2) {
            checkQuestion2();
        }
    }

    private void checkQuestion2() {
        if (mAnswer1CB.isChecked() && mAnswer2CB.isChecked() && !mAnswer3CB.isChecked() && mAnswer4CB.isChecked()) {
            Questions.setQuestionAnswerredCorrect(2);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(EXTRAS_RESULT, true);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            Questions.setQuestionAnswerredIncorrect(2);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(EXTRAS_RESULT, false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}
