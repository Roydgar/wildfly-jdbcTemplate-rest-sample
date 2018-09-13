package tk.roydgar.wildfly.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tk.roydgar.wildfly.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    //for DDL queries we use execute() method
    //for insert/update/delete we use update() method
    //for select we use query() or queryForObject() method

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findById(Long userId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?"       //query
                    , new Object[] {userId}                                                 //query params
                    , new BeanPropertyRowMapper<>(User.class)));        //maps result set to the object with given type
                                                                        //in case if pojo fields and column names
                                                                        //match (different cases and underscores are ok)

        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public User save(User user) {
        jdbcTemplate.update("INSERT INTO user (name, phone) VALUES (?, ?)", user.getName(), user.getPhone());

        user.setId(jdbcTemplate.queryForObject("SELECT @@identity", Long.class));
        return user;
    }

    public void saveAll(List<User> users) {
        jdbcTemplate.batchUpdate("INSERT INTO user (name, phone) VALUES (?, ?)", new BatchPreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, users.get(i).getName());
                ps.setString(2, users.get(i).getPhone());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }

        });
    }

    public Optional<User> update(User user) {
        if (!findById(user.getId()).isPresent()) {
            return Optional.empty();
        }

        jdbcTemplate.update("UPDATE user SET name = ?, phone = ? WHERE id = ?"
                , user.getName(), user.getPhone(), user.getId());

        return Optional.of(user);
    }

    public Optional<User> deleteById(Long userId) {
        Optional<User> user = findById(userId);

        if (user.isPresent()) {
            jdbcTemplate.update("DELETE FROM user WHERE id = ?", userId);
        }
        return user;
    }

}
