package dsproject;

import java.util.Random;

/**
 *
 * @author Khandine, Earl, Jared
 * 
 *  Multiple Choice
 *  Contains a question and 4 possible answers
 *  as well as a correct answer.
 * 
 *  Also contains a method to shuffle answers around.
 * 
 */
public class MultipleChoice {
    
    private String question, answer;
    private String[] options = new String[4];    
    
    
    /*
        Constructor
    */
    public MultipleChoice(String question, 
            String a, String b, String c, String d, String answer) {
        this.question = question;
        options[0] = a;
        options[1] = b;
        options[2] = c;
        options[3] = d;
        this.answer = answer;
    }

    /*
        Getters
    */
    public String getQuestion() { 
        return question; 
    }

    public String getA() {
        return options[0];
    }

    public String getB() {
        return options[1];
    }

    public String getC() {
        return options[2];
    }

    public String getD() {
        return options[3];
    }

    public String getAnswer() {
        return answer;
    }

    /*
        toString, this returns a string with the question and 
        all answers. It uses line seperator to for newlines.
    */
    @Override
    public String toString() {
        shuffleAnswers();
        return question + System.getProperty("line.separator")
                + "A. " + options[0] + System.getProperty("line.separator")
                + "B. " + options[1] + System.getProperty("line.separator")
                + "C. " + options[2] + System.getProperty("line.separator")
                + "D. " + options[3] + System.getProperty("line.separator")
                + "Answer: " + answer + System.getProperty("line.separator");
    }
    
    
    /*
        quick shuffle the options using Fisher-Yates Algorithm
        this shuffle produces an unbiased permutation
        https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
    */
    public void shuffleAnswers() {
        Random rand = new Random();
        for (int i=3; i>=1; i--) {
            int randNum = rand.nextInt((i - 0) + 1) + 0;
            String temp = options[i];
            options[i] = options[randNum];
            options[randNum] = temp;
        }
    }
    
}
