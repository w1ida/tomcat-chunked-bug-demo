tomcat version: 10.x

```shell
./gradlew bootRun
curl localhost:8080/unset-resp-header
# response body: ok

curl localhost:8080/set-resp-header
# error : curl: (56) Illegal or missing hexadecimal sequence in chunked-encoding
```


```java
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
        // 
        response.setHeader("Transfer-encoding", "chunked");
        response.setHeader("Connection", "close");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write("ok".getBytes(StandardCharsets.UTF_8));
        }
        response.flushBuffer();
    }

}

```