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

package org.omegat.core.machinetranslators;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.omegat.core.models.DemoMTModel;
import org.omegat.util.Language;
import org.omegat.util.WikiGet;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"unchecked", "rawtypes"})
public class DemoMT extends BaseTranslate {
    protected static final String MT_URL = "http://192.168.1.80:8080/test_hello";
//    protected static final String MARK_BEG = "{\"translatedText\":\"";
//    protected static final String MARK_END = "\"}";
//    protected static final Pattern RE_UNICODE = Pattern.compile("\\\\u([0-9A-Fa-f]{4})");
//    protected static final Pattern RE_HTML = Pattern.compile("&#([0-9]+);");
//    protected static final String CHARSET_MARK = "charset=";
//    protected static final String DEFAULT_RESPONSE_CHARSET = "ISO-8859-1";

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

//        Map<String, String> p = new TreeMap<String, String>();
//        p.put("v", "1.0");
//        String targetLang = tLang.getLanguageCode();
//         Differentiate in target between simplified and traditional Chinese
//        if ((tLang.getLanguage().compareToIgnoreCase("zh-cn") == 0)
//                || (tLang.getLanguage().compareToIgnoreCase("zh-tw") == 0))
//            targetLang = tLang.getLanguage();
//        else if ((tLang.getLanguage().compareToIgnoreCase("zh-hk") == 0))
//            targetLang = "ZH-TW"; // Google doesn't recognize ZH-HK
//        p.put("langpair", sLang.getLanguageCode() + '|' + targetLang);
//        p.put("q", trText);

//        Map<String, String> t = new TreeMap<>();
//        t.put("res", trText);
//        try {
////            String v = post(MT_URL, trText);
//
//            String v = khoatest(trText);
//
////            String v = WikiGet.post(GT_URL, p);
////            while (true) {
////                Matcher m = RE_UNICODE.matcher(v);
////                if (!m.find()) {
////                    break;
////                }
////                String g = m.group();
////                char c = (char) Integer.parseInt(m.group(1), 16);
////                v = v.replace(g, Character.toString(c));
////            }
////            v = v.replace("&quot;", "&#34;");
////            v = v.replace("&nbsp;", "&#160;");
////            v = v.replace("&amp;", "&#38;");
////            while (true) {
////                Matcher m = RE_HTML.matcher(v);
////                if (!m.find()) {
////                    break;
////                }
////                String g = m.group();
////                char c = (char) Integer.parseInt(m.group(1));
////                v = v.replace(g, Character.toString(c));
////            }
////
//            int beg = v.indexOf(MARK_BEG) + MARK_BEG.length();
//            int end = v.indexOf(MARK_END, beg);
//            tr = v.substring(beg, end);
////            System.out.println(tr);
//
//            // Attempt to clean spaces added by GT
//            // Spaces after
//            Matcher tag = PatternConsts.OMEGAT_TAG_SPACE.matcher(tr);
//            while (tag.find()) {
//                String searchTag = tag.group();
//                if (text.indexOf(searchTag) == -1) { // The tag didn't appear with a
//                    // trailing space in the source text
//                    String replacement = searchTag.substring(0, searchTag.length() - 1);
//                    tr = tr.replace(searchTag, replacement);
//                }
//            }
//
//            // Spaces before
//            tag = PatternConsts.SPACE_OMEGAT_TAG.matcher(tr);
//            while (tag.find()) {
//                String searchTag = tag.group();
//                if (text.indexOf(searchTag) == -1) { // The tag didn't appear with a
//                    // leading space in the source text
//                    String replacement = searchTag.substring(1, searchTag.length());
//                    tr = tr.replace(searchTag, replacement);
//                }
//            }
////            return tr;
//        } catch (Exception e) {
//            return e.getLocalizedMessage();
//        }
////        String v = WikiGet.post(GT_URL, p);
//        return tr;
        String result = post(trText);
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
        System.out.println(result);
        return result;
    }

    public static String post(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("res", text);

        try {
            return WikiGet.post(MT_URL, map);
//            return WikiGet.get(MT_URL, map, new HashMap<>());
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
