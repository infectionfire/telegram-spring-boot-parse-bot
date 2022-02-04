package com.example.parser.modules.VI;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static com.example.parser.methods.StringCharacterFormatter.characteristicsFormatter;

/**
 * Формирование поля "технические характеристики" для описания товарной карточки
 */

public class Characteristics implements Create{//технические характеристики
    private Characteristics() {
        throw new IllegalStateException("Utility class");
    }

    public static StringBuilder createCharacteristicsVI(Document document) {
        StringBuilder charact = new StringBuilder("<strong>Технические характеристики:</strong>\n\n");
        Element element = document
                .select("div.features.spoiler")
                .first();
        if (element!=null) {
            Elements names = element.select("ul");//вытаскиваем инфу из маркированного списка
            Elements values = names.select("li");

            for (Element value : values) {//цикл добавляет значения к строке, попутно форматируя ее
                String text = value.select("span.text").text();
                String valueInf = value.select("span.value").text();
                charact.append(characteristicsFormatter(text, valueInf));
            }
        }
        if (charact.toString().endsWith(";\n")){
            charact.replace(charact.length()-2,charact.length()-1,".");
        }
        return charact.append("\n");
    }

}