package com.morethanheroic.entry.domain.entry;

public interface EntryEntity {

    String getId();

    void setTitle(String title);

    String getTitle();

    void setContent(String markdown);

    String getContent();

    String[] getChildren();

    String getParent();
}
