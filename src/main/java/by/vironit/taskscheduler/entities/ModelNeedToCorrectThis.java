package by.vironit.taskscheduler.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelNeedToCorrectThis {
    private static ModelNeedToCorrectThis instanse = new ModelNeedToCorrectThis();

    private List<User> model;

    private ModelNeedToCorrectThis() {
        model = new ArrayList<>();
    }

    public static ModelNeedToCorrectThis getInstance() {
        return instanse;
    }

    public void add(User user) {
        model.add(user);
    }

    public List<String> list() {
        return model.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}
