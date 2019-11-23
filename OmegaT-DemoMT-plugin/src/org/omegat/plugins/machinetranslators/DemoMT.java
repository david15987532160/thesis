/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
 with fuzzy matching, translation memory, keyword search,
 glossaries, and translation leveraging into updated projects.

 Copyright (C) 2010 Alex Buloichik, Didier Briel
 Home page: http://www.omegat.org/
 Support center: http://groups.yahoo.com/group/OmegaT/

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 **************************************************************************/

package org.omegat.plugins.machinetranslators;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import org.omegat.plugins.core.BaseTranslate;
import org.omegat.plugins.util.Language;
import org.omegat.plugins.util.PatternConsts;
import org.omegat.plugins.util.WikiGet;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;

@SuppressWarnings({"unchecked", "rawtypes"})
public class DemoMT extends BaseTranslate {
    // Change IP address according to match hosting server
    protected static final String MT_URL = "http://127.0.0.1:8080/translate";

    @Override
    protected String getPreferenceName() {
        return "allow_demo_mt_translate";
    }

    public String getName() {
        return "Demo MT";
    }

    @Override
    protected String translate(Language sLang, Language tLang, String text) throws Exception {
        String trText = text.length() > 5000 ? text.substring(0, 4997) + "..." : text;

        String source = sLang.getLanguageCode();

////        String v = WikiGet.post(GT_URL, p);
//        return tr;
        String result = post(trText, source);
        // To return a ResultModel
//        Type type = new TypeToken<DemoMTModel<String>>() {
//        }.getType();
//        DemoMTModel<String> model = new Gson().fromJson(result, type);
//
//        if (model.getErrors().size() > 0) {
//            String error = "";
//
//            for (int i = 0; i < model.getErrors().size(); i++) {
//                error = model.getErrors().get(i) + "\n";
//            }
//
//            return error;
//        }
//
//        return model.getData();

        // Attempt to clean spaces added by GT
        // Spaces after
        Matcher tag = PatternConsts.OMEGAT_TAG_SPACE.matcher(result);
        while (tag.find()) {
            String searchTag = tag.group();
            if (text.indexOf(searchTag) == -1) { // The tag didn't appear with a
                // trailing space in the source text
                String replacement = searchTag.substring(0, searchTag.length() - 1);
                result = result.replace(searchTag, replacement);
            }
        }

        // Spaces before
        tag = PatternConsts.SPACE_OMEGAT_TAG.matcher(result);
        while (tag.find()) {
            String searchTag = tag.group();
            if (text.indexOf(searchTag) == -1) { // The tag didn't appear with a
                // leading space in the source text
                String replacement = searchTag.substring(1, searchTag.length());
                result = result.replace(searchTag, replacement);
            }
        }
        System.out.println(result);
        return result;
    }

    public static String post(String text, String source) {
        Map<String, String> map = new HashMap<>();
        map.put("res", text);
        map.put("src", source);

        try {
            return WikiGet.post(MT_URL, map);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
