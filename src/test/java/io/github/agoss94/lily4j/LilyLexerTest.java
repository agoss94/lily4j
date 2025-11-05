package io.github.agoss94.lily4j;

import io.github.airbag.Airbag;
import io.github.airbag.symbol.Symbol;
import io.github.airbag.symbol.SymbolProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LilyLexerTest {

    private static final Path RESOURCES = Paths.get(System.getProperty("user.dir"), "src", "test", "resources");
    private Airbag airbag;
    private SymbolProvider provider;

    @BeforeEach
    void setup() {
        airbag = Airbag.testLexer("io.github.agoss94.lily4j.LilyLexer");
        provider = airbag.getSymbolProvider();
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testfiles.csv")
    void testLilypondLexer(String fileBasename) throws IOException {
        String actualFileContent = Files.readString(RESOURCES.resolve("ly/%s.ly".formatted(fileBasename)));
        String expectedFileContent = Files.readString(RESOURCES.resolve("tokens/%s.token".formatted(fileBasename)));
        List<Symbol> expected = provider.fromSpec(expectedFileContent);
        List<Symbol> actual = provider.fromInput(actualFileContent);
        airbag.assertSymbolList(expected, actual);
    }


}