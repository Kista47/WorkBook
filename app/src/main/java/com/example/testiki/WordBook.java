package com.example.testiki;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordBook extends AppCompatActivity{

    private static int numberOfWord = 1;
    private static int maxNumberOfWord = 12;
    /////////////////////////////////////
    private static int pageNumber = 0;
    private static int maxPage;
    /////////////////////////////////////
    private static int searchMaxNumber = 0;
    private static int searchNumber = 0;
    ////////////////////////////////////
    private static List<Word> wordList = MainActivity.getList();
    private static final int colWord = 12;
    /////////////////////////////////////
    private static String searchWord = "";
    private boolean isRuss = true;
    /////////////////////////////////////
    private static TextView temp;
    private static TextView temp1;


    public static void setNumberOfWord(int numberOfWord) {
        WordBook.numberOfWord = numberOfWord;
    }

    public static void setMaxNumberOfWord(int maxNumberOfWord) {
        WordBook.maxNumberOfWord = maxNumberOfWord;
    }

    LinearLayout linear1,linear2;
    Button nextButt,prevButt,plus,swap;
    Button LeftButton,RightButton;
    SearchView search;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_book);
        addListenerOnButton(this);
        sortRus();
        /*
        if(numberOfWord + 12 * pageNumber <= wordList.size()){
            maxNumberOfWord = numberOfWord + 11 * pageNumber;
        }else maxNumberOfWord = wordList.size();*/
        maxPage = (int)Math.ceil((double)wordList.size()/12);

        linear1 = findViewById(R.id.Liner);
        linear2 = findViewById(R.id.LinerSecond);
        plus = findViewById(R.id.Pizda);
        search = findViewById(R.id.Search);


        this.showBook(linear1,linear2);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchWord = query;
                    search();
                    maxNumberOfWord = checkMaxNext();
                    showBook(linear1,linear2);
                    search.clearFocus();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchWord = newText;
                    search();
                    showBook(linear1,linear2);
                    return false;
                }
            });

    }
    public void addListenerOnButton(final WordBook wordBook){
        plus = findViewById(R.id.Pizda);

        plus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(MainActivity.addWord);
                    }
                }
        );

        nextButt = findViewById(R.id.NEXT);
        nextButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pageNumber + 1 != maxPage) {
                    if (searchWord.equals("")) {
                        numberOfWord += colWord;
                        maxNumberOfWord = checkMaxNext();
                        pageNumber++;
                        showBook(linear1, linear2);
                    }
                }
            }
        });

        LeftButton = findViewById(R.id.LeftLiner);
        RightButton = findViewById(R.id.RightLiner);

        LeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isRuss) {
                    sortEng();
                    isRuss = false;
                    showBook(linear1,linear2);

                    if(LeftButton.getText().equals("Русский")){
                        RightButton.setText("Русский");
                        LeftButton.setText("Английский");
                    }
                }else {
                    sortRus();
                    isRuss = true;
                    showBook(linear1,linear2);
                    if(LeftButton.getText().equals("Английский")){
                        RightButton.setText("Английский");
                        LeftButton.setText("Русский");
                    }
                }
            }
        });

        RightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isRuss) {
                    sortEng();
                    isRuss = false;
                    showBook(linear1,linear2);
                    if(RightButton.getText().equals("Английский")){
                        RightButton.setText("Русский");
                        LeftButton.setText("Английский");
                    }

                }else {
                    sortRus();
                    isRuss = true;
                    showBook(linear1,linear2);

                    if(RightButton.getText().equals("Русский")){
                        RightButton.setText("Английский");
                        LeftButton.setText("Русский");
                    }
                }
            }
        });


        prevButt = findViewById(R.id.PREV);
        prevButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pageNumber != 0) {
                    if (searchWord.equals("")) {
                        maxNumberOfWord = checkMaxPrev();
                        pageNumber--;
                        numberOfWord -= colWord;

                        showBook(linear1, linear2);
                    }
                }
            }
        });
    }

    public void showBook(LinearLayout linear1,LinearLayout linear2){


        int c = 0;

        TextView textView = findViewById(R.id.Pages);
        textView.setText(String.valueOf(numberOfWord + "/"+ maxNumberOfWord + "/" + wordList.size()));


            for (int i = 1; i <= colWord; i++) {
                try {
                    if (wordList.get(i - 1 + colWord * pageNumber) != null) {

                        showHelp(i);

                        if (c % 2 == 0) {
                            temp.setBackgroundColor(Color.DKGRAY);
                            temp.setTextColor(Color.WHITE);
                            temp1.setBackgroundColor(Color.DKGRAY);
                            temp1.setTextColor(Color.WHITE);
                            c = 1;
                        } else c = 0;
                    } else {
                        TextView temp = findViewById(linear1.getChildAt(i - 1).getId());
                        temp.setText("");
                        TextView temp2 = findViewById(linear2.getChildAt(i - 1).getId());
                        temp2.setText("");
                    }
                } catch (Exception e) {
                    TextView temp = findViewById(linear1.getChildAt(i - 1).getId());
                    temp.setText("");
                    TextView temp2 = findViewById(linear2.getChildAt(i - 1).getId());
                    temp2.setText("");
                }
            }
            searchWord = "";
    }
    public static int checkMaxNext(){
        if(wordList.size() - numberOfWord <= 12){
            return maxNumberOfWord = wordList.size();
        }
        maxPage = (int)Math.ceil((double)wordList.size()/12);
        return maxNumberOfWord + colWord;
    }
    public static int checkMaxPrev(){
        if(wordList.size() - numberOfWord <= 12){
            return maxNumberOfWord = colWord * (pageNumber);
        }
        maxPage = (int)Math.ceil((double)wordList.size()/12);
        return maxNumberOfWord - colWord;
    }


    public static void search(){
        wordList = new ArrayList<>();

        for(Word word :MainActivity.getList()){
            if(word.getName().toLowerCase().contains(searchWord.toLowerCase()) || word.getTranslate().toLowerCase().contains(searchWord.toLowerCase())){
                wordList.add(word);
            }
        }
        pageNumber = 0;
        maxPage = (int)Math.ceil((double)wordList.size()/12);
        checkMaxNext();
    }

    public static void sortRus(){
        Comparator<Word> wordComparator = new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Collections.sort(wordList,wordComparator);
    }
    public static void sortEng(){
        Comparator<Word> wordComparator = new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getTranslate().compareTo(o2.getTranslate());
            }
        };
        Collections.sort(wordList,wordComparator);
    }

    public void showHelp(int i) {

            if (isRuss) {
                temp = findViewById(linear1.getChildAt(i - 1).getId());
                temp.setText(wordList.get(i - 1 + colWord * pageNumber).getName());

                temp1 = findViewById(linear2.getChildAt(i - 1).getId());
                temp1.setText(wordList.get(i - 1 + colWord * pageNumber).getTranslate());

            } else {
                temp = findViewById(linear1.getChildAt(i - 1).getId());
                temp.setText(wordList.get(i - 1 + colWord * pageNumber).getTranslate());

                temp1 = findViewById(linear2.getChildAt(i - 1).getId());
                temp1.setText(wordList.get(i - 1 + colWord * pageNumber).getName());
                temp1.setGravity(View.FOCUS_RIGHT);
            }
    }
}