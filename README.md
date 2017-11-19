**Busy Flights API**

**Request**

| Name | Description |
| ------ | ------ |
| origin | 3 letter IATA code(eg. LHR, AMS) |
| destination | 3 letter IATA code(eg. LHR, AMS) |
| departureDate | ISO_LOCAL_DATE format |
| returnDate | ISO_LOCAL_DATE format |
| numberOfPassengers | Maximum 4 passengers |

**Response**

| Name | Description |
| ------ | ------ |
| airline | Name of Airline |
| supplier | Eg: CrazyAir or ToughJet |
| fare | Total price rounded to 2 decimals |
| departureAirportCode | 3 letter IATA code(eg. LHR, AMS) |
| destinationAirportCode | 3 letter IATA code(eg. LHR, AMS) |
| departureDate | ISO_DATE_TIME format |
| arrivalDate | ISO_DATE_TIME format |

The service connects to CrazyAir and ToughJet API's and aggregates their results.

**Stack**
Spring boot, java8, lombok, maven

**Notes about implementation**

Given domain models stayed as they are. As a first step kept them as given. 
For better implementation they can implement same interface and for the Date serialization/deserialization operations
custom classes might be implemented. Also mapping tool like Mapstruct could be useful request and response conversion.

Adding timeouts and retry operations should be configured for CrazyAir and ThoughJet service calls.

For better performance:
- CrazyAir and ThoughJet responses might be cached.
- Async FlightSearchService implementation
- Reactive FlightSearchService implementation also might be good
- Adding http2 support might be better for sending responses without waiting them all responses.

Besides 200 and 500 other HTTP return codes should be considered.
Best cases are tested. Project needs more test implementation.

