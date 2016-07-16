package com.bignerdranch.android.geoquiz;

/**
 * Created by sam on 16/7/13.
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mAnswerCheated;

    public boolean isAnswerCheated() {
        return mAnswerCheated;
    }

    public void setAnswerCheated(boolean answerCheated) {
        mAnswerCheated = answerCheated;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
