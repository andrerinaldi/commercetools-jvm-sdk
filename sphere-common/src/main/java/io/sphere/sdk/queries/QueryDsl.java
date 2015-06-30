package io.sphere.sdk.queries;

import io.sphere.sdk.expansion.ReferenceExpandeableDsl;
import io.sphere.sdk.http.HttpQueryParameter;

import java.util.List;
import static java.util.Arrays.asList;

/**
 *
 * @param <T> type of the query result
 * @param <C> type of the class implementing this class
 */
public interface QueryDsl<T, C> extends EntityQuery<T>, ReferenceExpandeableDsl<T, C> {
    /**
     * Returns an EntityQuery with the new predicate as predicate.
     * @param predicate the new predicate
     * @return an EntityQuery with predicate
     */
    C withPredicate(final QueryPredicate<T> predicate);

    /**
     * Returns a query with the new sort as sort.
     * @param sort list of sorts how the results of the query should be sorted
     * @return EntityQuery with sort
     */
    C withSort(final List<QuerySort<T>> sort);

    default C withSort(final QuerySort<T> sort) {
        return withSort(asList(sort));
    }

    C withLimit(final long limit);

    /**
     * Returns a new query with the new offset as offset.
     *
     * @param offset the number of items which should be omitted in the query result.
     * @return a new query
     * @throws java.lang.IllegalArgumentException if offset is
     * not between {@value io.sphere.sdk.queries.Query#MIN_OFFSET} and {@value io.sphere.sdk.queries.Query#MAX_OFFSET}.
     */
    C withOffset(final long offset);

    C withAdditionalQueryParameters(final List<HttpQueryParameter> additionalQueryParameters);
}