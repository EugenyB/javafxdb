package main.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import main.data.Student;

import java.util.List;

public class MainController {

    private static EntityManager em;

    public TableView<Student> table;
    public TableColumn<Student, Integer> idColumn;
    public TableColumn<Student, String> nameColumn;
    public TableColumn<Student, Integer> ageColumn;
    public Spinner<Integer> ageSpinner;
    public TextField nameField;


    public void initialize() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javafxdb");
        em = emf.createEntityManager();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        updateTable();
        clearFields();
    }

    private void updateTable() {
        List<Student> students = em.createNamedQuery("Student.findAll", Student.class).getResultList();
        table.setItems(FXCollections.observableList(students));
    }


    public void addStudent() {
        em.getTransaction().begin();
        Student student = new Student();
        student.setName(nameField.getText());
        student.setAge(ageSpinner.getValue());
        em.persist(student);
        em.getTransaction().commit();
        updateTable();
        clearFields();
    }

    private void clearFields() {
        nameField.clear();
        ageSpinner.valueFactoryProperty().setValue(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100));
    }
}
