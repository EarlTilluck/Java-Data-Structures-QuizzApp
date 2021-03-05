package dsproject;


 /**
 *
 * @author Khandine, Earl, Jared
 * 
 *  True False Question
 *  Contains a question string and an answer string which is supposed to
 *  be either true or false
 * 
 */
public class TrueFalse {

    private String question, answer;
    
    /*
        constructor
    */
    public TrueFalse(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /*
        Getters are here for testing, they are not used in the app.
    */
    public String getQuestion() {
        return question; 
    }
    
    public String getAnswer() { 
        return answer; 
    }

    /*
        toString, this returns a string with the question and 
        the answer. It uses line seperator to for newlines.
    */
    @Override
    public String toString() {
        return question + System.getProperty("line.separator") + 
                "Answer: " + answer + System.getProperty("line.separator");
    }
    
}// end class
