package com.example.testiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Choose extends AppCompatActivity {

    LinearLayout linearLayout;
    Button addTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose2);
        linearLayout = findViewById(R.id.Topics);
        addListener();
        showTopics();


    }

    private void showTopics() {
        for(Topic topic: MainActivity.getListTopic()){
            Button butTopic = new Button(this);
            butTopic.setText(topic.getName());
            addListenerTopic(butTopic,topic);
            linearLayout.addView(butTopic);
        }
    }

    private void addListener() {
        addTopic = findViewById(R.id.ADDTOPIC);
        addTopic.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(".AddTopic"));
                    }
                }
        );
    }
    private void addListenerTopic(Button button, final Topic topic) {
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityFastTestik.isMain = false;
                        ActivityFastTestik.wordList = topic.getWordArrayList();
                        startActivity(new Intent(".ActivityFastTestik"));
                    }
                }
        );
    }

}
