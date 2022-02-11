//package br.com.erp.configuration;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@NoArgsConstructor
//public class CorsInterceptor extends HandlerInterceptorAdapter {
//
//    private static final String ORIGIN = "Origin";
//    private static final String AC_REQUEST_METHOD = "Access-Control-Request-Method";
//    private static final String AC_REQUEST_HEADERS = "Access-Control-Request-Headers";
//
//    private static final String AC_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
//    private static final String AC_ALLOW_METHODS = "Access-Control-Allow-Methods";
//
//    private CorsData corsData;
//    //http://localhost:3000, https://erp-front-evert90.vercel.app,
//    private String origin = "https://erp-front-three.vercel.app";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        this.corsData = new CorsData(request);
//
//        if(this.corsData.isPreflighted()) {
//            response.setHeader("Access-Control-Allow-Headers", "authorization");
//            response.setHeader(AC_ALLOW_ORIGIN, origin);
//            response.setHeader(AC_ALLOW_METHODS, "GET,POST,PUT,DELETE, OPTIONS");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.addHeader("Access-Control-Allow-Private-Network", "true");
//
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        if(this.corsData.isSimple()) {
//            response.setHeader(AC_ALLOW_ORIGIN, origin);
//        }
//    }
//
//    @Getter
//    class CorsData {
//
//        private String origin;
//        private String requestMethods;
//        private String requestHeaders;
//
//        CorsData(HttpServletRequest request) {
//            this.origin = request.getHeader(ORIGIN);
//            this.requestMethods= request.getHeader(AC_REQUEST_METHOD);
//            this.requestHeaders = request.getHeader(AC_REQUEST_HEADERS);
//        }
//
//        public boolean hasOrigin(){
//            return origin != null && !origin.isEmpty();
//        }
//
//        public boolean hasRequestMethods(){
//            return requestMethods != null && !requestMethods.isEmpty();
//        }
//
//        public boolean hasRequestHeaders(){
//            return requestHeaders != null && !requestHeaders.isEmpty();
//        }
//
//        public boolean isPreflighted() {
//            return hasOrigin() && hasRequestHeaders() && hasRequestMethods();
//        }
//
//        public boolean isSimple() {
//            return hasOrigin() && !hasRequestHeaders();
//        }
//    }
//}