package aston.fr.quizzapp.quizz;

/**
 * Created by tanguye on 31/01/2018.
 */

public class Results {



    private String category;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(String[] incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}

