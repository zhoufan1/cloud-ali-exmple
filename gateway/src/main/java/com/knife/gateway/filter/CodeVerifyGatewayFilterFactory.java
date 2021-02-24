package com.knife.gateway.filter;

import com.google.common.base.Splitter;
import com.knife.foundation.exception.BusinessException;
import com.knife.foundation.provider.within.DesAlgorithm;
import com.knife.foundation.utils.CollectionUtils;
import com.knife.gateway.common.GateWayCode;
import com.knife.gateway.common.SecurityConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;

/**
 * @author knife
 * @date 2019/11/7 09:31
 * @description
 */
@Component
@Slf4j
public class CodeVerifyGatewayFilterFactory extends AbstractGatewayFilterFactory<CodeVerifyGatewayFilterFactory.CodeConfig> {

    private static final String KEY = "paths";

    public CodeVerifyGatewayFilterFactory() {
        super(CodeConfig.class);
    }

    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.GATHER_LIST;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(KEY);
    }

    @Override
    public GatewayFilter apply(CodeConfig config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            if (CollectionUtils.isNullOrEmpty(config.getPaths())) {
                chain.filter(exchange);
            }
            if (config.getPaths().contains(path)) {
                checkCode(request);
            }
            return chain.filter(exchange);
        };
    }

    @Data
    public static class CodeConfig {
        private List<String> paths;
    }

    private void checkCode(ServerHttpRequest request) {
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String verifyCode = queryParams.getFirst("verifyCode");
        String encrypt = queryParams.getFirst(SecurityConstants.VERIFY_CODE_ENCRYPT);
        checkVerifyEncrypt(verifyCode, encrypt);
    }

    private void checkVerifyEncrypt(String verifyCode, String encrypt) {
        if (StringUtils.isBlank(verifyCode)) {
            throw new BusinessException(GateWayCode.CaptchaError);
        }
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

