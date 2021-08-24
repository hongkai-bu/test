import com.hongkai.utils.SqliteUtils;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AcessTest {
    @Test
    public void test01(){
        try {
            SqliteUtils sqliteUtils =new SqliteUtils("D:\\DataBaseForNiHeWan\\Database.sqlite");
            sqliteUtils.openConnection();
            ResultSet rs= sqliteUtils.query("select * from account");
            ResultSetMetaData rsmd=rs.getMetaData();
            while(rs.next()){
                for(int i=1;i<rsmd.getColumnCount();i++){
                    System.out.println(rs.getObject(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
