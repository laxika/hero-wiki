package com.morethanheroic.entry.service.render;

import org.pegdown.LinkRenderer;
import org.pegdown.ast.WikiLinkNode;
import org.springframework.stereotype.Service;

@Service
public class WikiLinkRenderer extends LinkRenderer {

    public Rendering render(WikiLinkNode node) {
        //try {
        //Handle WikiLinks alternative format [[page|text]]
        String text = node.getText();
        String url = text;
        int pos;
        if ((pos = text.indexOf("|")) >= 0) {
            url = text.substring(0, pos);
            text = text.substring(pos + 1);
        }

        //url = "./" + URLEncoder.encode(url.replace(' ', '-'), "UTF-8") + ".html";
        return new Rendering("#/entry/" + url, text).withAttribute("ui-sref", "entry({id: '" + url + "'})");
        /*} catch (UnsupportedEncodingException e) {
            throw new IllegalStateException();
        }*/
    }
}
