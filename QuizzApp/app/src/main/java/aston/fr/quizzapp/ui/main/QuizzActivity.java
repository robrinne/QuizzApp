package aston.fr.quizzapp.ui.main;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import aston.fr.quizzapp.R;
import aston.fr.quizzapp.quizz.Response_code;
import aston.fr.quizzapp.quizz.Results;
import utils.Constant;
import utils.FastDialog;
import utils.Network;

public class QuizzActivity extends AppCompatActivity {


    private TextView textViewCategory;
    private TextView textViewQuestion;
    private LinearLayout linearLayoutButtons;
    private TextView score;
    public static final String TAG = "Test";
    private Dialog dialog;

    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);


        textViewCategory = findViewById(R.id.textViewCategory);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        linearLayoutButtons = findViewById(R.id.linearLayoutButtons);

        score = findViewById(R.id.textViewPoint);
        dialog = FastDialog.showProgressDialog(QuizzActivity.this,"Chargement");


        if(Network.isNetworkAvailable(QuizzActivity.this)){

            RequestQueue queue = Volley.newRequestQueue(QuizzActivity.this);
            String url = Constant.URL;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.e(TAG, "json " + response);

                            Gson myGson = new Gson();
                            Response_code quizz = myGson.fromJson(response, Response_code.class);



                            if(quizz.getResponse_code() == 0) {

                                Results resultsQuizz = quizz.getResults().get(number);

                                textViewCategory.setText(resultsQuizz.getCategory());
                                textViewQuestion.setText(resultsQuizz.getQuestion());

                                String[] stringsResponses = new String[1 + resultsQuizz.getIncorrect_answers().length];
                                stringsResponses[0] = resultsQuizz.getCorrect_answer();

                                for(int i=0;i<resultsQuizz.getIncorrect_answers().length;i++) {
                                    stringsResponses[i+1] = resultsQuizz.getIncorrect_answers()[i];
                                }

                                // TODO : random shuffle du tableau

                                // display buttons
                                linearLayoutButtons.removeAllViews();

                                for(int i=0;i<stringsResponses.length;i++) {
                                    View viewButton = LayoutInflater.from(QuizzActivity.this).inflate(R.layout.item_button, null);

                                    Button buttonResponse = viewButton.findViewById(R.id.buttonResponse);
                                    buttonResponse.setText(stringsResponses[i]);

                                    buttonResponse.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            

                                        }
                                    });

                                    linearLayoutButtons.addView(viewButton);
                                }





                            }else{

                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }else{

            dialog.dismiss();

            FastDialog.showDialog(QuizzActivity.this, FastDialog.SIMPLE_DIALOG, "Vous devez être connecté");
        }

    }}