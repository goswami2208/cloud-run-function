package com.example;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;

public class MyFunctionB implements HttpFunction {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        // Default to "World" if no name is provided
        String name = request.getFirstQueryParameter("name").orElse("World");

        BufferedWriter writer = response.getWriter();
        writer.write("Hello, " + name + "!");
    }
}