package tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SearchServiceTest.class, 
				UserServiceCategoryTest.class,
				UserServiceRecommendationTest.class,
				CarServiceNotificationTest.class,
				ReservationServiceDiscountTest.class,
				PenaltyPercentageTest.class})
public class AllTests {

}
