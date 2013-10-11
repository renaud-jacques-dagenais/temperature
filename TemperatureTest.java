/** 
 * @author Aditya Mahajan <aditya.mahajan@mcgill.ca>
 * @version 2013.10.06
 * Unit Testing Temperature class
 */

import org.junit.* ;

import static org.junit.Assert.* ;

public class TemperatureTest {
	
	private static final double threshold = 1e-6;

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
		 *     4) A random high temperature (between 1000000 and Double.MAX_VALUE)
		 */
		
		double randomLowTemperatureRef, randomHighTemperatureRef;
		Temperature temperatureBelowMinimum;
		Temperature minimumTemperature;
		Temperature randomLowTemperature;
		Temperature randomHighTemperature;
		
		// Celsius
		
		randomLowTemperatureRef  = generateRandomNumber(-273.15, 1000000);
		randomHighTemperatureRef = generateRandomNumber(1000000, Double.MAX_VALUE);
		
		temperatureBelowMinimum = new Temperature(-274.15, Temperature.Units.CELSIUS);
		minimumTemperature      = new Temperature(-273.15, Temperature.Units.CELSIUS);
		randomLowTemperature    = new Temperature(randomLowTemperatureRef,  Temperature.Units.CELSIUS);
		randomHighTemperature   = new Temperature(randomHighTemperatureRef, Temperature.Units.CELSIUS);
		
		assertFalse("Temperature in Celsius cannot be smaller than -273.15", temperatureBelowMinimum.getValue() < -273.15);
		assertTrue("Minimum Celsius temperature should be -273.15",  minimumTemperature.getValue() == -273.15);
		assertEquals("Celsius random low temperature is incorrect",  randomLowTemperatureRef,  randomLowTemperature.getValue(),  threshold);
		assertEquals("Celsius random high temperature is incorrect", randomHighTemperatureRef, randomHighTemperature.getValue(), threshold);
		
		// Fahrenheit
		
		randomLowTemperatureRef  = generateRandomNumber(-459.67, 1000000);
		randomHighTemperatureRef = generateRandomNumber(1000000, Double.MAX_VALUE);
		
		temperatureBelowMinimum = new Temperature(-460.67, Temperature.Units.FAHRENHEIT);
		minimumTemperature      = new Temperature(-459.67, Temperature.Units.FAHRENHEIT);
		randomLowTemperature    = new Temperature(randomLowTemperatureRef,  Temperature.Units.FAHRENHEIT);
		randomHighTemperature   = new Temperature(randomHighTemperatureRef, Temperature.Units.FAHRENHEIT);
		
		assertFalse("Temperature in Fahrenheit cannot be smaller than -459.67", temperatureBelowMinimum.getValue() < -459.67);
		assertTrue("Minimum Fahrenheit temperature should be -459.67",  minimumTemperature.getValue() == -459.67);
		assertEquals("Fahrenheit random low temperature is incorrect",  randomLowTemperatureRef,  randomLowTemperature.getValue(),  threshold);
		assertEquals("Fahrenheit random high temperature is incorrect", randomHighTemperatureRef, randomHighTemperature.getValue(), threshold);
		
		// Kelvin
		
		randomLowTemperatureRef  = generateRandomNumber(0,       1000000);
		randomHighTemperatureRef = generateRandomNumber(1000000, Double.MAX_VALUE);
		
		temperatureBelowMinimum = new Temperature(-1, Temperature.Units.KELVIN);
		minimumTemperature      = new Temperature( 0, Temperature.Units.KELVIN);
		randomLowTemperature    = new Temperature(randomLowTemperatureRef,  Temperature.Units.KELVIN);
		randomHighTemperature   = new Temperature(randomHighTemperatureRef, Temperature.Units.KELVIN);
		
		assertFalse("Temperature in Kelvib cannot be smaller than 0", temperatureBelowMinimum.getValue() < 0);
		assertTrue("Minimum Kelvin temperature should be 0",  minimumTemperature.getValue() == 0);
		assertEquals("Kelvin random low temperature is incorrect",  randomLowTemperatureRef,  randomLowTemperature.getValue(),  threshold);
		assertEquals("Kelvin random high temperature is incorrect", randomHighTemperatureRef, randomHighTemperature.getValue(), threshold);
	}
	
	@Test
	public void testChangeUnits() {
		/*
		 * This method verifies that changing the units from one scale to the others
		 * works correctly. For each scale, it checks if the conversion returns the 
		 * correct value for:
		 *     1) The minimum temperature
		 */
		
		Temperature minimumTemperature;
		
		/*
		 * Celsius
		 */
		
		// Celsius -> Fahrenheit
		
		minimumTemperature = new Temperature(-273.15, Temperature.Units.CELSIUS);
		minimumTemperature.changeUnits(Temperature.Units.FAHRENHEIT);
		assertEquals("Conversion of minimum temperature from Celsius to Fahrenheit failed", -459.67, minimumTemperature.getValue(), threshold);
		
		// Celsius -> Kelvin
		
		minimumTemperature = new Temperature(-273.15, Temperature.Units.CELSIUS);
		minimumTemperature.changeUnits(Temperature.Units.KELVIN);
		assertEquals("Conversion of minimum temperature from Celsius to Kelvin failed", 0, minimumTemperature.getValue(), threshold);
		
		/*
		 * Fahrenheit
		 */
		
		// Fahrenheit -> Celsius
		
		minimumTemperature = new Temperature(-459.67, Temperature.Units.FAHRENHEIT);
		minimumTemperature.changeUnits(Temperature.Units.CELSIUS);
		assertEquals("Conversion of minimum temperature from Fahrenheit to Celsius failed", -273.15, minimumTemperature.getValue(), threshold);
		
		// Fahrenheit -> Kelvin
		
		minimumTemperature = new Temperature(-459.67, Temperature.Units.FAHRENHEIT);
		minimumTemperature.changeUnits(Temperature.Units.KELVIN);
		assertEquals("Conversion of minimum temperature from Fahrenheit to Kelvin failed", 0, minimumTemperature.getValue(), threshold);
		
		/*
		 * Kelvin
		 */
		
		// Kelvin -> Celsius
		
		minimumTemperature = new Temperature(0, Temperature.Units.KELVIN);
		minimumTemperature.changeUnits(Temperature.Units.CELSIUS);
		assertEquals("Conversion of minimum temperature from Kelvin to Celsius failed", -273.15, minimumTemperature.getValue(), threshold);
		
		// Kelvin -> Fahrenheit
		
		minimumTemperature = new Temperature(0, Temperature.Units.KELVIN);
		minimumTemperature.changeUnits(Temperature.Units.FAHRENHEIT);
		assertEquals("Conversion of minimum temperature from Kelvin to Fahrenheit failed", -459.67, minimumTemperature.getValue(), threshold);
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
