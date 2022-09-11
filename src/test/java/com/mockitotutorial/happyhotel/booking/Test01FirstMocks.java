package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test01FirstMocks {

	private BookingService bookingService;
	private PaymentService paymentService;
	private RoomService roomService;
	private BookingDAO bookingDAO;
	private MailSender mailSender;

	@BeforeEach
	void setup() {
		this.paymentService = mock(PaymentService.class);
		this.roomService = mock(RoomService.class);
		this.bookingDAO = mock(BookingDAO.class);
		this.mailSender = mock(MailSender.class);
		this.bookingService = new BookingService(paymentService, roomService, bookingDAO, mailSender);
	}

	@Test
	void shouldCalculateCorrectPriceWhenCorrectInput() {
		// given
		BookingRequest bookingRequest = new BookingRequest(
			"1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), 2, false);
		double expected = 4 * 2 * 50.0;
		// when
		double actual = bookingService.calculatePrice(bookingRequest);
		// then
		assertEquals(expected, actual);
	}
	
	

}
