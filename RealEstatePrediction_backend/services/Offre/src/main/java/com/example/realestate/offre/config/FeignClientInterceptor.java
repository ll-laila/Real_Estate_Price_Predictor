//package com.example.realestate.offre.config;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Component
//public class FeignClientInterceptor implements RequestInterceptor {
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        // Get the current HTTP request
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (requestAttributes != null) {
//            HttpServletRequest request = requestAttributes.getRequest();
//            // Get the Authorization header
//            String authorizationHeader = request.getHeader("Authorization");
//            if (authorizationHeader != null) {
//                // Add the Authorization header to the Feign request
//                requestTemplate.header("Authorization", authorizationHeader);
//            }
//        }
//    }
//}
