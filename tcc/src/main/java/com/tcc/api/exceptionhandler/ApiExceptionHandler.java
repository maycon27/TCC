package com.tcc.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.hibernate.PersistentObjectException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
    private static final String CONSTANT_VALIDATION_LENGTH = "Length";
    private static final String CONSTANT_VALIDATION_PATTERN = "Pattern";
    private static final String CONSTANT_VALIDATION_MIN = "Min";
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro> erros = gerarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        String msgUsuario = " Recurso não encontrado";
        String msgDesvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handlerDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        String msgUsuario = String.format("Recurso não encontrado");
        String msgDesvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }



    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request){
        String msgUsuario = ex.getMessage();
        String msgDesvolvedor = ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> gerarListaDeErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<Erro>();
        bindingResult.getFieldErrors().forEach(FieldError -> {
            String msgUsuario = tratarMensagemDeErroParaUsuario(FieldError);
            String msgDesevolvedor = FieldError.toString();

            erros.add(new Erro(msgUsuario,msgDesevolvedor));
        });

        return erros;
    }

    private String tratarMensagemDeErroParaUsuario(FieldError fieldError) {
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)) {
            return fieldError.getDefaultMessage().concat(" é obrigatório.");
        }
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)) {
            return fieldError.getDefaultMessage().concat(" é obrigatório.");
        }
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)) {
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres", fieldError.getArguments()[2],
                    fieldError.getArguments()[1]));
        }
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_PATTERN)) {
            return fieldError.getDefaultMessage().concat(" Formato Inválido.");
        }
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_MIN)) {
            return fieldError.getDefaultMessage().concat(String.format("deve ser maior a igual a %s", fieldError.getArguments()[1]));
        }
        return fieldError.toString();
    }

}
