package com.example.parser.modules.VI;

import com.example.parser.modules.Creator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Формирование поля "Комплектация" для описания товарной карточки
 */

public class Equipment implements Creator {

    private Equipment() {
        throw new IllegalStateException("Utility class");
    }

    public static StringBuilder createComplectationVI(Document document) {
        StringBuilder equipment = new StringBuilder("<strong>Комплектация:</strong>\n\n");
        Document page = document;

        Element tableParameter = page.select("div.equipment.spoiler").first();
        if (tableParameter!=null){

            Elements names = tableParameter.select("ul");
            Elements values = names.select("li");

            for (Element value : values) {
                String theme = value.select("li").text();
                equipment.append("- ").append(theme).append(";\n");
                }
            equipment = new StringBuilder(equipment.toString().replace(";;", ";"));

            StringBuilder temp = equipment.replace(equipment.length()-2,equipment.length()-1,".")
                    .append("\n");
            equipment = new StringBuilder(temp.toString()
                    .replace("..", "."));
        }
        return equipment;
    }
}