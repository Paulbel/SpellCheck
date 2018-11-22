package com.vironit.language_tool.controller;

import com.vironit.language_tool.model.TextInput;
import com.vironit.language_tool.service.SpellCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api")
public class SpellCheckController {
    private final SpellCheckService checkService;

    @Autowired
    public SpellCheckController(SpellCheckService checkService) {
        this.checkService = checkService;
    }

    @PostMapping("/spell")
    public Object checkSpell(@RequestBody TextInput input) {
        return checkService.check(input);
    }

    @GetMapping("/spell")
    public String checkSpellName() {
        return "SpellChecker";
    }
}
