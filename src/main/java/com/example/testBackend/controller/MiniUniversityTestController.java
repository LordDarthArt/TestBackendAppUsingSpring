package com.example.testBackend.controller;

import java.sql.SQLException;

import com.example.testBackend.service.MiniUniversityService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import com.example.testBackend.entity.MiniUniversityEntity;
import com.example.testBackend.jsps.MiniUniversityCreateGroupJSP;
import com.example.testBackend.repository.MiniUniversityRepository;

import javax.sql.DataSource;

@RestController
public class MiniUniversityTestController {

	@Autowired
	private MiniUniversityService muService;
	private MiniUniversityRepository muRepo;
	private MiniUniversityCreateGroupJSP jsp;
	private DataSource dataSource;
	private JdbcTemplate jtm;

	public void setMUService(MiniUniversityService muService) {
		this.muService = muService;
	}

	public void setJtm(JdbcTemplate jtm) {
		this.jtm = jtm;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@GetMapping("/")
	public String home() {
		jsp = new MiniUniversityCreateGroupJSP();
		return jsp.jsp();
	}

	@GetMapping("/miniuniversity/getStuds")
	public JSONObject getMu() throws SQLException {
		JSONObject mu = muService.retrieveStudents();
		return mu;
	}

	@GetMapping("/miniuniversity/getStudById")
	public JSONObject getStudentById(@RequestParam(name="id", defaultValue = "0") Integer id) throws SQLException {
		return muService.getStudentById(id);
	}

	@RequestMapping("/miniuniversity/creategroup")
	public JSONObject createGroup(@RequestParam(name="name") String[] students,@RequestParam(name="age") String[] age,@RequestParam(name="group") String group) throws SQLException {
		JSONObject mu = muService.createGroup(students, age, group);
		return mu;
	}

	@GetMapping("/miniuniversity/groups")
	public JSONArray getTeachersGroup(@RequestParam(name="teacher", defaultValue = "") String teacher) throws SQLException {
		JSONArray mu = muService.getTeachersGroup(teacher);
		return mu;
	}

	@GetMapping("/miniuniversity/teachers")
	public JSONArray getStudentsTeacher(@RequestParam(name="stud", defaultValue = "") String stud) throws SQLException {
		JSONArray mu = muService.getStudentsTeacher(stud);
		return mu;
	}

	@RequestMapping("/miniuniversity/studsbygroup")
	public JSONArray getStudentsByGroup(@RequestParam(name="group", defaultValue = "") String group) throws SQLException {
		JSONArray mu = muService.getStudentsByGroup(group);
		return mu;
	}

	@RequestMapping("/miniuniversity/teachtogroup")
	public JSONObject setTeacherToGroup(@RequestParam(name="teacher", defaultValue = "") String teacher, @RequestParam(name="group", defaultValue = "") String group) throws SQLException {
		JSONObject mu = muService.setTeacherToGroup(teacher, group);
		return mu;
	}
}
