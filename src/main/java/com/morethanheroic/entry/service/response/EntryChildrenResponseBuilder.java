package com.morethanheroic.entry.service.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.entry.domain.entry.EntryEntity;
import com.morethanheroic.entry.service.provider.EntryProvider;
import com.morethanheroic.entry.service.response.domain.children.EntryChildrenResponse;
import com.morethanheroic.entry.service.response.domain.entry.EntryChildrenPartialResponse;

@Service
public class EntryChildrenResponseBuilder {

    @Autowired
    private EntryProvider entryProvider;

    @Autowired
    private EntryChildrenPartialResponseBuilder entryChildrenPartialResponseBuilder;

    public EntryChildrenResponse build(EntryEntity entryEntity) {
        return EntryChildrenResponse.builder()
                    .children(buildChildren(entryEntity))
                    .build();
    }

    private List<EntryChildrenPartialResponse> buildChildren(EntryEntity entryEntity) {
        return entryProvider.getChildrenOf(entryEntity)
                            .stream()
                            .map(entryChildrenPartialResponseBuilder::build)
                            .collect(Collectors.toList());
    }
}
