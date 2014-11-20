package controllers;

import java.util.Collection;

import play.mvc.Controller;
import flexjson.JSONSerializer;

public class DefaultController extends Controller {
	
	 /**
     * Render a 200 OK application/json response
     * @param model The Java object to serialize
     * @param js The serializer to be used
     */
    protected static void renderJSON(Object model, JSONSerializer js) {
    	String json = js.serialize(model);
		renderJSON(json);
    }

    /**
     * Render a 200 OK application/json response
     * @param models The Java collection to serialize
     * @param js The serializer to be used
     */
    protected static void renderJSON(Collection<Object> models, JSONSerializer js) {
    	String json = js.serialize(models);
    	renderJSON(json);
    }
    
}
