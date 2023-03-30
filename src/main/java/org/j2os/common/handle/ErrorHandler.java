package org.j2os.common.handle;

import lombok.extern.slf4j.Slf4j;
import org.j2os.common.exception.RecordNotExist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/*
    Bahador, Amirsam
 */
@Component
@Slf4j
public class ErrorHandler {
    @Value("${org.j2os.language}")
    private String language;
    private final MessageSource messageSource;

    public ErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Map<String, String> getError(Exception exception) {
        final Map<String, String> error = new HashMap<>();
        error.put("code", getErrorCodeByException(exception));
        error.put("message", messageSource.getMessage("ERROR".concat(getErrorCodeByException(exception)), null, Locale.forLanguageTag(language)));
        log.error(exception.getClass().toString().concat(exception.getMessage()));
        return error;
    }

    private String getErrorCodeByException(Exception exception) {
        if (exception instanceof RecordNotExist)
            return "1001";
        else if (exception instanceof ArithmeticException)
            return "1002";
        else if (exception instanceof ClassNotFoundException)
            return "1003";
        else
            return "2000";
    }
}
