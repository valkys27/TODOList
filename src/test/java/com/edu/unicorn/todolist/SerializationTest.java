package com.edu.unicorn.todolist;

import com.edu.unicorn.todolist.data.entity.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;

import java.io.*;
import java.nio.file.*;

import static org.assertj.core.api.Assertions.*;

public class SerializationTest {

    private static final String JSON_FILE_PATH = "src/test/resources/test.json";

    private ObjectMapper objectMapper;
    private Todo first;

    @Before
    public void intit() throws IOException {
        first = new Todo(30L, "main task", true);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void serializationTest() throws IOException {
        StringWriter actual = new StringWriter();
        objectMapper.writeValue(actual, first);
        String expected = String.join("", Files.readAllLines(Paths.get(JSON_FILE_PATH)));
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    public void deserializationTest() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(JSON_FILE_PATH));
        Todo second = objectMapper.readValue(data, Todo.class);
        assertThat(second).isEqualTo(first);
    }
}
