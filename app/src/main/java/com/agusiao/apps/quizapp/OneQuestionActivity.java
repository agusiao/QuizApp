package com.agusiao.apps.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.agusiao.apps.quizapp.questions.Questions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.agusiao.apps.quizapp.Const.EXTRAS_QUESTION;
import static com.agusiao.apps.quizapp.Const.EXTRAS_RESULT;
import static com.agusiao.apps.quizapp.Const.QUESTION_3;

public class OneQuestionActivity extends AppCompatActivity {
    @BindView(R.id.ONE_TV_Question)
    TextView mQuestionTV;
    @BindView(R.id.ONE_RG_Answers)
    RadioGroup mAnswersVG;

    int mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_question);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mQuestion = bundle.getInt(EXTRAS_QUESTION, QUESTION_3);
            if (mQuestion == QUESTION_3) {
                mQuestionTV.setText(getString(R.string.q3_question));

            }
        }
    }

    @OnClick(R.id.ONE_BTN_OK)
    protected void checkAnswer(View view) {
        if (mQuestion == QUESTION_3) {
            checkQuestion3();
        }
    }

    private void checkQuestion3() {
        if (mAnswersVG.getCheckedRadioButtonId() == R.id.ONE_RB_Answer1) {
            Questions.setQuestionAnswerredCorrect(3);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(EXTRAS_RESULT, true);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            Questions.setQuestionAnswerredIncorrect(3);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(EXTRAS_RESULT, false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}
