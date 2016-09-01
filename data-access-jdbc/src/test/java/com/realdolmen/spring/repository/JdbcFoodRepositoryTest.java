package com.realdolmen.spring.repository;

import com.realdolmen.spring.TestConfig;
import com.realdolmen.spring.ZooConfig;
import com.realdolmen.spring.domain.Animal;
import com.realdolmen.spring.domain.Food;
import com.realdolmen.spring.domain.VegiFood;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ZooConfig.class})
@ActiveProfiles("test")
public class JdbcFoodRepositoryTest {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private DataSource dataSource;

    @Before
    public void verifyThatWeAreUsingTheRightDataSource() {
        assertTrue("The test environment should use an embedded database instead of the production datasource", dataSource instanceof EmbeddedDatabase);
    }

    @Test
    public void findAllFoodReturnsAllFoods() throws Exception {
        List<Food> foods = foodRepository.findAllFood();
        assertEquals("Food repository does not return the right number of foods", 6, foods.size());
    }

    @Test
    public void findFoodByIdReturnsUniqueFood() throws Exception {
        assertEquals("Steak", foodRepository.findFoodById(2).getName());
        assertEquals(277, foodRepository.findFoodById(2).getCalories());
        assertEquals(2, foodRepository.findFoodById(2).getId().intValue());
    }

    @Test
    public void findFoodForAnimalTypeReturnsOnlyFoodsForSaidAnimal() throws Exception {
        List<Food> foods = foodRepository.findFoodForAnimalType(Animal.Type.elephant);
        assertEquals("Elephant should eat three foods", 3, foods.size());
        assertEquals("Apple", foods.get(0).getName());
        assertEquals("Banana", foods.get(1).getName());
        assertEquals("Carrot", foods.get(2).getName());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void removeFoodDeletesFoodFromTheDatabase() throws Exception {
        Food food = foodRepository.findFoodById(1);
        foodRepository.removeFood(food);
        foodRepository.findFoodById(1);
    }

    @Test
    public void addFoodForAnimalTypeAddsTheRightFood() throws Exception {
        foodRepository.addFoodForAnimalType(Animal.Type.tiger, new VegiFood(100, "Caesar salad", 100, false, true));
        assertEquals(4, foodRepository.findFoodForAnimalType(Animal.Type.tiger).size());
    }
}
