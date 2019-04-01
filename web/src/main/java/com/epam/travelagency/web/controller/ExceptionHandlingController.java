package com.epam.travelagency.web.controller;

import com.epam.travelagency.web.exception.BadRequestException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ExceptionHandlingController {

    private org.slf4j.Logger LOG = LoggerFactory.getLogger(ExceptionHandlingController.class);


    private static final String DEFAULT_ERROR_VIEW = "error/error";

    private static final String ERROR_VIEW_FOR_404 = "error/404";

    private static final String ERROR_VIEW_FOR_403 = "error/403";

    private static final String ERROR_VIEW_FOR_400 = "error/400";


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ModelAndView defaultErrorHandler() {
        return new ModelAndView(ERROR_VIEW_FOR_404);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Throwable.class)
    public ModelAndView internalServerError(Throwable e){
        var modelAndView = new ModelAndView();
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        modelAndView.addObject("errorMessage", e.getMessage());
        LOG.error("WHAT THE FUCK", e);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public ModelAndView badRequest(BadRequestException e){
        var modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR_VIEW_FOR_400);
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView accessDenied(){
        var modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR_VIEW_FOR_403);
        //modelAndView.addObject("errorMessage", )
        return modelAndView;
    }
}
