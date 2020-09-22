package com.example.testBackend.service;

import java.sql.SQLException;

import com.example.testBackend.entity.MiniUniversityEntity;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public interface MiniUniversityService {
	JSONObject retrieveStudents() throws SQLException;
	JSONObject getStudentById(Integer id) throws SQLException;
	JSONObject setTeacherToGroup(String group, String teacher) throws SQLException;
	JSONObject createGroup(String[] students, String[] age, String group) throws SQLException;
	JSONArray getTeachersGroup(String teacher) throws SQLException;
	JSONArray getStudentsByGroup(String group) throws SQLException;
	JSONArray getStudentsTeacher(String student) throws SQLException;
	JSONArray getAll() throws SQLException;
}