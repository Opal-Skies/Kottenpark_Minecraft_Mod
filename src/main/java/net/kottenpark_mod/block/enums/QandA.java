package net.kottenpark_mod.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum QandA implements StringIdentifiable {

    DEFAULT("vak","vraag",
            "antwoord1",
            "antwoord2",
            "antwoord3", 1),

    AK("ak","welk klimaat heeft Nederland? (volgens het Köppen-systeem)",
            "Cfa (warm zeeklimaat)",
            "Cfb (gematigd zeeklimaat)",
            "Cfc (koel zeeklimaat)", 2),

    BIO("bio","welk organel is de energiecentrale in een cel?",
            "Mitochondriën",
            "Chloroplasten",
            "Celkern", 1)


    ;

    private final String name;
    private final String question;
    private final String answerA;
    private final String answerB;
    private final String answerC;
    private final int correctIndex;

    private QandA(final String name, String question, String answerA, String answerB, String answerC, int correctIndex) {
        this.name = name;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.correctIndex = correctIndex;
    }


    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public String[] getQuestionArray() {
        return new String[]{this.question, this.answerA, this.answerB, this.answerC};
    }

    public int getCorrectIndex() {
        return this.correctIndex;
    }
}
