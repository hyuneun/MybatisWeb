package pack.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.mybatis.SqlMapConfig;

public class ProcessDao {
	private static ProcessDao dao = new ProcessDao();
	
	
	public static ProcessDao getInstance(){
		return dao;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public List selectdataAll() throws SQLException{
		SqlSession sqlSession = factory.openSession();
		List list = sqlSession.selectList("selectDataAll"); //dataMapper의id를 읽음
		sqlSession.close();
		return list;
	}
	
	public DataDto selectDataPart(String arg) throws SQLException{
		SqlSession sqlSession = factory.openSession();
		DataDto dto = sqlSession.selectOne("selectDataById",arg);
		sqlSession.close();
		return dto;
		
	}
	
	public void insertData(DataDto dto) throws SQLException{
		SqlSession sqlSession = factory.openSession(); //수동(커밋필요)
		//SqlSession sqlSession = factory.openSession(true); //오토커밋
		int re = sqlSession.insert("insertData",dto);
		//System.out.println("asdasdasdasd" + re);
		sqlSession.commit(); 					//sqlSession.rollback();		
		sqlSession.close();
	}
	
	public void updateData(DataDto dto) throws SQLException{
		SqlSession sqlSession = factory.openSession(true);
		sqlSession.update("updateData",dto); 					
		sqlSession.close();
	}
	
	public boolean deleteData(String arg){
		SqlSession sqlSession = factory.openSession();
		boolean b = false;
		try {
			int cou = sqlSession.delete("deleteData",arg);
			if(cou > 0)
				b = true;
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			System.out.println(e);
			sqlSession.rollback();//이렇게짜는것을 권장
		}finally {
			if(sqlSession != null) sqlSession.close();
		}
		return b;
	}
	
	
}
