package com.morethanheroic.entry.service.response.domain.children;

import java.util.List;

import com.morethanheroic.entry.service.response.domain.entry.EntryChildrenPartialResponse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EntryChildrenResponse {

    private final List<EntryChildrenPartialResponse> children;
}
