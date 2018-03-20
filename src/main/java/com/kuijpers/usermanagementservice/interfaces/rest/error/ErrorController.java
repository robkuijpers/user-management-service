package com.kuijpers.usermanagementservice.interfaces.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 *
 */
@Component
@RestControllerAdvice(basePackages = {"com.kuijpers.usermanagementservice.interfaces.rest"} )
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ErrorController {

    /**
     *
     * @param request
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(HttpServletRequest request, Exception ex) {

        return createErrorResponse(request, ex, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    /**
     *
     * @return
     */
    private ErrorResponse createErrorResponse(HttpServletRequest request, Exception ex, HttpStatus httpStatus) {

        ErrorResponse error = new ErrorResponse();

        error.setTimestamp(new Date().getTime());
        error.setError(ex.getClass().getSimpleName());
        error.setMessage(ex.getMessage());
        error.setStatus(httpStatus.value());
        error.setPath(request.getRequestURI());

        return error;

    }

}
