package com.example.stackoverflowapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    Context context;//To inflate the  question_list_item layout
    int resource;//This will be an item in the list
    List<Question> questionList;//Contains teh questions from the API.

    //Constructor to initialise our Array adapter class
    public QuestionListAdapter (Context context, int resource, List<Question> questionList) {
        super(context, resource, questionList);
        this.context = context;
        this.resource = resource;
        this.questionList = questionList;
    }

    //With this we can get the position and other details like question ID and Link to question
    //which can be used to open the questions and look at the answers depending on element selected.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        //To get the Imageview and textview components
        View view = inflater.inflate(R.layout.question_list_item, null);

        TextView textViewTitle= view.findViewById(R.id.textViewTitle);
        TextView textViewNumOfComments = view.findViewById(R.id.textViewNumOfComments);
        TextView textViewScore = view.findViewById(R.id.textViewScore);
        TextView textViewListOfTags = view.findViewById(R.id.textViewListOfTags);
        TextView textViewAnswerCount = view.findViewById(R.id.textViewAnswerCount);
        ImageView imageViewUserPic = view.findViewById(R.id.imageViewUserPic);

        Question question = questionList.get(position);

        textViewTitle.setText(question.getTitle());
        textViewListOfTags.setText(question.getTags());
        textViewAnswerCount.setText(question.getAnswerCount());
        textViewScore.setText(question.getScore());
        textViewNumOfComments.setText(question.getNumOfComments());

        //Using Picasso instead of Thread manipulation
        Picasso.with(context).load(question.getUserPic()).into(imageViewUserPic);

        return view;
    }
}
