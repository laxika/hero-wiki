package com.morethanheroic.entry.service.response.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntryResponse {

    private final String title;
    private final String content;
    private final List<EntryPathResponse> path;
    private final List<EntryChildrenResponse> children;
    private final boolean hasMoreChildren;
}
