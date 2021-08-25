import com.alibaba.fastjson.JSON;
import com.hongkai.utils.SqliteUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AcessTest {
    @Test
    public void test01(){
        try {
            SqliteUtils sqliteUtils =new SqliteUtils("D:/DataBaseForNiHeWan/Database.sqlite");
            sqliteUtils.openConnection();
            List<Map<String, Object>> list= sqliteUtils.queryMap("select * from account");
            System.out.println(JSON.toJSONString(list));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
