package kcsj.dao.Impl;

import kcsj.SearchCriteria;
import kcsj.dao.mas_reportDAO;
import kcsj.domain.mas_book;
import kcsj.domain.mas_report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class mas_reportDAOImpl extends DAOBase implements mas_reportDAO {
    private static final String MAS_REPORT_INSERT_SQL = "insert into mas_report (report_no, " +
            "report_name, report_type, report_unit, report_date, report_con) \n" +
            "values(?, ?, ?, ?, ?, ?)";
    @Override
    public void addmas_report(mas_report mas_report_add, String no, String pwd) {
        Connection con = null;
        try{
            con = getConnection(no, pwd);//建立连接
            PreparedStatement psmt = con.prepareStatement(MAS_REPORT_INSERT_SQL);//获取SQL模板语句
            //按问号的个数依次填入所需字段信息
            psmt.setString(1,mas_report_add.getReport_no());
            psmt.setString(2,mas_report_add.getReport_name());
            psmt.setString(3,mas_report_add.getReport_type());
            psmt.setString(4,mas_report_add.getReport_unit());
            psmt.setString(5,mas_report_add.getReport_date());
            psmt.setInt(6,mas_report_add.getReport_con());
            //psmt.setBytes(7,mas_report_add.getReport_identity());
            int rs;
            rs=psmt.executeUpdate();
            System.out.println("成功插入"+rs+"行");
            psmt.close();//关闭接口
        }catch(Exception e){//出错提示
            e.printStackTrace();
        }finally {
            try{
                con.close();//关闭连接
            }catch (Exception e){//出错提示
                e.printStackTrace();
            }
        }
    }

    private static final String MAS_REPORT_UPDATE_SQL = "update mas_report set report_first=? where report_no = ?;";
    private static final String MAS_REPORT_UPDATE_SQL2 = "update mas_report set report_last=? where report_no = ?;";

    @Override
    public void updatemas_report(mas_report mas_report_update, String no, String pwd,int i) {
        Connection con = null;
        try{
            con = getConnection(no, pwd);//建立连接
            if(i==1)
            {
                PreparedStatement psmt = con.prepareStatement(MAS_REPORT_UPDATE_SQL);//获取SQL模板语句
                //按问号的个数依次填入所需字段信息
                psmt.setString(1,mas_report_update.getReport_first());
                psmt.setString(2,mas_report_update.getReport_no());
                psmt.executeUpdate();//执行SQL语句相应功能
                psmt.close();//关闭接口
            }
            else {
                PreparedStatement psmt = con.prepareStatement(MAS_REPORT_UPDATE_SQL2);//获取SQL模板语
                psmt.setString(1,mas_report_update.getReport_second());
                psmt.setString(2,mas_report_update.getReport_no());
                psmt.executeUpdate();//执行SQL语句相应功能
                psmt.close();//关闭接口
            }
        }catch(Exception e){//出错提示
            e.printStackTrace();
        }finally {
            try{
                con.close();//关闭连接
            }catch (Exception e){//出错提示
                e.printStackTrace();
            }
        }
    }

    private static final String MAS_REPORT_DELETE_SQL = "delete from mas_report\n" +
            "where mas_report.report_no = ?";
    @Override
    public void deletemas_report(String report_no, String no, String pwd) {
        Connection con = null;
        try{
            con = getConnection(no, pwd);//建立连接
            PreparedStatement psmt = con.prepareStatement(MAS_REPORT_DELETE_SQL);//获取SQL模板语句
            //按问号的个数依次填入所需字段信息
            psmt.setString(1,report_no);
            psmt.executeUpdate();//执行SQL语句相应功能
            psmt.close();//关闭接口
        }catch(Exception e){//出错提示
            e.printStackTrace();
        }finally {
            try{
                con.close();//关闭连接
            }catch (Exception e){//出错提示
                e.printStackTrace();
            }
        }
    }

    private static final String MAS_REPORT_SELECT_SQL = "select * from mas_report\n" +
            "where mas_report.report_no = ?";
    @Override
    public mas_report getmas_report(String report_no, String no, String pwd) {
        mas_report mas_report_select = new mas_report();
        Connection con = null;
        try {
            con = getConnection(no, pwd);
            PreparedStatement psmt = con.prepareStatement(MAS_REPORT_SELECT_SQL);
            psmt.setString(1,report_no);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                mas_report_select.setReport_no(rs.getString("report_no"));
                mas_report_select.setReport_name(rs.getString("report_name"));
                mas_report_select.setReport_type(rs.getString("report_type"));
                mas_report_select.setReport_unit(rs.getString("report_unit"));
                mas_report_select.setReport_date(rs.getString("report_date"));
                mas_report_select.setReport_con(rs.getInt("report_con"));
                mas_report_select.setReport_identity(rs.getBytes("report_identity"));
                mas_report_select.setReport_first(rs.getString("report_first"));
                mas_report_select.setReport_second(rs.getString("report_last"));
            }
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return mas_report_select;
    }

    @Override
    public List<mas_book> findmas_report(SearchCriteria searchCriteria) {
        return null;
    }
}
