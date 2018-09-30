package com.hendyghsta.parsinghtml.data.model;

import com.github.florent37.retrojsoup.annotations.JsoupHref;
import com.github.florent37.retrojsoup.annotations.JsoupSrc;
import com.github.florent37.retrojsoup.annotations.JsoupText;

/**
 * Created by hendyghsta on 30/09/2018.
 */
public class ListHome {

    @JsoupText(".moviefilm .movief h2 a")
    public String title;

    @JsoupText(".moviefilm .quality span")
    public String quality;

    @JsoupSrc(".moviefilm a img")
    public String thumbnail;

    @JsoupHref(".moviefilm a")
    public String url;

    @Override
    public String toString() {
        return title+",\n"+
                quality+",\n"+
                thumbnail+",\n"+
                url;
    }
}
