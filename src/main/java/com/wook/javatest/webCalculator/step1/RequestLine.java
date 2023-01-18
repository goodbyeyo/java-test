package com.wook.javatest.webCalculator.step1;

import lombok.EqualsAndHashCode;

/**
 * HttpRequest
 *  - RequestLine (GET localhost:8080/calculate?operand1=7&operator=*&operand2=5 HTTP/1.1)
 *      - HttpMethod
 *      - path
 *      - queryString
 *  - Header
 *  - Body
 */
@EqualsAndHashCode
public class RequestLine {

    private final String method;    // GET
    private final String urlPath;   // /calculate
    private QueryStrings queryStrings;     // operand1=7&operator=*&operand2=5

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(queryString);
    }

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];

        String[] urlPathTokens = tokens[1].split("\\?");
        this.urlPath = urlPathTokens[0];

        if (urlPathTokens.length == 2) {
            this.queryStrings = new QueryStrings(urlPathTokens[1]);
        }
    }

    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }

    public boolean isMatchPath(String requestPath) {
        return urlPath.equals(requestPath);
    }

    public QueryStrings getQueryStrings() {
        return this.queryStrings;
    }
}
