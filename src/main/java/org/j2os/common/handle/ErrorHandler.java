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
    public Map<String, String> getError(Exception e) {
        log.error(e.getMessage());
        final Map<String, String> error = new HashMap<>();
        if (e instanceof RecordNotExist) {
            error.put("code", "1001");
            error.put("message", messageSource.getMessage("ERROR1001", null, Locale.forLanguageTag(language)));
        } else if (e instanceof ArithmeticException) {
            error.put("code", "1002");
            error.put("message", messageSource.getMessage("ERROR1002", null, Locale.forLanguageTag(language)));
        } else if (e instanceof ClassNotFoundException) {
            error.put("code", "1003");
            error.put("message", messageSource.getMessage("ERROR1003", null, Locale.forLanguageTag(language)));
        } else {
            error.put("code", "2000");
            error.put("message", messageSource.getMessage("ERROR2000", new Object[]{e.getMessage()}, Locale.forLanguageTag(language)));
        }
        return error;
    }
}
