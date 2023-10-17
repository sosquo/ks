package com.kuang.comsumer.service;

import com.kuang.provider.service.TicketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //远程引用指定的服务，他会按照全类名进行匹配，看谁给注册中心注册了这个全类名
    @Reference
    TicketService ticketService;

    public String bugTicket() {
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心买到了 = " + ticket);
        return ticket;
    }

}
