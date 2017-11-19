package com.travix.medusa.busyflights.validation;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateDepartureAndReturnDateValidator
    implements ConstraintValidator<ValidateDepartureAndReturnDate, Object> {

  @Override
  public void initialize(final ValidateDepartureAndReturnDate validateDepartureAndReturnDate) {
    log.debug("Departure and return date validatior initialized");
  }

  /**
   * Validates departure and return dates together.
   *
   * @param o
   * @param constraintValidatorContext
   * @return
   */
  @Override
  public boolean isValid(
      final Object o, final ConstraintValidatorContext constraintValidatorContext) {

    final BusyFlightsRequest req = (BusyFlightsRequest) o;

    final String departureDateStr = req.getDepartureDate();
    final String returnDateStr = req.getReturnDate();

    // All of the methods might be implemented as different annotations
    final boolean departureDateIsValid = isValidIsoLocalDate(departureDateStr);
    log.debug("{} departureDateIsValid: {}", departureDateStr, departureDateIsValid);

    final boolean returnDateIsValid = isValidIsoLocalDate(returnDateStr);
    log.debug("{} returnDateIsValid: {}", returnDateStr, returnDateIsValid);

    final boolean departureDateIsNotPast = isDateNotPast(departureDateStr);
    log.debug("{} departureDateIsNotPast: {}", departureDateStr, departureDateIsNotPast);

    final boolean returnDateIsNotPast = isDateNotPast(returnDateStr);
    log.debug("{} returnDateIsNotPast: {}", returnDateStr, returnDateIsNotPast);

    final boolean returnDateIsEqualOrAfter = isDateAfter(departureDateStr, returnDateStr);
    log.debug("{} {} returnDateIsEqualOrAfter: {}", departureDateStr, returnDateStr, returnDateIsEqualOrAfter);

    return departureDateIsValid
        && returnDateIsValid
        && departureDateIsNotPast
        && returnDateIsNotPast
        && returnDateIsEqualOrAfter;

  }

  private boolean isDateAfter(final String departureDateStr, final String returnDateStr) {
    final DateTimeFormatter isoLocalDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
    final LocalDate departureDate = LocalDate.parse(departureDateStr, isoLocalDateFormat);
    final LocalDate returnDate = LocalDate.parse(returnDateStr, isoLocalDateFormat);
    return departureDate.isBefore(returnDate);
  }

  private boolean isDateNotPast(final String dateStr) {
    final DateTimeFormatter isoLocalDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
    final LocalDate date = LocalDate.parse(dateStr, isoLocalDateFormat);
    return date.isAfter(LocalDate.now().minusDays(1L));
  }

  private boolean isValidIsoLocalDate(final String dateStr) {

    try {
      final DateTimeFormatter isoLocalDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
      LocalDate.parse(dateStr, isoLocalDateFormat);
      return true;
    } catch (Exception e) {
      log.error("{} is not valid", dateStr, e);
    }
    return false;
  }
}
