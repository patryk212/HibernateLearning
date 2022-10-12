package org.example.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class DAOUsageExample {
    public static void main(String[] args) {

        ProjectService projectService = new ProjectService(new ProjectDAODB());

        int ranId = (new Random()).nextInt(100);
        Project project = new Project("Project #" + ranId, new Date());

        projectService.persist(project);

        List<Project> projects = projectService.findAll();
        projects.stream().forEach(p-> System.out.println(p));

    }
}
