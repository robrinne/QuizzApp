package aston.fr.quizzapp.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Random;
import java.util.TimerTask;

import aston.fr.quizzapp.R;
import aston.fr.quizzapp.quizz.LastQuizzActivity;
import aston.fr.quizzapp.quizz.Response_code;
import aston.fr.quizzapp.quizz.Results;
import utils.Constant;
import utils.FastDialog;
import utils.Network;

public class QuizzActivity extends AppCompatActivity
{
    private TextView textViewCategory;
    private TextView textViewQuestion;
    private LinearLayout linearLayoutButtons;

    public static final String TAG = "Test";
    private Dialog dialog;
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        textViewCategory = findViewById(R.id.textViewCategory);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        linearLayoutButtons = findViewById(R.id.linearLayoutButtons);
        boolean refresh = true;

        dialog = FastDialog.showProgressDialog(QuizzActivity.this,"Chargement");

        boolean b = getIntent().getBooleanExtra("refresh", false);
        Log.e("BOOLEAN ====== ", ""+b);

        if(getIntent().getExtras() != null)
        {
            if(!getIntent().getBooleanExtra("refresh", false))
            {
                refresh = false;
            }
        }
        Log.e("Refresh :", ""+refresh);

        if(Network.isNetworkAvailable(QuizzActivity.this))
        {
            if(refresh) // cas où l'on doit charger les questions avec l'API
            {
                RequestQueue queue = Volley.newRequestQueue(QuizzActivity.this);
                String url = Constant.URL;
                url = String.format(url,Constant.NB_QUESTION,Constant.THEME_QUESTION,Constant.DIFFICULTY_QUESTION,Constant.TYPE_QUESTION);

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(final String response)
                    {
                        Log.e(TAG, "json " + response);

                        Gson myGson = new Gson();
                        final Response_code quizz = myGson.fromJson(response, Response_code.class);

                        if(quizz.getResponse_code() == 0)
                        {
                            final Results resultsQuizz = quizz.getResults().get(number);

                            textViewCategory.setText(resultsQuizz.getCategory());
                            textViewQuestion.setText(resultsQuizz.getQuestion());

                            final String[] stringsResponses = new String[1 + resultsQuizz.getIncorrect_answers().length];
                            Random r = new Random();
                            int index = r.nextInt((resultsQuizz.getIncorrect_answers().length) - 0 + 1) + 0;
                            Log.e("INDEX -------> ", ""+index);

                            int k = 0;
                            boolean flag = false;

                            while(k < resultsQuizz.getIncorrect_answers().length + 1)
                            {
                                if(k != index)
                                {
                                    if(!flag)
                                    {
                                        stringsResponses[k] = resultsQuizz.getIncorrect_answers()[k];
                                    }
                                    else
                                    {
                                        stringsResponses[k] = resultsQuizz.getIncorrect_answers()[k-1];
                                    }
                                }
                                else
                                {
                                    stringsResponses[index] = resultsQuizz.getCorrect_answer();
                                    flag = true;
                                }
                                k++;
                            }

                            // TODO : random shuffle du tableau

                            // display buttons
                            linearLayoutButtons.removeAllViews();

                            for(int i=0;i<stringsResponses.length;i++)
                            {
                                View viewButton = LayoutInflater.from(QuizzActivity.this).inflate(R.layout.item_button, null);

                                final Button buttonResponse = viewButton.findViewById(R.id.buttonResponse);
                                buttonResponse.setText(stringsResponses[i]);

                                buttonResponse.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        Button button = (Button) v;

                                        if (resultsQuizz.getCorrect_answer().equals(button.getText().toString() ))
                                        {
                                            Constant.SCORE_QUIZZ++;
                                            Toast.makeText(getApplicationContext(), "Bonne Reponse ! " + "\n"+ "Votre score est de : " + Constant.SCORE_QUIZZ , Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(), "FAUX !"+"\n" +"La bonne reponse était " + resultsQuizz.getCorrect_answer() + "\n" + "Votre score est de : " + Constant.SCORE_QUIZZ, Toast.LENGTH_LONG).show();
                                        }

                                        new Handler().postDelayed(new TimerTask()
                                        {
                                            @Override
                                            public void run()
                                            {
                                                Constant.REFRESH = false;
                                                Intent myIntent = new Intent(QuizzActivity.this, QuizzActivity.class);
                                                myIntent.putExtra("refresh", false);
                                                myIntent.putExtra("json", quizz);
                                                number = number+1;
                                                myIntent.putExtra("number", number);
                                                startActivity(myIntent);
                                                finish();
                                            }
                                        }, 1000);
                                    }
                                });
                                linearLayoutButtons.addView(viewButton);
                            }
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        // TODO : Erreur reponse API
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
            else // cas où l'on ne doit pas recharger les données de l'api
            {
                final Response_code quizz = (Response_code) getIntent().getSerializableExtra("json");;

                if(quizz != null && quizz.getResponse_code() == 0)
                {
                    number = getIntent().getIntExtra("number", 1);

                    if(number < Integer.parseInt(Constant.NB_QUESTION))
                    {
                        final Results resultsQuizz = quizz.getResults().get(number);

                        textViewCategory.setText(resultsQuizz.getCategory());
                        textViewQuestion.setText(resultsQuizz.getQuestion());

                        final String[] stringsResponses = new String[1 + resultsQuizz.getIncorrect_answers().length];
                        Random r = new Random();
                        int index = r.nextInt((resultsQuizz.getIncorrect_answers().length) - 0 + 1) + 0;
                        Log.e("INDEX -------> ", ""+index);

                        int k = 0;
                        boolean flag = false;

                        while(k < resultsQuizz.getIncorrect_answers().length + 1)
                        {
                            if(k != index)
                            {
                                if(!flag)
                                {
                                    stringsResponses[k] = resultsQuizz.getIncorrect_answers()[k];
                                }
                                else
                                {
                                    stringsResponses[k] = resultsQuizz.getIncorrect_answers()[k-1];
                                }
                            }
                            else
                            {
                                stringsResponses[index] = resultsQuizz.getCorrect_answer();
                                flag = true;
                            }
                            k++;
                        }

                        // TODO : random shuffle du tableau

                        // display buttons
                        linearLayoutButtons.removeAllViews();

                        for(int i=0;i<stringsResponses.length;i++)
                        {
                            View viewButton = LayoutInflater.from(QuizzActivity.this).inflate(R.layout.item_button, null);

                            final Button buttonResponse = viewButton.findViewById(R.id.buttonResponse);
                            buttonResponse.setText(stringsResponses[i]);

                            buttonResponse.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    Button button = (Button) v;

                                    if (resultsQuizz.getCorrect_answer().equals(button.getText().toString() ))
                                    {
                                        Constant.SCORE_QUIZZ++;
                                        Toast.makeText(getApplicationContext(), "Bonne Reponse ! " + "\n"+ "Votre score est de : " + Constant.SCORE_QUIZZ , Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "FAUX !"+"\n" +"La bonne reponse était " + resultsQuizz.getCorrect_answer() + "\n" + "Votre score est de : " + Constant.SCORE_QUIZZ , Toast.LENGTH_LONG).show();
                                    }

                                    new Handler().postDelayed(new TimerTask()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            Constant.REFRESH = false;
                                            Intent myIntent = new Intent(QuizzActivity.this, QuizzActivity.class);
                                            myIntent.putExtra("refresh", false);
                                            myIntent.putExtra("json", quizz);
                                            number = number+1;
                                            myIntent.putExtra("number", number);
                                            startActivity(myIntent);
                                            finish();
                                        }
                                    }, 1000);


                                }
                            });
                            linearLayoutButtons.addView(viewButton);
                        }
                    }
                    else
                    {
                        Intent myIntent = new Intent(QuizzActivity.this, LastQuizzActivity.class);
                        startActivity(myIntent);
                        finish();
                    }

                }
            }
        }
        else
        {

            dialog.dismiss();

            FastDialog.showDialog(QuizzActivity.this, FastDialog.SIMPLE_DIALOG, "Vous devez être connecté");
        }

    }
}