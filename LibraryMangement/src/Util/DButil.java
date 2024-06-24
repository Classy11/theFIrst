//接口文件
package Util;
import java.sql.*;

public class DButil {
    private static final String url="jdbc:sqlserver://localhost:1433;DatabaseName=ZZPLibrary;encrypt=false";
    private static final String username="sa";
    private static final String password="123456";
    //连接数据库
    public static Connection getconnection (){
        Connection connection=null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    /*
    关闭连接，释放资源
    对查询操作执行
     */
    public static void Clsoe(ResultSet rs,Statement stmt,Connection connection){
        {
            try {
                if (rs != null)
                    rs.close();
                if(stmt!=null)
                    stmt.close();
                if(connection!=null||connection.isClosed())
                    connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    /*
    关闭连接，释放资源
    对增删改操作执行
     */

    public static void Close(Statement stmt,Connection connection){
        try{
            if(stmt!=null)
                stmt.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
