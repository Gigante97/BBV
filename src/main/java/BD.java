import java.sql.*;

public class BD {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;
    static final String DB_URL = "jdbc:phoenix:hbase03.prod01.lp.crpt.tech";



    public static void connect(){
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
            System.out.println("Connecting to database");
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Create statement");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void disconnect(){
        try {
            connection.close();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static void checkCis(String[] array) throws SQLException {
        int count=0;
        preparedStatement = connection.prepareStatement("Select \"c\",\"st\" from TBL_JTI_TRACE_CIS where \"c\" = ?");
        for (int i =0; i<array.length; i++){
            try {
                preparedStatement.setString(1,array[i]);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    count++;
                    System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } System.out.println("Всего найдено " + count);

    }

    public static void checkCisHitory(String[] array) throws SQLException {
        preparedStatement = connection.prepareStatement("Select \"c\",\"did\" from TBL_JTI_TRACE_CIS_HISTORY where \"c\" = ? and \"t\"=6");
        for (int i =0; i<array.length; i++){
            try {
                preparedStatement.setString(1,array[i]);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){

                    System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
