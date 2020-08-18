package com.fabric.warehouse.web;

import com.fabric.warehouse.data.Product;
import com.fabric.warehouse.data.RobotTask;
import com.fabric.warehouse.data.TaskType;
import com.fabric.warehouse.service.ProductsService;
import com.fabric.warehouse.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Service {

    private ProductsService productsService;
    private RobotService robotService;

    @Autowired
    public Service(ProductsService productsService, RobotService robotService) {
        this.productsService = productsService;
        this.robotService = robotService;
    }

    @PostMapping("/order")
    public void order(@RequestBody List<String> products) {
        System.out.println("Order : " + products.stream().collect(Collectors.joining(", ")));

        products.stream().forEach(product-> robotService.add(TaskType.pick_from_stock, product));
    }


    @PostMapping("/supply")
    public void supply(@RequestBody List<String> products) {
        System.out.println("Supply : " + products.stream().collect(Collectors.joining(", ")));

        products.stream().forEach(product-> robotService.add(TaskType.put_to_stock, product));
    }

    @PostMapping("/task/{id}/complete")
    public void complete(@PathVariable("id") Integer id) {
        robotService.complete(id);
    }

    @GetMapping("/next-tasks")
    public List<RobotTask> nextTasks() {
        List<RobotTask> nextTasks = robotService.getTasks();
 //       System.out.println("Next tasks: " + nextTasks.stream().flatMap(task-> task.toString()).collect(Collectors.joining("\n")));
        return nextTasks;
    }

    /*
    Array of Product objects:
[{
  name: <str>,
  amount: <int>
}]
     */
    @GetMapping("/stock")
    public Collection<Product> stock() {
        System.out.println("Stock");
        return productsService.getProducts();

    }


}
