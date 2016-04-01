package com.morethanheroic.entry.service.render;

import org.pegdown.LinkRenderer;
import org.pegdown.ast.WikiLinkNode;
import org.springframework.stereotype.Service;

@Service
public class WikiLinkRenderer extends LinkRenderer {

    private static final String WIKI_LINK_SEPARATOR = "|";

    public Rendering render(WikiLinkNode node) {
        String innerWikiLinkText = node.getText();

        return buildRendering(getText(innerWikiLinkText), getUrl(innerWikiLinkText));
    }

    private String getText(String innerWikiLinkText) {
        int wikiLinkSeparatorPosition = innerWikiLinkText.indexOf(WIKI_LINK_SEPARATOR);
        if (wikiLinkSeparatorPosition >= 0) {
            return innerWikiLinkText.substring(wikiLinkSeparatorPosition + 1);
        }

        return innerWikiLinkText;
    }

    private String getUrl(String innerWikiLinkText) {
        String result = innerWikiLinkText;

        int wikiLinkSeparatorPosition = innerWikiLinkText.indexOf(WIKI_LINK_SEPARATOR);
        if (wikiLinkSeparatorPosition >= 0) {
            result = innerWikiLinkText.substring(0, wikiLinkSeparatorPosition);
        }

        return result.toLowerCase().replaceAll("\\s+", "-");
    }

    private Rendering buildRendering(String text, String url) {
        return new Rendering("#/entry/" + url, text).withAttribute("ui-sref", "entry({id: '" + url + "'})");
    }
}
