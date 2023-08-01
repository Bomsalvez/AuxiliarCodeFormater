package dev.senzalla.auxiliarcodeformater.service;

import dev.senzalla.auxiliarcodeformater.model.Directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static dev.senzalla.auxiliarcodeformater.model.Directory.EXTENSION;

class GeneratedResourceExceptionHandler {
    public GeneratedResourceExceptionHandler(Path pathException) throws IOException {
        Path newPatch = Path.of(pathException + "\\" + "ResourceExceptionHandler" + EXTENSION);
        if (!Files.exists(newPatch)) {
            var listArgs = List.of(
                    String.format("package %s;", Directory.getPathRoot(pathException.toFile())),
                    "\n\n",
                    "import lombok.AllArgsConstructor;",
                    "import org.springframework.context.MessageSource;",
                    "import org.springframework.context.i18n.LocaleContextHolder;",
                    "\n",
                    "import org.springframework.http.HttpStatus;",
                    "\n",
                    "import org.springframework.validation.FieldError;",
                    "\n",
                    "import org.springframework.web.bind.MethodArgumentNotValidException;",
                    "\n",
                    "import org.springframework.web.bind.MissingServletRequestParameterException;",
                    "\n",
                    "import org.springframework.web.bind.annotation.ExceptionHandler;",
                    "\n",
                    "import org.springframework.web.bind.annotation.ResponseStatus;",
                    "\n",
                    "import org.springframework.web.bind.annotation.RestControllerAdvice;",
                    "\n",
                    "import java.util.ArrayList;",
                    "\n",
                    "import java.util.List;",
                    "\n\n",
                    "@RestControllerAdvice",
                    "\n",
                    "@AllArgsConstructor",
                    "\n",
                    "public class ResourceExceptionHandler {",
                    "\n",
                    "private final MessageSource messageSource;",
                    "\n\n",
                    "@ResponseStatus(HttpStatus.BAD_REQUEST)",
                    "\n",
                    "@ExceptionHandler(MissingServletRequestParameterException.class)",
                    "\n",
                    "public Error handle(MissingServletRequestParameterException ex) {",
                    "\n",
                    "return new Error(ex.getMessage());",
                    "\n",
                    "}",
                    "\n\n",
                    "@ResponseStatus(HttpStatus.BAD_REQUEST)",
                    "\n",
                    "@ExceptionHandler(MethodArgumentNotValidException.class)",
                    "\n",
                    "public List<Error> exceptionHandler(MethodArgumentNotValidException exception) {",
                    "\n",
                    "List<Error> errors = new ArrayList<>();",
                    "\n",
                    "List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();",
                    "\n",
                    "fieldErrors.forEach(fieldError -> {",
                    "\n",
                    "String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());",
                    "\n",
                    "Error error = new Error(message, fieldError.getField());",
                    "\n",
                    "errors.add(error);",
                    "\n",
                    " });",
                    "\n",
                    "return errors;",
                    "\n",
                    "}",
                    "\n",
                    " }"
            );
            new CreateFile(newPatch, listArgs);
        }
    }
}
