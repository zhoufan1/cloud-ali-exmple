package com.example.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.example.foundation.dto.Response;
import com.example.foundation.exception.BusinessException;
import com.example.foundation.provider.within.DesAlgorithm;
import com.example.foundation.utils.CollectionUtils;
import com.example.gateway.common.GateWayCode;
import com.example.gateway.common.SecurityConstants;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * @author ZhouFan
 * @date 2019/11/7 09:31
 * @description
 */
@Component
@Slf4j
public class CodeVerifyFilter extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            if (StringUtils.contains(path, SecurityConstants.OAUTH_TOKEN_URL)) {
                try {
                    checkCode(request);
                } catch (Exception e) {
                    log.error("verify fail , ", e);
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
                    return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(Response.systemError(e.getMessage())))));

                }
            }
            return chain.filter(exchange);
        };
    }

    private void checkCode(ServerHttpRequest request) {
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String verifyCode = queryParams.getFirst("verifyCode");
        if (StringUtils.isBlank(verifyCode)) {
            throw new BusinessException(GateWayCode.CaptchaError);
        }
        String encrypt = queryParams.getFirst(SecurityConstants.VERIFY_CODE_ENCRYPT);
        if (StringUtils.isBlank(encrypt)) {
            throw new BusinessException(GateWayCode.CaptchaEncryptNull);
        }
        String valueInfo = DesAlgorithm.create(verifyCode).decrypt(encrypt);
        List<String> valueInfoList = Splitter.on(":").splitToList(valueInfo);
        if (CollectionUtils.isNullOrEmpty(valueInfoList) || valueInfoList.size() != 2) {
            throw new BusinessException(GateWayCode.CaptchaError);
        }
    }
}

