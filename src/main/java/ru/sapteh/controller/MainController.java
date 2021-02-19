package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
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

import java.text.SimpleDateFormat;
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

    //RightPane
    @FXML
    private Label idLbl;
    @FXML
    private Label lastNameLbl;
    @FXML
    private Label firstNameLbl;
    @FXML
    private Label regDateLbl;
    @FXML
    private Label countRoleLbl;
    @FXML
    private Label rolesLbl;


    @FXML
    public void initialize(){

        findByAllUserToDataBase();

        idColumn.setCellValueFactory(u ->
                new SimpleObjectProperty<>(u.getValue().getId()));
        lastNameColumn.setCellValueFactory(userStringCellDataFeatures ->
                new SimpleObjectProperty<>(userStringCellDataFeatures.getValue().getLastName()));
        firstNameColumn.setCellValueFactory(p ->
                new SimpleObjectProperty<>(p.getValue().getName()));

        userTableView.setEditable(true);
        userTableView.setItems(userList);


        //Count users to database
        countLabel.setText(String.valueOf(userList.size()));

        //Listener tab User tableView
        showUserDetails(null);
        userTableView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, user, t1) -> showUserDetails(t1)
        );
    }

    private void findByAllUserToDataBase(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<User, Integer> userDaoImp = new UserDaoImp(factory);

        userList.addAll(userDaoImp.findByAll());

        factory.close();
    }

    private void showUserDetails(User user){


        if(user != null){
            //Initialize roles to user
            StringBuilder sb = new StringBuilder();
            for(UserRole role : user.getUserRoles()){
                sb.append(role.getRole().getName()).append(", ");
            }

            idLbl.setText(String.valueOf(user.getId()));
            lastNameLbl.setText(user.getLastName());
            firstNameLbl.setText(user.getName());
            regDateLbl.setText(
                    new SimpleDateFormat("dd.MM.yyyy").format(user.getUserRoles().stream()
                    .max(Comparator.comparing(UserRole::getRegistrationDate))
                    .get().getRegistrationDate())
            );
            countRoleLbl.setText(String.valueOf(
                    user.getUserRoles().size()
            ));
            rolesLbl.setText(sb.toString());

        } else {
            idLbl.setText("");
            lastNameLbl.setText("");
            firstNameLbl.setText("");
            regDateLbl.setText("");
            countRoleLbl.setText("");
            rolesLbl.setText("");
        }


    }



}
