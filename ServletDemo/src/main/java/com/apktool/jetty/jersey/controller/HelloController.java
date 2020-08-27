package com.apktool.jetty.jersey.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * @author apktool
 * @package com.apktool.jetty.jersey.controller
 * @class HelloController
 * @description TODO
 * @date 2020-08-28 01:00
 */

@Path("/hello")
public class HelloController {
    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        msg = msg.replace("i", "o");
        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }

}
