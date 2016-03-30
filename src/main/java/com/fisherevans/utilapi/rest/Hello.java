package com.fisherevans.utilapi.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Hello {
    private static final Logger logger = LoggerFactory.getLogger(Hello.class);
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        logger.info("Got a hello");
        return "Hello, World!";
    }
}
