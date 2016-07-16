package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_USER_CHEATED = "com.bignerdranch.android.geoquiz.user_cheated";

    private static final String KEY_SHOWN = "shown";

    private Button mCheatButton;
    private TextView mAnswerText;
    private Boolean mAnswerIsTrue = false;
    private Boolean mAnswerShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mCheatButton = (Button) findViewById(R.id.show_answer_button);
        mAnswerText = (TextView) findViewById(R.id.answer_text);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        if (savedInstanceState != null) {
            toggleAnswerShown(savedInstanceState.getBoolean(KEY_SHOWN));
        }

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleAnswerShown(true);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putBoolean(KEY_SHOWN, mAnswerShown);
    }

    private void toggleAnswerShown(Boolean toShow) {
        if (!toShow) {
            mAnswerText.setText("");
            mAnswerShown = false;
        } else {
            mAnswerText.setText(mAnswerIsTrue ? R.string.true_button : R.string.false_button);
            mAnswerShown = true;
            setAnswerShownResult();
        }
    }

    private void setAnswerShownResult() {
        Intent i = new Intent();
        i.putExtra(EXTRA_USER_CHEATED, mAnswerShown);
        setResult(Activity.RESULT_OK, i);
    }

    public static Intent newIntent(Context context, Boolean answerIsTrue) {
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static Boolean wasUserCheated(Intent intent) {
        return intent.getBooleanExtra(EXTRA_USER_CHEATED, false);
    }
}
