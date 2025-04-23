package com.example.capstone3.Advise;

import com.example.capstone3.Api.ApiException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ControllerAdvise {


    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException apiException){
        String message=apiException.getMessage();
        return ResponseEntity.status(400).body(apiException.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        String message=methodArgumentNotValidException.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value= HttpMessageNotReadableException.class)
    public ResponseEntity httpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException){
        String message=httpMessageNotReadableException.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = JpaSystemException.class )
    public ResponseEntity jpaSystemException(JpaSystemException jpaSystemException){
        String message=jpaSystemException.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        String message = methodArgumentTypeMismatchException.getMessage();
        return ResponseEntity.status(400).body((message));
    }
    @ExceptionHandler(value = ObjectOptimisticLockingFailureException.class )
    public ResponseEntity objectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException objectOptimisticLockingFailureException){
        String message=objectOptimisticLockingFailureException.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity  HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        String message = httpRequestMethodNotSupportedException.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException d){
        String msg=d.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class )
    public ResponseEntity InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e){
        String message=e.getMessage();
        return ResponseEntity.status(200).body((message));
    }

    @ExceptionHandler(value = IllegalArgumentException.class )
    public ResponseEntity illegalArgumentException(IllegalArgumentException illegalArgumentException){
        String message=illegalArgumentException.getMessage();
        return ResponseEntity.status(400).body(message);}

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationException (ConstraintViolationException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body((msg));
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        String message=e.getMessage();
        return ResponseEntity.status(400).body((message));
    }
}
