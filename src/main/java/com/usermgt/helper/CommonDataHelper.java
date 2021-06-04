package com.usermgt.helper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CommonDataHelper {

    private int page , size;

    public void getCommonData(Map<String, ?> searchResult, Map<String, Object> response, List<?> lists) {
        response.put("lists", lists);
        response.put("currentPage", searchResult.get("currentPage"));
        response.put("nextPage", searchResult.get("nextPage"));
        response.put("previousPage", searchResult.get("previousPage"));
        response.put("size", searchResult.get("size"));
        response.put("total", searchResult.get("total"));
    }

}
