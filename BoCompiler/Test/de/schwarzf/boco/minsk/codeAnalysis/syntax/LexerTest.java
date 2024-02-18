package de.schwarzf.boco.minsk.codeAnalysis.syntax;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

        public static ArrayList<SyntaxKind> tokenKinds;
        public static ArrayList<String> tokenTexts;
        public static ArrayList<String> whitespaceTokenTexts;

        public static void addTokenListItem(SyntaxKind kind, String text) {
            tokenKinds.add(kind);
            tokenTexts.add(text);
        }

        public static void fillTokenList(){

            tokenKinds = new ArrayList<>();
            tokenTexts = new ArrayList<>();

            whitespaceTokenTexts = new ArrayList<>();

            // operators
            addTokenListItem(SyntaxKind.PLUS_TOKEN, "+");
            addTokenListItem(SyntaxKind.MINUS_TOKEN, "-");
            addTokenListItem(SyntaxKind.STAR_TOKEN, "*");
            addTokenListItem(SyntaxKind.SLASH_TOKEN, "/");
            addTokenListItem(SyntaxKind.BANG_TOKEN, "!");
            addTokenListItem(SyntaxKind.AMPERSAND_AMPERSAND_TOKEN, "&&");
            addTokenListItem(SyntaxKind.EQUALS_EQUALS_TOKEN, "==");
            addTokenListItem(SyntaxKind.BANG_EQUALS_TOKEN, "!=");
            addTokenListItem(SyntaxKind.EQUALS_TOKEN, "=");
            addTokenListItem(SyntaxKind.PIPE_PIPE_TOKEN, "||");

            // parentheses
            addTokenListItem(SyntaxKind.OPEN_PARENTHESIS_TOKEN, "(");
            addTokenListItem(SyntaxKind.CLOSE_PARENTHESIS_TOKEN, ")");

            // keywords
            addTokenListItem(SyntaxKind.TRUE_KEYWORD, "true");
            addTokenListItem(SyntaxKind.FALSE_KEYWORD, "false");

            // whitespace
            /*
            addTokenListItem(SyntaxKind.WHITESPACE, " ");
            addTokenListItem(SyntaxKind.WHITESPACE, "  ");
            addTokenListItem(SyntaxKind.WHITESPACE, "\r");
            addTokenListItem(SyntaxKind.WHITESPACE, "\n");
            addTokenListItem(SyntaxKind.WHITESPACE, "\r\n");
            */

            //numbers
            addTokenListItem(SyntaxKind.NUMBER_TOKEN, "1");
            addTokenListItem(SyntaxKind.NUMBER_TOKEN, "438795");

            // identifiers
            addTokenListItem(SyntaxKind.IDENTIFIER_TOKEN, "a");
            addTokenListItem(SyntaxKind.IDENTIFIER_TOKEN, "abc");

            //seperators
            whitespaceTokenTexts.add(" ");
            whitespaceTokenTexts.add("  ");
            whitespaceTokenTexts.add("\r");
            whitespaceTokenTexts.add("\n");
            whitespaceTokenTexts.add("\r\n");

        }

        @org.junit.jupiter.api.Test
        void lexerLexesTokens(){
            fillTokenList();

            for (int i = 0; i < tokenKinds.size(); i++) {
                lexerLexesToken(tokenKinds.get(i), tokenTexts.get(i));
            }

            // seperators
            for (int i = 0; i < whitespaceTokenTexts.size(); i++) {
                lexerLexesToken(SyntaxKind.WHITESPACE, whitespaceTokenTexts.get(i));
            }
        }
        void lexerLexesToken(SyntaxKind kind, String text) {
            ArrayList<SyntaxToken> tokens = (ArrayList<SyntaxToken>) SyntaxTree.parseTokens(text);
            var token = tokens.get(0);
            assertEquals(kind, token.getKind());
            assertEquals(text, token.getText());
        }

        @org.junit.jupiter.api.Test
        void lexerLexesTokenPairs(){
            fillTokenList();

            for (int i = 0; i < tokenKinds.size(); i++) {
                for (int j = 0; j < tokenKinds.size(); j++) {
                    if (!requriresSeperator(tokenKinds.get(i), tokenKinds.get(j))) {
                        lexerLexesTokenPair(tokenKinds.get(i), tokenTexts.get(i), tokenKinds.get(j), tokenTexts.get(j));
                    }
                }
            }
        }

        boolean requriresSeperator(SyntaxKind t1kind, SyntaxKind t2kind) {
            if (t1kind == SyntaxKind.IDENTIFIER_TOKEN && t2kind == SyntaxKind.IDENTIFIER_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.IDENTIFIER_TOKEN && t2kind == SyntaxKind.NUMBER_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.NUMBER_TOKEN && t2kind == SyntaxKind.IDENTIFIER_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.NUMBER_TOKEN && t2kind == SyntaxKind.NUMBER_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.BANG_TOKEN && t2kind == SyntaxKind.EQUALS_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.BANG_TOKEN && t2kind == SyntaxKind.EQUALS_EQUALS_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.EQUALS_TOKEN && t2kind == SyntaxKind.EQUALS_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.EQUALS_TOKEN && t2kind == SyntaxKind.EQUALS_EQUALS_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.TRUE_KEYWORD && t2kind == SyntaxKind.TRUE_KEYWORD) {
                return true;
            }
            if (t1kind == SyntaxKind.TRUE_KEYWORD && t2kind == SyntaxKind.FALSE_KEYWORD) {
                return true;
            }
            if (t1kind == SyntaxKind.FALSE_KEYWORD && t2kind == SyntaxKind.TRUE_KEYWORD) {
                return true;
            }
            if (t1kind == SyntaxKind.FALSE_KEYWORD && t2kind == SyntaxKind.FALSE_KEYWORD) {
                return true;
            }
            if (t1kind == SyntaxKind.TRUE_KEYWORD && t2kind == SyntaxKind.IDENTIFIER_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.FALSE_KEYWORD && t2kind == SyntaxKind.IDENTIFIER_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.TRUE_KEYWORD && t2kind == SyntaxKind.NUMBER_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.FALSE_KEYWORD && t2kind == SyntaxKind.NUMBER_TOKEN) {
                return true;
            }
            if (t1kind == SyntaxKind.NUMBER_TOKEN && t2kind == SyntaxKind.TRUE_KEYWORD) {
                return true;
            }
            if (t1kind == SyntaxKind.NUMBER_TOKEN && t2kind == SyntaxKind.FALSE_KEYWORD) {
                return true;
            }
            if (t1kind == SyntaxKind.IDENTIFIER_TOKEN && t2kind == SyntaxKind.TRUE_KEYWORD) {
                return true;
            }
            if (t1kind == SyntaxKind.IDENTIFIER_TOKEN && t2kind == SyntaxKind.FALSE_KEYWORD) {
                return true;
            }
            return false;
        }

        void lexerLexesTokenPair(SyntaxKind t1kind, String t1text, SyntaxKind t2kind, String t2text) {
            ArrayList<SyntaxToken> tokens = (ArrayList<SyntaxToken>) SyntaxTree.parseTokens(t1text + t2text);

            assertEquals(2, tokens.size());

            var token1 = tokens.get(0);
            assertEquals(t1kind, token1.getKind());
            assertEquals(t1text, token1.getText());

            var token2 = tokens.get(1);
            assertEquals(t2kind, token2.getKind());
            assertEquals(t2text, token2.getText());
        }

        // TODO implement e4 44m - test zwo tokens with separator in between

}