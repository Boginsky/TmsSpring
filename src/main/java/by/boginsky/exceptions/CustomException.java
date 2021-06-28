package by.boginsky.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "no such data exists")
public class CustomException extends DataAccessException {
    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
