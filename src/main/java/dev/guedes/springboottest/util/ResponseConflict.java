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
public class ResponseConflict {

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
    private static ResponseConflict instance;

    private ResponseConflict() {

    }

    public static ResponseConflict getInstance() {
        if (ResponseConflict.instance == null) {
            ResponseConflict.instance = new ResponseConflict();
            ResponseConflict.instance.httpStatus = HttpStatus.CONFLICT;
            ResponseConflict.instance.status = HttpStatus.CONFLICT.value();
            ResponseConflict.instance.reason = HttpStatus.CONFLICT.getReasonPhrase();
        }
        return ResponseConflict.instance;
    }

}
