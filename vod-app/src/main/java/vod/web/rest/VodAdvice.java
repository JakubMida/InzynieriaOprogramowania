package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(basePackages = "vod.web.rest")
@RequiredArgsConstructor
@Slf4j
public class VodAdvice {

    private final BookstoreValidator validator;
    private final BookstoreValidator bookstoreValidator;
    private final BookValidator bookValidator;

//    @InitBinder
//    void initBinder(WebDataBinder binder) {
////        if (binder.getTarget() != null && validator.supports(binder.getTarget().getClass())) {
////            binder.addValidators(validator);
////        }
//        binder.addValidators(validator);
//    }

    @InitBinder("bookstore")
    void initBinderForBookstore(WebDataBinder binder) {binder.addValidators(bookstoreValidator);}

    @InitBinder("bookDTO")
    void initBinderForBookDTO(WebDataBinder binder) {binder.addValidators(bookValidator);}


    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalAccessException(IllegalArgumentException e){
        log.error("illegal argument provided", e);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
    }
}
