package com.example.parser.modules.VI;

import com.example.parser.modules.Creator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Формирование поля особенности для описания товарной карточки
 */

public class Advantages implements Creator {//особенности

    private Advantages() {
        throw new IllegalStateException("Utility class");
    }


    public static StringBuilder createAdvantagesVI(Document document) {
        StringBuilder advantagesCreator = new StringBuilder("<strong>Особенности:</strong>\n\n");
        Element element = document
                .select("div.advantages.spoiler")
                .first();
        if (element!=null) {
            Elements names = element.select("ul");//вытаскиваем инфу из маркированного списка
            Elements values = names.select("li");

            for (int i =0 ;i < values.size();i++) {//цикл добавляет значения к строке, попутно форматируя ее
                String theme = values.get(i).select("li").text();
                if (values.size()-1!=i) {
                    advantagesCreator.append("- ").append(theme).append(";\n");
                }else {
                    advantagesCreator.append("- ").append(theme).append(".\n\n");
                }
            }
        }
        return advantagesCreator;
    }
}