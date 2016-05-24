package com.widehouse.web;

import com.widehouse.event.Event;
import com.widehouse.event.EventService;
import com.widehouse.user.User;
import com.widehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kiel on 2016. 5. 24..
 */
@Controller
public class IndexController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        User user = userRepository.findOne(1L);
        LocalDate defaultDate = LocalDate.now();
        List<Event> events = eventService.listUserEventByMonth(user, defaultDate);

        model.addAttribute("defaultDate", defaultDate);
        model.addAttribute("events", events.stream().map(EventView::new).collect(Collectors.toList()));

        return "index";
    }
}
