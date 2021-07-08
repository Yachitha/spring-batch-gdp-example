package com.assesment.rootcodelabs.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage<T> {
    private T data;
    private int statusCode;
    private String message;
}
