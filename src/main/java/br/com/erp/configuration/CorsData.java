package br.com.erp.configuration;

import javax.servlet.http.HttpServletRequest;

class CorsData {

    private String origin;
    private String requestMethods;
    private String requestHeaders;

    private static final String ORIGIN = "Origin";
    private static final String AC_REQUEST_METHOD = "Access-Control-Request-Method";
    private static final String AC_REQUEST_HEADERS = "Access-Control-Request-Headers";

    CorsData(HttpServletRequest request) {
        this.origin = request.getHeader(ORIGIN);
        this.requestMethods= request.getHeader(AC_REQUEST_METHOD);
        this.requestHeaders = request.getHeader(AC_REQUEST_HEADERS);
    }

    public boolean hasOrigin(){
        return origin != null && !origin.isEmpty();
    }

    public boolean hasRequestMethods(){
        return requestMethods != null && !requestMethods.isEmpty();
    }

    public boolean hasRequestHeaders(){
        return requestHeaders != null && !requestHeaders.isEmpty();
    }

    public boolean isPreflighted() {
        return hasOrigin() && hasRequestHeaders() && hasRequestMethods();
    }

    public boolean isSimple() {
        return hasOrigin() && !hasRequestHeaders();
    }
}
