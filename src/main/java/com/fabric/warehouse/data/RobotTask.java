package com.fabric.warehouse.data;

/*
 id: <str>,
 action: <str>,
 product: <str>,
 location: int[2]
 */
public class RobotTask {
    int id;
    TaskType action;
    String product;
    int[] location;

    public RobotTask(int id, TaskType action, String product, int[] location) {
        this.id = id;
        this.action = action;
        this.product = product;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskType getAction() {
        return action;
    }

    public void setAction(TaskType action) {
        this.action = action;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }
}
