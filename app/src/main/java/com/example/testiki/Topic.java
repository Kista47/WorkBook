package com.example.testiki;

import java.util.ArrayList;

public class Topic {

    private String name;
    private ArrayList<Word> wordArrayList = new ArrayList<>();

    public Topic(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Word> getWordArrayList() {
        return wordArrayList;
    }

    public void setWordArrayList(ArrayList<Word> wordArrayList) {
        this.wordArrayList = wordArrayList;
    }

}
