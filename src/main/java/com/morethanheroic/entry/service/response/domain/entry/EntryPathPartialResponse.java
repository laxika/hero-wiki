package com.morethanheroic.entry.service.response.domain.entry;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntryPathPartialResponse {

    private String title;
    private String id;
}
