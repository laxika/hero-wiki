package com.morethanheroic.entry.service.response.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EntryChildrenResponse {

    private final String id;
    private final String title;
    private final String content;
}
