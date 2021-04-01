package com.web.bmservice;

import com.web.bmservice.ws.BmWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BmJob {

    @Autowired
    BmWebSocket bmWebSocket;

    public void connectBm() {
        bmWebSocket.connect();
    }
}
