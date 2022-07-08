package net.unethicalite.backend.rest.exception.handler

import net.unethicalite.dto.exception.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.*
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.OPTIONS
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {
    private val logger = LoggerFactory.getLogger(ExceptionAdvice::class.java)

    @ExceptionHandler(NotFoundException::class)
    @RequestMapping(method= [OPTIONS], produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(value = NOT_FOUND)
    fun handleNotFoundException(throwable: Throwable) = ResponseEntity(ExceptionDto(throwable.message), NOT_FOUND)

    @ExceptionHandler(UnauthorizedException::class)
    @RequestMapping(method= [OPTIONS], produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(value = UNAUTHORIZED)
    fun handleUnauthorizedException(throwable: Throwable) = ResponseEntity(ExceptionDto(throwable.message), UNAUTHORIZED)

    @ExceptionHandler(AuthenticationException::class)
    @RequestMapping(method= [OPTIONS], produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(value = FORBIDDEN)
    fun handleAuthenticationException(throwable: Throwable) = ResponseEntity(ExceptionDto(throwable.message), FORBIDDEN)

    @ExceptionHandler(BadRequestException::class)
    @RequestMapping(method= [OPTIONS], produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(value = BAD_REQUEST)
    fun handleBadRequestException(throwable: Throwable) = ResponseEntity(ExceptionDto(throwable.message), BAD_REQUEST)

    @ExceptionHandler(RuntimeException::class)
    @RequestMapping(method= [OPTIONS], produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    fun handleRuntimeException(throwable: Throwable) = ResponseEntity(ExceptionDto("A server error occured."), INTERNAL_SERVER_ERROR).let {
        logger.error(throwable.message)
    }
}