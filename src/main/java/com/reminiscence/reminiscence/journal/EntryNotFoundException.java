package com.reminiscence.reminiscence.journal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entry not Found")
public class EntryNotFoundException extends RuntimeException {
}
