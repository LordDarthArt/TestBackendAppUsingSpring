package com.example.testBackend.service;

import java.sql.SQLException;

import com.example.testBackend.entity.MiniUniversityEntity;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public interface MiniUniversityService {
	public JSONObject retrieveStudents() throws SQLException;

	public JSONObject getStudentById(Integer id) throws SQLException;

	public JSONObject setTeacherToGroup(String group, String teacher) throws SQLException;

	public JSONObject createGroup(String[] students, String[] age, String group) throws SQLException;

	public JSONArray getTeachersGroup(String teacher) throws SQLException;

	public JSONArray getStudentsByGroup(String group) throws SQLException;

	public JSONArray getStudentsTeacher(String student) throws SQLException;
}