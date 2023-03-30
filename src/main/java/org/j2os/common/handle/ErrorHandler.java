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
        String errorCode = getErrorCodeByException(exception);
        String errorMessage = getErrorMessageByErrorCode(errorCode);
        final Map<String, String> errorMap = new HashMap<>();
        errorMap.put("code", errorCode);
        errorMap.put("message", errorMessage);
        log.error(exception.getClass().getName());
        return errorMap;
    }
    public String getErrorMessageByErrorCode(String errorCode) {
        return messageSource.getMessage("ERROR".concat(errorCode), null, Locale.forLanguageTag(language));
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
