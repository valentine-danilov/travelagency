package com.epam.travelagency.web.controller;

import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.service.HotelService;
import com.epam.travelagency.web.exception.BadRequestException;
import com.epam.travelagency.web.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public String getHotelList(Pagination<Hotel> pagination, ModelMap model) throws BadRequestException {
        Long pageNumber = hotelService.getPageNumber(10);
        pagination.setPageNumber(pageNumber);
        pagination.setPageSize(10);

        if (pagination.getPage() == null) {
            pagination.setPage(1);
        }

        if (pagination.getPage() < 1) {
            throw new BadRequestException("Requested page can't be loaded.");
        }

        List<Hotel> hotels = hotelService.findAllWithOffsetAndMaxResult(pagination.getOffset(), pagination.getPageSize());
        pagination.setContent(hotels);
        model.addAttribute("pagination", pagination);
        return "/hotels";
    }

    @GetMapping("/hotel")
    public String tourById(@RequestParam Integer id, ModelMap model) {
        Hotel hotel = hotelService.readById(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("API_KEY", "AIzaSyDk7s12knE17olahfF5JXsr9d-wSDqSq1k");
        return "/hotel";
    }
}
