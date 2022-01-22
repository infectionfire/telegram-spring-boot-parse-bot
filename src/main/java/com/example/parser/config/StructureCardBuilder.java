package com.example.parser.config;


import com.example.parser.modules.VI.ManualCrawler;
import com.example.parser.modules.VI.PhotoCrawler;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.parser.modules.VI.Advantages.createAdvantagesVI;
import static com.example.parser.modules.VI.Characteristics.createCharacteristicsVI;
import static com.example.parser.modules.VI.Equipment.createComplectationVI;
import static com.example.parser.modules.VI.Features.createFeaturesVI;
import static com.example.parser.modules.VI.Weight.createWeightVI;
import static com.example.parser.page_processing.GetPageVI.getPage;
import static com.example.parser.page_processing.GetPageVI.getPageFromUrl;


/**
 * Файл для составления конфигурации описания
 */
public class StructureCardBuilder {
    private static List<String> ttx = new ArrayList<>(100);//если товаров больше сотки поменять тоже
    private static List<String> photos = new ArrayList<>(100);//если товаров больше сотки поменять тоже
    private static List<String> instr = new ArrayList<>(100);//если товаров больше сотки поменять тоже

    public static List<String> getTtx() {
        return ttx;
    }

    public static void setTtx(List<String> ttx) {
        StructureCardBuilder.ttx = ttx;
    }

    public static List<String> getPhotos() {
        return photos;
    }

    public static void setPhotos(List<String> photos) {
        StructureCardBuilder.photos = photos;
    }

    public static List<String> getInstr() {
        return instr;
    }

    public static void setInstr(List<String> instr) {
        StructureCardBuilder.instr = instr;
    }

    private StructureCardBuilder() {
        throw new IllegalStateException("Utility class");
    }



    //функции для заполнения ттх
    public static void BuildDescriptions() throws IOException {
        List<String> productCards = new ArrayList<>(100);
        List<Document> documentList = getPage();
        List<String> photoListBuilder = new ArrayList<>(100);
        List<String> instrListBuilder = new ArrayList<>(100);
        for(Document document:documentList) {

            StringBuilder oneProductCard = new StringBuilder();

            oneProductCard.append(createFeaturesVI(document))
                    .append(createCharacteristicsVI(document))
                    .append(createAdvantagesVI(document))
                    .append(createComplectationVI(document))
                    .append(createWeightVI(document));
            productCards.add(oneProductCard.toString()
                    .replaceAll(";;",";")
                    .replaceAll("\\.;", ";")
                    .replaceAll("\\.\\.","."));
            photoListBuilder.add(PhotoCrawler.getPhoto(document));
            instrListBuilder.add(ManualCrawler.getManual(document));
            }
        setPhotos(photoListBuilder);
        setInstr(instrListBuilder);
        setTtx(productCards) ;
    }

    public static String BuildDescription(String search) throws IOException {

        Document document = getPageFromUrl(search);
        StringBuilder oneProductCard = new StringBuilder(createFeaturesVI(document)
                .append(createCharacteristicsVI(document))
                .append(createAdvantagesVI(document))
                .append(createComplectationVI(document))
                .append(createWeightVI(document)));

        return oneProductCard.toString()
                .replaceAll(";;", ";")
                .replaceAll(";;", ";")
                .replaceAll("\\.;", ";")
                .replaceAll("\\.;", ";")
                .replaceAll("\\.\\.", ".")
                .replaceAll("\\.\\.", ".");
    }
}
