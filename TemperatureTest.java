/** 
 * @author Aditya Mahajan <aditya.mahajan@mcgill.ca>
 * @version 2013.10.06
 * Unit Testing Temperature class
 */

import org.junit.* ;

import static org.junit.Assert.* ;

public class TemperatureTest {

	@Test
	public void testGetUnits() {
		Temperature celciusTemp    = new Temperature(0, Temperature.Units.CELSIUS);
		Temperature fahrenheitTemp = new Temperature(0, Temperature.Units.FAHRENHEIT);
		Temperature kelvinTemp     = new Temperature(0, Temperature.Units.KELVIN);
	   
		// Check if the right units are returned
		assertSame("Units should be Celsius",    Temperature.Units.CELSIUS,    celciusTemp.getUnits());
		assertSame("Units should be Fahrenheit", Temperature.Units.FAHRENHEIT, fahrenheitTemp.getUnits());
		assertSame("Units should be Kelvin",     Temperature.Units.KELVIN,     kelvinTemp.getUnits());
	 } 
	
	@Test
	public void testGetValue() {
		/*
		 * For each temperature scale, this method checks the returned value for:
		 *     1) A temperature below the scale's minimum
		 *     2) The minimum temperature
		 *     3) A random low temperature (between the scale's minimum and 1000000)
		 */
		
		double randomLowTemperatureRef, threshold = 1e-6;
		Temperature temperatureBelowMinimum;
		Temperature minimumTemperature;
		Temperature randomLowTemperature;
		
    // Celsius
		
		randomLowTemperatureRef  = generateRandomNumber(-273.15, 1000000);
		
		temperatureBelowMinimum = new Temperature(-274.15, Temperature.Units.CELSIUS);
		minimumTemperature      = new Temperature(-273.15, Temperature.Units.CELSIUS);
		randomLowTemperature    = new Temperature(randomLowTemperatureRef, Temperature.Units.CELSIUS);
		
		assertFalse("Temperature in Celcius cannot be smaller than -273.15", temperatureBelowMinimum.getValue() < -273.15);
		assertTrue("Minimum Celcius temperature should be -273.15", minimumTemperature.getValue() == -273.15);
		assertEquals("Celsius random low temperature is incorrect", randomLowTemperatureRef, randomLowTemperature.getValue(), threshold);
		
		// Fahrenheit
		
		randomLowTemperatureRef  = generateRandomNumber(-459.67, 1000000);
		
		temperatureBelowMinimum = new Temperature(-460.67, Temperature.Units.FAHRENHEIT);
		minimumTemperature      = new Temperature(-459.67, Temperature.Units.FAHRENHEIT);
		randomLowTemperature    = new Temperature(randomLowTemperatureRef,  Temperature.Units.FAHRENHEIT);
		
		assertFalse("Temperature in Fahrenheit cannot be smaller than -459.67", temperatureBelowMinimum.getValue() < -459.67);
		assertTrue("Minimum Fahrenheit temperature should be -459.67", minimumTemperature.getValue() == -459.67);
		assertEquals("Fahrenheit random low temperature is incorrect", randomLowTemperatureRef, randomLowTemperature.getValue(), threshold);
		
		// Kelvin
		
		randomLowTemperatureRef  = generateRandomNumber(0, 1000000);
		
		temperatureBelowMinimum = new Temperature(-1, Temperature.Units.KELVIN);
		minimumTemperature      = new Temperature( 0, Temperature.Units.KELVIN);
		randomLowTemperature    = new Temperature(randomLowTemperatureRef, Temperature.Units.KELVIN);
		
		assertFalse("Temperature in Kelvib cannot be smaller than 0", temperatureBelowMinimum.getValue() < 0);
		assertTrue("Minimum Kelvin temperature should be 0", minimumTemperature.getValue() == 0);
		assertEquals("Kelvin random low temperature is incorrect", randomLowTemperatureRef, randomLowTemperature.getValue(), threshold);
	}
	
	/**
	 * Generate a random number between [min, max]
	 * @param min
	 * @param max
	 * @return
	 */
	private double generateRandomNumber(double min, double max) {
		return (min + Math.random() * ((max - min) + 1));
	}
}
