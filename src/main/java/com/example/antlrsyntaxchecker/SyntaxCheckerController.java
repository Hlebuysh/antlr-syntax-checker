package com.example.antlrsyntaxchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/syntax")
public class SyntaxCheckerController {

    private final SyntaxCheckerService syntaxCheckerService;

    @Autowired
    public SyntaxCheckerController(SyntaxCheckerService syntaxCheckerService) {
        this.syntaxCheckerService = syntaxCheckerService;
    }

    @PostMapping("/check")
    public Map<String, Object> checkSyntax(@RequestBody Map<String, String> request) {
        String javaCode = request.get("code");
        List<String> errors = syntaxCheckerService.checkSyntax(javaCode);
        return Map.of("errors", errors, "isValid", errors.isEmpty());
    }
}