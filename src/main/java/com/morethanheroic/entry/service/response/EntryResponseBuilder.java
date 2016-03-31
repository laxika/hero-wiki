package com.morethanheroic.entry.service.response;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.morethanheroic.entry.domain.entry.EntryEntity;
import com.morethanheroic.entry.service.provider.EntryProvider;
import com.morethanheroic.entry.service.render.WikiLinkRenderer;
import com.morethanheroic.entry.service.response.domain.entry.EntryChildrenPartialResponse;
import com.morethanheroic.entry.service.response.domain.entry.EntryPathPartialResponse;
import com.morethanheroic.entry.service.response.domain.entry.EntryResponse;

@Service
public class EntryResponseBuilder {

    private static final int CHILDREN_SHOWN = 3;

    @Autowired
    private PegDownProcessor pegDownProcessor;

    @Autowired
    private EntryProvider entryProvider;
    
    @Autowired
    private EntryChildrenPartialResponseBuilder entryChildrenPartialResponseBuilder;

    public EntryResponse build(EntryEntity entryEntity) {
        return EntryResponse.builder()
            .title(entryEntity.getTitle())
            .content(pegDownProcessor.markdownToHtml(entryEntity.getContent(), new WikiLinkRenderer()))
            .path(buildPath(entryEntity))
            .children(buildChildren(entryEntity))
            .hasMoreChildren(entryEntity.getChildren().size() > CHILDREN_SHOWN)
            .build();
    }

    private List<EntryPathPartialResponse> buildPath(EntryEntity entryEntity) {
        final List<EntryPathPartialResponse> resultAsList = Lists.newArrayList(
            EntryPathPartialResponse.builder()
                             .id(entryEntity.getId())
                             .title(entryEntity.getTitle())
                             .build()
        );

        EntryEntity nextEntity = entryEntity;
        while (nextEntity.getParent() != null) {
            nextEntity = entryProvider.getParentOf(nextEntity);

            resultAsList.add(
                EntryPathPartialResponse.builder()
                     .id(nextEntity.getId())
                     .title(nextEntity.getTitle())
                     .build()
            );
        }

        return Collections.unmodifiableList(Lists.reverse(resultAsList));
    }

    private List<EntryChildrenPartialResponse> buildChildren(EntryEntity entryEntity) {
        return entryProvider.getChildrenOf(entryEntity, CHILDREN_SHOWN)
                                .stream()
                                .map(entryChildrenPartialResponseBuilder::build)
                                .collect(Collectors.toList());
    }
}
