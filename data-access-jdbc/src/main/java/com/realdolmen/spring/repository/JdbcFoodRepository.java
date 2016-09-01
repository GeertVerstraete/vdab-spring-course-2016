package com.realdolmen.spring.repository;

import com.realdolmen.spring.domain.Animal;
import com.realdolmen.spring.domain.Food;
import com.realdolmen.spring.domain.MeatyFood;
import com.realdolmen.spring.domain.VegiFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcFoodRepository implements FoodRepository {

    @Autowired
    private JdbcTemplate jt;

    private RowMapper<Food> frm = (rs, i) -> {
        Food f = null;
        String name = rs.getString("name");
        int calories = rs.getInt("calories");
        int foodId = rs.getInt("id");
        if (rs.getString("food_class").equals("MeatyFood")) {
            MeatyFood.Type type = MeatyFood.Type.valueOf(rs.getString("Meaty_food_type"));
            f = new MeatyFood(foodId, name, calories, type);
        } else {
            boolean fruit = rs.getBoolean("fruit");
            boolean vegetables = rs.getBoolean("vegetables");
            f = new VegiFood(foodId, name, calories, fruit, vegetables);

        }
        return f;

    };

    @Override
    public Food findFoodById(int id) {
        return jt.queryForObject("SELECT * FROM food where id=?",frm, id);
    };


    @Override
    public List<Food> findAllFood() {
        return jt.query("select * from food", frm);
    }

    @Override
    public List<Food> findFoodForAnimalType(Animal.Type type) {
        return jt.query("SELECT food.* FROM animal_diet inner join food on animal_diet.food_id=food.id where animal_diet.animal_type=?",frm, type);
    }

    @Override
    public void addFoodForAnimalType(Animal.Type animalType, Food food) {

    }

    public JdbcFoodRepository() {
    }

    @Override
    public void removeFood(Food food) {

    }
    // TODO: Inject a reference to your JdbcTemplate

    // TODO: Implement findAllFood() and create a RowMapper to make this easier!

    // TODO: Implement findFoodById() and reuse your RowMapper

    // TODO: Implement findFoodForAnimalType()

    // TODO: Implement removeFood()
}
