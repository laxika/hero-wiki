package com.morethanheroic.entry.domain.entry;

public interface EntryEntity {

    void setTitle(String title);

    String getTitle();

    void setContent(String markdown);

    String getContent();
}
