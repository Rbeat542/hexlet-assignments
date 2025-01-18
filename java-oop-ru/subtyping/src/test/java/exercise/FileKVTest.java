package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

// BEGIN
import static org.assertj.core.api.Assertions.assertThat;
// END

class FileKVTest {
    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    void testRealization() {
        KeyValueStorage newStorage = new FileKV(filepath.toString(), Map.of("key11", "value11"));
        assertThat(newStorage.get("key11", "def")).isEqualTo("value11");
        assertThat(newStorage.get("key12", "def")).isEqualTo("def");
    }
    // END
}
