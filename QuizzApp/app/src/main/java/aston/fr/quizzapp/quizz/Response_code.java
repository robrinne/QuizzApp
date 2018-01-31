package aston.fr.quizzapp.quizz;

import java.util.List;

/**
 * Created by tanguye on 31/01/2018.
 */

public class Response_code {
    private int response_code;
    private List<Results> results;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}
