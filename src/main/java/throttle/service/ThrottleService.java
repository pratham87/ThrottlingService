package throttle.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import throttle.utility.AggregateCount;
import throttle.utility.RequestData;

@Path("/")
public class ThrottleService {

	@Path("/track")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response enterTrackData(RequestData request) {
		System.out.println("Enter track data:" + request.toString());
		int resp = TrackDataCache.getInstance().addTrackData(request.getType(), request.getTime());
		if (resp < 0)
			return Response.status(400).entity("Error when posting the request").build();
		else
			return Response.status(201).entity("Request Post successful " + "\n" + request.toString()).build();
	}

	@Path("/aggregate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public AggregateCount getAggregateCount(@QueryParam("type") String type, @QueryParam("duration") int duration,
			@QueryParam("time") long time) {
		System.out.println("AggregateCount: type=" + type + " duration=" + duration + " time=" + time);
		int count = TrackDataCache.getInstance().calculateCount(type, duration, time);
		AggregateCount ac = new AggregateCount();
		ac.setCount(count);
		ac.setType(type);
		return ac;
	}

}
