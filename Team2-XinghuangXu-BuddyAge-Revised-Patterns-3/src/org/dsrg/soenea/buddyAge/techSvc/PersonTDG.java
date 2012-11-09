package org.dsrg.soenea.buddyAge.techSvc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public abstract class PersonTDG {
	public static final String BASE_NAME = "Person";
	public static final String TABLE = DbRegistry.getTablePrefix()+BASE_NAME;
	public static final String INSERT = "INSERT INTO " + TABLE + " (id,version,name,age,buddy) VALUES (?,?,?,?,?);";
	public static final String UPDATE = "UPDATE " + TABLE + " AS p set p.version=p.version+1, p.name=?, p.age=?, p.buddy=? WHERE p.id=? AND p.version=?;";
	public static final String DELETE = "DELETE FROM " + TABLE + " WHERE id=? AND version=?;";
	
	public static final String SELECT_ALL = "SELECT p.id, p.version, p.name, p.age, p.buddy FROM " + TABLE + " AS p;";
	public static final String SELECT = "SELECT p.id, p.version, p.name, p.age, p.buddy FROM " + TABLE + " AS p WHERE p.id=?;";
	
	public static ResultSet findAll() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		return ps.executeQuery();
	}
	
	public static ResultSet find(long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ps.setLong(1, id);
		return ps.executeQuery();
	}
	
	public static void insert(long id, long version, String name, int age, Long buddy) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setString(3, name);
		ps.setInt(4, age);
		ps.setObject(5, buddy, java.sql.Types.BIGINT);
		ps.executeUpdate();
		ps.close();
	}

	public static int update(long id, long version, String name, int age, Long buddy) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		ps.setString(1, name);
		ps.setInt(2, age);
		ps.setObject(3, buddy, java.sql.Types.BIGINT);
		ps.setLong(4, id);
		ps.setLong(5, version);		
		int count = ps.executeUpdate();
		ps.close();
		return count;
	}

	public static int delete(long id, long version) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setLong(1, id);
		ps.setLong(2, version);
		int count = ps.executeUpdate();
		ps.close();
		return count;
	}

//	public static long maxId() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}
