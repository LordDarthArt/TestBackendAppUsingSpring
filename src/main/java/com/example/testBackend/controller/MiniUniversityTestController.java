package com.example.testBackend.controller;

import com.example.testBackend.jsps.MiniUniversityCreateGroupJSP;
import com.example.testBackend.repository.MiniUniversityRepository;
import com.example.testBackend.service.MiniUniversityService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
public class MiniUniversityTestController {

    private MiniUniversityService muService;
    private MiniUniversityRepository muRepo;
    private DataSource dataSource;
    private JdbcTemplate jtm;

    @Autowired
    public void setMUService(MiniUniversityService muService) {
        this.muService = muService;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public JdbcTemplate getJtm() {
        return jtm;
    }

    public void setJtm(JdbcTemplate jtm) {
        this.jtm = jtm;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/")
    public String home() {
        MiniUniversityCreateGroupJSP jsp = new MiniUniversityCreateGroupJSP();
        return jsp.jsp();
    }

    @GetMapping("/miniuniversity/getStuds")
    public JSONObject getMu() throws SQLException {
        return muService.retrieveStudents();
    }

    @GetMapping("/miniuniversity/getStudById")
    public JSONObject getStudentById(
            @RequestParam(name = "id", defaultValue = "0") Integer id
    ) throws SQLException {
        return muService.getStudentById(id);
    }

    @RequestMapping("/miniuniversity/creategroup")
    public JSONObject createGroup(
            @RequestParam(name = "name") String[] students,
            @RequestParam(name = "age") String[] age,
            @RequestParam(name = "group") String group
    ) throws SQLException {
        return muService.createGroup(students, age, group);
    }

    @GetMapping("/miniuniversity/groups")
    public JSONArray getTeachersGroup(
            @RequestParam(name = "teacher", defaultValue = "") String teacher
    ) throws SQLException {
        return muService.getTeachersGroup(teacher);
    }

    @GetMapping("/miniuniversity/teachers")
    public JSONArray getStudentsTeacher(
            @RequestParam(name = "stud", defaultValue = "") String stud
    ) throws SQLException {
        return muService.getStudentsTeacher(stud);
    }

    @RequestMapping("/miniuniversity/studsbygroup")
    public JSONArray getStudentsByGroup(
            @RequestParam(name = "group", defaultValue = "") String group
    ) throws SQLException {
        return muService.getStudentsByGroup(group);
    }

    @RequestMapping("/miniuniversity/teachtogroup")
    public JSONObject setTeacherToGroup(
            @RequestParam(name = "teacher", defaultValue = "") String teacher,
            @RequestParam(name = "group", defaultValue = "") String group
    ) throws SQLException {
        return muService.setTeacherToGroup(teacher, group);
    }
}
