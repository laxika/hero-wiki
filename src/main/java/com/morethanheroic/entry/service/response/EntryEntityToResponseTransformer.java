package com.morethanheroic.entry.service.response;

import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.entry.domain.entry.EntryEntity;
import com.morethanheroic.entry.service.render.WikiLinkRenderer;
import com.morethanheroic.entry.service.response.domain.EntryResponse;

@Service
public class EntryEntityToResponseTransformer {

    @Autowired
    private PegDownProcessor pegDownProcessor;

    public EntryResponse transform(EntryEntity entryEntity) {
        return EntryResponse.builder()
            .title(entryEntity.getTitle())
            .content(pegDownProcessor.markdownToHtml(entryEntity.getContent(), new WikiLinkRenderer()))
                            .build();
    }
}
