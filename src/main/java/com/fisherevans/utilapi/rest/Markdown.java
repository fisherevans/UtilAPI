package com.fisherevans.utilapi.rest;

import org.pegdown.Extensions;
import org.pegdown.Parser;
import org.pegdown.PegDownProcessor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/markdown")
public class Markdown {
    @Path("/render")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public String renderMarkdown(String body) {
        PegDownProcessor processor = new PegDownProcessor(
                Extensions.FENCED_CODE_BLOCKS |
                Extensions.TABLES
        );
        String html = processor.markdownToHtml(body);
        return html;
    }
}
