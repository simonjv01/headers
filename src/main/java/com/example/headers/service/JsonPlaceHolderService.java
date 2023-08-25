package com.example.headers.service;

import com.example.headers.model.Post;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Map;

public interface JsonPlaceHolderService {

    @GetExchange("/posts")
    List<Post> findAll(@RequestHeader Map<String, String> headers);
}
