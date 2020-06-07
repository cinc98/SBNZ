package tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sbnz.integracija.example.model.User;

@RunWith(Suite.class)
@SuiteClasses({ SearchServiceTest.class, 
				UserServiceCategoryTest.class,
				UserServiceRecommendationTest.class })
public class AllTests {

}
