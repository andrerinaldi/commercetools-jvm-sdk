package io.sphere.sdk.products.search;

import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.search.model.*;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;

public class ProductProjectionSortSearchModel extends ProductDataSortSearchModel {

    ProductProjectionSortSearchModel(@Nullable final SearchModel<ProductProjection> parent, @Nullable final String pathSegment) {
        super(parent, pathSegment);
    }

    @Override
    public ProductVariantSortSearchModel allVariants() {
        return super.allVariants();
    }

    @Override
    public LocalizedStringSortSearchModel<ProductProjection, SingleValueSortSearchModel<ProductProjection>> name() {
        return super.name();
    }

    @Override
    public SingleValueSortSearchModel<ProductProjection> createdAt() {
        return super.createdAt();
    }

    @Override
    public SingleValueSortSearchModel<ProductProjection> lastModifiedAt() {
        return super.lastModifiedAt();
    }
}
