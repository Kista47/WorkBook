package com.example.testiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class AddWord extends AppCompatActivity {

    EditText wordRuss,wordEng;
    Button buttonAdd;
    private static BufferedWriter fWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        addListenerOnButton();
    }
    public void addListenerOnButton(){
        //pass = findViewById(R.id.editText);
        buttonAdd = findViewById(R.id.ADD);
        wordRuss = findViewById(R.id.RussWord);
        wordEng  = findViewById(R.id.EngWord);

        buttonAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!wordRuss.getText().toString().isEmpty() && !wordEng.getText().toString().isEmpty()) {
                            MainActivity.getList().add(new Word(wordRuss.getText().toString(), wordEng.getText().toString()));
                            saveFile();
                            startActivity(MainActivity.wordBook);

                            Toast.makeText(
                                    AddWord.this, "Слово добавлено в словарь", Toast.LENGTH_LONG
                            ).show();

                        }
                    }
                }
        );
    }
    public void saveFile(){
        try {
            fWriter = new BufferedWriter(new OutputStreamWriter(openFileOutput(MainActivity.FILE_NAME,MODE_PRIVATE)));
            for (Word word : MainActivity.getList()) {
                fWriter.write(word.getName() + ":" + word.getTranslate() + "\n");
            }
        }catch (IOException e){e.printStackTrace();}
        finally {
            try {
                if(fWriter != null)fWriter.close();
            }catch (Exception e){
                System.out.println("Ебанный рот этого казино");;}
        }
    }
}
