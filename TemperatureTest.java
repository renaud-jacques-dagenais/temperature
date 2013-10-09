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
		assertSame("Units should be Celcius",    Temperature.Units.CELSIUS,    celciusTemp.getUnits());
		assertSame("Units should be Fahrenheit", Temperature.Units.FAHRENHEIT, fahrenheitTemp.getUnits());
		assertSame("Units should be Kelvin",     Temperature.Units.KELVIN,     kelvinTemp.getUnits());
	 } 
}
