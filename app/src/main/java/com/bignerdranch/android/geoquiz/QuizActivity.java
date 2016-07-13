package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_oceans, true)
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        updateQuestion();

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrentIndex(false);
                updateQuestion();
            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrentIndex(false);
                updateQuestion();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrentIndex(true);
                updateQuestion();
            }
        });
    }

    private void updateCurrentIndex(boolean isPrev) {
        int toIndex = isPrev ? (mCurrentIndex - 1) : (mCurrentIndex + 1);
        mCurrentIndex = toIndex % mQuestionBank.length;
    }

    private void updateQuestion() {
        int questionId = mQuestionBank[Math.abs(mCurrentIndex)].getTextResId();
        mQuestionTextView.setText(questionId);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean questionAnswer = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int msgId;

        if (questionAnswer == userPressedTrue) {
            msgId = R.string.correct_toast;
        } else {
            msgId = R.string.false_button;
        }

        Toast.makeText(QuizActivity.this, msgId, Toast.LENGTH_SHORT).show();
    }
}
