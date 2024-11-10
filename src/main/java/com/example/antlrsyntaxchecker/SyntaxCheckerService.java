package com.example.antlrsyntaxchecker;

import com.example.antlrsintaxchecker.JavaLexer;
import com.example.antlrsintaxchecker.JavaParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Service;
import org.antlr.v4.runtime.*;


import java.util.List;

@Service
public class SyntaxCheckerService {
    public List<String> checkSyntax(String javaCode) {
        SyntaxErrorListener errorListener = new SyntaxErrorListener();

        CharStream input = CharStreams.fromString(javaCode);
        JavaLexer lexer = new JavaLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        parser.compilationUnit();

        return errorListener.getErrors();
    }
}