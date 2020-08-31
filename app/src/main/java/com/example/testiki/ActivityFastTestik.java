package com.example.testiki;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.IntArrayEvaluator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityFastTestik extends AppCompatActivity {

    static TextView Word,numbers;
    static TextView polata;
    static Button button1;
    static Button button2;
    static Button button3;
    static Button button4;
    static Button retest;
    static int randomWord,count = 11;
    //static int i = 0;
    static int schet = 0;
    public static ArrayList<Word> wordList = MainActivity.getList();
    static ArrayList<Word> wordListUses;
    static ArrayList<Integer> integerArrayList = new ArrayList<>();
    public static boolean isMain = true;
    static int j = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_testik);
        unitAll();
        addListener();
        test();

    }

    private void addListener() {
        retest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j = 1;
                schet = 0;
                numbers.setText(String.valueOf(j + "/" + 10));
                polata.setText(String.valueOf(schet));
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
                integerArrayList = new ArrayList<>();
                test();
            }
        });
    }

    private static void test(){
        //if(!isMain) count = wordList.size();
        if(j < count + 1) { //count
           // if (wordListUses.size() > 4) {
                numbers.setText(String.valueOf(j + "/" + count));
                randomWord = (int) (Math.random() * wordList.size());
                if(integerArrayList.size() != 0) {
                    while (true) {
                        int c = 0;
                        for(Integer number: integerArrayList) {
                            if(number == randomWord) {
                                randomWord = (int) (Math.random() * wordList.size());
                                c = 1;
                                break;
                            }
                        }
                        if(c == 0)break;
                    }
                }
                integerArrayList.add(randomWord);

                Word.setText(wordList.get(randomWord).getName());

                int random = (int) (Math.random() * 4);
                //int[] massRan = new int[3];
            int[] massRan = {-1,-1,-1};
                for (int k = 0; k < 3; k++) {
                    while (true) {
                        int temp = (int) (Math.random() * wordList.size());
                        if (randomWord != temp && massRan[0] != temp &&
                                massRan[1] != temp && massRan[2] != temp) {
                            massRan[k] = temp;
                            break;
                        }
                    }
                }

                switch (random) {
                    case 0: {
                        setButtListener(button1, wordList.get(randomWord).getTranslate());
                        setButtWrongListener(button2, wordList.get(massRan[0]).getTranslate());
                        setButtWrongListener(button3, wordList.get(massRan[1]).getTranslate());
                        setButtWrongListener(button4, wordList.get(massRan[2]).getTranslate());
                        break;
                    }
                    case 1: {
                        setButtListener(button2, wordList.get(randomWord).getTranslate());
                        setButtWrongListener(button1, wordList.get(massRan[0]).getTranslate());
                        setButtWrongListener(button3, wordList.get(massRan[1]).getTranslate());
                        setButtWrongListener(button4, wordList.get(massRan[2]).getTranslate());
                        break;
                    }
                    case 2: {
                        setButtListener(button3, wordList.get(randomWord).getTranslate());
                        setButtWrongListener(button2, wordList.get(massRan[0]).getTranslate());
                        setButtWrongListener(button1, wordList.get(massRan[1]).getTranslate());
                        setButtWrongListener(button4, wordList.get(massRan[2]).getTranslate());
                        break;
                    }
                    case 3: {
                        setButtListener(button4, wordList.get(randomWord).getTranslate());
                        setButtWrongListener(button2, wordList.get(massRan[0]).getTranslate());
                        setButtWrongListener(button3, wordList.get(massRan[1]).getTranslate());
                        setButtWrongListener(button1, wordList.get(massRan[2]).getTranslate());
                        break;
                    }
                }

            //} else Word.setText("Ваш словарь пуст или мало слов");
        }else {
            Word.setText("Конец");
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        }
    }

    public static void setButtListener(final Button button, String word){
        button.setText(word);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j++;
                schet++;
                polata.setText(String.valueOf(schet));
                //wordListUses.remove(randomWord);
                test();
            }
        });
    }

    public static void setButtWrongListener(final Button buttonWrong, String answerR){
        buttonWrong.setText(answerR);
        buttonWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j++;
                //wordListUses.remove(randomWord);
                test();
            }
        });
    }
    public void unitAll(){
        Word = findViewById(R.id.Word);
        polata = findViewById(R.id.schetnaiPolata);
        numbers = findViewById(R.id.Numbers);
        button1 = findViewById(R.id.butt1);
        button2 = findViewById(R.id.butt2);
        button3 = findViewById(R.id.butt3);
        button4 = findViewById(R.id.butt4);
        retest = findViewById(R.id.ReTest);
        if(isMain) wordList = MainActivity.getList();
        //wordListUses = wordList;
        count = wordList.size();
    }


}
