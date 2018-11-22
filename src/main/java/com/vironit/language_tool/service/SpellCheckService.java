package com.vironit.language_tool.service;

import com.vironit.language_tool.model.Answer;
import com.vironit.language_tool.model.TextInput;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.Languages;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class SpellCheckService {

    public Answer check(TextInput input) {
        Language language = Languages.getLanguageForLocale(Locale.forLanguageTag(input.getLanguage()));
        JLanguageTool langTool = new JLanguageTool(language);
        langTool.setMaxErrorsPerWordRate(2);
        langTool.setListUnknownWords(true);

        Answer answer = new Answer();
        String[] resultArray = new String[input.getText().length];
        for (int index = 0; index < input.getText().length; index++) {
            resultArray[index] = input.getText()[index];
            String message = input.getText()[index];
            String result = message;
            try {
                //System.out.println(Languages.get().stream().map(x->x.getName()+"->"+x.getLocale().getLanguage()).collect(Collectors.toList()));

                List<RuleMatch> matches = langTool.check(message);
                for (RuleMatch match : matches) {
                    String error = message.substring(match.getFromPos(), match.getToPos());
                    List<String> replacements = match.getSuggestedReplacements();
                    if (replacements.size() > 0) {
                        result = result.replace(error, match.getSuggestedReplacements().get(0));
                    } else {
                        result = result.replace(error, "");
                    }
                }
                resultArray[index] = result;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
        answer.setResult(resultArray);
        return answer;
    }
}
