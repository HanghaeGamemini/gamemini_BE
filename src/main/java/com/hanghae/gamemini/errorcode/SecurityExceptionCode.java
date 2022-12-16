package com.hanghae.gamemini.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SecurityExceptionCode implements StatusCode {
     private final boolean success = false;
     private final String statusMsg;
     private final int statusCode;
}
