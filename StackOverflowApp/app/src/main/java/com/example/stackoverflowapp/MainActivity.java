package com.example.stackoverflowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Question> questionList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionList = new ArrayList<>();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.stackexchange.com/2.3/questions?page=1&order=desc&sort=" +
                "activity&tagged=Android&site=stackoverflow";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //Convert the response into a JSON Object. Then extract the JSONArray
                            // which consists of Question data from the API
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray itemsArray = jsonObject.getJSONArray("items");

                            //Iterating through each array item and setting the array adapter
                            //Time complexity N
                            for (int i = 0; i < itemsArray.length(); i++) {

                                String imageUrl = String.valueOf(itemsArray.
                                        getJSONObject(i).getJSONObject("owner").get("profile_image"));

                                String title = String.valueOf(itemsArray.getJSONObject(i).
                                        get("title"));

                                String numOfComments = "Comments not available";

                                String score = "Score: " + String.valueOf(itemsArray.
                                        getJSONObject(i).get("score"));

                                //Not printing this in a list to preserve Time complexity of N
                                //Just prints the JSON Array "tags" as a string.
                                String tags = "  Tags: " + String.valueOf(itemsArray.
                                        getJSONObject(i).get("tags"));

                                String answerCount = String.valueOf(itemsArray
                                        .getJSONObject(i).
                                        get("answer_count")) + " answers";

                                questionList.add(new Question(imageUrl, title, numOfComments,
                                        score, tags, answerCount));

                                listView = findViewById(R.id.listViewQuestions);

                                QuestionListAdapter questionListAdapter = new
                                        QuestionListAdapter(getApplicationContext(),
                                        R.layout.question_list_item, questionList);

                                listView.setAdapter(questionListAdapter);
                            }


                        } catch (JSONException err) {
                            Log.d("Error", err.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "That didn't work!",
                        Toast.LENGTH_LONG).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


}