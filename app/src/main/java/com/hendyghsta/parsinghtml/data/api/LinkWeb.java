package com.hendyghsta.parsinghtml.data.api;

import com.github.florent37.retrojsoup.annotations.Select;
import com.hendyghsta.parsinghtml.data.model.ListHome;

import io.reactivex.Observable;


/**
 * Created by hendyghsta on 08/14/2018.
 */
public interface LinkWeb {

    @Select("#wrap #content .filmborder .filmcontent")
    Observable<ListHome> homeLastest();

}
