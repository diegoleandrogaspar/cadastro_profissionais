package br.com.diegoleandro.api.exceptionhandler;


import br.com.diegoleandro.api.exception.BusinessException;
import br.com.diegoleandro.api.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o " +
            "problema persistir, entre em contato com o administrador do sistema";


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Erro 4");
      Throwable rootCause = ExceptionUtils.getRootCause(ex);

      if (rootCause instanceof InvalidFormatException) {
          return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
      }
      else if(rootCause instanceof PropertyBindingException) {
          return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
      }

      ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
      String detail = "O Corpo da requisição está inválido. Verifique erro de sintaxe.";
      Problem problem = createProblemBuilder(status, problemType, detail).build();

      return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    /*
    private ResponseEntity<Object> handleMethodArgumentTypeMistatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format("O Parâmetro de URL '%s' recebeu o valor '%s',"
                        + " que é um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }
   */

    /*
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

    }
 */

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

       HttpStatus status = HttpStatus.NOT_FOUND;
       ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
       String detail = ex.getMessage();

       Problem problem = createProblemBuilder(status, problemType, detail).build();

       return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }


    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Erro 3");
        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A Propriedade '%s' não existe. "
                + "Corrija  ou remova essa propriedade e tente novamente.", path);

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Erro 2");
        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        Object invalidValue = ex.getValue();

        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detail = String.format("O valor '%s' para o campo '%s' é inválido. Os valores aceitos são: DESENVOLVEDOR, DESIGNER, SUPORTE, TESTER", invalidValue, path);

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Erro 1");
        if (body == null) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now().withNano(0))
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now().withNano(0))
                    .title((String) body)
                    .status(status.value())
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

        return Problem.builder()
                .timestamp(OffsetDateTime.now().withNano(0))
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
}
