package com.szczepanski.taskapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    private Task task = new Task(1,"Name", "Status");

    @Test
    public void getIdTest() {
        assertEquals(1,task.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals("Name" ,task.getName());
    }

    @Test
    public void getStatusTest() {
        assertEquals("Status" ,task.getStatus());
    }

    @Test
    public void setStatusTest() {
        task.setStatus("Status 2");
        assertEquals("Status 2",task.getStatus());
    }

    @Test
    public void equalsTest() {
    }
}