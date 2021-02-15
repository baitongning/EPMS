package dao;

import model.TbSchedule;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import tools.JDBCUtils;

import java.util.List;

/**
 * @author baito
 */
public class ScheduleDao {
	private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	public static List<TbSchedule> findAllSchedule() {
		try {
			String sql = "select * from tb_schedule";
			List<TbSchedule> tbSchedule = template.query(sql,new BeanPropertyRowMapper<TbSchedule>(TbSchedule.class));
			return tbSchedule;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static List<TbSchedule> findAllScheduleByName(String name) {
		try {
			String sql = "select * from tb_schedule where person_name like ?";
			List<TbSchedule> tbSchedule = template.query(sql,new BeanPropertyRowMapper<TbSchedule>(TbSchedule.class),name);
			return tbSchedule;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static TbSchedule findScheduleById(String id) {
		try {
			String sql = "select * from tb_schedule where person_id=?";
			TbSchedule tbSchedule = template.queryForObject(sql,new BeanPropertyRowMapper<TbSchedule>(TbSchedule.class),id);
			return tbSchedule;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int updateSchedule(TbSchedule tbSchedule) {
		int count = 0;
		try {
			String sql = "update tb_schedule set person_name=?,schedule_start=?,schedule_end=?,"
					+ "schedule_place=?,schedule_reason=? ,explains=? where person_id=?";
			count = template.update(sql,tbSchedule.getPerson_name(),tbSchedule.getSchedule_start(),tbSchedule.getSchedule_end(),
					tbSchedule.getSchedule_place(),tbSchedule.getSchedule_reason(),tbSchedule.getExplains(),tbSchedule.getPerson_id());
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}

	public static int addSchedule(TbSchedule tbSchedule) {
		int count = 0;
		try {
			String sql = "insert into tb_schedule(person_id,person_name,schedule_start,schedule_end,schedule_place,"
					+ "schedule_reason,explains) values(?,?,?,?,?,?,?)";
			count = template.update(sql,tbSchedule.getPerson_id(),tbSchedule.getPerson_name(),tbSchedule.getSchedule_start(),tbSchedule.getSchedule_end(),
					tbSchedule.getSchedule_place(),tbSchedule.getSchedule_reason(),tbSchedule.getExplains());
		} catch (DataAccessException e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}

	public static void deleteSchedule(String id) {
		try {
			String sql = "delete from tb_schedule where person_id=?";
			template.update(sql,id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
}
