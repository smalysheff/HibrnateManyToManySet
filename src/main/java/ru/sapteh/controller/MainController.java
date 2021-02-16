package ru.sapteh.controller;

import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.dao.UserDaoImp;
import ru.sapteh.model.Role;
import ru.sapteh.model.User;

import java.util.Date;
import java.util.List;

public class MainController {

    private ObservableList<User> userList = FXCollections.observableArrayList();

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
//
    @FXML
    private TableColumn<User, String> lastRegRoleColumn;

    @FXML
    public void initialize(){

        findByAllUserToDataBase();


        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<User, Integer> userIntegerCellDataFeatures) {

                return (ObservableValue<Integer>) new SimpleObjectProperty(userIntegerCellDataFeatures.getValue().getId());
            }
        });

        lastNameColumn.setCellValueFactory(userStringCellDataFeatures ->
                new SimpleObjectProperty<>(userStringCellDataFeatures.getValue().getLastName()));

        firstNameColumn.setCellValueFactory(p ->
                new SimpleObjectProperty<>(p.getValue().getName()));

        regDateColumn.setCellValueFactory(u ->
                new SimpleObjectProperty<>(
                        u.getValue().getUserRoles().iterator().next().getRegistrationDate()));

        countRoleColumn.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getUserRoles().size()));

//        lastRegRoleColumn.setCellValueFactory(u ->
//                new SimpleObjectProperty<>(
//                        u.getValue().getUserRoles().iterator().next().getRole()));


        userTableView.setItems(userList);
    }


    private void findByAllUserToDataBase(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<User, Integer> userDaoImp = new UserDaoImp(factory);

        userList.addAll(userDaoImp.findByAll());

        factory.close();
    }



}
