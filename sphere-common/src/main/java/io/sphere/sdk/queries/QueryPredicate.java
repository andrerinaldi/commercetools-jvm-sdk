package io.sphere.sdk.queries;

import io.sphere.sdk.annotations.Unsafe;

public interface QueryPredicate<T> {
    QueryPredicate<T> or(QueryPredicate<T> other);

    QueryPredicate<T> and(QueryPredicate<T> other);

    /**
     * The predicate for the SPHERE.IO HTTP API, not url encoded.
     * Example: masterData(current(name(en="MB PREMIUM TECH T"))) and id = "e7ba4c75-b1bb-483d-94d8-2c4a10f78472"
     *
     * @return predicate as String
     */
    String toSphereQuery();

    @Unsafe
    static <T> QueryPredicate<T> of(final String sphereQuery) {
        return new SimpleQueryPredicate<>(sphereQuery);
    }
}