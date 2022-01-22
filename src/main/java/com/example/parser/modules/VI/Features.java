package com.example.parser.modules.VI;

import com.example.parser.methods.HtmlToText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


/**
 *Формирование начального текста описания товарной карточки
 */

public class Features implements Create{

    private Features() {
        throw new IllegalStateException("Utility class");
    }
    //доделать метод

    public static StringBuilder createFeaturesVI(Document document)  throws IOException {

        Document page = document;
        Element element = page.select("div.content-block").first();
        if (element!=null){
            return new StringBuilder(HtmlToText.html2text(element.toString())+"\n\n");
        }
        return new StringBuilder("\n\n");
        }
}