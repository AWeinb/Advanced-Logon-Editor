/*
 * #######################################################
 * 
 * Copyright (c) 2013, A. Weinberger. All rights reserved.
 * --------------------------------------------------------
 */
package ale.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import ale.util.fileUtil.FileUtil;

/**
 * ------------------------------------------------- <br/>
 * Package: ale.controller <br/>
 * Class  : LanguageUtil <br/>
 * ---------------------------                       <br/>
 *                                                   <br/>
 * The <code>LanguageUtil</code> class contains Methods to create and read languages from files.  <br/>
 * The file content looks like this. <br/>
 *  <code>English (US)=en;US         <br/>
 *        German (DE)=de;DE </code> <br/>
 *  The class is used to manage available locales of the program. The file is a short summary of them.
 *                                                   <br/>
 *                                                   <br/>
 * Last edited: 21.04.2013 <br/>
 * ------------------------------------------------- <br/>
 */
public final class LanguageUtil {

    private static final String SPLIT_EQUAL = "=";
    private static final String SPLIT_SEMIC = ";";

    private static List<Language> languages;

    private LanguageUtil() {
    }

    /*
     * Reads the locales from the textfile to list. This happens only once. After the list is filled, it gets not refilled.
     */
    static List<Language> getLanguages(Path languageProperties) throws IOException {
        if (!FileUtil.control(languageProperties)) {
            throw new IOException();
        }

        if (languages == null) {
            readLanguageProperties(languageProperties);
        }

        return languages;
    }

    /*
     * Converts Language- and Countrycode to a language object.
     */
    static Language getLanguage(String lang, String country) {
        return new Language(lang, country);
    }

    // Splits a line with the two separators and creates a new language object.
    private static void readLanguageProperties(Path languageProperties) {
        languages = new LinkedList<>();

        List<String> tmp = FileUtil.readTextFile(languageProperties);

        for (String s : tmp) {
            String[] ar = s.split(SPLIT_EQUAL);

            if (ar.length == 2) {
                String n = ar[0];
                ar = ar[1].split(SPLIT_SEMIC);

                if ((ar.length == 2) && (n != null) && (ar[0] != null) && (ar[1] != null)) {
                    if (!n.equals("") && !ar[0].equals("") && !ar[1].equals("")) {
                        languages.add(new Language(n, ar[0], ar[1]));
                    }
                }
            }
        }
    }
}
