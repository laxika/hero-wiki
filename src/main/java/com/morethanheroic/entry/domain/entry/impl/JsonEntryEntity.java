package com.morethanheroic.entry.domain.entry.impl;

import java.util.List;

import com.morethanheroic.entry.domain.entry.EntryEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class JsonEntryEntity implements EntryEntity {

    private String id;

    @Setter
    private String title;

    @Setter
    private String content;

    private String parent;
    private List<String> children;
}
