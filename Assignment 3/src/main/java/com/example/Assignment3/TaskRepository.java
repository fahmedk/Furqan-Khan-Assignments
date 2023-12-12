package com.example.Assignment3;

import com.example.Assignment3.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class TaskRepository {
    private final String filePath = "tasks.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Task> tasks;

    public TaskRepository() {
        this.tasks = readFromFile();
    }

    public List<Task> findAll() {
        return tasks;
    }

    public Optional<Task> findById(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    public void save(Task task) {
        tasks.removeIf(t -> t.getId().equals(task.getId()));
        tasks.add(task);
        writeToFile();
    }

    public void deleteById(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
        writeToFile();
    }

    private List<Task> readFromFile() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void writeToFile() {
        try {
            objectMapper.writeValue(new File(filePath), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

