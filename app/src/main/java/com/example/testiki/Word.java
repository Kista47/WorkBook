package com.example.testiki;

public class Word {
    private String name,Translate;

    public String getName() {
        return name;
    }

    public String getTranslate() {
        return Translate;
    }

    /*
    enum Ass{
        VERB,
        NOUN,
        ADJECTIVE
    }*/

    public Word(String name, String translate) {
        this.name = name;
        Translate = translate;
    }
}
