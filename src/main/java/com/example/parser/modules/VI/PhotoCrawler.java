package com.example.parser.modules.VI;

import com.example.parser.modules.Creator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Парсит страницу и берет первое фото товара
 * можно переделать, сделать сплит через " и вытаскивать ссылки через endWith
 */

public class PhotoCrawler implements Creator {

    private static String photo = "";

    public static String getPhoto(Document document)  {
        PhotoUrlsCreateVI(document);
        return photo;
    }

    public static String PhotoUrlsCreateVI(Document document) {
        Document page = document;
        Element imageElement = page.select("div.item").first();
        if (imageElement!=null) {

            photo = imageElement.toString().replaceAll("<div class=\"stage\" data-behavior=\"stage\">", "")
                    .replaceAll("<div class=\"item -active\" data-behavior=\"item\">", "")
                    .replaceAll("<img src=\"data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7\" data-src=\"", "");
            String[] ph = photo.split("\"");
            photo = ph[0].strip();
        }
        return photo;
        }
}

