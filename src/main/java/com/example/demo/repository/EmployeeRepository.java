package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    MongoClient mongoClient;

    private MongoClient getClient() {
        if (mongoClient == null) {
            mongoClient = new MongoClient("localhost", 27017);
        }
        return mongoClient;
    }

    public List<Employee> getAll() {
        MongoClient mongoClient = getClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
        MongoCollection<Document> employeeCollection = mongoDatabase.getCollection("employee");
        List<Employee> employees = new ArrayList<>();
        for (Document e : employeeCollection.find()) {
            Employee emp = new Employee(
                    e.get("_id").toString(),
                    e.getString("firstName"),
                    e.getString("lastName"),
                    e.getString("address"));
            employees.add(emp);
        }
        return employees;
    }

    public String save(Employee employee) {
        MongoClient mongoClient = getClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
        MongoCollection<Document> employeeCollection = mongoDatabase.getCollection("employee");

        Document emp = new Document();
        emp.append("firstName", employee.getFirstName());
        emp.append("lastName", employee.getLastName());
        emp.append("address", employee.getAddress());

        String response;
        try {
            employeeCollection.insertOne(emp);
            response = "Success";
        } catch (Exception e) {
            response = "Error";
        }

        return response;
    }

    public String update(Employee employee) {
        MongoClient mongoClient = getClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
        MongoCollection<Document> employeeCollection = mongoDatabase.getCollection("employee");

        Document emp = new Document();
        emp.append("firstName", employee.getFirstName());
        emp.append("lastName", employee.getLastName());
        emp.append("address", employee.getAddress());

        String response;
        try {
            BasicDBObject filter = new BasicDBObject("_id", new ObjectId(employee.getId()));
            employeeCollection.updateOne(filter, new BasicDBObject("$set", emp));
            response = "Success";
        } catch (Exception e) {
            response = "Error";
        }

        return response;
    }

    public String delete(String id) {
        MongoClient mongoClient = getClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
        MongoCollection<Document> employeeCollection = mongoDatabase.getCollection("employee");

        String response;
        try {
            BasicDBObject filter = new BasicDBObject("_id", new ObjectId(id));
            employeeCollection.deleteOne(filter);
            response = "Success";
        } catch (Exception e) {
            response = "Error";
        }

        return response;
    }
}
