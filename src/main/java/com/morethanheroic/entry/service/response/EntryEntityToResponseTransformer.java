package com.morethanheroic.entry.service.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.morethanheroic.entry.domain.entry.EntryEntity;
import com.morethanheroic.entry.service.provider.EntryProvider;
import com.morethanheroic.entry.service.render.WikiLinkRenderer;
import com.morethanheroic.entry.service.response.domain.EntryChildrenResponse;
import com.morethanheroic.entry.service.response.domain.EntryPathResponse;
import com.morethanheroic.entry.service.response.domain.EntryResponse;

@Service
public class EntryEntityToResponseTransformer {

    @Autowired
    private PegDownProcessor pegDownProcessor;

    @Autowired
    private EntryProvider entryProvider;

    public EntryResponse transform(EntryEntity entryEntity) {
        return EntryResponse.builder()
            .title(entryEntity.getTitle())
            .content(pegDownProcessor.markdownToHtml(entryEntity.getContent(), new WikiLinkRenderer()))
            .path(buildPath(entryEntity))
            .children(buildChildren(entryEntity))
            .build();
    }

    private List<EntryPathResponse> buildPath(EntryEntity entryEntity) {
        final List<EntryPathResponse> resultAsList = Lists.newArrayList(
            EntryPathResponse.builder()
                .id(entryEntity.getId())
                .title(entryEntity.getTitle())
                .build()
        );

        EntryEntity nextEntity = entryEntity;
        while (nextEntity.getParent() != null) {
            nextEntity = entryProvider.getParentOf(nextEntity);

            resultAsList.add(
                EntryPathResponse.builder()
                     .id(nextEntity.getId())
                     .title(nextEntity.getTitle())
                     .build()
            );
        }

        return Collections.unmodifiableList(Lists.reverse(resultAsList));
    }

    private List<EntryChildrenResponse> buildChildren(EntryEntity entryEntity) {
        final List<EntryChildrenResponse> result = new ArrayList<>();

        for(EntryEntity childEntity : entryProvider.getChildrenOf(entryEntity)) {
            result.add(
                EntryChildrenResponse.builder()
                    .id(childEntity.getId())
                    .title(childEntity.getTitle())
                    .content(childEntity.getContent())
                    .build()
            );
        }

        return result;
    }
}
