package com.example.parser.page_processing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.parser.methods.ReadingFromFile.readUrlsFromTXTFile;

public class GetPageVI {
    
    public static List<Document> getPage() throws IOException {
            List<Document> documentList = new ArrayList<>();
                        //меняем значение для выбора ссылки, отсчет с нуля
            List<String> urls = readUrlsFromTXTFile();
            for (String url: urls) {
                documentList.add(Jsoup.parse(new URL(url), 45000));
            }
            return documentList;
    }

    @Scope("request")
    public static Document getPageFromUrl(String url)  {
        //меняем значение для выбора ссылки, отсчет с нуля

        try {
            return Jsoup.parse(new URL(url), 45000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Document("Has error");
    }

    public static List<Document> getPageFromMessage(List<String> urls) throws IOException {
        List<Document> documentList = new ArrayList<>();
        //меняем значение для выбора ссылки, отсчет с нуля
        for (String url: urls) {
            documentList.add(Jsoup.parse(new URL(url), 45000));
        }
        return documentList;
    }
}
