package app.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instanse = new Model();

    private List<User> model;

    private Model() {
        model = new ArrayList<>();
    }

    public static Model getInstance() {
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
