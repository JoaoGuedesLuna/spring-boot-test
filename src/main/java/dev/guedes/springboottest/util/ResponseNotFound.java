package dev.guedes.springboottest.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author João Guedes
 */
@ToString
public class ResponseNotFound {

    @JsonIgnore
    @Getter
    private HttpStatus httpStatus;
    @Getter
    private int status;
    @Getter
    private String reason;
    @Getter
    @Setter
    private String message;
    private static ResponseNotFound instance;

    private ResponseNotFound() {

    }

    public static ResponseNotFound getInstance() {
        if (ResponseNotFound.instance == null) {
            ResponseNotFound.instance = new ResponseNotFound();
            ResponseNotFound.instance.httpStatus = HttpStatus.NOT_FOUND;
            ResponseNotFound.instance.status = HttpStatus.NOT_FOUND.value();
            ResponseNotFound.instance.reason = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ResponseNotFound.instance;
    }

}
