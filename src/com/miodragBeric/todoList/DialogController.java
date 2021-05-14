package com.miodragBeric.todoList;

import com.miodragBeric.todoList.datamodel.ToDoItem;
import com.miodragBeric.todoList.datamodel.TodoData;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadLinePicker;

    public ToDoItem processResults(){
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadLineValue = deadLinePicker.getValue();

        ToDoItem newItem = new ToDoItem(shortDescription,details,deadLineValue);
        TodoData.getInstance().addTodoItem(newItem);
        return  newItem;
    }





}
