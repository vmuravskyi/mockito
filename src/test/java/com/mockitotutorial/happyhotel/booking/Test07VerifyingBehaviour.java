package com.mockitotutorial.happyhotel.booking;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test07VerifyingBehaviour {

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);
        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void shouldInvokePaymentWhenPrepaid() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1),
            LocalDate.of(2020, 1, 5), 2, true);
        // when
        bookingService.makeBooking(bookingRequest);
        // then
        verify(paymentServiceMock, times(1)).pay(bookingRequest, 400.0);
        verifyNoMoreInteractions(paymentServiceMock);
    }

    @Test
    void shouldNotInvokePaymentWhenNotPrepaid() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1),
            LocalDate.of(2020, 1, 5), 2, false);
        // when
        bookingService.makeBooking(bookingRequest);
        // then
        verify(paymentServiceMock, never()).pay(any(), anyDouble());
    }

}
