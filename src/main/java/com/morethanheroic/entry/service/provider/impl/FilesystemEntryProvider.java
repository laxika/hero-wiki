package com.morethanheroic.entry.service.provider.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.morethanheroic.entry.domain.entry.EntryEntity;
import com.morethanheroic.entry.domain.entry.impl.JsonEntryEntity;
import com.morethanheroic.entry.service.provider.EntryProvider;

@Service
@Primary
public class FilesystemEntryProvider implements EntryProvider {

    @Value("${entry.provider.filesystem.path}")
    private String path;

    private final ObjectMapper jsonObjectMapper = new ObjectMapper();

    @Override
    public EntryEntity getEntity(final String id) {
        try {
            return loadEntityFromFilesystem(id);
        } catch (IOException e) {
            throw new RuntimeException("Error happened when loading entity: " + id, e);
        }
    }

    @Override
    public EntryEntity getParentOf(final EntryEntity entryEntity) {
        return getEntity(entryEntity.getParent());
    }

    @Override
    public List<EntryEntity> getChildrenOf(final EntryEntity entryEntity) {
        return getChildrenOf(entryEntity, entryEntity.getChildren().size());
    }

    @Override
    public List<EntryEntity> getChildrenOf(final EntryEntity entryEntity, final int count) {
        if (count == 0) {
            return Collections.emptyList();
        }

        final List<EntryEntity> result = new ArrayList<>();

        for (String childrenId : entryEntity.getChildren()) {
            result.add(getEntity(childrenId));

            if (result.size() == count) {
                break;
            }
        }

        return Collections.unmodifiableList(result);
    }

    private EntryEntity loadEntityFromFilesystem(final String id) throws IOException {
        return jsonObjectMapper.readValue(new File(path + "/" + id), JsonEntryEntity.class);
    }
}
