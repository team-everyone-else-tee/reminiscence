package com.reminiscence.reminiscence.journal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Account not authorized to access this page")
public class UnauthorizedAccountException extends RuntimeException {
}
