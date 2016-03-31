package com.morethanheroic.entry.service.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.entry.domain.entry.EntryEntity;
import com.morethanheroic.entry.service.provider.EntryProvider;
import com.morethanheroic.entry.service.render.WikiLinkRenderer;
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
            .build();
    }

    private EntryPathResponse[] buildPath(EntryEntity entryEntity) {
        final List<EntryPathResponse> resultAsList = new ArrayList<>();
        resultAsList.add(
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

        EntryPathResponse result[] = resultAsList.toArray(new EntryPathResponse[resultAsList.size()]);

        ArrayUtils.reverse(result);

        return result;
    }
}
