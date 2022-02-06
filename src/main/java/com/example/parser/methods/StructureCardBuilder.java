package com.example.parser.methods;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

import static com.example.parser.methods.ReplaceSigns.replaceSigns;
import static com.example.parser.modules.VI.Advantages.createAdvantagesVI;
import static com.example.parser.modules.VI.Characteristics.createCharacteristicsVI;
import static com.example.parser.modules.VI.Equipment.createComplectationVI;
import static com.example.parser.modules.VI.Features.createFeaturesVI;
import static com.example.parser.modules.VI.Weight.createWeightVI;


/**
 * Файл для составления конфигурации описания
 */
public class StructureCardBuilder {

    private StructureCardBuilder() {
        throw new IllegalStateException("Utility class");
    }


    public static String BuildDescriptionVI(String search) {

        Document document;
        try {
            document = Jsoup.parse(new URL(search), 45000);
        } catch (IOException e) {
            return "Введите валидную ссылку";
        }
        StringBuilder oneProductCard = new StringBuilder(createFeaturesVI(document)
                .append(createCharacteristicsVI(document))
                .append(createAdvantagesVI(document))
                .append(createComplectationVI(document))
                .append(createWeightVI(document)));
        return replaceSigns(oneProductCard);
    }
}
