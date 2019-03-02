package com.chainsys.coursemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chainsys.coursemanagement.connectionutil.ConnectionUtil;
import com.chainsys.coursemanagement.model.EmployeeTopic;
import com.chainsys.coursemanagement.model.Status;
import com.chainsys.coursemanagement.model.Topic;

public class EmployeeTopicStatusDAO {

	
	public int addEmployeeTopicStatus(EmployeeTopic employeeTopic) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtil.getConnection();
		String query="INSERT INTO employee_topic(id,emp_id,topics_id,status_id) VALUES(employee_topic_id_seq.nextval,?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setInt(1, employeeTopic.getEmployee().getId());
		preparedStatement.setInt(2, employeeTopic.getTopic().getId());
		preparedStatement.setInt(3, employeeTopic.getStatus().getId());
		int noOfTopicStatusAdded=preparedStatement.executeUpdate();
		ConnectionUtil.closeConnection(connection, preparedStatement, null);
		return noOfTopicStatusAdded;
		
	}
	
	public int updateEmployeeTopicStatus(EmployeeTopic employeeTopic) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtil.getConnection();
		String query="UPDATE employee_topic set status_id=? WHERE topics_id=? AND emp_id=? ";
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setInt(1, employeeTopic.getStatus().getId());
		preparedStatement.setInt(2, employeeTopic.getTopic().getId());		
		preparedStatement.setInt(3, employeeTopic.getEmployee().getId());
		int noOfTopicStatusUpdated=preparedStatement.executeUpdate();
		ConnectionUtil.closeConnection(connection, preparedStatement, null);
		return noOfTopicStatusUpdated;
		
	}
	
	public EmployeeTopic selectStatusIdByTopicId(Topic topic) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtil.getConnection();
		String query="SELECT status_id from employee_topic WHERE topics_id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setInt(1, topic.getId());		
		ResultSet resultSet=preparedStatement.executeQuery();
		EmployeeTopic employeeTopic=new EmployeeTopic();
		while(resultSet.next())
		{
			Status status=new Status();
			status.setId(resultSet.getInt("status_id"));
			employeeTopic.setStatus(status);
		}
		ConnectionUtil.closeConnection(connection, preparedStatement, resultSet);
		return employeeTopic;
	}
	
	
}
