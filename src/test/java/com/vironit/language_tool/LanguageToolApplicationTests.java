package com.vironit.language_tool;

import com.vironit.language_tool.model.TextInput;
import com.vironit.language_tool.service.SpellCheckService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageToolApplicationTests {
    @Autowired
    SpellCheckService spellCheckService;

    @Test
    public void contextLoads() {
        TextInput textInput = new TextInput();
        textInput.setLanguage("us");
        textInput.setText(new String[]{"hllo wrld", "huppy to se you"});
        System.out.println(Arrays.toString(spellCheckService.check(textInput).getResult()));
    }

}
