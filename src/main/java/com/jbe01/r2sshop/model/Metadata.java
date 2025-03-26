package com.jbe01.r2sshop.model;

import lombok.Data;

@Data
public class Metadata {
    private int pageSize;
    private int pageNumber;
    private int totalCount;
    private int totalPage;
}
