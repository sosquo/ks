package com.kuang.provider.service;

import org.apache.dubbo.config.annotation.Service;

@Service
@org.springframework.stereotype.Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《狂神说Java》";
    }
}
