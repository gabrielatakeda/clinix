package org.example.controller;

import org.example.model.enums.TipoExame;

import java.util.HashMap;
import java.util.Map;

public class ProdutoPorExame {
    public static Map<String, Integer> produtosConsumidos(TipoExame tipoExame) {
        Map<String, Integer> mapa = new HashMap<>();

        switch (tipoExame) {
            case HEMOGRAMA:
                mapa.put("Tubo", 4);
                mapa.put("Seringa", 1);
                mapa.put("Corante", 1);
                break;
            case URINA:
                mapa.put("Frasco", 1);
                mapa.put("Medidor PH", 1);
                break;
            case GLICOSE:
                mapa.put("Seringa", 1);
                mapa.put("Tubo", 1);
                mapa.put("Tiras reagentes", 1);
                break;
            case COLESTEROL:
                mapa.put("Seringa", 1);
                mapa.put("Tubo", 1);
                mapa.put("Reagente de Colesterol", 1);
                break;
            default:
                break;
        }
        return mapa;
    }
}

