package com.agusiao.apps.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.agusiao.apps.quizapp.questions.Question;
import com.agusiao.apps.quizapp.questions.Questions;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.agusiao.apps.quizapp.Const.EXTRAS_QUESTION;
import static com.agusiao.apps.quizapp.Const.EXTRAS_RESULT;
import static com.agusiao.apps.quizapp.Const.QUESTION_0;
import static com.agusiao.apps.quizapp.Const.QUESTION_1;
import static com.agusiao.apps.quizapp.Const.QUESTION_2;
import static com.agusiao.apps.quizapp.Const.QUESTION_3;

public class MainActivity extends AppCompatActivity {
    private static final String SIS_SCORE = "score";
    private static final String SIS_BEST_SCORE = "best_score";
    private static final String SP_PREFS_NAME = "com.agusiao.apps.quizapp.score";
    private static final String SP_BEST_SCORE = "bestScore";

    @BindView(R.id.QL_TV_Score)
    protected TextView mScoreTV;
    @BindView(R.id.QL_TV_BestScore)
    protected TextView mBestScoreTV;
    @BindView(R.id.QL_RV_QuestionList)
    protected RecyclerView mQuestionList;

    private int mBestScore;
    private int mScore;

    private SharedPreferences mPreferences;
    private QuestionAdapter mQuestionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mPreferences = getSharedPreferences(SP_PREFS_NAME, Activity.MODE_PRIVATE);
        loadBestScore();
        loadDataToView();
        setOnClickForList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateScore();
        if (resultCode == Activity.RESULT_OK) {
            boolean returnValue = data.getBooleanExtra(EXTRAS_RESULT, false);
            if (returnValue) {
                Toast.makeText(this, getString(R.string.answer_correct), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getString(R.string.answer_incorrect), Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SIS_SCORE, mScoreTV.getText().toString());
        outState.putString(SIS_BEST_SCORE, mBestScoreTV.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String score = savedInstanceState.getString(SIS_SCORE);
        mScoreTV.setText(score);
        String bestScore = savedInstanceState.getString(SIS_BEST_SCORE);
        mBestScoreTV.setText(bestScore);
    }

    private void updateScore() {
        mScore = Questions.getScore();
        mScoreTV.setText(String.valueOf(mScore));
        if (mScore > mBestScore) {
            mBestScore = mScore;
            mBestScoreTV.setText(String.valueOf(mBestScore));
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putInt(SP_BEST_SCORE, mBestScore);
            editor.commit();
        }
    }

    private void setOnClickForList() {
        mQuestionList.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mQuestionList, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                Question question = Questions.getsQuestions().get(position);
                switch (question.getId()) {
                    case 0:
                        showQuestion0();
                        break;
                    case 1:
                        showQuestion1();
                        break;
                    case 2:
                        showQuestion2();
                        break;
                    case 3:
                        showQuestion3();
                        break;
                }
            }
        }));
    }

    private void showQuestion0() {
        Intent intent = new Intent(this, OpenQuestionActivity.class);
        intent.putExtra(EXTRAS_QUESTION, QUESTION_0);
        startActivityForResult(intent, QUESTION_0);
    }

    private void showQuestion1() {
        Intent intent = new Intent(this, YesNoQuestionActivity.class);
        intent.putExtra(EXTRAS_QUESTION, QUESTION_1);
        startActivityForResult(intent, QUESTION_1);
    }

    private void showQuestion2() {
        Intent intent = new Intent(this, MultiQuestionActivity.class);
        intent.putExtra(EXTRAS_QUESTION, QUESTION_2);
        startActivityForResult(intent, QUESTION_2);
    }

    private void showQuestion3() {
        Intent intent = new Intent(this, OneQuestionActivity.class);
        intent.putExtra(EXTRAS_QUESTION, QUESTION_3);
        startActivityForResult(intent, QUESTION_3);
    }

    private void loadDataToView() {
        loadBestScore();
        Questions.initQuestions(this.getResources());
        mQuestionsAdapter = new QuestionAdapter(Questions.getsQuestions());
        mQuestionList.setAdapter(mQuestionsAdapter);
    }

    private void loadBestScore() {
        mBestScore = mPreferences.getInt(SP_BEST_SCORE, 0);
        mBestScoreTV.setText(String.valueOf(mBestScore));
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mScoreTV.setText("0");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mQuestionList.setLayoutManager(mLayoutManager);
        mQuestionList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
