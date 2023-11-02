package com.example.tomcatchunkedbugdemo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class HeaderController {

    @RequestMapping("/unset-resp-header")
    public void unsetHeader(HttpServletResponse response) throws IOException {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write("ok".getBytes(StandardCharsets.UTF_8));
        }
        response.flushBuffer();
    }


    @RequestMapping("/set-resp-header")
    public void setHeader(HttpServletResponse response) throws IOException {
        response.setHeader("Transfer-encoding", "chunked");
        response.setHeader("Connection", "close");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write("ok".getBytes(StandardCharsets.UTF_8));
        }
        response.flushBuffer();
    }

}
