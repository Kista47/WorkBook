package com.example.testiki;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText pass;
    private Button btnFastTest,btnWordBook,btnAddWord,btnChoose;

    public static ArrayList<Word> getList() {
        return list;
    }

    private static ArrayList<Word> list = new ArrayList<>();
    private static ArrayList<Topic> listTopic = new ArrayList<>();

    public static ArrayList<Topic> getListTopic() {
        return listTopic;
    }

    private static BufferedReader fRead;
    private static BufferedWriter fWriter;

    //private static String FILE_NAME = Environment.getExternalStorageState();
    public  static File sdPath = Environment.getExternalStorageDirectory();

    public static Intent addWord =  new Intent(".AddWord");
    public static Intent wordBook =  new Intent(".WordBook");
    public static Intent Choose =  new Intent(".Choose");

    public final static String FILE_NAME = "content.txt";
    public final static String TOPIC_FILE_NAME = "Topics.txt";

    static {
        /*
        list.add(new Word("Пицца","Pizza"));
        list.add(new Word("Кот","Cat"));
        list.add(new Word("Круг","Circle"));
        list.add(new Word("Поле","Field"));
        list.add(new Word("Собака","Dog"));
        list.add(new Word("Квадрат","Square"));
        list.add(new Word("Мышь","Mouse"));
        list.add(new Word("Добавлять","Add"));
        list.add(new Word("Брать","Get"));
        list.add(new Word("Покупать","Buy"));
        list.add(new Word("Дрочить","Fap"));
        list.add(new Word("Жаба","Frog"));
        list.add(new Word("Мы","We"));
        list.add(new Word("Они","They"));
        list.add(new Word("Делать","Make"));
        */
//        Topic topic = new Topic("Дни недели");
//        topic.getWordArrayList().add(new Word("Понедельник","Monday"));
//        topic.getWordArrayList().add(new Word("Вторник","Tuesday"));
//        topic.getWordArrayList().add(new Word("Среда","Wednesday"));
//        topic.getWordArrayList().add(new Word("Четверг","Thursday"));
//        topic.getWordArrayList().add(new Word("Пятница","Friday"));
//        topic.getWordArrayList().add(new Word("Суббота","Saturday"));
//        topic.getWordArrayList().add(new Word("Воскресенье","Sunday"));
//        listTopic.add(topic);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
        //saveTopicFile();
        readTopicFile();
        readFile();
    }
    public void addListenerOnButton(){
        btnFastTest = findViewById(R.id.FastTest);
        btnWordBook = findViewById(R.id.wordbook);
        btnAddWord = findViewById(R.id.addButt);
        btnChoose = findViewById(R.id.Choose);

        btnFastTest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityFastTestik.isMain = true;
                        Intent intent = new Intent(".ActivityFastTestik");
                        startActivity(intent);
                    }
                }
        );
        btnChoose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Choose);
                        startActivity(intent);
                    }
                }
        );
        btnWordBook.setOnClickListener(
                new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         //readFile();
                         Intent intent = new Intent(".WordBook");
                         startActivity(intent);
                     }
                }
        );
        btnAddWord.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addWord.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(addWord);
                    }
                }
        );
    }

    public void readFile() {
        try {
            fRead = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));
            String temp = "";
            while ((temp = fRead.readLine()) != null) {
                getList().add(new Word(temp.split(":")[0], temp.split(":")[1]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(fRead != null)fRead.close();
            }catch (IOException e){e.printStackTrace();}
        }
    }

    public void saveTopicFile(){
        try {
            fWriter = new BufferedWriter(new OutputStreamWriter(openFileOutput(MainActivity.TOPIC_FILE_NAME,MODE_PRIVATE)));
            for (Topic topic : MainActivity.getListTopic()) {
                fWriter.write(topic.getName() + "#" + topic.getWordArrayList().size() + "\n");
                for(Word word: topic.getWordArrayList()) {
                    fWriter.write(word.getName() + ":" + word.getTranslate() + "\n");
                }
            }
        }catch (IOException e){e.printStackTrace();}
        finally {
            try {
                if(fWriter != null)
                    fWriter.close();
            }catch (Exception e){
                System.out.println("Ебанный рот этого казино");;}
        }
    }

    public void readTopicFile() {
        try {
            fRead = new BufferedReader(new InputStreamReader(openFileInput(TOPIC_FILE_NAME)));
            String temp = "";
            while ((temp = fRead.readLine()) != null) {
                String name = temp.split("#")[0];
                int count = Integer.parseInt(temp.split("#")[1]);
                Topic topic = new Topic(name);
                for(int i = 0; i < count; i++) {
                    temp = fRead.readLine();
                    topic.getWordArrayList().add(new Word(temp.split(":")[0], temp.split(":")[1]));
                }
                getListTopic().add(topic);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(fRead != null)fRead.close();
            }catch (IOException e){e.printStackTrace();}
        }
    }



}