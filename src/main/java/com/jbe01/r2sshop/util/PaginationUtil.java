package com.jbe01.r2sshop.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {
    public static PageRequest pageRequest(int page, int size, String sorts) {
        PageRequest pageRequest = PageRequest.of(page, size);

        if (!StringUtils.isEmpty(sorts)) {
            List<Sort.Order> orders = new ArrayList<>();
            String[] sortPairs = sorts.split(",");

            for (String sortPair : sortPairs) {
                String[] parts = sortPair.split(":");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid sort format: " + sortPair + ". Expected format: field:direction");
                }

                String field = parts[0].trim();
                String direction = parts[1].trim().toUpperCase();

                switch (direction) {
                    case "ASC":
                        orders.add(Sort.Order.asc(field));
                        break;
                    case "DESC":
                        orders.add(Sort.Order.desc(field));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid sort direction: " + direction + ". Use 'asc' or 'desc'");
                }
            }

            pageRequest = PageRequest.of(page, size, Sort.by(orders));
        }

        return pageRequest;

    };
}
