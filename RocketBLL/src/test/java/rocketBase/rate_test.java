package rocketBase;

import static org.junit.Assert.*;
import rocketBase.RateBLL;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	@Test
	public void rate_test_one() throws RateException{
		double InterestRate = RateBLL.getRate(700);
		assertTrue(InterestRate == 4);
	}
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void Rate_Exception_Test(){
		boolean thrown = false;
		
		try{
			RateBLL.getRate(100);
		} catch (RateException r){
			thrown = true;
		}
		assertTrue(thrown);		
	}
	@Test
	public void test() {
		assert(1==1);
	}

}
