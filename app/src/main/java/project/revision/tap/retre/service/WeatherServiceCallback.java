package project.revision.tap.retre.service;

import project.revision.tap.retre.Data.Channel;

/**
 * Created by prakash on 4/27/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFalure(Exception exception);
}
