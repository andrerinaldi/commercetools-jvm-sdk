package io.sphere.sdk.customobjects.demo;

import com.google.gson.Gson;
import io.sphere.sdk.customobjects.CustomObject;
import io.sphere.sdk.customobjects.queries.CustomObjectCustomJsonMappingFetchByKey;
import io.sphere.sdk.http.HttpResponse;

import java.util.function.Function;

public class GsonFooCustomObjectFetchByKey extends CustomObjectCustomJsonMappingFetchByKey<GsonFoo> {

    public GsonFooCustomObjectFetchByKey(final String container, final String key) {
        super(container, key);
    }

    @Override
    protected Function<HttpResponse, CustomObject<GsonFoo>> deserializeCustomObject() {
        return httpResponse -> {
            final String jsonAsString = getBodyAsString(httpResponse);
            return new Gson().fromJson(jsonAsString, GsonFooCustomObject.class);
        };
    }
}
