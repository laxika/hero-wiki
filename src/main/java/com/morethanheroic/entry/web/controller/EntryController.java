package com.morethanheroic.entry.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.entry.domain.entry.EntryEntity;
import com.morethanheroic.entry.service.provider.EntryProvider;
import com.morethanheroic.entry.service.response.EntryEntityToResponseTransformer;
import com.morethanheroic.entry.service.response.domain.EntryResponse;

@RestController
public class EntryController {

    @Autowired
    private EntryProvider entryProvider;

    @Autowired
    private EntryEntityToResponseTransformer entryEntityToResponseTransformer;

    @RequestMapping(value = "/entry/{entryId}")
    public EntryResponse getEntry(@PathVariable String entryId) {
        final EntryEntity entryEntity = entryProvider.getEntity(entryId);

        return entryEntityToResponseTransformer.transform(entryEntity);
    }
}
