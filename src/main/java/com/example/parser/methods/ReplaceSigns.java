package com.example.parser.methods;

public class ReplaceSigns {

    public static String replaceSigns(StringBuilder card){
        return card.toString()
                .replaceAll(";;", ";")
                .replaceAll(";;", ";")
                .replaceAll("\\.;", ";")
                .replaceAll("\\.;", ";")
                .replaceAll("\\.\\.", ".")
                .replaceAll("\\.\\.", ".");
    }
}
