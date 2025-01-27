package com.banquito.marca.aplicacion.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CBS-API", url = "${external.cbs.api.base-url}")
public interface  CbsClient {
    //@RequestMapping(method = RequestMethod.POST, value = "/v1/transacciones")
    //List<Post> getPosts();
}
