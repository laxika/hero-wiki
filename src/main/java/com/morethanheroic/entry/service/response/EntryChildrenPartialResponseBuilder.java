package com.morethanheroic.entry.service.response;

import org.springframework.stereotype.Service;

import com.morethanheroic.entry.domain.entry.EntryEntity;
import com.morethanheroic.entry.service.response.domain.entry.EntryChildrenPartialResponse;

@Service
public class EntryChildrenPartialResponseBuilder {

    private static final int CHARACTERS_SHOWN_FROM_CHILD_CONTENT = 90;

    public EntryChildrenPartialResponse build(EntryEntity childEntity) {
        return EntryChildrenPartialResponse.builder()
                    .id(childEntity.getId())
                    .title(childEntity.getTitle())
                    .content(buildChildContent(childEntity))
                    .build();
    }

    private String buildChildContent(EntryEntity childEntity) {
        String content = childEntity.getContent();

        if (content.length() > CHARACTERS_SHOWN_FROM_CHILD_CONTENT) {
            return content.substring(0, CHARACTERS_SHOWN_FROM_CHILD_CONTENT) + "...";
        }

        return content;
    }
}
