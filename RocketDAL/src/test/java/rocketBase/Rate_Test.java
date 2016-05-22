package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import rocketBase.RateDAL;
import org.junit.Test;
import rocketDomain.RateDomainModel;

public class Rate_Test {

	
	//TODO - RocketDAL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	
	@Test
	public void rate_test(){
		ArrayList<RateDomainModel> allRates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + allRates.size());
		assert(allRates.size() > 0);
		
		assertEquals(allRates.get(0).getdInterestRate(), 5.00, 0.01);
		assertEquals(allRates.get(1).getdInterestRate(), 4.50,  0.01);
		assertEquals(allRates.get(2).getdInterestRate(), 4.00,  0.01);
		assertEquals(allRates.get(3).getdInterestRate(), 3.75, 0.01);
		assertEquals(allRates.get(4).getdInterestRate(), 3.50,  0.01);	
		
	}
	//TODO - RocketDAL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	/*@Test 
	public void rate_exception_test(){
		boolean thrown = false;
		
		try{
			RateBLL.getRate(100);
		} catch (RateException r){
			thrown = true;
		}
		assertTrue(thrown);		
	}
	*/
	@Test
	public void test() {
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
		
		assert(1==1);
	}

}
