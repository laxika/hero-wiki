package com.morethanheroic.entry.service.response.domain.entry;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EntryChildrenPartialResponse {

    private final String id;
    private final String title;
    private final String content;
}
