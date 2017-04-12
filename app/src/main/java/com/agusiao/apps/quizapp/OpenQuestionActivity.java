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
import static com.agusiao.apps.quizapp.Const.QUESTION_0;
import static com.agusiao.apps.quizapp.Const.EXTRAS_RESULT;

public class OpenQuestionActivity extends AppCompatActivity {
    @BindView(R.id.OQ_TV_Question)
    TextView mQuestionTV;
    @BindView(R.id.OQ_ET_Answer)
    EditText mAnswer;

    int mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_question);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mQuestion = bundle.getInt(EXTRAS_QUESTION, QUESTION_0);
            if (mQuestion == QUESTION_0) {
                mQuestionTV.setText(getString(R.string.q1_question));
            }
        }
    }

    @OnClick(R.id.OQ_BTN_OK)
    protected void checkAnswer(View view) {
        if (mAnswer.getText().length() == 0) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();

        } else if (mQuestion == QUESTION_0) {
            checkQuestion0();
        }
    }

    private void checkQuestion0() {
        if (mAnswer.getText().toString().equals("2") || mAnswer.getText().toString().equalsIgnoreCase(getString(R.string.q1_answer))) {
            Questions.setQuestionAnswerredCorrect(0);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(EXTRAS_RESULT, true);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            Questions.setQuestionAnswerredIncorrect(0);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(EXTRAS_RESULT, false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

}
