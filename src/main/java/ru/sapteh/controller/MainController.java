package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.User;
import ru.sapteh.dao.UserDaoImp;

import java.util.Date;

public class MainController {

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
//
    @FXML
    private TableColumn<User, String> lastRegRoleColumn;

    @FXML
    public void initialize(){

        findByAllUserToDataBase();


        idColumn.setCellValueFactory(u ->
                        new SimpleObjectProperty<>(u.getValue().getId()));

        lastNameColumn.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getLastName()));

        firstNameColumn.setCellValueFactory(p ->
                new SimpleObjectProperty<>(p.getValue().getName()));

        countRoleColumn.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getUserRoles().size()));

//        regDateColumn.setCellValueFactory(u ->
//                new SimpleObjectProperty<>(u.getValue().getUserRoles().iterator().next().getRegistrationDate()));
//



//
//        lastRegRoleColumn.setCellValueFactory(u ->
//                new SimpleObjectProperty<>(u.getValue().getUserRoles().iterator().next().getRole()));







        userTableView.setItems(userList);
    }


    private void findByAllUserToDataBase(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<User, Integer> userDaoImp = new UserDaoImp(factory);

        userList.addAll(userDaoImp.findByAll());

        factory.close();
    }



}
