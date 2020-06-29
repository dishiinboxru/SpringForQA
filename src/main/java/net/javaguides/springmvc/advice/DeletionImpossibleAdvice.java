package net.javaguides.springmvc.advice;

import net.javaguides.springmvc.exception.DeletionImpossibleException;
import net.javaguides.springmvc.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DeletionImpossibleAdvice {

    @ResponseBody
    @ExceptionHandler(DeletionImpossibleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String deletionImpossibleHandler(DeletionImpossibleAdvice ex) {
        return String.format("Can't delete due to absense of such name in the phone book'");
    }
}
