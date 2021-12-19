package com.onlinestore.admin.brands;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Brand not found")
public class BrandNotFoundRestException extends Throwable {

}
