/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.controller;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.controller <br/>
 * Class  : Language <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>Language</code> class is used to store Locale data. It contains the language name, the language- and countrycode.  <br/>
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 21.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class Language {

    private String name;
    private String lang;
    private String country;

    Language(String name, String languageCode, String countryCode) {
        if ((name == null) || (languageCode == null) || (countryCode == null)) {
            throw new IllegalArgumentException();
        }

        if (name.equals("") || languageCode.equals("") || countryCode.equals("")) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.lang = languageCode;
        this.country = countryCode;
    }

    Language(String languageCode, String countryCode) {
        if ((languageCode == null) || (countryCode == null)) {
            throw new IllegalArgumentException();
        }

        if (languageCode.equals("") || countryCode.equals("")) {
            throw new IllegalArgumentException();
        }

        this.lang = languageCode;
        this.country = countryCode;
    }

    /**
     * This function returns the name of the language.
     * 
     * @return The name of the language object as String.
     */
    public String getNameString() {
        return this.name;
    }

    /**
     * This functions returns the language code.
     * 
     * @return The language code as String.
     */
    public String getLanguageString() {
        return this.lang;
    }

    /**
     * This function returns the country code.
     * 
     * @return The country code as String.
     */
    public String getCountryString() {
        return this.country;
    }
}
