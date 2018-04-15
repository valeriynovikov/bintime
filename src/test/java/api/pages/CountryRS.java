package api.pages;

import lombok.Data;

@Data
public class CountryRS {
    private String name;

    private String[] topLevelDomain;

    private String alpha2Code;

    private String alpha3Code;

    private String[] callingCodes;

    private String capital;

    private String[] altSpellings;

    private String region;

    private String subregion;

    private int population;

    private double[] latlng;

    private String demonym;

    private double area;

    private double gini;

    private String[] timezones;

    private String[] borders;

    private String nativeName;

    private String numericCode;

    private String[] currencies;

    private String[] languages;

    private Translations translations;

    private String relevance;

}
