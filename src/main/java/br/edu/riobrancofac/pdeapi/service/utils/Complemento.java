package br.edu.riobrancofac.pdeapi.service.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

public class Complemento {

    public static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccessControlAllowOrigin("*");
        httpHeaders.setAccessControlAllowCredentials(true);
        List<HttpMethod> httpMethods = new ArrayList<>();
        httpMethods.add(HttpMethod.GET);
        httpMethods.add(HttpMethod.POST);
        httpMethods.add(HttpMethod.DELETE);
        httpMethods.add(HttpMethod.PUT);
        httpMethods.add(HttpMethod.PATCH);
        httpHeaders.setAccessControlAllowMethods(httpMethods);
        List<String> strings = new ArrayList<>();
        strings.add("Content-Type");
        strings.add("Accept");
        strings.add("X-Requested-With");
        strings.add("Authorization");
        httpHeaders.setAccessControlAllowHeaders(strings);

        return httpHeaders;
    }
}
