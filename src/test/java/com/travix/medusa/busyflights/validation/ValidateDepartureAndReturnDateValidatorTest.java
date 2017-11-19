package com.travix.medusa.busyflights.validation;

import static org.mockito.Mockito.*;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import javax.validation.ConstraintValidatorContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ValidateDepartureAndReturnDateValidatorTest {

  @InjectMocks
  ValidateDepartureAndReturnDateValidator validateDepartureAndReturnDateValidator;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }


  @Test
  public void testIsValid() throws Exception {
    String departureDate = "2018-11-18";
    String returnDate = "2018-11-19";
    final BusyFlightsRequest mockRequest = mock(BusyFlightsRequest.class);
    final ConstraintValidatorContext mockContext = mock(ConstraintValidatorContext.class);
    doReturn(departureDate).when(mockRequest).getDepartureDate();
    doReturn(returnDate).when(mockRequest).getReturnDate();

    boolean result = validateDepartureAndReturnDateValidator.isValid(mockRequest, mockContext);
    Assert.assertEquals(true, result);
  }
}
