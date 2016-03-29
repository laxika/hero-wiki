package com.morethanheroic.entry.service.response.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntryResponse {

    private String title;
    private String markdown;
}
