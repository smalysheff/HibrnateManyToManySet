package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.dao.impl.UserDaoImp;
import ru.sapteh.model.User;
import ru.sapteh.model.UserRole;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class MainController {

    //TableView
    private final ObservableList<User> userList = FXCollections.observableArrayList();
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, Date> regDateColumn;
    @FXML
    private TableColumn<User, Integer> countRoleColumn;
    @FXML
    private TableColumn<User, String> lastRegRoleColumn;

    //Bottom
    @FXML
    private Label countLabel;


    @FXML
    public void initialize(){

        findByAllUserToDataBase();


        idColumn.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getId()));
        lastNameColumn.setCellValueFactory(userStringCellDataFeatures ->
                new SimpleObjectProperty<>(userStringCellDataFeatures.getValue().getLastName()));

        firstNameColumn.setEditable(true);
        firstNameColumn.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue());
        });

//        firstNameColumn.getTableView().getItems().get(
//                new EventHandler<TableColumn.CellEditEvent<User, String>>() {
//                    @Override
//                    public void handle(TableColumn.CellEditEvent<User, String> event) {
//                        System.out.println(event.getTablePosition().getRow());
//                    }
//                });

        firstNameColumn.setCellValueFactory(p ->
                new SimpleObjectProperty<>(p.getValue().getName()));
        regDateColumn.setCellValueFactory(u ->
                new SimpleObjectProperty<>(
                        u.getValue().getUserRoles().stream()
                        .min(Comparator.comparing(UserRole::getRegistrationDate))
                        .get().getRegistrationDate()));
        countRoleColumn.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getUserRoles().size()));
//        lastRegRoleColumn.setCellValueFactory(u ->
//                new SimpleObjectProperty<>(
//                        u.getValue().getUserRoles().stream().findFirst().get().getRole().toString()));
        userTableView.setEditable(true);
        userTableView.setItems(userList);

        //Count users to database
        countLabel.setText(String.valueOf(userList.size()));
    }




    private void findByAllUserToDataBase(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<User, Integer> userDaoImp = new UserDaoImp(factory);

        userList.addAll(userDaoImp.findByAll());

        factory.close();
    }



}
