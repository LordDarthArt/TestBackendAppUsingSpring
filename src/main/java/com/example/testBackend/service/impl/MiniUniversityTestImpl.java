package com.example.testBackend.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.testBackend.fill.FillTheEmpty;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.testBackend.entity.MiniUniversityEntity;
import com.example.testBackend.repository.MiniUniversityRepository;
import com.example.testBackend.service.MiniUniversityService;

import javax.sql.DataSource;

@Service
public class MiniUniversityTestImpl implements MiniUniversityService {
	
	private MiniUniversityRepository muRepository;
	private JdbcTemplate jtm = new JdbcTemplate();

	private JSONObject jsonObject = new JSONObject();
	private JSONArray jsonArray = new JSONArray();

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jtm = new JdbcTemplate(dataSource);
	}

	@Autowired
	public void setMuRepository(MiniUniversityRepository muRepository) {
		this.muRepository = muRepository;
	}

	public void setJtm(JdbcTemplate jtm) {
		this.jtm = jtm;
	}

	public MiniUniversityRepository getMuRepository() {
		return muRepository;
	}

	@Override
	public JSONObject retrieveStudents() throws SQLException {
		String sql = "select DISTINCT NAME, AGE from miniuniversity";
		new FillTheEmpty().furfill(jtm);
		jsonObject.put("STUDENTS", jtm.query(sql, new ColumnMapRowMapper()));
		return jsonObject;
	}

	@Override
	public JSONObject getStudentById(Integer id) throws SQLException {
		new FillTheEmpty().furfill(jtm);
		String sql = "SELECT DISTINCT NAME FROM MINIUNIVERSITY WHERE ID=?";
		jsonObject.put("NAME", jtm.query(sql, new Object[] {id}, new ColumnMapRowMapper()).get(0).get("name"));
		return jsonObject;
	}

	@Override
	public JSONObject setTeacherToGroup(String teacher, String group) throws SQLException {
		jsonObject.clear();
		new FillTheEmpty().furfill(jtm);
		String sqlFirst = "UPDATE MINIUNIVERSITY SET TEACHER=? WHERE GROUPS=?";
		jtm.update(sqlFirst, new Object[] { teacher, group });
		String sqlSecond = "SELECT DISTINCT TEACHER, GROUPS FROM MINIUNIVERSITY WHERE TEACHER=? AND GROUPS=?";
		jsonObject.put("TEACHER", (jtm.query(sqlSecond, new Object[] { teacher, group }, new ColumnMapRowMapper())).get(0).get("TEACHER"));
		jsonObject.put("GROUP", (jtm.query(sqlSecond, new Object[] { teacher, group }, new ColumnMapRowMapper())).get(0).get("GROUPS"));
		return jsonObject;
	}

	@Override
	public JSONObject createGroup(String[] students, String[] age, String group) {
		jsonArray.clear();
		String sqlFirst = "INSERT INTO MINIUNIVERSITY (name, age, groups) VALUES (?, ?, ?)";
		JSONObject createdGroup = new JSONObject();
		HashMap<String, String> hashMap = new HashMap<>();
		if (students.length==age.length) {
			for (int i=0; i<students.length; i++) {
				jtm.update(sqlFirst, new Object[]{students[i], age[i], group});
			}
			String sqlSecond = "SELECT NAME, AGE, GROUPS FROM MINIUNIVERSITY WHERE NAME=? AND AGE=? AND GROUPS=?";
			for (int i=0; i<students.length; i++) {
				JSONObject jsonka = new JSONObject();
				jsonka.put("NAME", (jtm.query(sqlSecond, new Object[]{students[i], age[i], group}, new ColumnMapRowMapper()).get(0).get("NAME")));
				jsonka.put("AGE", (jtm.query(sqlSecond, new Object[]{students[i], age[i], group}, new ColumnMapRowMapper()).get(0).get("AGE")));
				jsonArray.add(i, jsonka);
			}
			createdGroup.put("GROUP",group);
			createdGroup.put("STUDENTS", jsonArray);
		} else {
			createdGroup.put("error", "incorrect request");
		}
		return createdGroup;
	}

	@Override
	public JSONArray getTeachersGroup(String teacher) throws SQLException {
		jsonArray.clear();
		String sql = "SELECT DISTINCT GROUPS FROM MINIUNIVERSITY WHERE TEACHER=? ORDER BY GROUPS";
		new FillTheEmpty().furfill(jtm);
		jtm.query(sql, new Object[] { teacher }, new ColumnMapRowMapper());
		for (int i=0; i<(jtm.query(sql, new Object[]{teacher}, new ColumnMapRowMapper()).size()); i++) {
			JSONObject jsonObjectd = new JSONObject();
			jsonObjectd.put("GROUP", (jtm.query(sql, new Object[]{teacher}, new ColumnMapRowMapper()).get(i).get("GROUPS")));
			jsonArray.add(i, jsonObjectd);
		}
		return jsonArray;
	}

	@Override
	public JSONArray getStudentsByGroup(String group) throws SQLException {
		jsonArray.clear();
		String sql = "SELECT DISTINCT NAME, AGE FROM MINIUNIVERSITY WHERE GROUPS=? ORDER BY NAME";
		new FillTheEmpty().furfill(jtm);
		for (int i=0; i<(jtm.query(sql, new Object[]{group}, new ColumnMapRowMapper()).size()); i++) {
			JSONObject jsonObjectd = new JSONObject();
			jsonObjectd.put("NAME", String.valueOf(jtm.query(sql, new Object[]{group}, new ColumnMapRowMapper()).get(i).get("NAME")));
			jsonObjectd.put("AGE", (int)jtm.query(sql, new Object[]{group}, new ColumnMapRowMapper()).get(i).get("AGE"));
			jsonArray.add(i, jsonObjectd);
		}
		return jsonArray;
	}

	@Override
	public JSONArray getStudentsTeacher(String student) throws SQLException {
		jsonArray.clear();
		String sql = "SELECT DISTINCT TEACHER FROM MINIUNIVERSITY WHERE NAME=?";
		new FillTheEmpty().furfill(jtm);
		ArrayList<String> teachers = new ArrayList<>();
		for (int i=0; i<(jtm.query(sql, new Object[]{student}, new ColumnMapRowMapper()).size()); i++) {
			JSONObject jsonObjecd = new JSONObject();
			teachers.add(String.valueOf(jtm.query(sql, new Object[]{student}, new ColumnMapRowMapper()).get(i).get("TEACHER")));
			jsonObjecd.put("TEACHER", teachers.get(i));
			jsonArray.add(i, jsonObjecd);
		}
		return jsonArray;
	}
}
