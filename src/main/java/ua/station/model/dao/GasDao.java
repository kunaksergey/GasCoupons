package ua.station.model.dao;

import ua.station.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sa on 04.11.17.
 */
@Service
public class GasDao {
    private static final String CREATE = "";
    private static final String SELECT_ALL = "SELECT *from gas";
    private static final String SELECT_ONE = "SELECT *from gas where id=?";
    private static final String DELETE = "DELETE from gas where id=?";
    private static final String UPDATE = "UPDATE gas SET name=? where id=?";
    private static final String INSERT = "INSERT INTO gas (name) values(?)";
    @Autowired
    private Environment env;



    public List<Product> findAll() {

        List<Product> gasList = new ArrayList<>();
        try (Connection conn = getConnection()) {

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL);

            while (rs.next()) {
                gasList.add(new Product(rs.getInt("id"),rs.getString("name")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gasList;
    }

    public Product findOne(Integer id) {
        Product gas = new Product();

        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SELECT_ONE);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            gas = new Product(rs.getInt("id"),rs.getString("name"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gas;
    }

    public void deleteById(int id) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product update(int id, String name) {
           try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, name);
            ps.setInt(2, id);
            int newId = ps.executeUpdate();
            return findOne(newId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product create(String name) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, name);
            int id = ps.executeUpdate();
            return findOne(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(env.getProperty("db.url"),
                env.getProperty("db.user"), env.getProperty("db.password"));
    }

}
