package com.morethanheroic.entry.domain.entry.impl;

import com.morethanheroic.entry.domain.entry.EntryEntity;

import lombok.Data;

@Data
public class JsonEntryEntity implements EntryEntity {

    private String title;
    private String content;
}
