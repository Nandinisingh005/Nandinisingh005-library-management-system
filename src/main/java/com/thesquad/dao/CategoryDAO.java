package com.thesquad.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.thesquad.models.CategoryModel;
import com.thesquad.connection.DBConnection;

public class CategoryDAO {

    public CategoryDAO() {

    }

    public void create(CategoryModel category, DBConnection connection) {
        String sql = "INSERT INTO categoria(nome) values(?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, category.getName());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CategoryModel category, DBConnection connection) {
        String sql = "UPDATE categoria SET nome = ? WHERE pk_categoria = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getCategoryId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int categoryId, DBConnection connection) {
        String sql = "DELETE FROM categoria WHERE pk_categoria = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, categoryId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CategoryModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM categoria";

        List<CategoryModel> categoryList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                CategoryModel category = new CategoryModel();
                category.setCategoryId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());

                categoryList.add(category);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public CategoryModel getCategoryById(int categoryId, DBConnection connection) {
        String sql = "SELECT * FROM categoria WHERE pk_categoria = ?";

        try {
            CategoryModel category = new CategoryModel();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, categoryId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                category.setCategoryId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return category;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
