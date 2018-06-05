package com.hangman.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.UserDictionary;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import Wordslist.Wordslist;

public class MainActivity extends AppCompatActivity {


    int k;int chk=0;
    int score=6;int y=0;
    String txt,ori;
    String[] red=new String[100];
    TextView word,Desc,wrng,scor;
    EditText letter;
    ImageView manimg;
    Button guess;
    String [] items = new String[100];
    String wrong="";
    int HighScore;


    private Wordslist mWordslist = new Wordslist();

    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();


        guess = (Button) findViewById(R.id.guess);
        letter=(EditText)findViewById(R.id.letter);
        word=(TextView)findViewById(R.id.word);
        Desc=(TextView)findViewById(R.id.Desc);
        wrng=(TextView)findViewById(R.id.wrng);
        scor=(TextView) findViewById(R.id.scor);
        manimg=(ImageView)findViewById(R.id.manimg);
        begin();

    guess.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String result = letter.getText().toString();
            result=result.toUpperCase();


            for(int i=1;i<=txt.length();i++) {{
                if (items[i].equals(result)) {
                    red[i] = result;
                    y++;
                } else if (red[i] != "-") {
                } else {

                    red[i] = "-";
                }
            }
                if(i==1)
                {ori=red[i];}
                if(i!=1)
                {ori=ori+red[i];}
                }

                if(y==0)
                {score--;
                    scor.setText("CURRENT SCORE: "+score+"\n"+"HIGHSCORE: "+HighScore);
                    wrong=wrong+" "+result;
                    wrng.setText("   GUESSED WRONG LETTERS :"+wrong);}

                if(score==5)
                {manimg.setImageResource(R.drawable.face);}
                else if(score==4)
                {manimg.setImageResource(R.drawable.body);}
                else if(score==3)
                {manimg.setImageResource(R.drawable.hand1);}
                else if(score==2)
                {manimg.setImageResource(R.drawable.hand2);}
                else if(score==1)
                {manimg.setImageResource(R.drawable.leg1);}
                else if(score==0)
                {manimg.setImageResource(R.drawable.leg2);}


                y=0;


                word.setText(ori);

                for(int j=0;j<txt.length();j++)
                {if(red[j] == ("-"))
                 {chk++;}
                }

                if(score==0)
                {gameover();}

                if(chk==0)
                {congrats();}

                chk=0;



        }
    });





    }


    private void begin(){
        readmsg();
        scor.setText("CURRENT SCORE: "+"6\n"+"HIGHSCORE: "+HighScore);
        k = r.nextInt(20);
        txt=mWordslist.getWords(k);

        items=txt.split("");
        ori=items[0];
        for(int i=1;i<=txt.length();i++) {
            if (items[i] == "/") {
                red[i] = "/";

            } else {
                red[i] = "-";
            }
            ori=ori+red[i];
        }

        word.setText(ori);
        Desc.setText("Description: "+mWordslist.getitems(k));
    }

    private void congrats() {

        if(score>HighScore) {
            writ();
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("Awesome!!Word found!. Score: "+score)
                .setCancelable(false)
                .setPositiveButton("CONTINUE",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        })

                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialogBuilder.show();




    }


    private void gameover() {

        if(score>HighScore) {
            writ();
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("GameOver :(. Score: "+score)
                .setCancelable(false)
                .setPositiveButton("CONTINUE",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        })

                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialogBuilder.show();




    }



    public void writ()
    {         SharedPreferences sharedPreferences=getSharedPreferences("Mydata",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("Highscore",score);

        editor.commit();

        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();

    }


    public void readmsg()
    {

       SharedPreferences sharedPreferences=getSharedPreferences("Mydata",MODE_PRIVATE);
       HighScore=sharedPreferences.getInt("Highscore",0);


    }



}
