package com.warthur.nacos.demo.domain.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author warthur
 * @date 2018/11/06
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class RequestParamUtils {

    @SuppressWarnings("unchecked")
    private static List<String> handleRequestParam(HttpServletRequest request, Object[] reqParams) {

        Map<String, Object> requestMap = new HashMap<>();

        // 获取requestBody，放入treeMap
        boolean method = "POST".equals(request.getMethod()) || "PUT".equals(request.getMethod());
        if (reqParams.length > 0 && method) {
            Arrays.stream(reqParams).forEach(param ->
                    requestMap.putAll(new ObjectMapper().convertValue(param, TreeMap.class)));
        }

        // 获取 query string，放入treeMap
        List<String> requestList = new ArrayList<>(Arrays.asList(request.getQueryString().split("&")));

        // 剔除signature
        requestList.remove("signature=" + request.getParameter("signature"));

        // 复杂对象的值只拿size
        for (Map.Entry<String, Object> entry : requestMap.entrySet()) {
            if (entry.getValue() == null || entry.getValue().equals(StringUtils.EMPTY)) {
                continue;
            }

            StringBuilder params = new StringBuilder(entry.getKey()).append("=");
            if (entry.getValue() instanceof Map) {
                params.append(((Map) entry.getValue()).size());
            } else if (entry.getValue() instanceof List) {
                params.append(((List) entry.getValue()).size());
            } else {
                params.append(entry.getValue());
            }

            requestList.add(params.toString());
        }

        return requestList;
    }
}
