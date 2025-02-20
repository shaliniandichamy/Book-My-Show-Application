package com.application.demo.Book_my_show.service;

import com.application.demo.Book_my_show.convertors.TicketConvertor;
import com.application.demo.Book_my_show.entity.ShowEntity;
import com.application.demo.Book_my_show.entity.ShowSeatEntity;
import com.application.demo.Book_my_show.entity.TicketEntity;
import com.application.demo.Book_my_show.entity.UserEntity;
import com.application.demo.Book_my_show.repository.ShowRepository;
import com.application.demo.Book_my_show.repository.TicketRepository;
import com.application.demo.Book_my_show.repository.UserRepository;
import com.application.demo.Book_my_show.requestdtos.TicketRequestDto;
import com.application.demo.Book_my_show.requestdtos.UserRequestDto;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService
{
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public String addTicket(TicketRequestDto ticketRequestDto) throws Exception
    {
        //convert the request dto into entity
        TicketEntity ticketEntity=TicketConvertor.convertTicketRequestDtoToTicketEntity(ticketRequestDto);

        //validation check wheather seats are avail
       boolean isSeatsAvailable= checkRequestedSeatAvailability(ticketRequestDto);
       if(isSeatsAvailable==false)
       {
           throw new Exception("requested seats are not available ,please select other seats");
       }

       //cal amount total of booking
       ShowEntity showEntity=showRepository.findById(ticketRequestDto.getShowId()).get();
       List<ShowSeatEntity> showSeatEntityList=showEntity.getListOfShowSeats();

       List<String> requestedSeats=ticketRequestDto.getRequestedSeats();

       double totalAmount=0.00;
       for(ShowSeatEntity showSeatEntity:showSeatEntityList)
       {
           if(requestedSeats.contains(showSeatEntity.getSeatNo()))
           {
               totalAmount=totalAmount+showSeatEntity.getPrice();
               showSeatEntity.setBooked(true);
               showSeatEntity.setBookedAt(new Date());
           }
       }
       ticketEntity.setTotalAmount(totalAmount);
       ticketEntity.setMoviename(showEntity.getMovieEntity().getMovieName());
       ticketEntity.setShowDateTime(showEntity.getShowDateTime());
       ticketEntity.setTheatername(showEntity.getTheaterEntity().getName());

       String allottedSeats=getAllottedSeats(requestedSeats);
       ticketEntity.setBookedSeats(allottedSeats);

       ticketEntity.setShowEntity(showEntity);
       UserEntity userEntity=userRepository.findById(ticketRequestDto.getUserId()).get();
       ticketEntity.setUserEntity(userEntity);

       ticketEntity=ticketRepository.save(ticketEntity);

       List<TicketEntity> ticketEntityList=showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
       showEntity.setListOfBookedTickets(ticketEntityList);

       showRepository.save(showEntity);

       List<TicketEntity> ticketEntityList1=userEntity.getBookedTickets();
       ticketEntityList1.add(ticketEntity);
       userEntity.setBookedTickets(ticketEntityList1);

        userRepository.save(userEntity);

        String bodyMail="Hi,this is  to confirm your booking for seats no/s : "+allottedSeats+" for the movie "+ticketEntity.getMoviename()+
                "and the show timing is : "+ticketEntity.getShowDateTime()+"and theater is "+ticketEntity.getTheatername()+
                " and your total amount is "+ticketEntity.getTotalAmount();

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("indiatosouthkorea@gmail.com");
        mimeMessageHelper.setTo(userEntity.getEmail());
        mimeMessageHelper.setText(bodyMail);
        mimeMessageHelper.setSubject("confirming your booked tickets!");
        javaMailSender.send(mimeMessage);

        return "Ticket has been booked successfully";
    }

    private String getAllottedSeats(List<String> requestedSeats)
    {
        String   result="";
        for(String seat:requestedSeats)
        {
            result=result+seat+", ";
        }
        return result;
    }


    private boolean checkRequestedSeatAvailability(TicketRequestDto ticketRequestDto)
    {
        int showId=ticketRequestDto.getShowId();
        List<String> requestedSeats=ticketRequestDto.getRequestedSeats();

        ShowEntity showEntity=showRepository.findById(showId).get();
        List<ShowSeatEntity> showSeatEntityList=showEntity.getListOfShowSeats();

        for(ShowSeatEntity showSeatEntity:showSeatEntityList)
        {
            String seatNo=showSeatEntity.getSeatNo();

            if(requestedSeats.contains(seatNo))
            {
                if(showSeatEntity.isBooked()==true)
                {
                    return false;//seat is already booked
                }
            }

        }
        return true;
    }

    public List<TicketEntity> getTicketsByPriceLessThan500()
    {
        List<TicketEntity> ticketEntityList=ticketRepository.getTicketsByPriceLessThan500();
        return ticketEntityList;
    }
}
