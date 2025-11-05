lexer grammar LilyLexer;

options {
    tokenVocab = Lilypond;
    superClass = LilyBaseLexer;
}

HEADER: BS 'header';

fragment BS: '\\';