package com.fabric.warehouse.service;

import com.fabric.warehouse.data.RobotTask;
import com.fabric.warehouse.data.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RobotService {

    private static List<RobotTask> nextTasks = new ArrayList<>();
    private HashMap<Integer,RobotTask> working = new HashMap<>();
    private static int nextId = 1;

    private ProductsService productsService;

    @Autowired
    public RobotService(ProductsService productsService) {
        this.productsService = productsService;
    }

    public void add(TaskType type, String productName) {
        RobotTask task = new RobotTask(nextId++, type, productName, productsService.getLocation(productName));
        nextTasks.add(task);
    }

    public List<RobotTask> getTasks() {
        List<RobotTask> result = new ArrayList<>();
        result.addAll(nextTasks);
        nextTasks.clear();

        result.stream().forEach(task -> working.put(task.getId(), task));

        return result;
    }

    public void complete(int id) {
        RobotTask task = working.remove(id);

        System.out.println("Task complete : " +id );


        if (task != null) {
            switch (task.getAction()) {
                case put_to_stock:
                    productsService.add(task.getProduct());
                    break;
                case pick_from_stock:
                    productsService.get(task.getProduct());
                    break;
            }
        } else {
            throw new IllegalArgumentException("Invalid task id " + id);
        }
    }
}
