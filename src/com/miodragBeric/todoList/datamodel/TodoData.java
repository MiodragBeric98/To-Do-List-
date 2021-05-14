package com.miodragBeric.todoList.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {

    private static TodoData instance = new TodoData();
    private static String fileName = "TodoListItems.txt";

    private ObservableList<ToDoItem> toDoItems;
    private DateTimeFormatter formater;

    public static TodoData getInstance() {
        return instance;
    }

    private TodoData() {
        formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public ObservableList<ToDoItem> getToDoItems() {
        return toDoItems;
    }
    public List<ToDoItem> getTodoItems(){
        return toDoItems;
    }
    public void addTodoItem(ToDoItem item){
        toDoItems.add(item);
    }

    public void loadTodoItems() throws IOException {
        toDoItems = FXCollections.observableArrayList();//vidiljva lista nizova
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);

        String input;
        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formater);
                ToDoItem todoItem = new ToDoItem(shortDescription, details, date);
                toDoItems.add(todoItem);
            }

        } finally {
            if (br != null) {
                br.close();
            }
        }

    }


    public void storeTodoItems() throws IOException {

        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<ToDoItem> iter = toDoItems.iterator();
            while(iter.hasNext()){
                ToDoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadLine().format(formater)));
                bw.newLine(); //dodaje novu liniju u textFieldu
            }

        } finally {
            if(bw != null){
                bw.close();
            }
        }


    }
    public void deleteTodoItem(ToDoItem item){
        toDoItems.remove(item);
    }
}


