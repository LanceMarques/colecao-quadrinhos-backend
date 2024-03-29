package com.ihc.quadrinhosorientais.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class QuadrinhosExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    String mensagemUsr =
        messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
    String mensagemDev = ex.getCause().toString();

    List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<Erro> erros = criarListaDeErros(ex.getBindingResult());
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<Object> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex, WebRequest request) {
    String mensagemUsr = messageSource.getMessage("recurso.operacao-nao-permitida", null,
        LocaleContextHolder.getLocale());
    String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
    List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
      WebRequest request) {

    String mensagemUsr = messageSource.getMessage("recurso.operacao-nao-permitida", null,
        LocaleContextHolder.getLocale());
    String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
    List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

  }

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
      WebRequest request) {
    String mensagemUsr = messageSource.getMessage("recurso.dados-incompletos", null,
        LocaleContextHolder.getLocale());
    String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
    List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({NonUniqueResultException.class})
  public ResponseEntity<Object> handleNonUniqueResultException(NonUniqueResultException ex,
      WebRequest request) {
    String mensagemUsr = messageSource.getMessage("genero.cadastro-replicado", null,
        LocaleContextHolder.getLocale());
    String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
    List<Erro> erros = Arrays.asList(new Erro(mensagemUsr, mensagemDev));
    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  private List<Erro> criarListaDeErros(BindingResult bindingResult) {
    List<Erro> erros = new ArrayList<>();

    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      String mensagemUsr = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
      String mensagemDev = fieldError.toString();
      erros.add(new Erro(mensagemUsr, mensagemDev));
    }
    return erros;
  }

}
