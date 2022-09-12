package com.mockitotutorial.happyhotel.booking;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test08Spies {

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = spy(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);
        this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void shouldMakeBookingWhenInputOk() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1),
            LocalDate.of(2020, 1, 5), 2, true);
        // when
        String bookingId = bookingService.makeBooking(bookingRequest);
        // then
        verify(bookingDAOMock).save(bookingRequest);
        System.out.println("bookingId=" + bookingId);
    }

    @Test
    void cancelBookingWhenInputOk() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 1, 1),
            LocalDate.of(2020, 1, 5), 2, true);
        bookingRequest.setRoomId("1.3");
        String bookingId = "1";
        // when
        doReturn(bookingRequest).when(bookingDAOMock).get(bookingId);
        bookingService.cancelBooking(bookingId);
        // then
    }

}
