package br.pucrs.totem.middlewares.abstractions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
