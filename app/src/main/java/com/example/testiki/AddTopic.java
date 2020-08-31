package com.example.testiki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddTopic extends AppCompatActivity {

        TextView UpText,DownText,addTopicView;
        EditText UpEdit,DownEdit;
        Button butt;
        Topic top;
        int j = 0;
        String topicName;
        int countWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_topic);
        UpEdit = findViewById(R.id.UpEdit);
        DownEdit = findViewById(R.id.DownEdit);
        butt = findViewById(R.id.TopicButt);
        UpText = findViewById(R.id.UpText);
        DownText = findViewById(R.id.DownText);
        addTopicView = findViewById(R.id.topicTextView);
        addListener();
    }

   private void addListener() {
        butt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTop();
                        MainActivity.getListTopic().add(top);
                    }
                }
        );
    }
    private void addListenerForWord() {
        butt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addWord();
                    }
                }
        );
    }



    public void addWord(){
        addTopicView.setText(String.valueOf(j+1+"/"+countWord));
        UpEdit.setText("");
        DownEdit.setText("");
        if(j == countWord - 1) {
            butt.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(MainActivity.Choose);
                        }
                    }
            );
        }
                Word word = new Word(String.valueOf(UpEdit.getText()), String.valueOf(DownEdit.getText()));
                top.getWordArrayList().add(word);
                int c = 0;
                for(Word aWord :MainActivity.getList()){
                    if(!aWord.getName().equals(word.getName()) && !aWord.getTranslate().equals(word.getTranslate()))
                        c++;
                }
                if(c==0)MainActivity.getList().add(word);
                j++;
            //}

    }

    public void addTop(){
            //if(top == null) {
                topicName = String.valueOf(UpEdit.getText());
                try {
                    countWord = Integer.parseInt(String.valueOf(DownEdit.getText()));
                    top = new Topic(topicName);
                    UpText.setText("Русский");
                    UpEdit.setText("");
                    DownEdit.setText("");
                    DownText.setText("Английский:");
                    addListenerForWord();
                    addWord();

                } catch (Exception e) {
                    Toast.makeText(AddTopic.this, "Ошибка ввода ", Toast.LENGTH_SHORT).show();
                    addTop();
                }
            //}else addWord();
    }
}





