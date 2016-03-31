package com.morethanheroic.entry.service.response.domain.entry;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntryResponse {

    private final String title;
    private final String content;
    private final List<EntryPathPartialResponse> path;
    private final List<EntryChildrenPartialResponse> children;
    private final boolean hasMoreChildren;
}
