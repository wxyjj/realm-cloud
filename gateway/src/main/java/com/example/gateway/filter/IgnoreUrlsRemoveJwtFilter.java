package com.example.gateway.filter;

import com.example.common.constant.AuthConstant;
import com.example.gateway.properties.IgnoreUrlProperties;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 白名单路径访问时需要移除JWT请求头
 *
 * @Author wxy
 * @Date 2020/9/22 11:07
 * @Version 1.0
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {
    @Resource
    private IgnoreUrlProperties ignoreUrlProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String path = uri.getPath();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径移除JWT请求头
        List<String> ignoreUrls = ignoreUrlProperties.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, path)) {
                request = exchange.getRequest().mutate().header(AuthConstant.JWT_TOKEN_HEADER, "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}
