package com.morethanheroic.entry.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.entry.domain.entry.EntryEntity;
import com.morethanheroic.entry.service.provider.EntryProvider;
import com.morethanheroic.entry.service.response.EntryChildrenResponseBuilder;
import com.morethanheroic.entry.service.response.domain.children.EntryChildrenResponse;

@RestController
public class EntryChildrenController {

    @Autowired
    private EntryChildrenResponseBuilder entryChildrenResponseBuilder;

    @Autowired
    private EntryProvider entryProvider;

    @RequestMapping(value = "/entry/{entryId}/child/")
    public EntryChildrenResponse getEntry(@PathVariable String entryId) {
        final EntryEntity entryEntity = entryProvider.getEntity(entryId);

        return entryChildrenResponseBuilder.build(entryEntity);
    }
}
