package com.morethanheroic.entry.service.provider;

import com.morethanheroic.entry.domain.entry.EntryEntity;

public interface EntryProvider {

    EntryEntity getEntity(String id);
}
