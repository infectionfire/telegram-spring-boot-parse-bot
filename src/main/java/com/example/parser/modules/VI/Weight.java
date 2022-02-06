package com.example.parser.modules.VI;

import com.example.parser.modules.Creator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Weight implements Creator {//вес, первая строка - брутто, остальные перекидываем в габариты

    private Weight() {
        throw new IllegalStateException("Utility class");
    }

    public static StringBuilder createWeightVI(Document document) {
        StringBuilder weigh = new StringBuilder("<strong>Габаритные размеры:</strong>\n\n");
        //берем страницу
        Document page = document;
        //выбираем поля
        Elements tableParameter = page.select("ul.unordered-list");
        if (tableParameter!=null) {
            //выбираем вложение
            Elements names = tableParameter.select("li.item");
            List<String> listTemp = new ArrayList<>();
            //добавляем
            for (Element value : names) {
                String theme = value.select("li.item").text();
                listTemp.add(theme);
            }
            int count = 0;
            for (int i = 1; i < listTemp.size(); i++) {
                if (listTemp.get(i-1).equals("Единица товара: Штука")) {
                    count = i;
                }
            }
            if (count != 0) {
                //редактируем статику
                weigh.append("- Вес брутто: ")
                        .append(listTemp.get(count)
                        .replace("Вес, кг: ", ""))
                        .append(" кг;\n");
                weigh.append("- Габаритные размеры (ДхШхВ): ")
                        .append(listTemp.get(count + 1)
                        .replace("Длина, мм: ", ""))
                        .append("x");
                weigh.append(listTemp.get(count + 2)
                        .replace("Ширина, мм: ", ""))
                        .append("x");
                weigh.append(listTemp.get(count + 3)
                        .replace("Высота, мм: ", ""))
                        .append(" мм.\n\n");
            }
        }
        return weigh;
    }




}