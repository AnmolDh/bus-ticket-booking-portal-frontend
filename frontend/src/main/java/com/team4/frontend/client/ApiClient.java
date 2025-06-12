package com.team4.frontend.client;

import com.team4.frontend.dto.request.*;
import com.team4.frontend.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ApiClient {

    private final WebClient webClient;

    private String buildUri(String path, int page, int size) {
        return UriComponentsBuilder.fromPath(path)
                .queryParam("page", page)
                .queryParam("size", size)
                .toUriString();
    }

    public ApiResponse<PagedResponse<BookingResponseDto>> getAllBookings(int page, int size) {
        return webClient.get()
                .uri(buildUri("/bookings", page, size))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PagedResponse<BookingResponseDto>>>() {})
                .block();
    }

    public ApiResponse<BookingResponseDto> getBookingById(Integer id) {
        return webClient.get()
                .uri("/bookings/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<BookingResponseDto>>() {})
                .block();
    }

    public ApiResponse<BookingResponseDto> createBooking(BookingRequestDto bookingRequestDto) {
        return webClient.post()
                .uri("/bookings")
                .bodyValue(bookingRequestDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<BookingResponseDto>>() {})
                .block();
    }


    public ApiResponse<BookingResponseDto> updateBooking(Integer id, BookingRequestDto bookingRequestDto) {
        return webClient.put()
                .uri("/bookings/" + id)
                .bodyValue(bookingRequestDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<BookingResponseDto>>() {})
                .block();
    }



    public ApiResponse<PagedResponse<TripResponseDto>> getAllTrips(int page, int size) {
        return webClient.get()
                .uri(buildUri("/trips", page, size))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PagedResponse<TripResponseDto>>>() {})
                .block();
    }

    public ApiResponse<TripResponseDto> getTripById(Integer id) {
        return webClient.get()
                .uri("/trips/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<TripResponseDto>>() {})
                .block();
    }

    public ApiResponse<TripResponseDto> createTrip(TripRequestDto tripRequestDto) {
        return webClient.post()
                .uri("/trips")
                .bodyValue(tripRequestDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<TripResponseDto>>() {})
                .block();
    }

    public ApiResponse<TripResponseDto> updateTrip(Integer id, TripRequestDto tripRequestDto) {
        return webClient.put()
                .uri("/trips/" + id)
                .bodyValue(tripRequestDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<TripResponseDto>>() {})
                .block();
    }

    public ApiResponse<PagedResponse<DriverResponseDto>> getAllDrivers(int page, int size) {
        return webClient.get()
                .uri(buildUri("/drivers", page, size))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PagedResponse<DriverResponseDto>>>() {})
                .block();
    }

    public ApiResponse<DriverResponseDto> getDriverById(Integer id) {
        return webClient.get()
                .uri("/drivers/id/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<DriverResponseDto>>() {})
                .block();
    }

    public ApiResponse<DriverResponseDto> createDriver(DriverRequestDto dto) {
        return webClient.post().uri("/drivers")
                .bodyValue(dto)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ApiResponse<DriverResponseDto>>() {})
                .block();
    }

    public ApiResponse<DriverResponseDto> updateDriver(Integer id, DriverRequestDto dto) {
        return webClient.put().uri("/drivers/" + id)
                .bodyValue(dto)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ApiResponse<DriverResponseDto>>() {})
                .block();
    }

    public ApiResponse<PagedResponse<BusResponseDto>> getAllBuses(int page, int size) {
        return webClient.get()
                .uri(buildUri("/buses", page, size))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PagedResponse<BusResponseDto>>>() {})
                .block();
    }

    public ApiResponse<BusResponseDto> getBusById(Integer id) {
        return webClient.get()
                .uri("/buses/id/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<BusResponseDto>>() {})
                .block();
    }

    public ApiResponse<BusResponseDto> createBus(BusRequestDto busRequestDto) {
        return webClient.post()
                .uri("/buses")
                .bodyValue(busRequestDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<BusResponseDto>>() {})
                .block();
    }

    public ApiResponse<BusResponseDto> updateBus(Integer id, BusRequestDto busRequestDto) {
        return webClient.put()
                .uri("/buses/" + id)
                .bodyValue(busRequestDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<BusResponseDto>>() {})
                .block();
    }


    public ApiResponse<PagedResponse<AgencyOfficeResponseDto>> getAllAgencyOffices(int page, int size) {
        return webClient.get()
                .uri(buildUri("/agencies/offices", page, size))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PagedResponse<AgencyOfficeResponseDto>>>() {})
                .block();
    }

    public ApiResponse<AgencyOfficeResponseDto> getAgencyOfficeById(Integer id) {
        return webClient.get()
                .uri("/agencies/offices/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<AgencyOfficeResponseDto>>() {})
                .block();
    }

    public ApiResponse<AgencyOfficeResponseDto> createOffice(AgencyOfficeRequestDto officeDto) {
        return webClient.post()
                .uri("/agencies/offices")
                .bodyValue(officeDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<AgencyOfficeResponseDto>>() {})
                .block();
    }

    // Update office
    public ApiResponse<AgencyOfficeResponseDto> updateOffice(Integer id, AgencyOfficeRequestDto officeDto) {
        return webClient.put()
                .uri("/agencies/offices/" + id)
                .bodyValue(officeDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<AgencyOfficeResponseDto>>() {})
                .block();
    }

    public ApiResponse<PagedResponse<CustomerResponseDto>> getAllCustomers(int page, int size) {
        return webClient.get()
                .uri(buildUri("/customers", page, size))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PagedResponse<CustomerResponseDto>>>() {})
                .block();
    }

    public ApiResponse<CustomerResponseDto> getCustomerById(Integer id) {
        return webClient.get()
                .uri("/customers/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<CustomerResponseDto>>() {})
                .block();
    }

    public ApiResponse<CustomerResponseDto> createCustomer(CustomerRequestDto dto) {
        return webClient.post()
                .uri("/customers")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<CustomerResponseDto>>() {})
                .block();
    }

    public ApiResponse<CustomerResponseDto> updateCustomer(Integer id, CustomerRequestDto dto) {
        return webClient.put()
                .uri("/customers/" + id)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<CustomerResponseDto>>() {})
                .block();
    }


    public ApiResponse<PagedResponse<PaymentResponseDto>> getAllPayments(int page, int size) {
        return webClient.get()
                .uri(buildUri("/payments", page, size))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PagedResponse<PaymentResponseDto>>>() {})
                .block();
    }

    public ApiResponse<PaymentResponseDto> getPaymentById(Integer id) {
        return webClient.get()
                .uri("/payments/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PaymentResponseDto>>() {})
                .block();
    }

    public ApiResponse<PaymentResponseDto> createPayment(PaymentRequestDto dto) {
        return webClient.post()
                .uri("/payments")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PaymentResponseDto>>() {})
                .block();
    }

    public ApiResponse<PaymentResponseDto> updatePayment(Integer id, PaymentRequestDto dto) {
        return webClient.put()
                .uri("/payments/" + id)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PaymentResponseDto>>() {})
                .block();
    }

    public ApiResponse<PagedResponse<RouteResponseDto>> getAllRoutes(int page, int size) {
        return webClient.get()
                .uri(buildUri("/routes", page, size))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PagedResponse<RouteResponseDto>>>() {})
                .block();
    }

    public ApiResponse<PagedResponse<AddressResponseDto>> getAllAddresses(int page, int size) {
        return webClient.get()
                .uri(buildUri("/routes", page, size))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PagedResponse<AddressResponseDto>>>() {})
                .block();
    }
}
