package ru.stqa.pft.soap;

import net.webservicex.GeoIPService;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;


public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("93.100.203.210");
    assertTrue(ipLocation.contains("RU"));
  }
  @Test
  public void testInvalidIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("93.100.203.xxx");
    assertTrue(ipLocation.contains("RU"));
  }

}