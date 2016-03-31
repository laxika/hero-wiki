package com.morethanheroic.entry.service.provider;

import java.util.List;

import com.morethanheroic.entry.domain.entry.EntryEntity;

public interface EntryProvider {

    EntryEntity getEntity(String id);

    EntryEntity getParentOf(EntryEntity entryEntity);

    List<EntryEntity> getChildrenOf(EntryEntity entryEntity);
}
