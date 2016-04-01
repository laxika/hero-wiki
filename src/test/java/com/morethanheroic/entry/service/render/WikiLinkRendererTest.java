package com.morethanheroic.entry.service.render;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.pegdown.LinkRenderer;
import org.pegdown.ast.WikiLinkNode;

public class WikiLinkRendererTest {

    WikiLinkRenderer wikiLinkRenderer;

    @Before
    public void setup() {
        wikiLinkRenderer = new WikiLinkRenderer();
    }

    @Test
    public void testRenderResultsLowercaseHrefWhenCalledWithUppercaseInput() throws Exception {
        WikiLinkNode wikiLinkNode = new WikiLinkNode("Hello|Hello");

        LinkRenderer.Rendering rendering = wikiLinkRenderer.render(wikiLinkNode);

        assertThat(rendering.text, is("Hello"));
        assertThat(rendering.href, is("#/entry/hello"));
        assertThat(rendering.attributes, hasSize(1));
        assertThat(rendering.attributes.get(0).name, is("ui-sref"));
        assertThat(rendering.attributes.get(0).value, is("entry({id: 'hello'})"));
    }

    @Test
    public void testRenderResultsReplacedHrefWhenCalledWithTextContainingSpaceAsInput() throws Exception {
        WikiLinkNode wikiLinkNode = new WikiLinkNode("He ll o|Hello World");

        LinkRenderer.Rendering rendering = wikiLinkRenderer.render(wikiLinkNode);

        assertThat(rendering.text, is("Hello World"));
        assertThat(rendering.href, is("#/entry/he-ll-o"));
        assertThat(rendering.attributes, hasSize(1));
        assertThat(rendering.attributes.get(0).name, is("ui-sref"));
        assertThat(rendering.attributes.get(0).value, is("entry({id: 'he-ll-o'})"));
    }

    @Test
    public void testRenderResultsEmptyHrefWhenCalledWithSeparatorAsFirstCharacterInInput() throws Exception {
        WikiLinkNode wikiLinkNode = new WikiLinkNode("|Hello World");

        LinkRenderer.Rendering rendering = wikiLinkRenderer.render(wikiLinkNode);

        assertThat(rendering.text, is("Hello World"));
        assertThat(rendering.href, is("#/entry/"));
        assertThat(rendering.attributes, hasSize(1));
        assertThat(rendering.attributes.get(0).name, is("ui-sref"));
        assertThat(rendering.attributes.get(0).value, is("entry({id: ''})"));
    }

    @Test
    public void testRenderResultsEmptyTextWhenCalledWithSeparatorAsLastCharacterInInput() throws Exception {
        WikiLinkNode wikiLinkNode = new WikiLinkNode("Hello World|");

        LinkRenderer.Rendering rendering = wikiLinkRenderer.render(wikiLinkNode);

        assertThat(rendering.text, is(""));
        assertThat(rendering.href, is("#/entry/hello-world"));
        assertThat(rendering.attributes, hasSize(1));
        assertThat(rendering.attributes.get(0).name, is("ui-sref"));
        assertThat(rendering.attributes.get(0).value, is("entry({id: 'hello-world'})"));
    }
}