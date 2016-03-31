package com.morethanheroic.entry.domain.entry;

import java.util.List;

public interface EntryEntity {

    String getId();

    void setTitle(String title);

    String getTitle();

    void setContent(String markdown);

    String getContent();

    List<String> getChildren();

    String getParent();
}
