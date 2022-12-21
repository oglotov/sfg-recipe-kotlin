package ua.wwind.glotov.recipe.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView
import ua.wwind.glotov.recipe.exceptions.NotFoundException

@ControllerAdvice
class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: Exception): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "404error"
        modelAndView.addObject("exception", ex)
        return modelAndView
    }

}