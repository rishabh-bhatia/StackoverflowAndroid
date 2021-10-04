package com.example.stackoverflowapp;

public class Question {

    String userPic;

    String title, numOfComments, score, tags, answerCount;

    public Question(String userPic, String title, String numOfComments, String score, String tags, String answerCount) {
        this.userPic = userPic;
        this.title = title;
        this.numOfComments = numOfComments;
        this.score = score;
        this.tags = tags;
        this.answerCount = answerCount;
    }

    public String getUserPic() {
        return userPic;
    }

    public String getTitle() {
        return title;
    }

    public String getNumOfComments() {
        return numOfComments;
    }

    public String getScore() {
        return score;
    }

    public String getTags() {
        return tags;
    }

    public String getAnswerCount() {
        return answerCount;
    }
}
