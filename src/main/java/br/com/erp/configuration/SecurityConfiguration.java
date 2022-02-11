package br.com.erp.configuration;


import br.com.erp.service.security.UserDetailsServiceImpl;
import br.com.erp.service.security.jwt.AuthEntryPointJwt;
import br.com.erp.service.security.jwt.AuthTokenFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.*;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsCustomFilter corsCustomFilter() {
        return new CorsCustomFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsCustomFilter(), SessionManagementFilter.class)
                .cors().and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/users/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }



    //    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        // Configure your Cors COnfig
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000",
                "https://erp-front-evert90.vercel.app",
                "https://erp-front-three.vercel.app",
                "https://erp-front-npvnnu0y1-evert90.vercel.app")
        );
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // Configure your Cors COnfig
        source.registerCorsConfiguration("/**", configuration);
        var teste = new CorsFilter2(source);
        teste.setCorsProcessor(new AgoraVai());
        return teste;
    }

    @Bean
    public CorsProcessor corsProcessor() {
        return new AgoraVai();
    }

    public class CorsFilter2 extends CorsFilter {

        private final CorsConfigurationSource configSource;

        private CorsProcessor processor = new DefaultCorsProcessor();


        /**
         * Constructor accepting a {@link CorsConfigurationSource} used by the filter
         * to find the {@link CorsConfiguration} to use for each incoming request.
         * @see UrlBasedCorsConfigurationSource
         */
        public CorsFilter2(CorsConfigurationSource configSource) {
            super(configSource);
            this.configSource = configSource;
        }


        /**
         * Configure a custom {@link CorsProcessor} to use to apply the matched
         * {@link CorsConfiguration} for a request.
         * <p>By default {@link DefaultCorsProcessor} is used.
         */
        public void setCorsProcessor(CorsProcessor processor) {
            //Assert.notNull(processor, "CorsProcessor must not be null");
            this.processor = new AgoraVai();
        }


        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {

            CorsConfiguration corsConfiguration = this.configSource.getCorsConfiguration(request);
            boolean isValid = this.processor.processRequest(corsConfiguration, request, response);
            if (!isValid || CorsUtils.isPreFlightRequest(request)) {
                return;
            }
            filterChain.doFilter(request, response);
        }

    }


    public class AgoraVai extends DefaultCorsProcessor {

        private static final Log logger = LogFactory.getLog(org.springframework.web.cors.DefaultCorsProcessor.class);


        @Override
        @SuppressWarnings("resource")
        public boolean processRequest(@Nullable CorsConfiguration config, HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {

            Collection<String> varyHeaders = response.getHeaders(HttpHeaders.VARY);
            if (!varyHeaders.contains(HttpHeaders.ORIGIN)) {
                response.addHeader(HttpHeaders.VARY, HttpHeaders.ORIGIN);
            }
            if (!varyHeaders.contains(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD)) {
                response.addHeader(HttpHeaders.VARY, HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD);
            }
            if (!varyHeaders.contains(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS)) {
                response.addHeader(HttpHeaders.VARY, HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
            }

            if (!CorsUtils.isCorsRequest(request)) {
                return true;
            }

            if (response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN) != null) {
                logger.trace("Skip: response already contains \"Access-Control-Allow-Origin\"");
                return true;
            }

            boolean preFlightRequest = CorsUtils.isPreFlightRequest(request);
            if (config == null) {
                if (preFlightRequest) {
                    rejectRequest(new ServletServerHttpResponse(response));
                    return false;
                }
                else {
                    return true;
                }
            }

            return handleInternal(new ServletServerHttpRequest(request), new ServletServerHttpResponse(response), config, preFlightRequest);
        }

        /**
         * Invoked when one of the CORS checks failed.
         * The default implementation sets the response status to 403 and writes
         * "Invalid CORS request" to the response.
         */
        protected void rejectRequest(ServerHttpResponse response) throws IOException {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            response.getBody().write("Invalid CORS request".getBytes(StandardCharsets.UTF_8));
            response.flush();
        }

        /**
         * Handle the given request.
         */
        protected boolean handleInternal(ServerHttpRequest request, ServerHttpResponse response,
                                         CorsConfiguration config, boolean preFlightRequest) throws IOException {

            String requestOrigin = request.getHeaders().getOrigin();
            String allowOrigin = checkOrigin(config, requestOrigin);
            HttpHeaders responseHeaders = response.getHeaders();

            if (allowOrigin == null) {
                logger.debug("Reject: '" + requestOrigin + "' origin is not allowed");
                rejectRequest(response);
                return false;
            }

            HttpMethod requestMethod = getMethodToUse(request, preFlightRequest);
            List<HttpMethod> allowMethods = checkMethods(config, requestMethod);
            if (allowMethods == null) {
                logger.debug("Reject: HTTP '" + requestMethod + "' is not allowed");
                rejectRequest(response);
                return false;
            }

            List<String> requestHeaders = getHeadersToUse(request, preFlightRequest);
            List<String> allowHeaders = checkHeaders(config, requestHeaders);
            if (preFlightRequest && allowHeaders == null) {
                logger.debug("Reject: headers '" + requestHeaders + "' are not allowed");
                rejectRequest(response);
                return false;
            }

            responseHeaders.setAccessControlAllowOrigin(allowOrigin);

            if (preFlightRequest) {
                responseHeaders.add("Access-Control-Allow-Private-Network", "true");
                responseHeaders.setAccessControlAllowMethods(allowMethods);
            }

            if (preFlightRequest && !allowHeaders.isEmpty()) {
                responseHeaders.setAccessControlAllowHeaders(allowHeaders);
            }

            if (!CollectionUtils.isEmpty(config.getExposedHeaders())) {
                responseHeaders.setAccessControlExposeHeaders(config.getExposedHeaders());
            }

            if (Boolean.TRUE.equals(config.getAllowCredentials())) {
                responseHeaders.setAccessControlAllowCredentials(true);
            }

            if (preFlightRequest && config.getMaxAge() != null) {
                responseHeaders.setAccessControlMaxAge(config.getMaxAge());
            }

            response.flush();
            return true;
        }

        /**
         * Check the origin and determine the origin for the response. The default
         * implementation simply delegates to
         * {@link org.springframework.web.cors.CorsConfiguration#checkOrigin(String)}.
         */
        @Nullable
        protected String checkOrigin(CorsConfiguration config, @Nullable String requestOrigin) {
            return config.checkOrigin(requestOrigin);
        }

        /**
         * Check the HTTP method and determine the methods for the response of a
         * pre-flight request. The default implementation simply delegates to
         * {@link org.springframework.web.cors.CorsConfiguration#checkHttpMethod(HttpMethod)}.
         */
        @Nullable
        protected List<HttpMethod> checkMethods(CorsConfiguration config, @Nullable HttpMethod requestMethod) {
            return config.checkHttpMethod(requestMethod);
        }

        @Nullable
        private HttpMethod getMethodToUse(ServerHttpRequest request, boolean isPreFlight) {
            return (isPreFlight ? request.getHeaders().getAccessControlRequestMethod() : request.getMethod());
        }

        /**
         * Check the headers and determine the headers for the response of a
         * pre-flight request. The default implementation simply delegates to
         * {@link org.springframework.web.cors.CorsConfiguration#checkOrigin(String)}.
         */
        @Nullable
        protected List<String> checkHeaders(CorsConfiguration config, List<String> requestHeaders) {
            return config.checkHeaders(requestHeaders);
        }

        private List<String> getHeadersToUse(ServerHttpRequest request, boolean isPreFlight) {
            HttpHeaders headers = request.getHeaders();
            return (isPreFlight ? headers.getAccessControlRequestHeaders() : new ArrayList<>(headers.keySet()));
        }

    }




//    public class CORSFilter extends CorsFilter {
//
//        public CORSFilter(CorsConfigurationSource source) {
//            super(source);
//        }
//
//        @Override
//        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//                throws ServletException, IOException {
//            response.addHeader("Access-Control-Allow-Credentials", "true");
//            response.addHeader("Access-Control-Allow-Headers", "authorization");
//            response.addHeader("Access-Control-Allow-Methods ", "GET,POST,PUT,DELETE");
//            response.addHeader("Access-Control-Allow-Origin ", "https://erp-front-three.vercel.app");
//            response.addHeader("Access-Control-Allow-Private-Network", "true");
//            filterChain.doFilter(request, response);
//        }
//
//    }
//
//    @Bean
//    public CORSFilter corsFilter() {
//        return new CORSFilter(corsConfigurationSource());
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedMethods(asList("GET", "POST", "PUT", "DELETE"));
//        configuration.setAllowedOrigins(asList("http://localhost:3000",
//                "https://erp-front-evert90.vercel.app",
//                "https://erp-front-three.vercel.app",
//                "https://erp-front-npvnnu0y1-evert90.vercel.app")
//        );
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//        configuration.setMaxAge(1800L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }



}